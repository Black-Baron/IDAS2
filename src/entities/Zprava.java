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
public class Zprava {
    int id;
    String cas;
    String obsah;
    String uzivatel_jmeno, uzivatel_prijmeni, kamarad_jmeno, kamarad_prijmeni;

    public Zprava(int id, String cas, String obsah) {
        this.id = id;
        this.cas = cas;
        this.obsah = obsah;
    }

    public Zprava(int id, String cas, String obsah, String uzivatel__jmeno, String uzivatel_prijmeni, String kamarad_jmeno, String kamarad_prijmeni) {
        this.id = id;
        this.cas = cas;
        this.obsah = obsah;
        this.uzivatel_jmeno = uzivatel__jmeno;
        this.uzivatel_prijmeni = uzivatel_prijmeni;
        this.kamarad_jmeno = kamarad_jmeno;
        this.kamarad_prijmeni = kamarad_prijmeni;
    }

    public String getUzivatel_jmeno() {
        return uzivatel_jmeno;
    }

    public String getUzivatel_prijmeni() {
        return uzivatel_prijmeni;
    }

    public String getKamarad_jmeno() {
        return kamarad_jmeno;
    }

    public String getKamarad_prijmeni() {
        return kamarad_prijmeni;
    }

    public void setUzivatel_jmeno(String uzivatel__jmeno) {
        this.uzivatel_jmeno = uzivatel__jmeno;
    }

    public void setUzivatel_prijmeni(String uzivatel_prijmeni) {
        this.uzivatel_prijmeni = uzivatel_prijmeni;
    }

    public void setKamarad_jmeno(String kamarad_jmeno) {
        this.kamarad_jmeno = kamarad_jmeno;
    }

    public void setKamarad_prijmeni(String kamarad_prijmeni) {
        this.kamarad_prijmeni = kamarad_prijmeni;
    }




    public int getId() {
        return id;
    }

    public String getCas() {
        return cas;
    }

    public String getObsah() {
        return obsah;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public void setObsah(String obsah) {
        this.obsah = obsah;
    }

    @Override
    public String toString() {
        return "Zprava{" + "id=" + id + ", cas=" + cas + ", obsah=" + obsah + '}';
    }
    
}
