package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalDebts.getTypicalDebts;

import org.junit.Test;

public class XmlAdaptedDebtTest {

    @Test
    public void toModelType_validDebtDetails_returnsDebt() throws Exception {
        XmlAdaptedDebt debt = new XmlAdaptedDebt(getTypicalDebts().get(0));
        assertEquals(debt, getTypicalDebts().get(0));
    }

}
