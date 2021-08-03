package ManagerUI;

import UI.UIMain;

import javax.swing.*;

public class ManagerUI extends JFrame {
    String managerAccount;
    public static final String TITLEMANAGER = "Manager Library";
    public ManagerUI(JFrame loginPanel) {
        UIMain castUI = (UIMain) loginPanel;
        managerAccount = castUI.getUserNameTf().getText();
        System.out.println(managerAccount);
        setSize(1500, 900);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle(TITLEMANAGER);
        add(new JLabel("fuck", SwingConstants.CENTER));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
