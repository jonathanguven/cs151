package minimac;

import java.util.List;

public class Block implements Instruction {
    private List<Instruction> instructions;

    public Block(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    @Override
    public void execute(MiniMac mac) {
        for (Instruction instruction : instructions) {
            instruction.execute(mac);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{ ");
        for (Instruction instruction : instructions) {
            result.append(instruction.toString()).append(" ; ");
        }
        if (!instructions.isEmpty()) {
            result.setLength(result.length() - 2);
        }
        result.append(" }");
        return result.toString();
    }
}
