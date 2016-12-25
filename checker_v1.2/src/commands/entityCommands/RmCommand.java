package commands.entityCommands;

import bash.Bash;
import fileSystem.Directory;
import fileSystem.Entity;

public abstract class RmCommand extends FileCommand {

    public RmCommand(String[] args, Bash bash) {
	super(args, bash);
	// TODO Auto-generated constructor stub
    }

    @Override
    public abstract void execute();

    public boolean checkParents(Entity toDelete, Directory currentDirectory,
	    Directory root) {
	Directory curr = currentDirectory;
	do {
	    if (toDelete.equals(curr)) {
		return false;
	    }
	    curr = curr.getParent();
	} while (!curr.equals(root));
	return true;
    }
}
