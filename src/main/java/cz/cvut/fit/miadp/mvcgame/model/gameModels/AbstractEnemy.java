package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.model.GameObject;

public abstract class AbstractEnemy extends GameObject {
    public AbstractEnemy(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
}
