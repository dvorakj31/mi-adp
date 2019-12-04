package cz.cvut.fit.miadp.mvcgame.model.gameModels.objects;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractMissile;

public class Missile extends AbstractMissile {
    public Missile(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
        this.posX += MvcGameConfig.MOVE_SPEED;
    }
}
