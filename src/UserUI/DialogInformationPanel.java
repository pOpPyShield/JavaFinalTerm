package UserUI;

import DB.DBConnect;
import JFileChooserCustom.ImageFileView;
import JFileChooserCustom.ImageFilter;
import JFileChooserCustom.ImagePreview;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class DialogInformationPanel extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;
    private boolean checkInformation = true;

    //Image
    JLabel displayImage;
    JButton uploadBtn;

    JTextField name;
    JComboBox day, month, year;
    String[] dayData, monthData, yearData;
    JButton okBtn, cancelBtn;

    //DB
    DBConnect workingWithDB;
    Connection getConnect;
    PreparedStatement pstmt;

    //JFileChooser
    private JFileChooser fc;
    private File imgFile;
    private InputStream imageDisplay;
    private Blob imageInsertSQL;

    //Id user
    int idUser;
    public DialogInformationPanel(int idUser) {
        this.workingWithDB = new DBConnect(3306,"javafinalimportant","adminjava","Admin1234@");
        getConnect = this.workingWithDB.getConn();
        this.idUser = idUser;
        createThreeDataInCBBox();
        setTitle("Information student");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(0, 2));
        JPanel leftCol = new JPanel();
        leftCol.setBorder(new EmptyBorder(10, 10, 10, 10));
        leftCol.setLayout(new GridLayout(5, 0));
        leftCol.add(new JLabel());
        leftCol.add(new JLabel());
        JPanel firstRowContainTextField = new JPanel();
        firstRowContainTextField.setLayout(new GridLayout(2, 0));
        JPanel rowOfFirst = new JPanel();
        rowOfFirst.setLayout(new GridLayout(0, 2));
        JPanel rowOfNameLabel = new JPanel();
        rowOfNameLabel.setLayout(new GridLayout(2, 0));
        rowOfNameLabel.add(new JLabel("Name: ", SwingConstants.RIGHT));
        rowOfNameLabel.add(new JLabel());
        rowOfFirst.add(rowOfNameLabel);

        JPanel rowOfNameField = new JPanel();
        rowOfNameField.setLayout(new GridLayout(2, 0));
        rowOfNameField.add(name = new JTextField(20));
        rowOfNameField.add(new JLabel());
        rowOfFirst.add(rowOfNameField);
        firstRowContainTextField.add(rowOfFirst);

        JPanel rowOfSecond = new JPanel();
        rowOfSecond.setLayout(new GridLayout(0, 2));
        JPanel rowOfDateOfBirthLabel = new JPanel();
        rowOfDateOfBirthLabel.setLayout(new GridLayout(2, 0));
        rowOfDateOfBirthLabel.add(new JLabel("Day of birth: ", SwingConstants.RIGHT));
        rowOfDateOfBirthLabel.add(new JLabel());
        rowOfSecond.add(rowOfDateOfBirthLabel);

        JPanel rowOfComboBox = new JPanel();
        rowOfComboBox.setLayout(new GridLayout(2, 0));
        JPanel containCbBox = new JPanel();
        containCbBox.setLayout(new GridLayout(0, 3));
        containCbBox.add(day = new JComboBox(dayData));
        day.setSelectedIndex(-1);
        day.setActionCommand("Day");
        day.addActionListener(new ButtonActionListener());
        containCbBox.add(month = new JComboBox(monthData));
        month.setSelectedIndex(-1);
        month.setActionCommand("Month");
        month.addActionListener(new ButtonActionListener());
        containCbBox.add(year = new JComboBox(yearData));
        year.setSelectedIndex(-1);
        year.setActionCommand("Year");
        year.addActionListener(new ButtonActionListener());
        rowOfComboBox.add(containCbBox);
        rowOfComboBox.add(new JLabel());
        rowOfSecond.add(rowOfComboBox);
        firstRowContainTextField.add(rowOfSecond);
        leftCol.add(firstRowContainTextField);
        JPanel secondRowContainButton = new JPanel();
        secondRowContainButton.setLayout(new GridLayout(2,0));
            JPanel containButtons = new JPanel();
            containButtons.setLayout(new GridLayout(0,2,0,10));
                containButtons.add(okBtn = new JButton("OK"));
                okBtn.setActionCommand("Update infor");
                okBtn.addActionListener(new ButtonActionListener());
                containButtons.add(cancelBtn = new JButton("Cancel"));
                cancelBtn.setActionCommand("Cancel");
                cancelBtn.addActionListener(new ButtonActionListener());
            secondRowContainButton.add(containButtons);
            secondRowContainButton.add(new JLabel());
        leftCol.add(secondRowContainButton);
        leftCol.add(new JLabel());
        add(leftCol);

        JPanel rightCol = new JPanel();
        rightCol.setLayout(new BorderLayout());
        rightCol.add(displayImage = new JLabel("Don't have image here", SwingConstants.CENTER), BorderLayout.CENTER);
        displayImage.setBorder(BorderFactory.createLineBorder(Color.blue));
        JPanel bottomContainUploadBtn = new JPanel();
        bottomContainUploadBtn.setLayout(new GridLayout(0,3));
            bottomContainUploadBtn.add(new JLabel());
            JPanel panelContainButton = new JPanel();
            panelContainButton.setLayout(new BorderLayout());
                panelContainButton.add(new JLabel(), BorderLayout.NORTH);
                panelContainButton.add(uploadBtn = new JButton("Upload"));
                uploadBtn.setActionCommand("Upload");
                uploadBtn.addActionListener(new ButtonActionListener());
                panelContainButton.add(new JLabel(), BorderLayout.SOUTH);
            bottomContainUploadBtn.add(panelContainButton);
            bottomContainUploadBtn.add(new JLabel());
        rightCol.add(bottomContainUploadBtn, BorderLayout.SOUTH);
        add(rightCol);
        setDefaultLookAndFeelDecorated(true);
        setVisible(true);
    }

    public class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() instanceof JButton) {
                JButton btn = (JButton) actionEvent.getSource();
                switch (btn.getActionCommand()) {
                    case "Update infor":
                        if(validateField()) {
                            addInformation();
                            JOptionPane.showMessageDialog(getParent(), "Update success.");
                            dispose();
                        }
                        break;
                    case "Cancel":
                        dispose();
                        break;
                    case "Upload":
                        displayChooseFile();
                        break;

                }
            }
        }
        private boolean validateField() {
            if (name.getText().isEmpty()) {
                JOptionPane.showMessageDialog(getParent(), "Enter name.");
                return false;
            } else if (day.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(getParent(), "Choose day.");
                return false;
            } else if (month.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(getParent(), "Choose month.");
                return false;
            } else if (year.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(getParent(), "Choose year");
                return false;
            } else if(displayImage.getIcon() == null){
                JOptionPane.showMessageDialog(getParent(), "Choose a img.");
                return false;
            }else if (name.getText().isEmpty() && day.getSelectedIndex() == -1 && month.getSelectedIndex() == -1 && year.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(getParent(), "Please input all the field.");
                return false;
            } else {
                return true;
            }
        }

        private void addInformation() {
            Date dateOfBirth = new Date();
            dateOfBirth.setDate(Integer.parseInt(day.getSelectedItem().toString()));
            dateOfBirth.setMonth(Integer.parseInt(month.getSelectedItem().toString()) - 1);
            dateOfBirth.setYear(Integer.parseInt(year.getSelectedItem().toString()) - 1900);
            System.out.println(idUser);
            try {
                pstmt = getConnect.prepareStatement("INSERT INTO UserInfor(IDUser,DateOfBirth,Name) VALUES (?,?,?)");
                pstmt.setInt(1, idUser);
                java.sql.Date dateInsertToSQL = new java.sql.Date(dateOfBirth.getTime());
                pstmt.setDate(2,dateInsertToSQL);
                pstmt.setString(3, name.getText());
                pstmt.executeUpdate();
            } catch (Exception ex) { ex.printStackTrace();}

            try {
                pstmt = getConnect.prepareStatement("INSERT INTO ImageUser(IDUser, ImageUser) VALUES (?,?)");
                pstmt.setInt(1,idUser);
                pstmt.setBlob(2, imageInsertSQL);
                pstmt.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        private void displayChooseFile() {
            if(fc == null) {
                fc = new JFileChooser();
                //Add a custom file filter and disable the default
                //(Accept All) file filter.
                fc.addChoosableFileFilter(new ImageFilter());
                fc.setAcceptAllFileFilterUsed(false);

                //Add custom icons for file types.
                fc.setFileView(new ImageFileView());

                //Add the preview pane.
                fc.setAccessory(new ImagePreview(fc));
            }
            //Show it.
            int returnVal = fc.showDialog(getParent(),
                    "Attach");
            //Process the results.
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                imgFile = fc.getSelectedFile();
                try {
                    imageDisplay = new FileInputStream(imgFile);
                } catch (Exception ex) {ex.printStackTrace();}
                try {
                    imageInsertSQL = new SerialBlob(Files.readAllBytes(imgFile.toPath()));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedImage img = null;
                try {
                    img = ImageIO.read(imgFile);
                }catch (Exception e) {e.printStackTrace();}
                ImageIcon imageIcon = new ImageIcon(fitImage(img,displayImage.getWidth(),displayImage.getHeight()));
                displayImage.setIcon(imageIcon);
            } else {
                System.out.println("Attachment cancelled by user.");
            }
            //Reset the file chooser for the next time it's shown.
            fc.setSelectedFile(null);
        }

        private Image fitImage(Image img, int w, int h) {
            BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = resizedImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img, 0, 0, w, h, null);
            g2.dispose();
            return resizedImage;
        }
    }


    public void createThreeDataInCBBox() {
        this.dayData = new String[31];
        this.monthData = new String[12];
        this.yearData = new String[71];
        for (int i = 0; i <= 30; i++) {
                dayData[i] =String.valueOf(i + 1);
        }

        for (int i = 0; i <= 11; i++) {
            monthData[i] = String.valueOf(i + 1);
        }
        for (int i = 0; i <= 70; i++) {
            yearData[i] = String.valueOf(1950 + i + 1);
        }


    }
}