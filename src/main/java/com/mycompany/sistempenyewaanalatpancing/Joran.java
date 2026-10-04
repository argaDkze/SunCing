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
public class Joran extends Alatpancing {

    private int panjangCm;
    private String bahan;

    public Joran(String kode, String nama, double hargaPerHari, int stok,
                 int panjangCm, String bahan) {
        super(kode, nama, hargaPerHari, stok);
        this.panjangCm = 0;
        this.bahan = "-";
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

    // Overriding: ada biaya asuransi Rp5.000 (sekali sewa)
    @Override
    public double hitungBiayaSewa(int hari) {
        return super.hitungBiayaSewa(hari) + 5000;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("         -> Panjang: " + panjangCm + " cm | Bahan: " + bahan);
    }
}