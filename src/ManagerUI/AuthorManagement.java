package ManagerUI;

import DB.DBConnect;
import JFileChooserCustom.ImageFileView;
import JFileChooserCustom.ImageFilter;
import JFileChooserCustom.ImagePreview;
import ObjectZZ.Author;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;

public class AuthorManagement extends JPanel {
    DBConnect workingWithDB;
    //This is to add the jtree of the author management
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private ManagerUI uiMain;
    //Display image
    JLabel displayImageLabel;

    //Display name
    JTextField nameAuthor;
    //Cb box display day of birth
    JComboBox day,month,year;
    String[] dayData, monthData,yearData;
    //Btn add, update, delete
    JButton addBtn, updateBtn, deleteBtn;

    //Add to cardPanel
        //JTable
        JTable jt;
        String[] column = {"Id author", "Name author", "Day of birth"};
        VstTableItemModelAuthor authorTableModel;
        ArrayList<Author> authorList;
        TableRowSorter<VstTableItemModelAuthor> sorter;
        //Find field, Button find
        JTextField findTf;
        JButton btnUpload;
        //JFileChooser
        private JFileChooser fc;
        private File imgFile;
        private InputStream imageDisplay;
        private Blob imageInsertSQL;
        //Panel contain component above
        JPanel containJTablePanel;
        Connection getConnect;
        PreparedStatement pstmt;
        //Id sql
        int idSql = -1;
    public AuthorManagement(ManagerUI cardLayout) {
        this.workingWithDB = new DBConnect(3306,"javafinalimportant","adminjava","Admin1234@");
        getConnect = workingWithDB.getConn();
        createThreeDataInCBBox();
        this.cardLayout = cardLayout.getCardLayout();
        this.cardPanel = cardLayout.getCardPanel();
        this.uiMain = cardLayout;
        setLayout(new GridLayout(2,0));
        JPanel containImageOfAuthor = new JPanel();
        containImageOfAuthor.setLayout(new GridLayout(0,3));
            containImageOfAuthor.add(new JLabel(""));
            containImageOfAuthor.add(displayImageLabel = new JLabel("Don't have image here :(", SwingConstants.CENTER));
            displayImageLabel.setBorder(BorderFactory.createLineBorder(Color.blue));

            JPanel rightOfImage = new JPanel();
            rightOfImage.setLayout(new GridLayout(2,0));
                JPanel row1OfButtonUpload = new JPanel();
                row1OfButtonUpload.setBorder(new EmptyBorder(0,10,0,0));
                row1OfButtonUpload.setLayout(new GridLayout(0,4));
                    JPanel firstCol = new JPanel();
                    firstCol.setLayout(new GridLayout(5,0));
                        firstCol.add(new JLabel(""));
                        firstCol.add(new JLabel(""));
                        firstCol.add(new JLabel(""));
                        firstCol.add(new JLabel(""));
                        firstCol.add(btnUpload = new JButton("Upload"));
                        btnUpload.setActionCommand("Upload image");
                        btnUpload.addActionListener(new ButtonListener());
                    row1OfButtonUpload.add(firstCol);
                    row1OfButtonUpload.add(new JLabel(""));
                    row1OfButtonUpload.add(new JLabel(""));
                    row1OfButtonUpload.add(new JLabel(""));
                rightOfImage.add(row1OfButtonUpload);
                rightOfImage.add(new JLabel(""));
            containImageOfAuthor.add(rightOfImage);
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
                        containThreeCbBox.add(day = new JComboBox(dayData));
                        day.setSelectedIndex(-1);
                        day.setActionCommand("Day");
                        day.addActionListener(new ButtonListener());
                        day.setBorder(new EmptyBorder(0,0,0,10));
                        containThreeCbBox.add(month = new JComboBox(monthData));
                        month.setSelectedIndex(-1);
                        month.setActionCommand("Month");
                        month.addActionListener(new ButtonListener());
                        month.setBorder(new EmptyBorder(0,0,0,10));
                        containThreeCbBox.add(year = new JComboBox(yearData));
                        year.setSelectedIndex(-1);
                        year.setActionCommand("Year");
                        year.addActionListener(new ButtonListener());
                        year.setBorder(new EmptyBorder(0,0,0,0));
                    rowDayOfBirth.add(containThreeCbBox);
                    inforRow.add(rowDayOfBirth);
                    //Btn panel
                    JPanel containButton = new JPanel();
                    containButton.setBorder(new EmptyBorder(10,0,0,10));
                    containButton.setLayout(new GridLayout(0,3));
                        containButton.add(addBtn = new JButton("Add"));
                        addBtn.setActionCommand("Add");
                        addBtn.addActionListener(new ButtonListener());
                        containButton.add(updateBtn = new JButton("Update"));
                        updateBtn.setActionCommand("Update");
                        updateBtn.addActionListener(new ButtonListener());
                        containButton.add(deleteBtn = new JButton("Delete"));
                        deleteBtn.setActionCommand("Delete");
                        deleteBtn.addActionListener(new ButtonListener());
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
                    containFindButton.add(new JLabel("Find", SwingConstants.CENTER));
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
                authorList = initializeAuthor();
                authorTableModel = new VstTableItemModelAuthor(authorList,column);
                jt = new JTable(authorTableModel) {
                    public boolean editCellAt(int row, int column, EventObject eventObject) {return false;}
                };
                sorter = new TableRowSorter<>(authorTableModel);
                jt.setRowSorter(sorter);
                findTf.addKeyListener(new KeyListenerFind());
                jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                jt.addMouseListener(new ClickRowListener());
                JScrollPane scrollPane = new JScrollPane(jt);
                jt.setFillsViewportHeight(true);
                containJTable.add(scrollPane);
                containJTable.add(new JLabel(""));
            containAllFieldWithTitleBorder.add(containJTable, BorderLayout.CENTER);
            cardPanel.add(containJTablePanel, ManagerUI.AUTHORTAB);
    }
    public void createThreeDataInCBBox() {
        this.dayData = new String[31];
        this.monthData = new String[12];
        this.yearData = new String[71];
        for(int i = 0;i<=30;i++) {
            dayData[i] = String.valueOf(i+1);
        }

        for(int i = 0;i<=11;i++) {
            monthData[i] = String.valueOf(i+1);
        }
        for(int i =0;i<=70;i++) {
            yearData[i] = String.valueOf(1950 + i + 1);
        }

    }
    public void showAuthorManagementPanel() {
        cardLayout.show(cardPanel, ManagerUI.AUTHORTAB);
        this.uiMain.clearManagerUI();
    }

    public void clearUI() {
        displayImageLabel.setIcon(null);
        nameAuthor.setText("");
        day.setSelectedIndex(-1);
        month.setSelectedIndex(-1);
        year.setSelectedIndex(-1);
        findTf.setText("");
    }

    private Image fitImage(Image img, int w, int h) {
        BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();
        return resizedImage;
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
            try {
                imageInsertSQL = new SerialBlob(Files.readAllBytes(imgFile.toPath()));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedImage img = null;
            try {
                img = ImageIO.read(imgFile);
            }catch (Exception e) {e.printStackTrace();}
            ImageIcon imageIcon = new ImageIcon(fitImage(img,displayImageLabel.getWidth(),displayImageLabel.getHeight()));
            displayImageLabel.setIcon(imageIcon);
        } else {
            System.out.println("Attachment cancelled by user.");
        }
        //Reset the file chooser for the next time it's shown.
        fc.setSelectedFile(null);
    }
    private class ClickRowListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            int i = jt.getSelectedRow();
            TableModel zz = jt.getModel();
            idSql = Integer.valueOf(authorTableModel.getValueAt(i,4).toString());
            nameAuthor.setText(authorTableModel.getValueAt(i,1).toString());
            String[] splitString = authorTableModel.getValueAt(i,2).toString().split("/");

            for(int d = 0; d<day.getItemCount();d++) {
                if(splitString[0] == day.getItemAt(d)) {
                    day.setSelectedIndex(d);
                    break;
                }
            }
            for(int m = 0; m <month.getItemCount();m++) {
                if(splitString[1] == month.getItemAt(m)) {
                    month.setSelectedIndex(m);
                    break;
                }
            }
            for(int y =0;y<year.getItemCount();y++) {
                if(splitString[2] == year.getItemAt(y)) {
                    System.out.println(true);
                    year.setSelectedIndex(y);
                    break;
                }
            }
            imageDisplay = new ByteArrayInputStream((byte[]) authorTableModel.getValueAt(i,3));
            try {
                imageInsertSQL = new SerialBlob((byte[]) authorTableModel.getValueAt(i,3));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            BufferedImage img = null;
            try {
                img = ImageIO.read(imageDisplay);
            }catch (Exception e) {e.printStackTrace();}
            ImageIcon imageIcon = new ImageIcon(fitImage(img,displayImageLabel.getWidth(),displayImageLabel.getHeight()));
            displayImageLabel.setIcon(imageIcon);
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
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() instanceof JButton) {
                JButton zz = (JButton) actionEvent.getSource();
                switch (zz.getActionCommand()) {
                    case "Upload image":
                        displayChooseFile();
                        break;
                    case "Add":
                        if(validateField()) {
                            addAuthor();
                            clearUI();
                            uiMain.refreshCBAuthor();
                        }
                        break;
                    case "Update":
                        if(idSql != -1) {
                            if(validateField()) {
                                updateAuthor();
                                clearUI();
                            }
                        } else {
                            JOptionPane.showMessageDialog(getParent(), "Choose a row before update.");
                        }
                        break;
                    case "Delete":
                        if(idSql != -1) {
                            deleteAuthor();
                            clearUI();
                        } else {
                            JOptionPane.showMessageDialog(getParent(), "Choose a row before update.");
                        }
                        break;
                }
            }
        }

        private boolean validateField() {
            if(nameAuthor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(getParent(),"Name can not be empty.");
                return false;
            } else if(day.getSelectedIndex() == -1 || month.getSelectedIndex() == -1 || year.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(getParent(), "Please fill all the day of birth.");
                return false;
            } else if (imgFile == null) {
                JOptionPane.showMessageDialog(getParent(), "Please choose a file.");
                return false;
            } else if(nameAuthor.getText().isEmpty() && day.getSelectedIndex() == -1 && month.getSelectedIndex() == -1 && year.getSelectedIndex() == -1 && imgFile == null) {
                JOptionPane.showMessageDialog(getParent(), "Please fill all the field.");
                return false;
            } else {
                return true;
            }
        }
        private void addAuthor() {
            Date dateAdd = new Date();
            dateAdd.setDate(Integer.parseInt(day.getSelectedItem().toString()));
            dateAdd.setMonth(Integer.parseInt(month.getSelectedItem().toString()) - 1);
            dateAdd.setYear(Integer.parseInt(year.getSelectedItem().toString()) - 1900);
            //Add to db
            getConnect = workingWithDB.getConn();
            try {
                pstmt = getConnect.prepareStatement("INSERT INTO AuthorInfor(NAMEAUTHOR, DATEOFBIRTH, IMGAUTHOR) VALUES (?,?,?)");
                pstmt.setString(1,nameAuthor.getText());
                java.sql.Date dateInsertToSQL = new java.sql.Date(dateAdd.getTime());
                pstmt.setDate(2, dateInsertToSQL);
                pstmt.setBlob(3,imageDisplay);
                pstmt.executeUpdate();
            }catch (Exception ex) {ex.printStackTrace();}
            clearTheTableAfterOperation();
        }

        private void updateAuthor() {
            Date dateAdd = new Date();
            dateAdd.setDate(Integer.parseInt(day.getSelectedItem().toString()));
            dateAdd.setMonth(Integer.parseInt(month.getSelectedItem().toString()) - 1);
            dateAdd.setYear(Integer.parseInt(year.getSelectedItem().toString()) - 1900);
            getConnect = workingWithDB.getConn();
            try {
                pstmt = getConnect.prepareStatement("UPDATE AuthorInfor SET NameAuthor=?, DateOfBirth=?,imgAuthor=? WHERE AuthorID=?");
                pstmt.setString(1,nameAuthor.getText());
                java.sql.Date dateUpdateToSQL = new java.sql.Date(dateAdd.getTime());
                pstmt.setDate(2,dateUpdateToSQL);
                pstmt.setBlob(3, imageInsertSQL);
                pstmt.setInt(4,idSql);
                pstmt.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            clearTheTableAfterOperation();
        }

        private void deleteAuthor() {
            getConnect = workingWithDB.getConn();
            try {
                pstmt = getConnect.prepareStatement("DELETE FROM AuthorInfor WHERE AuthorID=?");
                pstmt.setInt(1,idSql);
                pstmt.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            clearTheTableAfterOperation();
        }
    }

    public void clearTheTableAfterOperation() {
        authorList = initializeAuthor();
        authorTableModel.setObject(authorList);
        authorTableModel.refresh(authorList);
    }
    public ArrayList<Author> initializeAuthor() {
        ArrayList<Author> zauZau = new ArrayList<>();
        int idDisplay = 0;
        workingWithDB.setQuery("SELECT * FROM AuthorInfor");
        ResultSet zz = workingWithDB.selectFromDB();
        try {
            while (zz.next()) {
                String nameAuthor = zz.getString("NameAuthor");
                int authorIdInMysql = zz.getInt("AuthorID");
                Date dateOfBirth = zz.getDate("DateOfBirth");
                byte[] bytes = zz.getBytes("imgAuthor");
                idDisplay++;
                Author goGo = new Author(authorIdInMysql,nameAuthor,dateOfBirth,bytes);
                goGo.setIdAuthor(idDisplay);
                zauZau.add(goGo);
            }
        }catch (Exception ex) {ex.printStackTrace();}
        return zauZau;
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
            findBook(findTf.getText());
        }

        private void findBook(String searchName) {
            sorter.setRowFilter(RowFilter.regexFilter(searchName));
        }
    }
}
