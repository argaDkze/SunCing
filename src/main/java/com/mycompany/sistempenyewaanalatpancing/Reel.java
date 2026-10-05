/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
// SUBCLASS 2
public class Reel extends Alatpancing {

    private String tipeReel;
    private double gearRatio;

    public Reel(String kode, String nama, double hargaPerHari, int stok,
                String tipeReel, double gearRatio) {
        super(kode, nama, hargaPerHari, stok);
        this.tipeReel = "-";
        this.gearRatio = 0;
        setTipeReel(tipeReel);
        setGearRatio(gearRatio);
    }

    public String getTipeReel() {
        return tipeReel;
    }

    public double getGearRatio() {
        return gearRatio;
    }

    public void setTipeReel(String tipeReel) {
        if (tipeReel == null || tipeReel.trim().equals("")) {
            System.out.println("Tipe reel tidak boleh kosong!");
        } else {
            this.tipeReel = tipeReel.trim();
        }
    }

    public void setGearRatio(double gearRatio) {
        if (gearRatio < 3 || gearRatio > 10) {
            System.out.println("Gear ratio harus antara 3 - 10!");
        } else {
            this.gearRatio = gearRatio;
        }
    }

    @Override
    public String getJenis() {
        return "Reel";
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        return super.hitungBiayaSewa(hari) + 3000;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("         -> Tipe: " + tipeReel + " | Gear Ratio: " + gearRatio);
    }
}