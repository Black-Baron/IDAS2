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
public class Skupina {

    int id_nazev_skupiny;
    String nazev, nazevPredmetu, nazevStudijnihoOboru, nazevRole, nazevStudijnihoPlanu;
    int id_uzivatele, id_role, id_predmety, id_studijni_plany;

    public Skupina(int id_nazev, int id_uzivatele, int id_role, int id_predmety, int id_studijni_plany) {
        this.id_nazev_skupiny = id_nazev;
        this.id_uzivatele = id_uzivatele;
        this.id_role = id_role;
        this.id_predmety = id_predmety;
        this.id_studijni_plany = id_studijni_plany;
    }

    public Skupina(int id, String nazev, String nazevPredmetu, String nazevStudijnihoOboru, String nazevRole, String nazevStudijnihoPlanu) {
        this.id_nazev_skupiny = id;
        this.nazev = nazev;
        this.nazevPredmetu = nazevPredmetu;
        this.nazevStudijnihoOboru = nazevStudijnihoOboru;
        this.nazevRole = nazevRole;
        this.nazevStudijnihoPlanu = nazevStudijnihoPlanu;
    }

    public Skupina(int id_nazev_skupiny, int id_predmety, String nazev, int id_role) {
        this.id_nazev_skupiny = id_nazev_skupiny;
        this.id_predmety = id_predmety;
        this.nazev = nazev;
        this.id_role = id_role;
    }
 public Skupina(int id_nazev_skupiny, int id_predmety) {
        this.id_nazev_skupiny = id_nazev_skupiny;
        this.id_predmety = id_predmety;
    }

    public Skupina(int id_nazev_skupiny, String nazev) {
        this.id_nazev_skupiny = id_nazev_skupiny;
        this.nazev = nazev;
    }
 
    public String getNazevPredmetu() {
        return nazevPredmetu;
    }

    public String getNazevStudijnihoOboru() {
        return nazevStudijnihoOboru;
    }

    public String getNazevRole() {
        return nazevRole;
    }

    public String getNazevStudijnihoPlanu() {
        return nazevStudijnihoPlanu;
    }

    public int getId_uzivatele() {
        return id_uzivatele;
    }

    public int getId_role() {
        return id_role;
    }

    public int getId_predmety() {
        return id_predmety;
    }

    public int getId_studijni_plany() {
        return id_studijni_plany;
    }

    public void setId_uzivatele(int id_uzivatele) {
        this.id_uzivatele = id_uzivatele;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public void setId_predmety(int id_predmety) {
        this.id_predmety = id_predmety;
    }

    public void setId_studijni_plany(int id_studijni_plany) {
        this.id_studijni_plany = id_studijni_plany;
    }

    public int getId() {
        return id_nazev_skupiny;
    }

    public String getNazev() {
        return nazev;
    }

    public void setId(int id) {
        this.id_nazev_skupiny = id;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setNazevPredmetu(String nazevPredmetu) {
        this.nazevPredmetu = nazevPredmetu;
    }

    public void setNazevStudijnihoOboru(String nazevStudijnihoOboru) {
        this.nazevStudijnihoOboru = nazevStudijnihoOboru;
    }

    public void setNazevRole(String nazevRole) {
        this.nazevRole = nazevRole;
    }

    public void setNazevStudijnihoPlanu(String nazevStudijnihoPlanu) {
        this.nazevStudijnihoPlanu = nazevStudijnihoPlanu;
    }

    @Override
    public String toString() {
        return "Skupina{" + "id=" + id_nazev_skupiny + ", nazev=" + nazev + ", id_uzivatele=" + id_uzivatele + ", id_role=" + id_role + ", id_predmety=" + id_predmety + ", id_studijni_plany=" + id_studijni_plany + '}';
    }

}
