package vs.gui;

import vs.App;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Window extends JFrame {
    private final JPanel panel;

    public Window() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (gd.isFullScreenSupported())
            gd.setFullScreenWindow(this);

        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 24));
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 24));
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 24));
        UIManager.put("PasswordField.font", new Font("Arial", Font.PLAIN, 24));

        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(App.class.getResource("/background.jpg")));

        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel = new JPanel(new CardLayout());
        panel.setOpaque(false);

        backgroundPanel.add(panel, BorderLayout.CENTER);

        setContentPane(backgroundPanel);
        setVisible(true);
    }

    public void addView(JPanel view, String name) {
        panel.add(view, name);
    }

    public void show(String name) {
        if (panel.getLayout() instanceof CardLayout cardLayout)
            cardLayout.show(panel, name);
    }
}
