import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFrame extends JFrame {

    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin;

    public LoginFrame() {
        setTitle("Login - Oracle app");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        txtUser = new JTextField();
        txtPass = new JPasswordField();
        btnLogin = new JButton("Ingresar");

        add(new JLabel("Usuario:"));
        add(txtUser);
        add(new JLabel("Contraseña:"));
        add(txtPass);
        add(btnLogin);

        btnLogin.addActionListener(e -> validarUsuario());

        setVisible(true);
    }

    private void validarUsuario() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        String sql = "SELECT * FROM APP_USERS WHERE USERNAME=? AND PASSWORD =?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, user);
            pst.setString(2, pass);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Acceso concedido");
                dispose();
                new FormFrame();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales invalidas");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
