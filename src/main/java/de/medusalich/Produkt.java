package de.medusalich;

/**
 * Repräsentiert ein Produkt mit einem Namen und einer Menge in Millilitern.
 */
public class Produkt {

    private final String name;
    private final int ml;

    protected Produkt(String name, int ml) {
        this.name = name;
        this.ml = ml;
    }

    public String getName() {
        return name;
    }

    public int getMl() {
        return ml;
    }
}
