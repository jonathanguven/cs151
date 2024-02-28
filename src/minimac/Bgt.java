package minimac;

public class Bgt implements Instruction {
    private int location;
    private int offset;

    public Bgt(int location, int offset) {
        this.location = location;
        this.offset = offset;
    }

    public String toString() {
        return "Bgt " + location + " " + offset;
    }

    @Override
    public void execute(MiniMac mac) {
        if (location >= 0 && location < mac.size) {
            if (mac.memory[location] > 0) {
                mac.ip += offset;
                mac.notifySubscribers();
            }
        } else {
            throw new IndexOutOfBoundsException("Invalid memory access at location " + location);
        }
    }
}
