package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;

import GameState.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener
{

    //Dimensions
    public static final int WIDTH = 360;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;
    
    //Thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;
    
    //Image
    private BufferedImage image;
    private Graphics2D g;
    
    //GameStateManager
    private GameStateManager gsm;
    
    public GamePanel()
    {
        super();
        setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        setFocusable(true);
        requestFocus();
    }
    
    public void addNotify()
    {
        super.addNotify();
        if(thread == null)
        {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
            
        }
    }
    
    public void init()
    {
        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        running = true;
        gsm = new GameStateManager();
    }
    
    public void run() 
    {
        long start;
        long elapsed;
        long wait;
        
        init();
        while (running)
        {
            start = System.nanoTime();
            
            update();
            draw();
            drawToScreen();
            
            elapsed = System.nanoTime()-start;
            wait = targetTime - elapsed/1000000;
            try
            {
                if(wait > 0)
                {
                    Thread.sleep(wait);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void update()
    {
        gsm.update();
    }
    
    public void draw()
    {
        gsm.draw(g);
    }
    
    public void drawToScreen()
    {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT * SCALE, null);
        g2.dispose();
    }
    
    public void keyTyped(KeyEvent key) 
    {
        // TODO Auto-generated method stub
        
    }
    
    public void keyPressed(KeyEvent key) 
    {
        gsm.keyPressed(key.getKeyCode());
        
    }

    public void keyReleased(KeyEvent key) 
    {
        gsm.keyReleased(key.getKeyCode());
        
    }

}
