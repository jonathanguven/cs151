package minimac;

public class Load implements Instruction {
    private int value;
    private int location;

    public Load(int location, int value) {
        this.value = value;
        this.location = location;
    }

    public String toString() {
        return "Load " + location + " " + value;
    }

    @Override
    public void execute(MiniMac mac) {
        if (location >= 0 && location < mac.size) {
            mac.memory[location] = value;
        } else {
            throw new IndexOutOfBoundsException("Invalid memory access at location " + location);
        }
    }
}
