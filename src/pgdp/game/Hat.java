package pgdp.game;

public class Hat extends Entity {
    private boolean dontRemove;

    public Hat(Position position) {
        super(new LocatedBoundingBox(position, new BoundingBox(1, 1)));
        this.dontRemove = true;
    }

    @Override
    public boolean visit(Game game) {
        return dontRemove;
    }

    @Override
    public Image getImage() {
        return Image.HAT;
    }

    public void setDontRemove(boolean dontRemove) {
        this.dontRemove = dontRemove;
    }

    public boolean isDontRemove() {
        return dontRemove;
    }
}
