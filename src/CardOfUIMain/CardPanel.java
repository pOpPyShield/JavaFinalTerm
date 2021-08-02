package CardOfUIMain;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    private CardLayout cardLayout = new CardLayout();
    public CardPanel() {
        setLayout(cardLayout);
    }

    public void addCard(JPanel panel, String name) {
        add(panel,name);
    }

    public void showCard(String name) {
        cardLayout.show(this, name);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}
