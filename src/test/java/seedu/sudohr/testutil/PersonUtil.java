package seedu.sudohr.testutil;

import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.sudohr.logic.commands.AddCommand;
import seedu.sudohr.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.tag.Tag;

/**
 * A utility class for Employee.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Employee person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Employee person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ID + person.getId().value + " ");
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEmployeeDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditEmployeeDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getId().ifPresent(id -> sb.append(PREFIX_ID).append(id.value).append(" "));
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
