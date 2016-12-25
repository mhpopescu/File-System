package commands;

import bash.Bash;
import fileSystem.FileSystem;

public abstract class Command {
    protected Bash bash = null;
    protected String[] args = null;
    protected FileSystem system = null;

    public Command(String[] args, Bash bash) {
	super();
	this.args = args;
	this.bash = bash;
	system = bash.getFileSystem();
    }

    public void setArgs(String[] args) {
	this.args = args;
    }

    public void setBash(Bash bash) {
	this.bash = bash;
    }

    public abstract void execute();
}