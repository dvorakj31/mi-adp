package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.model.Position;

public class RealisticMoveStrategy implements IMovingStrategy {

    @Override
    public Position updatePosition(Position pos, float power, float angle, long lifetime) {
        Position ret = new Position();
        ret.setX((int)(pos.getX() + power * Math.cos(angle)));
        ret.setY((int)((pos.getY() + (power * Math.sin(angle))) + Math.abs(Math.pow (1.04, lifetime/10))));
        return ret;
    }

    @Override
    public String getName() {
        return "Realistic";
    }
}
