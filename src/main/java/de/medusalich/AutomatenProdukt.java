package de.medusalich;

/**
 * Repräsentiert ein Produkt im Kaffeeautomaten mit Preis und Verfügbarkeit.
 */
public class AutomatenProdukt {

    private Produkt produkt;
    private double preis;
    private int anzahl;

    public AutomatenProdukt(Produkt produkt, double preis, int anzahl) {
        this.produkt = produkt;
        this.preis = preis;
        this.anzahl = anzahl;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public double getPreis() {
        return preis;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public void reduziereAnzahl() {
        anzahl -= 1;
    }
}