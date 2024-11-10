import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    final static int FORM_WIDTH = 700;
    final static int FORM_HEIGHT = 700;


    Main() {
        setSize(FORM_WIDTH, FORM_HEIGHT);
        setPreferredSize(new Dimension(FORM_WIDTH, FORM_HEIGHT));
        setVisible(true);
    }

    void Bresenham(Graphics2D graphics, int x1, int y1, int x2, int y2) {
        int x = x1;
        int y = y1;
        int Dx = x2 - x1;
        int Dy = y2 - y1;
        int d = 2 * Dy - Dx;
        graphics.setPaint(Color.black);
        for (int i = 1; i <= Dx; i++)
        {
            graphics.fillRect(x, y, 1, 1);
            if (d >= 0) {
                y++;
                d += -2 * Dx + 2 * Dy;
            } else
                d += 2 * Dy;
            x++;
        }
    }

    public void paint(Graphics g) {
        Bresenham((Graphics2D) g, 150, 300, 160, 20);

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("AnimationExample");

        Window window = new Main();

        frame.add(window);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}