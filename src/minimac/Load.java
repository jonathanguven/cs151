package minimac;

public class Load implements Instruction {
    private int value;
    private int location;

    public Load(int location, int value) {
        this.value = value;
        this.location = location;
    }

    @Override
    public void execute(MiniMac mac) {
        Integer[] memory = mac.getMemory();
        if (location >= 0 && location < mac.size) {
            memory[location] = value;
            mac.notifySubscribers();
        } else {
            throw new IndexOutOfBoundsException("Invalid memory access at location " + location);
        }
    }
}
