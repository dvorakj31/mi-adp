package cz.cvut.fit.miadp.mvcgame.model.gameModels.objects;

import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractCannon;

public class Cannon extends AbstractCannon {
    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
