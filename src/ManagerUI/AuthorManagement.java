package ManagerUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.EventObject;

public class AuthorManagement extends JPanel {
    //This is to add the jtree of the author management
    private CardLayout cardLayout;
    private JPanel cardPanel;

    //Display image
    JLabel displayImageLabel;

    //Display name
    JTextField nameAuthor;
    //Cb box display day of birth
    JComboBox day,month,year;
    //Btn add, update, delete
    JButton addBtn, updateBtn, deleteBtn;

    //Add to cardPanel
        //JTable
        JTable jt;
        String[] column = {"Id author", "Name author", "Day of birth"};

        //Find field, Button find
        JTextField findTf;
        JButton btnFind;

        //Panel contain component above
        JPanel containJTablePanel;
    public AuthorManagement(ManagerUI cardLayout) {
        this.cardLayout = cardLayout.getCardLayout();
        this.cardPanel = cardLayout.getCardPanel();
        setLayout(new GridLayout(2,0));
        JPanel containImageOfAuthor = new JPanel();
        containImageOfAuthor.setLayout(new GridLayout(0,3));
            containImageOfAuthor.add(new JLabel(""));
            containImageOfAuthor.add(displayImageLabel = new JLabel("Don't have image here :(", SwingConstants.CENTER));
            displayImageLabel.setBorder(BorderFactory.createLineBorder(Color.blue));
            containImageOfAuthor.add(new JLabel(""));
        add(containImageOfAuthor);

        JPanel containInforOfAuthor = new JPanel();
        containInforOfAuthor.setLayout(new GridLayout(0,3));
            JPanel leftOfContainInforAuthor = new JPanel();
            containInforOfAuthor.add(leftOfContainInforAuthor);

            JPanel centerOfContainInforAuthor = new JPanel();
            centerOfContainInforAuthor.setLayout(new GridLayout(2,0));
                //infor section
                JPanel inforRow = new JPanel();
                inforRow.setLayout(new GridLayout(3,0));
                    JPanel rowName1 = new JPanel();
                    rowName1.setBorder(new EmptyBorder(10,0,0,10));
                    rowName1.setLayout(new GridLayout(0,2));
                    rowName1.add(new JLabel("Name of author: ", SwingConstants.RIGHT));
                    rowName1.add(nameAuthor = new JTextField(20));
                    inforRow.add(rowName1);

                    JPanel rowDayOfBirth = new JPanel();
                    rowDayOfBirth.setBorder(new EmptyBorder(10,0,0,10));
                    rowDayOfBirth.setLayout(new GridLayout(0,2));
                    rowDayOfBirth.add(new JLabel("Day of birth: ", SwingConstants.RIGHT));
                    JPanel containThreeCbBox = new JPanel();
                    containThreeCbBox.setLayout(new GridLayout(0,3));
                        containThreeCbBox.add(day = new JComboBox());
                        day.setBorder(new EmptyBorder(0,0,0,10));
                        containThreeCbBox.add(month = new JComboBox());
                        month.setBorder(new EmptyBorder(0,0,0,10));
                        containThreeCbBox.add(year = new JComboBox());
                        year.setBorder(new EmptyBorder(0,0,0,0));
                    rowDayOfBirth.add(containThreeCbBox);
                    inforRow.add(rowDayOfBirth);
                    //Btn panel
                    JPanel containButton = new JPanel();
                    containButton.setBorder(new EmptyBorder(10,0,0,10));
                    containButton.setLayout(new GridLayout(0,3));
                        containButton.add(addBtn = new JButton("Add"));
                        containButton.add(updateBtn = new JButton("Update"));
                        containButton.add(deleteBtn = new JButton("Delete"));
                    inforRow.add(containButton);
                centerOfContainInforAuthor.add(inforRow);

                JPanel filterRow = new JPanel();
                centerOfContainInforAuthor.add(filterRow);
            containInforOfAuthor.add(centerOfContainInforAuthor);
            JPanel rightOfContainInforAuthor = new JPanel();
            containInforOfAuthor.add(rightOfContainInforAuthor);
        add(containInforOfAuthor);
        createJTablePanel();
    }

    private void createJTablePanel() {
        containJTablePanel = new JPanel();
        containJTablePanel.setBorder(new EmptyBorder(0,20,10,20));
        //containJTablePanel.setBorder(BorderFactory.createTitledBorder(ManagerUI.AUTHORTAB));
        containJTablePanel.setLayout(new BorderLayout());
        JPanel containAllFieldWithTitleBorder = new JPanel();
        containAllFieldWithTitleBorder.setBorder(BorderFactory.createTitledBorder(ManagerUI.AUTHORTAB));
        containAllFieldWithTitleBorder.setLayout(new BorderLayout());
        containJTablePanel.add(containAllFieldWithTitleBorder);
            JPanel containFindField = new JPanel();
            containFindField.setLayout(new GridLayout(0,3));
                containFindField.add(new JLabel(""));
                containFindField.add(findTf = new JTextField());
                JPanel containFindButton = new JPanel();
                containFindButton.setLayout(new GridLayout(0,6));
                    containFindButton.add(btnFind =  new JButton("Find"));
                    containFindButton.add(new JLabel(""));
                    containFindButton.add(new JLabel(""));
                    containFindButton.add(new JLabel(""));
                    containFindButton.add(new JLabel(""));
                    containFindButton.add(new JLabel(""));
                containFindField.add(containFindButton);
            containAllFieldWithTitleBorder.add(containFindField, BorderLayout.NORTH);

            JPanel containJTable = new JPanel();
            containJTable.setBorder(new EmptyBorder(10,0,0,0));
            containJTable.setLayout(new GridLayout(0,3));
                containJTable.add(new JLabel(""));
                DefaultTableModel model = new DefaultTableModel(column, 0);
                jt = new JTable(model) {
                    public boolean editCellAt(int row, int column, EventObject eventObject) {return false;}
                };
                jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                JScrollPane scrollPane = new JScrollPane(jt);
                jt.setFillsViewportHeight(true);
                containJTable.add(scrollPane);
                containJTable.add(new JLabel(""));
            containAllFieldWithTitleBorder.add(containJTable, BorderLayout.CENTER);
            cardPanel.add(containJTablePanel, ManagerUI.AUTHORTAB);
    }
    public void showAuthorManagementPanel() {
        cardLayout.show(cardPanel, ManagerUI.AUTHORTAB);
    }

    public void clearUI() {
        displayImageLabel.setText("");
        nameAuthor.setText("");
        day.setSelectedIndex(-1);
        month.setSelectedIndex(-1);
        year.setSelectedIndex(-1);
        findTf.setText("");
    }

}
