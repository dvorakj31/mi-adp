package cz.cvut.fit.miadp.mvcgame;

import java.util.List;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
// in future, use Bridge to remove this dependency
import cz.cvut.fit.miadp.mvcgame.view.GameView;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MvcGame
{
    private Position logoPos;
    private GameModel model;
    private GameView view;
    private GameController controller;


    public void init()
    {
        this.model = new GameModel();
        this.view = new GameView(this.model);
        this.controller = this.view.createController();
        logoPos = new Position( MvcGameConfig.INIT_CANNON_X, MvcGameConfig.INIT_CANNON_Y );
    }

    public void processPressedKeys(List<String> pressedKeysCodes)
    {
        for(String code : pressedKeysCodes)
            this.controller.onKeyPress(code);
    }

    public void update()
    {
        this.model.timeTick();
    }

    public void render(IGameGraphics gr)
    {
//        gr.drawImage(new Image("images/cannon.png"), logoPos.getX(), logoPos.getY());
        this.view.setGraphics(gr);
        this.view.render();
    }

    public String getWindowTitle()
    {
        return "The MI-ADP.16 MvcGame";
    }

    public int getWindowWidth()
    {
        return MvcGameConfig.MAX_X;
    }

    public int getWindowHeight()
    {
        return  MvcGameConfig.MAX_Y;
    }
}