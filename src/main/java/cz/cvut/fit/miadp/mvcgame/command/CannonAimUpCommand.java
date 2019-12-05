package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class CannonAimUpCommand extends AbstractGameCommand {
    public CannonAimUpCommand(IGameModel model) {
        super(model);
    }

    @Override
    void execute() {
        this.model.cannonAimUp();
    }
}
