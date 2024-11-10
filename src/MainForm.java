import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainForm extends JFrame {
    final static int Form_Wight = 600;
    final static int Form_Hight = 500;
    Integer x1, x2, y1, y2;

    public MainForm() {
        setSize(Form_Wight, Form_Hight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        reset_coordinates();

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (x1 == null && y1 == null) {
                        x1 = e.getX();
                        y1 = e.getY();
                    } else if (x2 == null && y2 == null) {
                        x2 = e.getX();
                        y2 = e.getY();
                        drawBrez(getGraphics(), x1, y1, x2, y2);
                        reset_coordinates();
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    repaint();
                }
            }
        });
    }

    private void reset_coordinates(){
        x1 = null;
        x2 = null;
        y1 = null;
        y2 = null;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.clearRect(0, 0, this.getHeight(), this.getWidth());
    }


    void drawBrez(Graphics graphics, int I1, int J1, int I2, int J2) {
        int dI = Math.abs(I2 - I1);
        int dJ = Math.abs(J2 - J1);
        int directionX = I1 < I2 ? 1 : -1;
        int directionY = J1 < J2 ? 1 : -1;

        if (dI > dJ) {
            int d = 2 * dJ - dI;
            int j = J1;
            for (int i = I1; i != I2; i += directionX) {
                graphics.fillRect(i, j, 3, 3);
                if (d >= 0) {
                    j += directionY;
                    d -= 2 * dI;
                }
                d += 2 * dJ;
            }
        } else {
            int d = 2 * dI - dJ;
            int i = I1;
            for (int j = J1; j != J2; j += directionY) {
                graphics.fillRect(i, j, 3, 3);
                if (d >= 0) {
                    i += directionX;
                    d -= 2 * dJ;
                }
                d += 2 * dI;
            }
        }
        graphics.fillRect(I2, J2, 3, 3);
    }

    public static void main(String[] arg) {
        new MainForm();
    }

}
