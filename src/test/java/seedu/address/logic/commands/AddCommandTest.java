package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.Name;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.accounting.Amount;
import seedu.address.model.accounting.Debt;
import seedu.address.model.accounting.DebtId;
import seedu.address.model.accounting.DebtStatus;
import seedu.address.model.group.Friendship;
import seedu.address.model.group.Group;
import seedu.address.model.jio.Jio;
import seedu.address.model.restaurant.Rating;
import seedu.address.model.restaurant.Restaurant;
import seedu.address.model.restaurant.WrittenReview;
import seedu.address.model.timetable.Date;
import seedu.address.model.timetable.Week;
import seedu.address.model.user.Password;
import seedu.address.model.user.User;
import seedu.address.model.user.Username;
import seedu.address.testutil.RestaurantBuilder;

public class AddCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullRestaurant_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddCommand(null);
    }

    @Test
    public void execute_restaurantAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingRestaurantAdded modelStub = new ModelStubAcceptingRestaurantAdded();
        Restaurant validRestaurant = new RestaurantBuilder().build();

        CommandResult commandResult = new AddCommand(validRestaurant).execute(modelStub, commandHistory);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validRestaurant), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validRestaurant), modelStub.restaurantsAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateRestaurant_throwsCommandException() throws Exception {
        Restaurant validRestaurant = new RestaurantBuilder().build();
        AddCommand addCommand = new AddCommand(validRestaurant);
        ModelStub modelStub = new ModelStubWithRestaurant(validRestaurant);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddCommand.MESSAGE_DUPLICATE_RESTAURANT);
        addCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Restaurant alice = new RestaurantBuilder().withName("Alice").build();
        Restaurant bob = new RestaurantBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different restaurant -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void addRestaurant(Restaurant restaurant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRestaurant(Restaurant restaurant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRestaurant(Restaurant target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateRestaurant(Restaurant target, Restaurant editedRestaurant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Restaurant> getFilteredRestaurantList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRestaurantList(Predicate<Restaurant> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDebtId(DebtId debtId) {
            return false;
        }

        @Override
        public boolean matchDebtToAmount(DebtId debtId, Amount amount) {
            return false;
        }

        @Override
        public boolean matchDebtToUser(DebtId debtId, Username user) {
            return false;
        }

        @Override
        public boolean matchDebtToStatus(DebtId debtId, DebtStatus status) {
            return false;
        }

        @Override
        public boolean debtExist(Username debtorUsername) {
            return false;
        }

        @Override
        public boolean allowToClear(Username debtorUsername, Amount amount) {
            return false;
        }

        @Override
        public void addDebt(Username debtorUsername, Amount amount) {
        }

        @Override
        public void addGroupDebt(Name groupName, Amount amount) {

        }

        @Override
        public void clearDebt(Username debtorUsername, Amount amount) {
        }
        @Override
        public void acceptedDebtRequest(Username creditorUsername, Amount amount, DebtId debtId) {
        }
        @Override
        public void deleteDebtRequest(Username creditorUsername, Amount amount, DebtId debtId) {
        }

        @Override
        public ObservableList<Debt> getDebtList() {
            return null;
        }

        @Override
        public ObservableList<Debt> getCreditorList() {
            return null;
        }

        @Override
        public ObservableList<Debt> getDebtorList() {
            return null;
        }

        @Override
        public ObservableList<Debt> getDebtRequestReceived() {
            return null;
        }

        @Override
        public ObservableList<Debt> getDebtRequestSent() {
            return null;
        }

        @Override
        public void debtListing(ObservableList<Debt> list) {

        }
        // To be Done Later, fake tests for now

        @Override
        public ObservableList<Jio> getJioList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredJioList(Predicate<Jio> predicate) {}

        @Override
        public boolean hasJio(Jio jio) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasJioName(Name jioName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeJioOfName(Name jioName) {}

        @Override
        public void createJio(Jio jio) {}

        @Override
        public boolean isCurrentUserInJioOfName(Name jioName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCurrentUserToJioOfName(Name jioName) {}

        @Override
        public boolean isCurrentUserCreatorOfJio(Name jioName) {
            return true;
        }

        @Override
        public void addUser(User user) {}

        @Override
        public boolean hasUser(Username username) {
            return true;
        }

        @Override
        public boolean verifyLogin(Username username, Password password) {
            return true;
        }

        @Override
        public boolean isCurrentlyLoggedIn() {
            return true;
        }

        @Override
        public void loginUser(User user){}

        @Override
        public void loginUser(Username username){}

        @Override
        public void logoutUser() {}

        @Override
        public void displayProfile() {}

        @Override
        public void addUserReview(Restaurant restaurant, Rating rating, WrittenReview writtenReview) {}

        @Override
        public boolean hasUsernameSentRequest(Username friendUsername) {
            return true;
        }

        @Override
        public boolean hasUsernameFriendRequest(Username friendusername) {
            return true;
        }

        @Override
        public boolean hasUsernameFriend(Username friendUsername) {
            return true;
        }

        @Override
        public void addFriend(Username friendUsername) {}

        @Override
        public void acceptFriend(Username friendUsername) {}

        @Override
        public boolean isSameAsCurrentUser(Username username) {
            return true;
        }

        @Override
        public void deleteFriend(Username friendUsername) {}

        @Override
        public void deleteFriendRequest(Username friendUsername) {}

        @Override
        public ObservableList<Friendship> getFriendRequestsList() {
            return null;
        }

        @Override
        public ObservableList<Friendship> getFriendsList() {
            return null;
        }

        @Override
        public boolean hasGroup(Name group) {
            return true;
        }

        @Override
        public void addGroup(Name groupName) {}

        @Override
        public boolean hasGroupRequest(Name groupName) {
            return true;
        }

        @Override
        public void acceptGroupRequest(Name groupName) {}

        @Override
        public boolean isInGroup(Name groupName) {
            return true;
        }

        @Override
        public void addPendingUsersGroup(Pair<Name, List<Username>> pair) {}

        @Override
        public boolean isAllValidUsers(List<Username> listUsernames) {
            return true;
        }

        @Override
        public boolean hasUsersInGroup(Pair<Name, List<Username>> pair) {
            return true;
        }

        @Override
        public boolean hasRequestForUsers(Pair<Name, List<Username>> pair) {
            return true;
        }

        @Override
        public void deleteGroup(Name groupName) {}

        @Override
        public void deleteGroupRequest(Name groupName) {}

        @Override
        public ObservableList<Group> getGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Group> getGroupRequestList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
        }

        // ========= timetable commands =========

        @Override
        public void blockDateForCurrentUser(Date date) {}

        @Override
        public void freeDateForCurrentUser(Date date) {}

        @Override
        public boolean hasDateForCurrentUser(Date date) {
            return true;
        }

        @Override
        public void displayUserWeekSchedule(Week weekNumber) {
            
        }

    }

    /**
     * A Model stub that contains a single restaurant.
     */
    private class ModelStubWithRestaurant extends ModelStub {
        private final Restaurant restaurant;

        ModelStubWithRestaurant(Restaurant restaurant) {
            requireNonNull(restaurant);
            this.restaurant = restaurant;
        }

        @Override
        public boolean hasRestaurant(Restaurant restaurant) {
            requireNonNull(restaurant);
            return this.restaurant.isSameRestaurant(restaurant);
        }

        @Override
        public void displayUserWeekSchedule(Week weekNumber) {
        }

        @Override
        public void debtListing(ObservableList<Debt> list) {
        }
    }

    /**
     * A Model stub that always accept the restaurant being added.
     */
    private class ModelStubAcceptingRestaurantAdded extends ModelStub {
        final ArrayList<Restaurant> restaurantsAdded = new ArrayList<>();

        @Override
        public boolean hasRestaurant(Restaurant restaurant) {
            requireNonNull(restaurant);
            return restaurantsAdded.stream().anyMatch(restaurant::isSameRestaurant);
        }

        @Override
        public void addRestaurant(Restaurant restaurant) {
            requireNonNull(restaurant);
            restaurantsAdded.add(restaurant);
        }

        @Override
        public void displayUserWeekSchedule(Week weekNumber) {

        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public void debtListing(ObservableList<Debt> list) {

        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
