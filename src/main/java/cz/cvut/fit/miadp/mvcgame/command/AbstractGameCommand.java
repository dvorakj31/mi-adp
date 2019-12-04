package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public abstract class AbstractGameCommand {
    protected IGameModel model;
    protected Object memento;

    public AbstractGameCommand(IGameModel model) {
        this.model = model;
    }

    public void doExecute() {
        this.memento = this.model.createMemento();
        this.execute();
    }

    abstract void execute();

    public void unexecute() {
        this.model.setMemento(memento);
    }
}
