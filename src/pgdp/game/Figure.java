package pgdp.game;

import java.util.List;

public abstract class Figure extends Entity {
    private Direction lastDirection;
    private int attackCooldown;
    private int moveCooldown;
    private int diabledCooldown;
    private boolean hasHat;
    private Controls controls;

    public Figure(LocatedBoundingBox locatedBoundingBox) {
        super(locatedBoundingBox);
        this.lastDirection = Direction.UP;
        this.attackCooldown = this.getFullAttackCooldown(); // needs to be changed to getFullAttackCooldown()
        this.moveCooldown = this.getFullMoveCooldown();
        this.diabledCooldown = 0;
        this.hasHat = false;
    }

    public abstract void attack(Game game);
    public abstract int getFullMoveCooldown();
    public abstract int getFullAttackCooldown();

    public boolean visit(Game game) {
        if (this.diabledCooldown > 0) {
            this.diabledCooldown--;
            if (this.diabledCooldown > 0)
                return true;
        }

        this.moveCooldown--;
        if (this.moveCooldown == 0) {
            this.moveCooldown = this.getFullMoveCooldown();
            this.controls.move(game, this);
        }

        if (this.attackCooldown > 0)
            this.attackCooldown--;
        if (this.attackCooldown == 0) {
            if (this.controls.attack(game, this)) {
                this.attackCooldown = this.getFullAttackCooldown();
                this.attack(game);
            }
        }

        return true;
    }

    public void moveTo(Game game, Direction direction) {
        this.lastDirection = direction;

        List<Entity> entities = game.getEntities();

        for (Entity e : entities) {
            if (e instanceof Hat) {
                if (game.getCollisionBoard().getCollisions(this, direction).contains(e))
                    if (!this.hasHat) {
                        this.hasHat = true;
                        game.getCollisionBoard().remove(e);
                        ((Hat) e).setDontRemove(false);
                    }
            }
        }

        game.getCollisionBoard().move(this, this.lastDirection);
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public void setControls(Controls controls) {
        this.controls = controls;
    }

    public void setDiabledCooldown(int diabledCooldown) {
        this.diabledCooldown = diabledCooldown;
    }

    public void setHasHat(boolean hasHat) {
        this.hasHat = hasHat;
    }

    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }

    public void setMoveCooldown(int moveCooldown) {
        this.moveCooldown = moveCooldown;
    }

    public Controls getControls() {
        return controls;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public int getDiabledCooldown() {
        return diabledCooldown;
    }

    public int getMoveCooldown() {
        return moveCooldown;
    }

    public boolean isHasHat() {
        return hasHat;
    }
}
