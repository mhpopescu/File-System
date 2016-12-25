package user;

import fileSystem.Directory;

public class User {
    private String name;
    private Directory homeDirectory;

    public User(String name) {
	this.setName(name);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Directory getHomeDirectory() {
	return homeDirectory;
    }

    public void setHomeDirectory(Directory homeDirectory) {
	this.homeDirectory = homeDirectory;
    }
}
