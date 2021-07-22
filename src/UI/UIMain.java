package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class UIMain extends JFrame {
    public UIMain() throws IOException {
        setLayout(new GridLayout(0,2));
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        URL url = getClass().getResource("/Pictures/jframeIcon.png");
        ImageIcon icon = new ImageIcon(url);
        setIconImage(icon.getImage());
        setTitle("Library Management");


        BackgroundPane leftPanel = new BackgroundPane();
        leftPanel.setBackground(ImageIO.read(new File("/home/huygrogbro/IdeaProjects/FinalTerm/src/Pictures/leftPanelBg.jpeg")));
        leftPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        add(leftPanel);

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
                        Label userNameDisplay = new Label("Enter your username: ");
                        Font fontUserNameDisplay = new Font("Helvetica", Font.BOLD, 10);
                        userNameDisplay.setFont(fontUserNameDisplay);
                        containComponentInFirstRow.add(userNameDisplay);
                        box1.add(containComponentInFirstRow);

                        JPanel containComponentInSecondRow = new JPanel();
                        JTextField userNameTf = new JTextField(20);
                        userNameTf.setPreferredSize(new Dimension(20,20));
                        Font fontUserNameTf = new Font("SansSerif", Font.BOLD,10);
                        userNameTf.setFont(fontUserNameTf);
                        containComponentInSecondRow.add(userNameTf);
                        box1.add(containComponentInSecondRow);

                        JPanel containComponentInThird = new JPanel();
                        Label passwordDisplay = new Label("Enter your password: ");
                        passwordDisplay.setFont(fontUserNameDisplay);
                        containComponentInThird.add(passwordDisplay);
                        box1.add(containComponentInThird);

                        JPanel containComponentInFourth = new JPanel();
                        JPasswordField passwordTf = new JPasswordField(20);
                        passwordTf.setPreferredSize(new Dimension(20,20));
                        containComponentInFourth.add(passwordTf);
                        box1.add(containComponentInFourth);
                    JPanel box2 = new JPanel();
                            JPanel handleButton1 = new JPanel();
                            JButton button1 = new JButton("Login");
                            button1.setPreferredSize(new Dimension(150, 60));
                            handleButton1.add(button1);
                            box2.add(handleButton1);

                            JPanel handleButton2 = new JPanel();
                            JButton button2 = new JButton("Register");
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
        add(rightPanel);

        //Debug
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
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
