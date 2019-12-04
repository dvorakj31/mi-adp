package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.model.GameObject;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbstractEnemy extends GameObject {
    public AbstractEnemy(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }

}
