package CALab;

import mvc.Model;

import java.util.HashSet;
import java.util.Set;

public abstract class Grid extends Model {
    static private int time = 0;
    protected int dim = 20;
    protected Cell[][] cells;

    public Grid(int dim) {
        this.dim = dim;
        cells = new Cell[dim][dim];
        populate();
    }

    public Grid() {
        this(20);
    }

    public int getDim() {
        return dim;
    }

    public int getTime() {
        return time;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public abstract Cell makeCell(boolean uniform);

    protected void populate() {
        // 1. use makeCell to fill in cells
        // 2. use getNeighbors to set the neighbors field of each cell
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col] = makeCell(true);
            }
        }
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col].neighbors = getNeighbors(cells[row][col], 1);
            }
        }
        repopulate(true);
    }

    // called when Populate button is clicked
    public void repopulate(boolean randomly) {
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                if (randomly) {
                    // randomly set the status of each cell
                    cells[row][col].reset(true);
                } else {
                    // set the status of each cell to 0 (dead)
                    cells[row][col].reset(false);
                }
            }
        }
        // comment out if populate changes the ambience of cells
        // observe();
        // notify subscribers
        changed();
    }


    public Set<Cell> getNeighbors(Cell asker, int radius) {
        /*
        return the set of all cells that can be reached from the asker in radius steps.
        If radius = 1 this is just the 8 cells touching the asker.
        Tricky part: cells in row/col 0 or dim - 1.
        The asker is not a neighbor of itself.
        */
        Set<Cell> reachable = new HashSet<>();
        int row = asker.getRow();
        int col = asker.getCol();

        for (int i = row - radius; i <= row + radius; i++) {
            for (int j = col - radius; j <= col + radius; j++) {
                int wrappedRow = (i + dim) % dim;
                int wrappedCol = (j + dim) % dim;

                if (!(i == row && j == col)) {
                    reachable.add(cells[wrappedRow][wrappedCol]);
                }
            }
        }
        return reachable;
    }

    // cell phases:

    public void observe() {
        // call each cell's observe method and notify subscribers
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col].neighbors = getNeighbors(cells[row][col], 1);
                cells[row][col].observe();
            }
        }
        changed();
    }

    public void interact() {
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col].interact();
            }
        }
        changed();
    }

    public void update() {
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col].update();
            }
        }
        changed();
    }

    public void updateLoop(int cycles) {
        observe();
        for (int cycle = 0; cycle < cycles; cycle++) {
            observe();
            interact();
            update();
            time++;
            System.out.println("time = " + getTime());
        }
    }
}
