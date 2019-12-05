package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;

public class SimpleMoveStrategy implements IMovingStrategy {

    @Override
    public Position updatePosition(Position pos, float power, float angle, long lifetime) {
        Position ret = new Position();
        ret.setX((int)(pos.getX() + (power * 3 * Math.cos(angle))));
        ret.setY((int)(pos.getY() + (power * 3 * Math.sin(angle))));
        return ret;
    }

    @Override
    public String getName() {
        return "Simple";
    }
}
