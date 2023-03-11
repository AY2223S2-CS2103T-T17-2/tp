package seedu.sudohr.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");


    /* Prefix definitions for event*/
    public static final Prefix PREFIX_EVENT_TITLE = new Prefix("d/");
    public static final Prefix PREFIX_EVENT_INDEX = new Prefix("g/");
    public static final Prefix PREFIX_EMPLOYEE_INDEX = new Prefix("p/");
}
