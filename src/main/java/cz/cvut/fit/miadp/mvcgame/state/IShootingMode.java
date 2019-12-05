package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractMissile;

import java.util.ArrayList;

public interface IShootingMode {
    String getName();
    ArrayList<AbstractMissile> shoot(AbstractCannon cannon);
    void nextMode(AbstractCannon cannon);
}
