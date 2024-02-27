package minimac;

public class Halt implements Instruction {
    public String toString() {
        return "Halt";
    }

    @Override
    public void execute(MiniMac mac) {
        mac.halt = true;
    }
}
