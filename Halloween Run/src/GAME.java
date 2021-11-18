import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class GAME extends JFrame {

    public GAME(){
        add(new GameArea());
    }
    public  static class GameArea extends JPanel{

        private int n=15;
        private Candy candy[]= new Candy[n];
        private int floorX1=0;
        private int floorX2=1500;
        private int check=1;
        private int speed=10;
        private int enter=1;
        public int x=1700;
        public int score=0;

        //NEW OBJECT
        Charecter ghost=new Charecter(200,545,200,100,150);
        Trap trap = new Trap(1400,545,150,100,speed,2);

        //IMAGE
        URL bg1URL = this.getClass().getResource("image/BACKGOURD/bg1.jpg");
        URL bg2URL = this.getClass().getResource("image/BACKGOURD/bg2.jpg");
        URL bg3URL = this.getClass().getResource("image/BACKGOURD/bg3.jpg");
        URL bg4URL = this.getClass().getResource("image/BACKGOURD/bg4.jpg");
        URL bg5URL = this.getClass().getResource("image/BACKGOURD/bg5.jpg");
        URL floorURL = this.getClass().getResource("image/PROP/floor-1.png");
        Image bg1 = new ImageIcon(bg1URL).getImage();
        Image bg2 = new ImageIcon(bg2URL).getImage();
        Image bg3 = new ImageIcon(bg3URL).getImage();
        Image bg4 = new ImageIcon(bg4URL).getImage();
        Image bg5 = new ImageIcon(bg5URL).getImage();
        Image floor = new ImageIcon(floorURL).getImage();

        //TIMER IN GAME
        Timer time1 = new Timer(10,new Listener());

        public GameArea(){

            // CREATE CANDY OBJECT
            for(int i=0;i<15;i++){
                candy[i]=new Candy(x,545,100,100,speed);
                x+=100;
            }
            time1.start();
            setFocusable(true);

            //KEYBOARD CONTROL
            addKeyListener(new KeyListener() {
                @Override public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode()==74){
                            ghost.jump();
                        }
                        if(e.getKeyCode()==70){
                            ghost.slide();
                        }
                        if(e.getKeyCode()==27){
                            time1.stop();

                        }if(enter==1){

                        if(e.getKeyCode()==KeyEvent.VK_ENTER){
                          enter=0;
                        }
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {

                  time1.start();        //BACK TO DEFAULT VALUE
                  ghost.y=545;
                  ghost.wide=100;
                  ghost.high=150;

                }
            });
        }

        //PAINT
        @Override
        public void paint(Graphics g) {

            //HOME
            if(enter==1){
                g.drawImage(bg4,0,0,getWidth(),getHeight(),this);
            }

            //GAME GRAPHIC
            if(check==1&&enter==0){
                super.paint(g);
                Graphics2D g2d=(Graphics2D)g;
                //BACKGROUND
               if(score<300){
                   g.drawImage(bg1,0,0,getWidth(),getHeight(),this);
               }else if(score<900){
                   g.drawImage(bg2,0,0,getWidth(),getHeight(),this);
                   this.speed=15;
                   for (int i = 0; i < n; i++) {
                       candy[i].speed=15;
                   }
                   trap.speed=15;
               }else if(score>=900){
                   g.drawImage(bg3,0,-150,getWidth(),getHeight(),this);
                   this.speed=20;
                   for (int i = 0; i < n; i++) {
                       candy[i].speed=20;
                   }
                   trap.speed=20;
               }

               //SCORE
                g.setFont(new Font("OCR A Extended",Font.PLAIN,50));
                g.setColor(Color.white);
                g.drawString("Score :  "+score,1000,100);
                g.setColor(Color.RED);

                //DRAW GHOST TRAP CANDY
                ghost.run(g2d);
                //SPAWN CANDY
                for(int i=0;i<n;i++){
                    candy[i].spawn(g2d,score,ghost);
                }
                trap.spanw(g2d,ghost);
                //DEDUCT TRAP
                if(trap.deduct==1&&trap.HIT==1){
                    score-=10;
                } trap.HIT=0;

                //CHECK HIT CANDY
                for (int i = 0; i < n; i++) {
                    if(candy[i].HIT==1){
                        score+=10;
                    }candy[i].HIT=0;
                }

                //FLOOR
                g.drawImage(floor,floorX1,700,1520,200,this);
                g.drawImage(floor,floorX2-1,700,1520,200,this);
                floorX1-=speed;
                floorX2-=speed;
                if(floorX1<-1500 ){
                    floorX1=1500;
                }
                if(floorX2<-1500 ){
                    floorX2=1500;
                }

                //HP
                g.setColor(Color.GRAY);
                g.fillRect(15,15,200,25);
                g.setColor(Color.GREEN);
                g.fillRect(15,15,ghost.life,25);
                g.setColor(Color.WHITE);
                g.drawRect(15,15,200,25);
            }

            //GAMEOVER
            if(ghost.life<=0){
                check=0;
                super.paintComponent(g);
                g.setColor(Color.black);
                g.drawImage(bg5,0,0,getWidth(),getHeight(),this);
                g.setFont(new Font("OCR A Extended",Font.PLAIN,70));
                g.drawString(" YOUR SCORE: \n"+score,400,400);
            }
        }

        class Listener implements ActionListener{
           @Override
           public void actionPerformed(ActionEvent e) {
               repaint();
               }
           }
       }

}





