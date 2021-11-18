import javax.swing.*;
public class run extends JFrame {
    public static void main(String[] args) {
        GAME frame = new GAME();
        frame.setSize(1500,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
