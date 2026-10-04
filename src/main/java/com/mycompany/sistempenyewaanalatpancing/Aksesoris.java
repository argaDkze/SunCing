/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
public class Aksesoris extends Alatpancing {

    private String jenisAksesoris;
    private int jumlahItem;

    public Aksesoris(String kode, String nama, double hargaPerHari, int stok,
                     String jenisAksesoris, int jumlahItem) {
        super(kode, nama, hargaPerHari, stok);
        this.jenisAksesoris = "-";
        this.jumlahItem = 0;
        setJenisAksesoris(jenisAksesoris);
        setJumlahItem(jumlahItem);
    }

    public String getJenisAksesoris() {
        return jenisAksesoris;
    }

    public int getJumlahItem() {
        return jumlahItem;
    }

    public void setJenisAksesoris(String jenisAksesoris) {
        if (jenisAksesoris == null || jenisAksesoris.trim().equals("")) {
            System.out.println("Jenis aksesoris tidak boleh kosong!");
        } else {
            this.jenisAksesoris = jenisAksesoris.trim();
        }
    }

    public void setJumlahItem(int jumlahItem) {
        if (jumlahItem <= 0) {
            System.out.println("Jumlah item harus lebih dari 0!");
        } else {
            this.jumlahItem = jumlahItem;
        }
    }

    @Override
    public String getJenis() {
        return "Aksesoris";
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        double total = super.hitungBiayaSewa(hari);
        if (hari >= 3) {
            total = total - (total * 0.10);
        }
        return total;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("         -> Jenis: " + jenisAksesoris + " | Isi: " + jumlahItem + " item");
    }
}
