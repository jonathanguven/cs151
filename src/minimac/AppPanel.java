package minimac;

import tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static tools.Utilities.getFileName;

public class AppPanel extends JPanel implements ActionListener {
    private MiniMac mac;
    private ControlPanel controls;
    private MiniMacView view;

    public AppPanel() {
        mac = new MiniMac();
        view = new MiniMacView(mac);
        controls = new ControlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
        this.add(view);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("MiniMac");
        frame.setSize(800, 480);
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"Parse", "Run", "Clear"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {
                case "Parse": {
                    String fileName = JOptionPane.showInputDialog(this, "Enter program file name", "Input", JOptionPane.QUESTION_MESSAGE);
                    if (fileName != null && !fileName.trim().isEmpty()) {
                        try {
                            String programString = Files.readString(Path.of(fileName.trim()));
                            mac.instructions = MiniMacParser.parse(programString);
                            view.updateInstructions(mac.instructions);
                        } catch (java.nio.file.NoSuchFileException err) {
                            JOptionPane.showMessageDialog(this, "Program " + fileName + " doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (IOException err) {
                            JOptionPane.showMessageDialog(this, "An error occurred while reading the file: " + err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception err) {
                            JOptionPane.showMessageDialog(this, "An error occurred: " + err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No file name entered or operation cancelled.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                }

                case "Run": {
                    if (mac.instructions != null) {
                        mac.execute();
                    } else {
                        System.out.println("No instructions to run.");
                    }
                    break;
                }

                case "Clear": {
                    mac.clear();
                    if (mac.instructions != null) {
                        mac.instructions.clear();
                    }
                    view.update();
                    break;
                }

                case "About": {
                    Utilities.inform("MiniMac is a virtual processor with a tiny but extendable memory and a tiny but extendable instruction set.\nMade by Jonathan Nguyen.\nCS 151 Section 4");
                    break;
                }

                case "Help": {
                    String[] cmmds = new String[]{
                            "Parse: Parses a file of the inputted program name",
                            "Run: Runs the parsed program",
                            "Clear: Clears the memory and instructions",
                    };
                    Utilities.inform(cmmds);
                    break;
                }

                case "New": {
                    mac = new MiniMac();
                    view = new MiniMacView(mac);
                    this.removeAll();
                    this.add(controls);
                    this.add(view);
                    this.revalidate();
                    this.repaint();
                    break;
                }

                case "Save": {
                    save(mac, true);
                    break;
                }

                case "Open": {
                    mac = open(mac);
                    view.setMac(mac, mac.instructions);
                    break;
                }

                case "Quit": {
                    System.exit(0);
                    break;
                }

                default: {
                    throw new Exception("Unrecognized command: " + cmmd);
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }

    public static void save(MiniMac model, Boolean saveAs) {
        String fName = model.getFileName();
        if (fName == null || saveAs) {
            fName = getFileName(fName, false);
            model.setFileName(fName);
        }
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
            model.setUnsavedChanges(false);
            os.writeObject(model);
            os.close();
        } catch (Exception err) {
            model.setUnsavedChanges(true);
            Utilities.error(err);
        }
    }

    public static MiniMac open(MiniMac model) {
//        saveChanges(model);
        String fName = getFileName(model.getFileName(), true);
        MiniMac newModel = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
            newModel = (MiniMac)is.readObject();
            is.close();
        } catch (Exception err) {
            Utilities.error(err);
        }
        return newModel;
    }

    private static void saveChanges(MiniMac model) {
        save(model, false);
    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            setLayout(new FlowLayout());
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(3, 1, 5, 5));

            JButton parse = new JButton("Parse");
            parse.addActionListener(AppPanel.this);
            buttonPanel.add(parse);

            JButton run = new JButton("Run");
            run.addActionListener(AppPanel.this);
            buttonPanel.add(run);

            JButton clear = new JButton("Clear");
            clear.addActionListener(AppPanel.this);
            buttonPanel.add(clear);

            add(buttonPanel);
        }
    }

    public static void main(String[] args) {
        AppPanel app = new AppPanel();
    }
}