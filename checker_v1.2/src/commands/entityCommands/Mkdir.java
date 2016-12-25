package commands.entityCommands;

import bash.Bash;
import errors.Errors;
import fileSystem.Directory;
import fileSystem.Entity;
import user.User;
import utils.Rights;

/**
 * Mkdir command class to create a new directory at a specific path.
 * 
 * @author Mike
 *
 */
public class Mkdir extends FileCommand {

    public Mkdir(String[] args, Bash bash) {
	super(args, bash);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void execute() {
	computePath();
	Directory now = null;
	String directoryName = getArgument();

	if (getPath() == null) {
	    directoryName = args[1];
	    if (isAbsolute()) {
		now = system.getRootDirectory();
		directoryName = getArgument();
	    } else {
		now = system.getCurrentDirectory();
	    }
	} else {
	    now = system.findDirectory(getPath(), isAbsolute(), args);
	    if (now == null) {
		return;
	    }
	}

	Entity entity = now.getEntity(directoryName);

	if (directoryName.equals("/")
		|| (entity != null && entity.isDirectory())) {
	    Errors.printError(-1, args);
	    return;
	}

	if (entity != null && !entity.isDirectory()) {
	    Errors.printError(-3, args);
	    return;
	}

	User currentUser = system.getCurrentUser();
	if (!system.isUserRoot()
		&& !now.hasRight(currentUser, Rights.WRITE.value())) {
	    Errors.printError(-5, args);
	    return;
	}
	now.addEntity(new Directory(directoryName, currentUser, now));
    }

}
