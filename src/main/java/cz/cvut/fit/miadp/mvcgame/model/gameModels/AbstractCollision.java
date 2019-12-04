package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbstractCollision extends LifetimeLimitedGO {
    public AbstractCollision(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCollision(this);
    }
}
