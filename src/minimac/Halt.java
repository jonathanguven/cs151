package minimac;

public class Halt implements Instruction {
    @Override
    public void execute(MiniMac mac) {
        mac.halt(true);
        System.out.println("Halting the machine");
    }
}
