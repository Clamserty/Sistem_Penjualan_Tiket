# 🎟️ Sistem Informasi Penjualan Tiket Berbasis Java Desktop

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![IDE](https://img.shields.io/badge/VS_Code-007ACC?style=for-the-badge&logo=visual-studio-code&logoColor=white)

Proyek ini merupakan **Tugas Akhir / Ujian Akhir Semester (UAS)** untuk mata kuliah **Pemrograman Berorientasi Objek (PBO)** di ITB STIKOM Bali. Aplikasi ini dirancang untuk mengelola manajemen data logistik tiket (CRUD) serta transaksi kasir yang terintegrasi secara langsung dengan database MySQL.

---

## 👥 Tim Pengembang

| Nama Pengembang | NIM | Peran (Role) | Jaringan |
| :--- | :---: | :---: | :---: |
| **I Kadek Sandyarthana Putra Sukarsa** | 250040014 | Lead Programmer & UI/UX Designer | [@Sandya_cx](https://instagram.com/Sandya_cx) |
| **Frederico Wijaya** | 250040057 | Database Administrator & QA | [@rrco.077](https://www.instagram.com/rrco.077/) |

* **Dosen Pengampu:** Made Agus Putra Subali, S.Kom., M.Kom. 

---

## 🚀 Fitur Utama Aplikasi

1. **Secure Login System:** Membatasi hak akses menu sebelum user terautentikasi oleh database.
2. **Master Data Tiket (CRUD):** Manajemen data tiket (Tambah, Lihat, Ubah, Hapus) secara dinamis.
3. **Kasir Transaksi:** Perhitungan otomatis total biaya, uang bayar, kembalian, serta validasi *error* jika stok habis.
4. **Live Transaction History:** Tabel riwayat penjualan yang diperbarui secara langsung menggunakan query `INNER JOIN`.
5. **Integrated About & Help:** Menu profil pengembang yang interaktif dan terhubung langsung ke file panduan eksternal.

---

## 📁 Struktur Folder Proyek

```text
Sistem_Penjualan_Tiket/
├── src/              # Source Code Utama (*.java)
│   ├── database/     # Kelas Koneksi DB
│   ├── model/        # Kelas Entitas Data
│   ├── view/         # Komponen Panel GUI (MainFrame, dll)
│   └── lib/          # External Library (Driver JDBC MySQL)
├── assets/           # Aset Statis / Foto Profil Tim
├── README.md         # Dokumentasi Repositori
├── README.txt        # Dokumentasi Lebih Lengkap 
└── database_tiket.sql # File Backup Database
```

---

## 🛠️ Panduan Instalasi & Menjalankan Aplikasi

### Persyaratan Sistem (Prerequisites)
* Java Development Kit (JDK) versi 11 atau yang lebih baru.
* Web Server lokal XAMPP (untuk mengaktifkan MySQL).
* IDE Visual Studio Code beserta *Extension Pack for Java*.

### Langkah-Langkah Pemasangan

1. **Konfigurasi Database:**
   * Aktifkan modul **Apache** dan **MySQL** pada XAMPP Control Panel.
   * Akses `http://localhost/phpmyadmin/` di browser kamu.
   * Buat database baru dengan nama `database_tiket`.
   * Pilih database tersebut, masuk ke menu **Import**, pilih file `database_tiket.sql` dari folder proyek ini, lalu klik **Go**.

2. **Kloning Repositori:**
   ```bash
   git clone [https://github.com/Clamserty/Sistem_Penjualan_Tiket.git](https://github.com/Clamserty/Sistem_Penjualan_Tiket.git)
   ```

3. **Membuka Proyek di VS Code:**
   * Pilih **Open Folder...** di VS Code dan pastikan kamu memilih folder terluar (`Sistem_Penjualan_Tiket`), **bukan** folder `src`.

4. **Menghubungkan Library JDBC:**
   * Pada panel kiri VS Code, cari bagian **Java Projects** -> **Referenced Libraries**.
   * Klik ikon `+` (Plus) di sebelah kanan, lalu pilih file `.jar` yang terletak di dalam folder `lib/`.

5. **Menjalankan Program:**
   * Buka file `src/view/MainFrame.java`.
   * Tekan tombol **F5** pada keyboard untuk menjalankan aplikasi lewat *debugger* resmi (agar sinkronisasi *Current Working Directory* untuk folder `assets` berjalan sempurna).
   * **Kredensial Login Default:** Username: `admin` \| Password: `admin123`.
