package commands.userCommand;

import bash.Bash;
import commands.Command;
import fileSystem.FileSystem;
import utils.Errors;

/**
 * Chuser command class to change current user from system
 * 
 * @author Mike
 *
 */
public class ChUser extends Command {

    public ChUser(String[] args, Bash bash) {
	super(args, bash);
    }

    @Override
    public void execute() {
	FileSystem fileSystem = bash.getFileSystem();
	if (!fileSystem.containsUser(args[1])) {
	    Errors.printError(-8, args);
	    return;
	}
	fileSystem.setCurrentUser(args[1]);
    }
}
