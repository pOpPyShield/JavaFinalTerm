package ManagerUI;

import UI.UIMain;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.EventObject;
import java.util.List;
import java.util.jar.JarEntry;

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
    //Set title line border with each tab in table
        JPanel panelWithLineTitle;
    //Author management panel
        AuthorManagement authorManager;
    public ManagerUI(JFrame loginPanel) {
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
                            containJTextFieldPriceOfBook.add(cbAuthor = new JComboBox());
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
                            cbDay.setBorder(new EmptyBorder(0,0,0,10));
                            cbDayBox2.add(cbMonth = new JComboBox());
                            cbMonth.setBorder(new EmptyBorder(0,0,0,10));
                            cbDayBox2.add(cbYear = new JComboBox());
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
                        typeBox1.add(cbType = new JComboBox());
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
                                paddingFirstRowOfFilter.add(new JLabel("Filter: ", SwingConstants.LEFT), BorderLayout.LINE_START);
                                paddingFirstRowOfFilter.add(cbFilter = new JComboBox(stringTheFilterHas), BorderLayout.CENTER);
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
                    thirdPanelPaddingOfFind.add(btnFind = new JButton("Find"));
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
                DefaultTableModel model = new DefaultTableModel(column,0);
                jt = new JTable(model) {
                    public boolean editCellAt(int row, int column, EventObject eventObject) {return false;}
                };
                jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        displayImage.setText("");
        nameOfBook.setText("");

        //Find field
        findField.setText("");

        //Cb box
        cbFilter.setSelectedIndex(-1);
        cbAuthor.setSelectedIndex(-1);
        cbYear.setSelectedIndex(-1);
        cbMonth.setSelectedIndex(-1);
        cbDay.setSelectedIndex(-1);
        cbType.setSelectedIndex(-1);
    }

    private class TabbedChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            switch (tabbedPanel.getSelectedIndex()) {
                case 0:
                    cardLayout.show(cardPanel,BOOKPANELTAB);
                    break;
                case 1:
                    clearManagerUI();
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
}
