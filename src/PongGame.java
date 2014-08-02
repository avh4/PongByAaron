import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongGame extends JComponent implements ActionListener,
        MouseMotionListener {

    private int ballX = 400;
    private int ballY = 150;
    private int paddleX = 0;
    private int ballYSpeed = 7;
    private int ballXSpeed = 5;
    private int hits = 0;

    public static void main(String[] args) {
        JFrame window = new JFrame("Pong Game by Aaron");
        PongGame game = new PongGame();
        window.add(game);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        Timer t = new Timer(100, game);
        t.start();

        window.addMouseMotionListener(game);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // draw the sky
        g.setColor(new Color(178, 223, 224));
        g.fillRect(0, 0, 800, 600);

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
        ballX = ballX + ballXSpeed;
        ballY = ballY + ballYSpeed;
        if (ballX + 30 >= paddleX
                && ballX <= paddleX + 150
                && ballY + 30 >= 510
                && ballY <= 510 + 15) {
            ballYSpeed = -7;
            hits = hits + 1;
        }
        if (ballX >= 800 - 30) {
            ballXSpeed = -5;
        }
        if (ballX <= 0) {
            ballXSpeed = 5;
        }
        if (ballY <= 0) {
            ballYSpeed = 7;
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
