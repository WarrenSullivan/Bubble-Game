package javagame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
    
    public static Dimension Screensize;
    public GamePanel g;
    
    GameFrame(){
        Screensize = Toolkit.getDefaultToolkit().getScreenSize();               //this line must come before the next one
        g = new GamePanel();                                                    //GamePanel relies on Screensize to be non-null
        
        this.setSize(Screensize.width, Screensize.height);
        this.setUndecorated(true);                                              //windowless fullscreen
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        
        g.setFocusable(true);                                                   //needed for keyboard input?
        this.add(g);
        this.setVisible(true);
        Run();
    }
    
    public void Run(){
        ArrayList<Bubble> Bubbles = g.GetBubbles();
        
        while(true){
            synchronized(Bubbles){
                for(Iterator<Bubble> i = Bubbles.iterator(); i.hasNext();){
                    Bubble b = i.next();
                    
                    if(!b.GetPopped()){
                        b.Move();

                        if(b.GetX()-b.GetSize()/2 <= 0){
                            b.SetXS(0-b.GetXS());
                        }
                        else if(b.GetY()-b.GetSize()/2 <= 0){
                            b.SetYS(0-b.GetYS());
                        }
                        else if(b.GetX()+b.GetSize()/2 >= Screensize.width){
                            b.SetXS(0-b.GetXS());
                        }
                        else if(b.GetY()+b.GetSize()/2 >= Screensize.height){
                            b.SetYS(0-b.GetYS());
                        }
                        
                        for(Iterator<Bubble> j = Bubbles.iterator(); 
                            j.hasNext();)
                        {
                            Bubble a = j.next();                                //something in this loop can cause a partial crash
                            if(b.Overlaps(a) && a.GetPopped()){
                                b.SetPopped(true);
                            }
                        }
                        
                    }
                    else{
                        b.Pop();
                    }
                    
                    
                    
                    if(b.IsDead()){
                        i.remove();
                    }
                }
                
                g.repaint();
            }
            
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
    }
}
