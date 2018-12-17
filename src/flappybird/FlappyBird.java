package flappybird;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
/**
 *
 * @author Ambesh Tiwari
 */

import java.awt.*;

public class FlappyBird extends JFrame implements Runnable{
    
    static final int FH=800,FW=800,FPS=60;
    float velocity,gravity,lift;
    LinkedList <Pipes> pipes;
    int x,y;
    Image bfi; Graphics bfg;
    
    FlappyBird()
    {
        this.pipes = new LinkedList<Pipes>();
        this.setSize(FW,FH);
        this.setLocation(400,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new Handler());
        x=200; y=FH/2;
        velocity=0; gravity=0.4f; lift=8;
    }
    Image bird= new ImageIcon("bird.gif").getImage(); 
    static FlappyBird f=new FlappyBird();
    
    public static void main(String[] args) {
        Thread t= new Thread(f);
        t.start();
       f.setVisible(true);
 
    }
    public void clear(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,FW,FH);
        //g.setColor(Color.BLACK);
    }
    
    public void sleep(long t)
    {
        try
        {Thread.sleep(t);}
        catch(Exception e){}
    }
    
    public void update()
    {
        if(y<FH-50){
                velocity+=gravity;
                y+=velocity;
                if(y<50){y=50; velocity=0;}
            }
        else y=FH-51;
    }
    
    public boolean collison(Pipes p)
    {
        return (x+30>p.x && x<p.x+50 && ( y+10<p.top || y+20>p.bottom));
    }
    
    @Override
    public void run()
    {
        int counter=0;
        while(true)
        {
            repaint();
            update();
            counter++;
            if(counter==50){
                pipes.addLast(new Pipes());
                counter=0;
            }
            for(int i=0;i<pipes.size();i++){
                if(collison(pipes.get(i)))pipes.get(i).color=Color.red;
                if(pipes.get(i).x>0)pipes.get(i).update();
                else pipes.removeFirst();
            }
            sleep(1000/FPS);
        }
    }
    
    @Override
    public void paint(Graphics g){
        bfi= createImage(getWidth(),getHeight());
        bfg= bfi.getGraphics();
        paintComponent(bfg);
        g.drawImage(bfi, 0, 0, this);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paint(g);
        //clear(g);
        g.drawImage(bird,x,y,60,40,f);
        //g.setColor(Color.blue);
        //g.drawOval(x, y, 50, 50);
        for(int i=0;i<pipes.size();i++)
        {
            g.setColor(pipes.get(i).color);
            g.fillRect(pipes.get(i).x, 0, pipes.get(i).pipewidth, pipes.get(i).top);
            g.fillRect(pipes.get(i).x, pipes.get(i).bottom, pipes.get(i).pipewidth, FH-pipes.get(i).bottom);
            
        }
    }
    
   class Handler implements KeyListener
    {
    @Override
    public void keyPressed(KeyEvent ke)
    {
        if(ke.getKeyCode()==KeyEvent.VK_SPACE){
           if(y>50) velocity-=lift;
    }
       
    }
    @Override
    public void keyReleased(KeyEvent ke)
    {}
    @Override
    public void keyTyped(KeyEvent ke)
    {}
    }
    
}
