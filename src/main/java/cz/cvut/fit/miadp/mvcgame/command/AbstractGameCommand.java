package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public abstract class AbstractGameCommand {
    protected IGameModel model;
    protected Object memento;

    public AbstractGameCommand(IGameModel model) {
        this.model = model;
    }

    public abstract void execute();

    public abstract void unexecute();
}
