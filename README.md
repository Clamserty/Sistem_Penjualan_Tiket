# Sistem_Penjualan_Tiket
Sistem Informasi Penjualan Tiket berbasis Java Desktop (Swing) dan MySQL. Tugas Akhir / UAS Mata Kuliah Pemrograman Berorientasi Objek (PBO) - ITB STIKOM Bali.

# 🎟️ Sistem Informasi Penjualan Tiket Berbasis Java Desktop

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![IDE](https://img.shields.io/badge/VS_Code-007ACC?style=for-the-badge&logo=visual-studio-code&logoColor=white)

Proyek ini merupakan **Tugas Akhir / Ujian Akhir Semester (UAS)** untuk mata kuliah **Pemrograman Berorientasi Objek (PBO)** di ITB STIKOM Bali. Aplikasi ini dirancang untuk mengelola manajemen data logistik tiket (CRUD) serta transaksi kasir yang terintegrasi secara langsung dengan database MySQL.

---

## 👥 Tim Pengembang

| Nama Pengembang | NIM | Peran (Role) | Jaringan |
| :--- | :---: | :---: | :---: |
| **I Kadek Sandyarthana P. S.** | 250040014 | Lead Programmer & UI/UX Designer | [@Sandya_cx](https://instagram.com/Sandya_cx) |
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
├── .vscode/          # Konfigurasi CWD Workspace VS Code
├── src/              # Source Code Utama (*.java)
│   ├── database/     # Kelas Koneksi DB
│   ├── model/        # Kelas Entitas Data
│   └── view/         # Komponen Panel GUI (MainFrame, dll)
├── assets/           # Aset Statis / Foto Profil Tim
├── lib/              # External Library (Driver JDBC MySQL)
├── README.md         # Dokumentasi Repositori
└── database_tiket.sql # File Backup Database
