package ManagerUI;

import UI.UIMain;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
    public ManagerUI(JFrame loginPanel) {
        UIMain castUI = (UIMain) loginPanel;
        managerAccount = castUI.getUserNameTf().getText();
        setSize(1500, 900);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle(TITLEMANAGER);
        setLayout(new GridLayout(2,0));

        //Book manager
        //Author manager
        //Student manager
        //Borrow manager
        JTabbedPane tabbedPanel = new JTabbedPane();
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
                        row1OfContainSearchField.setLayout(new GridLayout(0,3));
                            JPanel leftOfSearchField = new JPanel();
                            row1OfContainSearchField.add(leftOfSearchField);

                            JPanel splitToTwo = new JPanel();
                            splitToTwo.setLayout(new GridLayout(2,0));
                                splitToTwo.add(findField = new JTextField(20));
                                JPanel paddingFindField = new JPanel();
                                splitToTwo.add(paddingFindField);
                            row1OfContainSearchField.add(splitToTwo);

                            JPanel rightOfSearchField = new JPanel();
                            rightOfSearchField.setLayout(new GridLayout(0,3));
                                JPanel containBtnFind1 = new JPanel();
                                containBtnFind1.setLayout(new GridLayout(2,0));
                                    containBtnFind1.add(btnFind = new JButton("Find"));
                                    JPanel paddingOfBtnFind = new JPanel();
                                    containBtnFind1.add(paddingOfBtnFind);
                                rightOfSearchField.add(containBtnFind1);

                                JPanel colRightOFSearchField = new JPanel();
                                rightOfSearchField.add(colRightOFSearchField);

                                JPanel colFinalOfSearchField = new JPanel();
                                rightOfSearchField.add(colFinalOfSearchField);
                            row1OfContainSearchField.add(rightOfSearchField);
                        containSearchField.add(row1OfContainSearchField);

                        //Filter place
                        JPanel row2OfContainSearchField = new JPanel();
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

            JPanel authorManager = new JPanel();
            tabbedPanel.add(authorManager, AUTHORTAB);
            JPanel studentPanel = new JPanel();
            tabbedPanel.add(studentPanel, STUDENTTAB);
            JPanel borrowPanel = new JPanel();
            tabbedPanel.add(borrowPanel, BORROWTAB);
        add(tabbedPanel);

        //The place where the JTable go with
        JPanel cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        add(cardPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
