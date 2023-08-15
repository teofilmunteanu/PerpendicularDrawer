import java.awt.*;

public class Bilet33 extends Frame {
    Toolkit tool;
    Image im;
    int ww, hh;
    Graphics graphics;

    Button btn;

    int nrPoints = 0;
    int[] pointsX = new int[2];
    int[] pointsY = new int[2];

    public static void main(String[] args) {
        new Bilet33();
    }

    Bilet33() {
        tool = getToolkit();
        Dimension res = tool.getScreenSize();
        ww = res.width;
        hh = res.height;
        setResizable(false);
        setTitle("Bilet 33");
        setLayout(null);
        setBackground(Color.lightGray);

        resize(ww, hh);
        move(0, 0);
        setVisible(true);

        im = createImage(ww, hh);
        graphics = im.getGraphics();
    }

    void Desenare() {
        graphics.clearRect(0, 0, ww, hh);

        graphics.setColor(Color.red);
        graphics.fillOval(pointsX[0] - 3, pointsY[0] - 3, 6, 6);
        graphics.fillOval(pointsX[1] - 3, pointsY[1] - 3, 6, 6);
        graphics.drawLine(pointsX[0], pointsY[0], pointsX[1], pointsY[1]);
        double distance = (Math.sqrt((pointsX[1] - pointsX[0]) * (pointsX[1] - pointsX[0])
                + (pointsY[1] - pointsY[0]) * (pointsY[1] - pointsY[0])));

        graphics.setColor(Color.blue);
        int midX = (pointsX[0] + pointsX[1]) / 2;
        int midY = (pointsY[0] + pointsY[1]) / 2;

        double panta1 = (double) (pointsY[1] - pointsY[0]) / (pointsX[1] - pointsX[0]);

        double panta2 = -1.0 / panta1;
        for (int i = midX - (int) (distance / 2); i < midX + (distance / 2); i += 1) {
            for (int j = midY - (int) (distance / 2); j < midY + (distance / 2); j += 1) {
                if ((j - midY) - (panta2 * (i - midX)) >= -5 && (j - midY) - (panta2 * (i - midX)) <= 5) {
                    graphics.fillRect((int) i, (int) j, 1, 1);
                }
            }
        }

        repaint();
    }

    public void deseneazaPunct(int x, int y) {
        graphics.setColor(Color.red);
        graphics.fillOval(x, y, 5, 5);
        repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(im, 0, 0, this);
    }

    public void update(Graphics g) {
        paint(g);
    }

    public boolean handleEvent(Event e) {
        if (e.id == Event.WINDOW_DESTROY) {
            System.exit(0);
        }

        return super.handleEvent(e);
    }

    public boolean mouseDown(Event e, int x, int y) {
        if (nrPoints < 2) {
            pointsX[nrPoints] = x;
            pointsY[nrPoints] = y;

            deseneazaPunct(x, y);
            nrPoints++;

            if (nrPoints == 2) {
                Desenare();
            }
        }

        return true;
    }
}
