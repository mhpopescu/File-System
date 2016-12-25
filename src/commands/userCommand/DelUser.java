package commands.userCommand;

import bash.Bash;
import commands.Command;
import fileSystem.Directory;
import fileSystem.Entity;
import user.User;
import utils.Errors;

/**
 * Deluser command class to delete a user from system
 * 
 * @author Mike
 *
 */
public class DelUser extends Command {

    public DelUser(String[] args, Bash bash) {
	super(args, bash);
    }

    @Override
    public void execute() {
	String name = args[1];
	if (system.isUserRoot()) { // check for permisions
	    if (!system.containsUser(name)) // check if user exists
		Errors.printError(-8, args);
	    else {
		system.delUser(name);
		User nextUser = system.getFirstUser();
		setUsers(name, nextUser, system.getRootDirectory());
	    }
	} else {
	    Errors.printError(-10, args);
	}
    }

    /**
     * recursive method to iterate through file system and change the owner of
     * files when one is deleted
     * 
     * @param name
     *            deteled user's name
     * @param next
     *            user to change with
     * @param root
     *            to change the whole file system call with /
     */
    private void setUsers(String name, User next, Directory root) {
	for (Entity curr : root.getEntities()) {
	    if (curr.getUser().getName().equals(name)) {
		curr.setUser(next);
	    }
	    if (curr.isDirectory()) {
		setUsers(name, next, (Directory) curr);
	    }
	}
    }
}
