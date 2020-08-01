/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caredog;

import static caredog.CareDog.panel;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

/**
 *
 * @author napoleonsantana17
 */

class Item {
    
    ImageIcon bone = new ImageIcon("bone.png"); 
    ImageIcon water = new ImageIcon("water.png");
    int offset_x = 0;
    int offset_y = 0;
    int org_x = 100;
    int org_y = 450;
    int h = 30;
    int w = 30;
    int x = org_x;
    int y = org_y;
    int left = 10;
    int right = 90;
    int top = 375;
    int bot = 320;
    
    public Item(){
        return;
    }
    
    public Item (int offset_x_in, int offset_y_in){
        offset_x = offset_x_in;
        offset_y = offset_y_in;
        org_x = 100 + offset_x;
        org_y = 450 + offset_y;
        h = 30;
        w = 30;
        x = org_x;
        y = org_y;
    }
    
    public void init(){
        org_x = 100 + offset_x;
        org_y = 450 + offset_y;
        h = 30;
        w = 30;
        x = org_x;
        y = org_y;
    }
    
    public void spawnItem(String type, Graphics g){
        
        if("bone" == type){
            g.drawImage(bone.getImage(), x, y, panel);
        }
        else if("water" == type){
            g.drawImage(water.getImage(), x, y, panel);
        }
    }
    
    
    public boolean containsPoint(int x_in, int y_in){
        if(x_in + 20 > x && x_in  - 20 < x + w){
            if(y_in + 15 > y && y_in - 15< y + h){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean feed(){
        if(left < x && right > x){
            if(top + 15 > y && bot < y - 15){
                init();
                return true;
            }
        }
        
        return false;
    }
    
    public void move(int x_in, int y_in){
        x = x_in;
        y = y_in;
    }
    
}
