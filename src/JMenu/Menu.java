package JMenu;

import MainFrame.MainFrame;
import FileDialogPackage.FileProcess;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

enum MenuOptions {File, Preferences}

enum MenuFileOptions {Open, Randomise, Select, Exit}

enum MenuPreferencesOptions {Sound, About}

enum MenuSelectOptions {
    Author, Minion,
}

public class Menu extends JMenuBar {

    public final JCheckBoxMenuItem on = new JCheckBoxMenuItem("On");
    public final JCheckBoxMenuItem off = new JCheckBoxMenuItem("Off");

    private final MainFrame myFrame;

    public Menu(MainFrame mainFrame) {
        myFrame = mainFrame;
        setMenuOptions();
    }


    private void setMenuOptions() {
        JMenu fileMenu = new JMenu(MenuOptions.File.toString());
        fileMenu.add(setFileOptions(MenuFileOptions.Open.toString()));


        JMenu selectMenu = new JMenu(MenuFileOptions.Select.toString());
        selectMenu.add(setFileOptions(MenuSelectOptions.Author.toString()));
        selectMenu.add(setFileOptions(MenuSelectOptions.Minion.toString()));
        fileMenu.add(selectMenu);


        fileMenu.add(setFileOptions(MenuFileOptions.Randomise.toString()));
        fileMenu.add(setFileOptions(MenuFileOptions.Exit.toString()));
        this.add(fileMenu);

        JMenu preferencesMenu = new JMenu(MenuOptions.Preferences.toString());


        JMenu soundSwitcher = new JMenu(MenuPreferencesOptions.Sound.toString());
        on.setState(true);
        on.setActionCommand("on");
        on.addActionListener(myFrame.new SwitchAudio());
        off.setActionCommand("off");
        off.addActionListener(myFrame.new SwitchAudio());
        soundSwitcher.add(on);
        soundSwitcher.add(off);
        preferencesMenu.add(soundSwitcher);


        preferencesMenu
                .add(setFileOptions(MenuPreferencesOptions.About.toString()));
        this.add(preferencesMenu);
    }

    private JMenuItem setFileOptions(String itemName) {
        JMenuItem item = new JMenuItem(itemName);
        item.setActionCommand(itemName);
        item.addActionListener(myFrame.new MenuListener());
        return item;
    }

    public BufferedImage[] selectImage(String name) {
        String path = "./MediaFiles/" + name + ".jpg";
        File url = new File(path);
        return FileProcess.chunkImage(Objects.requireNonNull(FileProcess.cutImage(url)));
    }


}


