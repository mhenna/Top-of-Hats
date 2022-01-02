package pgdp.game;

import java.util.List;

public class Cookie extends Entity {
    private Direction direction;

    public Cookie(Position position, Direction direction) {
        super(new LocatedBoundingBox(position, new BoundingBox(1, 1)));
        this.direction = direction;
    }

    @Override
    public boolean visit(Game game) {
        if (game.getCollisionBoard().hasCollisions(this, this.direction)) {
            List<Entity> entitiesCollidedWith = game.getCollisionBoard().getCollisions(this, this.direction);

            for (Entity e : entitiesCollidedWith) {
                if (e instanceof Figure figure) {
                    figure.setDiabledCooldown(60);

                    if (figure.isHasHat()) {
                        figure.setHasHat(false);
                        if (e.getHitBox().isPresent())
                            game.addEntityAdd(new Hat(e.getHitBox().get().getPosition()));
                    }
                }
            }
        }else {
            game.getCollisionBoard().move(this, this.direction);
            return true;
        }

        return false;
    }

    @Override
    public Image getImage() {
        return Image.COOKIE;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
