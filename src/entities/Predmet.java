/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author qwasyx0-ntb
 */
public class Predmet {

    int id, id_stob, id_stpl;
    String nazev;
    String zkratka;
    String popis;
    String nazev_skupiny, nazev_stud_ob, nazev_stud_pl;

    public Predmet(int id, String nazev, String zkratka, String popis) {
        this.id = id;
        this.nazev = nazev;
        this.zkratka = zkratka;
        this.popis = popis;
    }

    public Predmet(int id, String nazev, String zkratka, String nazev_skupiny, String nazev_stud_ob, String nazev_stud_pl) {
        this.id = id;
        this.nazev = nazev;
        this.zkratka = zkratka;
        this.nazev_skupiny = nazev_skupiny;
        this.nazev_stud_ob = nazev_stud_ob;
        this.nazev_stud_pl = nazev_stud_pl;
    }

    public Predmet(int id, int id_stob, int id_stpl, String nazev, String zkratka, String nazev_skupiny, String nazev_stud_ob, String nazev_stud_pl) {
        this.id = id;
        this.id_stob = id_stob;
        this.id_stpl = id_stpl;
        this.nazev = nazev;
        this.zkratka = zkratka;
        this.nazev_skupiny = nazev_skupiny;
        this.nazev_stud_ob = nazev_stud_ob;
        this.nazev_stud_pl = nazev_stud_pl;
    }

    public int getId_stob() {
        return id_stob;
    }

    public int getId_stpl() {
        return id_stpl;
    }

    public void setId_stob(int id_stob) {
        this.id_stob = id_stob;
    }

    public void setId_stpl(int id_stpl) {
        this.id_stpl = id_stpl;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setNazev_skupiny(String nazev_skupiny) {
        this.nazev_skupiny = nazev_skupiny;
    }

    public void setNazev_stud_ob(String nazev_stud_ob) {
        this.nazev_stud_ob = nazev_stud_ob;
    }

    public void setNazev_stud_pl(String nazev_stud_pl) {
        this.nazev_stud_pl = nazev_stud_pl;
    }

    public String getNazev_skupiny() {
        return nazev_skupiny;
    }

    public String getNazev_stud_ob() {
        return nazev_stud_ob;
    }

    public String getNazev_stud_pl() {
        return nazev_stud_pl;
    }

    public void setZkratka(String zkratka) {
        this.zkratka = zkratka;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public int getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public String getZkratka() {
        return zkratka;
    }

    public String getPopis() {
        return popis;
    }

}
