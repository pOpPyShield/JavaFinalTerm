package UserUI;

import CardOfUIMain.CardPanel;
import DB.DBConnect;
import ManagerUI.ManagerUI;
import ObjectZZ.Author;
import ObjectZZ.Book;
import TreeModelCustom.VstTableItemModel;
import UI.UIMain;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;

public class HomeUserPanel extends JPanel {
    public static final String NAME = "HomeUserPanel";
    public static final String TITLE = "Student place";
    public static final String NAMEBOOKCARD = "Book panel";
    public static final String BORROWBOOKCARD = "Borrow panel";
    public static final String INFORMATIONCARD = "Information panel";
    private CardPanel card;
    private UIMain main;
    //Left panel
    private JButton exitToLoginPanel, bookBtn, borrowBtn, informationBtn;

    //Finding Book
    private JTextField tfFinding;
    private JButton findButton;

    //JTextField
    JTextField idBook, nameBook, priceBook, nameAuthor, typeBook;

    //JLabel display image
    JLabel placeDisplayImage;
    private InputStream imageDisplay;
    //Table in Book
    JTable jt;
    String[] column = {"IDBook", "Name of book", "Price", "Name author", "Type"};
    VstTableItemModelUser customModel;
    ArrayList<Book> bookArr;
    TableRowSorter<VstTableItemModelUser> sorter;

    //JComboBox
    String[] filterString = {"Name", "Author", "Price", "Type"};
    JComboBox cb;

    //JPanel cardLayout
    JPanel cardPanel;
    CardLayout cardLayout;

    //Borrow panel depend on Jpanel card layout
    BorrowPanel componentBorrowBookPanel;
    //Information panel depend on JPanel card Layout
    InformationPanel componentInformationPanel;
    //DB Connect
    private DBConnect connectDB;
    private Connection connection;
    private PreparedStatement pstmt;

    //Student
    String userName;
    private int idUser;
    int selectedIndex = -1;
    public HomeUserPanel(UIMain main, CardPanel card) {
        this.connectDB = new DBConnect(3306, "javafinalimportant", "adminjava", "Admin1234@");
        connection = this.connectDB.getConn();
        userName = main.getUserNameTf().getText();
        getIdUser();
        this.card = card;
        this.main = main;
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        //JLabel center
        JLabel studentPlace = new JLabel("Student Place", SwingConstants.CENTER);
        studentPlace.setBorder(new EmptyBorder(50, 0, 50, 0));
        studentPlace.setForeground(Color.WHITE);
        studentPlace.setFont(new Font("SansSerif", Font.BOLD, 30));
        Color topColor = new Color(250, 148, 32);
        topPanel.setBackground(topColor);
        topPanel.add(studentPlace, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        Color colorForAllLeftJPanel = new Color(58, 65, 69);
        leftPanel.setBackground(colorForAllLeftJPanel);
        leftPanel.setPreferredSize(new Dimension(300, 0));
        leftPanel.setLayout(new GridLayout(3, 0));
        JPanel firstRowOfLeftPanel = new JPanel();
        firstRowOfLeftPanel.setBackground(colorForAllLeftJPanel);
        leftPanel.add(firstRowOfLeftPanel);


        JPanel secondRowOfLeftPanel = new JPanel();
        secondRowOfLeftPanel.setBackground(colorForAllLeftJPanel);
        secondRowOfLeftPanel.setLayout(new GridLayout(3, 0, 10, 0));
        Dimension dimensionForButton = new Dimension(200, 70);
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
        thirdRowOfLeftPanel.setLayout(new GridLayout(3, 0, 10, 0));

        JPanel firstRowOfThird = new JPanel();
        firstRowOfThird.setBackground(colorForAllLeftJPanel);
        thirdRowOfLeftPanel.add(firstRowOfThird);

        JPanel secondRowOfThird = new JPanel();
        secondRowOfThird.setBackground(colorForAllLeftJPanel);
        exitToLoginPanel = new JButton("Exit");
        exitToLoginPanel.setActionCommand("Exit");
        exitToLoginPanel.addActionListener(new ButtonActionListener());
        exitToLoginPanel.setBackground(topColor);
        exitToLoginPanel.setPreferredSize(new Dimension(250, 70));
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
        titleOFBook.setFont(new Font("SansSerif", Font.ITALIC, 40));
        titleOFBook.setBorder(new EmptyBorder(10, 0, 10, 0));
        titleOFBook.setForeground(Color.BLACK);
        bookPanel.add(titleOFBook, BorderLayout.NORTH);

        JPanel belowLabel = new JPanel();
        belowLabel.setBorder(new EmptyBorder(10, 20, 20, 20));
        belowLabel.setLayout(new BorderLayout());
        JPanel northOfBelowLabel = new JPanel();
        northOfBelowLabel.setLayout(new GridLayout(0, 3));
        JPanel firstColOfNorth = new JPanel();
        northOfBelowLabel.add(firstColOfNorth);

        JPanel secondColOfNorth = new JPanel();
        secondColOfNorth.setLayout(new BorderLayout());
        tfFinding = new JTextField(20);
        tfFinding.addKeyListener(new KeyListenerFind());
        tfFinding.setFont(new Font("Serif", Font.ITALIC, 16));
        tfFinding.setPreferredSize(new Dimension(0, 30));
        secondColOfNorth.add(tfFinding, BorderLayout.CENTER);
        northOfBelowLabel.add(secondColOfNorth);

        JPanel thirdColOFNorth = new JPanel();
        thirdColOFNorth.setLayout(new BorderLayout());
        thirdColOFNorth.add(new JLabel("Find", SwingConstants.LEFT), BorderLayout.LINE_START);
        JPanel sureFindButton = new JPanel();
        thirdColOFNorth.add(sureFindButton, BorderLayout.CENTER);
        thirdColOFNorth.setBackground(Color.BLUE);
        northOfBelowLabel.add(thirdColOFNorth);
        belowLabel.add(northOfBelowLabel, BorderLayout.NORTH);

        JPanel belowOfFindField = new JPanel();
        belowOfFindField.setBorder(new EmptyBorder(10, 0, 0, 0));
        belowOfFindField.setLayout(new GridLayout(2, 0));

        JPanel firstRowOfField = new JPanel();
        firstRowOfField.setLayout(new GridLayout(0, 2));
        //First col
        JPanel colDisplayInformation = new JPanel();
        colDisplayInformation.setLayout(new GridLayout(5, 0));
        JPanel containIdBook = new JPanel();
        containIdBook.setLayout(new GridLayout(0, 2));
        containIdBook.add(new JLabel("Id Book: ", SwingConstants.RIGHT));
        JPanel containJTextIDBook = new JPanel();
        containJTextIDBook.setLayout(new GridLayout(3, 0));
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
        containNameBook.setLayout(new GridLayout(0, 2));
        containNameBook.add(new JLabel("Name Book: ", SwingConstants.RIGHT));
        JPanel containJTextNameBook = new JPanel();
        containJTextNameBook.setLayout(new GridLayout(3, 0));
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
        containPriceBook.setLayout(new GridLayout(0, 2));
        containPriceBook.add(new JLabel("Price Book: ", SwingConstants.RIGHT));
        JPanel containJTextPriceBook = new JPanel();
        containJTextPriceBook.setLayout(new GridLayout(3, 0));
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
        containAuthorNameBook.setLayout(new GridLayout(0, 2));
        containAuthorNameBook.add(new JLabel("Name Author: ", SwingConstants.RIGHT));
        JPanel containJTextAuthorName = new JPanel();
        containJTextAuthorName.setLayout(new GridLayout(3, 0));
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
        containTypeOfBook.setLayout(new GridLayout(0, 2));
        containTypeOfBook.add(new JLabel("Type Book: ", SwingConstants.RIGHT));
        JPanel containJTextTypeBook = new JPanel();
        containJTextTypeBook.setLayout(new GridLayout(3, 0));
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
        containImage.setBorder(new EmptyBorder(10, 100, 10, 100));
        containImage.setLayout(new BorderLayout());
        placeDisplayImage = new JLabel("Don't have image here :(", SwingConstants.CENTER);
        placeDisplayImage.setBorder(BorderFactory.createLineBorder(Color.blue));
        containImage.add(placeDisplayImage, BorderLayout.CENTER);
        colDisplayImageAndBtnBorrow.add(containImage, BorderLayout.CENTER);

        JPanel containButton = new JPanel();
        containButton.setLayout(new GridLayout(0, 3));
        JPanel row1Contain = new JPanel();
        containButton.add(row1Contain);
        JButton borrowBtn = new JButton("Borrow Book");
        borrowBtn.setActionCommand("Borrow book");
        borrowBtn.addActionListener(new ButtonActionListener());
        borrowBtn.setPreferredSize(new Dimension(0, 40));
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
        comboboxContain.setLayout(new GridLayout(0, 3));
        JPanel comboBox1 = new JPanel();
        comboBox1.setLayout(new BorderLayout());
        comboBox1.add(new JLabel("Filter: ", SwingConstants.CENTER), BorderLayout.LINE_START);
        cb = new JComboBox(filterString);
        cb.setSelectedIndex(-1);
        comboBox1.add(cb, BorderLayout.CENTER);
        cb.setActionCommand("Cb filter");
        comboboxContain.add(comboBox1);

        JPanel comboBox2 = new JPanel();
        comboboxContain.add(comboBox2);

        JPanel comboBox3 = new JPanel();
        comboboxContain.add(comboBox3);
        secondRowOfField.add(comboboxContain, BorderLayout.NORTH);

        JPanel tableContain = new JPanel();
        tableContain.setBorder(new EmptyBorder(10, 0, 0, 0));
        tableContain.setLayout(new BorderLayout());
        bookArr = initializeBook();
        customModel = new VstTableItemModelUser(bookArr, column);
        jt = new JTable(customModel) {
            public boolean editCellAt(int row, int column, EventObject a) {
                return false;
            }
        };
        sorter = new TableRowSorter<>(customModel);
        jt.setRowSorter(sorter);
        cb.addActionListener(new ButtonActionListener());
        jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jt.addMouseListener(new MouseClickRow());
        JScrollPane scrollPane = new JScrollPane(jt);
        jt.setFillsViewportHeight(true);
        tableContain.add(scrollPane, BorderLayout.CENTER);
        secondRowOfField.add(tableContain, BorderLayout.CENTER);
        belowOfFindField.add(secondRowOfField);

        belowLabel.add(belowOfFindField, BorderLayout.CENTER);

        bookPanel.add(belowLabel, BorderLayout.CENTER);
        cardPanel.add(bookPanel, NAMEBOOKCARD);
        componentBorrowBookPanel = new BorrowPanel(this);
        componentInformationPanel = new InformationPanel(this);
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
            if (actionEvent.getSource() instanceof JButton) {
                JButton button = (JButton) actionEvent.getSource();
                switch (button.getActionCommand()) {
                    case "Exit":
                        //Book section
                        clearAllInformationFieldAfterLogout();
                        //Borrow section
                        componentBorrowBookPanel.clearBorrowPanel();

                        main.setSize(UIMain.WIDTH, UIMain.HEIGHT);
                        main.setTitle(UIMain.TITLE);
                        main.repaint();
                        main.setLocationRelativeTo(null);
                        card.showCard(UIMain.NAMELOGIN);
                        break;
                    case NAMEBOOKCARD:

                        cardLayout.show(cardPanel, NAMEBOOKCARD);
                        break;
                    case BORROWBOOKCARD:

                        componentBorrowBookPanel.showBorrowPanel();
                        cb.setSelectedIndex(-1);
                        break;
                    case INFORMATIONCARD:
                        componentInformationPanel.showInformationPanel();
                        System.out.println(main.getUserNameTf().getText());
                        break;
                    case "Borrow book":
                        if(selectedIndex != -1) {
                            if (checkInfor()) {

                            } else {
                                JOptionPane.showMessageDialog(getParent(), "Please update information before borrow book.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(getParent(), "Please choose a row before borrow.");
                        }
                        break;
                }
            } else if (actionEvent.getSource() instanceof JComboBox) {
                JComboBox cbz = (JComboBox) actionEvent.getSource();
                switch (cbz.getActionCommand()) {
                    case "Cb filter":
                        if(cb.getSelectedIndex() != -1) {
                            DefaultRowSorter sorter = ((DefaultRowSorter) jt.getRowSorter());
                            ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
                            String columnChoose = cb.getSelectedItem().toString();
                            switch (columnChoose) {
                                case "Author name":
                                    sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
                                    break;
                                case "Type":
                                    sortKeys.add(new RowSorter.SortKey(5, SortOrder.ASCENDING));
                                    break;
                                case "Price":
                                    sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
                                    break;
                            }
                            sorter.setSortKeys(sortKeys);
                        }
                        break;
                }
            }
        }

        private void clearAllInformationFieldAfterLogout() {
            tfFinding.setText("");
            idBook.setText("");
            nameBook.setText("");
            priceBook.setText("");
            nameAuthor.setText("");
            typeBook.setText("");
            placeDisplayImage.setIcon(null);
            cb.setSelectedIndex(-1);
        }
    }
    //Borrow book function
    //Get id user and check information
    private void getIdUser() {
        try {
            pstmt = connection.prepareStatement("SELECT IdUser FROM User WHERE Name=?");
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                idUser = rs.getInt("IdUser");
            }
        } catch (Exception ex) {ex.printStackTrace();}
    }
    //Check information
    private boolean checkInfor() {
        boolean checkUserInfor = false;
        try {
            pstmt = connection.prepareStatement("SELECT * FROM UserInfor WHERE IDUser=?");
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                checkUserInfor = true;
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return checkUserInfor;
    }
    //Initialize one author
    private Author initializeOneAuthor(int idAuthor) {
        Author go = new Author();
        DBConnect zz = new DBConnect(3306, "javafinalimportant", "adminjava", "Admin1234@");
        Connection connection = zz.getConn();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT AuthorID, NameAuthor FROM AuthorInfor WHERE AuthorID =?");
            pstmt.setInt(1, idAuthor);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                go.setIdAuthor(rs.getInt("AuthorID"));
                go.setNameOfAuthor(rs.getString("NameAuthor"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return go;
    }

    private ArrayList<Book> initializeBook() {
        ArrayList<Book> zauzau = new ArrayList<>();
        int idDisplay = 0;
        connectDB.setQuery("SELECT BookInfor.IDBook, BookInfor.NameOfBook, BookInfor.Price, BookInfor.AuthorID, BookInfor.DayAdd, BookInfor.IDUser, BookInfor.TypeBook, BookOfAuthor.Image FROM BookInfor INNER JOIN BookOfAuthor ON  BookInfor.IDBook = BookOfAuthor.IDBook");
        ResultSet rs = connectDB.selectFromDB();
        try {
            while (rs.next()) {
                idDisplay++;
                int idBookSql = rs.getInt("IDBook");
                String nameOfBook = rs.getString("NameOfBook");
                float price = rs.getFloat("Price");
                int authorId = rs.getInt("AuthorID");
                Date dateAdd = rs.getDate("DayAdd");
                String typeBook = rs.getString("TypeBook");
                byte[] image = rs.getBytes("Image");
                Book goGo = new Book(idBookSql, nameOfBook, price, initializeOneAuthor(authorId), dateAdd, typeBook, image);
                goGo.setIDBook(idDisplay);
                zauzau.add(goGo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return zauzau;
    }
    public int getId() {return idUser;}
    private class KeyListenerFind implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            findBook(tfFinding.getText());
        }

        private void findBook(String searchName) {
            sorter.setRowFilter(RowFilter.regexFilter(searchName));
        }

    }
    private class MouseClickRow implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            int i = jt.getSelectedRow();
            selectedIndex = jt.getSelectedRow();
            TableModel model = jt.getModel();
            idBook.setText(model.getValueAt(i,0).toString());
            nameBook.setText(model.getValueAt(i,1).toString());
            priceBook.setText(model.getValueAt(i,2).toString());
            nameAuthor.setText(model.getValueAt(i,3).toString());
            typeBook.setText(model.getValueAt(i,4).toString());
            imageDisplay = new ByteArrayInputStream((byte[]) model.getValueAt(i,6));
            BufferedImage img = null;
            try {
                img = ImageIO.read(imageDisplay);
            }catch (Exception e) {e.printStackTrace();}
            ImageIcon imageIcon = new ImageIcon(fitImage(img,placeDisplayImage.getWidth(),placeDisplayImage.getHeight()));
            placeDisplayImage.setIcon(imageIcon);
        }


        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
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