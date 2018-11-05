package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of debts that enforces uniqueness between its elements and does not allow nulls.
 * Supports a minimal set of list operations.
 *
 */
public class UniqueFriendList implements Iterable<Friendship> {
    private final ObservableList<Friendship> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent friendship as the given argument.
     */
    public boolean contains(Friendship toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a debt to the list.
     * The debt must not already exist in the list.
     */
    public void add(Friendship toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent friendship from the list.
     * The friendship must exist in the list.
     */
    public void remove(Friendship toRemove) {
        requireNonNull(toRemove);
        internalList.remove(toRemove);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Friendship> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    public Iterator<Friendship> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueFriendList // instanceof handles nulls
                && internalList.equals(((UniqueFriendList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code debts} contains only unique friends.
     */
    private boolean friendsAreUnique(List<Friendship> friends) {
        for (int i = 0; i < friends.size() - 1; i++) {
            for (int j = i + 1; j < friends.size(); j++) {
                if (friends.get(i).equals(friends.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
