package com.ftn.modul3.zavrsni.jwd.Pregledi.enumeration;

public enum Pol {
    MUSKI,
    ZENSKI;

    public static Pol fromString(String name) {
        return Pol.valueOf(name.toUpperCase());
    }
}
