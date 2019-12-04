package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class MoveCannonUpCommand extends AbstractGameCommand {

    public MoveCannonUpCommand(IGameModel model) {
        super(model);
    }

    @Override
    void execute() {
        this.model.moveCannonUp();
    }

}
