package UserUI;

import CardOfUIMain.CardPanel;
import UI.UIMain;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeUserPanel extends JPanel {
    public static final String NAME = "HomeUserPanel";
    public static final String TITLE = "Student place";
    private CardPanel card;
    private JFrame main;
    //Left panel
    private JButton exitToLoginPanel, bookBtn, borrowBtn, informationBtn;
    public HomeUserPanel(UIMain main, CardPanel card) {
        this.card = card;
        this.main = main;
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        //JLabel center
        JLabel studentPlace = new JLabel("Student Place",SwingConstants.CENTER);
        studentPlace.setBorder(new EmptyBorder(50,0,50,0));
        studentPlace.setForeground(Color.WHITE);
        studentPlace.setFont(new Font("SansSerif", Font.BOLD, 30));
        Color topColor = new Color(250,148,32);
        topPanel.setBackground(topColor);
        topPanel.add(studentPlace, BorderLayout.CENTER);
        /*
        exitToLoginPanel = new JButton("Exit");
        exitToLoginPanel.setActionCommand("Exit");
        exitToLoginPanel.addActionListener(new ButtonActionListener());
        topPanel.add(exitToLoginPanel);
         */
        add(topPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        Color colorForAllLeftJPanel = new Color(58,65,69);
        leftPanel.setBackground(colorForAllLeftJPanel);
        leftPanel.setPreferredSize(new Dimension(300,0));
        leftPanel.setLayout(new GridLayout(3,0));
            JPanel firstRowOfLeftPanel = new JPanel();
            firstRowOfLeftPanel.setBackground(colorForAllLeftJPanel);
            leftPanel.add(firstRowOfLeftPanel);


            JPanel secondRowOfLeftPanel = new JPanel();
            secondRowOfLeftPanel.setBackground(colorForAllLeftJPanel);
            secondRowOfLeftPanel.setLayout(new GridLayout(3,0,10,0));
                Dimension dimensionForButton = new Dimension(200,70);
                Color colorOfButton = Color.WHITE;
                JPanel containBookBtn = new JPanel();
                containBookBtn.setBackground(colorForAllLeftJPanel);
                bookBtn = new JButton("BOOK");
                bookBtn.setPreferredSize(dimensionForButton);
                ImageIcon iconOfBookBtn = new ImageIcon("/home/huygrogbro/IdeaProjects/FinalTerm/src/Pictures/Book.png");
                bookBtn.setIcon(iconOfBookBtn);
                bookBtn.setBackground(colorOfButton);
                containBookBtn.add(bookBtn);
                secondRowOfLeftPanel.add(containBookBtn);

                JPanel containBorrowBookBtn = new JPanel();
                containBorrowBookBtn.setBackground(colorForAllLeftJPanel);
                borrowBtn = new JButton("BORROW BOOK");
                borrowBtn.setPreferredSize(dimensionForButton);
                ImageIcon iconOfBorrowBookBtn = new ImageIcon("/home/huygrogbro/IdeaProjects/FinalTerm/src/Pictures/BorrowBook.png");
                borrowBtn.setIcon(iconOfBorrowBookBtn);
                borrowBtn.setBackground(colorOfButton);
                containBorrowBookBtn.add(borrowBtn);
                secondRowOfLeftPanel.add(containBorrowBookBtn);

                JPanel containInformation = new JPanel();
                containInformation.setBackground(colorForAllLeftJPanel);
                informationBtn = new JButton("INFORMATION");
                informationBtn.setPreferredSize(dimensionForButton);
                ImageIcon iconOfInfomationBTN = new ImageIcon("/home/huygrogbro/IdeaProjects/FinalTerm/src/Pictures/InformationStudent.png");
                informationBtn.setIcon(iconOfInfomationBTN);
                informationBtn.setBackground(colorOfButton);
                containInformation.add(informationBtn);
                secondRowOfLeftPanel.add(containInformation);

            leftPanel.add(secondRowOfLeftPanel);

            JPanel thirdRowOfLeftPanel = new JPanel();
            thirdRowOfLeftPanel.setBackground(colorForAllLeftJPanel);
            thirdRowOfLeftPanel.setLayout(new GridLayout(3,0,10,0));

                JPanel firstRowOfThird = new JPanel();
                firstRowOfThird.setBackground(colorForAllLeftJPanel);
                thirdRowOfLeftPanel.add(firstRowOfThird);

                JPanel secondRowOfThird = new JPanel();
                secondRowOfThird.setBackground(colorForAllLeftJPanel);
                exitToLoginPanel = new JButton("Exit");
                exitToLoginPanel.setActionCommand("Exit");
                exitToLoginPanel.addActionListener(new ButtonActionListener());
                exitToLoginPanel.setBackground(topColor);
                exitToLoginPanel.setPreferredSize(new Dimension(250,70));
                secondRowOfThird.add(exitToLoginPanel);
                thirdRowOfLeftPanel.add(secondRowOfThird);

                JPanel thridRowOfThird = new JPanel();
                thridRowOfThird.setBackground(colorForAllLeftJPanel);
                thirdRowOfLeftPanel.add(thridRowOfThird);
            leftPanel.add(thirdRowOfLeftPanel);
        add(leftPanel, BorderLayout.LINE_START);

        JPanel centerPanel = new JPanel();
        add(centerPanel, BorderLayout.CENTER);

        card.addCard(this, NAME);
    }

    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButton button = (JButton) actionEvent.getSource();
            switch (button.getActionCommand()) {
                case "Exit":
                    main.setSize(UIMain.WIDTH, UIMain.HEIGHT);
                    main.setTitle(UIMain.TITLE);
                    main.repaint();
                    main.setLocationRelativeTo(null);
                    card.showCard(UIMain.NAMELOGIN);
                    break;
            }
        }

    }
}
