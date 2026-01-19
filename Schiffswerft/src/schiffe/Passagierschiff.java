package schiffe;

import definitions.Definitions;

public class Passagierschiff extends Schiff {

    public Passagierschiff() {
        super(Definitions.PASSAGIERSCHIFF_KOSTEN,
                Definitions.PASSAGIERSCHIFF_GEWINN,
                Definitions.PASSAGIERSCHIFF_STREICHEN);
    }

    @Override
    public String getTyp() {
        return "Passagierschiff";
    }
}