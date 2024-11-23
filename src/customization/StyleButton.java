package customization;

import javax.swing.*;
import java.awt.*;

public class StyleButton {
    public static void styleButton(JButton button, int width) {
        button.setPreferredSize(new Dimension(width, 30));
        button.setMinimumSize(new Dimension(width, 30));
        button.setMaximumSize(new Dimension(width, 30));
        button.setBackground(ColorChoice.BUTTON_BACKGROUND);
        button.setForeground(ColorChoice.BUTTON_FOREGROUND);
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setHorizontalAlignment(SwingConstants.HORIZONTAL);
    }
}
