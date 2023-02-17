/**
 *
 */

import java.awt.*;

public class MagnumOpus {

    private static final Color[] colors = new Color[]{new Color(0x66129d), new Color(0x980FBE), new Color(0x054ca0), new Color(0x1E1279)};

    public static void main(String[] args) {
        MagnumOpus run = new MagnumOpus();
        run.run();
    }

    /**
     *
     */
    public void run() {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.enableDoubleBuffering();

        boolean increaseAngle = true;
        double deltaAngle = -1.0;
        while (true) {
            drawSpiral(deltaAngle);

            if (increaseAngle)
                deltaAngle += .01;
            else
                deltaAngle -= .01;

            if (deltaAngle >= 1.0)
                increaseAngle = false;
            if (deltaAngle <= -1.0)
                increaseAngle = true;
        }
    }

    /**
     *
     * @param deltaAngle
     */
    private void drawSpiral(double deltaAngle) {
        StdDraw.clear(Color.black);
        int colorCounter = 0;
        for (int deg = 0; deg < 360; deg += 15) {
            colorCounter++;
            if (colorCounter == colors.length)
                colorCounter = 0;
            StdDraw.setPenColor(colors[colorCounter]);
            drawLeg(.5, .5, deg, (int)Math.min(720, (360 * 1.0/Math.abs(deltaAngle))), 0, deltaAngle);
        }
        StdDraw.show();
    }

    /**
     *
     * @param x
     * @param y
     * @param angleDeg
     * @param depth
     * @param radius
     * @param deltaAngle
     */
    private void drawLeg(double x, double y, double angleDeg, int depth, double radius, double deltaAngle) {
        if (depth <= 0)
            return;

        radius += 1.0 / 360.0 / 2.0;
        double angRad = Math.toRadians(angleDeg + deltaAngle);
        double newX = .5 + Math.cos(angRad) * radius;
        double newY = .5 + Math.sin(angRad) * radius;

        StdDraw.line(x, y, newX, newY);

        drawLeg(newX, newY, angleDeg + deltaAngle, depth - 1, radius, deltaAngle);
    }

}
