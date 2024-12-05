package vs.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import vs.App;
import vs.core.candidates.Candidate;
import vs.core.persons.Persons;
import vs.core.votes.Votes;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class CandidateView {
    private JPanel panel;
    private JButton voteButton;
    private JLabel candidateImage;
    private final App app;

    CandidateView(App app, int width) {
        this.app = app;
        panel.setSize(new Dimension(width, -1));
        ImageIcon originalImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/blank.jpg")));
        Image scaledImage = originalImage.getImage().getScaledInstance(width, defaultHeight(originalImage, width), Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(scaledImage);
        voteButton.setText("Votar en Blanco");
        candidateImage.setIcon(imageIcon);
        voteButton.addActionListener(e -> vote());
    }

    CandidateView(App app, Candidate candidate, int width) {
        this.app = app;
        panel.setSize(new Dimension(width, -1));
        ImageIcon originalImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + candidate.list() + ".jpg")));
        Image scaledImage = originalImage.getImage().getScaledInstance(width, defaultHeight(originalImage, width), Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(scaledImage);
        candidateImage.setIcon(imageIcon);
        voteButton.addActionListener(e -> vote(candidate));
    }

    private void vote(Candidate candidate) {
        int r = JOptionPane.showConfirmDialog(null, candidate.politicalParty(),
                "Confirmar voto a: ", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION)
            return;
        try {
            Votes.voteCandidate(candidate);
            Persons.setVoted(app.getCurrentPerson());
            app.switchTo("validating");
        } catch (SQLException | ClassNotFoundException e) {
            new ErrorView(e);
        }
    }

    private void vote() {
        int r = JOptionPane.showConfirmDialog(null, "Voto en blanco",
                "Confirmar voto: ", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION)
            return;
        try {
            Votes.voteBlank();
            Persons.setVoted(app.getCurrentPerson());
            app.switchTo("validating");
        } catch (SQLException | ClassNotFoundException e) {
            new ErrorView(e);
        }
    }

    private int defaultHeight(ImageIcon originalIcon, int desiredWidth) {
        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();
        return (originalHeight * desiredWidth) / originalWidth;
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
        panel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel.setOpaque(false);
        candidateImage = new JLabel();
        candidateImage.setIcon(new ImageIcon(getClass().getResource("/blank.jpg")));
        candidateImage.setText("");
        panel.add(candidateImage, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        voteButton = new JButton();
        voteButton.setText("Votar");
        panel.add(voteButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel.add(spacer2, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel.add(spacer3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}