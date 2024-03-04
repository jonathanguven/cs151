package minimac;

public class Move implements Instruction {
    private int src;
    private int dest;

    public Move(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }

    public String toString() {
        return "Move " + src + " " + dest;
    }

    public void execute(MiniMac mac) {
        if (dest >= 0 && dest < mac.size) {
            mac.memory[dest] = mac.memory[src];
        } else {
            throw new IndexOutOfBoundsException("Invalid memory access at location " + dest);
        }
    }
}
