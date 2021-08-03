package UserUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InformationPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    //Image section
    JLabel imageDisplay, nameOfStudent;

    //Information section
        //+JLabel display information
        JLabel idLabel, nameLabel;

        //+JComboBox display day of birth
        JComboBox day,month,year;

        //+Button update
        JButton btnUpdate;
    public InformationPanel(HomeUserPanel card) {
        cardLayout = card.getCardLayout();
        cardPanel = card.getCardPanel();
        cardPanel.add(this,HomeUserPanel.INFORMATIONCARD);
        setLayout(new BorderLayout());
        JLabel northLabel = new JLabel("Information Student", SwingConstants.CENTER);
        northLabel.setFont(new Font("SansSerif",Font.ITALIC, 40));
        northLabel.setBorder(new EmptyBorder(10,0,10,0));
        northLabel.setForeground(Color.BLACK);
        add(northLabel,BorderLayout.NORTH);

        JPanel belowLabel = new JPanel();
        belowLabel.setBorder(new EmptyBorder(10,20,20,20));
        belowLabel.setLayout(new GridLayout(2,0));
            JPanel containImageOfStudent = new JPanel();
            containImageOfStudent.setLayout(new GridLayout(0,3));
                JPanel imageBox1 = new JPanel();
                containImageOfStudent.add(imageBox1);
                JPanel imageBox2 = new JPanel();
                imageBox2.setLayout(new BorderLayout());
                imageDisplay = new JLabel("Don't have image :(, please update.", SwingConstants.CENTER);
                imageDisplay.setBorder(BorderFactory.createLineBorder(Color.blue));
                imageBox2.add(imageDisplay,BorderLayout.CENTER);
                nameOfStudent = new JLabel("Don't have name :(, please update.", SwingConstants.CENTER);
                nameOfStudent.setBorder(new EmptyBorder(10,0,0,0));
                imageBox2.add(nameOfStudent, BorderLayout.SOUTH);
                containImageOfStudent.add(imageBox2);
                JPanel imageBox3 = new JPanel();
                containImageOfStudent.add(imageBox3);
            belowLabel.add(containImageOfStudent);
            JPanel containInformationOfStudent = new JPanel();
            containInformationOfStudent.setLayout(new GridLayout(0,3));
                JPanel containInforBox1 = new JPanel();
                containInformationOfStudent.add(containInforBox1);
                JPanel containInforBox2 = new JPanel();
                containInforBox2.setLayout(new GridLayout(4,0));
                    //row1
                    JPanel containIDStudent = new JPanel();
                    containIDStudent.setLayout(new GridLayout(0,2));
                    containIDStudent.add(new JLabel("ID Student: ", SwingConstants.RIGHT));
                    JPanel containIdLabel = new JPanel();
                    containIdLabel.setLayout(new GridLayout(3,0));
                        JPanel boxContainIDLabel1 = new JPanel();
                        containIdLabel.add(boxContainIDLabel1);
                        JPanel boxContainIDLabel2 = new JPanel();
                        boxContainIDLabel2.setLayout(new BorderLayout());
                        idLabel = new JLabel("Not specific", SwingConstants.LEFT);
                        boxContainIDLabel2.add(idLabel,BorderLayout.CENTER);
                        containIdLabel.add(boxContainIDLabel2);
                        JPanel boxContainIDLabel3 = new JPanel();
                        containIdLabel.add(boxContainIDLabel3);
                        containIDStudent.add(containIdLabel);
                    containInforBox2.add(containIDStudent);

                    //row2
                    JPanel containNameStudent = new JPanel();
                    containNameStudent.setLayout(new GridLayout(0,2));
                    containNameStudent.add(new JLabel("Name Student: ", SwingConstants.RIGHT));
                    JPanel containNameLabel = new JPanel();
                    containNameLabel.setLayout(new GridLayout(3,0));
                        JPanel boxContainNameLabel1 = new JPanel();
                        containNameLabel.add(boxContainNameLabel1);
                        JPanel boxContainNameLabel2 = new JPanel();
                        boxContainNameLabel2.setLayout(new BorderLayout());
                        nameLabel = new JLabel("Not specific", SwingConstants.LEFT);
                        boxContainNameLabel2.add(nameLabel, BorderLayout.CENTER);
                        containNameLabel.add(boxContainNameLabel2);
                        JPanel boxContainNameLabel3 = new JPanel();
                        containNameLabel.add(boxContainNameLabel3);
                        containNameStudent.add(containNameLabel);
                    containInforBox2.add(containNameStudent);

                    //row3
                    JPanel containDayOfBirthStudent = new JPanel();
                    containDayOfBirthStudent.setLayout(new GridLayout(0,2));
                    containDayOfBirthStudent.add(new JLabel("Day of birth: ",SwingConstants.RIGHT));
                    JPanel containDayOfBirthCB = new JPanel();
                    containDayOfBirthCB.setLayout(new GridLayout(0,3));
                        //Col1
                        JPanel containDayCB = new JPanel();
                        containDayCB.setBorder(new EmptyBorder(0,0,0,10));
                        containDayCB.setLayout(new GridLayout(3,0));
                        JPanel boxDay1 = new JPanel();
                        containDayCB.add(boxDay1);
                        JPanel boxDay2 = new JPanel();
                        boxDay2.setLayout(new BorderLayout());
                        boxDay2.add(day = new JComboBox(), BorderLayout.CENTER);
                        day.setEnabled(false);
                        containDayCB.add(boxDay2);
                        JPanel boxDay3 = new JPanel();
                        containDayCB.add(boxDay3);
                        containDayOfBirthCB.add(containDayCB);

                        //Col2
                        JPanel containMonthCB = new JPanel();
                        containMonthCB.setBorder(new EmptyBorder(0,0,0,10));
                        containMonthCB.setLayout(new GridLayout(3,0));
                        JPanel boxMonth1 = new JPanel();
                        containMonthCB.add(boxMonth1);
                        JPanel boxMonth2 = new JPanel();
                        boxMonth2.setLayout(new BorderLayout());
                        boxMonth2.add(month = new JComboBox(), BorderLayout.CENTER);
                        month.setEnabled(false);
                        containMonthCB.add(boxMonth2);
                        JPanel boxMonth3 = new JPanel();
                        containMonthCB.add(boxMonth3);
                        containDayOfBirthCB.add(containMonthCB);

                        //Col3
                        JPanel containYearCB = new JPanel();
                        containYearCB.setLayout(new GridLayout(3,0));
                        JPanel boxYear1 = new JPanel();
                        containYearCB.add(boxYear1);
                        JPanel boxYear2 = new JPanel();
                        boxYear2.setLayout(new BorderLayout());
                        boxYear2.add(year=new JComboBox(), BorderLayout.CENTER);
                        year.setEnabled(false);
                        containYearCB.add(boxYear2);
                        JPanel boxYear3 = new JPanel();
                        containYearCB.add(boxYear3);
                        containDayOfBirthCB.add(containYearCB);
                        containDayOfBirthStudent.add(containDayOfBirthCB);
                    containInforBox2.add(containDayOfBirthStudent);

                    //row4
                    JPanel containButton = new JPanel();
                    containButton.setLayout(new GridLayout(0,3));
                        JPanel buttonBox1 = new JPanel();
                        containButton.add(buttonBox1);
                        JPanel buttonBox2 = new JPanel();
                        buttonBox2.setLayout(new BorderLayout());
                        btnUpdate = new JButton("Update");
                        buttonBox2.add(btnUpdate, BorderLayout.CENTER);
                        containButton.add(buttonBox2);
                        JPanel buttonBox3 = new JPanel();
                        containButton.add(buttonBox3);
                    containInforBox2.add(containButton);
                containInformationOfStudent.add(containInforBox2);
                JPanel containInforBox3 = new JPanel();
                containInformationOfStudent.add(containInforBox3);
            belowLabel.add(containInformationOfStudent);
            add(belowLabel, BorderLayout.CENTER);
    }

    public void showInformationPanel() { this.cardLayout.show(cardPanel, HomeUserPanel.INFORMATIONCARD);}

}
