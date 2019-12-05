package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class ChangeMovingStrategy extends AbstractGameCommand {
    public ChangeMovingStrategy(IGameModel model) {
        super(model);
    }

    @Override
    void execute() {
        this.model.changeMovingStrategy();
    }
}
