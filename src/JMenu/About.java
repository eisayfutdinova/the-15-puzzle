package JMenu;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {
    public About() {
        super("Разработчик");
        setBounds(600, 100, 600, 300);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        ImageIcon image = new ImageIcon("./MediaFiles/About.jpg");
        JLabel label = new JLabel("", image, JLabel.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        add(panel);
    }
}
