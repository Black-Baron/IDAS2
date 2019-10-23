/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author qwasyx0-pc
 */
public class Lajk {
    int id;
    int id_komentare;
    int id_uzivatele;
    int id_kategorie;
    String kategorie, uzivatel;

    public Lajk(int id, int id_komentare, int id_uzivatele, int id_kategorie) {
        this.id = id;
        this.id_komentare = id_komentare;
        this.id_uzivatele = id_uzivatele;
        this.id_kategorie = id_kategorie;
    }

    public Lajk(int id, int kategorie) {
        this.id_kategorie = kategorie;
    }
    

    public Lajk(int id, String kategorie, String uzivatel, int komentar) {
        this.id = id;
        this.kategorie = kategorie;
        this.uzivatel = uzivatel;
        this.id_komentare = komentar;
    }

    public void setKategorie(String predmet) {
        this.kategorie = predmet;
    }

    public void setUzivatel(String uzivatel) {
        this.uzivatel = uzivatel;
    }


    public String getKategorie() {
        return kategorie;
    }

    public String getUzivatel() {
        return uzivatel;
    }



    public int getId() {
        return id;
    }

    public int getId_komentare() {
        return id_komentare;
    }

    public int getId_uzivatele() {
        return id_uzivatele;
    }

    public int getId_kategorie() {
        return id_kategorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_komentare(int id_komentare) {
        this.id_komentare = id_komentare;
    }

    public void setId_uzivatele(int id_uzivatele) {
        this.id_uzivatele = id_uzivatele;
    }

    public void setId_kategorie(int id_kategorie) {
        this.id_kategorie = id_kategorie;
    }
    
}
