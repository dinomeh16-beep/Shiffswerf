package spiel;

public class SpielendeExeption extends Exception {
    private static final long serialVersionUID = 1L;

    public SpielendeExeption() {
        super("Spiel beendet durch Benutzerauswahl.");
    }

    public SpielendeExeption(String message) {
        super(message);
    }
}