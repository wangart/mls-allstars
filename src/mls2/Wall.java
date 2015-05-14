/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mls2;

import org.newdawn.slick.geom.Line;

/**
 *
 * @author user
 */
public class Wall extends Line{
    boolean hor = false;
    public Wall(float x1,float y1,float x2,float y2){
        super(x1,y1,x2,y2);
        if (y1==y2){
            hor = false;
        }        
    }
}
