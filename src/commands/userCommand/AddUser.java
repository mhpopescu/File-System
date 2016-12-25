package commands.userCommand;

import bash.Bash;
import commands.Command;
import utils.Errors;

/**
 * Adduser command class to add a user in system.
 * 
 * @author Mike
 *
 */
public class AddUser extends Command {

    public AddUser(String[] args, Bash bash) {
	super(args, bash);
    }

    @Override
    public void execute() {
	if (!system.isUserRoot()) { // check for permisions
	    Errors.printError(-10, args);
	    return;
	}
	if (system.containsUser(args[1])) { // check if user exists
	    Errors.printError(-9, args);
	    return;
	}
	system.addUser(args[1]);
    }
}
