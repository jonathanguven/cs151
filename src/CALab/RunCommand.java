package CALab;

import mvc.Command;
import mvc.Model;

public class RunCommand extends Command {
    private final int cycles;

    public RunCommand(Model m, int cycles) {
        super(m);
        this.cycles = cycles;
    }

    @Override
    public void execute() {
        Grid grid = (Grid) model;
        grid.updateLoop(cycles);
    }
}
