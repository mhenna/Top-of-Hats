package pgdp.game;

import java.util.ArrayList;
import java.util.List;
import pgdp.game.helper.Random;

public abstract class AIControls implements Controls {

    private Entity target;

    public Entity selectTarget(Game game) {
        if (this.target == null || !this.target.getHitBox().isEmpty()
                || (this.target instanceof Figure && !(((Figure)this.target).isHasHat()))) {

            List<Entity> entities = game.getEntities();
            List<Entity> entityRandomPool = new ArrayList<>();

            for (Entity e : entities) {
                if (e instanceof Hat && !e.getHitBox().isEmpty()
                || e instanceof Figure && ((Figure) e).isHasHat())
                    entityRandomPool.add(e);
            }

            int randomIndex = Random.get(0, entityRandomPool.size() - 1);
            this.target = entityRandomPool.get(randomIndex);
        }

        return this.target;
    }

    public Direction selectDirection(Entity other, Figure self) {
        Position otherPosition = null;
        Position selfPosition = null;
        Direction d = null;

        if (other.getHitBox().isPresent())
            otherPosition = other.getHitBox().get().getPosition();
        else d = Direction.UP;

        if (self.getHitBox().isPresent())
            selfPosition = self.getHitBox().get().getPosition();
        else d = Direction.UP;

        if (d == null) {
            if ((otherPosition.getX() == selfPosition.getX()) && (otherPosition.getY() == selfPosition.getY()))
                d = Direction.UP;

            if (otherPosition.getX() == selfPosition.getX()) {
                if (selfPosition.getY() > otherPosition.getY())
                    d = Direction.LEFT;
                else d = Direction.RIGHT;
            } else if (otherPosition.getY() == selfPosition.getY()) {
                if (selfPosition.getX() > otherPosition.getX())
                    d = Direction.UP;
                else d = Direction.DOWN;
            } else {
                int selfX = selfPosition.getX();
                int selfY = selfPosition.getY();
                int otherX = otherPosition.getX();
                int otherY = otherPosition.getY();

                if (selfX > otherX && selfY > otherY)
                    d = Direction.UP_LEFT;
                else if (selfX > otherX && selfY < otherY)
                    d = Direction.UP_RIGHT;
                else if (selfX < otherX && selfY > otherY)
                    d = Direction.DOWN_LEFT;
                else if (selfX < otherX && selfY < otherY)
                    d = Direction.DOWN_RIGHT;
            }
        }

        if (d == null)
            d = Direction.UP;

        return d;
    }

    public void move(Game game, Figure figure) {
        Direction direction = null;
        if (!figure.isHasHat()) {
            Entity other = this.selectTarget(game);
            direction = this.selectDirection(other, figure);
        } else {
            List<Direction> directions = new ArrayList<>();
            directions.add(Direction.DOWN_RIGHT);
            directions.add(Direction.DOWN);
            directions.add(Direction.DOWN_LEFT);
            directions.add(Direction.UP);
            directions.add(Direction.UP_LEFT);
            directions.add(Direction.UP_RIGHT);
            directions.add(Direction.LEFT);
            directions.add(Direction.RIGHT);

            int randomIndex = Random.get(0, directions.size() - 1);
            direction = directions.get(randomIndex);
        }

        figure.moveTo(game, direction);
    }

    public abstract boolean attack(Game game, Figure figure);
}
