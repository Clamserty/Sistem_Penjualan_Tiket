package view;

/**
 * MainFrame adalah kelas utama yang mewakili jendela utama aplikasi.
 * Menggunakan CardLayout untuk menampilkan berbagai panel (Login, MasterTiket, Transaksi, AboutUs) secara dinamis.
 * Menyediakan navigasi horizontal di atas dan status bar di bawah.
 * @author I Kadek Sandyarthana Putra Sukarsa
 * @version 1.2
*/

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel statusBar;
    private JLabel lblUserSession;
    
    // Navigasi BAR  
    private JMenuBar menuBar;
    private JButton btnMasterTiket, btnTransaksi, btnAboutUs, btnLogout;

    // Konstruktor utama frame aplikasi
    public MainFrame() {
        cobaSetLookAndFeel();
        setTitle("Sistem Informasi Penjualan Tiket v1.2");
        setSize(900, 650); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Mendaftarkan halaman ke CardLayout
        mainPanel.add(new LoginPanel(this), "Login");
        mainPanel.add(new MasterTiketPanel(), "MasterTiket");
        mainPanel.add(new TransaksiPanel(), "Transaksi");
        mainPanel.add(new AboutUsPanel(), "AboutUs");

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Inisialisasi navigasi horizontal di atas
        initHorizontalNav();
        
        // Inisialisasi status bar di bawah
        initStatusBar();
        
        // Set menu akses awal (hanya login yang terlihat)
        setMenuAkses(false);

        // Tampilkan halaman login saat aplikasi pertama kali dijalankan
        cardLayout.show(mainPanel, "Login");
    }

    private void initHorizontalNav() {
        menuBar = new JMenuBar();
        menuBar.setMargin(new Insets(5, 10, 5, 10)); // Beri ruang nafas/padding pada menu bar

        // Inisialisasi tombol menu utama
        btnMasterTiket = new JButton("[ ] Master Data Tiket");
        btnTransaksi = new JButton("[ ] Kasir Transaksi");
        btnAboutUs = new JButton("[i] About Us");
        btnLogout = new JButton("[x] Logout");

        styleNavButton(btnMasterTiket);
        styleNavButton(btnTransaksi);
        styleNavButton(btnAboutUs);
        styleLogoutButton(btnLogout);

        // Tambahkan tombol ke menu bar dengan jarak antar tombol
        menuBar.add(btnMasterTiket);
        menuBar.add(Box.createHorizontalStrut(10)); 
        menuBar.add(btnTransaksi);
        menuBar.add(Box.createHorizontalStrut(10));
        menuBar.add(btnAboutUs);
        
        menuBar.add(Box.createHorizontalGlue());         
        menuBar.add(btnLogout);
        setJMenuBar(menuBar);

        // Event listener perpindahan halaman sekaligus highlight tombol aktif
        btnMasterTiket.addActionListener(e -> tampilkanHalaman("MasterTiket"));
        btnTransaksi.addActionListener(e -> tampilkanHalaman("Transaksi"));
        btnAboutUs.addActionListener(e -> tampilkanHalaman("AboutUs"));
        
        btnLogout.addActionListener(e -> {
            int konfirmasi = JOptionPane.showConfirmDialog(this, 
                    "Apakah Anda yakin ingin logout?", "Konfirmasi Keluar", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (konfirmasi == JOptionPane.YES_OPTION) {
                setMenuAkses(false);
                updateStatusUser("Silakan login terlebih dahulu");
                tampilkanHalaman("Login");
            }
        });
    }
    // Inisialisasi status bar
    private void initStatusBar() {
        statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        statusBar.setBackground(new Color(245, 245, 245));
        
        lblUserSession = new JLabel("Status: Silakan login terlebih dahulu");
        lblUserSession.setFont(new Font("Arial", Font.PLAIN, 11));
        lblUserSession.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0)); 
        statusBar.add(lblUserSession, BorderLayout.WEST);
        
        // label copyright di sisi kanan status bar
        JLabel lblCopyright = new JLabel("© 2026 SS-250040014 & FW-250040057 | All Rights Reserved");
        lblCopyright.setFont(new Font("Arial", Font.BOLD, 11));
        lblCopyright.setForeground(new Color(127, 140, 141)); // Warna abu-abu elegan
        lblCopyright.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 10)); 
        statusBar.add(lblCopyright, BorderLayout.EAST);
        
        add(statusBar, BorderLayout.SOUTH);
    }

    // Fungsi untuk menampilkan halaman tertentu berdasarkan nama halaman
    public void tampilkanHalaman(String namaHalaman) {
        cardLayout.show(mainPanel, namaHalaman);
        aturHighlightTombol(namaHalaman);
    }

    // Fungsi untuk mengatur visibilitas menu navigasi berdasarkan status login
    public void setMenuAkses(boolean status) {
        btnMasterTiket.setVisible(status);
        btnTransaksi.setVisible(status);
        btnAboutUs.setVisible(status);
        btnLogout.setVisible(status);
        menuBar.revalidate();
        menuBar.repaint();
    }

    // Fungsi untuk memperbarui teks status user di status bar
    public void updateStatusUser(String info) {
        lblUserSession.setText(info);
    }

    // Fungsi untuk menambahkan gaya pada tombol navigasi agar terlihat lebih modern
    private void styleNavButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Ubah kursor menjadi tangan saat hover
    }

    private void styleLogoutButton(JButton button) {
        styleNavButton(button);
        button.setForeground(new Color(192, 57, 43)); 
    }

    // Fungsi untuk mengatur highlight pada tombol navigasi sesuai halaman aktif
    private void aturHighlightTombol(String namaHalaman) {
        // Reset warna dasar semua tombol navigasi
        btnMasterTiket.setForeground(Color.BLACK);
        btnTransaksi.setForeground(Color.BLACK);
        btnAboutUs.setForeground(Color.BLACK);

        // Beri warna biru tebal pada tombol halaman yang sedang aktif saat ini
        Color activeColor = new Color(41, 128, 185);
        if (namaHalaman.equals("MasterTiket")) {
            btnMasterTiket.setForeground(activeColor);
        } else if (namaHalaman.equals("Transaksi")) {
            btnTransaksi.setForeground(activeColor);
        } else if (namaHalaman.equals("AboutUs")) {
            btnAboutUs.setForeground(activeColor);
        }
    }

    // Fungsi untuk mencoba mengatur Look & Feel agar sesuai dengan sistem operasi pengguna
    private void cobaSetLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Jika gagal, tetap gunakan Look & Feel default Java
        }
    }

    // --- MAIN METHOD UNTUK MENJALANKAN APLIKASI ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}