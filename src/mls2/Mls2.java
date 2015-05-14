/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mls2;

/**
 *
 * @author wanga8384
 */
import java.awt.Font;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import org.newdawn.slick.SlickException;

public class Mls2 extends BasicGame {

    public Mls2() {
        super("Slick2DPath2Glory - SimpleGame");
    }
    Player[] p = new Player[2];
    Line[] wall = new Line[12];
    int control = 0;
    int count = 0;
    int bounceLim=0;
    boolean button = false;
    Ball ball = new Ball(400, 400, 15);    
    @Override
    public void init(GameContainer gc) throws SlickException {
        p[0] = new Player((float)0.8, (float) 0.95, 300, 300, 30,1,1);
        p[1] = new Player(1, (float) 0.95, 550, 550, 30,1,1);

                
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();

       


        if (input.isKeyDown(Input.KEY_A)) {
            p[control].addxVel(-(p[control].acc));
        }
        if (input.isKeyDown(Input.KEY_D)) {
            p[control].addxVel(p[control].acc);
        }

        if (input.isKeyDown(Input.KEY_W)) {
            p[control].addyVel(-(p[control].acc));
        }
        if (input.isKeyDown(Input.KEY_S)) {
            p[control].addyVel(p[control].acc);
        }
        if ((input.isKeyDown(Input.KEY_A)) || input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_W)) {
            button = true;
        } else {
            button = false;
        }
        
        if (button == false) {
            for (int c = 0; c < p.length; c++) {
                p[c].aFriction();
            }
        } else {
            for (int c = 0; c < p.length; c++) {
                if (c != control) {
                    p[c].aFriction();
                }
            }
        }

        if (input.isKeyDown(Input.KEY_SPACE) && count > 10) {
            control += 1;
            if (control > p.length - 1) {
                control = 0;
            }
            count = 0;
        }
        if (count < 40) {
            count += 1;
        }
        bounceLim++;
        if(ball.getCenterX()+ball.getRadius()>800||ball.getCenterX()-ball.getRadius()<0){
            ball.wallBounce(0);
        }
        if(ball.getCenterY()+ball.getRadius()>800||ball.getCenterY()-ball.getRadius()<0){
            ball.wallBounce(1);
        }

        for (int c = 0; c < p.length; c++) {
            if (ball.intersects(p[c])&&bounceLim>30) {
                ball.bounce(p[c].xvelocity,p[c].yvelocity,p[c].getCenterX(),p[c].getCenterY());
                bounceLim = 0;
            }
        }
         for (int c = 0; c < p.length; c++) {
            p[c].updateVars();
        }
         ball.updateVars();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        for (int c = 0; c < p.length; c++) {
            g.draw(p[c]);
        }
        g.draw(ball);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Mls2());
        app.setDisplayMode(1000, 700, false);
        app.setTargetFrameRate(90);
        app.start();

    }

    public void construct(Player player) {
    }
}
