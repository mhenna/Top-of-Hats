package pgdp.game;

import java.util.List;

public class WolfPingu extends Figure {

    public WolfPingu(Position position) {
        super(new LocatedBoundingBox(position, new BoundingBox(3, 3)));
    }

    @Override
    public void attack(Game game) {
        List<Entity> entities = game.getEntities();

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
    }

    @Override
    public int getFullMoveCooldown() {
        return 10;
    }

    @Override
    public int getFullAttackCooldown() {
        return 120;
    }

    @Override
    public Image getImage() {
        return Image.WOLF_PINGU;
    }
}
