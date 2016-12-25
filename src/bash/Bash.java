package bash;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import commands.Command;
import commands.CommandFactory;
import fileSystem.FileSystem;

/**
 * Send commands to File System
 * 
 * @author Mike
 *
 */
public class Bash {
    private FileSystem fileSystem;
    private InputReader reader;

    /**
     * 
     * @param inputFile
     *            fisierul din care se citesc comenzile
     * @throws FileNotFoundException
     */
    public Bash(String inputFile) throws FileNotFoundException {
	reader = new InputReader(inputFile);
	fileSystem = null;
    }

    public FileSystem getFileSystem() {
	return fileSystem;
    }

    public void setFileSystem(FileSystem fileSystem) {
	this.fileSystem = fileSystem;
    }

    /**
     * execute every command
     */
    public void start() {
	CommandFactory factory = new CommandFactory(this);
	String[] args = reader.nextCommand();

	if (!init(args)) {
	    return;
	}

	fileSystem = new FileSystem();
	args = reader.nextCommand();

	while (args != null) {
	    Command command;
	    try {
		command = factory.getCommand(args);
		if (command != null) {
		    command.execute();
		}
	    } catch (NoSuchMethodException | SecurityException
		    | InstantiationException | IllegalAccessException
		    | IllegalArgumentException | InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    args = reader.nextCommand();
	}

	fileSystem.printAll();
    }

    /**
     * Initial command to initiate the filesystem
     * 
     * @param args
     *            command parameters
     * @return
     */
    private boolean init(String[] args) {
	return (args[0].equals("mkdir") && args[1].equals("/"));
    }
}
