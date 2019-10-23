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
public class Komentar {
    int id;
    String obsah;
    String cas;
    String uzivatel;
    int id_komentare;
    int id_uzivatele;
    int id_predmety;

    public Komentar(String obsah, String cas, int id_komentare, int id_uzivatele, int id_predmety) {
        this.obsah = obsah;
        this.cas = cas;
        this.id_komentare = id_komentare;
        this.id_uzivatele = id_uzivatele;
        this.id_predmety = id_predmety;
    }
        public Komentar(int id,String obsah, int id_komentare, int id_uzivatele, int id_predmety) {
            this.id = id;
        this.obsah = obsah;
        this.id_komentare = id_komentare;
        this.id_uzivatele = id_uzivatele;
        this.id_predmety = id_predmety;
    }
    public Komentar(int id,String obsah, String cas, int id_komentare, String uzivatel) {
        this.id = id;
        this.obsah = obsah;
        this.cas = cas;
        this.id_komentare = id_komentare;
        this.uzivatel = uzivatel;
    }

    public String getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(String uzivatel) {
        this.uzivatel = uzivatel;
    }


    public int getId() {
        return id;
    }

    public String getObsah() {
        return obsah;
    }

    public String getCas() {
        return cas;
    }

    public int getId_komentare() {
        return id_komentare;
    }

    public int getId_uzivatele() {
        return id_uzivatele;
    }

    public int getId_predmety() {
        return id_predmety;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setObsah(String obsah) {
        this.obsah = obsah;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public void setId_komentare(int id_komentare) {
        this.id_komentare = id_komentare;
    }

    public void setId_uzivatele(int id_uzivatele) {
        this.id_uzivatele = id_uzivatele;
    }

    public void setId_predmety(int id_predmety) {
        this.id_predmety = id_predmety;
    }
    
}
