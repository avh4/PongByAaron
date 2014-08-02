import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PongGame extends JComponent implements ActionListener,
        MouseMotionListener {

    private int ballX = 400;
    private int ballY = 150;
    private int paddleX = 0;
    private int levelSpeedX = 3;
    private int levelSpeedY = 2;
    private int ballXSpeed = levelSpeedX;
    private int ballYSpeed = levelSpeedY;
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
        // draw the sky
        g.setColor(sky);
        g.fillRect(0, 0, 800, 600);

        // draw the level number
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

        // draw the paddle
        g.setColor(new Color(110, 61, 23));
        g.fillRect(paddleX, 510, 150, 15);

        // draw the ball
        g.setColor(new Color(155, 93, 169));
        g.fillOval(ballX, ballY, 30, 30);

        // draw the hit count
        g.setColor(new Color(151, 33, 34));
        g.setFont(new Font(Font.SERIF, Font.PLAIN, 50));
        g.drawString("Hits: " + hits, 321, 560);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timeInLevel = timeInLevel + 1;
        ballX = ballX + ballXSpeed;
        ballY = ballY + ballYSpeed;
        if (ballX + 30 >= paddleX
                && ballX <= paddleX + 150
                && ballY + 30 >= 510
                && ballY <= 510 + 15) {
            ballYSpeed = -levelSpeedY;
            hits = hits + 1;
            if (hits >= 2) {
                level = level + 1;
                hits = 0;
                levelSpeedX = levelSpeedX + 2;
                levelSpeedY = levelSpeedY + 2;
                sky = sky.darker();
                timeInLevel = 0;
            }
        }
        if (ballX >= 800 - 30) {
            ballXSpeed = -levelSpeedX;
        }
        if (ballX <= 0) {
            ballXSpeed = levelSpeedX;
        }
        if (ballY <= 0) {
            ballYSpeed = levelSpeedY;
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paddleX = e.getX() - 75;
        repaint();
    }
}
