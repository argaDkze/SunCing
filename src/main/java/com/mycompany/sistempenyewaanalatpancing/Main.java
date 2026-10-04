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

public class Main {

    static Scanner input = new Scanner(System.in);

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

    static void tambahData(Penyewaanmanager manager) {
        System.out.println("\n--- TAMBAH ALAT PANCING ---");
        System.out.println("1. Joran");
        System.out.println("2. Reel");
        System.out.println("3. Aksesoris");
        int tipe = bacaInt("Pilih tipe alat: ");

        if (tipe < 1 || tipe > 3) {
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
        }

        if (manager.tambahAlat(alat)) {
            System.out.println("Data berhasil ditambahkan!");
        }
    }

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
            String jenis = bacaString("Jenis (Joran/Reel/Aksesoris): ");
            double max = bacaDouble("Harga maksimal: ");
            manager.cariAlat(jenis, max);
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }

    static void hitungSewa(Penyewaanmanager manager) {
        System.out.println("\n--- HITUNG BIAYA SEWA ---");
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

        double total;
        if (jawab.equalsIgnoreCase("y")) {
            total = alat.hitungBiayaSewa(hari, true);
        } else {
            total = alat.hitungBiayaSewa(hari);
        }

        System.out.println("Alat        : " + alat.getNama() + " (" + alat.getJenis() + ")");
        System.out.println("Lama sewa   : " + hari + " hari");
        System.out.printf("Total biaya : Rp%.0f%n", total);
    }

    public static void main(String[] args) {
        Penyewaanmanager manager = new Penyewaanmanager();

        // Data awal 
        manager.tambahAlat(new Joran("JR01", "Daiwa Crossfire", 25000, 5, 240, "Carbon"));
        manager.tambahAlat(new Joran("JR02", "Shimano FX", 30000, 3, 270, "Fiberglass"));
        manager.tambahAlat(new Reel("RL01", "Shimano Sienna 2500", 20000, 4, "Spinning", 5.2));
        manager.tambahAlat(new Reel("RL02", "Abu Garcia Max", 35000, 2, "Baitcasting", 6.4));
        manager.tambahAlat(new Aksesoris("AK01", "Paket Umpan dan Kail", 10000, 10, "Tackle Box", 25));

        int pilihan;

        do {
            System.out.println("\n=====================================");
            System.out.println("   SISTEM PENYEWAAN ALAT PANCING");
            System.out.println("=====================================");
            System.out.println("1. Tambah Data Alat");
            System.out.println("2. Tampilkan Seluruh Data");
            System.out.println("3. Cari Alat");
            System.out.println("4. Hitung Biaya Sewa");
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