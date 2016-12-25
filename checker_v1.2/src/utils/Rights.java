package utils;

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
