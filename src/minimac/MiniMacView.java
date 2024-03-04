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


    public void setMac(MiniMac newMac) {
        mac.unsubscribe(this);
        mac = newMac;
        mac.subscribe(this);

    }

    @Override
    public void update() {
        component.updateLists(mac);
    }
}