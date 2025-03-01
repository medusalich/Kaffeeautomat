package de.medusalich;

import de.medusalich.produkte.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Repräsentiert den Kaffeeautomaten, der Produkte verwaltet, Zahlungen verarbeitet
 * und den Bestand überwacht.
 */
public class KaffeeAutomat {

    private final StringBuilder sb = new StringBuilder();
    private final Scanner scanner = new Scanner(System.in);
    private final List<AutomatenProdukt> produkte = new ArrayList<>();
    private final String dateiName = "protokoll.txt";

    public KaffeeAutomat() {
        produkte.add(new AutomatenProdukt(new Espresso(), 1.50, 0));
        produkte.add(new AutomatenProdukt(new LatteMacchiato(), 2.50, 20));
        produkte.add(new AutomatenProdukt(new Cappuccino(), 2.00, 20));
        produkte.add(new AutomatenProdukt(new SchwarzerKaffee(), 0.50, 20));
        produkte.add(new AutomatenProdukt(new HeisseSchokolade(), 1.00, 20));
        produkte.add(new AutomatenProdukt(new Tomatensuppe(), 0.80, 10));
        produkte.add(new AutomatenProdukt(new HeissesWasser(), 0.00, 30));
    }

    /**
     * Schreibt die aktuellen Bestände der Produkte in eine Datei.
     */
    public void schreibeInDatei() {
        try (FileWriter fw = new FileWriter(dateiName)) {
            for (AutomatenProdukt produkt : produkte) {
                fw.write(produkt.getAnzahl() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben in die Datei: " + e.getMessage());
        }
    }

    /**
     * Liest die Bestände der Produkte aus einer Datei und aktualisiert die internen Werte.
     */
    public void leseAusDatei() {
        try (BufferedReader br = new BufferedReader(new FileReader(dateiName))) {
            String zeile;
            int i = 0;
            while ((zeile = br.readLine()) != null) {
                produkte.get(i).setAnzahl(Integer.valueOf(zeile));
                i++;
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
    }

    public List<AutomatenProdukt> getProdukte() {
        return produkte;
    }

    /**
     * Verarbeitet die Zahlung für das ausgewählte Produkt. Fordert den Benutzer auf,
     * Münzen einzuwerfen, und gibt Rückmeldungen zur Zahlung.
     */
    public void bezahlen(AutomatenProdukt produkt) {

        double eingeworfenerBetrag = 0;
        while (true) {
            System.out.println("Münzen einwerfen: ");
            double betrag = scanner.nextDouble();
            eingeworfenerBetrag += betrag;

            if (eingeworfenerBetrag < produkt.getPreis()) {
                sb.setLength(0);
                sb.append("\n")
                        .append("Nicht genügend Geld. ")
                        .append("\n")
                        .append("Der Preis für ")
                        .append(produkt.getProdukt().getName())
                        .append(" beträgt ")
                        .append(String.format("%.2f", produkt.getPreis()))
                        .append(" Euro. ");
                System.out.println(sb);
                continue;
            }

            sb.setLength(0);
            sb.append("\n").append("Zahlung erfolgreich. ");
            if (eingeworfenerBetrag > produkt.getPreis()) {
                double rueckgeld = eingeworfenerBetrag - produkt.getPreis();
                sb.append("\n")
                        .append("Du bekommst ")
                        .append(String.format("%.2f", rueckgeld))
                        .append(" Euro Rückgeld. ");
            }
            sb.append("\n").append(produkt.getProdukt().getName()).append(" wird zubereitet. ");
            System.out.println(sb);
            break;
        }
    }

    /**
     * Produziert das ausgewählte Produkt und gibt eine entsprechende Meldung aus.
     */
    public void produziert(AutomatenProdukt produkt) {
        sb.setLength(0);
        sb.append("Einen Moment Bitte :)").append("\n");
        sb.append(produkt.getProdukt().getName()).append(" ist fertig, VORSICHT HEIß!");
        produkt.reduziereAnzahl();
        System.out.println(sb);
    }

    /**
     * Überprüft, ob das angegebene Produkt im Automaten verfügbar ist.
     *
     * @param produkt Das Produkt, dessen Bestand überprüft wird.
     * @return true, wenn das Produkt verfügbar ist, andernfalls false.
     */
    public boolean bestandPruefen(AutomatenProdukt produkt) {
        return produkt.getAnzahl() > 0;
    }
}