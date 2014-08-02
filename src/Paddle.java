import java.awt.*;

public class Paddle {
    public int x = 0;

    public void draw(Graphics g) {
        g.setColor(new Color(110, 61, 23));
        g.fillRect(x, 510, 150, 15);
    }
}
