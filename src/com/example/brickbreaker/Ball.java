package com.example.brickbreaker;

import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private int radius;
    private int velocityX;
    private int velocityY;

    public Ball(int x, int y, int radius, int velocityX, int velocityY) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    public void move() {
        x += velocityX;
        y += velocityY;
    }

    public int getRadius() {
        return radius;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
 // Inside the Ball class

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

}

