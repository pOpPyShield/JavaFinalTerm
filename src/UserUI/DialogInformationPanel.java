package UserUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DialogInformationPanel extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;
    private boolean checkInformation = true;


    public DialogInformationPanel() {
        setTitle("Information student");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(0,2));
            JPanel leftCol = new JPanel();
            leftCol.setBorder(new EmptyBorder(10,10,10,10));
            leftCol.setLayout(new GridLayout(5,0));
                leftCol.add(new JLabel());
                leftCol.add(new JLabel());
                JPanel firstRowContainTextField = new JPanel();
                firstRowContainTextField.setLayout(new GridLayout(2,0));
                    JPanel rowOfFirst = new JPanel();
                    rowOfFirst.setLayout(new GridLayout(0,2));
                        JPanel rowOfNameLabel = new JPanel();
                        rowOfNameLabel.setLayout(new GridLayout(2,0));
                        rowOfNameLabel.add(new JLabel("Name: ", SwingConstants.RIGHT));
                        rowOfNameLabel.add(new JLabel());
                        rowOfFirst.add(rowOfNameLabel);

                        JPanel rowOfNameField = new JPanel();
                        rowOfNameField.setLayout(new GridLayout(2,0));
                        rowOfNameField.add(new JTextField(20));
                        rowOfNameField.add(new JLabel());
                        rowOfFirst.add(rowOfNameField);
                    firstRowContainTextField.add(rowOfFirst);

                    JPanel rowOfSecond = new JPanel();
                    rowOfSecond.setLayout(new GridLayout(0,2));
                        JPanel rowOfDateOfBirthLabel = new JPanel();
                        rowOfDateOfBirthLabel.setLayout(new GridLayout(2,0));
                        rowOfDateOfBirthLabel.add(new JLabel("Day of birth: ", SwingConstants.RIGHT));
                        rowOfDateOfBirthLabel.add(new JLabel());
                        rowOfSecond.add(rowOfDateOfBirthLabel);

                        JPanel rowOfComboBox = new JPanel();
                        rowOfComboBox.setLayout(new GridLayout(2,0));

                        rowOfSecond.add(rowOfComboBox);
                    firstRowContainTextField.add(rowOfSecond);
                leftCol.add(firstRowContainTextField);
                JPanel secondRowContainButton = new JPanel();
                secondRowContainButton.setBorder(BorderFactory.createLineBorder(Color.blue));
                leftCol.add(secondRowContainButton);
                leftCol.add(new JLabel());
            add(leftCol);
            JPanel rightCol = new JPanel();
            add(rightCol);
        setDefaultLookAndFeelDecorated(true);
        setVisible(true);
    }
}
