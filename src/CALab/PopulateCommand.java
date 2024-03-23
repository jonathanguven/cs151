package CALab;

import mvc.Command;
import mvc.Model;

public class PopulateCommand extends Command {
    public PopulateCommand(Model m) {
        super(m);
    }

    @Override
    public void execute() {
        Grid grid = (Grid) getModel();
        grid.repopulate(true);
    }
}
