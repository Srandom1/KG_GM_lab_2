import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainForm extends JFrame {
    final static int Form_Wight = 600;
    final static int Form_Hight = 500;
    Integer x1, x2, y1, y2; // Используем Integer для того, чтобы можно было установить null

    public MainForm() {
        setSize(Form_Wight, Form_Hight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        x1 = null; y1 = null; // Инициализация значениями null
        x2 = null; y2 = null;

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
        int d, delta1, delta2;
        int i, j;
        int dI, dJ;
        int temp;

        dI = Math.abs(I2 - I1);
        dJ = Math.abs(J1 - J2);

        if (dI >= dJ) {

            if (I1 > I2) {
                temp = I2;
                I2 = I1;
                I1 = temp;
                temp = J2;
                J2 = J1;
                J1 = temp;
            }

            d = 2 * dJ - dI;
            delta1 = 2 * dJ;
            delta2 = 2 * (dJ - dI);
            j = J1;
            for (i = I1; i < I2; ++i) {
                graphics.fillRect(i, j, 3, 3);
                if (d >= 0) {
                    j--;
                    d += delta2;
                } else {
                    d += delta1;
                }
            }
        }
        else {

            if (J1 > J2) {
                temp = I2;
                I2 = I1;
                I1 = temp;
                temp = J2;
                J2 = J1;
                J1 = temp;
            }

            d = 2 * dI - dJ;
            delta1 = 2 * dI;
            delta2 = 2 * (dI - dJ);
            i = I1;
            for (j = J1; j < J2; ++j) {
                graphics.fillRect(i, j, 3, 3);
                if (d >= 0) {
                    i--;
                    d += delta2;
                } else {
                    d += delta1;
                }
            }
        }
    }

    public static void main(String[] arg) {
        new MainForm();
    }

}
