package fileSystem;

import user.User;

public class File extends Entity {
    private String data;

    /**
     * 
     * @param name
     * @param permisions
     * @param user
     * @param parent
     */
    public File(String name, String permisions, User user, Directory parent) {
	super(name, permisions, user, parent);
    }

    /**
     * 
     * @param name
     * @param user
     * @param parent
     */
    public File(String name, User user, Directory parent) {
	super(name, "rwx---", user, parent);
    }

    public void setData(String data) {
	this.data = data;
    }

    public String getData() {
	return data;
    }

    @Override
    public boolean isDirectory() {
	return false;
    }

    @Override
    public boolean isEmpty() {
	return data.isEmpty();
    }

}
