/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caredog;

import static caredog.CareDog.panel;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author napoleonsantana17
 */
class NPC {
    
    
        String idle[] = new String[7];
        ImageIcon dog;
        String type = "null";
        int idle_state = 0;
        int pause = 0;
        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;
        
        public NPC(){
            System.out.println("missing parameters!");
        }
        
        public NPC(String type_in){
            type = type_in;
            dog = new ImageIcon(type + "/idle/sprite_0.png");
            w = dog.getIconWidth();
            h = dog.getIconHeight();
        }
        
        public NPC(String type_in, int x_in, int y_in){
            type = type_in;
            dog = new ImageIcon(type + "/idle/sprite_0.png");
            x = x_in;
            y = y_in;
        }

        public void draw(Graphics g){
            g.drawImage(dog.getImage(), x, y, panel);
        }
        
        public void setLocation(int x_in, int y_in){
            x_in = x;
            y_in = y;
        }
        
        public boolean containsPoint(int x_in, int y_in){
                if(x_in + 20 > x + 68 && x_in  - 20 < x + w + 68){
                    if(y_in + 15 > y + 53 && y_in - 15< y + h + 53){
                        return true;
                    }
                }
            return false;
        }
    
        
        public void idle(){
            if(pause > 4){
                if(idle_state < 7){
                    idle[idle_state] = type + "/idle/sprite_" + idle_state + ".png";
                    dog = new ImageIcon(idle[idle_state]);
                    idle_state++;
                }
                else{
                    idle_state = 0;
                }

                pause = 0;
            }
            else{
                pause++;
            }

        }
}
