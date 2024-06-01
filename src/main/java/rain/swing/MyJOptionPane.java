package rain.swing;

import javax.swing.*;
import java.awt.*;

/**
 * 对话框类
 */
public class MyJOptionPane {
    public void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);//JOptionPane.INFORMATION_MESSAGE
    }
}
