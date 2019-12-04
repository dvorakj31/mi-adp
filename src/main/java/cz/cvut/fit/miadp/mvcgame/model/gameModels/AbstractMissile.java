package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.GameObject;

public abstract class AbstractMissile extends GameObject {
    public AbstractMissile(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
    public abstract void move();
    public boolean collide(AbstractEnemy enemy) {
        boolean bCollides = true;
        int aX = this.posX;
        int aY = this.posY;
        int bX = enemy.getPosX();
        int bY = enemy.getPosY();

        //GameConfig.COLLIDE_FACTOR;
        bCollides = bCollides && (Math.abs(aX - bX) < MvcGameConfig.COLLISION_DIVERGENCE);
        bCollides = bCollides && (Math.abs(aY - bY) < MvcGameConfig.COLLISION_DIVERGENCE);

        return bCollides;
    }
}
