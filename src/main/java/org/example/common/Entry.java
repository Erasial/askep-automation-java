package org.example.common;

import java.time.LocalDate;

public class Entry {
    private final String name;
    private final String birthdate;
    private final String tooth;
    private final String diagnose;
    private final String procedures;
    private final LocalDate date;

    public Entry(String name, String birthdate, String tooth, String diagnose, String operations, LocalDate date) {
        this.name = name;
        this.birthdate = birthdate;
        this.tooth = tooth;
        this.diagnose = diagnose;
        this.procedures = operations;
        this.date = date;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getTooth() {
        return tooth;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public String getProcedures() {
        return procedures;
    }

    public LocalDate getDate() {
        return date;
    }
}


