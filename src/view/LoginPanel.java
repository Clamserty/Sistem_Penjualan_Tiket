package view;

/**
 * Panel "Login" yang menampilkan form login untuk pengguna.
 * Panel ini menggunakan layout GridBagLayout untuk menyusun label, field input, dan tombol login secara rapi.
 * Menggunakan UserDao untuk memvalidasi kredensial pengguna terhadap database.
 * @author I Kadek Sandyarthana Putra Sukarsa
 * @version 1.2
*/

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private UserDao userDao;

    // Konstruktor menerima referensi ke MainFrame untuk mengakses metode dan status aplikasi
    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.userDao = new UserDaoImpl(); // Instansiasi DAO User
        initComponents();
    }

    // Inisialisasi komponen panel login
    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Judul Form
        JLabel lblTitle = new JLabel("LOGIN SISTEM", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitle, gbc);

        // Input Username
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Username:"), gbc);

        txtUsername = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        add(txtUsername, gbc);

        // Input Password
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1; gbc.gridy = 2;
        add(txtPassword, gbc);

        // Tombol Login
        btnLogin = new JButton("Login");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnLogin, gbc);

        // Event handling ketika tombol login diklik
        btnLogin.addActionListener(e -> prosesLogin());
    }

    // Proses login dengan memvalidasi input dan memanggil metode login dari UserDao
    private void prosesLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Validasi input kosong
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Memanggil fungsi login dari objek DAO
        User user = userDao.login(username, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login Berhasil! Selamat Datang, " + user.getUsername(), "Sukses", JOptionPane.INFORMATION_MESSAGE);
            
            txtUsername.setText("");
            txtPassword.setText("");
            
            mainFrame.updateStatusUser("Sesi Aktif: " + user.getUsername() + " (Administrator)"); 
            mainFrame.setMenuAkses(true);
            mainFrame.tampilkanHalaman("MasterTiket"); 
        } else {
            // Jika login gagal, tampilkan pesan error
            JOptionPane.showMessageDialog(this, "Username atau Password Salah!", "Gagal Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}