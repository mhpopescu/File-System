package commands.entityCommands;

import bash.Bash;
import errors.Errors;
import fileSystem.Directory;
import fileSystem.Entity;
import fileSystem.File;
import user.User;
import utils.Rights;

public class WriteToFile extends FileCommand {

    public WriteToFile(String[] args, Bash bash) {
	super(args, bash);
	// TODO Auto-generated constructor stub
    }

    /**
     * second parameter in args should be data to write in the file
     * 
     * @return file data
     */
    private String getDataArg() {
	return args[2];
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
	/*
	 * if (getPath() == null) { now = system.getCurrentDirectory(); } else {
	 * now = system.findDirectory(getPath(), isAbsolute(), args); } if (now
	 * == null) { return; } if (fileName == null) { fileName = args[1]; }
	 */

	if (!now.contains(fileName)) {
	    Errors.printError(-11, args); // no such file error
	    return;
	}

	Entity fileToWrite = now.getEntity(fileName);
	User currentUser = system.getCurrentUser();

	if (fileToWrite.isDirectory()) {
	    Errors.printError(-1, args); // not a file error
	    return;
	}

	if (!system.isUserRoot()
		&& !fileToWrite.hasRight(currentUser, Rights.WRITE.value())) {
	    Errors.printError(-5, args); // no rights error
	    return;
	}

	((File) fileToWrite).setData(getDataArg());
    }

}
