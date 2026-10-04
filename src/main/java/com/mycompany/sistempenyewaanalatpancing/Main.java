/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;
import java.util.*;
/**
 *
 * @author Hype AMD
 */

import java.util.Scanner;

import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    // Membaca angka bulat dengan aman
    static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat!");
            }
        }
    }

    // Membaca angka desimal dengan aman
    static double bacaDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Double.parseDouble(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    static String bacaString(String pesan) {
        System.out.print(pesan);
        return input.nextLine();
    }

    // ===== Menu 1: Tambah data =====
    static void tambahData(Penyewaanmanager manager) {
        System.out.println("\n--- TAMBAH ALAT PANCING ---");
        System.out.println("1. Joran");
        System.out.println("2. Reel");
        System.out.println("3. Aksesoris");
        System.out.println("4. Umpan");
        int tipe = bacaInt("Pilih tipe alat: ");

        if (tipe < 1 || tipe > 4) {
            System.out.println("Tipe tidak valid!");
            return;
        }

        String kode = bacaString("Kode alat        : ");
        if (manager.kodeSudahAda(kode)) {
            System.out.println("Kode sudah dipakai, gunakan kode lain!");
            return;
        }
        String nama = bacaString("Nama alat        : ");
        double harga = bacaDouble("Harga sewa/hari   : ");
        int stok = bacaInt("Stok              : ");

        // Dideklarasikan bertipe Superclass (Alatpancing) -> UPCASTING,
        // objek konkretnya baru diketahui saat runtime (Joran/Reel/Aksesoris/Umpan)
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

        if (manager.tambahAlat(alat)) {
            System.out.println("Data berhasil ditambahkan!");
        }
    }

    // ===== Menu 3: Pencarian (method overloading) =====
    static void menuCari(Penyewaanmanager manager) {
        System.out.println("\n--- CARI ALAT PANCING ---");
        System.out.println("1. Cari berdasarkan nama");
        System.out.println("2. Cari berdasarkan harga maksimal");
        System.out.println("3. Cari berdasarkan jenis dan harga maksimal");
        int pilih = bacaInt("Pilih: ");

        if (pilih == 1) {
            String kata = bacaString("Kata kunci nama: ");
            manager.cariAlat(kata);
        } else if (pilih == 2) {
            double max = bacaDouble("Harga maksimal: ");
            manager.cariAlat(max);
        } else if (pilih == 3) {
            String jenis = bacaString("Jenis (Joran/Reel/Aksesoris/Umpan): ");
            double max = bacaDouble("Harga maksimal: ");
            manager.cariAlat(jenis, max);
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }

    // ===== Menu 4: Simulasi Transaksi (Hitung Biaya Sewa) =====
    // Mengambil objek lewat referensi bertipe Superclass (cariByKode mengembalikan
    // Alatpancing), lalu diteruskan ke method prosesTransaksi() yang parameternya
    // juga bertipe Superclass -> mendemonstrasikan UPCASTING pada parameter method.
    static void hitungSewa(Penyewaanmanager manager) {
        System.out.println("\n--- SIMULASI TRANSAKSI / HITUNG BIAYA SEWA ---");
        String kode = bacaString("Kode alat: ");
        Alatpancing alat = manager.cariByKode(kode);

        if (alat == null) {
            System.out.println("Alat tidak ditemukan!");
            return;
        }

        int hari = bacaInt("Lama sewa (hari): ");
        if (hari <= 0) {
            System.out.println("Lama sewa harus lebih dari 0!");
            return;
        }

        String jawab = bacaString("Apakah member? (y/n): ");

        // Method Overloading (Compile-Time Polymorphism): kompiler menentukan
        // versi prosesTransaksi() yang dipanggil berdasarkan jumlah argumen
        // yang diberikan saat kompilasi, BUKAN saat program berjalan.
        if (jawab.equalsIgnoreCase("y")) {
            prosesTransaksi(alat, hari, true);
        } else {
            prosesTransaksi(alat, hari);
        }
    }

    // ===== Method Overload 1: tanpa status member =====
    // Parameter "Alatpancing alat" bertipe Superclass, sehingga method ini bisa
    // menerima objek dari SELURUH subclass (Joran, Reel, Aksesoris, Umpan) -> upcasting.
    static void prosesTransaksi(Alatpancing alat, int hari) {
        // Runtime Polymorphism / Dynamic Binding: baris di bawah ini memanggil
        // hitungBiayaSewa() dan tampilkanInfo() milik SUPERCLASS secara sintaks,
        // namun JVM akan mengeksekusi versi hasil @Override sesuai wujud objek
        // asli (Joran/Reel/Aksesoris/Umpan) yang baru diketahui saat program berjalan.
        double total = alat.hitungBiayaSewa(hari);
        cetakStruk(alat, hari, total, false);
    }

    // ===== Method Overload 2: dengan status member (Compile-Time Polymorphism) =====
    // Nama method sama (prosesTransaksi), jumlah parameter berbeda (3 vs 2) ->
    // dipilih oleh kompiler saat compile-time (static binding), bukan saat runtime.
    static void prosesTransaksi(Alatpancing alat, int hari, boolean member) {
        double total = alat.hitungBiayaSewa(hari, member); // dynamic binding tetap berlaku di sini
        cetakStruk(alat, hari, total, member);
    }

    // Helper cetak struk; alat.tampilkanInfo() juga dieksekusi secara polimorfis
    static void cetakStruk(Alatpancing alat, int hari, double total, boolean member) {
        System.out.println("\n===== STRUK TRANSAKSI =====");
        alat.tampilkanInfo(); // dynamic binding: method hasil override yang dijalankan
        System.out.println("Lama sewa   : " + hari + " hari");
        System.out.println("Status      : " + (member ? "Member (dapat diskon)" : "Non-member"));
        System.out.printf("Total biaya : Rp%.0f%n", total);
        System.out.println("============================");
    }

    public static void main(String[] args) {
        Penyewaanmanager manager = new Penyewaanmanager();

        // Data awal (5 objek, mencakup seluruh variasi subclass: Joran, Reel,
        // Aksesoris, Umpan). Array penyimpanan di Penyewaanmanager bertipe
        // Superclass (Alatpancing[]), sehingga mampu menampung keempatnya sekaligus.
        manager.tambahAlat(new Joran("JR01", "Daiwa Crossfire", 25000, 5, 240, "Carbon"));
        manager.tambahAlat(new Joran("JR02", "Shimano FX", 30000, 3, 270, "Fiberglass"));
        manager.tambahAlat(new Reel("RL01", "Shimano Sienna 2500", 20000, 4, "Spinning", 5.2));
        manager.tambahAlat(new Aksesoris("AK01", "Paket Tackle Box", 10000, 10, "Tackle Box", 25));
        manager.tambahAlat(new Umpan("UM01", "Umpan Jangkrik Premium", 8000, 20, "Jangkrik", 150));

        int pilihan;

        do {
            System.out.println("\n=====================================");
            System.out.println("   SISTEM PENYEWAAN ALAT PANCING");
            System.out.println("=====================================");
            System.out.println("1. Tambah Data Alat");
            System.out.println("2. Tampilkan Seluruh Data");
            System.out.println("3. Cari Alat");
            System.out.println("4. Simulasi Transaksi / Hitung Biaya Sewa");
            System.out.println("5. Keluar");
            System.out.println("-------------------------------------");
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
                    menuCari(manager);
                    break;
                case 4:
                    hitungSewa(manager);
                    break;
                case 5:
                    System.out.println("Terima kasih, sampai jumpa!");
                    break;
                default:
                    System.out.println("Menu tidak valid, coba lagi!");
            }
        } while (pilihan != 5);

        input.close();
    }
}