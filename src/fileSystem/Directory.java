package fileSystem;

import java.util.ArrayList;
import java.util.List;

import user.User;

/**
 * 
 * @author Mike
 *
 */
public class Directory extends Entity {
    private List<Entity> entities;

    public Directory(String name, String permisions, User user,
	    Directory parent) {
	super(name, permisions, user, parent);
	entities = new ArrayList<Entity>();
    }

    /**
     * Create a new directory with default permisions
     * 
     * @param name
     * @param user
     * @param parent
     */
    public Directory(String name, User user, Directory parent) {
	super(name, "rwx---", user, parent);
	entities = new ArrayList<Entity>();
    }

    /**
     * 
     * @param entity
     */
    public void addEntity(Entity entity) {
	entities.add(entity);
    }

    public List<Entity> getEntities() {
	return entities;
    }

    /**
     * 
     * @param entity
     */
    public void removeEntity(Entity entity) {
	if (entities.contains(entity)) {
	    entities.remove(entity);
	} else {

	}
    }

    @Override
    public boolean isDirectory() {
	return true;
    }

    /**
     * 
     * @param entity
     * @return true if the directory contains the file/directory received as
     *         parameter
     */
    public boolean contains(Entity entity) {
	return entities.contains(entity);
    }

    /**
     * 
     * @param name
     * @return true if the directory contains the file/directory with the name
     *         received as parameter
     */
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
