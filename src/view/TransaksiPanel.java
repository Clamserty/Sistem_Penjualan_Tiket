package view;

import dao.TiketDao;
import dao.TiketDaoImpl;
import dao.TransaksiDao;
import dao.TransaksiDaoImpl;
import model.Tiket;
import model.Transaksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TransaksiPanel extends JPanel {
    private JComboBox<String> cbTiket;
    private JTextField txtHarga, txtStokTersedia, txtJumlahBeli, txtTotalHarga, txtBayar, txtKembalian;
    private JTable tableRiwayat;
    private DefaultTableModel tableModel;
    private JButton btnHitung, btnBayar, btnRefresh;
    
    private TiketDao tiketDao;
    private TransaksiDao transaksiDao;
    private List<Tiket> listTiketActive;
    private Tiket tiketTerpilih;

    public TransaksiPanel() {
        this.tiketDao = new TiketDaoImpl();
        this.transaksiDao = new TransaksiDaoImpl();
        initComponents();
        loadTiketToComboBox();
        loadRiwayatTransaksi();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 1. Judul Atas
        JLabel lblTitle = new JLabel("MENU KASIR & RIWAYAT TRANSAKSI", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitle, BorderLayout.NORTH);

        // 2. Panel Input Kasir (Bagian Atas)
        JPanel panelKasir = new JPanel(new GridBagLayout());
        panelKasir.setBorder(BorderFactory.createTitledBorder("Mesin Kasir Penjualan"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Baris 0: Pilih Tiket
        gbc.gridx = 0; gbc.gridy = 0; panelKasir.add(new JLabel("Pilih Tiket:"), gbc);
        cbTiket = new JComboBox<>();
        gbc.gridx = 1; panelKasir.add(cbTiket, gbc);

        // Baris 1: Stok Tersedia
        gbc.gridx = 0; gbc.gridy = 1; panelKasir.add(new JLabel("Stok Tersedia:"), gbc);
        txtStokTersedia = new JTextField(25);
        txtStokTersedia.setEditable(false);
        txtStokTersedia.setBackground(Color.LIGHT_GRAY);
        gbc.gridx = 1; panelKasir.add(txtStokTersedia, gbc);

        // Baris 2: Harga Satuan
        gbc.gridx = 0; gbc.gridy = 2; panelKasir.add(new JLabel("Harga Satuan (Rp):"), gbc);
        txtHarga = new JTextField(25);
        txtHarga.setEditable(false);
        txtHarga.setBackground(Color.LIGHT_GRAY);
        gbc.gridx = 1; panelKasir.add(txtHarga, gbc);

        // Baris 3: Jumlah Pembelian
        gbc.gridx = 0; gbc.gridy = 3; panelKasir.add(new JLabel("Jumlah Beli:"), gbc);
        txtJumlahBeli = new JTextField(25);
        gbc.gridx = 1; panelKasir.add(txtJumlahBeli, gbc);

        // Baris 4: Total Harga (Otomatis/Dihitung)
        gbc.gridx = 0; gbc.gridy = 4; panelKasir.add(new JLabel("Total Harga (Rp):"), gbc);
        txtTotalHarga = new JTextField(25);
        txtTotalHarga.setEditable(false);
        txtTotalHarga.setBackground(Color.LIGHT_GRAY);
        txtTotalHarga.setFont(new Font("Arial", Font.BOLD, 13));
        txtTotalHarga.setForeground(Color.BLUE);
        gbc.gridx = 1; panelKasir.add(txtTotalHarga, gbc);

        // Baris 5: INPUT TUNAI/BAYAR (Fitur Kasir Baru)
        gbc.gridx = 0; gbc.gridy = 5; panelKasir.add(new JLabel("Uang Bayar Tunai (Rp):"), gbc);
        txtBayar = new JTextField(25);
        txtBayar.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 1; panelKasir.add(txtBayar, gbc);

        // Baris 6: KEMBALIAN (Fitur Kasir Baru)
        gbc.gridx = 0; gbc.gridy = 6; panelKasir.add(new JLabel("Uang Kembalian (Rp):"), gbc);
        txtKembalian = new JTextField(25);
        txtKembalian.setEditable(false);
        txtKembalian.setBackground(Color.LIGHT_GRAY);
        txtKembalian.setFont(new Font("Arial", Font.BOLD, 13));
        txtKembalian.setForeground(new Color(39, 174, 96));
        gbc.gridx = 1; panelKasir.add(txtKembalian, gbc);

        // Tombol Kontrol Kasir
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        btnRefresh = new JButton("Refresh");
        btnHitung = new JButton("Hitung");
        btnBayar = new JButton("Proses Transaksi & Cetak");
        btnBayar.setBackground(new Color(46, 204, 113));
        btnBayar.setForeground(Color.WHITE);
        // Matikan tema gradasi bawaan Windows
        btnBayar.setContentAreaFilled(false); 
        btnBayar.setOpaque(true);

        panelTombol.add(btnRefresh);
        panelTombol.add(btnHitung);
        panelTombol.add(btnBayar);

        // Gabungkan Form Kasir dan Tombolnya ke dalam satu panel container atas
        JPanel containerAtas = new JPanel(new BorderLayout());
        containerAtas.add(panelKasir, BorderLayout.CENTER);
        containerAtas.add(panelTombol, BorderLayout.SOUTH);

        // 3. Tabel Riwayat Transaksi (Bagian Bawah)
        String[] kolom = {"ID", "Tanggal", "Nama Tiket", "Qty", "Total", "Bayar", "Kembalian"};
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableRiwayat = new JTable(tableModel);
        JScrollPane scrollTabel = new JScrollPane(tableRiwayat);
        scrollTabel.setBorder(BorderFactory.createTitledBorder("Log/Riwayat Transaksi Terkini"));

        // Menggunakan JSplitPane untuk membagi layar atas (kasir) dan bawah (riwayat) secara proporsional
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, containerAtas, scrollTabel);
        splitPane.setDividerLocation(270);
        add(splitPane, BorderLayout.CENTER);

        // --- ACTION LISTENERS ---
        cbTiket.addActionListener(e -> aksiPilihTiket());
        btnHitung.addActionListener(e -> hitungTotalDanKembalian(false));
        btnBayar.addActionListener(e -> prosesPembayaranKasir());
        btnRefresh.addActionListener(e -> {
            loadTiketToComboBox();
            loadRiwayatTransaksi();
        });
    }

    private void loadTiketToComboBox() {
        cbTiket.removeAllItems();
        cbTiket.addItem("-- Pilih Tiket --");
        listTiketActive = tiketDao.readAll();
        for (Tiket t : listTiketActive) {
            cbTiket.addItem(t.getNamaTiket());
        }
        clearFormKasir();
    }

    private void loadRiwayatTransaksi() {
        tableModel.setRowCount(0);
        List<Transaksi> list = transaksiDao.readAllWithTiketName();
        for (Transaksi t : list) {
            Object[] data = {
                t.getIdTransaksi(), t.getTanggal(), t.getNamaTiket(),
                t.getJumlahBeli(), t.getTotalHarga(), t.getBayar(), t.getKembalian()
            };
            tableModel.addRow(data);
        }
    }

    private void aksiPilihTiket() {
        int index = cbTiket.getSelectedIndex();
        if (index <= 0 || listTiketActive == null) {
            tiketTerpilih = null;
            txtStokTersedia.setText("");
            txtHarga.setText("");
            return;
        }
        tiketTerpilih = listTiketActive.get(index - 1);
        txtStokTersedia.setText(String.valueOf(tiketTerpilih.getStokTiket()));
        txtHarga.setText(String.valueOf(tiketTerpilih.getHarga()));
    }

    private boolean hitungTotalDanKembalian(boolean silent) {
        if (tiketTerpilih == null) {
            if (!silent) JOptionPane.showMessageDialog(this, "Pilih tiket terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            int qty = Integer.parseInt(txtJumlahBeli.getText().trim());
            if (qty <= 0) {
                if (!silent) JOptionPane.showMessageDialog(this, "Jumlah beli minimal 1!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            // Validasi Stok Sesuai Instruksi Utama
            if (qty > tiketTerpilih.getStokTiket()) {
                JOptionPane.showMessageDialog(this, "Stok tidak mencukupi!", "Peringatan Stok", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            // Hitung total harga
            double total = tiketTerpilih.getHarga() * qty;
            txtTotalHarga.setText(String.valueOf(total));

            // Jika user sudah mengisi kolom Bayar Tunai, hitung kembaliannya
            String strBayar = txtBayar.getText().trim();
            if (!strBayar.isEmpty()) {
                double bayar = Double.parseDouble(strBayar);
                if (bayar < total) {
                    txtKembalian.setText("Uang Kurang!");
                    if (!silent) JOptionPane.showMessageDialog(this, "Uang pembayaran kurang!", "Gagal Kasir", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                double kembalian = bayar - total;
                txtKembalian.setText(String.valueOf(kembalian));
            }
            return true;

        } catch (NumberFormatException e) {
            if (!silent) JOptionPane.showMessageDialog(this, "Input Jumlah Beli & Uang Bayar harus angka valid!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void prosesPembayaranKasir() {
        // Eksekusi hitung & validasi kelayakan angka input
        if (!hitungTotalDanKembalian(true)) {
            JOptionPane.showMessageDialog(this, "Periksa kembali input pembelian dan uang tunai!", "Gagal Transaksi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (txtBayar.getText().trim().isEmpty() || txtKembalian.getText().equals("Uang Kurang!")) {
            JOptionPane.showMessageDialog(this, "Masukkan jumlah uang bayar tunai dengan benar!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int qty = Integer.parseInt(txtJumlahBeli.getText().trim());
        double total = Double.parseDouble(txtTotalHarga.getText());
        double bayar = Double.parseDouble(txtBayar.getText().trim());
        double kembalian = Double.parseDouble(txtKembalian.getText());

        int konfirmasi = JOptionPane.showConfirmDialog(this, "Proses Transaksi Kasir?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            
            // 1. Simpan Log ke Tabel Transaksi
            Transaksi t = new Transaksi(0, tiketTerpilih.getIdTiket(), null, qty, total, bayar, kembalian, null);
            transaksiDao.simpanTransaksi(t);

            // 2. Potong Stok Otomatis di database
            int stokBaru = tiketTerpilih.getStokTiket() - qty;
            tiketDao.updateStok(tiketTerpilih.getIdTiket(), stokBaru);

            JOptionPane.showMessageDialog(this, "Transaksi Berhasil!\nKembalian: Rp " + kembalian, "Kasir Sukses", JOptionPane.INFORMATION_MESSAGE);

            // 3. Refresh Data Combo Box & Live JTable Riwayat
            loadTiketToComboBox();
            loadRiwayatTransaksi();
        }
    }

    private void clearFormKasir() {
        txtJumlahBeli.setText("");
        txtTotalHarga.setText("");
        txtBayar.setText("");
        txtKembalian.setText("");
        txtStokTersedia.setText("");
        txtHarga.setText("");
        tiketTerpilih = null;
    }
}