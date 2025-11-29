package org.example.common;

public class PalliativeEntry {
    private final String name;
    private final String refLink;
    private final String encounterLink;

    public PalliativeEntry(String name, String refLink, String encounter_link) {
        this.name = name;
        this.refLink = refLink;
        this.encounterLink = encounter_link;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getRefLink() {
        return refLink;
    }

    public String getEncounterLink() {
        return encounterLink;
    }
}


