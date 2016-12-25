package commands.entityCommands;

import bash.Bash;
import fileSystem.Directory;
import fileSystem.Entity;
import fileSystem.File;
import user.User;
import utils.Errors;
import utils.Rights;

/**
 * Creates an empty file at a specific path, or in the current directory
 * 
 * @author Mike
 *
 */
public class Touch extends FileCommand {

    public Touch(String[] args, Bash bash) {
	super(args, bash);
    }

    @Override
    public void execute() {
	computePath();
	Directory now;
	String fileName = getArgument();
	if (getPath() == null) {
	    fileName = args[1];
	    if (isAbsolute()) {
		now = system.getRootDirectory();
		fileName = getArgument();
	    } else {
		now = system.getCurrentDirectory();
	    }
	} else {
	    now = system.findDirectory(getPath(), isAbsolute(), args);
	    if (now == null) {
		return;
	    }
	}

	User user = system.getCurrentUser();
	if (!system.isUserRoot() && !now.hasRight(user, Rights.WRITE.value())) {
	    Errors.printError(-5, args);
	    return;
	}
	if (now.contains(fileName)) {
	    Entity entity = now.getEntity(fileName);
	    if (entity.isDirectory()) {
		Errors.printError(-1, args);
	    } else {
		Errors.printError(-7, args);
	    }
	    return;
	}
	now.addEntity(new File(fileName, user, now));
    }

}
