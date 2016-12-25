package fileSystem;

import java.util.ArrayList;
import java.util.List;

import user.User;

public class Directory extends Entity {
    private List<Entity> entities;

    public Directory(String name, String permisions, User user,
	    Directory parent) {
	super(name, permisions, user, parent);
	entities = new ArrayList<Entity>();
	// TODO Auto-generated constructor stub
    }

    public Directory(String name, User user, Directory parent) {
	super(name, "rwx---", user, parent);
	entities = new ArrayList<Entity>();
	// TODO Auto-generated constructor stub
    }

    public void addEntity(Entity entity) {
	entities.add(entity);
    }

    public List<Entity> getEntities() {
	return entities;
    }

    public void setEntities(List<Entity> entities) {
	this.entities = entities;
    }

    public void removeEntity(Entity entity) {
	if (entities.contains(entity)) {
	    entities.remove(entity);
	} else {

	}
    }

    @Override
    public boolean isDirectory() {
	// TODO Auto-generated method stub
	return true;
    }

    public boolean contains(Entity entity) {
	return entities.contains(entity);
    }

    public boolean contains(String name) {
	return getEntity(name) != null ? true : false;
    }

    public Entity getEntity(String name) {
	if (name.equals(".")) {
	    return this;
	}
	if (name.equals("..")) {
	    return this.parent;
	}
	if (name.equals("/") && this.getName().equals("/")) {
	    return this;
	}
	for (Entity now : entities) {
	    if (now.getName().equals(name))
		return now;
	}
	return null;
    }

    @Override
    public boolean isEmpty() {
	return entities.isEmpty();
    }

    public Directory getParent() {
	return parent;
    }
}
