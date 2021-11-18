import javax.swing.*;
import java.awt.*;
import java.net.URL;
public class Candy{
    private int x, y,wide,hight;
    public int HIT=0,speed;

    //IMAGE
    URL coin1URL = this.getClass().getResource("image/PROP/coin1.png");
    Image coin1 = new ImageIcon(coin1URL).getImage();
    URL coin2URL = this.getClass().getResource("image/PROP/coin2.png");
    Image coin2 = new ImageIcon(coin2URL).getImage();
    URL coin3URL = this.getClass().getResource("image/PROP/coin3.png");
    Image coin3= new ImageIcon(coin3URL).getImage();

    public Candy(int x, int y, int wide,int hight,int speed) {
        this.x = x;
        this.y = y;
        this.wide=wide;
        this.hight=hight;
        this.speed=speed;
    }

    //SPAWN CANDY
    public void spawn(Graphics2D game, int score, Charecter ghost) {
        x -= speed;
        if (x < -300) {
            x = 1700;
            y=545;
        }
        //CHANGE CANDY
        if(score<300){
            game.drawImage(coin1,x,y,wide,hight,null);
        }else if(score<900){
            game.drawImage(coin2,x,y,wide,hight,null);
        }else if(score>=900){
            game.drawImage(coin3,x,y,wide,hight,null);
        }

        //HITBOX
        if((ghost.x<=x&&ghost.x+ghost.wide>=x)||(x+wide>=ghost.x&&x+wide<=ghost.x+ghost.wide)) {
            if ((ghost.y + ghost.high >= y&&ghost.y + ghost.high<=y+ hight)||(ghost.y>=y&&ghost.y<y+hight)) {
                HIT=1;
                y=-100;
            }
        }
    }

}
