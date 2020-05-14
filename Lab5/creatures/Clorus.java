package creatures;

import huglife.Creature;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    @Override
    public String name(){
        return super.name();
    }

    public void move() {
        energy -= 0.03;
        if (energy < 0){
            energy = 0;
        }
    }

    public void stay() {
        energy -= 0.01;
        if (energy < 0){
            energy = 0;
        }
    }

    public void attack(Creature c) {
         energy += c.energy();
    }

    public Clorus replicate() {
        energy = energy / 2;
        Clorus babyClorus = new Clorus(energy);
        return babyClorus;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.add(d);
            } else if (neighbors.get(d).name().equals("plip")) {
                plipNeighbors.add(d);
            }
        }
        if (emptyNeighbors.size()==0) { //Rule 1
            return new Action(Action.ActionType.STAY);
        }else if (plipNeighbors.size()>0){
            return new Action(Action.ActionType.ATTACK,randomEntry(plipNeighbors));
        }else if (energy >= 1){
            return new Action(Action.ActionType.REPLICATE,randomEntry(emptyNeighbors));
        }else {
            return new Action(Action.ActionType.MOVE,randomEntry(emptyNeighbors));
        }
    }
}
