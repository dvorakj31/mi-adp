package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.model.GameObject;

public abstract class AbstractCollision extends GameObject {
    public AbstractCollision(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
}
