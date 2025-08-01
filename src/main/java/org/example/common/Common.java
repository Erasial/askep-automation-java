package org.example.common;

import org.apache.poi.ss.usermodel.CellType;
import org.example.locators.CommonLocators;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Common {
    private final WebDriver driver;

    public Common(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findLast(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements.get(elements.size() - 1);
    }

    public boolean isAdult(String birthdate) {
        LocalDate today = LocalDate.now();
        LocalDate localBirthDate = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

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

    public String convertDate(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            return date;
        }
    }

    public boolean noInitialData() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(1)).until(
                    ExpectedConditions.visibilityOfElementLocated(
                            CommonLocators.noDataTextXpath)
            );
            return true;
        }
        catch (TimeoutException e) {
            return false;
        }
    }
}
