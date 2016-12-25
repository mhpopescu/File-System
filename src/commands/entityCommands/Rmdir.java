package commands.entityCommands;

import bash.Bash;
import fileSystem.Directory;
import fileSystem.Entity;
import user.User;
import utils.Errors;
import utils.Rights;

/**
 * Removes a directory from a specific path, or from current directory
 * 
 * @author Mike
 *
 */
public class Rmdir extends RmCommand {

    public Rmdir(String[] args, Bash bash) {
	super(args, bash);
    }

    @Override
    public void execute() {
	computePath();
	Directory now = system.findDirectory(getPath(), isAbsolute(), args);
	String toDelete = getArgument();

	if (getPath() == null) {
	    toDelete = args[1];
	    if (isAbsolute()) {
		now = system.getRootDirectory();
		toDelete = getArgument();
	    } else {
		now = system.getCurrentDirectory();
	    }

	}
	if (now == null) {
	    return;
	}

	if (!now.contains(toDelete)) {
	    Errors.printError(-2, args); // no such file error
	    return;
	}

	Entity directoryToDelete = now.getEntity(toDelete);
	User currentUser = system.getCurrentUser();

	if (!directoryToDelete.isDirectory()) {
	    Errors.printError(-3, args); // not a directory error
	    return;
	}

	if (!checkParents(directoryToDelete, system.getCurrentDirectory(),
		system.getRootDirectory())) {
	    Errors.printError(-13, args);
	    return;
	}

	if (!system.isUserRoot()
		&& !now.hasRight(currentUser, Rights.WRITE.value())) {
	    Errors.printError(-5, args); // no rights error
	    return;
	}

	if (!directoryToDelete.isEmpty()) { // directory is not empty error
	    Errors.printError(-14, args);
	    return;
	}

	now.removeEntity(directoryToDelete);
    }

}
