package com.example.brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("serial")
public class BrickBreakerGame extends JPanel implements KeyListener, ActionListener {
    private boolean isGameRunning = false;
    private boolean isGameOver = false;
    private Timer timer;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;

    public BrickBreakerGame() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        paddle = new Paddle(350, 550, 100, 10);
        ball = new Ball(400, 500, 10, -1, -2);

        bricks = new ArrayList<>();
        int brickWidth = 70;
        int brickHeight = 20;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                bricks.add(new Brick(i * brickWidth * 2, j * brickHeight + 50, brickWidth, brickHeight));
            }
        }

        timer = new Timer(10, this);
    }

    public void startGame() {
        isGameRunning = true;
        isGameOver = false;
        resetGame();
        timer.start();
    }

    public void stopGame() {
        isGameRunning = false;
        timer.stop();
    }

    private void resetGame() {
        paddle = new Paddle(350, 550, 100, 10);
        ball = new Ball(400, 500, 10, -1, -2);

        // Reset the bricks to their original state
        resetBricks();
    }

    private void resetBricks() {
        bricks.clear();
        int brickWidth = 70;
        int brickHeight = 20;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                bricks.add(new Brick(i * brickWidth * 2, j * brickHeight + 50, brickWidth, brickHeight));
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameRunning) {
            return;
        }

        ball.move();
        checkCollisions();
        updateGameState();

        repaint();
    }

    private void checkCollisions() {
        if (ball.getX() - ball.getRadius() <= 0 || ball.getX() + ball.getRadius() >= getWidth()) {
            ball.setVelocityX(-ball.getVelocityX());
        }

        if (ball.getY() - ball.getRadius() <= 0) {
            ball.setVelocityY(-ball.getVelocityY());
        }

        if (ball.getY() + ball.getRadius() >= getHeight()) {
            ball = new Ball(400, 500, 10, -1, -2);
            isGameRunning = false;
            isGameOver = true;
        }

        if (ball.getX() >= paddle.getX() && ball.getX() <= paddle.getX() + paddle.getWidth()
                && ball.getY() + ball.getRadius() >= paddle.getY() && ball.getY() - ball.getRadius() <= paddle.getY()) {
            ball.setVelocityY(-ball.getVelocityY());
        }

        Iterator<Brick> iterator = bricks.iterator();
        while (iterator.hasNext()) {
            Brick brick = iterator.next();
            if (brick.isAlive()) {
                if (ball.getX() >= brick.getX() && ball.getX() <= brick.getX() + brick.getWidth()
                        && ball.getY() + ball.getRadius() >= brick.getY()
                        && ball.getY() - ball.getRadius() <= brick.getY() + brick.getHeight()) {
                    brick.setAlive(false);
                    ball.setVelocityY(-ball.getVelocityY());
                }
            }
        }

        if (ball.getY() - ball.getRadius() > getHeight()) {
            ball = new Ball(400, 500, 10, -1, -2);
            isGameRunning = false;
            isGameOver = true;
        }
    }

    private void updateGameState() {
        boolean allBricksDestroyed = true;
        for (Brick brick : bricks) {
            if (brick.isAlive()) {
                allBricksDestroyed = false;
                break;
            }
        }

        if (allBricksDestroyed) {
            isGameRunning = false;
            isGameOver = true;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paddle.draw(g);
        ball.draw(g);
        for (Brick brick : bricks) {
            brick.draw(g);
        }

        if (isGameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            String message = "Game Over! Press Enter to restart.";
            int messageWidth = g.getFontMetrics().stringWidth(message);
            g.drawString(message, (getWidth() - messageWidth) / 2, getHeight() / 2);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (isGameOver && keyCode == KeyEvent.VK_ENTER) {
            resetGame();
            isGameOver = false;
            startGame();
        } else if (!isGameOver) {
            if (keyCode == KeyEvent.VK_LEFT) {
                paddle.move(-20);
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                paddle.move(20);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Brick Breaker");
        BrickBreakerGame game = new BrickBreakerGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        game.startGame();
        game.requestFocusInWindow();
    }
}
