package javagame;

import java.awt.Color;
import java.util.Random;

public class Bubble{
    final static int startsize = 25;
    final private Random r;
    
    private int x;
    private int y;                                                              //x, y, and size are center/from center, not edge
    private int size;
    private int xs;
    private int ys;                                                             //speed in x/y direction
    private int count;
    
    private Color c;
    private boolean popped;
    private boolean dead;
    
    Bubble(int x, int y){
        this.x = x;
        this.y = y;
        size = startsize;
        popped = false;
        count = 0;
        dead = false;
        
        r = new Random(x+y);
        c = new Color(r.nextInt(150)+50, r.nextInt(150)+50, r.nextInt(150)+50); //random color from (50,50,50) to (200,200,200)
        
        do{
            xs = r.nextInt(9)-4;
        }while(xs == 0);
        
        do{
            ys = r.nextInt(9)-4;
        }while(ys == 0);
    }
    
    public boolean Overlaps(Bubble b){
        return Math.sqrt(Math.pow((x-b.GetX()), 2)+Math.pow((y-b.GetY()), 2)) <
                size/2 + b.GetSize()/2;
    }
    
    public void Pop(){
        if(count < 1){
            c = c.darker();
        }
        
        if(size < 100){
           size++; 
        }
        
        if(count < 250){
            count++;
        }
        else{
            if(size > 10){
                size = size - 10;
            }
            else{
                dead = true;
            }
        }
    }
    
    public void Move(){
        x = x + xs;
        y = y + ys;
    }
    
    public int GetX(){ return x; }
    public int GetY(){ return y; }
    public int GetSize(){ return size; }
    public int GetXS(){ return xs; }
    public int GetYS(){ return ys; }
    public Color GetColor(){ return c; }
    public boolean GetPopped(){ return popped; }
    public boolean IsDead(){ return dead; }
    
    public void SetX(int x){ this.x = x; }
    public void SetY(int y){ this.y = y; }
    public void SetSize(int size){ this.size = size; }
    public void SetXS(int xs){ this.xs = xs; }
    public void SetYS(int ys){ this.ys = ys; }
    public void SetColor(Color c){ this.c = c; }
    public void SetPopped(boolean popped){ this.popped = popped; }
}
