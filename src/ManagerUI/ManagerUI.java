package ManagerUI;

import DB.DBConnect;
import JFileChooserCustom.ImageFileView;
import JFileChooserCustom.ImageFilter;
import JFileChooserCustom.ImagePreview;
import ObjectZZ.Author;
import ObjectZZ.Book;
import TreeModelCustom.VstTableItemModel;
import UI.UIMain;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.List;

public class ManagerUI extends JFrame {
    String managerAccount;
    public static final String TITLEMANAGER = "Manager Library";
    public static final String BOOKPANELTAB = "Book management";
    public static final String AUTHORTAB = "Author management";
    public static final String STUDENTTAB = "Student management";
    public static final String BORROWTAB = "Borrow management";

    //Card layout
    CardLayout cardLayout;
    JPanel cardPanel;

    //Tabbed panel
    JTabbedPane tabbedPanel;
    //Information book
        //Label display name of book
        JTextField nameOfBook, priceOfBook;
        //Type cb box
        JComboBox cbType, cbAuthor, cbDay, cbMonth, cbYear;
            //String inside cbType
            String[] typeElement = {"Science", "Mathematical", "Literature", "Comics", "Programmer", "Historical", "Geographical", "Physic", "Biology"};
            //String inside cbAuthor
            List<Author> authorArr;
            Vector<Author> passToCbAuthor;
        //Display image in right
        JLabel displayImage, nameOfBookImage;
        //Button upload image
        JButton btnUploadImage;
        //Button add, update, delete
        JButton btnAdd,btnUpdate,btnDelete;
        //Search field
        JTextField findField;
        //Button find
        JButton btnFind;
        //Filter
        JComboBox cbFilter;
        String[] stringTheFilterHas = {"Author name", "Type", "Price"};
    //JTable of information book
        JTable jt;
        String[] column = {"ID of book", "Name of book", "Price", "Author name", "Day add", "Type"};
        VstTableItemModel customModel;
        ArrayList<Book> bookTest;
        TableRowSorter<VstTableItemModel> sorter;
    //Set title line border with each tab in table
        JPanel panelWithLineTitle;
    //Author management panel
        AuthorManagement authorManager;
    //JFileChooser
        private JFileChooser fc;
        private File imgFile;
        private InputStream imageDisplay;
        private Blob imageInsertSQL;
        private int idSql = -1;
    //Button listener to get field and initialize
        private Author author;
        private String type;
    //DB Connect
        private DBConnect connectDB;
        private Connection connection;
        private PreparedStatement pstmt;
    public ManagerUI(JFrame loginPanel) {
        this.connectDB = new DBConnect(3306,"javafinalimportant","adminjava","Admin1234@");
        connection = connectDB.getConn();
        authorArr = new ArrayList<>();
        initializeAuthor();
        passToCbAuthor = new Vector<>(authorArr);
        UIMain castUI = (UIMain) loginPanel;
        managerAccount = castUI.getUserNameTf().getText();
        setSize(1500, 900);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle(TITLEMANAGER);
        setLayout(new GridLayout(2,0));
        //Initialize card panel
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        //Book manager
        //Author manager
        //Student manager
        //Borrow manager
        tabbedPanel = new JTabbedPane();
        tabbedPanel.addChangeListener(new TabbedChangeListener());
        tabbedPanel.setBorder(new EmptyBorder(10,20,10,20));
            JPanel bookPanel = new JPanel();
            bookPanel.setLayout(new GridLayout(0,2));
                JPanel splitTwoPanelInLeft = new JPanel();
                splitTwoPanelInLeft.setLayout(new GridLayout(2,0));
                //Contain JTextField
                JPanel containInforBook = new JPanel();
                containInforBook.setLayout(new GridLayout(4,0));
                    //Name of book section
                    JPanel containNameOFBook = new JPanel();
                    containNameOFBook.setLayout(new GridLayout(0,2));
                    containNameOFBook.add(new JLabel("Name of book: ", SwingConstants.RIGHT));
                    JPanel containJTextFieldNameOfBook = new JPanel();
                    containJTextFieldNameOfBook.setLayout(new GridLayout(3,0));
                        JPanel boxName1 = new JPanel();
                        containJTextFieldNameOfBook.add(boxName1);
                        JPanel boxName2 = new JPanel();
                        boxName2.setLayout(new BorderLayout());
                        boxName2.add(nameOfBook = new JTextField(20));
                        containJTextFieldNameOfBook.add(boxName2);
                        JPanel boxName3 = new JPanel();
                        containJTextFieldNameOfBook.add(boxName3);
                    containNameOFBook.add(containJTextFieldNameOfBook);
                    containInforBook.add(containNameOFBook);

                    //Price of book section
                    JPanel containPrice = new JPanel();
                    containPrice.setLayout(new GridLayout(0,2));
                        //Col 1 contain JLabel
                        JPanel containJLabelPriceOfBook = new JPanel();
                        containJLabelPriceOfBook.setLayout(new GridLayout(3,0));
                            containJLabelPriceOfBook.add(new JLabel("Price of book: ", SwingConstants.RIGHT));
                            JPanel priceOfBookBox1 = new JPanel();
                            containJLabelPriceOfBook.add(priceOfBookBox1);
                            containJLabelPriceOfBook.add(new JLabel("Author of book: ", SwingConstants.RIGHT));
                        containPrice.add(containJLabelPriceOfBook);
                        //Col 2 contain
                        JPanel containJTextFieldPriceOfBook = new JPanel();
                        containJTextFieldPriceOfBook.setLayout(new GridLayout(3,0));
                            containJTextFieldPriceOfBook.add(priceOfBook = new JTextField(20));
                            JPanel boxPrice1 = new JPanel();
                            containJTextFieldPriceOfBook.add(boxPrice1);
                            containJTextFieldPriceOfBook.add(cbAuthor = new JComboBox(passToCbAuthor));
                            cbAuthor.setActionCommand("Cb Author");
                            cbAuthor.addActionListener(new ButtonListener());
                            cbAuthor.setSelectedIndex(-1);
                        containPrice.add(containJTextFieldPriceOfBook);
                    containInforBook.add(containPrice);

                    //Day add section
                    JPanel containDayAdd = new JPanel();
                    containDayAdd.setLayout(new GridLayout(3,0));
                        JPanel dayAddBox1 = new JPanel();
                        containDayAdd.add(dayAddBox1);
                        JPanel dayAddBox2 = new JPanel();
                        dayAddBox2.setLayout(new GridLayout(0,2));
                        dayAddBox2.add(new JLabel("Day add: ", SwingConstants.RIGHT));
                        JPanel containCBDay = new JPanel();
                        containCBDay.setLayout(new BorderLayout());
                            JPanel cbDayBox2 = new JPanel();
                            cbDayBox2.setLayout(new GridLayout(0,3));
                            cbDayBox2.add(cbDay = new JComboBox());
                            cbDay.setEnabled(false);
                            cbDay.setBorder(new EmptyBorder(0,0,0,10));
                            cbDayBox2.add(cbMonth = new JComboBox());
                            cbMonth.setEnabled(false);
                            cbMonth.setBorder(new EmptyBorder(0,0,0,10));
                            cbDayBox2.add(cbYear = new JComboBox());
                            cbYear.setEnabled(false);
                            containCBDay.add(cbDayBox2, BorderLayout.CENTER);
                        dayAddBox2.add(containCBDay);
                        containDayAdd.add(dayAddBox2);
                        JPanel dayAddBox3 = new JPanel();
                        containDayAdd.add(dayAddBox3);
                    containInforBook.add(containDayAdd);

                    //Type section
                    JPanel containType = new JPanel();
                    containType.setLayout(new GridLayout(3,0));
                        JPanel typeBox1 = new JPanel();
                        typeBox1.setLayout(new GridLayout(0,2));
                        typeBox1.add(new JLabel("Type: ", SwingConstants.RIGHT));
                        typeBox1.add(cbType = new JComboBox(typeElement));
                        cbType.setActionCommand("Cb Type");
                        cbType.addActionListener(new ButtonListener());
                        cbType.setSelectedIndex(-1);
                        containType.add(typeBox1);
                        JPanel typeBox2 = new JPanel();
                        containType.add(typeBox2);
                        JPanel typeBox3 = new JPanel();
                        containType.add(typeBox3);
                    containInforBook.add(containType);
                splitTwoPanelInLeft.add(containInforBook);

                //Button and search go here
                JPanel btnAndSearchField = new JPanel();
                btnAndSearchField.setLayout(new GridLayout(2,0));
                    //Button
                    JPanel containBtn = new JPanel();
                    containBtn.setLayout(new GridLayout(0,2));
                        JPanel boxContainBtn1 = new JPanel();
                        containBtn.add(boxContainBtn1);

                        JPanel boxContainBtn2 = new JPanel();
                        boxContainBtn2.setLayout(new GridLayout(0,3));
                            //Btn add
                            JPanel containBtnAdd = new JPanel();
                            containBtnAdd.setLayout(new GridLayout(3,0));
                                JPanel topAddBtn1 = new JPanel();
                                topAddBtn1.setLayout(new BorderLayout());
                                topAddBtn1.add(btnAdd = new JButton("Add"));
                                btnAdd.setActionCommand("Add book");
                                btnAdd.addActionListener(new ButtonListener());
                                containBtnAdd.add(topAddBtn1);

                                JPanel topAddBtn2 = new JPanel();
                                containBtnAdd.add(topAddBtn2);

                                JPanel topAddBtn3 = new JPanel();
                                containBtnAdd.add(topAddBtn3);
                            boxContainBtn2.add(containBtnAdd);

                            //Btn update
                            JPanel containBtnUpdate = new JPanel();
                            containBtnUpdate.setLayout(new GridLayout(3,0));
                                JPanel topUpdateBtn1 = new JPanel();
                                topUpdateBtn1.setLayout(new BorderLayout());
                                topUpdateBtn1.add(btnUpdate = new JButton("Update"));
                                btnUpdate.setActionCommand("Update book");
                                btnUpdate.addActionListener(new ButtonListener());
                                containBtnUpdate.add(topUpdateBtn1);

                                JPanel topUpdateBtn2 = new JPanel();
                                containBtnUpdate.add(topUpdateBtn2);

                                JPanel topUpdateBtn3 = new JPanel();
                                containBtnUpdate.add(topUpdateBtn3);
                            boxContainBtn2.add(containBtnUpdate);

                            //Btn delete
                            JPanel containBtnDelete = new JPanel();
                            containBtnDelete.setLayout(new GridLayout(3,0));
                                JPanel topDeleteBtn1 = new JPanel();
                                topDeleteBtn1.setLayout(new BorderLayout());
                                topDeleteBtn1.add(btnDelete = new JButton("Delete"));
                                btnDelete.setActionCommand("Delete book");
                                btnDelete.addActionListener(new ButtonListener());
                                containBtnDelete.add(topDeleteBtn1);

                                JPanel topDeleteBtn2 = new JPanel();
                                containBtnDelete.add(topDeleteBtn2);

                                JPanel topDeleteBtn3 = new JPanel();
                                containBtnDelete.add(topDeleteBtn3);
                            boxContainBtn2.add(containBtnDelete);
                        containBtn.add(boxContainBtn2);
                    btnAndSearchField.add(containBtn);

                    //Search field
                    JPanel containSearchField = new JPanel();
                    containSearchField.setLayout(new GridLayout(2,0));
                        JPanel row1OfContainSearchField = new JPanel();
                        containSearchField.add(row1OfContainSearchField);

                        //Filter place
                        JPanel row2OfContainSearchField = new JPanel();
                        row2OfContainSearchField.setLayout(new GridLayout(0,2));
                            JPanel leftPanelOfFilter = new JPanel();
                            leftPanelOfFilter.setLayout(new GridLayout(2,0));
                                JPanel firstRowOfFilter = new JPanel();
                                leftPanelOfFilter.add(firstRowOfFilter);

                                JPanel paddingFirstRowOfFilter = new JPanel();
                                paddingFirstRowOfFilter.setLayout(new BorderLayout());
                                paddingFirstRowOfFilter.add(new JLabel("Order: ", SwingConstants.LEFT), BorderLayout.LINE_START);
                                paddingFirstRowOfFilter.add(cbFilter = new JComboBox(stringTheFilterHas), BorderLayout.CENTER);
                                cbFilter.setActionCommand("Cb filter");

                                cbFilter.setSelectedIndex(-1);
                                leftPanelOfFilter.add(paddingFirstRowOfFilter);
                            row2OfContainSearchField.add(leftPanelOfFilter);

                            JPanel rightPanelOfFilter = new JPanel();
                            row2OfContainSearchField.add(rightPanelOfFilter);
                        containSearchField.add(row2OfContainSearchField);
                    btnAndSearchField.add(containSearchField);
                splitTwoPanelInLeft.add(btnAndSearchField);

                bookPanel.add(splitTwoPanelInLeft);
                //Contain image
                JPanel containImageBook = new JPanel();
                containImageBook.setBorder(new EmptyBorder(10,0,10,0));
                containImageBook.setLayout(new GridLayout(2,0));
                    //row 1
                    JPanel rowFirstRight = new JPanel();
                    rowFirstRight.setLayout(new GridLayout(0,3));
                        //col 1
                        JPanel firstRightBox1 = new JPanel();
                        rowFirstRight.add(firstRightBox1);
                        //col 2 contain image
                        JPanel firstRightBox2 = new JPanel();
                        firstRightBox2.setLayout(new BorderLayout());
                        displayImage = new JLabel("Don't have image here :(.", SwingConstants.CENTER);
                        displayImage.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                        firstRightBox2.add(displayImage, BorderLayout.CENTER);
                        rowFirstRight.add(firstRightBox2);
                        //col 3
                        JPanel firstRightBox3 = new JPanel();
                        rowFirstRight.add(firstRightBox3);
                    containImageBook.add(rowFirstRight);

                    //row 2
                    JPanel rowSecondRight = new JPanel();
                    rowSecondRight.setBorder(new EmptyBorder(10,0,0,0));
                    rowSecondRight.setLayout(new BorderLayout());
                        //North
                        JPanel northOfRowSecond = new JPanel();
                        northOfRowSecond.setLayout(new GridLayout(0,3));
                            //Col 1
                            JPanel rowSecondBoxNorth1 = new JPanel();
                            northOfRowSecond.add(rowSecondBoxNorth1);
                            //Col 2
                            JPanel rowSecondBoxNorth2 = new JPanel();
                            rowSecondBoxNorth2.setLayout(new BorderLayout());
                            rowSecondBoxNorth2.add(nameOfBookImage = new JLabel("", SwingConstants.CENTER));
                            northOfRowSecond.add(rowSecondBoxNorth2);
                            //Col 3
                            JPanel rowSecondBoxNorth3 = new JPanel();
                            northOfRowSecond.add(rowSecondBoxNorth3);
                        rowSecondRight.add(northOfRowSecond, BorderLayout.NORTH);

                        //Center
                        JPanel centerOfRowSecond = new JPanel();
                        centerOfRowSecond.setBorder(new EmptyBorder(10,0,0,0));
                        centerOfRowSecond.setLayout(new GridLayout(3,0));
                            JPanel containBtnUpload = new JPanel();
                            containBtnUpload.setLayout(new GridLayout(0,5));
                                JPanel btnUploadBox1 = new JPanel();
                                containBtnUpload.add(btnUploadBox1);
                                JPanel btnUploadBox2 = new JPanel();
                                containBtnUpload.add(btnUploadBox2);

                                JPanel btnUploadBox3 = new JPanel();
                                btnUploadBox3.setLayout(new BorderLayout());
                                btnUploadImage = new JButton("Upload");
                                btnUploadImage.setActionCommand("Choose image");
                                btnUploadImage.addActionListener(new ButtonListener());
                                btnUploadBox3.add(btnUploadImage, BorderLayout.CENTER);
                                containBtnUpload.add(btnUploadBox3);

                                JPanel btnUploadBox4 = new JPanel();
                                containBtnUpload.add(btnUploadBox4);
                                JPanel btnUploadBox5 = new JPanel();
                                containBtnUpload.add(btnUploadBox5);
                            centerOfRowSecond.add(containBtnUpload);
                            JPanel containBtnUpload2 = new JPanel();
                            centerOfRowSecond.add(containBtnUpload2);
                            JPanel containBtnUpload3 = new JPanel();
                            centerOfRowSecond.add(containBtnUpload3);
                        rowSecondRight.add(centerOfRowSecond, BorderLayout.CENTER);
                    containImageBook.add(rowSecondRight);
                bookPanel.add(containImageBook);
            tabbedPanel.add(bookPanel, BOOKPANELTAB);



        //The place where the JTable go with
        JPanel containJTableInformationBook = new JPanel();
        Border paneEdge = BorderFactory.createEmptyBorder(0,20,10,20);
        containJTableInformationBook.setBorder(paneEdge);
        containJTableInformationBook.setLayout(new BorderLayout());
            panelWithLineTitle = new JPanel();
            panelWithLineTitle.setLayout(new BorderLayout());
                JPanel containFindTextField = new JPanel();
                containFindTextField.setLayout(new GridLayout(0,3));
                    JPanel firstPanelPaddingOfFind = new JPanel();
                    containFindTextField.add(firstPanelPaddingOfFind);

                    containFindTextField.add(findField = new JTextField(20));

                    JPanel thirdPanelPaddingOfFind = new JPanel();
                    thirdPanelPaddingOfFind.setLayout(new GridLayout(0,6));
                    thirdPanelPaddingOfFind.add(new JLabel("Find", SwingConstants.CENTER));
                    thirdPanelPaddingOfFind.add(new JLabel(""));
                    thirdPanelPaddingOfFind.add(new JLabel(""));
                    thirdPanelPaddingOfFind.add(new JLabel(""));
                    thirdPanelPaddingOfFind.add(new JLabel(""));
                    thirdPanelPaddingOfFind.add(new JLabel(""));
                    containFindTextField.add(thirdPanelPaddingOfFind);
                panelWithLineTitle.add(containFindTextField, BorderLayout.NORTH);

                JPanel containTable = new JPanel();
                containTable.setLayout(new BorderLayout());
                containTable.setBorder(new EmptyBorder(10,10,10,10));
                bookTest = initializeBook();
                customModel= new VstTableItemModel(bookTest,column);
                jt = new JTable(customModel) {
                    public boolean editCellAt(int row, int column, EventObject eventObject) {return false;}
                };
                sorter = new TableRowSorter<>(customModel);
                jt.setRowSorter(sorter);
                cbFilter.addActionListener(new ButtonListener());
                findField.addKeyListener(new KeyListenerFind());
                jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                jt.addMouseListener(new MouseClickRow());
                JScrollPane scrollPane = new JScrollPane(jt);
                jt.setFillsViewportHeight(true);
                containTable.add(scrollPane, BorderLayout.CENTER);
                panelWithLineTitle.add(containTable, BorderLayout.CENTER);
            panelWithLineTitle.setBorder(BorderFactory.createTitledBorder(BOOKPANELTAB));
            containJTableInformationBook.add(panelWithLineTitle);
        cardPanel.add(containJTableInformationBook, BOOKPANELTAB);

        authorManager = new AuthorManagement(this);
        //Initialize tab
        tabbedPanel.add(authorManager, AUTHORTAB);
        JPanel studentPanel = new JPanel();
        tabbedPanel.add(studentPanel, STUDENTTAB);
        JPanel borrowPanel = new JPanel();
        tabbedPanel.add(borrowPanel, BORROWTAB);
        add(tabbedPanel);
        add(cardPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public void clearManagerUI() {
        nameOfBook.setText("");
        priceOfBook.setText("");

        //Image
        displayImage.setIcon(null);
        nameOfBook.setText("");

        //Find field
        findField.setText("");

        //Cb box
        cbFilter.setSelectedIndex(-1);
        cbAuthor.setSelectedIndex(-1);
        cbYear.removeAllItems();
        cbMonth.removeAllItems();
        cbDay.removeAllItems();
        cbType.setSelectedIndex(-1);
    }

    private class TabbedChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            switch (tabbedPanel.getSelectedIndex()) {
                case 0:
                    cardLayout.show(cardPanel,BOOKPANELTAB);
                    if(authorManager != null) {
                        authorManager.clearUI();
                    }
                    break;
                case 1:
                    authorManager.showAuthorManagementPanel();
                    break;
                case 2:
                    clearManagerUI();
                    break;
                case 3:
                    clearManagerUI();
                    break;
            }
        }
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() instanceof JButton) {
                JButton btn = (JButton) actionEvent.getSource();
                switch (btn.getActionCommand()) {
                    case "Choose image":
                        displayChooseFile();
                        break;
                    case "Add book":
                        if(validateFieldBeforeInsert()) {
                            if(!validateElementEqual()) {
                                addBook();
                                clearManagerUI();
                            } else {
                                JOptionPane.showMessageDialog(getParent(),"Already has a book with this name, try another");
                            }
                        }
                        break;
                    case "Update book":
                        if(idSql != -1) {
                            if(validateFieldBeforeInsert()) {

                                clearManagerUI();
                                JOptionPane.showMessageDialog(getParent(), "Update success");
                            }
                        } else {
                            JOptionPane.showMessageDialog(getParent(), "Please choose a row.");
                        }
                        break;
                    case "Delete book":
                        break;

                }
            } else if(actionEvent.getSource() instanceof JComboBox) {
                JComboBox cb = (JComboBox) actionEvent.getSource();
                switch (cb.getActionCommand()) {
                    case "Cb Author":
                        author = (Author) cb.getSelectedItem();
                        break;
                    case "Cb Type":
                        type = (String) cb.getSelectedItem();
                        break;
                    case "Cb filter":
                        if(cbFilter.getSelectedIndex() != -1) {
                            DefaultRowSorter sorter = ((DefaultRowSorter) jt.getRowSorter());
                            ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
                            String columnChoose = cbFilter.getSelectedItem().toString();
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
        private boolean validateFieldBeforeInsert() {
            if(nameOfBook.getText().isEmpty()) {
                JOptionPane.showMessageDialog(getParent(),"Name can not be empty, try again.");
                return false;
            } else if (priceOfBook.getText().isEmpty()) {
                JOptionPane.showMessageDialog(getParent(), "Price can not be empty, try again");
                return false;
            } else if(cbAuthor.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(getParent(), "Choose author.");
                return false;
            } else if(cbType.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(getParent(), "Choose type.");
                return false;
            } else if(displayImage.getIcon() == null) {
                JOptionPane.showMessageDialog(getParent(), "Choose image.");
                return false;
            } else if(nameOfBook.getText().isEmpty() && priceOfBook.getText().isEmpty() && cbAuthor.getSelectedIndex() == -1 &&
                       cbType.getSelectedIndex() == -1 || displayImage.getIcon() == null ) {
                JOptionPane.showMessageDialog(getParent(), "Input all the field.");
                return false;
            }else {
                return true;
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

        private void addBook() {
            String nameBook = nameOfBook.getText();
            float priceBook = Float.parseFloat(priceOfBook.getText());
            Date date = new Date();
            //int idOfBookDisplay = bookTest.size()+1;
            int idUser = getIdUser();
            try {
                pstmt = connection.prepareStatement("INSERT INTO BookInfor(NameOfBook, Price, AuthorID, DayAdd, IDUser, TypeBook) VALUES (?,?,?,?,?,?)");
                pstmt.setString(1,nameBook);
                pstmt.setFloat(2,priceBook);
                pstmt.setInt(3,author.getIdSql());
                java.sql.Date dateUpdateToSQL = new java.sql.Date(date.getTime());
                pstmt.setDate(4,dateUpdateToSQL);
                pstmt.setInt(5,idUser);
                pstmt.setString(6, type);
                pstmt.executeUpdate();
            }catch (Exception ex) {ex.printStackTrace();}

            try {
                pstmt = connection.prepareStatement("INSERT INTO BookOfAuthor(AuthorId, IDBook, Image) VALUES (?,LAST_INSERT_ID(),?)");
                pstmt.setInt(1,idUser);
                pstmt.setBlob(2,imageDisplay);
                pstmt.executeUpdate();
            } catch (Exception ex) {ex.printStackTrace();}
        }
        private void updateBook() {

        }
        private int getIdUser() {
            int idUser = 0;
            try {
                pstmt = connection.prepareStatement("SELECT IdUser FROM User WHERE Name=?");
                pstmt.setString(1, managerAccount);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    idUser = rs.getInt("IdUser");
                }
            } catch (Exception ex) {ex.printStackTrace();}
            return idUser;
        }
        private boolean validateElementEqual() {
            boolean checkElement = false;
            for(Book zz : bookTest) {
                if(zz.getNameOfBook().equals(nameOfBook.getText())) {
                    checkElement = true;
                    break;
                }
            }
            return checkElement;
        }

    }
    //Initialize array of book
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
                Book goGo = new Book(idBookSql,nameOfBook,price, initializeOneAuthor(authorId),dateAdd,typeBook,image);
                goGo.setIDBook(idDisplay);
                zauzau.add(goGo);
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return zauzau;
    }
    //Initialize one author
    private Author initializeOneAuthor(int idAuthor) {
        Author go = new Author();
        DBConnect zz = new DBConnect(3306,"javafinalimportant","adminjava","Admin1234@");
        Connection connection = zz.getConn();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT AuthorID, NameAuthor FROM AuthorInfor WHERE AuthorID =?");
            pstmt.setInt(1,idAuthor);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                go.setIdAuthor(rs.getInt("AuthorID"));
                go.setNameOfAuthor(rs.getString("NameAuthor"));
            }
        }catch (Exception ex) {ex.printStackTrace();}
        return go;
    }
    //Initialize array author
    private void initializeAuthor() {
        connectDB.setQuery("SELECT * FROM AuthorInfor");
        ResultSet rs = connectDB.selectFromDB();
        try {
            while (rs.next()) {
                int idAuthorMysql = rs.getInt("AuthorID");
                String nameAuthor = rs.getString("NameAuthor");
                authorArr.add(new Author(idAuthorMysql,nameAuthor));
            }
        } catch (Exception ex) {ex.printStackTrace();}
    }

    private class KeyListenerFind implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            findBook(findField.getText());
        }

        private void findBook(String searchName) {
            sorter.setRowFilter(RowFilter.regexFilter(searchName));
        }
    }

    private class MouseClickRow implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            int i = jt.getSelectedRow();
            idSql = jt.getSelectedRow();
            TableModel model = jt.getModel();
            nameOfBook.setText(model.getValueAt(i,1).toString());
            priceOfBook.setText(model.getValueAt(i,2).toString());
            for(int z = 0;z<cbAuthor.getItemCount();z++) {
                if(cbAuthor.getItemAt(z).toString().contains(model.getValueAt(i,3).toString())) {
                    cbAuthor.setSelectedIndex(z);
                    break;
                }
            }
            String[] splitString = model.getValueAt(i,4).toString().split("/");
            String day = splitString[0];
            cbDay.addItem(day);
            String month = splitString[1];
            cbMonth.addItem(month);
            String year = splitString[2];
            cbYear.addItem(year);
            for(int g = 0;g<cbType.getItemCount();g++) {
                if(cbType.getItemAt(g).toString().contains(model.getValueAt(i,5).toString())) {
                    cbType.setSelectedIndex(g);
                    break;
                }
            }
            imageDisplay = new ByteArrayInputStream((byte[]) model.getValueAt(i,6));
            try {
                imageInsertSQL = new SerialBlob((byte[]) model.getValueAt(i,6));
            } catch (Exception ex) {ex.printStackTrace();}
            BufferedImage img = null;
            try {
                img = ImageIO.read(imageDisplay);
            }catch (Exception e) {e.printStackTrace();}
            ImageIcon imageIcon = new ImageIcon(fitImage(img,displayImage.getWidth(),displayImage.getHeight()));
            displayImage.setIcon(imageIcon);
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
