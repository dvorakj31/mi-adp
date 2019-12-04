package cz.cvut.fit.miadp.mvcgame.model.gameModels;

import cz.cvut.fit.miadp.mvcgame.model.GameObject;

public abstract class LifetimeLimitedGO extends GameObject {
    private long bornAt = System.currentTimeMillis();

    public long getAge() {
        return System.currentTimeMillis() - this.bornAt;
    }
}
