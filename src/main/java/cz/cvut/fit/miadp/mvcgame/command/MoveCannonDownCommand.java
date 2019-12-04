package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class MoveCannonDownCommand extends AbstractGameCommand {
    public MoveCannonDownCommand(IGameModel model) {
        super(model);
    }

    @Override
    public void execute() {
        this.model.moveCannonDown();
    }

    @Override
    public void unexecute() {

    }
}
