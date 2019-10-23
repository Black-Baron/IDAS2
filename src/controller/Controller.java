/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DatabaseHelper;
import entities.Skupina;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author qwasyx0-pc
 */
public class Controller {
    
    private DatabaseHelper dh;
    
    public boolean login(String login, String password) {
        try {
            dh = new DatabaseHelper(login, password);
            return true;
        } catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Login controller exception");
        }
        return false;
    }
    public void pridejKomentarNaSkupinu(Skupina skupina, String obsah) throws SQLException {
      //  dh.pridejKomentar(skupina, obsah);
    }

}
