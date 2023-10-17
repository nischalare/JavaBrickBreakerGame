package com.example.brickbreaker;

import java.awt.*;

public class Paddle {
    private int x;
    private int y;
    private int width;
    private int height;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    public void move(int dx) {
        x += dx;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }
 // Inside the Paddle class

    public int getY() {
        return y;
    }

}
