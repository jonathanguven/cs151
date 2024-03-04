package minimac;
import tools.Publisher;
import tools.Utilities;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MiniMac extends Publisher implements Serializable {
    int size = 32;
    Integer[] memory = new Integer[size];
    boolean halt = false;
    int ip = 0;
    List<Instruction> instructions;
    String fileName;

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
        notifySubscribers();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUnsavedChanges(boolean b) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(this);
            os.close();
        } catch (Exception err) {
            Utilities.error(err);
        }
    }
}

