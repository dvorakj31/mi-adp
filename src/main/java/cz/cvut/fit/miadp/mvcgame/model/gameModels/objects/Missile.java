package cz.cvut.fit.miadp.mvcgame.model.gameModels.objects;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

public class Missile extends AbstractMissile {

    public Missile(int x, int y, float angle, float power, IMovingStrategy strategy) {
        super(x, y, angle, power, strategy);
    }
}
