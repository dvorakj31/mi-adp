package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.model.GameObject;

public abstract class AbstractCannon extends GameObject {

    protected int x;
    protected int y;


    public abstract int getX();
    public abstract int getY();

    public abstract void setX(int x);
    public abstract void setY(int y);
}
