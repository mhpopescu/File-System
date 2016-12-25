package bash;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class InputReader {
    private BufferedReader br;

    public InputReader(String inputFile) throws FileNotFoundException {
	br = new BufferedReader(new FileReader(inputFile));
    }

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
