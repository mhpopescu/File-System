package commands;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import bash.Bash;
import commands.entityCommands.Cat;
import commands.entityCommands.Cd;
import commands.entityCommands.Chmod;
import commands.entityCommands.Ls;
import commands.entityCommands.Mkdir;
import commands.entityCommands.Rm;
import commands.entityCommands.Rmdir;
import commands.entityCommands.Touch;
import commands.entityCommands.WriteToFile;
import commands.userCommand.AddUser;
import commands.userCommand.ChUser;
import commands.userCommand.DelUser;

/**
 * Here is stored the Command Library and the commands are instatiated
 * 
 * @author Mike
 *
 */
public class CommandFactory {
    private Bash bash;

    /**
     * *Library of commands:* hashmap with key the command name and the value
     * the refference to command class Every command should have a constructor
     * with String[] and Bash parameters
     */
    final private static Map<String, Class<? extends Command>> commandLibrary;
    static {
	commandLibrary = new HashMap<String, Class<? extends Command>>();
	commandLibrary.put("adduser", AddUser.class);
	commandLibrary.put("chuser", ChUser.class);
	commandLibrary.put("deluser", DelUser.class);
	commandLibrary.put("cat", Cat.class);
	commandLibrary.put("cd", Cd.class);
	commandLibrary.put("chmod", Chmod.class);
	commandLibrary.put("ls", Ls.class);
	commandLibrary.put("mkdir", Mkdir.class);
	commandLibrary.put("rm", Rm.class);
	commandLibrary.put("rmdir", Rmdir.class);
	commandLibrary.put("touch", Touch.class);
	commandLibrary.put("writetofile", WriteToFile.class);
    }

    /**
     * 
     * @param bash
     */
    public CommandFactory(Bash bash) {
	this.bash = bash;
    }

    /**
     * 
     * @param args
     *            Array of Strings, command's name should at args[0]
     * @return an instance of the specific command object
     */
    public Command getCommand(String[] args) throws NoSuchMethodException,
	    SecurityException, InstantiationException, IllegalAccessException,
	    IllegalArgumentException, InvocationTargetException {

	Class<? extends Command> commandClass;
	commandClass = commandLibrary.get(args[0]); // get refference to the
						    // class
	Constructor<? extends Command> constructor = commandClass
		.getConstructor(String[].class, Bash.class); // get the command
							     // constructor
	Command command = constructor.newInstance(args, bash); // instantiate a
							       // new command

	return command;
    }
}
