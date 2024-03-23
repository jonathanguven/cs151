package CALab;

import mvc.Command;
import mvc.Model;

public class ClearCommand extends Command {
    public ClearCommand(Model m) {
        super(m);
    }

    @Override
    public void execute() {
        Grid grid = (Grid) model;
        grid.repopulate(false);
        grid.observe();
    }
}
