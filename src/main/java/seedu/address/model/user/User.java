package seedu.address.model.user;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.accounting.Debt;
import seedu.address.model.accounting.DebtStatus;

/**
 * Represents a User in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class User {
    // Identity fields
    private final Username username;
    private final Password password;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final List<Friendship> friendRequests = new ArrayList<>();
    private final List<Friendship> friends = new ArrayList<>();
    private final List<Debt> debts = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public User(Username username, Password password, Name name, Phone phone, Email email) {
        requireAllNonNull(username, password, name, phone, email);
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public List<Friendship> getFriendRequests() {
        return friendRequests;
    }

    public List<Friendship> getFriends() {
        return friends;
    }

    public List<Debt> getDebts() {
        return debts;
    }

    /**
     * Returns true if both users of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two users.
     */
    public boolean isSameUser(User otherUser) {
        if (otherUser == this) {
            return true;
        }

        return otherUser != null
                && otherUser.getName().equals(getName())
                && (otherUser.getPhone().equals(getPhone()) || otherUser.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both users have the same identity and data fields.
     * This defines a stronger notion of equality between two users.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof User)) {
            return false;
        }

        User otherUser = (User) other;
        return otherUser.getUsername().equals(getUsername());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(username, password, name, phone, email);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getUsername())
                .append(" Password: ")
                .append(getPassword())
                .append(" Name: ")
                .append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail());
        /*.append(" Friends: ");
        getTags().forEach(builder::append);*/
        return builder.toString();
    }

    /**
     * Allows a user to add other users as friends.
     * Friendship request will not be created if friendship request already exists.
     * Friendship request will not be created if friendship already exists.
     * @param user User is not able to add oneself as a friend.
     */
    public void addFriend(User user) {
        // checks to make sure that friend is not oneself
        if (!user.isSameUser(this)) {
            Friendship friendship = new Friendship(this, this, this);
            // checks that friendship is not already in friendRequests
            if (!user.friendRequests.contains(friendship) && !user.friends.contains(friendship)) {
                user.friendRequests.add(friendship);
            }
        }
    }

    /**
     * Used for storage purposes.
     * Allows user to add a friend or friendRequest by taking in
     * @param friendship
     * @return the updated User
     */
    public User addFriendship(Friendship friendship) {
        FriendshipStatus friendshipStatus = friendship.getFrienshipStatus();
        if (friendshipStatus.equals(FriendshipStatus.ACCEPTED)) {
            friends.add(friendship);
        } else {
            friendRequests.add(friendship);
        }
        return this;
    }

    /**
     * @return String of all the user's friends separated by newline character.
     */
    public String listFriends() {
        return listHelper(friends);
    }

    /**
     * @return String that contains all the friendRequests received of this user separated by newline character.
     */
    public String listFriendRequests() {
        return listHelper(friendRequests);
    }

    /**
     * Helper method.
     * @param list List that you want to print out.
     * @return String that contains all elements in list.
     */
    public String listHelper(List<Friendship> list) {
        String toReturn = "";
        for (Friendship friendship: list) {
            toReturn += friendship.getFriendUser().getName() + "\n";
        }
        return toReturn;
    }

    /**
     * Ensures that the friendship can only be accepted by the party who did not initiate the request.
     * Changes friendship status to ACCEPTED.
     * Deletes friendship from friendRequests for the accepting party.
     * Adds friendship to friends list for both parties.
     * @param username Name of the friend to accept.
     */
    public void acceptFriendRequest(Name username) {
        Friendship friendship = findFriendshipInList(friendRequests, username);
        User friend = friendship.getFriendUser();
        // ensures that the RESTAURANT who initiated the friendship is not the one accepting
        if (!friendship.getInitiatedBy().equals(this)) {
            // changes friendship to accepted
            friendship.changeFriendshipStatus();

            // deletes from friendRequests for both parties
            friendRequests.remove(friendship);

            // adds to friends for both parties
            friends.add(friendship);
            Friendship friendship2 = new Friendship(this, friend, this, FriendshipStatus.ACCEPTED);
            friend.friends.add(friendship2);
        }
    }

    /**
     * Deletes the friendship request of the user with name username
     * @param username Name of the user that you want to delete friendRequest of
     */
    public void deleteFriendRequest(Name username) {
        Friendship friendship = findFriendshipInList(friendRequests, username);
        friendRequests.remove(friendship);
    }

    /**
     * Deletes the friendship for both parties when delete is initiated by one party.
     * Deletes friendship only if friendship exists.
     * @param username Name of the friend you want to delete
     */
    public void deleteFriend(Name username) {
        // gets the friendship for both parties
        Friendship friendship = findFriendshipInList(friends, username);
        User friend = friendship.getFriendUser();
        Friendship friendship2 = findFriendshipInList(friend.friends, this.getName());

        // deletes the friendship for both parties
        if (friends.contains(friendship)) {
            friends.remove(friendship);
            friend.friends.remove(friendship2);
        }
    }

    /**
     * Helper method to find friendship in list.
     * @param list List in which you would like to search in.
     * @param username Name of the friend that you would like to find.
     * @return Friendship between this user and user with Name username.
     */
    public Friendship findFriendshipInList(List<Friendship> list, Name username) {
        for (Friendship friendship: list) {
            if (friendship.getFriendUser().getName().equals(username)) {
                return friendship;
            }
        }
        return null;
    }

    /**
     * Method to add a debts to a user.
     * @param debt a debt to add.
     * @return the user who the debt added to.
     */
    public User addDebt(Debt debt) {
        debts.add(debt);
        return this;
    }

    /**
     * Method for the credotir to create and add a debt.
     * @param debtor the debtor of the adding debt
     * @param amount the amount of the adding debt
     */
    public void addDebt(User debtor, double amount) {
        if (!debtor.isSameUser(this)) {
            Debt d = new Debt(this, debtor, amount);
            this.debts.add(d);
            debtor.debts.add(d);
        }
    }

    /**
     * Method for the creditor to clear a debt.
     * @param debtor the debtor of the clearing debt.
     * @param amount the amount of the clearing debt.
     * @param debtId the debtId of the clearing debt.
     */
    public void clearDebt(User debtor, double amount, String debtId) {
        Debt toFind = new Debt(this, debtor, amount, debtId, DebtStatus.ACCEPTED);
        Debt changeTo = new Debt(this, debtor, amount, debtId, DebtStatus.CLEARED);
        int i = this.debts.indexOf(toFind);
        this.debts.set(i, changeTo);
        i = debtor.debts.indexOf(toFind);
        debtor.debts.set(i, changeTo);

    }

    /**
     * Method for debtor to accepted a received debt.
     * @param creditor the creditor of the accepting debt.
     * @param amount the amount of the accepting debt.
     * @param debtId the debtId of the accepting debt.
     */
    public void acceptedDebtRequest(User creditor, double amount, String debtId) {
        Debt toFind = new Debt(creditor, this, amount, debtId, DebtStatus.PENDING);
        Debt changeTo = new Debt(creditor, this, amount, debtId, DebtStatus.ACCEPTED);
        int i = this.debts.indexOf(toFind);
        this.debts.set(i, changeTo);
        i = creditor.debts.indexOf(toFind);
        creditor.debts.set(i, changeTo);
    }

    /**
     * Method for debtor to reject and delete a received debt.
     * @param creditor the creditor of the deleting debt.
     * @param amount the amount of the deleting debt.
     * @param debtId the debtId of the deleting debt.
     */
    public void deleteDebtRequest(User creditor, double amount, String debtId) {
        Debt toFind = new Debt(creditor, this, amount, debtId, DebtStatus.PENDING);
        this.debts.remove(toFind);
        creditor.debts.remove(toFind);
    }

    /**
     * Method to list all the debt record.
     * @return a String contain all the debt history of this user, include creditor, debtor, amount, debt ID and status.
     */
    public String listDebtHistory() {
        String toReturn = "";
        for (Debt d: this.debts) {
            toReturn += d.toString() + "\n";
        }
        return toReturn;
    }

    /**
     * Method to list all the debtor.
     * @return a String of all debt which creditor is the user and have a accepted status,
     * include creditor, debtor, amount, debt ID and status.
     */
    public String listDebtor() {
        String toReturn = "";
        for (Debt d: this.debts) {
            if (d.getCreditor().equals(this.name) && d.getDebtStatus().equals(DebtStatus.ACCEPTED)) {
                toReturn += d.toString() + "\n";
            }
        }
        return toReturn;
    }

    /**
     * Method to list all the creditor.
     * @return a String of all debt which debtor is the user and have a accepted status,
     * include creditor, debtor, amount, debt ID and status.
     */
    public String listCreditor() {
        String toReturn = "";
        for (Debt d: this.debts) {
            if (d.getDebtor().equals(this.name) && d.getDebtStatus().equals(DebtStatus.ACCEPTED)) {
                toReturn += d.toString() + "\n";
            }
        }
        return toReturn;
    }

    /**
     * Method to list all received request.
     * @return a String of all debt which debtor is the user and have a pending status,
     * include creditor, debtor, amount, debt ID and status.
     */
    public String listDebtRequestReceived() {
        String toReturn = "";
        for (Debt d: this.debts) {
            if (d.getDebtor().equals(this.name) && d.getDebtStatus().equals(DebtStatus.PENDING)) {
                toReturn += d.toString() + "\n";
            }
        }
        return toReturn;
    }

    /**
     * Method to list all sent request.
     * @return a String of all debt which creditor is the user and have a pending status,
     * include creditor, debtor, amount, debt ID and status.
     */
    public String listDebtRequestSent() {
        String toReturn = "";
        for (Debt d: this.debts) {
            if (d.getCreditor().equals(this.name) && d.getDebtStatus().equals(DebtStatus.PENDING)) {
                toReturn += d.toString() + "\n";
            }
        }
        return toReturn;
    }
}