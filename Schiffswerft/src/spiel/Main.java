package spiel;

import inout.InOut;
import inout.InOutException;
import werft.Werft;

public class Main {
    public static void main(String[] args) {
        Werft dieWerft = new Werft();

        try {
            while (true) {
                // Werft arbeitet einen Monat
                dieWerft.arbeitetEinenMonat();
                // Ausgabe: Zustand der Werft
                dieWerft.zustandAusgeben();
                // Auswahl
                int auswahl = InOut.readMenu("Was ist zu tun?",
                        "Ein Frachtschiff bauen@" +
                                "Ein Passagierschiff bauen@" +
                                "Ein Tankschiff bauen@" +
                                "Ein Schiff streichen@" +
                                "Ein Schiff verschrotte@" +
                                "Nichts tun@" +
                                "Spielende");
                switch (auswahl) {
                    case 1 ->// ein Frachtschiff bauen
                    {
                        dieWerft.frachtschiffBauen();
                    }
                    case 2 -> // ein Passagierschiff bauen
                    {
                        dieWerft.passagierschiffBauen();
                    }
                    case 3 -> // ein Tankschiff bauen
                    {
                        dieWerft.tankschiffBauen();
                    }
                    case 4 -> // ein Schiff streichen
                    {
                        dieWerft.schiffStreichen();
                    }
                    case 5 -> // ein Schiff verschrotten
                    {
                        dieWerft.schiffVerschrotten();
                    }
                    case 6 -> // Pause
                    {
                        InOut.printString("Einen monat pausiert");
                    }
                    case 7 -> // Spielende
                    {
                        throw new SpielendeExeption();
                    }
                    default -> {
                        InOut.printString("Unknown menu entry!");
                    }
                }
            }
            // ...
        } catch (InOutException x) {
            // Fängt I/O-Fehler ab (diese ist nicht mit den anderen verwandt)
            InOut.printString("Fehleingabe, Spielende");
        } catch (KonkursException x) { // <--- 1. Zuerst die spezifische Ausnahme
            InOut.printString("Spielende wegen Konkurs!");
        } catch (SpielendeExeption x) { // <--- 2. Dann die allgemeine Ausnahme
            InOut.printString("Spielende durch Benutzerauswahl!");
        }

    }

}