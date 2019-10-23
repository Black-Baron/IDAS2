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
public class Uzivatel {
    String jmeno;
    String prijmeni;
    int id;
    String rokStudia;
    String pracoviste;
    String Fakulta;
    String heslo;
    String telefon;
    String email;

    public Uzivatel(String jmeno, String prijmeni, int id_uzivatel, String rokStudia, String Fakulta, String heslo, String telefon, String email, String pracoviste) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.id = id_uzivatel;
        this.rokStudia = rokStudia;
        this.Fakulta = Fakulta;
        this.heslo = heslo;
        this.telefon = telefon;
        this.email = email;
        this.pracoviste = pracoviste;
    }

    public Uzivatel(String jmeno, String prijmeni, String rokStudia, String pracoviste, String Fakulta, String heslo, String telefon, String email) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rokStudia = rokStudia;
        this.pracoviste = pracoviste;
        this.Fakulta = Fakulta;
        this.heslo = heslo;
        this.telefon = telefon;
        this.email = email;
    }

    public Uzivatel(String jmeno, String prijmeni, int id) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.id = id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public int getId() {
        return id;
    }

    public String getRokStudia() {
        return rokStudia;
    }

    public String getPracoviste() {
        return pracoviste;
    }

    public String getFakulta() {
        return Fakulta;
    }

    public String getHeslo() {
        return heslo;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRokStudia(String rokStudia) {
        this.rokStudia = rokStudia;
    }

    public void setPracoviste(String pracoviste) {
        this.pracoviste = pracoviste;
    }

    public void setFakulta(String Fakulta) {
        this.Fakulta = Fakulta;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Uzivatel{" + "jmeno=" + jmeno + ", prijmeni=" + prijmeni + ", id=" + id + ", rokStudia=" + rokStudia + ", pracoviste=" + pracoviste + ", Fakulta=" + Fakulta + ", heslo=" + heslo + ", telefon=" + telefon + ", email=" + email + '}';
    }


    
}
