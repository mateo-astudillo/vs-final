package vs.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import vs.App;
import vs.core.incidences.Incidence;
import vs.core.incidences.Incidences;
import vs.core.persons.Persons;
import vs.core.votingTable.VotingTable;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Locale;

public class Validating {
    private final App app;
    private JPanel panel;
    private JTextField documentField;
    private JButton validateButton;
    private JButton closeVotingTableButton;

    public Validating(App app) {
        this.app = app;
        documentField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (documentField.getText().length() > 8)
                    e.consume();
                char c = e.getKeyChar();
                if (!Character.isDigit(c))
                    e.consume();
            }
        });
        validateButton.addActionListener(e -> validate(app));
        closeVotingTableButton.addActionListener(e -> closeVotingTable());
    }

    private void closeVotingTable() {
        int r = JOptionPane.showConfirmDialog(null, "Confirmar",
                "Cierre de mesa", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION)
            return;
        try {
            long d = Duration.between(LocalTime.of(18, 0), LocalTime.now()).toMinutes();
            if (d < 10) {
                new ErrorView("Todavía no es hora del cierre de mesa");
                Incidences.register(app.getCurrentUser().id(), new Incidence("Cierre de mesa", "intento de cierre de mesa temprano"));
            }
            VotingTable.close(app.getCurrentUser().id(), app.getRealOpeningTime());
            app.loadChar();
            app.switchTo("results");
        } catch (SQLException | ClassNotFoundException e) {
            new ErrorView(e);
        }
    }

    private void validate(App app) {
        try {
            int document = Integer.parseInt(documentField.getText());
            if (!Persons.validate(document)) {
                new ErrorView("No se encontró ninguna persona con el documento: " + document);
                return;
            }
            if (Persons.hasBeenCancelled(document)) {
                new ErrorView("La persona con el documento: " + document + " ha sido anulada");
                return;
            }
            if (Persons.hasVoted(document)) {
                new ErrorView("La persona con el documento: " + document + " ya ha votado");
                return;
            }
            app.setCurrentPerson(Persons.get(document));
            app.switchTo("person");
            clear();
        } catch (NumberFormatException numberFormatException) {
            new ErrorView("Debe ingresar un DNI válido");
        } catch (SQLException | ClassNotFoundException e) {
            new ErrorView(e);
        }
    }

    private void clear() {
        documentField.setText("");
    }

    public JPanel getPanel() {
        return panel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel.setEnabled(true);
        panel.setOpaque(false);
        final JLabel label1 = new JLabel();
        label1.setText("Documento");
        panel.add(label1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel.add(spacer2, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel.add(spacer3, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel.add(spacer4, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        documentField = new JTextField();
        panel.add(documentField, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        validateButton = new JButton();
        validateButton.setText("Validar");
        panel.add(validateButton, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        closeVotingTableButton = new JButton();
        Font closeVotingTableButtonFont = this.$$$getFont$$$(null, -1, 28, closeVotingTableButton.getFont());
        if (closeVotingTableButtonFont != null) closeVotingTableButton.setFont(closeVotingTableButtonFont);
        closeVotingTableButton.setText("Cerrar Mesa");
        panel.add(closeVotingTableButton, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel.add(spacer5, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 50), new Dimension(-1, 20), 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
