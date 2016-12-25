package commands.userCommand;

import bash.Bash;
import commands.Command;
import errors.Errors;
import fileSystem.FileSystem;

/**
 * Chuser command class to change current user from system
 * 
 * @author Mike
 *
 */
public class ChUser extends Command {

    /**
     * 
     * @param args
     *            is the whole command, user's name should be first argument in
     *            args
     * @param bash
     */
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
