package pgdp.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private CollisionBoard collisionBoard;
    private List<Entity> entities;
    private List<Entity> entityAdd;

    public Game(CollisionBoard collisionBoard) {
        this.collisionBoard = collisionBoard;
        this.entities = new ArrayList<>();
        this.entityAdd = new ArrayList<>();
    }

    public void runTick() {
        List<Entity> entitiesToRemove = new ArrayList<>();
        for (Entity e : entities) {
            if (!e.visit(this)) {
                entitiesToRemove.add(e);
                this.collisionBoard.remove(e);
            }
        }
        entities.removeAll(entitiesToRemove);


        List<Entity> entityAddCopy = new ArrayList<>(entityAdd);
        List<Entity> successfullyAddedEntities = new ArrayList<>();

        for (Entity e : entityAdd) {
            boolean addResult = this.collisionBoard.set(e);
            if (addResult) {
                entityAddCopy.remove(e);
                successfullyAddedEntities.add(e);
            }
        }

        this.entities.addAll(successfullyAddedEntities);
        this.entityAdd = new ArrayList<>(entityAddCopy);
    }

    public CollisionBoard getCollisionBoard() {
        return collisionBoard;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getEntityAdd() {
        return entityAdd;
    }

    public void addEntity(Entity e) {
        this.entities.add(e);
    }

    public void removeEntity(Entity e) {
        this.entities.remove(e);
    }

    public void addEntityAdd(Entity e) {
        this.entityAdd.add(e);
    }

    public void removeEntityAdd(Entity e) {
        this.entityAdd.remove(e);
    }

    public void setCollisionBoard(CollisionBoard collisionBoard) {
        this.collisionBoard = collisionBoard;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void setEntityAdd(List<Entity> entityAdd) {
        this.entityAdd = entityAdd;
    }
}
