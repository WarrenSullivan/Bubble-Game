package javagame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputHandler implements MouseListener, KeyListener{
    private GamePanel g;
    
    InputHandler(GamePanel g){
        this.g = g;
    }
    
    @Override
    public void mouseReleased(MouseEvent e){}
    
    @Override
    public void mouseEntered(MouseEvent e){}
    
    @Override
    public void mouseExited(MouseEvent e){}
    
    @Override
    public void mouseClicked(MouseEvent e){}
    
    @Override
    public void mousePressed(MouseEvent e){
        Bubble b = new Bubble(e.getX(), e.getY());
        b.SetPopped(true);
        b.SetColor(Color.WHITE);
        synchronized(g.GetBubbles()){
            g.GetBubbles().add(b);
        }
    }
    
    
    @Override
    public void keyReleased(KeyEvent e){}
    
    @Override
    public void keyPressed(KeyEvent e){}
    
    @Override
    public void keyTyped(KeyEvent e){
        if(e.getKeyChar()=='r' || e.getKeyChar()=='R'){
            g.Initialize();
            g.repaint();
        }
        
        if(e.getKeyChar()=='x' || e.getKeyChar()=='X'){
            System.exit(0);                                                     //WIP, figure out how to exit gracefully
        }
    }
}
