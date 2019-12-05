package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameModels.AbstractMissile;

import java.util.ArrayList;

public class DoubleShootingMode implements IShootingMode {
    @Override
    public String getName() {
        return "Double";
    }

    @Override
    public ArrayList<AbstractMissile> shoot(AbstractCannon cannon) {
        ArrayList<AbstractMissile> missiles = new ArrayList<>();
        cannon.aimUp();
        missiles.add(cannon.simpleShot());
        cannon.aimDown();

        cannon.aimDown();
        missiles.add(cannon.simpleShot());
        cannon.aimUp();

        return missiles;
    }

    @Override
    public void nextMode(AbstractCannon cannon) {
        cannon.toggleMode();
    }
}
