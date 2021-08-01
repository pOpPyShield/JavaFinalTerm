package UI;

import ObjectZZ.RegisterAndLogin;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class UIMain extends JFrame {
    //Card layout
    JPanel cardLayout;

    //JLabel of card register
    JLabel labelCenter;
    //Login panel
        //JTextField
        JTextField userNameTf;
        //JPasswordField
        JPasswordField passwordTf;
        //JButton
        JButton btnLogin;
    //Register panel
        //JTextField
            JTextField tfUserNameReg;
        //JPasswordField
            JPasswordField tfPasswordReg, tfPasswordAgain;
        //JRadio button
            JRadioButton radioBtn1, radioBtn2;
        //Button group
            ButtonGroup buttonGroupReg;
        //JButton
            JButton btnReg;
    public UIMain() throws IOException {
        //setLayout(new GridLayout(0,2));
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        URL url = getClass().getResource("/Pictures/jframeIcon.png");
        ImageIcon icon = new ImageIcon(url);
        setIconImage(icon.getImage());
        setTitle("Library Management");

        cardLayout = new JPanel();
        cardLayout.setLayout(new CardLayout());

        JPanel card1 = new JPanel();
        card1.setLayout(new GridLayout(0, 2));
        BackgroundPane leftPanel = new BackgroundPane();
        leftPanel.setBackground(ImageIO.read(new File("/home/huygrogbro/IdeaProjects/FinalTerm/src/Pictures/leftPanelBg.jpeg")));
        leftPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        card1.add(leftPanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1));

            //Inside right panel, first row of grid layout
            BackgroundPane insideRightPanelFirstRow = new BackgroundPane();
            insideRightPanelFirstRow.setBackground(ImageIO.read(new File("/home/huygrogbro/IdeaProjects/FinalTerm/src/Pictures/wave.png")));
            insideRightPanelFirstRow.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            ImageIcon imgIconInsideFirstRow = new ImageIcon(url);
            JLabel componentImageOfCenterOfJPanel = new JLabel(imgIconInsideFirstRow, JLabel.CENTER);
            JLabel wordBelowImage = new JLabel("Huy's Library");
            Font wordBelowImageFont = new Font("Open Sans", Font.ITALIC, 50);
            wordBelowImage.setFont(wordBelowImageFont);
            insideRightPanelFirstRow.add(componentImageOfCenterOfJPanel, gbc);

            gbc.insets = new Insets(10, 0, 0, 0); //TOp padding
            insideRightPanelFirstRow.add(wordBelowImage, gbc);

            //Inside right panel, second row of grid layout
            JPanel insideRightPanelSecondRow = new JPanel();
            insideRightPanelSecondRow.setLayout(new BoxLayout(insideRightPanelSecondRow, BoxLayout.Y_AXIS));
                JPanel secondRowFirst = new JPanel();
                secondRowFirst.setLayout(new BoxLayout(secondRowFirst, BoxLayout.Y_AXIS));
                    JPanel box1 = new JPanel();
                    box1.setLayout(new GridLayout(2,2));
                        JPanel containComponentInFirstRow = new JPanel();
                        JLabel userNameDisplay = new JLabel("Enter your username: ", SwingConstants.RIGHT);
                        Font fontUserNameDisplay = new Font("Helvetica", Font.BOLD, 10);
                        userNameDisplay.setFont(fontUserNameDisplay);
                        containComponentInFirstRow.add(userNameDisplay);
                        box1.add(containComponentInFirstRow);

                        JPanel containComponentInSecondRow = new JPanel();
                        userNameTf = new JTextField(20);
                        userNameTf.setPreferredSize(new Dimension(20,20));
                        Font fontUserNameTf = new Font("SansSerif", Font.BOLD,10);
                        userNameTf.setFont(fontUserNameTf);
                        containComponentInSecondRow.add(userNameTf);
                        box1.add(containComponentInSecondRow);

                        JPanel containComponentInThird = new JPanel();
                        JLabel passwordDisplay = new JLabel("Enter your password: ",SwingConstants.RIGHT);
                        passwordDisplay.setFont(fontUserNameDisplay);
                        containComponentInThird.add(passwordDisplay);
                        box1.add(containComponentInThird);

                        JPanel containComponentInFourth = new JPanel();
                        passwordTf = new JPasswordField(20);
                        passwordTf.setPreferredSize(new Dimension(20,20));
                        containComponentInFourth.add(passwordTf);
                        box1.add(containComponentInFourth);
                    JPanel box2 = new JPanel();
                            JPanel handleButton1 = new JPanel();
                            btnLogin = new JButton("Login");
                            btnLogin.setActionCommand("handle login");
                            btnLogin.addActionListener(new ButtonActionListener());
                            btnLogin.setPreferredSize(new Dimension(150, 60));
                            handleButton1.add(btnLogin);
                            box2.add(handleButton1);

                            JPanel handleButton2 = new JPanel();
                            JButton button2 = new JButton("Register");
                            button2.setActionCommand("register switch");
                            button2.addActionListener(new ButtonActionListener());
                            button2.setPreferredSize(new Dimension(150, 60));
                            handleButton2.add(button2);
                            box2.add(handleButton2);
                secondRowFirst.add(box1);
                secondRowFirst.add(box2);
                JPanel secondRowSecond = new JPanel();
                insideRightPanelSecondRow.add(secondRowFirst);
                insideRightPanelSecondRow.add(secondRowSecond);

        rightPanel.add(insideRightPanelFirstRow);
        rightPanel.add(insideRightPanelSecondRow);
        card1.add(rightPanel);
        cardLayout.add(card1, "Login panel");

        //Card2
        JPanel card2 = new JPanel();
        card2.setLayout(new BorderLayout());
            //Jpanel at north of card2
            JPanel panelContaiButton = new JPanel();
            panelContaiButton.setLayout(new BorderLayout());
            panelContaiButton.setBorder(BorderFactory.createEmptyBorder(30,40,20,0));
                JButton buttonBackToLogin = new JButton("Back");
                buttonBackToLogin.setPreferredSize(new Dimension(100,50));
                buttonBackToLogin.setActionCommand("login switch");
                buttonBackToLogin.addActionListener(new ButtonActionListener());
            //Button at left
            panelContaiButton.add(buttonBackToLogin, BorderLayout.LINE_START);

            //Jpanel at center of card2
            JPanel panelCenter = new JPanel();
            panelCenter.setLayout(new BorderLayout());
            labelCenter = new JLabel("Register user", SwingConstants.CENTER);
            labelCenter.setFont(new Font("SansSerif", Font.ITALIC,40));
            labelCenter.setForeground(Color.RED);
            panelCenter.add(labelCenter, BorderLayout.NORTH);
                JPanel panelBelowLabel = new JPanel();
                panelBelowLabel.setLayout(new GridLayout(2,0));
                    //Row 1 is grid with 3 column
                    JPanel row1 = new JPanel();
                    row1.setLayout(new GridLayout(0,3));
                        //col 1 is default
                        JPanel col1 = new JPanel();
                        row1.add(col1);
                        //col 2 is contain input and label
                        JPanel col2 = new JPanel();
                        col2.setLayout(new GridLayout(4,0));

                            //Row 1 contain username section
                            JPanel containUserNameRow1 = new JPanel();
                            containUserNameRow1.setLayout(new BorderLayout());
                            containUserNameRow1.add(new JLabel("UserName: ", SwingConstants.CENTER), BorderLayout.LINE_START);
                            JPanel containUserNameField = new JPanel();
                            containUserNameField.setLayout(new GridLayout(3,0));
                            JPanel topDefault = new JPanel();
                            containUserNameField.add(topDefault);
                            containUserNameField.add(tfUserNameReg = new JTextField(20));
                            JPanel bottomDefault = new JPanel();
                            containUserNameField.add(bottomDefault);
                            containUserNameRow1.add(containUserNameField, BorderLayout.CENTER);
                            col2.add(containUserNameRow1);

                            //Row 2 contain password section
                            JPanel containPasswordRow2 = new JPanel();
                            containPasswordRow2.setLayout(new BorderLayout());
                            containPasswordRow2.add(new JLabel("Password: ", SwingConstants.CENTER), BorderLayout.LINE_START);
                            JPanel containPasswordField = new JPanel();
                            containPasswordField.setLayout(new GridLayout(3,0));
                            JPanel topDefault2 = new JPanel();
                            containPasswordField.add(topDefault2);
                            containPasswordField.add(tfPasswordReg = new JPasswordField(20));
                            JPanel bottomDefault2 = new JPanel();
                            containPasswordField.add(bottomDefault2);
                            containPasswordRow2.add(containPasswordField, BorderLayout.CENTER);
                            col2.add(containPasswordRow2);

                            //Row 3 contain password again section
                            JPanel containPasswordAgainRow3 = new JPanel();
                            containPasswordAgainRow3.setLayout(new BorderLayout());
                            containPasswordAgainRow3.add(new JLabel("Enter again: ", SwingConstants.CENTER), BorderLayout.LINE_START);
                            JPanel containPasswordAgainField = new JPanel();
                            containPasswordAgainField.setLayout(new GridLayout(3,0));
                            JPanel topDefault3 = new JPanel();
                            containPasswordAgainField.add(topDefault3);
                            containPasswordAgainField.add(tfPasswordAgain = new JPasswordField(20));
                            JPanel bottomDefault3 = new JPanel();
                            containPasswordAgainField.add(bottomDefault3);
                            containPasswordAgainRow3.add(containPasswordAgainField, BorderLayout.CENTER);
                            col2.add(containPasswordAgainRow3);

                            //Row 4 contain radio button section
                            JPanel containRadioButton = new JPanel();
                            containRadioButton.setLayout(new BorderLayout());
                            containRadioButton.add(new JLabel("Type of user: ",SwingConstants.CENTER), BorderLayout.LINE_START);
                            JPanel containRadioButtonField = new JPanel();
                            containRadioButtonField.setLayout(new GridLayout(3,0));
                            JPanel topDefaul4 = new JPanel();
                            containRadioButtonField.add(topDefaul4);
                            JPanel centerCustom4 = new JPanel();
                            radioBtn1 = new JRadioButton("User", true);
                            radioBtn1.setActionCommand("user");
                            radioBtn2 = new JRadioButton("Manager");
                            radioBtn2.setActionCommand("manager");
                            buttonGroupReg = new ButtonGroup();
                            buttonGroupReg.add(radioBtn1);
                            buttonGroupReg.add(radioBtn2);
                            centerCustom4.add(radioBtn1);
                            centerCustom4.add(radioBtn2);
                            containRadioButtonField.add(centerCustom4);
                            JPanel bottomDefault4 = new JPanel();
                            containRadioButtonField.add(bottomDefault4);
                            containRadioButton.add(containRadioButtonField, BorderLayout.CENTER);
                            col2.add(containRadioButton);

                        row1.add(col2);
                        //col 3 is default
                        JPanel col3 = new JPanel();
                        row1.add(col3);
                    //Row2
                    JPanel row2 = new JPanel();
                    row2.setLayout(new BorderLayout());
                        JPanel northOfRow2 = new JPanel();
                        northOfRow2.setLayout(new GridLayout(0,3));

                            //Col 1
                            JPanel leftDefaultNorthOfRow2 = new JPanel();
                            northOfRow2.add(leftDefaultNorthOfRow2);

                            //Col 2
                            JPanel centerCustomNorthOfRow2 = new JPanel();
                            centerCustomNorthOfRow2.setLayout(new GridLayout(0,3));

                                //Col 1
                                JPanel leftOfCenterCol = new JPanel();
                                centerCustomNorthOfRow2.add(leftOfCenterCol);

                                //Col 2
                                centerCustomNorthOfRow2.add(btnReg = new JButton("Register"));
                                btnReg.setActionCommand("handle register");
                                btnReg.addActionListener(new ButtonActionListener());
                                btnReg.setPreferredSize(new Dimension(100,40));
                                //Col 3
                                JPanel rightOfCenterCol = new JPanel();
                                centerCustomNorthOfRow2.add(rightOfCenterCol);
                                northOfRow2.add(centerCustomNorthOfRow2);
                            //Col 3
                            JPanel rightDefaultNorthOfRow2 = new JPanel();
                            northOfRow2.add(rightDefaultNorthOfRow2);
                        row2.add(northOfRow2, BorderLayout.NORTH);
                    panelBelowLabel.add(row1);
                    panelBelowLabel.add(row2);
                panelCenter.add(panelBelowLabel, BorderLayout.CENTER);
        card2.add(panelCenter, BorderLayout.CENTER);
        card2.add(panelContaiButton, BorderLayout.NORTH);
        cardLayout.add(card2, "Register panel");
        add(cardLayout);

        //Debug
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButton button = (JButton) actionEvent.getSource();
            CardLayout card = (CardLayout) (cardLayout.getLayout());
            RegisterAndLogin validateCheck;
            switch (button.getActionCommand()) {
                //First frame of application
                case "register switch":
                    System.out.println("Click on register");
                    card.show(cardLayout, "Register panel");
                    tfUserNameReg.setText("");
                    tfPasswordReg.setText("");
                    tfPasswordAgain.setText("");
                    radioBtn1.setSelected(true);
                    break;
                case "login switch":
                    System.out.println("Click on back to login");
                    card.show(cardLayout,"Login panel");
                    userNameTf.setText("");
                    passwordTf.setText("");
                    break;
                case "handle register":
                    validateCheck = new RegisterAndLogin(tfUserNameReg.getText(),tfPasswordReg.getText(),tfPasswordAgain.getText(),buttonGroupReg.getSelection().getActionCommand());
                    validateCheck.checkInRegisterTwoFieldMatch();
                    if(validateCheck.getCheckInTheTwoField() == 2) {
                        userNameTf.setText(validateCheck.getUserName());
                        passwordTf.setText(validateCheck.getPassword());
                        radioBtn1.setSelected(true);
                        card.show(cardLayout,"Login panel");
                    } else {
                        radioBtn1.setSelected(true);
                        card.show(cardLayout,"Login panel");
                    }
                    break;
                case "handle login":
                    validateCheck = new RegisterAndLogin(userNameTf.getText(), passwordTf.getText());
                    validateCheck.checkInLogin();
                    break;
            }
        }
    }
    //https://stackoverflow.com/questions/22162398/how-to-set-a-background-picture-in-jpanel
    public class BackgroundPane extends JPanel {

        private BufferedImage img;
        private BufferedImage scaled;

        public BackgroundPane() {
        }

        @Override
        public Dimension getPreferredSize() {
            return img == null ? super.getPreferredSize() : new Dimension(img.getWidth(), img.getHeight());
        }

        public void setBackground(BufferedImage value) {
            if (value != img) {
                this.img = value;
                repaint();
            }
        }

        @Override
        public void invalidate() {
            super.invalidate();
            if (getWidth() > img.getWidth() || getHeight() > img.getHeight()) {
                scaled = getScaledInstanceToFill(img, getSize());
            } else {
                scaled = img;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (scaled != null) {
                int x = (getWidth() - scaled.getWidth()) / 2;
                int y = (getHeight() - scaled.getHeight()) / 2;
                g.drawImage(scaled, x, y, this);
            }
        }

    }

    public static BufferedImage getScaledInstanceToFill(BufferedImage img, Dimension size) {

        double scaleFactor = getScaleFactorToFill(img, size);

        return getScaledInstance(img, scaleFactor);

    }

    public static double getScaleFactorToFill(BufferedImage img, Dimension size) {

        double dScale = 1;

        if (img != null) {

            int imageWidth = img.getWidth();
            int imageHeight = img.getHeight();

            double dScaleWidth = getScaleFactor(imageWidth, size.width);
            double dScaleHeight = getScaleFactor(imageHeight, size.height);

            dScale = Math.max(dScaleHeight, dScaleWidth);

        }

        return dScale;

    }

    public static double getScaleFactor(int iMasterSize, int iTargetSize) {

        double dScale = (double) iTargetSize / (double) iMasterSize;

        return dScale;

    }

    public static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor) {

        return getScaledInstance(img, dScaleFactor, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);

    }

    protected static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor, Object hint, boolean bHighQuality) {

        BufferedImage imgScale = img;

        int iImageWidth = (int) Math.round(img.getWidth() * dScaleFactor);
        int iImageHeight = (int) Math.round(img.getHeight() * dScaleFactor);

//        System.out.println("Scale Size = " + iImageWidth + "x" + iImageHeight);
        if (dScaleFactor <= 1.0d) {

            imgScale = getScaledDownInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);

        } else {

            imgScale = getScaledUpInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);

        }

        return imgScale;

    }

    protected static BufferedImage getScaledDownInstance(BufferedImage img,
                                                         int targetWidth,
                                                         int targetHeight,
                                                         Object hint,
                                                         boolean higherQuality) {

        int type = (img.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = (BufferedImage) img;
        if (targetHeight > 0 || targetWidth > 0) {
            int w, h;
            if (higherQuality) {
                // Use multi-step technique: start with original size, then
                // scale down in multiple passes with drawImage()
                // until the target size is reached
                w = img.getWidth();
                h = img.getHeight();
            } else {
                // Use one-step technique: scale directly from original
                // size to target size with a single drawImage() call
                w = targetWidth;
                h = targetHeight;
            }

            do {
                if (higherQuality && w > targetWidth) {
                    w /= 2;
                    if (w < targetWidth) {
                        w = targetWidth;
                    }
                }

                if (higherQuality && h > targetHeight) {
                    h /= 2;
                    if (h < targetHeight) {
                        h = targetHeight;
                    }
                }

                BufferedImage tmp = new BufferedImage(Math.max(w, 1), Math.max(h, 1), type);
                Graphics2D g2 = tmp.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
                g2.drawImage(ret, 0, 0, w, h, null);
                g2.dispose();

                ret = tmp;
            } while (w != targetWidth || h != targetHeight);
        } else {
            ret = new BufferedImage(1, 1, type);
        }
        return ret;
    }

    protected static BufferedImage getScaledUpInstance(BufferedImage img,
                                                       int targetWidth,
                                                       int targetHeight,
                                                       Object hint,
                                                       boolean higherQuality) {

        int type = BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = (BufferedImage) img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w < targetWidth) {
                w *= 2;
                if (w > targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h < targetHeight) {
                h *= 2;
                if (h > targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
            tmp = null;

        } while (w != targetWidth || h != targetHeight);
        return ret;
    }
}
