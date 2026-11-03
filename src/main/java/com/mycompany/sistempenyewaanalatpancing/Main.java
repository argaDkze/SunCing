/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;
import java.util.*;

public class Main {

    static Scanner input = new Scanner(System.in);

    static int bacaInt(String pesan) {
        System.out.print(pesan);
        int angka = input.nextInt();
        input.nextLine();
        return angka;
    }

    static double bacaDouble(String pesan) {
        System.out.print(pesan);
        double angka = input.nextDouble();
        input.nextLine();
        return angka;
    }

    static String bacaString(String pesan) {
        System.out.print(pesan);
        return input.nextLine();
    }

    static void isiDataAwal(Penyewaanmanager manager) {
        try {
            manager.tambahAlat(new Joran("JR01", "Daiwa Crossfire", 25000, 5, 240, "Carbon"));
            manager.tambahAlat(new Joran("JR02", "Shimano FX", 30000, 3, 270, "Fiberglass"));
            manager.tambahAlat(new Reel("RL01", "Shimano Sienna 2500", 20000, 4, "Spinning", 5.2));
            manager.tambahAlat(new Aksesoris("AK01", "Paket Tackle Box", 10000, 10, "Tackle Box", 25));
            manager.tambahAlat(new Umpan("UM01", "Umpan Jangkrik Premium", 8000, 20, "Jangkrik", 150));

            manager.tambahAnggota(new Anggota("AG01", "Budi Santoso", "081234567890"));
            manager.tambahAnggota(new Anggota("AG02", "Siti Aminah", "081345678901"));
            manager.tambahAnggota(new Anggota("AG03", "Rizki Pratama", "082156789012"));
        } catch (DataTidakValidException e) {
            System.out.println("Gagal mengisi data awal: " + e.getMessage());
        }
    }

    static void tambahData(Penyewaanmanager manager) throws DataTidakValidException {
        System.out.println("\n--- TAMBAH ALAT PANCING ---");
        System.out.println("1. Joran");
        System.out.println("2. Reel");
        System.out.println("3. Aksesoris");
        System.out.println("4. Umpan");
        int tipe = bacaInt("Pilih tipe alat: ");

        if (tipe < 1 || tipe > 4) {
            throw new DataTidakValidException("Tipe alat harus 1 sampai 4!");
        }

        String kode = bacaString("Kode alat        : ");
        if (manager.kodeSudahAda(kode)) {
            throw new DataTidakValidException("Kode " + kode.trim().toUpperCase() + " sudah dipakai!");
        }
        String nama = bacaString("Nama alat        : ");
        double harga = bacaDouble("Harga sewa/hari   : ");
        int stok = bacaInt("Stok              : ");

        Alatpancing alat = null;

        switch (tipe) {
            case 1:
                int panjang = bacaInt("Panjang joran (cm): ");
                String bahan = bacaString("Bahan joran       : ");
                alat = new Joran(kode, nama, harga, stok, panjang, bahan);
                break;
            case 2:
                String tipeReel = bacaString("Tipe reel (Spinning/Baitcasting): ");
                double gear = bacaDouble("Gear ratio        : ");
                alat = new Reel(kode, nama, harga, stok, tipeReel, gear);
                break;
            case 3:
                String jenisAks = bacaString("Jenis aksesoris   : ");
                int jumlahItem = bacaInt("Jumlah item       : ");
                alat = new Aksesoris(kode, nama, harga, stok, jenisAks, jumlahItem);
                break;
            case 4:
                String jenisUmpan = bacaString("Jenis umpan       : ");
                int berat = bacaInt("Berat umpan (gram): ");
                alat = new Umpan(kode, nama, harga, stok, jenisUmpan, berat);
                break;
        }

        manager.tambahAlat(alat);
        System.out.println("Data berhasil ditambahkan!");
    }

    static void cariAlat(Penyewaanmanager manager) throws DataTidakValidException {
        System.out.println("\n--- CARI ALAT PANCING ---");
        String kataKunci = bacaString("Kata kunci (kode/nama/jenis): ");

        try {
            ArrayList<Alatpancing> hasil = manager.cariAlat(kataKunci);
            System.out.println("Ditemukan " + hasil.size() + " alat:");
            manager.tampilkanDaftar(hasil);
        } catch (AlatTidakDitemukanException e) {
            System.out.println("Pencarian gagal: " + e.getMessage());
        } finally {
            System.out.println("Proses pencarian selesai.");
        }
    }

    static void kelolaAnggota(Penyewaanmanager manager) throws DataTidakValidException {
        System.out.println("\n--- KELOLA ANGGOTA ---");
        System.out.println("1. Tambah anggota baru");
        System.out.println("2. Cari anggota");
        System.out.println("3. Tampilkan semua anggota");
        int pilih = bacaInt("Pilih: ");

        if (pilih == 1) {
            String id = bacaString("ID anggota : ");
            String nama = bacaString("Nama       : ");
            String noHp = bacaString("No HP      : ");
            Anggota anggota = new Anggota(id, nama, noHp);
            manager.tambahAnggota(anggota);
            System.out.println("Anggota berhasil ditambahkan!");
        } else if (pilih == 2) {
            String id = bacaString("ID anggota yang dicari: ");
            if (manager.anggotaAda(id)) {
                Anggota anggota = manager.getAnggota(id);
                System.out.println("Anggota ditemukan:");
                System.out.println("Nama  : " + anggota.getNama());
                System.out.println("No HP : " + anggota.getNoHp());
            } else {
                System.out.println("ID anggota tidak terdaftar.");
            }
        } else if (pilih == 3) {
            manager.tampilkanSemuaAnggota();
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }

    static void simulasiTransaksi(Penyewaanmanager manager)
            throws AlatTidakDitemukanException, DataTidakValidException {
        System.out.println("\n--- SIMULASI TRANSAKSI / HITUNG BIAYA SEWA ---");
        String kode = bacaString("Kode alat: ");
        Alatpancing alat = manager.cariByKode(kode);

        int hari = bacaInt("Lama sewa (hari): ");
        if (hari <= 0) {
            throw new DataTidakValidException("Lama sewa harus lebih dari 0 hari!");
        }

        String idAnggota = bacaString("ID anggota (kosongkan jika bukan anggota): ");

        if (idAnggota.trim().equals("")) {
            prosesTransaksi(alat, hari);
        } else if (manager.anggotaAda(idAnggota)) {
            prosesTransaksi(alat, hari, true);
        } else {
            System.out.println("ID anggota tidak terdaftar, diproses sebagai non-member.");
            prosesTransaksi(alat, hari);
        }
    }

    static void prosesTransaksi(Alatpancing alat, int hari) {
        double total = alat.hitungBiayaSewa(hari);
        cetakStruk(alat, hari, total, false);
    }

    static void prosesTransaksi(Alatpancing alat, int hari, boolean member) {
        double total = alat.hitungBiayaSewa(hari, member);
        cetakStruk(alat, hari, total, member);
    }

    static void cetakStruk(Alatpancing alat, int hari, double total, boolean member) {
        System.out.println("\n===== STRUK TRANSAKSI =====");
        alat.tampilkanInfo();
        System.out.println("Lama sewa   : " + hari + " hari");
        if (member) {
            System.out.println("Status      : Member (dapat diskon 10%)");
        } else {
            System.out.println("Status      : Non-member");
        }
        System.out.printf("Total biaya : Rp%.0f%n", total);
        System.out.println("============================");
    }

    static void aksiKhusus(Penyewaanmanager manager)
            throws AlatTidakDitemukanException, DataTidakValidException {
        System.out.println("\n--- AKSI KHUSUS (INTERFACE) ---");
        String kode = bacaString("Kode alat: ");
        Alatpancing alat = manager.cariByKode(kode);

        System.out.println("Alat terpilih: " + alat.getNama() + " (" + alat.getJenis() + ")");
        System.out.println("1. Proses sewa fisik (serah terima alat)");
        System.out.println("2. Beri rating");
        int pilih = bacaInt("Pilih aksi: ");

        if (pilih == 1) {
            if (alat instanceof Dapatdisewa) {
                Dapatdisewa disewa = (Dapatdisewa) alat;
                disewa.prosesSewaFisik();
            } else {
                System.out.println(alat.getJenis() + " tidak bisa disewa secara fisik.");
            }
        } else if (pilih == 2) {
            if (alat instanceof Dapatdinilai) {
                Dapatdinilai dinilai = (Dapatdinilai) alat;
                int rating = bacaInt("Rating (" + Dapatdinilai.RATING_MIN + "-" + Dapatdinilai.RATING_MAX + "): ");
                dinilai.beriRating(rating);
            } else {
                System.out.println(alat.getJenis() + " tidak bisa diberi rating.");
            }
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }

    static void hitungDenda(Penyewaanmanager manager)
            throws AlatTidakDitemukanException, DataTidakValidException {
        System.out.println("\n--- HITUNG DENDA KETERLAMBATAN ---");
        String kode = bacaString("Kode alat: ");
        Alatpancing alat = manager.cariByKode(kode);

        int hariTerlambat = bacaInt("Terlambat berapa hari: ");
        if (hariTerlambat < 0) {
            throw new DataTidakValidException("Jumlah hari terlambat tidak boleh negatif!");
        }

        double denda = alat.hitungDendaKeterlambatan(hariTerlambat);
        System.out.println("Alat         : " + alat.getNama() + " (" + alat.getJenis() + ")");
        System.out.println("Terlambat    : " + hariTerlambat + " hari");
        System.out.printf("Total denda  : Rp%.0f%n", denda);
    }

    public static void main(String[] args) {
        input.useLocale(Locale.US);

        Penyewaanmanager manager = new Penyewaanmanager();
        isiDataAwal(manager);

        int pilihan = 0;

        do {
            System.out.println("\n=====================================");
            System.out.println("   SISTEM PENYEWAAN ALAT PANCING");
            System.out.println("=====================================");
            System.out.println("1. Tambah Data Alat");
            System.out.println("2. Tampilkan Seluruh Data Alat");
            System.out.println("3. Cari Alat");
            System.out.println("4. Kelola Anggota");
            System.out.println("5. Simulasi Transaksi / Hitung Biaya Sewa");
            System.out.println("6. Aksi Khusus (Sewa Fisik / Beri Rating)");
            System.out.println("7. Hitung Denda Keterlambatan");
            System.out.println("8. Keluar");
            System.out.println("-------------------------------------");

            try {
                pilihan = bacaInt("Pilih menu: ");

                switch (pilihan) {
                    case 1:
                        tambahData(manager);
                        break;
                    case 2:
                        System.out.println();
                        manager.tampilkanSemua();
                        break;
                    case 3:
                        cariAlat(manager);
                        break;
                    case 4:
                        kelolaAnggota(manager);
                        break;
                    case 5:
                        simulasiTransaksi(manager);
                        break;
                    case 6:
                        aksiKhusus(manager);
                        break;
                    case 7:
                        hitungDenda(manager);
                        break;
                    case 8:
                        System.out.println("Terima kasih, sampai jumpa!");
                        break;
                    default:
                        System.out.println("Menu tidak valid, coba lagi!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: input harus berupa angka (desimal pakai titik)!");
                input.nextLine();
            } catch (DataTidakValidException e) {
                System.out.println("Data tidak valid: " + e.getMessage());
            } catch (AlatTidakDitemukanException e) {
                System.out.println("Data tidak ditemukan: " + e.getMessage());
            }
        } while (pilihan != 8);

        input.close();
    }
}