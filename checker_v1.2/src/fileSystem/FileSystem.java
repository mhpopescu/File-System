package fileSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import errors.Errors;
import user.User;
import utils.Rights;

public class FileSystem {
    private Directory rootDirectory;
    private List<User> users;
    private Directory currentDirectory;
    private User currentUser;
    private User rootUser;

    public FileSystem() {
	rootUser = new User("root");
	currentUser = rootUser;
	rootDirectory = new Directory("/", "rwxr-x", rootUser, null);
	setRootDirectory();

	rootUser.setHomeDirectory(rootDirectory);
	users = new ArrayList<User>();
	users.add(rootUser);

	currentDirectory = rootDirectory;
    }

    public void setCurrentDirectory(Directory currentDirectory) {
	this.currentDirectory = currentDirectory;
    }

    public Directory getCurrentDirectory() {
	return currentDirectory;
    }

    public Directory getRootDirectory() {
	return rootDirectory;
    }

    public void setRootDirectory() {
	this.rootDirectory.setParent(rootDirectory);
    }

    public User getCurrentUser() {
	return currentUser;
    }

    /**
     * system method to add a new user and creates a folder in /
     * 
     * @param args
     *            for adduser command, starts from index 1; first argument
     *            should be the user's name
     * 
     */
    public void addUser(String name) {
	User newUser = new User(name);
	users.add(newUser);
	Directory userDirectory = new Directory(name, newUser, rootDirectory);
	rootDirectory.addEntity(userDirectory);
	newUser.setHomeDirectory(userDirectory);
    }

    /**
     * system method to check if a user exists
     * 
     * @param name
     *            user's name
     * @return
     */
    public boolean containsUser(String name) {
	return getUserByName(name) != null ? true : false;
    }

    public boolean isUserRoot() {
	return currentUser.equals(rootUser);
    }

    public void setCurrentUser(String name) {
	User user = getUserByName(name);
	currentUser = user;
	currentDirectory = user.getHomeDirectory();
	// TODO
    }

    /**
     * remove a user from system by name
     * 
     * @param name
     */
    public void delUser(String name) {
	User user = getUserByName(name);
	users.remove(user);
    }

    /**
     * system method to set current user
     * 
     * @param user
     *            user's name
     */
    public void setFileUsersAfterDelete(String name) {
	for (Entity curr : rootDirectory.getEntities()) {
	    if (curr.isDirectory() && curr.getName().equals(name)) {
		currentDirectory = (Directory) curr;
		return;
	    }
	}
    }

    /**
     * 
     * @param path
     * @param absolute
     * @param args
     * @return
     */
    public Directory findDirectory(StringTokenizer path, boolean absolute,
	    String[] args) {
	Directory now;
	if (path == null) {
	    return currentDirectory;
	}
	if (absolute) {
	    now = rootDirectory;
	} else {
	    now = currentDirectory;
	}

	String nextDir = null;
	boolean found = false;
	while (path.hasMoreTokens()) {
	    nextDir = path.nextToken();
	    found = false;
	    if (nextDir.equals("..")) {
		now = now.parent;
		found = true;
	    } else if (nextDir.equals(".")) {
		found = true;
	    } else {
		for (Entity curr : now.getEntities()) {
		    if (curr.getName().equals(nextDir)) {
			if (!curr.isDirectory()) {
			    Errors.printError(-3, args); // nu e director
			    return null;
			}
			now = (Directory) curr;
			found = true;
			break;
		    }
		}
	    }
	    if (found == false) { // nu exista
		Errors.printError(-2, args);
		return null;
	    }
	    if (!now.isDirectory()) {
		Errors.printError(-3, args); // nu e director
		return null;
	    }
	    if (!isUserRoot()
		    && !now.hasRight(currentUser, Rights.EXECUTE.value())) {
		Errors.printError(-6, args); // nu are drepturi
		return null;
	    }
	}
	return now;
    }

    public void printAll() {
	recursivePrint(rootDirectory, 0);
    }

    private void recursivePrint(Entity root, int height) {
	StringBuilder level = new StringBuilder("");
	for (int i = 0; i < height; ++i) {
	    level.append("\t");
	}
	System.out.println(level + root.toString());
	if (root.isDirectory()) {
	    for (Entity entity : ((Directory) root).getEntities()) {
		recursivePrint(entity, height + 1);
	    }
	}
    }

    private User getUserByName(String name) {
	for (User user : users) {
	    if (user.getName().equals(name)) {
		return user;
	    }
	}
	return null;
    }

    public User getFirstUser() {
	return users.get(1);
    }

}
