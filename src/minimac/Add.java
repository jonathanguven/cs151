package minimac;

public class Add implements Instruction {
    private int num1;
    private int num2;
    private int dest;

    public Add(int num1, int num2, int dest) {
        this.num1 = num1;
        this.num2 = num2;
        this.dest = dest;
    }

    @Override
    public void execute(MiniMac mac) {
        Integer[] memory = mac.getMemory();
        memory[dest] = memory[num1] + memory[num2];
        mac.notifySubscribers();
    }
}
