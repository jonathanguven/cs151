package minimac;

public class Blt implements Instruction {
    private int location;
    private int offset;

    public Blt(int location, int offset) {
        this.location = location;
        this.offset = offset;
    }

    public String toString() {
        return "Blt " + location + " " + offset;
    }

    @Override
    public void execute(MiniMac mac) {
        if (location >= 0 && location < mac.size) {
            if (mac.memory[location] < 0) {
                mac.ip += offset;
            }
        } else {
            throw new IndexOutOfBoundsException("Invalid memory access at location " + location);
        }
    }
}
