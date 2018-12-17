
package flappybird;
/**
 *
 * @author Ambesh Tiwari
 */
import java.awt.Color;

public class Pipes {
    int top, bottom,gap;
    int x,pipewidth; float speed;
    Color color;
    Pipes()
    {   this.color = new Color(20,200,20);
x=FlappyBird.FW-50;
        top=(int)(Math.random()*600);
        gap=50+(int)(Math.random()*200);
        bottom=top+gap;
        pipewidth=50;
        speed=5;
    }
    
    void update()
    {
        x-=speed;
    }
}
