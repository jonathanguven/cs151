package minimac;

import tools.Publisher;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MiniMac extends Publisher implements Serializable {
    int size = 32;
    Integer[] memory = new Integer[size];
    boolean halt = false;
    int ip = 0;

    public MiniMac() {
        clear();
    }

    public void clear() {
        Arrays.fill(memory, 0);
        notifySubscribers();
    }

//    public void execute() {
//
//    }

    public Integer[] getMemory() {
        return memory;
    }

    public void halt(boolean halt) {
        this.halt = halt;
    }

    public static void main(String[] args) {
        MiniMac mac = new MiniMac();
        Integer[] memory = mac.getMemory();

        Load load5 = new Load(0, 5);
        load5.execute(mac);
        System.out.println("loading 5 to location 0: " + memory[0]);

        Load load10 = new Load(1, 10);
        load10.execute(mac);
        System.out.println("loading 10 to location 1: " + memory[1]);

        Add add = new Add(0, 1, 2);
        add.execute(mac);
        System.out.println("testing addition of 5 + 10: " + memory[2]);

        Multiply multiply = new Multiply(0, 1, 3);
        multiply.execute(mac);
        System.out.println("testing multiplication of 5 * 10: " + memory[3]);

        Halt halt = new Halt();
        halt.execute(mac);
    }
}

