package Teams;

import enums.Nation;
import pawns.Guard;
import pawns.Leader;
import pawns.Pawn;

import java.awt.*;
import java.util.ArrayList;

public class Team {

    private ArrayList<Pawn> members;

    public Team(Nation nation,int rowPosition,boolean isLeaderFirst){
        this.members = setMembers(nation,rowPosition,isLeaderFirst);
    }

    private ArrayList<Pawn> setMembers(Nation nation,int rowPosition,boolean isLeaderFirst) {
        ArrayList<Pawn> pawns = new ArrayList<>();

        int dir = (isLeaderFirst) ? 4 : 0;
        int i;
        for (i = 0; i < 4; i++) pawns.add(new Guard(Math.abs(dir-i),rowPosition, nation));
        pawns.add(new Leader(Math.abs(dir-i), rowPosition, nation));

        return pawns;
    }

    protected Pawn getPawn(Point tile){
        for (Pawn member: members) {
            if(member.getXPos() ==tile.x && member.getYPos()== tile.y) return member;
        }
        return null;
    }

    public void render(Graphics g) {
        for (Pawn member: members) member.render(g);
    }
}
