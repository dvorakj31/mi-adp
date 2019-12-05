package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class ChangeShootMode extends AbstractGameCommand {
    public ChangeShootMode(IGameModel model) {
        super(model);
    }

    @Override
    void execute() {
        this.model.cannonToggleShootingMode();
    }
}
