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
public class Reel extends Alatpancing implements Dapatdisewa, Dapatdinilai {
    private String tipeReel;
    private double gearRatio;
    private int totalRating;
    private int jumlahRating;

    public Reel(String kode, String nama, double hargaPerHari, int stok,
                String tipeReel, double gearRatio) {
        super(kode, nama, hargaPerHari, stok);
        this.tipeReel = "-";
        this.gearRatio = 0;
        this.totalRating = 0;
        this.jumlahRating = 0;
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
    public double hitungDendaKeterlambatan(int hariTerlambat) {
        if (hariTerlambat <= 0) {
            return 0;
        }
        return getHargaPerHari() * 0.4 * hariTerlambat;
    }

    @Override
    public void prosesSewaFisik() {
        if (getStok() <= 0) {
            System.out.println("Stok " + getNama() + " habis, tidak bisa disewa!");
        } else {
            setStok(getStok() - 1);
            System.out.println("Reel " + getNama() + " dicek (drag dan putaran handle), lalu diserahkan ke penyewa.");
            System.out.println("Sisa stok: " + getStok());
        }
    }

    @Override
    public void beriRating(int rating) {
        if (rating < RATING_MIN || rating > RATING_MAX) {
            System.out.println("Rating harus antara " + RATING_MIN + " - " + RATING_MAX + "!");
        } else {
            totalRating = totalRating + rating;
            jumlahRating++;
            System.out.println("Rating " + rating + " untuk " + getNama() + " tersimpan.");
        }
    }

    @Override
    public double getRataRating() {
        if (jumlahRating == 0) {
            return 0;
        }
        return (double) totalRating / jumlahRating;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("         -> Tipe: " + tipeReel + " | Gear Ratio: " + gearRatio);
        if (jumlahRating == 0) {
            System.out.println("         -> Rating: belum ada");
        } else {
            System.out.printf("         -> Rating: %.1f (%d ulasan)%n", getRataRating(), jumlahRating);
        }
    }
}