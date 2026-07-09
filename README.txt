========================================================================
             DOKUMENTASI PANDUAN PENGGUNAAN APLIKASI
        SISTEM INFORMASI PENJUALAN TIKET (TUGAS BESAR PBO)
                         VERSION 1.2
========================================================================

------------------------------------------------------------------------
1. INFORMASI PROYEK & PENGEMBANG
------------------------------------------------------------------------
* Nama Aplikasi     : Sistem Informasi Penjualan Tiket
* Institusi         : ITB STIKOM Bali
* Mata Kuliah       : Pemrograman Berorientasi Objek (PBO)
* Tahun Akademik    : 2026

* Tim Pengembang    :
  1. I Kadek Sandyarthana P. S. (Inisial: ss)
     NIM  : 250040014
     Role : Lead Programmer & UI/UX Designer
  2. Frederico Wijaya
     NIM  : 250040057
     Role : Database Administrator & Quality Assurance (QA)

------------------------------------------------------------------------
2. DESKRIPSI APLIKASI
------------------------------------------------------------------------
Aplikasi ini dirancang menggunakan bahasa pemrograman Java dengan 
antarmuka grafis (GUI) Java Swing dan didukung oleh database MySQL. 
Sistem ini memfasilitasi manajemen logistik internal (CRUD Tiket) 
sekaligus transaksi kasir langsung yang terintegrasi penuh secara 
real-time dengan validasi stok otomatis.

------------------------------------------------------------------------
3. STRUKTUR DIREKTORI PROYEK
------------------------------------------------------------------------
Sistem_Penjualan_Tiket/     <-- Direktori Utama (Workspace Root)
├── .vscode/                <-- Konfigurasi Workspace VS Code
│   └── launch.json         <-- Pengaturan CWD agar path file aman
├── src/                    <-- Source Code Java
│   ├── database/           <-- Kelas Koneksi DB (Koneksi.java)
│   ├── model/              <-- Kelas Entitas Data
│   └── view/               <-- Semua Komponen Panel GUI (MainFrame, dll)
|   └── lib/                <-- Tempat External Library / Driver
│       └── mysql-connector-j-x.x.jar <-- Driver JDBC MySQL
├── assets/                 <-- Penyimpanan Media / Foto Tim
│   ├── sandya.png          <-- Foto Profil Developer 1
│   └── rico.jpg            <-- Foto Profil Developer 2
├── README.txt              <-- File Dokumentasi Ini
└── pbo_db_tiket.sql        <-- File SQL Backup Database

------------------------------------------------------------------------
4. KEBUTUHAN SISTEM (PREREQUISITES)
------------------------------------------------------------------------
Untuk menjalankan aplikasi ini dengan lancar, pastikan perangkat Anda
telah terpasang:
1. Java Development Kit (JDK) versi 11 atau yang lebih baru.
2. Web Server lokal (sangat direkomendasikan menggunakan XAMPP).
3. IDE Visual Studio Code yang sudah terpasang extension:
   - Extension Pack for Java (oleh Microsoft)

------------------------------------------------------------------------
5. PANDUAN INSTALASI & KONFIGURASI 
------------------------------------------------------------------------
Langkah 1: Konfigurasi Database (MySQL)
1. Aktifkan modul Apache dan MySQL pada XAMPP Control Panel.
2. Buka browser dan akses halaman `http://localhost/phpmyadmin/`.
3. Buat database baru dengan nama: `database_tiket`.
4. Pilih database tersebut, klik menu "Import", pilih file 
   `database_tiket.sql` yang berada di root folder proyek ini, lalu klik "Go".

Langkah 2: Membuka Proyek di VS Code
1. Buka VS Code.
2. Pilih "Open Folder..." dan pastikan Anda memilih folder terluar yaitu 
   `Sistem_Penjualan_Tiket` (BUKAN folder src).

Langkah 3: Memastikan Library Terhubung
1. Di sidebar kiri VS Code, gulir ke bawah pada bagian panel "Java Projects".
2. Cari menu "Referenced Libraries".
3. Jika kosong atau terjadi error JDBC, klik ikon "+" di sebelah kanannya, 
   lalu pilih file `.jar` di dalam folder `lib/mysql-connector-j-x.x.jar`.

Langkah 4: Menjalankan Aplikasi
1. Buka file `src/view/MainFrame.java`.
2. Tekan tombol [F5] pada keyboard atau buka tab "Run and Debug" di VS Code 
   dan klik tombol "Play" hijau untuk mematikan kesalahan direktori kerja (CWD).

------------------------------------------------------------------------
6. PANDUAN OPERASIONAL FITUR APLIKASI
------------------------------------------------------------------------
Aplikasi ini memiliki 4 alur antarmuka utama yang saling terikat:

1. MODUL LOGIN SYSTEM (Gerbang Keamanan)
   * Saat pertama kali dijalankan, seluruh menu navigasi atas dikunci.
   * Masukkan kredensial akun yang valid dari database.
     [Catatan: Default Akun -> Username: admin | Password: admin]
   * Jika sukses, sistem akan membuka seluruh hak akses navigasi dan 
     mencatat nama Anda pada Status Bar di pojok kiri bawah.

2. MODUL MASTER DATA TIKET (Manajemen Logistik / CRUD)
   * Digunakan untuk menambah jenis tiket baru, mengubah harga, memperbarui 
     stok awal, maupun menghapus data tiket yang sudah tidak dijual.
   * Setiap perubahan data di panel ini akan langsung memperbarui opsi 
     pilihan pada halaman kasir transaksi.

3. MODUL KASIR TRANSAKSI (Logika Bisnis Utama)
   * Pilih tiket melalui komponen Dropdown yang tersedia. Harga dan stok 
     akan otomatis terisi secara real-time dari database.
   * Masukkan jumlah beli. Tekan tombol hitung untuk melihat total biaya.
   * Masukkan nominal uang bayar.
   * Jika jumlah beli melebihi stok, atau uang bayar kurang dari total, 
     sistem akan memunculkan dialog peringatan (error validation).
   * Klik "Proses Transaksi & Cetak". Sistem akan otomatis memotong jumlah 
     stok di database dan memperbarui tabel riwayat penjualan di bagian bawah.

4. MODUL ABOUT US & HELP (Identitas Tim)
   * Menampilkan profil resmi para pengembang aplikasi beserta foto asli.
   * Menyediakan tombol interaktif terintegrasi yang dapat langsung membuka 
     file dokumentasi panduan (README.txt) ini secara otomatis lewat sistem.

------------------------------------------------------------------------
7. TROUBLESHOOTING (PENANGANAN MASALAH)
------------------------------------------------------------------------
* Masalah: Muncul error "Driver JDBC tidak ditemukan".
  Solusi : Pastikan file driver mysql-connector di dalam folder `lib` sudah 
           didaftarkan pada menu "Referenced Libraries" di VS Code Anda.

* Masalah: Aplikasi berhasil terbuka tetapi data tabel kosong / tidak bisa login.
  Solusi : Periksa kembali koneksi MySQL di XAMPP Anda. Pastikan database 
           sudah di-import dengan benar dan sesuaikan konfigurasi user/password 
           database pada kelas `Koneksi.java`.

* Masalah: Foto profil tim atau file README tidak mau terbuka saat diklik.
  Solusi : Anda membuka folder yang salah di VS Code. Pastikan Anda melakukan 
           "Open Folder" pada direktori terluar (`Sistem_Penjualan_Tiket`) 
           dan jalankan program utama menggunakan tombol F5.
========================================================================