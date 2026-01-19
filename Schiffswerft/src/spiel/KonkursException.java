package spiel;

public class KonkursException extends SpielendeExeption {
    private static final long serialVersionUID = 1L;

    public KonkursException() {
        super("Konkurs! Das Kapital ist aufgebraucht.");
    }
}
