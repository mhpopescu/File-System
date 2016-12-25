package commands;

import bash.Bash;
import fileSystem.FileSystem;

/**
 * Base class for every command
 * 
 * @author Mike
 *
 */
public abstract class Command {
    protected Bash bash = null;
    protected String[] args = null;
    protected FileSystem system = null;

    /**
     * 
     * @param args
     *            String[]
     * @param bash
     *            Bash
     */
    public Command(String[] args, Bash bash) {
	super();
	this.args = args;
	this.bash = bash;
	system = bash.getFileSystem();
    }

    public abstract void execute();
}