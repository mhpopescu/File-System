package errors;

import java.util.HashMap;
import java.util.Map;

public class Errors {
    final private static Map<Integer, String> errors;
    static {
	errors = new HashMap<Integer, String>();
	errors.put(-1, "Is a directory");
	errors.put(-2, "No such directory");
	errors.put(-3, "Not a directory");
	errors.put(-4, "No rights to read");
	errors.put(-5, "No rights to write");
	errors.put(-6, "No rights to execute");
	errors.put(-7, "File already exists");
	errors.put(-8, "User does not exist");
	errors.put(-9, "User already exists");
	errors.put(-10, "No rights to change user status");
	errors.put(-11, "No such file");
	errors.put(-12, "No such file or directory");
	errors.put(-13, "Cannot delete parent or current directory");
	errors.put(-14, "Non empty directory");
    }

    public static void printError(Integer id, String[] args) {
	System.out.print(id + ": ");
	System.out.print(args[0]);
	for (int i = 1; i < args.length; ++i) {
	    System.out.print(" " + args[i]);
	}
	System.out.println(": " + errors.get(id));
    }
}
