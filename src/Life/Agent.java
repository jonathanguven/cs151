package Life;

import CALab.Cell;

import java.awt.*;

public class Agent extends Cell {
    public int status = 0;
    public int ambience = 0;

    @Override
    public void observe() {
        ambience = 0;
        for (Cell a : neighbors) {
            if (((Agent) a).getStatus() == 1) {
                ambience++;
            }
        }
    }

    @Override
    public void interact() {
    }

    @Override
    public void update() {
        if (status == 1) {
            if (Society.death.contains(ambience)) {
                status = 0;
            }
        } else {
            if (Society.rebirth.contains(ambience)) {
                status = 1;
            }
        }
    }

    @Override
    public void nextState() {
        if (status == 0 && Society.death.contains(status)) {
            status = 1;
        } else if (status == 1 && Society.death.contains(ambience)) {
            status = 0;
        }
    }

    @Override
    public void reset(boolean randomly) {
        if (randomly) {
            status = (int) (Math.random() * 2);
        } else {
            status = 0;
            ambience = 0;
        }
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public int getAmbience() {
        return ambience;
    }


    @Override
    public Color getColor() {
        if (getStatus() == 0) {
            return Color.RED;
        } else {
            return Color.GREEN;
        }
    }

}