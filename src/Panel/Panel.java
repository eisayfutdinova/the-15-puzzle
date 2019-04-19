package Panel;

import FileDialogPackage.FileProcess;
import MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;
import java.util.Random;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class Panel extends JPanel {

    private final MainFrame myFrame;

    private int[] replaces = new int[16];
    private final JButton[] buttons = new JButton[16];
    private static int count = 0;
    private final int colRow = 4;
    private final BufferedImage[] defaultImage = FileDialogPackage.FileProcess.chunkImage(
            Objects.requireNonNull(
                    FileProcess.cutImage(
                            new File("./MediaFiles/Minion.jpg")
                    )
            )
    );

    public Panel(MainFrame mainFrame) {
        initialiseNumbers();
        myFrame = mainFrame;
        configurePanel();
    }

    private void configurePanel() {
        setLayout(new GridLayout(colRow, colRow));
    }

    public void fillGrid(BufferedImage[] chunks, boolean isDefault) {

        replaces = initialiseNumbers();
        for (int i = 0; i < 16; i++)
            buttons[i] = new JButton();


        if (isDefault)
            chunks = defaultImage.clone();


        for (int i = 0; i < colRow * colRow; ++i) {
            Icon icon = new ImageIcon(chunks[i]);
            buttons[i].setIcon(icon);

        }


        removeAll();

        ImageIcon[] images = new ImageIcon[16];


        for (int i = 0; i < replaces.length; ++i)
            images[i] = (ImageIcon) buttons[i].getIcon();

        for (int i = 0; i < replaces.length; ++i) {

            if (replaces[i] == 15)
                buttons[i].setVisible(false);
            else
                buttons[i].setVisible(true);

            buttons[i].setIcon(images[replaces[i]]);

            buttons[i].setName(Integer.toString(i));
            buttons[i].setFocusable(false);

            buttons[i].addActionListener(myFrame.new ClickListener());

            add(buttons[i]);
        }


        validate();
        repaint();
    }

    public void changeReplaces(String name) {
        int n = Integer.parseInt(name);
        int j = 0;

        while (replaces[j] != 15)
            j++;

        if (canMove(n, j)) {
            swap(n, j);
            count++;
            changePanel(n, j);
            playAudio();
        }

        if (checkWin()) {
            gameOver();
            String text = (Integer.toString(count));
            buttons[n].setText(text);
            buttons[n].setVisible(true);
            JOptionPane.showMessageDialog(null, "YOU WIN!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void gameOver() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < replaces.length; ++i)
            if (replaces[i] != i)
                return false;

        return true;
    }

    private boolean canMove(int i, int j) {
        return i == j + 1 || i == j - 1 || i == j - 4 || i == j + 4;
    }


    private void swap(int i, int j) {
        int temp = replaces[i];
        replaces[i] = replaces[j];
        replaces[j] = temp;
    }

    private void changePanel(int i, int j) {
        ImageIcon imageIcon = (ImageIcon) buttons[i].getIcon();
        buttons[i].setIcon(null);
        buttons[i].setVisible(false);

        buttons[j].setIcon(imageIcon);
        buttons[j].setVisible(true);
    }


    public void randomize() {
        for (int i = 0; i < 1000; ++i) {
            int index = new Random().nextInt(15);
            int n = Integer.parseInt(buttons[index].getName());
            int j = 0;

            while (replaces[j] != 15)
                j++;

            if (canMove(n, j)) {
                swap(n, j);
                changePanel(n, j);
            }
        }
    }

    private void playAudio() {

        if (myFrame.musicSwitcher) {
            try {
                InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("./MediaFiles/pops.wav")));
                AudioStream audioStream = new AudioStream(inputStream);
                AudioPlayer.player.start(audioStream);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private int[] initialiseNumbers() {
        replaces = new int[16];
        int counter = 0;
        for (int i = 0; i < 16; ++i) {
            replaces[i] = counter;
            ++counter;
        }

        return replaces;
    }


}
