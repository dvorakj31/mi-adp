package cz.cvut.fit.miadp.mvcgame.controller;

import cz.cvut.fit.miadp.mvcgame.command.MoveCannonDownCommand;
import cz.cvut.fit.miadp.mvcgame.command.MoveCannonUpCommand;
import cz.cvut.fit.miadp.mvcgame.command.ShootMissileCommand;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.awt.event.KeyEvent;

public class GameController {
    private final IGameModel model;

    public GameController(IGameModel model) {
        this.model = model;
    }

    public void onKeyPress(String code) {
        switch(code) {
            case "UP":
                this.model.registerCommand(new MoveCannonUpCommand(this.model));
                break;
            case "DOWN":
                this.model.registerCommand(new MoveCannonDownCommand(this.model));
                break;
            case "SPACE":
                this.model.registerCommand(new ShootMissileCommand(this.model));
            default:
                // pass
        }
    }

}
