package bash;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 
 * @author Mike
 *
 */
public class InputReader {
    private BufferedReader br;

    /**
     * 
     * @param inputFile
     * @throws FileNotFoundException
     */
    public InputReader(String inputFile) throws FileNotFoundException {
	br = new BufferedReader(new FileReader(inputFile));
    }

    /**
     * Read every line from the input file; parse it and return an array of
     * arguments; every argument is a string, command name is saved as args[0]
     * string between commas are saved as a single String
     * 
     * @return
     */
    public String[] nextCommand() {
	String line;
	String[] args;
	try {
	    line = br.readLine();
	} catch (IOException e) {
	    return null;
	}
	if (line != null) {
	    int i = 0;
	    StringTokenizer st = new StringTokenizer(line, "\"");
	    StringTokenizer command = new StringTokenizer(st.nextToken(), " ");
	    if (st.hasMoreElements()) {
		args = new String[command.countTokens() + 1];
	    } else {
		args = new String[command.countTokens()];
	    }
	    while (command.hasMoreTokens()) {
		args[i++] = command.nextToken();
	    }

	    if (st.hasMoreElements()) {
		args[i] = "\"" + st.nextToken() + "\"";
	    }
	    return args;
	}
	return null;
    }
}
