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
	// TODO Auto-generated constructor stub
    }

    private StringTokenizer path = null;
    private StringTokenizer fullPath = null;
    private String argument = null;
    private boolean absolute = false;

    @Override
    public abstract void execute();

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

    public StringTokenizer getPath() {
	return path;
    }

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

    public boolean isAbsolute() {
	return absolute;
    }

    public String getArgument() {
	return argument;
    }

    protected void setAbsolute(boolean bool) {
	absolute = bool;
    }
}
