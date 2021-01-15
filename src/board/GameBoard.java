package board;

import enums.Colors;
import enums.Nation;
import pawns.Guard;
import pawns.Leader;
import pawns.Pawn;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameBoard extends JFrame {

    ArrayList<BoardTile> tiles;
    ArrayList<Pawn> yellowSide;
    ArrayList<Pawn> greenSide;

    /**
     * Constructor used for setting the main window and all objects used in the game
     */
    public GameBoard() {
        this.tiles = setTiles();
        this.yellowSide = setYellowSide();
        this.greenSide = setGreenSide();

        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Method, used for drawing the entities on the screen
     *
     * @param g Graphics base class
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        paintTiles(g);
        paintEnemySides(g);
    }

    /**
     * Method, used for drawing the tiles on the screen
     *
     * @param g Graphics base class
     */
    private void paintTiles(Graphics g) {
        for (BoardTile tile : tiles) {
            tile.render(g);
        }
    }

    /**
     * Method, used for drawing the pawns on the screen
     *
     * @param g Graphics base class
     */
    private void paintEnemySides(Graphics g) {
        for (int i = 0; i < 5; i++) {
            yellowSide.get(i).render(g);
            greenSide.get(i).render(g);
        }
    }

    /**
     * Sets the pawns for the Yellow nation
     *
     * @return ArrayList containing the pawn objects
     */
    private ArrayList<Pawn> setYellowSide() {
        ArrayList<Pawn> pawns = new ArrayList<>();

        for (int i = 0; i < 4; i++) pawns.add(new Guard(i, 0, Nation.YELLOW));
        pawns.add(new Leader(4, 0, Nation.YELLOW));

        return pawns;
    }

    /**
     * Sets the pawns for the Green nation
     *
     * @return ArrayList containing the pawn objects
     */
    private ArrayList<Pawn> setGreenSide() {
        ArrayList<Pawn> pawns = new ArrayList<>();

        for (int i = 4; i > 0; i--) pawns.add(new Guard(i, 4, Nation.GREEN));
        pawns.add(new Leader(0, 4, Nation.GREEN));

        return pawns;
    }

    /**
     * Creates all columns in the board
     *
     * @return Initialised board
     */
    private ArrayList<BoardTile> setTiles() {
        ArrayList<BoardTile> tempTiles = new ArrayList<>();
        tempTiles.addAll(getTileColumn(0, false));
        tempTiles.addAll(getTileColumn(1, true));
        tempTiles.addAll(getCenterColumn());
        tempTiles.addAll(getTileColumn(3, true));
        tempTiles.addAll(getTileColumn(4, false));
        return tempTiles;
    }

    /**
     * Creates a column of tiles. Used for all columns, besides the center one
     *
     * @param xPos      Horizontal position on the gameBoard
     * @param isReverse Value indicating the orientation of the column
     * @return ArrayList with the tiles, contained in a column
     */
    private ArrayList<BoardTile> getTileColumn(int xPos, boolean isReverse) {
        int yPos = 0;
        int dir = (isReverse) ? 4 : 0;

        ArrayList<BoardTile> standardTileColumn = new ArrayList<>();

        standardTileColumn.add(new BoardTile(xPos, Math.abs(dir - yPos++), Colors.GRAPHITE.color, Colors.PEACH.color));
        standardTileColumn.add(new BoardTile(xPos, Math.abs(dir - yPos++), Colors.GRAPHITE.color, Colors.GRAY.color));
        standardTileColumn.add(new BoardTile(xPos, Math.abs(dir - yPos++), Colors.GRAPHITE.color, Colors.WHITE.color));
        standardTileColumn.add(new BoardTile(xPos, Math.abs(dir - yPos++), Colors.GRAPHITE.color, Colors.GRAY.color));
        standardTileColumn.add(new BoardTile(xPos, Math.abs(dir - yPos), Colors.GRAPHITE.color, Colors.GRAPHITE.color));

        return standardTileColumn;
    }

    /**
     * Sets the tiles in the center column of the board
     *
     * @return ArrayList with the tiles, contained in the center column
     */
    private ArrayList<BoardTile> getCenterColumn() {
        int yPos = 0;
        int xPos = 2;

        ArrayList<BoardTile> centerTileColumn = new ArrayList<>();

        centerTileColumn.add(new BoardTile(xPos, yPos++, Colors.GRAPHITE.color, Colors.WHITE.color));
        centerTileColumn.add(new BoardTile(xPos, yPos++, Colors.GRAPHITE.color, Colors.WHITE.color));
        centerTileColumn.add(new CenterTile(xPos, yPos++, Colors.GRAPHITE.color, Colors.WHITE.color, Colors.GRAPHITE.color, Colors.GRAY.color));
        centerTileColumn.add(new BoardTile(xPos, yPos++, Colors.GRAPHITE.color, Colors.WHITE.color));
        centerTileColumn.add(new BoardTile(xPos, yPos, Colors.GRAPHITE.color, Colors.WHITE.color));

        return centerTileColumn;
    }

}
