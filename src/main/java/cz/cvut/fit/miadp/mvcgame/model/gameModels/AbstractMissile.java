package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbstractMissile extends GameObject {
    public AbstractMissile(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
    public abstract void move();
    public boolean collide(AbstractEnemy enemy) {
        return (Math.abs(this.posX - enemy.getPosX()) < MvcGameConfig.COLLISION_DIVERGENCE) &&
                (Math.abs(this.posY - enemy.getPosY()) < MvcGameConfig.COLLISION_DIVERGENCE);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitMissile(this);
    }
}
