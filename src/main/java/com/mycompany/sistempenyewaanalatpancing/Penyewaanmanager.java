/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

import java.util.ArrayList;
import java.util.HashMap;

public class Penyewaanmanager {

    private ArrayList<Alatpancing> daftarAlat = new ArrayList<>();
    private HashMap<String, Anggota> dataAnggota = new HashMap<>();

    public void tambahAlat(Alatpancing alat) {
        daftarAlat.add(alat);
        Alatpancing.catatAlatBaru();
    }

    public boolean kodeSudahAda(String kode) {
        for (int i = 0; i < daftarAlat.size(); i++) {
            if (daftarAlat.get(i).getKode().equalsIgnoreCase(kode.trim())) {
                return true;
            }
        }
        return false;
    }

    public Alatpancing cariByKode(String kode) throws AlatTidakDitemukanException {
        for (int i = 0; i < daftarAlat.size(); i++) {
            if (daftarAlat.get(i).getKode().equalsIgnoreCase(kode.trim())) {
                return daftarAlat.get(i);
            }
        }
        throw new AlatTidakDitemukanException("Alat dengan kode \"" + kode.trim() + "\" tidak ditemukan!");
    }

    public ArrayList<Alatpancing> cariAlat(String kataKunci)
            throws AlatTidakDitemukanException, DataTidakValidException {
        if (kataKunci == null || kataKunci.trim().equals("")) {
            throw new DataTidakValidException("Kata kunci pencarian tidak boleh kosong!");
        }

        String kunci = kataKunci.trim().toLowerCase();
        ArrayList<Alatpancing> hasil = new ArrayList<>();

        for (int i = 0; i < daftarAlat.size(); i++) {
            Alatpancing alat = daftarAlat.get(i);
            boolean cocokKode = alat.getKode().toLowerCase().contains(kunci);
            boolean cocokNama = alat.getNama().toLowerCase().contains(kunci);
            boolean cocokJenis = alat.getJenis().toLowerCase().contains(kunci);
            if (cocokKode || cocokNama || cocokJenis) {
                hasil.add(alat);
            }
        }

        if (hasil.isEmpty()) {
            throw new AlatTidakDitemukanException("Tidak ada alat dengan kata kunci \"" + kataKunci.trim() + "\"!");
        }
        return hasil;
    }

    public void tampilkanSemua() {
        if (daftarAlat.isEmpty()) {
            System.out.println("Belum ada data alat pancing.");
            return;
        }
        tampilkanDaftar(daftarAlat);
        System.out.println("Jumlah alat di daftar     : " + daftarAlat.size());
        System.out.println("Total alat tercatat       : " + Alatpancing.getTotalAlat());
    }

    public void tampilkanDaftar(ArrayList<Alatpancing> daftar) {
        System.out.println("=========================================================================");
        System.out.printf("%-8s %-12s %-24s %-12s %-5s%n", "KODE", "JENIS", "NAMA", "HARGA/HARI", "STOK");
        System.out.println("=========================================================================");
        for (int i = 0; i < daftar.size(); i++) {
            Alatpancing alat = daftar.get(i);
            alat.tampilkanInfo();
            cetakKemampuan(alat);
        }
        System.out.println("=========================================================================");
    }

    private void cetakKemampuan(Alatpancing alat) {
        String kemampuan = "";
        if (alat instanceof Dapatdisewa) {
            kemampuan = kemampuan + "[Dapat Disewa] ";
        }
        if (alat instanceof Dapatdinilai) {
            kemampuan = kemampuan + "[Dapat Dinilai] ";
        }
        System.out.println("         -> Kemampuan: " + kemampuan);
    }

    public void tambahAnggota(Anggota anggota) throws DataTidakValidException {
        if (dataAnggota.containsKey(anggota.getId())) {
            throw new DataTidakValidException("ID anggota " + anggota.getId() + " sudah terdaftar!");
        }
        dataAnggota.put(anggota.getId(), anggota);
    }

    public boolean anggotaAda(String id) {
        return dataAnggota.containsKey(id.trim().toUpperCase());
    }

    public Anggota getAnggota(String id) {
        return dataAnggota.get(id.trim().toUpperCase());
    }

    public void tampilkanSemuaAnggota() {
        if (dataAnggota.isEmpty()) {
            System.out.println("Belum ada data anggota.");
            return;
        }
        System.out.println("Jumlah anggota: " + dataAnggota.size());
        System.out.println("=================================================");
        System.out.printf("%-8s %-24s %-15s%n", "ID", "NAMA", "NO HP");
        System.out.println("=================================================");
        for (String id : dataAnggota.keySet()) {
            dataAnggota.get(id).tampilkanInfo();
        }
        System.out.println("=================================================");
    }
}