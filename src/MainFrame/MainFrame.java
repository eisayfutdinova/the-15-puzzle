package MainFrame;

import FileDialogPackage.FileDialog;
import Panel.Panel;

import javax.swing.*;

import JMenu.Menu;
import JMenu.About;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainFrame extends JFrame {

    private final Panel myPanel = new Panel(this);
    private final Menu myMenu = new Menu(this);
    public boolean musicSwitcher = true;
    private static final String NAME = "The 15 Puzzle";

    private MainFrame() {
        super(NAME);
        configureFrame();
        setJElements();
        setVisible(true);
        JOptionPane.showMessageDialog(null, "Чтобы игра началась нажмите OK ", "Information!", JOptionPane.WARNING_MESSAGE);

        myPanel.randomize();
    }

    private void configureFrame() {
        int BOUNDS = 200;
        int SIZE = 500;
        setBounds(BOUNDS, BOUNDS, SIZE, SIZE);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setJElements() {

        setJMenuBar(myMenu);
        myPanel.fillGrid(new BufferedImage[]{}, true);
        add(myPanel);

    }

    private void reloadPanel(BufferedImage[] newImage) {

        myPanel.removeAll();
        myPanel.fillGrid(newImage, false);
    }

    private void Message() {
        JOptionPane.showMessageDialog(null, "Чтобы игра началась нажмите OK ", "Information!", JOptionPane.WARNING_MESSAGE);

        myPanel.randomize();
    }

    public class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "Open":
                    FileDialog dialog = new FileDialog();
                    File dialogResult = dialog.openDialog();
                    if (dialogResult != null) {
                        reloadPanel(dialog.setDialogResult(dialogResult));
                        Message();
                    }
                    break;
                case "Randomise":
                    myPanel.randomize();
                    break;
                case "About":
                    new About();
                    break;
                case "Exit":
                    System.exit(0);
                    break;
            }

            switch (command) {
                case "Author":
                    reloadPanel(myMenu.selectImage("Author"));
                    Message();
                    break;
                case "Minion":
                    reloadPanel(myMenu.selectImage("Minion"));
                    Message();
                    break;
                case "MacOS":
                    reloadPanel(myMenu.selectImage("MacOS"));
                    Message();
                    break;
                case "Potter":
                    reloadPanel(myMenu.selectImage("Potter"));
                    Message();
                    break;
            }
        }
    }

    public class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = ((JButton) e.getSource()).getName();
            myPanel.changeReplaces(name);

        }
    }


    public class SwitchAudio implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "on":
                    myMenu.on.setState(true);
                    myMenu.off.setState(false);
                    musicSwitcher = true;
                    break;
                case "off":
                    myMenu.on.setState(false);
                    myMenu.off.setState(true);
                    musicSwitcher = false;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

}
