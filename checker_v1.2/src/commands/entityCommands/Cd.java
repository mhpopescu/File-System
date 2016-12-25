package commands.entityCommands;

import java.util.StringTokenizer;

import bash.Bash;
import fileSystem.Directory;

public class Cd extends FileCommand {

    public Cd(String[] args, Bash bash) {
	super(args, bash);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void execute() {
	computePath();
	StringTokenizer path = getFullPath();
	if (path == null) {
	    path = new StringTokenizer(args[1], " ");
	    setAbsolute(false);
	}
	Directory now = system.findDirectory(path, isAbsolute(), args);
	if (now != null) {
	    system.setCurrentDirectory(now);
	}
    }

}
