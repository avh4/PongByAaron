import java.awt.*;

public class Ball {
    public int x = 400;
    public int y = 150;
    public int xSpeed;
    public int ySpeed;

    public Ball(int levelSpeedX, int levelSpeedY) {
        xSpeed = levelSpeedX;
        ySpeed = levelSpeedY;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(155, 93, 169));
        g.fillOval(x, y, 30, 30);
    }
}
