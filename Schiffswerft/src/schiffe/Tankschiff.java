package schiffe;

import definitions.Definitions;

public class Tankschiff extends Schiff {

    public Tankschiff() {
        super(Definitions.TANKER_KOSTEN,
                Definitions.TANKER_GEWINN,
                Definitions.TANKER_STREICHEN);
    }

    @Override
    public String getTyp() {
        return "Tankschiff";
    }
}