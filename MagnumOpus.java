/** MagnumOpus.java
 * A class that draws a recursive pattern that I created.
 * The recursive pattern that I drew was several spirals
 * that all converge at one point.
 *
 * @author Noah Ruderman
 * @since 2/10/2023
 */

import java.awt.*;

public class MagnumOpus {

    private static final Color[] colors = new Color[]{new Color(0x66129d), new Color(0x980FBE), new Color(0x054ca0), new Color(0x1E1279)};

    public static void main(String[] args) {
        MagnumOpus run = new MagnumOpus();
        run.run();
    }

    /** The method that holds the infinite animation loop */
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
     * A method to draw the spiral
     * @param deltaAngle the difference in angle to draw the spiral, the higher the angle, the more curvy it is.
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
     * Recursively draws a leg of a spiral.
     * @param x the current x position
     * @param y the current y position
     * @param angleDeg the angle at which it is being drawn at relative to the center
     * @param depth recursion depth parameter
     * @param radius the distance from the center of the spiral
     * @param deltaAngle the change in angle to draw the leg
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
