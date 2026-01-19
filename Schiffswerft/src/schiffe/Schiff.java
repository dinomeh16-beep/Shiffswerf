package schiffe;

public abstract class Schiff {
    private static int nextId = 1;
    protected final int id;
    protected double baukosten;
    protected double monatsGewinn;
    protected double streichKosten;

    protected double zustand;
    protected int anzahlAnstriche;

    public Schiff(double baukosten, double gewinn, double streichKosten) {
        this.id = nextId++;
        this.baukosten = baukosten;
        this.monatsGewinn = gewinn;
        this.streichKosten = streichKosten;
        this.zustand = 1.0; // 100% intakt bei Bau
        this.anzahlAnstriche = 0;
    }


    public boolean altern() {
        // Rostfaktor 0.92 pro Monat
        this.zustand = this.zustand * 0.92;
        return this.zustand < 0.25;
    }

    public void streichen() {
        this.anzahlAnstriche++;
        // Formel: 100% - (5% * Anzahl Anstriche)
        this.zustand = 1.0 - (0.05 * this.anzahlAnstriche);
        // Sicherheit-Zustand geht nicht unter 0
        if (this.zustand < 0) this.zustand = 0;
    }

    // Getter
    public int getId() { return id; }
    public double getBaukosten() { return baukosten; }
    public double getGewinn() { return monatsGewinn; }
    public double getStreichKosten() { return streichKosten; }
    public double getZustand() { return zustand; }
    public abstract String getTyp();

    @Override
    public String toString() {
        return String.format("%s (ID: %d, Zustand: %.1f%%)", getTyp(), id, zustand * 100);
    }
}