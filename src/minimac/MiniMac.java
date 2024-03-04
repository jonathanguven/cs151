package minimac;
import tools.Publisher;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;

public class MiniMac extends Publisher implements Serializable {
    int size = 32;
    Integer[] memory = new Integer[size];
    boolean halt = false;
    int ip = 0;
    List<Instruction> instructions;

//    Constructor
    public MiniMac() {
        clear();
    }

//    Execute list of instructions
    public void execute() {
        while (ip < instructions.size() && !halt) {
            Instruction next = instructions.get(ip++);
            next.execute(this);
        }
        ip = 0;
        halt = false;
        notifySubscribers();
    }

//    Clear memory
    public void clear() {
        halt = false;
        ip = 0;
        Arrays.fill(memory, 0);
        instructions = null;
        notifySubscribers();
    }
}

