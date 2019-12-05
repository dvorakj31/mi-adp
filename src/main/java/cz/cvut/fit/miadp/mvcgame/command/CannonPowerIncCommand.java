package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class CannonPowerIncCommand extends AbstractGameCommand {
    public CannonPowerIncCommand(IGameModel model) {
        super(model);
    }

    @Override
    void execute() {
        this.model.cannonPowerInc();
    }
}
