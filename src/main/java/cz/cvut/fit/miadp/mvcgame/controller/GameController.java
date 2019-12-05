package cz.cvut.fit.miadp.mvcgame.controller;

import cz.cvut.fit.miadp.mvcgame.command.*;
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
                break;
            case "M":
                this.model.registerCommand(new ChangeShootMode(this.model));
                break;
            case "L":
                this.model.registerCommand(new ChangeMovingStrategy(this.model));
                break;
            case "Q":
                this.model.registerCommand(new CannonPowerDecCommand(this.model));
                break;
            case "E":
                this.model.registerCommand(new CannonPowerIncCommand(this.model));
                break;
            case "A":
                this.model.registerCommand(new CannonAimUpCommand(this.model));
                break;
            case "D":
                this.model.registerCommand(new CannonAimDownCommand(this.model));
                break;
            case "C":
                this.model.registerCommand(new UndoCommand(this.model));
                break;
            default:
                // pass
        }
    }

}
