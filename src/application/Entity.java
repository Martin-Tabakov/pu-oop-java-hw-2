package application;

import java.awt.*;

public abstract class Entity {

    protected int entitySize = 100;
    protected final int offset = 50;

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
}
