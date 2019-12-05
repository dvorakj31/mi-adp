package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.model.Position;

public interface IMovingStrategy {
    Position updatePosition(Position pos, float power, float angle, long lifetime);

    String getName();
}
