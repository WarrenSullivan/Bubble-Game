package javagame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel{
    private ArrayList<Bubble> Bubbles;
    private InputHandler Input;
    
    GamePanel(){
        Bubbles = new ArrayList();
        Input = new InputHandler(this);
        Dimension d = new Dimension(GameFrame.Screensize.width, 
                GameFrame.Screensize.height);
        
        this.setPreferredSize(d);
        this.addKeyListener(Input);
        this.addMouseListener(Input);
        this.setFocusable(true);
        
        Initialize();
    }
    
    public ArrayList<Bubble> GetBubbles(){ return Bubbles; }
    public void SetBubbles(ArrayList<Bubble> Bubbles){ this.Bubbles = Bubbles; }
    
    public void Initialize(){
        Random r = new Random();
        
        synchronized(Bubbles){
            Bubbles.clear();

            for(int i= 0; i < 100; i++){
                Bubbles.add(new Bubble(r.nextInt(GameFrame.Screensize.width     //adds i number of bubbles
                    -Bubble.startsize)+(Bubble.startsize/2),                    //if adding difficulty, change this
                    r.nextInt(GameFrame.Screensize.height-Bubble.startsize)+
                    (Bubble.startsize/2)));
            }
        }
    }
    
    @Override
    public void paint(Graphics g){
        g.clearRect(0, 0, GameFrame.Screensize.width, 
                GameFrame.Screensize.height);
        
        synchronized(Bubbles){
            for(Bubble b : Bubbles){
                g.setColor(b.GetColor());                                       //gets what color the bubble is
                g.fillOval(b.GetX()-(b.GetSize()/2), b.GetY()-(b.GetSize()/2),  //draws the bubble, centered on x/y cord
                        b.GetSize(), b.GetSize());                                  
            }
        }
    }
}
