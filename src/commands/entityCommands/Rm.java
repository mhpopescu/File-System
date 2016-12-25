package commands.entityCommands;

import bash.Bash;
import fileSystem.Directory;
import fileSystem.Entity;
import utils.Errors;
import utils.Rights;

/**
 * Removes an entity from a specific path, or from current directory. Could be
 * used with "-r" argument to delete recursively
 * 
 * @author Mike
 *
 */
public class Rm extends RmCommand {
    public Rm(String[] args, Bash bash) {
	super(args, bash);
    }

    private boolean recursive = false;

    public void parseArgs() {
	if (args[1].equals("-r"))
	    recursive = true;
    }

    @Override
    public void execute() {
	parseArgs();
	computePath();
	Directory now = null;
	String toDelete = getArgument();

	if (getPath() == null) {
	    if (recursive) {
		toDelete = args[2];
	    } else {
		toDelete = args[1];
	    }
	    if (isAbsolute()) {
		now = system.getRootDirectory();
		toDelete = getArgument();
	    } else {
		now = system.getCurrentDirectory();
	    }

	} else {
	    now = system.findDirectory(getPath(), isAbsolute(), args);
	    if (now == null) {
		return;
	    }
	}

	Entity entity = now.getEntity(toDelete);

	if (entity != null && !checkParents(entity,
		system.getCurrentDirectory(), system.getRootDirectory())) {
	    Errors.printError(-13, args);
	    return;
	}

	if (!now.contains(toDelete)) { // no such file
	    Errors.printError(-11, args);
	    return;
	}

	if (!system.isUserRoot() && !now.hasRight(system.getCurrentUser(),
		Rights.WRITE.value())) {
	    Errors.printError(-5, args);
	    return;
	}

	if (entity.isDirectory() && recursive == false) {// not a directory
	    Errors.printError(-1, args);
	    return;
	}
	now.removeEntity(entity);

    }

}
