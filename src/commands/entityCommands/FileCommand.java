package commands.entityCommands;

import java.util.StringTokenizer;

import bash.Bash;
import commands.Command;

/**
 * Abstract class inherited by commands which operates with files
 * 
 * @author Mike
 *
 */
public abstract class FileCommand extends Command {
    public FileCommand(String[] args, Bash bash) {
	super(args, bash);
    }

    private StringTokenizer path = null;
    private StringTokenizer fullPath = null;
    private String argument = null;
    private boolean absolute = false;

    @Override
    public abstract void execute();

    /**
     * Used to find if the command contains a path to another file of directory
     * fullPath is used for any command like cd; Also, removes any trailing '/'
     */
    public void computePath() {
	String s = findFirstPath();
	if (s == null || s.length() == 0) {
	    argument = "/";
	    return;
	}
	if (s.equals(".") || s.equals("..")) {
	    path = new StringTokenizer(s, " ");
	    fullPath = path;
	    argument = s;
	    return;
	}
	if (s.contains("/") && (s.lastIndexOf("/") == (s.length() - 1))) {
	    s = s.substring(0, s.length() - 1);
	}
	if (!s.contains("/")) {
	    argument = s;
	    return;
	}
	fullPath = new StringTokenizer(s, "/");
	path = new StringTokenizer(s.substring(0, s.lastIndexOf("/")), "/");
	argument = s.substring(s.lastIndexOf("/") + 1, s.length());
    }

    /**
     * @return A tokanized String with the first path found in a command (except
     *         for the last element, the last is saved as an argument); Returns
     *         NULL when no path is found
     */
    public StringTokenizer getPath() {
	return path;
    }

    /**
     * 
     * @return A tokanized String with the whole path in a command. Returns NULL
     *         when no path is found
     */
    public StringTokenizer getFullPath() {
	return fullPath;
    }

    /**
     * 
     * Search for the first path in a command
     * 
     */
    private String findFirstPath() {
	for (String s : args) {
	    if (s.equals("..") || s.equals(".")) {
		return s;
	    } else if (s.contains("/")) {
		if (s.startsWith("/")) {
		    absolute = true;
		    return s.substring(1);
		}
		return s;
	    }
	}
	return null;
    }

    public String getPathArgument() {
	return argument;
    }

    /**
     * 
     * @return true if the path is absolute, else returns false
     */
    public boolean isAbsolute() {
	return absolute;
    }

    /**
     * 
     * @return the last argument in every path; usefull for commands like
     *         mkdir/rm/touch
     */
    public String getArgument() {
	return argument;
    }

    protected void setAbsolute(boolean bool) {
	absolute = bool;
    }
}
