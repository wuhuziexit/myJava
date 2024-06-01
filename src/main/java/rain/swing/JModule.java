package rain.swing;

import javax.swing.*;
import java.awt.*;

public class JModule {
    /**
     * @param st    界面标题
     * @param w     界面宽
     * @param h     界面高
     * @param close 界面以何种方式关闭
     *              0-不执行任何操作
     *              1-只隐藏界面，setVisible(false)
     *              2-点击关闭按钮，隐藏并释放窗体，dispose()，最后一个窗口被释放后，该程序也随之运行结束
     *              3-直接关闭应用程序，System.exit(0)
     * @return 界面
     */
    public JFrame jFrame(String st, int w, int h, int close) {
        JFrame JF = new JFrame();
        JF.setSize(w, h);//		框架大小
        JF.setTitle(st);//设置框架标题
//		jf1.setAlwaysOnTop(true);//		界面置顶
        JF.setLocationRelativeTo(null);//		界面居中
        JF.setDefaultCloseOperation(close);//		界面关闭
        JF.setLayout(null);//		取消默认的居中方式
//        JF.getContentPane().repaint();//刷新界面
        JF.setVisible(true);//		设置框架可见
        return JF;
    }

    /**
     * @param st    界面标题
     * @param w     界面宽
     * @param h     界面高
     * @param close 界面以何种方式关闭
     *              0-不执行任何操作
     *              1-只隐藏界面，setVisible(false)
     *              2-点击关闭按钮，隐藏并释放窗体，dispose()，最后一个窗口被释放后，该程序也随之运行结束
     *              3-直接关闭应用程序，System.exit(0)
     * @param b     是否取消默认居中方式
     * @return 界面
     */
    public JFrame jFrame(String st, int w, int h, int close, boolean b) {
        JFrame JF = new JFrame();
        JF.setSize(w, h);
        JF.setTitle(st);
        JF.setLocationRelativeTo(null);//		界面居中
        JF.setDefaultCloseOperation(close);//		界面关闭
        if (b) JF.setLayout(null);      //		取消默认的居中方式
        JF.setVisible(true);//		设置框架可见
        return JF;
    }

    /**
     * @param st 标签字段，或者图片资源的路径
     * @param x  横坐标
     * @param y  纵坐标
     * @param w  宽
     * @param h  高
     * @return 标签
     */
    public JLabel jLabel(String st, int x, int y, int w, int h) {
        JLabel JL = null;
        for (int i = 0; i < st.length(); i++) {
            if (st.charAt(i) == '\\') {
                ImageIcon IMAGE = new ImageIcon(st);
                JL = new JLabel(IMAGE);
                break;
            } else {
                JL = new JLabel(st);
            }
        }
        if (JL != null) JL.setBounds(new Rectangle(x, y, w, h));
        return JL;
    }

    /**
     * 文本区(JTextArea)，它是窗体中一个放置文本的区域。
     * 文本区与文本框的主要区别在于文本区可存放多行文本。javax.swing包中的JTextArea类用来建立文本区。
     * JTextArea组件没有事件。
     *
     * @param st 文本区初始文本
     * @param x  横坐标
     * @param y  纵坐标
     * @param w  宽
     * @param h  高
     * @param ET 是否可编辑
     * @return 文本区
     */
    public JTextArea jTextArea(String st, int x, int y, int w, int h, boolean ET) {
        JTextArea JT = new JTextArea(st);
        JT.setLineWrap(true); //设置自动换行
        JT.setBounds(new Rectangle(x, y, w, h));//边界指定相对于其父对象的宽度，高度和位置。
        JT.setEditable(ET);//是否可编辑
//        JT.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //添加黑色边框
        return JT;
    }

    /**
     * @param st 按钮文本
     * @param x  横坐标
     * @param y  纵坐标
     * @param w  宽
     * @param h  高
     * @return JButton
     */
    public JButton jButton(String st, int x, int y, int w, int h) {
        JButton JB = new JButton(st);
        JB.setBounds(new Rectangle(x, y, w, h));
        return JB;
    }

    /**
     * JTextField是一个轻量级组件，允许编辑单行文本。
     *
     * @param st 初始文本
     * @param x  横坐标
     * @param y  纵坐标
     * @param w  宽
     * @param h  高
     * @param ET 是否可编辑
     * @return JTextField
     */
    public JTextField jTextField(String st, int x, int y, int w, int h, boolean ET) {
        JTextField JT = new JTextField(st);
        JT.setBounds(new Rectangle(x, y, w, h));
        JT.setEditable(ET);
//        JT.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //添加黑色边框
        return JT;
    }

    public JTable jTable(Object[][] data, Object[] titles) {
        JTable jTable = new JTable(data, titles);
//        jFrame.pack();//调整窗口的大小，使其适应组件的大小和布局。
        return jTable;
    }

    /**
     *
     */
    public JComboBox jComboBox(Object[] option, int x, int y, int w, int h) {
        JComboBox jComboBox = new JComboBox(option);
        jComboBox.setBounds(new Rectangle(x, y, w, h));
        return jComboBox;
    }
}
