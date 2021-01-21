package Teams;

import application.Entity;
import enums.Nation;
import pawns.Pawn;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TeamManager {

    Team greenTeam;
    Team yellowTeam;
    Pawn selectedPawn = null;

    public TeamManager() {
        this.yellowTeam = new Team(Nation.YELLOW, 0, false);
        this.greenTeam = new Team(Nation.GREEN, 4, true);
    }

    public void paintTeams(Graphics g) {
        yellowTeam.render(g);
        greenTeam.render(g);
    }

    public boolean movePawn(MouseEvent e) {
        Point selectedTile = getPosition(e);

        if (!isInBoundary(selectedTile)) return false;

        Pawn result = getPawnOnSelectedTile(selectedTile);

        if (result == null && selectedPawn != null) {
            if (validateMove(selectedTile, selectedPawn)) {
                movePawn(selectedTile, selectedPawn);
                return true;
            }
        }
        if (result != null && selectedPawn == null) {
            selectedPawn = result;
            result = null;
        }
        if (result != null) selectedPawn = result;

        return false;
    }

    private void movePawn(Point selectedTile, Pawn selectedPawn) {
        System.out.println("Moving pawn");
        selectedPawn.setXPos(selectedTile.x);
        selectedPawn.setYPos(selectedTile.y);
    }

    private boolean validateMove(Point selectedTile, Pawn selectedPawn) {
        return switch (selectedPawn.getClass().getSimpleName()) {
            case "Leader" -> isValidLeaderMove(selectedTile, selectedPawn);
            case "Guard" -> isValidGuardMove(selectedTile, selectedPawn);
            default -> false;
        };
    }

    private boolean isValidLeaderMove(Point selectedTile, Pawn selectedPawn) {


        if (selectedTile.x == selectedPawn.getXPos()) {
            if (selectedTile.y < selectedPawn.getYPos()) return verifyDestUp(selectedTile, selectedPawn);
            if (selectedTile.y > selectedPawn.getYPos()) return verifyDestDown(selectedTile, selectedPawn);
        }
        if (selectedTile.y == selectedPawn.getYPos()) {
            if (selectedTile.x < selectedPawn.getXPos()) return verifyDestLeft(selectedTile, selectedPawn);
            if (selectedTile.x > selectedPawn.getXPos()) return verifyDestRight(selectedTile, selectedPawn);
        }
        return false;
    }

    private boolean verifyDestUp(Point selectedTile, Pawn selectedPawn) {
        Point possibleDest = null;
        int i;

        for (i = selectedPawn.getYPos() - 1; i > -1; i--) {
            if (getPawnOnSelectedTile(new Point(selectedPawn.getXPos(), i)) != null) {
                possibleDest = new Point(selectedPawn.getXPos(), ++i);
                break;
            }
        }
        if (possibleDest == null) return selectedTile.y == 0;

        return possibleDest.y == selectedTile.y;
    }

    private boolean verifyDestDown(Point selectedTile, Pawn selectedPawn) {
        Point possibleDest = null;
        int i;

        for (i = selectedPawn.getYPos() + 1; i < 5; i++) {
            if (getPawnOnSelectedTile(new Point(selectedPawn.getXPos(), i)) != null) {
                possibleDest = new Point(selectedPawn.getXPos(), --i);
                break;
            }
        }
        if (possibleDest == null) return selectedTile.y == 4;

        return possibleDest.y == selectedTile.y;
    }

    private boolean verifyDestRight(Point selectedTile, Pawn selectedPawn) {
        Point possibleDest = null;
        int i;

        for (i = selectedPawn.getXPos() + 1; i < 5; i++) {
            if (getPawnOnSelectedTile(new Point(i, selectedPawn.getYPos())) != null) {
                possibleDest = new Point(--i, selectedPawn.getYPos());
                break;
            }
        }
        if (possibleDest == null) return selectedTile.x == 4;

        return possibleDest.x == selectedTile.x;
    }

    private boolean verifyDestLeft(Point selectedTile, Pawn selectedPawn) {
        Point possibleDest = null;
        int i;

        for (i = selectedPawn.getXPos() - 1; i > -1; i--) {
            if (getPawnOnSelectedTile(new Point(i, selectedPawn.getYPos())) != null) {
                possibleDest = new Point(++i, selectedPawn.getYPos());
                break;
            }
        }
        if (possibleDest == null) return selectedTile.x == 0;

        return possibleDest.x == selectedTile.x;
    }

    private boolean isValidGuardMove(Point selectedTile, Pawn selectedPawn) {
        return isHorizontallyAdjacent(selectedTile, selectedPawn) ||
                isVerticallyAdjacent(selectedTile, selectedPawn);
    }

    private boolean isHorizontallyAdjacent(Point selectedTile, Pawn selectedPawn) {
        return Math.abs(selectedTile.x - selectedPawn.getXPos()) == 1 &&
                Math.abs(selectedTile.y - selectedPawn.getYPos()) == 0;
    }

    private boolean isVerticallyAdjacent(Point selectedTile, Pawn selectedPawn) {
        return Math.abs(selectedTile.x - selectedPawn.getXPos()) == 0 &&
                Math.abs(selectedTile.y - selectedPawn.getYPos()) == 1;
    }


    private Pawn getPawnOnSelectedTile(Point selectedTile) {
        Pawn resYellow = yellowTeam.getPawn(selectedTile);
        Pawn resGreen = greenTeam.getPawn(selectedTile);

        if (resYellow != null) return resYellow;
        return resGreen;
    }

    private boolean isInBoundary(Point selection) {
        return (selection.y > -1 && selection.y < 5) &&
                (selection.x > -1 && selection.x < 5);
    }

    private Point getPosition(MouseEvent e) {
        Point point = e.getPoint();
        return new Point(
                (point.x + Entity.offset) / Entity.entitySize - 1,
                (point.y + Entity.offset) / Entity.entitySize - 1);
    }
}
