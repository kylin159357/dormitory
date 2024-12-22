package swing;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        // 设置窗口标题
        setTitle("宿舍管理系统 - 登录");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置主面板为 BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 添加内边距
        add(mainPanel);

        // 标题部分
        JLabel titleLabel = new JLabel("宿舍管理系统", JLabel.CENTER);
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 24)); // 设置支持中文的字体
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // 表单部分
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 设置控件之间的间距（优化用户名和密码与输入框的距离）
        gbc.insets = new Insets(5, 5, 5, 2); // 上、左、下、右的间距，右侧减小为2像素
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
        usernameField = new JTextField();
        usernameField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

        gbc.gridx = 0; // 第一列
        gbc.gridy = 0; // 第一行
        gbc.weightx = 0; // 不扩展标签宽度
        formPanel.add(usernameLabel, gbc);

        gbc.gridx = 1; // 第二列
        gbc.gridy = 0; // 第一行
        gbc.weightx = 1.0; // 水平方向扩展权重
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0; // 第一列
        gbc.gridy = 1; // 第二行
        gbc.weightx = 0; // 不扩展标签宽度
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1; // 第二列
        gbc.gridy = 1; // 第二行
        formPanel.add(passwordField, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // 按钮部分
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 16)); // 设置支持中文的字体

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            boolean loginSuccess = authenticate(username, password);

            if (loginSuccess) {
                JOptionPane.showMessageDialog(this, "登录成功");
                dispose(); // 关闭登录窗口
                new MainFrame(); // 打开主界面
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误");
            }
        });

        JButton cancelButton = new JButton("取消");
        cancelButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 16)); // 设置支持中文的字体

        cancelButton.addActionListener(e -> System.exit(0)); // 退出程序

        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 窗口居中显示
        setLocationRelativeTo(null);
    }

    /**
     * 调用后端接口验证用户名和密码
     */
    private boolean authenticate(String username, String password) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            // 后端接口地址
            String url = "http://localhost:8080/api/login";

            // 构造请求体
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/x-www-form-urlencoded");

            String requestBody = "username=" + username + "&password=" + password;

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            // 调用后端接口
            ResponseEntity<String> response =
                    restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            return response.getStatusCode().is2xxSuccessful(); // 如果响应状态码是2xx，则表示成功

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // 登录失败
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
