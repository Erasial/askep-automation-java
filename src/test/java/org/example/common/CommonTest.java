package org.example.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

public class CommonTest {
    @Test
    void localDateTest() {
        String result = convertDate("20.03.2022");
        Assertions.assertEquals("20.03.2022", result, "stupid nigga fix that");

        String result2 = convertDate("2022-03-20");
        Assertions.assertEquals("20.03.2022", result2, "stupid nigga fix that");
    }

    @Test void isAdultTest() {
        boolean adult = isAdult("2006-05-07");
        Assertions.assertTrue(adult);

        boolean notAdult = isAdult("2008-07-25");
        Assertions.assertFalse(notAdult);
    }

    @Test
    void fillEntriesTest() {
        ArrayList<Entry> entries = fillEntries("main");
        for (Entry entry : entries) {
            System.out.print(entry.getName() + " " +
                    entry.getBirthdate() + " " +
                    entry.getTooth() + " " +
                    entry.getDiagnose() + " " +
                    entry.getProcedures() + " " +
                    entry.getDate()
            );
            System.out.println();
        }
    }

    public String convertDate(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            return date;
        }
    }

    public boolean isAdult(String birthdate) {
        LocalDate today = LocalDate.now();
        LocalDate localBirthDate = LocalDate.parse(birthdate);

        long diff = ChronoUnit.YEARS.between(localBirthDate, today);
        return diff >= 18;
    }

    public ArrayList<Entry> fillEntries(String sheetName) {
        ArrayList<Entry> entries = new ArrayList<>();

        try
        {
            LocalDate minDate = LocalDate.of(1899, 12, 30);

            File file = new File(Strings.tableDir);   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet(sheetName);     //

            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator(); //iterating over each column
                if (row.getRowNum() == 0) {
                    continue;
                }
                if (row.getCell(0) == null) {
                    break;
                }
                String name = "";
                String birthdate = "";
                String tooth = "";
                String diagnose = "";
                String procedures = "";
                LocalDate date = LocalDate.now();

                for (int i = 0; i < 6; i++) {
                    Cell cell = cellIterator.next();
                    String value = "";
                    if (cell.getCellType() == CellType.FORMULA) {
                        value = switch (cell.getCachedFormulaResultType()) {
                            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
                            case STRING -> cell.getRichStringCellValue().toString();
                            default -> value;
                        };
                    } else {
                        value = switch (cell.getCellType()) {
                            case STRING -> cell.getStringCellValue();
                            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
                            default -> value;
                        };
                    }

                    switch (i) {
                        case 0:
                            name = value;
                            break;
                        case 1:
                            birthdate = value;
                            break;
                        case 2:
                            tooth = value;
                            break;
                        case 3:
                            diagnose = value;
                            break;
                        case 4:
                            procedures = value;
                            break;
                        case 5:
                            date = minDate.plusDays((int) Double.parseDouble(value));
                            break;
                    }
                }

                Entry entry = new Entry(name, birthdate, tooth, diagnose, procedures, date);
                entries.add(entry);
            }
            fis.close();
            wb.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return entries;
    }
}
