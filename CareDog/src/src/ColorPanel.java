/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caredog;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import static java.awt.SystemColor.text;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.TextLayout;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Napoleon
 */
class ColorPanel extends JPanel{

    ImageIcon bar = new ImageIcon("hunger_bar.png");
    ImageIcon bg = new ImageIcon("bg.png");
    Item bone = new Item();
    Item water = new Item(50, 0);
    NPC dog = new NPC("dog", -10, 350);
    Color greenish = new Color(22, 87, 0);
    Color gray = new Color(222, 222, 220);
    Color redish = new Color(232, 46, 46);
    Color blueish = new Color(103, 195, 252);
    Color gold = new Color(251,189,0);
    int x = 0;
    int y = 0;
    int scoreboard_x = 60;
    int scoreboard_y = 200;
    double speed = 0.025;
    int score = 0;
    int hunger = 1;
    int health = 1;
    int thurst = 1;
    boolean water_pressed =false;
    boolean bone_pressed = false;
    boolean triggerHunger = false;
    boolean end = false;
    boolean input = true;
    String user_input = "";
    Font block_font;
    Font block_fontB;
    
    public ColorPanel() {
        try {
            newfont();
        } catch (FontFormatException ex) {
            Logger.getLogger(ColorPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ColorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setBackground(Color.BLACK);
        addMouseListener(new PanelListener());
        addMouseMotionListener(new PanelMotionListener());
    }
    
    public void init(){
            bar = new ImageIcon("hunger_bar.png");
            bg = new ImageIcon("bg.png");
            bone = new Item();
            water = new Item(50, 0);
            dog = new NPC("dog", -10, 350);
            greenish = new Color(22, 87, 0);
            gray = new Color(222, 222, 220);
            redish = new Color(232, 46, 46);
            blueish = new Color(103, 195, 252);
            gold = new Color(251,189,0);
            x = 0;
            y = 0;
            scoreboard_x = 60;
            scoreboard_y = 200;
            speed = 0.025;
            score = 0;
            hunger = 1;
            health = 1;
            thurst = 1;
            water_pressed =false;
            bone_pressed = false;
            triggerHunger = false;
            end = false;
    }
    
    public void paintComponent(Graphics g){
        if(input == true){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setFont(block_font);
            g.setColor(gold);
             g.drawString("Enter Name", getWidth() / 4, getHeight() / 4);
            g.drawString(user_input, getWidth() / 4, getHeight() / 2);
        }
        else if(health < 170){
            g.drawImage(bg.getImage(), 0, 0, this);
            g.drawImage(bar.getImage(), 55, 30, this);
            g.drawImage(bar.getImage(), 55, 80, this);
            g.drawImage(bar.getImage(), 55, 130, this);
            drawBar("hunger", g);
            drawBar("health", g);
            drawBar("water", g);

            dog.draw(g);
            dog.idle();

            g.setFont(block_font);
            g.setColor(gold);
            g.drawString(Integer.toString(score), scoreboard_x, scoreboard_y );

            updateHealth();
            randomize();

            bone.spawnItem("bone", g);
            water.spawnItem("water", g);
        }
        else{
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setFont(block_font);
            g.setColor(Color.RED);
            g.drawString("GAMEOVER", getWidth() / 4, getHeight() / 2);
            end = true;
        }
        
        try {
            Thread.sleep(10);
            repaint();
        } catch (InterruptedException ex) {
            Logger.getLogger(ColorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateHealth(){
        if(thurst >= 170 || hunger >= 170 && health < 170){
            health += 1;
        }
    }
    
    public void randomize(){
        Random rng = new Random();
        if(rng.nextDouble() < 0.95 + speed){
            return;
        }
        else{
            if(hunger < 170){
                hunger += 2;
            }
        }
        
        if(rng.nextDouble() < 0.6 + speed){
            return;
        }
        else{
            if(thurst < 170){
                thurst += 4;
            }
        }
    }
    
    public void drawBar(String type, Graphics g){
        if("hunger".equals(type)){
            Color oldColor = g.getColor();
            g.setColor(gray);
            g.drawRect(58, 33, 174 - hunger, 17);
            g.setColor(Color.WHITE);
            g.fillRect(60, 35, 171 - hunger, 14);
            g.setColor(oldColor);
        }
        else if("health".equals(type)){
            Color oldColor = g.getColor();
            g.setColor(redish);
            g.drawRect(58, 33 + 50, 174 - health, 17);
            g.setColor(Color.RED);
            g.fillRect(60, 35 + 50, 171 - health, 14);
            g.setColor(oldColor);
        }
        else if("water".equals(type)){
            Color oldColor = g.getColor();
            g.setColor(blueish);
            g.drawRect(58, 33 + 100, 174 - thurst, 17);
            g.setColor(Color.BLUE);
            g.fillRect(60, 35 + 100, 171 - thurst, 14);
            g.setColor(oldColor);
        }
    }
    
    private class PanelListener extends MouseAdapter{
        public void mousePressed(MouseEvent e){
            x = e.getX();
            y = e.getY();
            
            
            if(bone.containsPoint(x, y)){
                bone_pressed = true;
            }
            else if(water.containsPoint(x,y)){
                water_pressed = true;
            }
            else if(dog.containsPoint(x,y)){
                int per_thurst = Math.abs(thurst - 170);
                int per_hunger = Math.abs(hunger - 170);
                int per_health = Math.abs(health - 170);
                
                
                score += (per_thurst + per_hunger + per_health) / 20;
            }
        }
        
        public void mouseReleased(MouseEvent e){
            bone_pressed = false;
            water_pressed = false;
            
            if(bone.feed() == true){
                 hunger = Math.max(hunger - 10, 0);
            }
            else if(water.feed() == true){
                thurst = Math.max(thurst - 10, 0);
            }
        }
    }

    private class PanelMotionListener extends MouseMotionAdapter{
        public void mouseDragged(MouseEvent e){
            if(bone_pressed == true){
                x = e.getX() - 20;
                y = e.getY() - 15;
                bone.move(x, y);
            }
            else if(water_pressed == true){
                x = e.getX() - 20;
                y = e.getY() - 15;
                water.move(x, y);
            }

        }
    } 
    
    
    public void newfont() throws MalformedURLException, FontFormatException, IOException{
        URL fontUrl = new URL("https://drive.google.com/uc?export=download&id=0B9ZK6IxThvNFajB2MTNtSmVTRVk");
        block_font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
        block_font = block_font.deriveFont(Font.PLAIN,20);
        block_fontB = block_font.deriveFont(Font.PLAIN,22);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(block_font);

    }
}
