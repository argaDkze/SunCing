/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
// SUBCLASS 4 
public class Umpan extends Alatpancing {

    private String jenisUmpan;
    private int beratGram;

    public Umpan(String kode, String nama, double hargaPerHari, int stok,
                 String jenisUmpan, int beratGram) {
        super(kode, nama, hargaPerHari, stok);
        this.jenisUmpan = "-";
        this.beratGram = 0;
        setJenisUmpan(jenisUmpan);
        setBeratGram(beratGram);
    }

    public String getJenisUmpan() {
        return jenisUmpan;
    }

    public int getBeratGram() {
        return beratGram;
    }

    public void setJenisUmpan(String jenisUmpan) {
        if (jenisUmpan == null || jenisUmpan.trim().equals("")) {
            System.out.println("Jenis umpan tidak boleh kosong!");
        } else {
            this.jenisUmpan = jenisUmpan.trim();
        }
    }

    public void setBeratGram(int beratGram) {
        if (beratGram <= 0) {
            System.out.println("Berat umpan harus lebih dari 0!");
        } else {
            this.beratGram = beratGram;
        }
    }

    @Override
    public String getJenis() {
        return "Umpan";
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        double total = super.hitungBiayaSewa(hari) + 1000; 
        if (beratGram >= 100) {
            total = total - (total * 0.05); 
        }
        return total;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("         -> Jenis Umpan: " + jenisUmpan + " | Berat: " + beratGram + " gram");
    }
}
