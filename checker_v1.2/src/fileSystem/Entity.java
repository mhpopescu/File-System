package fileSystem;

import user.User;

public abstract class Entity {
    private String name;
    private User user;
    // first three are owner permisions
    // last tree are others permisions
    private int permisions = 0;
    private final String permisionsTypes = "rwxrwx";
    protected Directory parent = null;
    static int shiftToOwnerPermisions = 3;

    /**
     * 
     * @param name
     * @param permisions
     * @param user
     * @param parent
     */
    public Entity(String name, String permisions, User user, Directory parent) {
	this.name = name;
	setPermisions(permisions);
	this.user = user;
	this.parent = parent;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    /**
     * 
     * @param newPermisions
     */
    public void setPermisions(String newPermisions) {
	for (int i = 0; i < permisionsTypes.length(); ++i) {
	    if (newPermisions.charAt(i) == permisionsTypes.charAt(i)) {
		permisions |= 1 << (permisionsTypes.length() - i - 1);
	    }
	}
    }

    /**
     * 
     * @param newPermisions
     */
    public void setPermisions(int newPermisions) {
	permisions = (newPermisions / 10) << 3;
	permisions |= (newPermisions % 10);

    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public abstract boolean isDirectory();

    /**
     * 
     * @param user
     * @param right
     * @return
     */
    public boolean hasRight(User user, int right) {
	if (this.user.equals(user)) {
	    right <<= shiftToOwnerPermisions;
	}
	return (permisions & right) == right;
    }

    public void setParent(Directory dir) {
	parent = dir;
    }

    private String permisionsToString() {
	String res = "";
	for (int i = 0; i < permisionsTypes.length(); ++i) {
	    if ((permisions & (1 << (permisionsTypes.length() - i - 1))) != 0) {
		res += permisionsTypes.charAt(i);
	    } else {
		res += "-";
	    }
	}
	if (this.isDirectory())
	    return "d" + res;
	else
	    return "f" + res;
    }

    @Override
    public String toString() {
	return getName() + " " + permisionsToString() + " " + user.getName();
    }

    public abstract boolean isEmpty();
}
