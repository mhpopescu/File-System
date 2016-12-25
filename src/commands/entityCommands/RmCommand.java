package commands.entityCommands;

import bash.Bash;
import fileSystem.Directory;
import fileSystem.Entity;

/**
 * Inherited by rm and rmdir
 * 
 * @author Mike
 *
 */
public abstract class RmCommand extends FileCommand {

    public RmCommand(String[] args, Bash bash) {
	super(args, bash);
    }

    @Override
    public abstract void execute();

    /**
     * Both rm -r and rmdir have to check if the directory wanted to be deleted
     * is not a parent of the current directory
     * 
     * @param toDelete
     * @param currentDirectory
     * @param root
     * @return
     */
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
