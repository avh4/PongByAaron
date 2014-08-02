import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PongGame extends JComponent implements ActionListener,
        MouseMotionListener {

    private int levelSpeedX = 3;
    private int levelSpeedY = 2;
    private Paddle paddle = new Paddle();
    private Ball ball = new Ball(levelSpeedX, levelSpeedY);
    private int hits = 0;
    private int level = 1;
    private Color sky = new Color(178, 223, 224);
    private int timeInLevel = 0;
    private String[] levelNames = {
            "Trial by fire",
            "Into the jungle",
            "Hope returns",
            "Skyward!",
            "Hall of the Mountain King",
            "Beyond the veil",
            "..."
    };

    public static void main(String[] args) {
        JFrame window = new JFrame("Pong Game by Aaron");
        PongGame game = new PongGame();
        window.add(game);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Timer t = new Timer(20, game);
        t.start();

        window.addMouseMotionListener(game);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawSky(g);
        drawLevelInfo(g);
        paddle.draw(g);
        ball.draw(g);
        drawScoreboard(g);
    }

    private void drawScoreboard(Graphics g) {
        g.setColor(new Color(151, 33, 34));
        g.setFont(new Font(Font.SERIF, Font.PLAIN, 50));
        g.drawString("Hits: " + hits, 321, 560);
    }

    private void drawLevelInfo(Graphics g) {
        int alpha = 255 - timeInLevel;
        g.setColor(new Color(151, 33, 34, Math.max(0, alpha)));
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 80));
        String levelMessage = "LEVEL " + level;
        int textWidth = g.getFontMetrics().stringWidth(levelMessage);
        g.drawString(levelMessage, (800 - textWidth) / 2, 232);
        // draw the level name
        if (level <= 7) {
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 45));
            String levelName = levelNames[level - 1];
            int levelNameWidth = g.getFontMetrics().stringWidth(levelName);
            g.drawString(levelName, (800 - levelNameWidth) / 2, 300);
        }
    }

    private void drawSky(Graphics g) {
        g.setColor(sky);
        g.fillRect(0, 0, 800, 600);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timeInLevel = timeInLevel + 1;
        ball.x = ball.x + ball.xSpeed;
        ball.y = ball.y + ball.ySpeed;
        if (ball.x + 30 >= paddle.x
                && ball.x <= paddle.x + paddle.width
                && ball.y + 30 >= 510
                && ball.y <= 510 + 15) {
            ball.ySpeed = -levelSpeedY;
            hits = hits + 1;
            if (hits >= 2) {
                level = level + 1;
                hits = 0;
                levelSpeedX = levelSpeedX + 2;
                levelSpeedY = levelSpeedY + 2;
                sky = sky.darker();
                timeInLevel = 0;
                paddle.width = paddle.width - 10;
            }
        }
        if (ball.x >= 800 - 30) {
            ball.xSpeed = -levelSpeedX;
        }
        if (ball.x <= 0) {
            ball.xSpeed = levelSpeedX;
        }
        if (ball.y <= 0) {
            ball.ySpeed = levelSpeedY;
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paddle.x = e.getX() - 75;
        repaint();
    }
}
