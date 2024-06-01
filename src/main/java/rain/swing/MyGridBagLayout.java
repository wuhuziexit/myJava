package rain.swing;

import javax.swing.*;
import java.awt.*;

/**
 * 网格包布局
 *
 * @author rain
 * @
 */
public class MyGridBagLayout {
    /**
     * 设置约束边距
     *
     * @param gbc    约束
     * @param top    上边距
     * @param left   左边距
     * @param bottom 下边距
     * @param right  右边距
     */
    public void setGbcInsets(GridBagConstraints gbc, int top, int left, int bottom, int right) {
        gbc.insets = new Insets(top, left, bottom, right);//设置边距
    }

    /**
     * 设置所在行和列
     *
     * @param gbc 约束
     * @param l   组件所在的列
     * @param h   组件所在的行
     */
    public void setGridBagConstraints(GridBagConstraints gbc, int l, int h) {
//        组件在网格布局中的位置
        gbc.gridx = l;//组件所在的列
        gbc.gridy = h;//组件所在的行
//        组件所占用的网格数，可以跨越多行和多列
        gbc.gridwidth = 1;//跨越列，占据1列
        gbc.gridheight = 1;//跨越行，占据1行
        gbc.anchor = GridBagConstraints.CENTER;//居中对齐
    }

    /**
     * 返回一个网格包布局约束
     *
     * @return GridBagConstraints
     */
    public GridBagConstraints getGridBagConstraints() {
        return new GridBagConstraints();
    }

    /**
     * 创建一个网格布局的JPanel
     *
     * @return JPanel
     */
    public JPanel jPanel() {
        return new JPanel(new GridBagLayout());
    }

    /**
     * @param title  界面标题
     * @param width  界面宽
     * @param height 界面高
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
        frame.setLayout(new GridBagLayout());
        return frame;
    }

    /**
     * 给窗口添加组件
     *
     * @param frame 窗口
     * @param comp  组件
     * @param gbc   约束
     */
    public void add(JFrame frame, java.awt.Component comp, GridBagConstraints gbc) {
        frame.add(comp, gbc);
    }
}
