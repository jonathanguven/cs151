package minimac;

import tools.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MiniMacView extends JPanel implements Subscriber {
    private MiniMacComponent component;
    private MiniMac mac;

    public MiniMacView(MiniMac mac) {
        this.mac = mac;
        this.mac.subscribe(this); // subscribe to updates from minimac
        setLayout(new BorderLayout());
        component = new MiniMacComponent(mac);
        add(component, BorderLayout.CENTER);
    }


    public void setMac(MiniMac newMac, List<Instruction> instructions) {
        mac.unsubscribe(this);
        mac = newMac;
        mac.instructions = instructions;
        component.updateMemory(mac);
        component.updateInstructions(instructions);
        mac.subscribe(this);
    }

    public void updateInstructions(List<Instruction> instructions) {
        component.updateInstructions(instructions);
    }

    @Override
    public void update() {
        component.updateMemory(mac);
        component.updateInstructions(mac.instructions);
    }
}