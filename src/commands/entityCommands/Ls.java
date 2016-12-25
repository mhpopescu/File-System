package commands.entityCommands;

import bash.Bash;
import fileSystem.Directory;
import fileSystem.Entity;
import utils.Errors;
import utils.Rights;

/**
 * List the content of directories
 * 
 * @author Mike
 *
 */
public class Ls extends FileCommand {

    public Ls(String[] args, Bash bash) {
	super(args, bash);
    }

    @Override
    public void execute() {
	computePath();
	Directory now = null;
	String entityName = getArgument();
	Entity entity = null;

	if (getPath() == null) {
	    if (isAbsolute()) {
		now = system.getRootDirectory();
		entityName = getArgument();
	    } else {
		now = system.getCurrentDirectory();
		entityName = args[1];
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
	entity = now.getEntity(entityName);

	if (entity == null) {
	    Errors.printError(-12, args);
	    return;
	}

	if (!entity.isDirectory()) {
	    System.out.println(entity);
	    return;
	}

	if (!system.isUserRoot() && !entity.hasRight(system.getCurrentUser(),
		Rights.EXECUTE.value())) {
	    Errors.printError(-6, args);
	    return;
	}

	if (!system.isUserRoot() && !entity.hasRight(system.getCurrentUser(),
		Rights.READ.value())) {
	    Errors.printError(-4, args);
	    return;
	}

	for (Entity curr : ((Directory) entity).getEntities()) {
	    System.out.println(curr);
	}
    }

}
