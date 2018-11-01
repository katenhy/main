package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.util.Pair;
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

/**
 * The API of the Model component.
 */
public interface Model {

    //=========== Model Manager Miscellaneous Methods =+==========================================================

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Restaurant> PREDICATE_SHOW_ALL_RESTAURANTS = unused -> true;

    Predicate<Jio> PREDICATE_SHOW_ALL_JIOS = unused -> true;

    Predicate<Group> PREDICATE_SHOW_ALL_GROUPS = unused -> true;

    /**
     * Clears existing backing model and replaces with the provided new data.
     */
    void resetData(ReadOnlyAddressBook newData);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a user is currently logged into the Makan Book
     */
    boolean isCurrentlyLoggedIn();

    //=========== Model Manager Restaurants Methods =+============================================================

    /**
     * Returns true if a restaurant with the same identity as {@code restaurant} exists in the address book.
     */
    boolean hasRestaurant(Restaurant restaurant);

    /**
     * Deletes the given restaurant.
     * The restaurant must exist in the address book.
     */
    void deleteRestaurant(Restaurant target);

    /**
     * Adds the given restaurant.
     * {@code restaurant} must not already exist in the address book.
     */
    void addRestaurant(Restaurant restaurant);

    /**
     * Replaces the given restaurant {@code target} with {@code editedRestaurant}.
     * {@code target} must exist in the address book.
     * The restaurant identity of {@code editedRestaurant}
     * must not be the same as another existing restaurant in the address book.
     */
    void updateRestaurant(Restaurant target, Restaurant editedRestaurant);

    /**
     * Displays the Profile of a User.
     */
    void displayProfile();

    //=========== Filtered Restaurant List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the filtered restaurant list
     */
    ObservableList<Restaurant> getFilteredRestaurantList();

    /**
     * Updates the filter of the filtered restaurant list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRestaurantList(Predicate<Restaurant> predicate);

    //=========== Model Manager User Methods =+===================================================================

    /**
     * Returns true if a user with the {@code username} exists in User Data.
     */
    boolean hasUser(Username username);

    /**
     * Returns true if the {@code password} matches the password of the user with {@code username}.
     */
    boolean verifyLogin(Username username, Password password);

    /**
     * Adds the given {@code user}. The user must not already exist in the User Data.
     */
    void addUser(User user);

    /**
     * Allows the {@code user} to be logged in immediately after they sign up.
     * There should not be anyone currently logged in before.
     */
    void loginUser(User user);

    /**
     * Allows a User to log into the account with {@code username}.
     * There should not be anyone currently logged in before.
     */
    void loginUser(Username username);

    /**
     * Allows a current User to logout of their account.
     */
    void logoutUser();

    /**
     * Creates a UserReview and a RestaurantReview for storage into both RestaurantAddressBook and UserData.
     */
    void addUserReview(Restaurant restaurant, Rating rating, WrittenReview writtenReview);

    //=========== Model Manager User Methods =+===================================================================

    boolean hasUsernameSentRequest(Username friendUsername);

    boolean hasUsernameFriend(Username friendUsername);

    boolean hasUsernameFriendRequest(Username friendUsername);

    void addFriend(Username friendUsername);

    void acceptFriend(Username friendUsername);

    boolean isSameAsCurrentUser(Username username);

    void deleteFriend(Username friendUsername);

    void deleteFriendRequest(Username friendUsername);

    ObservableList<Friendship> getFriendsList();

    ObservableList<Friendship> getFriendRequestsList();

    boolean hasGroup(Name group);

    boolean hasGroupRequest(Name groupName);

    void addGroup(Name groupName);

    void acceptGroupRequest(Name groupName);

    boolean isInGroup(Name groupName);

    void addPendingUsersGroup(Pair<Name, List<Username>> pair);

    boolean isAllValidUsers(List<Username> listUsernames);

    boolean hasUsersInGroup(Pair<Name, List<Username>> pair);

    boolean hasRequestForUsers(Pair<Name, List<Username>> pair);

    void deleteGroup(Name groupName);

    void deleteGroupRequest(Name groupName);

    /**
     * Returns an unmodifiable view of the group list
     */
    ObservableList<Group> getGroupList();

    ObservableList<Group> getGroupRequestList();

    void updateFilteredGroupList(Predicate<Group> predicate);

    //============ Timetable commands ==========================

    void blockDateForCurrentUser(Date date);

    void freeDateForCurrentUser(Date date);

    boolean hasDateForCurrentUser(Date date);

    void displayUserWeekSchedule(Week weekNumber);

    //=========== Jio methods ===============================================================================

    ObservableList<Jio> getJioList();

    void updateFilteredJioList(Predicate<Jio> predicate);

    boolean hasJio(Jio jio);

    boolean hasJioName(Name jioName);

    void removeJioOfName(Name jioName);

    void createJio(Jio jio);

    boolean isCurrentUserInJioOfName(Name jioName);

    void addCurrentUserToJioOfName(Name jioName);

    boolean isCurrentUserCreatorOfJio(Name jioName);

    //=========== Undo/Redo/Commit ===============================================================================

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();

    //========== Model Manager Debt methods ======================================================================
    boolean hasDebtId(DebtId debtId);

    boolean matchDebtToAmount(DebtId debtId, Amount amount);

    boolean matchDebtToUser(DebtId debtId, Username user);

    boolean matchDebtToStatus(DebtId debtId, DebtStatus status);

    boolean debtExist(Username debtorUsername);

    boolean allowToClear(Username debtorUsername, Amount amount);

    void addDebt(Username debtorUsername, Amount amount);

    void addGroupDebt(Name groupName, Amount amount);

    void clearDebt(Username debtorUsername, Amount amount);

    void acceptedDebtRequest(Username creditorUsername, Amount amount, DebtId debtId);

    void deleteDebtRequest(Username creditorUsername, Amount amount, DebtId debtId);

    ObservableList<Debt> getDebtList();

    ObservableList<Debt> getCreditorList();

    ObservableList<Debt> getDebtorList();

    ObservableList<Debt> getDebtRequestReceived();

    ObservableList<Debt> getDebtRequestSent();

    void debtListing(ObservableList<Debt> list);

}

