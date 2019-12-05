package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractMissile;

import java.util.ArrayList;

public class SimpleShootingMode implements IShootingMode {
    @Override
    public String getName() {
        return "Simple";
    }

    @Override
    public ArrayList<AbstractMissile> shoot(AbstractCannon cannon) {
        ArrayList<AbstractMissile> missiles = new ArrayList<>();
        missiles.add(cannon.simpleShot());
        return missiles;
    }

    @Override
    public void nextMode(AbstractCannon cannon) {
        cannon.toggleMode();
    }
}
