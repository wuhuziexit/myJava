package rain.swing;

import javax.swing.*;
import java.awt.*;

/**
 * 边界布局
 */
public class MyBorderLayout {
    /**
     * 一个居中的界面
     *
     * @param title  标题
     * @param width  宽
     * @param height 高
     * @param close  界面以何种方式关闭
     *               0-不执行任何操作
     *               1-只隐藏界面，setVisible(false)
     *               2-点击关闭按钮，隐藏并释放窗体，dispose()，最后一个窗口被释放后，该程序也随之运行结束
     *               3-直接关闭应用程序，System.exit(0)
     * @return 界面
     */
    public JFrame jFrame(String title, int width, int height, int close) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(close); //设置关闭方式
        frame.setSize(width, height); // 设置窗口大小
        frame.setLocationRelativeTo(null);//取消默认居中方式
        frame.setVisible(true);// 显示窗口
        return frame;
    }

    /**
     * @return 一个边界布局的JPanel
     */
    public JPanel jPanel() {
        JPanel panel = new JPanel(new BorderLayout());// 创建一个JPanel，并设置其布局为BorderLayout
        return panel;
    }

    /**
     * 为JPanel添加子项
     *
     * @param jPanel   父组件
     * @param comp     子组件
     * @param position 方位
     *                 BorderLayout.NORTH-北(上)
     *                 BorderLayout.SOUTH-南(下)
     *                 BorderLayout.EAST-东(左)
     *                 BorderLayout.WEST-西(右)
     *                 BorderLayout.CENTER-中心
     */
    public void add(JPanel jPanel, java.awt.Component comp, String position) {
        jPanel.add(comp, position);
    }
}
