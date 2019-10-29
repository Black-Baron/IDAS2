package gui;

import database.DatabaseHelper;
import entities.Uzivatel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author qwasyx0-ntb
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField textFieldLogin;
    @FXML
    private Button buttonPrihlasit;
    @FXML
    private PasswordField passwordFieldHeslo;
    
    private DatabaseHelper dh;
    private ArrayList<String> login;
    private static String uzivatel;
    @FXML
    private Button buttonRegistrovat;
    @FXML
    private TextField textFieldJmeno;
    @FXML
    private TextField textFiledPrijmeni;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldHeslo;
    @FXML
    private TextField textFieldPracoviste;
    @FXML
    private TextField textFieldFakulta;
    @FXML
    private TextField textFieldTelefon;
    @FXML
    private TextField textFieldRok;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uzivatel = "";
    }

    @FXML
    private void Prihlasit(ActionEvent event) throws IOException, SQLException {
        boolean log;
        String name = "st49636";
        String psw = "qwasyx0";
        log = login(name, psw);
        if (log == true) {
                        if (!dh.controllDB()) {
            dh.createScript();
          //  dh.insertScript();
          //TODO: upravit pro nove udaje, bude i import xml
            }
            login = dh.prihlaseniUzivatele(textFieldLogin.getText(), passwordFieldHeslo.getText());
            uzivatel = textFieldLogin.getText();

            if (login != null) {
                System.out.println("Signed in");
                formExit(event, textFieldLogin.getText());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Chyba");
                alert.setContentText("Špatně zadané přihlašovací údaje");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chyba");
            alert.setContentText("There was a problem with database connection");
            alert.showAndWait();

        }
    }

    //Přihlašovací metoda
    public boolean login(String login, String psw) {
        try {
            dh = new DatabaseHelper(login, psw);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String getPrihlasenyUzivatel(){
        String u = uzivatel;
        return u;
    }
    //Zobrazení okna Main
    public void formExit(ActionEvent event, String name) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLSocialNetwork.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage main_stage = new Stage();
            main_stage.setScene(new Scene(root1));
            main_stage.setResizable(false);
            FXMLSocialNetworkController controller = fxmlLoader.<FXMLSocialNetworkController>getController();
            controller.setHelper(dh);

            main_stage.show();

            main_stage.setTitle("Logged on " + name);
            closeLogin();
        } catch (IOException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chyba");
            alert.setContentText("Can't load new window" + e);
            alert.showAndWait();
        }
    }

    public void closeLogin() {
        Stage stage = new Stage();
        stage = (Stage) buttonPrihlasit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Registrovat(ActionEvent event) throws SQLException {
            Uzivatel novy = new Uzivatel(textFieldJmeno.getText(), textFiledPrijmeni.getText(), textFieldRok.getText(), textFieldPracoviste.getText(), textFieldFakulta.getText(), textFieldHeslo.getText(), textFieldTelefon.getText(), textFieldEmail.getText());
            System.out.println(novy);
            String name = "st49636";
        String psw = "qwasyx0";
        boolean log;
        log = login(name, psw);
        if (log == true) {
           dh.registraceUzivatele(novy);  
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Registrace úspěšná");
            alert.setContentText("Nyní se můžete přihlásit");
            alert.showAndWait();
        }
    }
}
