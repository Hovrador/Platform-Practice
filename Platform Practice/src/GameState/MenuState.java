package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import Main.GamePanel;
import TileMap.Background;

public class MenuState extends GameState{

    private Background bg;
    
    private int currentChoice = 0;
    private String[] options = {"Start", "Help", "Quit"};
    
    private Color titleColor;
    private Font titleFont;
    private String title = "Hamster Run";
    private Font font;
    
    public MenuState(GameStateManager gsm)
    {
        this.gsm = gsm;
        
        try
        {
            bg = new Background("/Backgrounds/Title Background.png", 1);
            bg.setVector(-0.1, 0);
            
            titleColor = new Color(124,124,124);
            titleFont = new Font("Century Gothic", Font.PLAIN, 34);
            font = new Font("Arial", Font.PLAIN, 16);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void init() {
    }

    
    public void update() 
    {
        bg.update();        
    }

    
    public void draw(Graphics2D g) 
    {
        //Draw Background
        bg.draw(g);     
        //Draw Title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString(title, ((int)(GamePanel.WIDTH/2))-((int)(title.length()*17/2)), 70);
        //Draw Options
        g.setFont(font);
        for(int i =0; i < options.length; i++)
        {
            if(i==currentChoice)
            {
                g.setColor(Color.WHITE);
            }
            else
            {
                g.setColor(Color.BLACK);
            }
            g.drawString(options[i],((int)(GamePanel.WIDTH/2))-((int)(options[0].length()/2)) , 140 + (20*i));
        }
    }

    
    public void keyPressed(int k) 
    {
        if(k == KeyEvent.VK_ENTER)
        {
            select();
        }
        if(k == KeyEvent.VK_UP){
            currentChoice --;
            if(currentChoice < 0)
            {
                currentChoice = 2;
            }
        }
        if(k == KeyEvent.VK_DOWN)
        {
            currentChoice ++;
            if(currentChoice >2)
            {
                currentChoice = 0;
            }
        }
        
    }
    
    public void select()
    {
        if(currentChoice == 0)
        {
            
        }
        if(currentChoice == 1)
        {
            
        }
        if(currentChoice == 2)
        {
            System.exit(0);
        }
    }

    
    public void keyReleased(int k) 
    {
        // TODO Auto-generated method stub
        
    }

}
