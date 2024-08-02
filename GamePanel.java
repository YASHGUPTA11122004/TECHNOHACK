import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private final int UNIT_SIZE = 10;
    private final int ALL_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private final int DELAY = 100;

    private final ArrayList<Point> snake = new ArrayList<>();
    private Point food;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    private void startGame() {
        snake.clear();
        snake.add(new Point(50, 50));
        spawnFood();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void spawnFood() {
        int x = new Random().nextInt((int) (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        int y = new Random().nextInt((int) (HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        food = new Point(x, y);
    }

    private void move() {
        Point head = new Point(snake.get(0));
        switch (direction) {
            case 'U' -> head.y -= UNIT_SIZE;
            case 'D' -> head.y += UNIT_SIZE;
            case 'L' -> head.x -= UNIT_SIZE;
            case 'R' -> head.x += UNIT_SIZE;
        }
        snake.add(0, head);
        if (head.equals(food)) {
            spawnFood();
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    private void checkCollisions() {
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            running = false;
        }
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                running = false;
                break;
            }
        }
        if (!running) {
            timer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            g.setColor(Color.RED);
            g.fillOval(food.x, food.y, UNIT_SIZE, UNIT_SIZE);

            for (Point point : snake) {
                g.setColor(Color.GREEN);
                g.fillRect(point.x, point.y, UNIT_SIZE, UNIT_SIZE);
            }
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        g.drawString("Game Over", WIDTH / 6, HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollisions();
        }
        repaint();
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
