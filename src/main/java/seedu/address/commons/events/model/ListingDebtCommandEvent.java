package seedu.address.commons.events.model;

import javafx.collections.ObservableList;
import seedu.address.commons.events.BaseEvent;
import seedu.address.model.accounting.Debt;

public class ListingDebtCommandEvent extends BaseEvent {

    public final ObservableList<Debt> listingItem;

    public ListingDebtCommandEvent(ObservableList<Debt> listingItem) {
        this.listingItem = listingItem;
    }

    @Override
    public String toString() {
        return "Listing Debt";
    }
}
