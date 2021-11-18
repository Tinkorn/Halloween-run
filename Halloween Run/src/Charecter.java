import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Charecter {
    public int x,y,wide,life,high;

    //IMAGE
    URL run1URL = this.getClass().getResource("image/RUN/run1.png");
    Image run1 = new ImageIcon(run1URL).getImage();

    public Charecter(int x,int y,int life,int wide,int high) {
        this.x=x;
        this.y=y;
        this.life=life;
        this.wide=wide;
        this.high=high;
    }
    //JUMP
    public void jump(){
            y =345;
    }
    //SLIDE
    public  void slide(){
        wide=150;
        y=595;
        high=100;
    }
    //RUN
    public void run(Graphics2D g2d){
            g2d.drawImage(run1,x-150,y-100,null);
    }

}
