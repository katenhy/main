package seedu.address.logic.parser.accounting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEBTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.accounting.AcceptDebtRequestCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.accounting.Amount;
import seedu.address.model.accounting.DebtId;
import seedu.address.model.user.Username;

/**
 * Parses input arguments and creates a new AcceptDebtRequestCommand object
 */
public class AcceptDebtRequestCommandParser implements Parser<AcceptDebtRequestCommand> {

    @Override
    public AcceptDebtRequestCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_USERNAME, PREFIX_AMOUNT, PREFIX_DEBTID);
        if (!arePrefixesPresent(argMultimap, PREFIX_USERNAME, PREFIX_AMOUNT, PREFIX_DEBTID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                        AcceptDebtRequestCommand.MESSAGE_USAGE));
        }
        Username creditorUsername = ParserUserUtil.parseUsername(argMultimap.getValue(PREFIX_USERNAME).get());
        Amount amount = ParserUserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        DebtId debtId = ParserUserUtil.parseDebtId(argMultimap.getValue(PREFIX_DEBTID).get());
        return new AcceptDebtRequestCommand(creditorUsername, amount, debtId);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
