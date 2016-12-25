package commands.entityCommands;

import bash.Bash;
import fileSystem.Directory;
import fileSystem.Entity;
import fileSystem.File;
import user.User;
import utils.Errors;
import utils.Rights;

/**
 * Prints the content of a file
 * 
 * @author Mike
 *
 */
public class Cat extends FileCommand {

    public Cat(String[] args, Bash bash) {
	super(args, bash);
    }

    @Override
    public void execute() {
	computePath();
	Directory now = null;
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

	if (!now.contains(fileName)) {
	    Errors.printError(-11, args); // no such file error
	    return;
	}

	Entity fileToCat = now.getEntity(fileName);
	User currentUser = system.getCurrentUser();

	if (fileToCat.isDirectory()) {
	    Errors.printError(-1, args); // is a directory error
	    return;
	}

	if (!system.isUserRoot()
		&& !fileToCat.hasRight(currentUser, Rights.READ.value())) {
	    Errors.printError(-4, args); // no rights error
	    return;
	}

	System.out.println(((File) fileToCat).getData());
    }

}
