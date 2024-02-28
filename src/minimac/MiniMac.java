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

//    Constructor
    public MiniMac() {
        clear();
    }

//    Execute list of instructions
    public void execute(List<Instruction> instructions) {
        while (ip < instructions.size() && !halt) {
            Instruction next = instructions.get(ip++);
            next.execute(this);
        }
    }

//    Clear memory
    public void clear() {
        Arrays.fill(memory, 0);
        notifySubscribers();
    }

    public void printMemory() {
        for (int i = 0; i < memory.length; i++) {
            System.out.println("memory[" + i + "] = " + memory[i]);
        }
    }

    public static void main(String[] args) {
        MiniMac mac = new MiniMac();
        List<Instruction> instructions;
        String programString;
        Scanner scanner = new Scanner(System.in);

//        System.out.println("Enter the name of the file containing the program: ");
//        String fileName = scanner.nextLine();

        String fileName = "less";
        try {
            programString = Files.readString(Path.of(fileName));
            instructions = MiniMacParser.parse(programString);
        } catch (Exception e) {
            System.out.println("Parsing error: " + e.getMessage());
            return;
        }

        mac.execute(instructions);
        mac.printMemory();
    }
}

