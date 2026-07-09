package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AboutUsPanel extends JPanel {

    public AboutUsPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        setBackground(new Color(248, 249, 250));

        // 1. Header
        JPanel panelHeader = new JPanel(new GridLayout(2, 1, 5, 5));
        panelHeader.setOpaque(false);
        
        JLabel lblTitle = new JLabel("TENTANG PENGEMBANG", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(44, 62, 80));
        
        JLabel lblSubtitle = new JLabel("Sistem Informasi Penjualan Tiket - Tugas Akhir PBO", JLabel.CENTER);
        lblSubtitle.setFont(new Font("Arial", Font.ITALIC, 13));
        lblSubtitle.setForeground(new Color(127, 140, 141));
        
        panelHeader.add(lblTitle);
        panelHeader.add(lblSubtitle);
        add(panelHeader, BorderLayout.NORTH);

        // 2. Konten Tengah: Kartu Profil Tim
        JPanel panelTim = new JPanel(new GridLayout(1, 2, 25, 0));
        panelTim.setOpaque(false);

        /* * PENTING: Taruh file foto kamu (misal: sandya.jpg) di dalam folder proyekmu.
         * Jalur/Path di bawah ini berasumsi foto diletakkan di root folder proyek (sejajar dengan src).
         */
        JPanel card1 = buatKartuAnggota(
            "I Kadek Sandyarthana P. S.", 
            "250040014", 
            "Lead Programmer & UI",
            "assets/sandya.png", // Nama file foto kamu
            "SS",
            new Color(41, 128, 185)
        );

        JPanel card2 = buatKartuAnggota(
            "Frederico Wijaya", 
            "250040057", 
            "Database & QA",
            "assets/rico.png", // Nama file foto temanmu
            "FW",
            new Color(39, 174, 96)
        );

        panelTim.add(card1);
        panelTim.add(card2);
        add(panelTim, BorderLayout.CENTER);

        // 3. Bagian Bawah: Tombol README & Copyright
        JPanel panelBawah = new JPanel(new BorderLayout(10, 10));
        panelBawah.setOpaque(false);

        // Tombol Buka Bantuan / Panduan Aplikasi
        JButton btnReadme = new JButton("[?] Buka Panduan Pengguna (README.txt)");
        btnReadme.setFont(new Font("Arial", Font.BOLD, 12));
        btnReadme.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReadme.setBackground(new Color(52, 152, 219));
        btnReadme.setForeground(Color.WHITE);
        btnReadme.setFocusPainted(false);
        // Matikan tema gradasi bawaan Windows
        btnReadme.setContentAreaFilled(false); 
        btnReadme.setOpaque(true);
        
        // Pendorong tombol agar berada di tengah-tengah bawah
        JPanel panelTombolCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTombolCenter.setOpaque(false);
        panelTombolCenter.add(btnReadme);

        JLabel lblFooter = new JLabel("Versi Aplikasi 1.2", JLabel.CENTER);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(149, 165, 166));

        panelBawah.add(panelTombolCenter, BorderLayout.NORTH);
        panelBawah.add(lblFooter, BorderLayout.SOUTH);
        add(panelBawah, BorderLayout.SOUTH);

        // --- ACTION LISTENER UTK BUKA FILE README EXTERNAL ---
        btnReadme.addActionListener(e -> aksiBukaReadme());
    }

    private JPanel buatKartuAnggota(String nama, String nim, String role, String pathFoto, String inisialFallback, Color warnaAksen) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Komponen Foto / Bingkai Gambar
        JLabel lblFoto = new JLabel("", JLabel.CENTER);
        lblFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Coba load foto dari folder proyek
        File fileFoto = new File(pathFoto);
        if (fileFoto.exists()) {
            ImageIcon iconAsli = new ImageIcon(pathFoto);
            // Skala foto otomatis diperkecil secara halus menjadi 100x120 piksel (gaya pasfoto)
            Image imgTerukur = iconAsli.getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(imgTerukur));
        } else {
            // Jika foto belum dimasukkan/tidak ketemu, tampilkan inisial teks bulat sebagai cadangan (fallback)
            lblFoto.setText(inisialFallback);
            lblFoto.setFont(new Font("Arial", Font.BOLD, 24));
            lblFoto.setForeground(Color.WHITE);
            lblFoto.setBackground(warnaAksen);
            lblFoto.setOpaque(true);
            lblFoto.setPreferredSize(new Dimension(100, 120));
            lblFoto.setMaximumSize(new Dimension(100, 120));
            lblFoto.setBorder(BorderFactory.createEmptyBorder(35, 10, 10, 10));
        }

        JLabel lblNama = new JLabel(nama);
        lblNama.setFont(new Font("Arial", Font.BOLD, 14));
        lblNama.setForeground(new Color(44, 62, 80));
        lblNama.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNim = new JLabel("NIM: " + nim);
        lblNim.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNim.setForeground(new Color(127, 140, 141));
        lblNim.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblRole = new JLabel(role);
        lblRole.setFont(new Font("Arial", Font.BOLD, 11));
        lblRole.setForeground(warnaAksen);
        lblRole.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Penyusunan tata letak komponen
        card.add(Box.createVerticalGlue());
        card.add(lblFoto);
        card.add(Box.createVerticalStrut(12));
        card.add(lblNama);
        card.add(Box.createVerticalStrut(4));
        card.add(lblNim);
        card.add(Box.createVerticalStrut(8));
        card.add(lblRole);
        card.add(Box.createVerticalGlue());

        return card;
    }

    private void aksiBukaReadme() {
        try {
            // Membaca file README.txt yang berada di luar folder src (root project)
            File file = new File("README.txt");
            if (file.exists()) {
                // Menggunakan fungsi bawaan OS untuk membuka file teks lewat Notepad secara otomatis
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "File README.txt belum dibuat atau tidak ditemukan di root direktori proyek!", 
                    "Panduan Tidak Ditemukan", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal membuka panduan: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}