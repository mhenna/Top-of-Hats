package pgdp.game;

import pgdp.game.helper.PlayerCtl;

public class PlayerControls implements Controls {
    @Override
    public void move(Game game, Figure figure) {
        if (PlayerCtl.isUp())
            figure.moveTo(game, Direction.UP);
        else if (PlayerCtl.isDown())
            figure.moveTo(game, Direction.DOWN);
        else if (PlayerCtl.isLeft())
            figure.moveTo(game, Direction.LEFT);
        else if (PlayerCtl.isRight())
            figure.moveTo(game, Direction.RIGHT);
        else if (PlayerCtl.isUp() && PlayerCtl.isRight())
            figure.moveTo(game, Direction.UP_RIGHT);
        else if (PlayerCtl.isUp() && PlayerCtl.isLeft())
            figure.moveTo(game, Direction.UP_LEFT);
        else if (PlayerCtl.isDown() && PlayerCtl.isRight())
            figure.moveTo(game, Direction.DOWN_RIGHT);
        else if (PlayerCtl.isDown() && PlayerCtl.isLeft())
            figure.moveTo(game, Direction.DOWN_LEFT);
        else if (PlayerCtl.isLeft() && PlayerCtl.isUp() && PlayerCtl.isRight())
            figure.moveTo(game, Direction.UP);
        else if (PlayerCtl.isLeft() && PlayerCtl.isDown() && PlayerCtl.isRight())
            figure.moveTo(game, Direction.DOWN);
        else if (PlayerCtl.isLeft() && PlayerCtl.isUp() && PlayerCtl.isDown())
            figure.moveTo(game, Direction.UP_LEFT);
        else if (PlayerCtl.isRight() && PlayerCtl.isUp() && PlayerCtl.isDown())
            figure.moveTo(game, Direction.UP_RIGHT);
        else if (PlayerCtl.isLeft() && PlayerCtl.isRight())
            figure.moveTo(game, Direction.LEFT);
        else if (PlayerCtl.isUp() && PlayerCtl.isDown())
            figure.moveTo(game, Direction.UP);
        else if (PlayerCtl.isAttack())
            figure.attack(game);
    }

    @Override
    public boolean attack(Game game, Figure figure) {
        return PlayerCtl.isAttack();
    }
}
