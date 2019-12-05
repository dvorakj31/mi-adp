package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class CannonPowerDecCommand extends AbstractGameCommand {
    public CannonPowerDecCommand(IGameModel model) {
        super(model);
    }

    @Override
    void execute() {
        this.model.cannonPowerDec();
    }
}
