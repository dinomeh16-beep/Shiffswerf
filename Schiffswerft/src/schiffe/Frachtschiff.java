package schiffe;

import definitions.Definitions;

public class Frachtschiff extends Schiff {

    public Frachtschiff() {
        super(Definitions.FRACHTSCHIFF_KOSTEN,
                Definitions.FRACHTSCHIFF_GEWINN,
                Definitions.FRACHTSCHIFF_STREICHEN);
    }

    @Override
    public String getTyp() {
        return "Frachtschiff";
    }
}