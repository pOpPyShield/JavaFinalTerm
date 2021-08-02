package UserUI;

import CardOfUIMain.CardPanel;
import UI.UIMain;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class HomeUserPanel extends JPanel {
    public static final String NAME = "HomeUserPanel";
    public static final String TITLE = "Student place";
    public static final String NAMEBOOKCARD = "Book panel";
    public static final String BORROWBOOKCARD = "Borrow panel";
    public static final String INFORMATIONCARD = "Information panel";
    private CardPanel card;
    private JFrame main;
    //Left panel
    private JButton exitToLoginPanel, bookBtn, borrowBtn, informationBtn;

    //Finding Book
    private JTextField tfFinding;
    private JButton findButton;

    //JTextField
    JTextField idBook, nameBook, priceBook, nameAuthor, typeBook;

    //JLabel display image
    JLabel placeDisplayImage;
    //Table in Book
    JTable jt;
    String[] column = {"IDBook","Name of book","Price","Name author","Type"};

    //JComboBox
    String[] filterString = {"Name", "Author", "Price", "Type"};
    JComboBox cb;

    //JPanel cardLayout
    JPanel cardPanel;
    CardLayout cardLayout;

    //Borrow panel depend on Jpanel card layout
    BorrowPanel componentBorrowBookPanel;
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
                bookBtn.setActionCommand(NAMEBOOKCARD);
                bookBtn.addActionListener(new ButtonActionListener());
                bookBtn.setPreferredSize(dimensionForButton);
                ImageIcon iconOfBookBtn = new ImageIcon("/home/huygrogbro/IdeaProjects/FinalTerm/src/Pictures/Book.png");
                bookBtn.setIcon(iconOfBookBtn);
                bookBtn.setBackground(colorOfButton);
                containBookBtn.add(bookBtn);
                secondRowOfLeftPanel.add(containBookBtn);

                JPanel containBorrowBookBtn = new JPanel();
                containBorrowBookBtn.setBackground(colorForAllLeftJPanel);
                borrowBtn = new JButton("BORROW BOOK");
                borrowBtn.setActionCommand(BORROWBOOKCARD);
                borrowBtn.addActionListener(new ButtonActionListener());
                borrowBtn.setPreferredSize(dimensionForButton);
                ImageIcon iconOfBorrowBookBtn = new ImageIcon("/home/huygrogbro/IdeaProjects/FinalTerm/src/Pictures/BorrowBook.png");
                borrowBtn.setIcon(iconOfBorrowBookBtn);
                borrowBtn.setBackground(colorOfButton);
                containBorrowBookBtn.add(borrowBtn);
                secondRowOfLeftPanel.add(containBorrowBookBtn);

                JPanel containInformation = new JPanel();
                containInformation.setBackground(colorForAllLeftJPanel);
                informationBtn = new JButton("INFORMATION");
                informationBtn.setActionCommand(INFORMATIONCARD);
                informationBtn.addActionListener(new ButtonActionListener());
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

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BorderLayout());
            JLabel titleOFBook = new JLabel("Book information", SwingConstants.CENTER);
            titleOFBook.setFont(new Font("SansSerif", Font.ITALIC,40));
            titleOFBook.setBorder(new EmptyBorder(10,0,10,0));
            titleOFBook.setForeground(Color.BLACK);
            bookPanel.add(titleOFBook, BorderLayout.NORTH);

            JPanel belowLabel = new JPanel();
            belowLabel.setBorder(new EmptyBorder(10,20,20,20));
            belowLabel.setLayout(new BorderLayout());
                JPanel northOfBelowLabel = new JPanel();
                northOfBelowLabel.setLayout(new GridLayout(0,3));
                    JPanel firstColOfNorth = new JPanel();
                    northOfBelowLabel.add(firstColOfNorth);

                    JPanel secondColOfNorth = new JPanel();
                    secondColOfNorth.setLayout(new BorderLayout());
                    tfFinding = new JTextField(20);
                    tfFinding.setFont(new Font("Serif",Font.ITALIC,16));
                    tfFinding.setPreferredSize(new Dimension(0,30));
                    secondColOfNorth.add(tfFinding, BorderLayout.CENTER);
                    northOfBelowLabel.add(secondColOfNorth);

                    JPanel thirdColOFNorth = new JPanel();
                    thirdColOFNorth.setLayout(new BorderLayout());
                    findButton = new JButton("FIND");
                    thirdColOFNorth.add(findButton, BorderLayout.LINE_START);
                    JPanel sureFindButton = new JPanel();
                    thirdColOFNorth.add(sureFindButton, BorderLayout.CENTER);
                    thirdColOFNorth.setBackground(Color.BLUE);
                    northOfBelowLabel.add(thirdColOFNorth);
                belowLabel.add(northOfBelowLabel, BorderLayout.NORTH);

                JPanel belowOfFindField = new JPanel();
                belowOfFindField.setBorder(new EmptyBorder(10,0,0,0));
                belowOfFindField.setLayout(new GridLayout(2,0));

                    JPanel firstRowOfField = new JPanel();
                    firstRowOfField.setLayout(new GridLayout(0,2));
                        //First col
                        JPanel colDisplayInformation = new JPanel();
                        colDisplayInformation.setLayout(new GridLayout(5,0));
                            JPanel containIdBook = new JPanel();
                            containIdBook.setLayout(new GridLayout(0,2));
                            containIdBook.add(new JLabel("Id Book: ",SwingConstants.RIGHT));
                            JPanel containJTextIDBook = new JPanel();
                            containJTextIDBook.setLayout(new GridLayout(3,0));
                            JPanel boxId1 = new JPanel();
                            containJTextIDBook.add(boxId1);
                            idBook = new JTextField(20);
                            idBook.setEditable(false);
                            containJTextIDBook.add(idBook);
                            JPanel boxId2 = new JPanel();
                            containJTextIDBook.add(boxId2);
                            containIdBook.add(containJTextIDBook);
                            colDisplayInformation.add(containIdBook);

                            JPanel containNameBook = new JPanel();
                            containNameBook.setLayout(new GridLayout(0,2));
                            containNameBook.add(new JLabel("Name Book: ", SwingConstants.RIGHT));
                            JPanel containJTextNameBook = new JPanel();
                            containJTextNameBook.setLayout(new GridLayout(3,0));
                            JPanel boxName1 = new JPanel();
                            containJTextNameBook.add(boxName1);
                            nameBook = new JTextField(20);
                            nameBook.setEditable(false);
                            containJTextNameBook.add(nameBook);
                            JPanel boxName2 = new JPanel();
                            containJTextNameBook.add(boxName2);
                            containNameBook.add(containJTextNameBook);
                            colDisplayInformation.add(containNameBook);

                            JPanel containPriceBook = new JPanel();
                            containPriceBook.setLayout(new GridLayout(0,2));
                            containPriceBook.add(new JLabel("Price Book: ", SwingConstants.RIGHT));
                            JPanel containJTextPriceBook = new JPanel();
                            containJTextPriceBook.setLayout(new GridLayout(3,0));
                            JPanel boxPrice1 = new JPanel();
                            containJTextPriceBook.add(boxPrice1);
                            priceBook = new JTextField(20);
                            priceBook.setEditable(false);
                            containJTextPriceBook.add(priceBook);
                            JPanel boxPrice2 = new JPanel();
                            containJTextPriceBook.add(boxPrice2);
                            containPriceBook.add(containJTextPriceBook);
                            colDisplayInformation.add(containPriceBook);

                            JPanel containAuthorNameBook = new JPanel();
                            containAuthorNameBook.setLayout(new GridLayout(0,2));
                            containAuthorNameBook.add(new JLabel("Name Author: ", SwingConstants.RIGHT));
                            JPanel containJTextAuthorName = new JPanel();
                            containJTextAuthorName.setLayout(new GridLayout(3,0));
                            JPanel boxAuthorName1 = new JPanel();
                            containJTextAuthorName.add(boxAuthorName1);
                            nameAuthor = new JTextField(20);
                            nameAuthor.setEditable(false);
                            containJTextAuthorName.add(nameAuthor);
                            JPanel boxAuthorName2 = new JPanel();
                            containJTextAuthorName.add(boxAuthorName2);
                            containAuthorNameBook.add(containJTextAuthorName);
                            colDisplayInformation.add(containAuthorNameBook);

                            JPanel containTypeOfBook = new JPanel();
                            containTypeOfBook.setLayout(new GridLayout(0,2));
                            containTypeOfBook.add(new JLabel("Type Book: ", SwingConstants.RIGHT));
                            JPanel containJTextTypeBook = new JPanel();
                            containJTextTypeBook.setLayout(new GridLayout(3,0));
                            JPanel boxType1 = new JPanel();
                            containJTextTypeBook.add(boxType1);
                            typeBook = new JTextField(20);
                            typeBook.setEditable(false);
                            containJTextTypeBook.add(typeBook);
                            JPanel boxType2 = new JPanel();
                            containJTextTypeBook.add(boxType2);
                            containTypeOfBook.add(containJTextTypeBook);
                            colDisplayInformation.add(containTypeOfBook);
                        firstRowOfField.add(colDisplayInformation);

                        //Second col
                        JPanel colDisplayImageAndBtnBorrow = new JPanel();
                        colDisplayImageAndBtnBorrow.setLayout(new BorderLayout());
                            JPanel containImage = new JPanel();
                            containImage.setBorder(new EmptyBorder(10,100,10,100));
                            containImage.setLayout(new BorderLayout());
                            placeDisplayImage = new JLabel("Not has image here :(", SwingConstants.CENTER);
                            placeDisplayImage.setBorder(BorderFactory.createLineBorder(Color.blue));
                            containImage.add(placeDisplayImage, BorderLayout.CENTER);
                            colDisplayImageAndBtnBorrow.add(containImage, BorderLayout.CENTER);

                            JPanel containButton = new JPanel();
                            containButton.setLayout(new GridLayout(0,3));
                            JPanel row1Contain = new JPanel();
                            containButton.add(row1Contain);
                            JButton borrowBtn = new JButton("Borrow Book");
                            borrowBtn.setPreferredSize(new Dimension(0,40));
                            containButton.add(borrowBtn);
                            JPanel row2Contain = new JPanel();
                            containButton.add(row2Contain);
                            colDisplayImageAndBtnBorrow.add(containButton, BorderLayout.SOUTH);

                        firstRowOfField.add(colDisplayImageAndBtnBorrow);
                    belowOfFindField.add(firstRowOfField);

                    //This is the place contain JTable
                    JPanel secondRowOfField = new JPanel();
                    secondRowOfField.setLayout(new BorderLayout());
                        JPanel comboboxContain = new JPanel();
                        comboboxContain.setLayout(new GridLayout(0,3));
                            JPanel comboBox1 = new JPanel();
                            comboBox1.setLayout(new BorderLayout());
                            comboBox1.add(new JLabel("Filter: ", SwingConstants.CENTER), BorderLayout.LINE_START);
                            cb = new JComboBox(filterString);
                            cb.setSelectedIndex(-1);
                            comboBox1.add(cb,BorderLayout.CENTER);
                            comboboxContain.add(comboBox1);

                            JPanel comboBox2 = new JPanel();
                            comboboxContain.add(comboBox2);

                            JPanel comboBox3 = new JPanel();
                            comboboxContain.add(comboBox3);
                        secondRowOfField.add(comboboxContain, BorderLayout.NORTH);

                        JPanel tableContain = new JPanel();
                        tableContain.setBorder(new EmptyBorder(10,0,0,0));
                        tableContain.setLayout(new BorderLayout());
                        DefaultTableModel testModel = new DefaultTableModel(column,0);
                        jt = new JTable(testModel) {
                            public boolean editCellAt(int row, int column, EventObject a) {return false;}
                        };
                        jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        JScrollPane scrollPane = new JScrollPane(jt);
                        jt.setFillsViewportHeight(true);
                        tableContain.add(scrollPane,BorderLayout.CENTER);
                        secondRowOfField.add(tableContain, BorderLayout.CENTER);
                    belowOfFindField.add(secondRowOfField);

                belowLabel.add(belowOfFindField, BorderLayout.CENTER);

            bookPanel.add(belowLabel, BorderLayout.CENTER);
            cardPanel.add(bookPanel, NAMEBOOKCARD);
            componentBorrowBookPanel = new BorrowPanel(this);

        add(cardPanel, BorderLayout.CENTER);

        card.addCard(this, NAME);
    }
    public JPanel getCardPanel() {
        return this.cardPanel;
    }

    public CardLayout getCardLayout() {
        return this.cardLayout;
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
                case NAMEBOOKCARD:
                    System.out.println(NAMEBOOKCARD);
                    cardLayout.show(cardPanel, NAMEBOOKCARD);
                    break;
                case BORROWBOOKCARD:
                    componentBorrowBookPanel.showBorrowPanel();
                    cb.setSelectedIndex(-1);
                    break;
                case INFORMATIONCARD:
                    System.out.println(INFORMATIONCARD);
                    break;

            }
        }

    }
}
