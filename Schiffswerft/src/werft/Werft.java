package werft;

import definitions.Definitions;
import inout.InOut;
import inout.InOutException;
import spiel.KonkursException;
import schiffe.Schiff;
import schiffe.Frachtschiff;
import schiffe.Passagierschiff;
import schiffe.Tankschiff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Werft {

    // Felder der Werft
    private double kapital; // Das aktuelle Kapital der Werft (in Mio EUR)
    private int monat;      // Der aktuelle Spielmonat
    private List<Schiff> gebauteSchiffe; // Liste aller Schiffe

    public Werft() {
        this.kapital = Definitions.ANFANGSKAPITAL;
        this.monat = 0;
        this.gebauteSchiffe = new ArrayList<>();
        InOut.printString("Werft gestartet mit: " + kapital + " Mio EUR");
    }

    public void arbeitetEinenMonat() throws KonkursException {
        monat++;
        InOut.printString("\n--- Monat " + monat + " wird simuliert ---");

        Iterator<Schiff> it = gebauteSchiffe.iterator();

        while (it.hasNext()) {
            Schiff s = it.next();

            // 1. Gewinn einstreichen
            double gewinn = s.getGewinn();
            kapital += gewinn;

            boolean istGesunken = s.altern();

            if (istGesunken) {
                // Regel-Bergungskosten= 5 * des Neupreises
                double strafe = s.getBaukosten() * 5.0;
                kapital -= strafe;

                InOut.printString("ALARM! " + s.getTyp() + " (ID: " + s.getId() + ") ist GESUNKEN!");
                InOut.printString("Bergungskosten & Strafe: -" + String.format("%.2f", strafe) + " Mio EUR.");

                it.remove();
            }
        }

        if (kapital < 0) {
            throw new KonkursException();
        }
    }

    public void zustandAusgeben() {
        InOut.printString("\n--- Zustand nach Monat " + monat + " ---");
        InOut.printString("Aktuelles Kapital: " + String.format("%.2f", kapital) + " Mio EUR");
        InOut.printString("Anzahl aktiver Schiffe: " + gebauteSchiffe.size());

        if (!gebauteSchiffe.isEmpty()) {
            InOut.printString("Schiffsflotte:");
            for (Schiff schiff : gebauteSchiffe) {
                InOut.printString("  " + schiff.toString());
            }
        }
    }

    // Hilfsmethode beim Bauen

    private void schiffBauen(Schiff schiff) throws KonkursException {
        double kosten = schiff.getBaukosten();

        if (kapital < kosten) {
            InOut.printString("Nicht genügend Kapital (" + String.format("%.2f", kapital)
                    + " Mio) zum Bau von " + schiff.getTyp() + " (Kosten: " + kosten + " Mio).");
            return;
        }

        kapital -= kosten;
        gebauteSchiffe.add(schiff);

        InOut.printString(schiff.getTyp() + " (ID: " + schiff.getId()
                + ") erfolgreich gebaut. Kosten: " + kosten + " Mio.");

        if (kapital < 0) {
            throw new KonkursException();
        }
    }

    // Aktionen

    public void frachtschiffBauen() throws KonkursException {
        schiffBauen(new Frachtschiff());
    }

    public void passagierschiffBauen() throws KonkursException {
        schiffBauen(new Passagierschiff());
    }

    public void tankschiffBauen() throws KonkursException {
        schiffBauen(new Tankschiff());
    }

    public void schiffStreichen() throws KonkursException {
        if (gebauteSchiffe.isEmpty()) {
            InOut.printString("Keine Schiffe zum Streichen vorhanden.");
            return;
        }

        try {
            int id = InOut.readInt("Bitte geben Sie die ID des Schiffes ein (Streichen):");
            Schiff target = findSchiffById(id);

            if (target != null) {
                double kosten = target.getStreichKosten();

                if (kapital < kosten) {
                    InOut.printString("Zu wenig Geld zum Streichen! Benötigt: " + kosten + " Mio.");
                    return;
                }

                // Bezahlen
                kapital -= kosten;
                target.streichen();

                InOut.printString(target.getTyp() + " (ID: " + id + ") gestrichen. Kosten: " + kosten + " Mio.");
                InOut.printString("Neuer Zustand: " + String.format("%.1f%%", target.getZustand() * 100));

                if (kapital < 0) throw new KonkursException();
            } else {
                InOut.printString("Schiff mit ID " + id + " nicht gefunden.");
            }
        } catch (InOutException e) {
            InOut.printString("Abbruch.");
        }
    }

    public void schiffVerschrotten() throws KonkursException {
        if (gebauteSchiffe.isEmpty()) {
            InOut.printString("Keine Schiffe zum Verschrotten vorhanden.");
            return;
        }

        try {
            int id = InOut.readInt("Bitte geben Sie die ID des zu verschrottenden Schiffs ein:");
            Schiff target = findSchiffById(id);

            if (target != null) {
                // Regel- Verschrotten= 1/10 des Schiffspreises
                double kosten = target.getBaukosten() / 10.0;

                if (kapital < kosten) {
                    InOut.printString("Warnung: Selbst zum Verschrotten fehlt Geld! (Kosten: " + kosten + ")");
                    // Wir erlauben es trotzdem mal, f�hrt aber zum Konkurs
                }

                // Bezahlen
                kapital -= kosten;
                // Entfernen
                gebauteSchiffe.remove(target);

                InOut.printString(target.getTyp() + " (ID: " + id + ") verschrottet. Kosten: " + String.format("%.2f", kosten) + " Mio.");

                if (kapital < 0) throw new KonkursException();
            } else {
                InOut.printString("Schiff mit ID " + id + " nicht gefunden.");
            }
        } catch (InOutException e) {
            InOut.printString("Abbruch.");
        }
    }

    // Hilfsmethode- Sucht Schiff über ID
    private Schiff findSchiffById(int id) {
        for (Schiff s : gebauteSchiffe) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}