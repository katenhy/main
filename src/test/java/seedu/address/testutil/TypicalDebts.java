package seedu.address.testutil;

import static seedu.address.testutil.TypicalUsers.getTypicalUsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.accounting.Amount;
import seedu.address.model.accounting.Debt;


public class TypicalDebts {
    public static final Debt DEBT_A =
            new Debt(getTypicalUsers().get(0),
                    getTypicalUsers().get(1),
                    new Amount("7"));

    public static final Debt DEBT_B =
            new Debt(getTypicalUsers().get(1),
                    getTypicalUsers().get(0),
                    new Amount("5"));

    public static final Debt DEBT_C =
            new Debt(getTypicalUsers().get(0),
                    getTypicalUsers().get(1),
                    new Amount("4"));

    public TypicalDebts() {}

    public static List<Debt> getTypicalDebts() {
        return new ArrayList<>(Arrays.asList(DEBT_A,DEBT_B,DEBT_C));
    }
}


