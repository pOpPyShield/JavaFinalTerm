package UserUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.EventObject;

public class BorrowPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    //JTextField
    JTextField idOfBook, nameOfBook, priceOfBook, typeOfBook;

    //Jlabel display image
    JLabel whereContainImageAboutBook;
    //JCombobox date borrow
    JComboBox day;
    JComboBox month;
    JComboBox year;

    //JRadioButton
    JRadioButton radioBtn1, radioBtn2;
    //JTable
    JTable jt;
    String[] column = {"IDBook","Name of book","Price","Name author","Type", "Date borrow", "Date return"};
    public BorrowPanel(HomeUserPanel card) {
        cardLayout= card.getCardLayout();
        cardPanel = card.getCardPanel();
        cardPanel.add(this,HomeUserPanel.BORROWBOOKCARD);
        setLayout(new BorderLayout());
        JLabel northLabel = new JLabel("Borrow Book", SwingConstants.CENTER);
        northLabel.setFont(new Font("SansSerif",Font.ITALIC, 40));
        northLabel.setBorder(new EmptyBorder(10,0,10,0));
        northLabel.setForeground(Color.BLACK);
        add(northLabel,BorderLayout.NORTH);

        JPanel belowLabel = new JPanel();
        belowLabel.setBorder(new EmptyBorder(10,20,20,20));
        belowLabel.setLayout(new GridLayout(2,0));
            JPanel topPanelContainInformation = new JPanel();
            topPanelContainInformation.setLayout(new GridLayout(0,2));
                JPanel colLeftContainInformation = new JPanel();
                colLeftContainInformation.setLayout(new GridLayout(6,0));
                    //Row 1
                    JPanel containIdOfBook = new JPanel();
                    containIdOfBook.setLayout(new GridLayout(0,2));
                    containIdOfBook.add(new JLabel("Id Book: ", SwingConstants.RIGHT));
                    JPanel containJTextFieldIDOfBook = new JPanel();
                    containJTextFieldIDOfBook.setLayout(new GridLayout(3,0));
                    JPanel boxId1 = new JPanel();
                    containJTextFieldIDOfBook.add(boxId1);
                    idOfBook = new JTextField(20);
                    idOfBook.setEditable(false);
                    containJTextFieldIDOfBook.add(idOfBook);
                    JPanel boxId2 = new JPanel();
                    containJTextFieldIDOfBook.add(boxId2);
                    containIdOfBook.add(containJTextFieldIDOfBook);
                    colLeftContainInformation.add(containIdOfBook);

                    //Row 2
                    JPanel containNameOfBook = new JPanel();
                    containNameOfBook.setLayout(new GridLayout(0,2));
                    containNameOfBook.add(new JLabel("Name of book: ", SwingConstants.RIGHT));
                    JPanel containJTextFieldNameOfBook = new JPanel();
                    containJTextFieldNameOfBook.setLayout(new GridLayout(3,0));
                    JPanel nameBook1 = new JPanel();
                    containJTextFieldNameOfBook.add(nameBook1);
                    nameOfBook = new JTextField(20);
                    nameOfBook.setEditable(false);
                    containJTextFieldNameOfBook.add(nameOfBook);
                    JPanel nameBook2= new JPanel();
                    containJTextFieldNameOfBook.add(nameBook2);
                    containNameOfBook.add(containJTextFieldNameOfBook);
                    colLeftContainInformation.add(containNameOfBook);

                    //Row 3
                    JPanel containPriceOfBook = new JPanel();
                    containPriceOfBook.setLayout(new GridLayout(0,2));
                    containPriceOfBook.add(new JLabel("Price Book: ", SwingConstants.RIGHT));
                    JPanel containJTextFieldPriceOfBook = new JPanel();
                    containJTextFieldPriceOfBook.setLayout(new GridLayout(3,0));
                    JPanel priceBook1 = new JPanel();
                    containJTextFieldPriceOfBook.add(priceBook1);
                    priceOfBook = new JTextField(20);
                    priceOfBook.setEditable(false);
                    containJTextFieldPriceOfBook.add(priceOfBook);
                    JPanel priceBook2= new JPanel();
                    containJTextFieldPriceOfBook.add(priceBook2);
                    containPriceOfBook.add(containJTextFieldPriceOfBook);
                    colLeftContainInformation.add(containPriceOfBook);

                    //Row 4
                    JPanel containTypeOfBook = new JPanel();
                    containTypeOfBook.setLayout(new GridLayout(0,2));
                    containTypeOfBook.add(new JLabel("Type of book: ", SwingConstants.RIGHT));
                    JPanel containJTextFieldTypeOfBook = new JPanel();
                    containJTextFieldTypeOfBook.setLayout(new GridLayout(3,0));
                    JPanel typeBook1 = new JPanel();
                    containJTextFieldTypeOfBook.add(typeBook1);
                    typeOfBook = new JTextField(20);
                    typeOfBook.setEditable(false);
                    containJTextFieldTypeOfBook.add(typeOfBook);
                    JPanel typeBook2= new JPanel();
                    containJTextFieldTypeOfBook.add(typeBook2);
                    containTypeOfBook.add(containJTextFieldTypeOfBook);
                    colLeftContainInformation.add(containTypeOfBook);

                    //Row 5
                    JPanel containDateBorrow = new JPanel();
                    containDateBorrow.setLayout(new GridLayout(0,2));
                    containDateBorrow.add(new JLabel("Date borrow: ", SwingConstants.RIGHT));
                    JPanel containDateBorrowSection = new JPanel();
                    containDateBorrowSection.setLayout(new GridLayout(0,3));
                        JPanel containDayComboBox = new JPanel();
                        containDayComboBox.setLayout(new GridLayout(3,0));
                        JPanel boxOfComboBox1 = new JPanel();
                        containDayComboBox.add(boxOfComboBox1);
                        containDayComboBox.add(day = new JComboBox());
                        day.setEnabled(false);
                        JPanel boxOfComboBox2 = new JPanel();
                        containDayComboBox.add(boxOfComboBox2);
                        containDateBorrowSection.add(containDayComboBox);

                        JPanel containMonthComboBox = new JPanel();
                        containMonthComboBox.setLayout(new GridLayout(3,0));
                        JPanel boxOfComboBoxMonth1 = new JPanel();
                        containMonthComboBox.add(boxOfComboBoxMonth1);
                        containMonthComboBox.add(month = new JComboBox());
                        month.setEnabled(false);
                        JPanel boxOfComboBoxMonth2 = new JPanel();
                        containMonthComboBox.add(boxOfComboBoxMonth2);
                        containDateBorrowSection.add(containMonthComboBox);

                        JPanel containYearComboBox = new JPanel();
                        containYearComboBox.setLayout(new GridLayout(3,0));
                        JPanel boxOfComboBoxYear1 = new JPanel();
                        containYearComboBox.add(boxOfComboBoxYear1);
                        containYearComboBox.add(year = new JComboBox());
                        year.setEnabled(false);
                        JPanel boxOfComboBoxYear2 = new JPanel();
                        containYearComboBox.add(boxOfComboBoxYear2);
                        containDateBorrowSection.add(containYearComboBox);
                    containDateBorrow.add(containDateBorrowSection);
                    colLeftContainInformation.add(containDateBorrow);

                    //Row 6
                    JPanel containDateReturn = new JPanel();
                    containDateReturn.setLayout(new GridLayout(0,2));
                    containDateReturn.add(new JLabel("3 Day: ", SwingConstants.RIGHT));
                    JPanel containRadioButton = new JPanel();
                    containRadioButton.setLayout(new GridLayout(0,2));
                    JPanel leftColContainTwoButton = new JPanel();
                    leftColContainTwoButton.setLayout(new GridLayout(0,2));
                    ButtonGroup radioGroup = new ButtonGroup();
                    radioBtn1 = new JRadioButton("Still",true);
                    radioBtn1.setEnabled(false);
                    radioBtn1.setForeground(Color.GREEN);
                    radioGroup.add(radioBtn1);
                    leftColContainTwoButton.add(radioBtn1);
                    radioBtn2 = new JRadioButton("Late");
                    radioBtn2.setEnabled(false);
                    radioBtn2.setForeground(Color.RED);
                    radioGroup.add(radioBtn2);
                    leftColContainTwoButton.add(radioBtn2);
                    containRadioButton.add(leftColContainTwoButton);
                    JPanel rightPanelRadioBtn = new JPanel();
                    containRadioButton.add(rightPanelRadioBtn);
                    containDateReturn.add(containRadioButton);
                    colLeftContainInformation.add(containDateReturn);
                topPanelContainInformation.add(colLeftContainInformation);

                JPanel colRightContainImage = new JPanel();
                colRightContainImage.setBorder(new EmptyBorder(10,100,10,100));
                colRightContainImage.setLayout(new BorderLayout());
                whereContainImageAboutBook = new JLabel("Don't have any image :(", SwingConstants.CENTER);
                whereContainImageAboutBook.setBorder(BorderFactory.createLineBorder(Color.blue));
                colRightContainImage.add(whereContainImageAboutBook, BorderLayout.CENTER);
                JLabel displayNameBelowImage = new JLabel("Choose a row.", SwingConstants.CENTER);
                colRightContainImage.add(displayNameBelowImage, BorderLayout.SOUTH);
                topPanelContainInformation.add(colRightContainImage);
            belowLabel.add(topPanelContainInformation);

            JPanel bottomPanelContainTable = new JPanel();
            bottomPanelContainTable.setBorder(new EmptyBorder(10,0,0,0));
            bottomPanelContainTable.setLayout(new BorderLayout());
            DefaultTableModel testModel = new DefaultTableModel(column,0);
            jt = new JTable(testModel) {
                public boolean editCellAt(int row, int column, EventObject eventObject) {return false;}
            };
            jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(jt);
            jt.setFillsViewportHeight(true);
            bottomPanelContainTable.add(scrollPane,BorderLayout.CENTER);
            belowLabel.add(bottomPanelContainTable);
        add(belowLabel, BorderLayout.CENTER);

    }
    private void getDayOfMoth() {
    }
    private void showRadioButton() {}
    public void showBorrowPanel() {
        cardLayout.show(cardPanel, HomeUserPanel.BORROWBOOKCARD);
    }
}
