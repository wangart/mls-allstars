/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mls2;

import org.newdawn.slick.geom.Circle;

/**
 *
 * @author wanga8384
 */
public class Player extends Circle {

    float velocity;
    float xvelocity = 0;
    float yvelocity = 0;
    float acc;
    float friction;
    float cAngle;
    float power;
    float speed;

    public Player(float acci, float frictioni, int startX, int startY, int radius, float p, float maxS) {
        super(startX, startY, radius);
        acc = acci;
        friction = frictioni;
        power = p;
        speed = maxS;
    }

    public void setVelocity(float v) {
    }

    public void updateVars() {
        if (xvelocity > 5) {
            xvelocity = 5;
        }
        if (yvelocity > 5) {
            yvelocity = 5;
        }
        if (xvelocity == 0 && yvelocity == 0) {
        } else if (xvelocity == 0) {
            if (yvelocity > 0) {
                cAngle = 90;
            } else if (yvelocity < 0) {
                cAngle = -90;
            }

        } else if (yvelocity == 0) {
            if (xvelocity > 0) {
                cAngle = 0;
            } else if (xvelocity < 0) {
                cAngle = 180;
            }
        } else {
            if (yvelocity > 0 && xvelocity > 0) {
                cAngle = (float) Math.toDegrees(Math.atan(yvelocity / xvelocity));
            } else if (yvelocity < 0 && xvelocity < 0) {
                cAngle = (float) (180 + Math.toDegrees(Math.atan(yvelocity / xvelocity)));
            } else if (yvelocity > 0 && xvelocity < 0) {
                cAngle = (float) Math.toDegrees(180 + Math.atan(yvelocity / xvelocity));
            } else {
                cAngle = (float) Math.toDegrees(360 + Math.atan(yvelocity / xvelocity));
            }
        }

        //xvelocity = (int) (velocity * Math.cos(cAngle));
        //yvelocity = (int) (velocity * Math.sin(cAngle));
        super.setCenterX((float) (super.getCenterX() + xvelocity));
        super.setCenterY((float) (super.getCenterY() + yvelocity));

    }

    public void addxVel(float x) {
        xvelocity += x;
    }

    public void addyVel(float y) {
        yvelocity += y;
    }

    public void aFriction() {
        xvelocity *= friction;
        yvelocity *= friction;
    }
}
