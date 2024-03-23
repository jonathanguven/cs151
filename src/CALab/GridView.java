package CALab;

import mvc.Model;
import mvc.View;

import java.awt.*;

public class GridView extends View {
    private CellView[][] cellViews;

    public GridView(Model model) {
        super(model);
        Grid grid = (Grid) model;
        int size = grid.getDim();
        cellViews = new CellView[size][size];
        this.setLayout((new GridLayout(size, size)));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = grid.getCell(i, j);
                cell.row = i;
                cell.col = j;
                cellViews[i][j] = new CellView(cell);
                this.add(cellViews[i][j]);
            }
        }
    }

    public void update() {
        for (int row = 0; row < cellViews.length; row++) {
            for (int col = 0; col < cellViews[0].length; col++) {
                cellViews[row][col].update();
            }
        }
    }

    @Override
    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);

        int dim = ((Grid) model).getDim();
        cellViews = new CellView[dim][dim];
        this.setLayout((new GridLayout(dim, dim)));
        this.removeAll();
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                Cell cell = ((Grid) model).getCell(row, col);
                cell.row = row;
                cell.col = col;
                cellViews[row][col] = new CellView(cell);
                this.add(cellViews[row][col]);
            }
        }
    }

}