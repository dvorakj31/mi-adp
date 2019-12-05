package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class CannonAimDownCommand extends AbstractGameCommand {
    public CannonAimDownCommand(IGameModel model) {
        super(model);
    }

    @Override
    void execute() {
        this.model.cannonAimDown();
    }
}
