/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistempenyewaanalatpancing;

/**
 *
 * @author Hype AMD
 */
public interface Dapatdinilai {

    int RATING_MIN = 1;
    int RATING_MAX = 5;

    void beriRating(int rating) throws DataTidakValidException;

    double getRataRating();
}
