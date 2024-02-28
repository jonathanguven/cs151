package minimac;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MiniMacView extends JPanel {
    private MiniMacComponent component;

    public MiniMacView(MiniMac mac) {
        setLayout(new BorderLayout());
        component = new MiniMacComponent(mac);
        add(component, BorderLayout.CENTER);
    }

    public void update(MiniMac mac, List<Instruction> instructions) {
        component.updateMemory(mac.memory);
        component.updateInstructions(instructions);
    }
}