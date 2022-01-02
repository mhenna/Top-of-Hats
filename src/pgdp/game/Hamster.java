package pgdp.game;

import java.util.List;

public class Hamster extends Figure {

    public Hamster(Position position) {
        super(new LocatedBoundingBox(position, new BoundingBox(3, 3)));
    }

    @Override
    public void attack(Game game) {
        List<Entity> entities = game.getCollisionBoard().getCollisions(this, this.getLastDirection());

        for (Entity e : entities) {
            if (e instanceof Figure) {
                if (game.getCollisionBoard().getCollisions(this, this.getLastDirection()).contains(e)) {
                    ((Figure) e).setDiabledCooldown(60);
                    if (((Figure) e).isHasHat()) {
                        ((Figure) e).setHasHat(false);
                        this.setHasHat(true);
                    }
                }
            }
        }

        if (entities.size() == 0) {
            switch (this.getLastDirection()) {
                case UP: {
                    int x = this.getHitBox().get().getPosition().getX() + 1;
                    int y = this.getHitBox().get().getPosition().getY() - 1;
                    List<Entity> entityAddList = game.getEntityAdd();
                    entityAddList.add(new Cookie(new Position(x, y), this.getLastDirection()));
                    game.setEntityAdd(entityAddList);
                    break;
                }

                case UP_LEFT: {
                    int x = this.getHitBox().get().getPosition().getX() - 1;
                    int y = this.getHitBox().get().getPosition().getY() - 1;
                    List<Entity> entityAddList = game.getEntityAdd();
                    entityAddList.add(new Cookie(new Position(x, y), this.getLastDirection()));
                    game.setEntityAdd(entityAddList);
                    break;
                }

                case UP_RIGHT: {
                    int x = this.getHitBox().get().getPosition().getX() + 3;
                    int y = this.getHitBox().get().getPosition().getY() - 1;
                    List<Entity> entityAddList = game.getEntityAdd();
                    entityAddList.add(new Cookie(new Position(x, y), this.getLastDirection()));
                    game.setEntityAdd(entityAddList);
                    break;
                }

                case DOWN: {
                    int x = this.getHitBox().get().getPosition().getX() + 1;
                    int y = this.getHitBox().get().getPosition().getY() + 3;
                    List<Entity> entityAddList = game.getEntityAdd();
                    entityAddList.add(new Cookie(new Position(x, y), this.getLastDirection()));
                    game.setEntityAdd(entityAddList);
                    break;
                }

                case DOWN_LEFT: {
                    int x = this.getHitBox().get().getPosition().getX() - 1;
                    int y = this.getHitBox().get().getPosition().getY() + 3;
                    List<Entity> entityAddList = game.getEntityAdd();
                    entityAddList.add(new Cookie(new Position(x, y), this.getLastDirection()));
                    game.setEntityAdd(entityAddList);
                    break;
                }

                case DOWN_RIGHT: {
                    int x = this.getHitBox().get().getPosition().getX() + 3;
                    int y = this.getHitBox().get().getPosition().getY() + 3;
                    List<Entity> entityAddList = game.getEntityAdd();
                    entityAddList.add(new Cookie(new Position(x, y), this.getLastDirection()));
                    game.setEntityAdd(entityAddList);
                    break;
                }

                case RIGHT: {
                    int x = this.getHitBox().get().getPosition().getX() + 3;
                    int y = this.getHitBox().get().getPosition().getY() + 1;
                    List<Entity> entityAddList = game.getEntityAdd();
                    entityAddList.add(new Cookie(new Position(x, y), this.getLastDirection()));
                    game.setEntityAdd(entityAddList);
                    break;
                }

                case LEFT: {
                    int x = this.getHitBox().get().getPosition().getX() - 1;
                    int y = this.getHitBox().get().getPosition().getY() + 1;
                    List<Entity> entityAddList = game.getEntityAdd();
                    entityAddList.add(new Cookie(new Position(x, y), this.getLastDirection()));
                    game.setEntityAdd(entityAddList);
                    break;
                }
            }
        }
    }

    @Override
    public int getFullMoveCooldown() {
        return 15;
    }

    @Override
    public int getFullAttackCooldown() {
        return 140;
    }

    @Override
    public Image getImage() {
        return Image.HAMSTER;
    }
}
