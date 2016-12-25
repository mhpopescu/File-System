package utils;

/**
 * Value of every permision as in Linux systems
 * 
 * @author Mike
 *
 */
public enum Rights {
    READ(4), WRITE(2), EXECUTE(1);

    int value = 0;

    Rights(int val) {
	value = val;
    }

    public int value() {
	return value;
    }
}
