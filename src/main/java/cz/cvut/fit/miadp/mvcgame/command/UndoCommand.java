package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class UndoCommand extends AbstractGameCommand {
    public UndoCommand(IGameModel model) {
        super(model);
    }

    @Override
    void execute() {
        this.model.undoLastCmd();
    }
}
