import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FormFrame extends JFrame {

    JTextField txtNombre, txtEdad, txtCorreo;
    JButton btnGuardar;

    public FormFrame() {
        setTitle("Formulario - Registro en Oracle");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1));

        txtNombre = new JTextField();
        txtEdad = new JTextField();
        txtCorreo = new JTextField();
        btnGuardar = new JButton("Guardar en Oracle");

        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Edad:"));
        add(txtEdad);
        add(new JLabel("Correo:"));
        add(txtCorreo);
        add(btnGuardar);

        btnGuardar.addActionListener(e -> guardarDatos());

        setVisible(true);
    }

    private void guardarDatos() {
        String nombre = txtNombre.getText();
        int edad = Integer.parseInt(txtEdad.getText());
        String correo = txtCorreo.getText();

        String sql = "INSERT INTO FORM_DATA(NOMBRE, EDAD, CORREO) VALUES (?, ?, ?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nombre);
            pst.setInt(2, edad);
            pst.setString(3, correo);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registro almacenado correctamente");

            txtNombre.setText("");
            txtEdad.setText("");
            txtCorreo.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}