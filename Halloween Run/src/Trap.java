import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Trap {
    private int x, y, width, height,Trap_Random,damage;
    public int deduct=0,HIT=0,speed;

    //IMAGE
    URL trap1URL = this.getClass().getResource("image/PROP/trap1.png");
    Image trap1 = new ImageIcon(trap1URL).getImage();
    URL trap2URL = this.getClass().getResource("image/PROP/trap2.png");
    Image trap2 = new ImageIcon(trap2URL).getImage();
    URL trap3URL = this.getClass().getResource("image/PROP/trap3.png");
    Image trap3 = new ImageIcon(trap3URL).getImage();
    URL trap4URL = this.getClass().getResource("image/PROP/trap4.png");
    Image trap4 = new ImageIcon(trap4URL).getImage();
    URL trap5URL = this.getClass().getResource("image/PROP/trap5.png");
    Image trap5 = new ImageIcon(trap5URL).getImage();
    URL hitURL = this.getClass().getResource("image/RUN/hit.png");
    Image hit = new ImageIcon(hitURL).getImage();
    Random rand = new Random();

    public Trap(int x, int y, int height, int width, int speed,int damage) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.damage=damage;
    }

    //SPAWN TRAP
    public void spanw(Graphics2D g2d,Charecter ghost) {
        x -= speed;
        if (x < -300) {
            x = 1700;
              Trap_Random = rand.nextInt(5); //RANDOM
        }if(Trap_Random==0){
            g2d.drawImage(trap1,x-100,y-70,null); //TRAP1
            damage=2;
            y=0;
            height=560;
            deduct=0;
        }else if(Trap_Random==1){
            g2d.drawImage(trap2,x-150,y-80,null); //TRAP2
            damage=5;
            y=0;
            height=560;
            deduct=0;
        }else if(Trap_Random==2){
            g2d.drawImage(trap3,x,y-70,null); //TRAP3
            damage=2;
            y=545;
            height=150;
            deduct=0;

        }else if(Trap_Random==3){
            g2d.drawImage(trap4,x-20,y-50,null); //TRAP4
            damage=0;
            y=545;
            height=150;
            deduct=1;

        }else if(Trap_Random==4){
            g2d.drawImage(trap5,x-30,y-70,null); //TRAP5
            damage=200;
            y=545;
            height=150;
            deduct=0;
        }
        //HITBOX
        if((ghost.x<=x&&ghost.x+ghost.wide>=x)||(x+width>=ghost.x&&x+width<=ghost.x+ghost.wide)) {
            if ((ghost.y + ghost.high >= y&&ghost.y + ghost.high<=y+height)||(ghost.y>=y&&ghost.y<=y+height)) {
                g2d.drawImage(hit,ghost.x-150,ghost.y-100,null);
                ghost.life-=damage;
                HIT=1;
            }
        }


    }

}
