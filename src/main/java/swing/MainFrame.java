package swing;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        // 设置窗口标题
        setTitle("宿舍管理系统 - 主界面");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置主面板为 BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // 设置背景颜色
        add(mainPanel);

        // 标题部分
        JLabel titleLabel = new JLabel("宿舍管理系统", JLabel.CENTER);
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 24)); // 设置字体
        titleLabel.setForeground(new Color(50, 50, 50)); // 设置字体颜色
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // 添加上下内边距
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // 按钮部分
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 三行两列，按钮之间有间距
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 添加外边距
        buttonPanel.setBackground(new Color(240, 240, 240)); // 背景颜色与主面板一致

        JButton userManagementButton = createStyledButton("用户和权限管理");
        JButton studentInfoButton = createStyledButton("学生信息管理");
        JButton dormInfoButton = createStyledButton("宿舍信息管理");
        JButton scoringButton = createStyledButton("评分管理");
        JButton repairButton = createStyledButton("报修管理");
        JButton queryButton = createStyledButton("查询功能");

        buttonPanel.add(userManagementButton);
        buttonPanel.add(studentInfoButton);
        buttonPanel.add(dormInfoButton);
        buttonPanel.add(scoringButton);
        buttonPanel.add(repairButton);
        buttonPanel.add(queryButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // 窗口居中显示
        setLocationRelativeTo(null);

        setVisible(true);
    }

    /**
     * 创建样式化的按钮
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 设置字体
        button.setBackground(new Color(200, 220, 240)); // 按钮背景颜色
        button.setForeground(new Color(50, 50, 50)); // 按钮文字颜色
        button.setFocusPainted(false); // 去掉焦点框
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 添加内边距

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new); // 启动主界面
    }
}
