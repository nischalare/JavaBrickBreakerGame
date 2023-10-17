package com.example.brickbreaker;

import java.awt.*;

public class Brick {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean alive;

    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alive = true;
    }

    public void draw(Graphics g) {
        if (alive) {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

