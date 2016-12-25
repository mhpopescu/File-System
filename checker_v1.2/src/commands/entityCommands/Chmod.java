package commands.entityCommands;

import bash.Bash;
import errors.Errors;
import fileSystem.Directory;
import fileSystem.Entity;
import utils.Rights;

public class Chmod extends FileCommand {

    public Chmod(String[] args, Bash bash) {
	super(args, bash);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void execute() {
	computePath();
	Directory now;
	String entityName = getArgument();

	if (getPath() == null) {
	    if (isAbsolute()) {
		now = system.getRootDirectory();
		entityName = getArgument();
	    } else {
		now = system.getCurrentDirectory();
		entityName = args[2];
		if (entityName.endsWith("/")) {
		    entityName = entityName.substring(0,
			    entityName.length() - 1);
		}
	    }

	} else {
	    now = system.findDirectory(getPath(), isAbsolute(), args);
	    if (now == null) {
		return;
	    }
	}

	/*
	 * if (getPath() == null) { now = system.getCurrentDirectory(); } else {
	 * now = system.findDirectory(getPath(), isAbsolute(), args); } if (now
	 * == null) { return; } if (entityName == null) { entityName = args[2];
	 * if (entityName.endsWith("/")) { entityName = entityName.substring(0,
	 * entityName.length() - 1); } }
	 */

	if (!now.contains(entityName)) {
	    Errors.printError(-12, args); // no such file error
	    return;
	}
	Entity entity = now.getEntity(entityName);
	if (!system.isUserRoot() && !entity.hasRight(system.getCurrentUser(),
		Rights.WRITE.value())) {
	    Errors.printError(-5, args);
	    return;
	}
	entity.setPermisions(Integer.parseInt(args[1]));
    }

}
