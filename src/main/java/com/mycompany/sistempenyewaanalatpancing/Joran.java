/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
// SUBCLASS 1
public class Joran extends Alatpancing implements Dapatdisewa, Dapatdinilai {
    private int panjangCm;
    private String bahan;
    private int totalRating;
    private int jumlahRating;

    public Joran(String kode, String nama, double hargaPerHari, int stok,
                 int panjangCm, String bahan) {
        super(kode, nama, hargaPerHari, stok);
        this.panjangCm = 0;
        this.bahan = "-";
        this.totalRating = 0;
        this.jumlahRating = 0;
        setPanjangCm(panjangCm);
        setBahan(bahan);
    }

    public int getPanjangCm() {
        return panjangCm;
    }

    public String getBahan() {
        return bahan;
    }

    public void setPanjangCm(int panjangCm) {
        if (panjangCm < 100 || panjangCm > 1000) {
            System.out.println("Panjang joran harus 100 - 1000 cm!");
        } else {
            this.panjangCm = panjangCm;
        }
    }

    public void setBahan(String bahan) {
        if (bahan == null || bahan.trim().equals("")) {
            System.out.println("Bahan joran tidak boleh kosong!");
        } else {
            this.bahan = bahan.trim();
        }
    }

    @Override
    public String getJenis() {
        return "Joran";
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        return super.hitungBiayaSewa(hari) + 5000;
    }

    @Override
    public double hitungDendaKeterlambatan(int hariTerlambat) {
        if (hariTerlambat <= 0) {
            return 0;
        }
        return getHargaPerHari() * 0.5 * hariTerlambat;
    }

    @Override
    public void prosesSewaFisik() {
        if (getStok() <= 0) {
            System.out.println("Stok " + getNama() + " habis, tidak bisa disewa!");
        } else {
            setStok(getStok() - 1);
            System.out.println("Joran " + getNama() + " dicek (ruas dan guide), lalu diserahkan ke penyewa.");
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
        System.out.println("         -> Panjang: " + panjangCm + " cm | Bahan: " + bahan);
        if (jumlahRating == 0) {
            System.out.println("         -> Rating: belum ada");
        } else {
            System.out.printf("         -> Rating: %.1f (%d ulasan)%n", getRataRating(), jumlahRating);
        }
    }
}