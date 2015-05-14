/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mls2;

import org.newdawn.slick.geom.Circle;

/**
 *
 * @author user
 */
public class Ball extends Circle {
    float angle;
    float xspeed;
    float yspeed;
    float velocity;
    float friction = (float) 0.98;
    
    public Ball(int x, int y, int r){
        super(x,y,r);
    }

    void bounce(float xV, float yV, float x, float y) {
       float pV = (float) Math.sqrt(Math.pow(xV, 2) + Math.pow(yV, 2));
       float tang = -1 / ((y -getCenterY() ) / (x - getCenterX()));
        if (xspeed >= 0) {                                                     //
            if (xspeed == 0) {                                                 //
                angle = (float) Math.toRadians(-90);                          //
            } else {                                                           //
                angle = (float) Math.atan((yspeed / xspeed));                 //
            }
        } else if (xspeed < 0) {                                               //
            angle = (float) (Math.toRadians(180) + Math.atan((yspeed / xspeed)));
        }
        System.out.println("tangent angle: " + Math.toDegrees(tang));
        System.out.println("angle: " + Math.toDegrees(angle));
        angle = (float) (2 * Math.atan(tang) - angle);                         //
        velocity = (float) (Math.sqrt(Math.pow(yspeed, 2) + Math.pow(xspeed, 2))+ pV); //
        xspeed = (float) (Math.cos(angle) * velocity * 1);                    //
        yspeed = (float) (Math.sin(angle) * velocity * 1);
    }
    void bounce(boolean hor){
        if (hor = false){
            yspeed *= -1;
        } else {
            xspeed *= -1;
        }
    }
     public void updateVars() {
        //xvelocity = (int) (velocity * Math.cos(cAngle));
        //yvelocity = (int) (velocity * Math.sin(cAngle));
        super.setCenterX((float) (super.getCenterX() + xspeed));
        super.setCenterY((float) (super.getCenterY() + yspeed));
//        xspeed *= friction;
//        yspeed *= friction;
    }
     public void wallBounce(int side){
         if (side == 0){
             xspeed *= -1;
         }else{
             yspeed*= -1;
         }
     }
}
