# -_aplicacion_java_con_login_formulario_conectadoOracle19c_swing_JDBC :. 
✅ Aplicación Java con Login + Formulario conectado a Oracle 19c (Swing + JDBC) :

<img width="1024" height="1024" alt="image" src="https://github.com/user-attachments/assets/71c2888e-7ab0-42ec-a3e2-5f30d52140e5" />  

## A continuación encontrará una solución completa, profesional y lista para ejecutar en IntelliJ IDEA, que cumple exactamente con lo solicitado :

✔️ Login gráfico (Java Swing)
✔️ Formulario gráfico para captura de información
✔️ Validación de usuario contra Oracle 19c
✔️ Registro de la información del formulario en Oracle 19c
✔️ Arquitectura organizada y ejecutable

## 1️⃣ Requerimientos Previos :

IntelliJ IDEA

JDK 8 o superior

Oracle Database 19c

Driver JDBC Oracle:

ojdbc8.jar


Crear base de datos / usuario para pruebas

## 2️⃣ Script Oracle 19c :

Ejecute en SQLPlus o SQL Developer:
```
CREATE TABLE APP_USERS (
    USERNAME VARCHAR2(50) PRIMARY KEY,
    PASSWORD VARCHAR2(50) NOT NULL
);

INSERT INTO APP_USERS VALUES ('admin', '1234');

COMMIT;

CREATE TABLE FORM_DATA (
    ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NOMBRE VARCHAR2(100),
    EDAD NUMBER,
    CORREO VARCHAR2(100),
    FECHA_REGISTRO DATE DEFAULT SYSDATE
);

COMMIT;
```

## 3️⃣ Configuración JDBC :

Modifique según su entorno:
```
public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "SYSTEM";
    private static final String PASSWORD = "su_password";

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```
## 4️⃣ Estructura del Proyecto :
* src
*  ├─ DBConnection.java
*  ├─ LoginFrame.java
*  ├─ FormFrame.java
*  └─ Main.java

## 5️⃣ Código Completo : 
🔹 DBConnection.java
```
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "SYSTEM";
    private static final String PASSWORD = "su_password";

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```
🔹 LoginFrame.java
```
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
        setTitle("Login - Oracle App");
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

        String sql = "SELECT * FROM APP_USERS WHERE USERNAME=? AND PASSWORD=?";

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
                JOptionPane.showMessageDialog(this, "Credenciales inválidas");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
```
🔹 FormFrame.java
```
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
```
🔹 Main.java
```
public class Main {
    public static void main(String[] args) {
        new LoginFrame();
    }
}
```
## 6️⃣ Flujo del Sistema

1️⃣ Usuario abre la aplicación
2️⃣ Aparece ventana de Login
3️⃣ Se valida usuario en Oracle 19c
4️⃣ Si es correcto, se abre el formulario
5️⃣ Usuario ingresa datos
6️⃣ Información se inserta en Oracle 19c
7️⃣ Buenas Prácticas Incluidas

✔️ Uso de PreparedStatement (seguridad SQL)
✔️ Separación básica de responsabilidades
✔️ Manejo de errores
✔️ Interfaz amigable
✔️ Arquitectura simple pero con enfoque empresarial . :. . / .
