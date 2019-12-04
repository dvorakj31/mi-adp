package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.visitor.IVisitable;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class GameObject implements IVisitable {
    protected int posX;
    protected int posY;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Position getPos() {
        return new Position(posX, posY);
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
