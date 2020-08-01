/* Napoleon Santana pd.6
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caredog;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Napoleon
 */
public class CareDog {

    /**
     * @param args the command line arguments
     */
    static ColorPanel panel;
    static Container pane;
    static int amount_input = 0;
    
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame gui = new JFrame();
        gui.setTitle("Doggy");
        gui.setSize(300,600);
        gui.setResizable(false);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new ColorPanel();
        pane = gui.getContentPane();
        pane.add(panel);
        gui.setVisible(true);
        gui.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                if(panel.input == true && amount_input < 10){
                    if(e.getKeyCode() == 10){
                        panel.input = false;
                    }
                    if(e.getKeyCode() == 20){
                        return;
                    }
                    if(e.getKeyCode() == 8){
                        amount_input -= 1;
                        panel.user_input = panel.user_input.substring(0, Math.max(0, amount_input));
                    }
                    else{
                        panel.user_input +=  Character.toString(e.getKeyChar());
                        amount_input += 1;
                    }
                }
                else{
                    if(e.getKeyCode() == 32 && panel.end == true){
                        panel.init();
                    }
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //To change body of generated methods, choose Tools | Templates.
            }

        });
        ImageIcon img = new ImageIcon("icon.png");
        gui.setIconImage(img.getImage());
    }
    
}
