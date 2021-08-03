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
                JPanel splitTwoPanelInRight = new JPanel();
                splitTwoPanelInRight.setLayout(new GridLayout(2,0));
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
                splitTwoPanelInRight.add(containInforBook);

                //Button and search go here
                JPanel btnAndSearchField = new JPanel();
                splitTwoPanelInRight.add(btnAndSearchField);

                bookPanel.add(splitTwoPanelInRight);
                //Contain image
                JPanel containImageBook = new JPanel();
                containImageBook.setLayout(new BorderLayout());
                containImageBook.add(new JLabel("Image here", SwingConstants.CENTER),BorderLayout.CENTER);
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
