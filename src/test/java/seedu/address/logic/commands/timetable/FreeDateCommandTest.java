package seedu.address.logic.commands.timetable;

import static seedu.address.testutil.TypicalRestaurants.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model, UndoCommand, and RedoCommand) and unit tests for
 * {@code BlockDateCommand}
 */
public class FreeDateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validDate_success() {

    }

    @Test
    public void execute_invalidDate_throwsCommandException() {

    }

    @Test
    public void execute_withoutLogin_throwsNotLoggedInCommandException() {

    }

    @Test
    public void execute_duplicateDate_throwsCommandException() {

    }

    @Test
    public void executeUndoRedo_validDate_success() throws Exception {

    }

    @Test
    public void executeUndoRedo_invalidDate_failure() {

    }

    @Test
    public void equals() {

    }
}
