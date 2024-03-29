package minimac;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public class MiniMacComponent extends JComponent {
    private JList<String> memory;
    private JList<String> instructions;
    private DefaultListModel<String> memoryModel;
    private DefaultListModel<String> instructionModel;

    public MiniMacComponent(MiniMac mac) {
        setLayout(new GridLayout(2, 1));

        memoryModel = new DefaultListModel<>();
        updateMemory(mac);
        memory = new JList<>(memoryModel);

        instructionModel = new DefaultListModel<>();
        instructions = new JList<>(instructionModel);

        add(new JScrollPane(memory));
        add(new JScrollPane(instructions));
    }

    public void updateMemory(MiniMac mac) {
        memoryModel.clear();
        for (int i = 0; i < mac.memory.length; i++) {
            memoryModel.addElement("memory[" + i + "] = " + mac.memory[i]);
        }
    }

    public void updateInstructions(List<Instruction> instructions) {
        instructionModel.clear();
        if (instructions == null) return;
        for (Instruction i : instructions) {
            instructionModel.addElement(i.toString());
        }
    }

}