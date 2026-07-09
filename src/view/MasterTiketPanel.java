package view;

import dao.TiketDao;
import dao.TiketDaoImpl;
import model.Tiket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MasterTiketPanel extends JPanel {
    private JTextField txtId, txtNama, txtHarga, txtStok;
    private JTable tableTiket;
    private DefaultTableModel tableModel;
    private JButton btnTambah, btnUbah, btnHapus, btnClear;
    private TiketDao tiketDao;

    public MasterTiketPanel() {
        this.tiketDao = new TiketDaoImpl(); // Inisialisasi DAO Tiket
        initComponents();
        loadDataKeTabel(); // Muat data dari database saat halaman dibuka
    }

    private void initComponents() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 1. Bagian Atas: Judul Halaman
        JLabel lblTitle = new JLabel("KELOLA MASTER DATA TIKET (CRUD)", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitle, BorderLayout.NORTH);

        // 2. Bagian Kiri: Form Input & Tombol Kontrol
        JPanel panelKiri = new JPanel(new BorderLayout(10, 10));
        
        // Form Input menggunakan GridBagLayout agar rapi dan responsif
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Data Tiket"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Input ID (Disabled karena Auto Increment di Database)
        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(new JLabel("ID Tiket:"), gbc);
        txtId = new JTextField();
        txtId.setEditable(false); 
        txtId.setBackground(Color.LIGHT_GRAY);
        gbc.gridx = 1;
        panelForm.add(txtId, gbc);

        // Input Nama Tiket
        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(new JLabel("Nama Tiket:"), gbc);
        txtNama = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(txtNama, gbc);

        // Input Harga Tiket
        gbc.gridx = 0; gbc.gridy = 2;
        panelForm.add(new JLabel("Harga Tiket:"), gbc);
        txtHarga = new JTextField();
        gbc.gridx = 1;
        panelForm.add(txtHarga, gbc);

        // Input Stok Tiket
        gbc.gridx = 0; gbc.gridy = 3;
        panelForm.add(new JLabel("Stok Tiket:"), gbc);
        txtStok = new JTextField();
        gbc.gridx = 1;
        panelForm.add(txtStok, gbc);

        panelKiri.add(panelForm, BorderLayout.CENTER);

        // Panel Tombol Aksi (Menggunakan GridLayout)
        JPanel panelTombol = new JPanel(new GridLayout(2, 2, 5, 5));
        btnTambah = new JButton("Tambah");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear Form");

        // Kondisi awal: Tombol Ubah dan Hapus dikunci sampai user memilih data di tabel
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);

        panelTombol.add(btnTambah);
        panelTombol.add(btnUbah);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        panelKiri.add(panelTombol, BorderLayout.SOUTH);
        add(panelKiri, BorderLayout.WEST);

        // 3. Bagian Kanan/Tengah: Tabel Data Visual
        String[] kolom = {"ID Tiket", "Nama Tiket", "Harga", "Stok"};
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mencegah sel tabel diedit langsung secara manual
            }
        };
        tableTiket = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableTiket);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Tiket Terkini"));
        add(scrollPane, BorderLayout.CENTER);

        // --- EVENT HANDLING (ACTION LISTENERS) ---
        
        // Event klik baris tabel untuk memindahkan data ke Form Input
        tableTiket.getSelectionModel().addListSelectionListener(e -> {
            int row = tableTiket.getSelectedRow();
            if (row != -1) {
                txtId.setText(tableModel.getValueAt(row, 0).toString());
                txtNama.setText(tableModel.getValueAt(row, 1).toString());
                txtHarga.setText(tableModel.getValueAt(row, 2).toString());
                txtStok.setText(tableModel.getValueAt(row, 3).toString());
                
                // Amankan UX: Kunci tombol Tambah, aktifkan tombol Ubah & Hapus
                btnTambah.setEnabled(false);
                btnUbah.setEnabled(true);
                btnHapus.setEnabled(true);
            }
        });

        // Event Tombol Tambah (Create)
        btnTambah.addActionListener(e -> aksiTambah());

        // Event Tombol Ubah (Update)
        btnUbah.addActionListener(e -> aksiUbah());

        // Event Tombol Hapus (Delete)
        btnHapus.addActionListener(e -> aksiHapus());

        // Event Tombol Clear Form
        btnClear.addActionListener(e -> clearForm());
    }

    // --- LOGIKA FUNGSIONAL CRUD ---

    private void loadDataKeTabel() {
        tableModel.setRowCount(0); // Bersihkan sisa data lama di tabel visual
        List<Tiket> listTiket = tiketDao.readAll();
        for (Tiket t : listTiket) {
            Object[] data = {t.getIdTiket(), t.getNamaTiket(), t.getHarga(), t.getStokTiket()};
            tableModel.addRow(data);
        }
    }

    private void aksiTambah() {
        try {
            String nama = txtNama.getText().trim();
            String strHarga = txtHarga.getText().trim();
            String strStok = txtStok.getText().trim();

            if (nama.isEmpty() || strHarga.isEmpty() || strStok.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field input wajib diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double harga = Double.parseDouble(strHarga);
            int stok = Integer.parseInt(strStok);

            Tiket tiketBaru = new Tiket(0, nama, harga, stok);
            tiketDao.create(tiketBaru);
            
            JOptionPane.showMessageDialog(this, "Data tiket baru berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            loadDataKeTabel();
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga dan Stok harus berupa angka valid!", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aksiUbah() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data tiket dari tabel terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());
            String nama = txtNama.getText().trim();
            double harga = Double.parseDouble(txtHarga.getText().trim());
            int stok = Integer.parseInt(txtStok.getText().trim());

            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama tiket tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Tiket tiketUpdate = new Tiket(id, nama, harga, stok);
            tiketDao.update(tiketUpdate);
            
            JOptionPane.showMessageDialog(this, "Data tiket berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            loadDataKeTabel();
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga dan Stok harus berupa angka!", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aksiHapus() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data tiket dari tabel yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus tiket ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(txtId.getText());
            tiketDao.delete(id);
            
            JOptionPane.showMessageDialog(this, "Data tiket telah dihapus dari sistem.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            loadDataKeTabel();
            clearForm();
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        tableTiket.clearSelection();
        
        // Kembalikan status tombol ke kondisi awal semula
        btnTambah.setEnabled(true);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
    }
}