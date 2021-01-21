package application;

import java.awt.*;

public abstract class Entity {

    public static final int entitySize = 100;
    public static final int offset = 50;

    protected int xPos;
    protected int yPos;
    protected int coordX;
    protected int coordY;

    protected Color fillColor;
    protected Color borderColor;

    /**
     * Constructor for a base class, used to initialise positions.
     *
     * @param xPos Horizontal position on the gameBoard
     * @param yPos Vertical position on the gameBoard
     */
    protected Entity(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.coordX = xPos * entitySize + xPos + offset;
        this.coordY = yPos * entitySize + yPos + offset;
    }

    public int getXPos() {
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    public void setXPos(int newPos) {
        this.xPos = newPos;
        this.coordX = xPos * entitySize + xPos + offset;
    }

    public void setYPos(int newPos) {
        this.yPos = newPos;
        this.coordY = yPos * entitySize + yPos + offset;
    }
}
