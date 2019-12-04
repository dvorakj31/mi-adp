package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class ShootMissileCommand extends AbstractGameCommand {
    public ShootMissileCommand(IGameModel model) {
        super(model);
    }

    @Override
    void execute() {
        this.model.cannonShoot();
    }

}
