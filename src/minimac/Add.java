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

    public String toString() {
        return "Add " + num1 + " " + num2 + " " + dest;
    }

    @Override
    public void execute(MiniMac mac) {
        mac.memory[dest] = mac.memory[num1] + mac.memory[num2];
    }
}
