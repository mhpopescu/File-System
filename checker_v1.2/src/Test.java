import java.io.FileNotFoundException;

import bash.Bash;

public class Test {

    public static void main(String[] args) {
	Bash bash;
	try {
	    bash = new Bash(args[0]);
	    bash.start();
	} catch (FileNotFoundException e1) {
	    System.out.println("File " + args[0] + " not found");
	}
    }
}
