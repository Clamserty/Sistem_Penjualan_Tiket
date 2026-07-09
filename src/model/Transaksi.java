package model;

/**
 * Kelas Transaksi merepresentasikan entitas transaksi dalam sistem.
 * Setiap transaksi memiliki ID, ID tiket terkait, jumlah pembelian, total harga, pembayaran, kembalian, dan tanggal transaksi.
 * @Author I Kadek Sandyarta Putra Sukarsa
 * @version 1.2
 */

public class Transaksi {
    private int idTransaksi;
    private int idTiket;
    private String namaTiket; 
    private int jumlahBeli;
    private double totalHarga;
    private double bayar;
    private double kembalian;
    private String tanggal;

    public Transaksi() {}

    public Transaksi(int idTransaksi, int idTiket, String namaTiket, int jumlahBeli, double totalHarga, double bayar, double kembalian, String tanggal) {
        this.idTransaksi = idTransaksi;
        this.idTiket = idTiket;
        this.namaTiket = namaTiket;
        this.jumlahBeli = jumlahBeli;
        this.totalHarga = totalHarga;
        this.bayar = bayar;
        this.kembalian = kembalian;
        this.tanggal = tanggal;
    }

    // Getter dan Setter
    public int getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(int idTransaksi) { this.idTransaksi = idTransaksi; }

    public int getIdTiket() { return idTiket; }
    public void setIdTiket(int idTiket) { this.idTiket = idTiket; }

    public String getNamaTiket() { return namaTiket; }
    public void setNamaTiket(String namaTiket) { this.namaTiket = namaTiket; }

    public int getJumlahBeli() { return jumlahBeli; }
    public void setJumlahBeli(int jumlahBeli) { this.jumlahBeli = jumlahBeli; }

    public double getTotalHarga() { return totalHarga; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }

    public double getBayar() { return bayar; }
    public void setBayar(double bayar) { this.bayar = bayar; }

    public double getKembalian() { return kembalian; }
    public void setKembalian(double kembalian) { this.kembalian = kembalian; }

    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
}