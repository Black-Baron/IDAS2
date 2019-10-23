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
public class StudijniPlan {
    int id;
    String nazev;
    String popis;
    int id_studijniObor;

    public StudijniPlan(int id, String nazev, String popis) {
        this.id = id;
        this.nazev = nazev;
        this.popis = popis;
    }

    public int getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }

    public int getId_studijniObor() {
        return id_studijniObor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public void setId_studijniObor(int id_studijniObor) {
        this.id_studijniObor = id_studijniObor;
    }

    @Override
    public String toString() {
        return "StudijniPlan{" + "id=" + id + ", nazev=" + nazev + ", popis=" + popis + ", id_studijniObory=" + id_studijniObor + '}';
    }
    
}
