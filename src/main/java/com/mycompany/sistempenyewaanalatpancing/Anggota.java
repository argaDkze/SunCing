/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
public class Anggota {

    private String id;
    private String nama;
    private String noHp;

    public Anggota(String id, String nama, String noHp) throws DataTidakValidException {
        setId(id);
        setNama(nama);
        setNoHp(noHp);
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setId(String id) throws DataTidakValidException {
        if (id == null || id.trim().equals("")) {
            throw new DataTidakValidException("ID anggota tidak boleh kosong!");
        }
        this.id = id.trim().toUpperCase();
    }

    public void setNama(String nama) throws DataTidakValidException {
        if (nama == null || nama.trim().equals("")) {
            throw new DataTidakValidException("Nama anggota tidak boleh kosong!");
        }
        this.nama = nama.trim();
    }

    public void setNoHp(String noHp) throws DataTidakValidException {
        if (noHp == null || noHp.trim().length() < 10) {
            throw new DataTidakValidException("Nomor HP minimal 10 karakter!");
        }
        this.noHp = noHp.trim();
    }

    public void tampilkanInfo() {
        System.out.printf("%-8s %-24s %-15s%n", id, nama, noHp);
    }
}