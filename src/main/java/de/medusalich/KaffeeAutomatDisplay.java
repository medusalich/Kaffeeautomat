package de.medusalich;

import java.util.List;
import java.util.Scanner;

/**
 * Die Hauptklasse für die Anzeige und Interaktion mit dem Kaffeeautomaten.
 * Sie ermöglicht dem Benutzer, Produkte auszuwählen und Zahlungen durchzuführen.
 */
public class KaffeeAutomatDisplay {

    /**
     * Startet die Anzeige des Kaffeeautomaten.
     */
    public static void main(String[] args) {

        display();

    }

    /**
     * Zeigt die verfügbaren Produkte des Kaffeeautomaten an und ermöglicht dem Benutzer,
     * ein Produkt auszuwählen. Überprüft den Bestand und führt die Zahlung durch.
     */
    public static void display() {

        Scanner scanner = new Scanner(System.in);
        KaffeeAutomat automat = new KaffeeAutomat();

        automat.leseAusDatei();

        AutomatenProdukt produkt = null;

        while (true) {
            List<AutomatenProdukt> produkte = automat.getProdukte();
            for (int i = 0; i < produkte.size(); i++) {
                int position = i + 1;
                String name = produkte.get(i).getProdukt().getName();
                int menge = produkte.get(i).getProdukt().getMl();
                double preis = produkte.get(i).getPreis();
                System.out.printf("%d %-20s %3d ml %6.2f\n", position, name, menge, preis);

            }
            System.out.println("\nWählen die Zahl für ein Getränk aus: ");
            int auswahl = scanner.nextInt();

            if ((auswahl > 0) && (auswahl <= produkte.size())) {
                produkt = produkte.get(auswahl - 1);
                if (automat.bestandPruefen(produkt)) {
                    break;
                } else {
                    System.out.println("Getränk leer, bitte Mitarbeiter informieren.\n");
                }
            }
            else {
                System.out.println("Ungültige Auswahl.");
            }

        }

        automat.bezahlen(produkt);

        automat.produziert(produkt);

        automat.schreibeInDatei();
    }
}
