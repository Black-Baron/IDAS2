package gui;

import database.DatabaseHelper;
import entities.Komentar;
import entities.Lajk;
import entities.Predmet;
import entities.Skupina;
import entities.Uzivatel;
import entities.Zprava;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author qwasyx0-ntb
 */
public class FXMLSocialNetworkController implements Initializable {

    private ObservableList selectedCells;
    private String uzivatel;
    private Uzivatel prihlasenyUzivatel;
    private Skupina vybranaSkupina;
    private Komentar vybranyKomentar;
    private Lajk vybranyLajk;
    private Uzivatel vybranyKamarad;
    private Uzivatel vybranyUzivatel;
    private Predmet vybranyPredmet;
    private Zprava vybranaZprava;
    private boolean prvniNacteni;
    private DatabaseHelper db;
    private Label label;
    private boolean vyhledavam;
    private int lastIdSkupiny;
    @FXML
    private Button buttonPridejKomentar;
    @FXML
    private TextField textFieldKomentar;
    @FXML
    private TableView<Skupina> tableViewSkupina;
    @FXML
    private TableColumn<Skupina, Integer> colSkupinaId;
    @FXML
    private TableColumn<Skupina, String> colSkupinaNazev;
    @FXML
    private TableColumn<Skupina, String> colSkupinaRole;
    @FXML
    private TableColumn<Skupina, String> colSkupinaPredmet;
    @FXML
    private TableColumn<Skupina, String> colSkupinaStudijniPlan;
    @FXML
    private TableColumn<Skupina, String> colSkupinaStudijniObor;
    @FXML
    private TableView<Komentar> tableViewKomentare;
    @FXML
    private TableColumn<Komentar, Integer> colKomentarId;
    @FXML
    private TableColumn<Komentar, String> colKomentarCas;
    @FXML
    private TableColumn<Komentar, String> colKomentarUzivatel;
    @FXML
    private TableColumn<Komentar, String> colKomentarObsah;
    @FXML
    private TableColumn<Komentar, Integer> colKomentarOdpoved;
    @FXML
    private TableView<Lajk> tableViewLajky;
    @FXML
    private TableColumn<Lajk, Integer> colLajkyId;
    @FXML
    private TableColumn<Lajk, String> colLajkyUzivatel;
    @FXML
    private TableColumn<Lajk, String> colLajkyKategorie;
    @FXML
    private TableColumn<Lajk, String> colLajkyKomentar;
    @FXML
    private Button buttonPridejLajk;
    @FXML
    private ComboBox<String> comboBoxLajk;
    @FXML
    private TableView<Uzivatel> tableViewKamaradi;
    @FXML
    private TableColumn<Uzivatel, Integer> colKamaradId;
    @FXML
    private TableColumn<Uzivatel, String> colKamaradJmeno;
    @FXML
    private TableColumn<Uzivatel, String> colKamaradPrijmeni;
    @FXML
    private TableView<Zprava> tableViewZpravy;
    @FXML
    private TableColumn<Zprava, Integer> ColZpravyId;
    @FXML
    private TableColumn<Zprava, Timestamp> colZpravyCas;
    @FXML
    private TableColumn<Zprava, String> colZpravyObsah;
    @FXML
    private TextField textFieldObsah;
    @FXML
    private Button buttonZpravaOdeslat;
    @FXML
    private TextField textFieldSkupinaId;
    @FXML
    private TextField textFieldSkupinaNazev;
    @FXML
    private ComboBox<String> comboBoxSkupinaRole;
    @FXML
    private ComboBox<String> comboBoxSkupinaPredmet;
    @FXML
    private ComboBox<String> comboBoxSkupinaStPlan;
    @FXML
    private ComboBox<String> comboBoxSkupinaStObor;
    @FXML
    private Button buttonSkupinaPridej;
    @FXML
    private Button buttonSkupinaUprav;
    @FXML
    private Button buttonSkupinaSmaz;
    @FXML
    private Button buttonKomentarOdpoved;
    @FXML
    private TextField textFieldKomentarId;
    @FXML
    private TextField textFieldPomocneKomentare;
    @FXML
    private Button buttonKomentarUprav;
    @FXML
    private Button buttonKomentarOdeber;
    @FXML
    private TextField textFieldLajkyId;
    @FXML
    private Button buttonLajkUprav;
    @FXML
    private Button buttonLajkOdeber;
    @FXML
    private Button buttonOdebratKamarada;
    @FXML
    private TextField textFieldZpravaId;
    @FXML
    private TableColumn<Zprava, String> colZpravyOdesilatel;
    @FXML
    private TableColumn<Zprava, String> colZpravyAdresat;
    @FXML
    private TextField textFieldStudijniPlanPom;
    @FXML
    private Button buttonOdhlasit;
    @FXML
    private TableView<Uzivatel> tableViewUzivatele;
    @FXML
    private TableColumn<Uzivatel, Integer> colUzivateleId;
    @FXML
    private TableColumn<Uzivatel, String> colUzivateleJmeno;
    @FXML
    private TableColumn<Uzivatel, String> colUzivatelePrijmeni;
    @FXML
    private TableColumn<Uzivatel, String> colUzivateleEmail;
    @FXML
    private TableColumn<Uzivatel, String> colUzivateleTelefon;
    @FXML
    private TableColumn<Uzivatel, String> colUzivateleRocnik;
    @FXML
    private TableColumn<Uzivatel, String> colUzivateleFakulta;
    @FXML
    private TableColumn<Uzivatel, String> colUzivatelePracoviste;
    @FXML
    private Button buttonUzivatelePridej;
    @FXML
    private TextField textFieldUzivatelPridejId;
    @FXML
    private TextField textFieldJmeno;
    @FXML
    private TextField textFieldPrijmeni;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldHeslo;
    @FXML
    private TextField textFieldRocnik;
    @FXML
    private TextField textFieldPracoviste;
    @FXML
    private TextField textFieldFakulta;
    @FXML
    private TextField textFieldTelefon;
    @FXML
    private TextField textFieldHledej;
    @FXML
    private Button buttonUlozitUdaje;
    @FXML
    private Button buttonEditovatUdaje;
    @FXML
    private TableView<Predmet> tableViuewPredmety;
    @FXML
    private TableColumn<Skupina, Integer> colPredmetyId;
    @FXML
    private TableColumn<Skupina, String> colPredmetyNazev;
    @FXML
    private TableColumn<Skupina, String> colPredmetyNazevSkupiny;
    @FXML
    private TableColumn<Skupina, String> colPredmetyZkratka;
    @FXML
    private TableColumn<Skupina, String> colPredmetyStPlan;
    @FXML
    private TableColumn<Skupina, String> colPredmetyStObor;
    @FXML
    private TextField textFieldPredmetyNazev;
    @FXML
    private Button buttonPredmetyPridatSkupinu;
    private ComboBox<String> comboBoxNastaveniPlan;
    @FXML
    private Button buttonPredmetUpravitSKupinu;
    @FXML
    private Button buttonSMazatSkupinu;
    @FXML
    private TextField textFieldPredmetyVyhledavani;
    @FXML
    private TextField textFieldPredmetyIdSkupiny;
    @FXML
    private Button buttonPredmetyPriradit;
    @FXML
    private ComboBox<String> comboBoxSkupinyBezPredmetu;
    @FXML
    private ComboBox<String> comboBoxSkupinyRole;
    @FXML
    private TextField textFieldPredmetyIdStPl;
    @FXML
    private TextField textFieldPredmetyIdStOb;
    @FXML
    private Button buttonZapsatSeDoSKupiny;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    public void setHelper(DatabaseHelper help) throws SQLException {
        db = help;
        nactiSkupiny();
        nactiKamarady();
        nactiUzivatele();
        nactiMojeUdaje();
        nactiPredmety();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uzivatel = FXMLLoginController.getPrihlasenyUzivatel();
        prihlasenyUzivatel = null;
        prvniNacteni = true;
        vyhledavam = false;
    }

    private void alertError(String exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Chyba");
        alert.setContentText(exception);
        alert.showAndWait();
    }
    
    //TODO: Vetsina prace s formulářem je tady
    //Je potreba upravit tak, aby byl splněn bod 9. zadání:
    
    /*9. Aplikace bude využívat minimálně 3 plnohodnotné formuláře
    (např. ošetření vstupních polí, apod.) pro vytváření nebo modifikaci
    dat v tabulkách, ostatní potřebné formuláře jsou samozřejmostí. 
    TODO: Tak to asi budeme muset překopat tu aplikaci tak, že např.
    poklikáním na řádek bude možnost přepisu údajů apod, 
    tedy bude tam menší množství tlačítek a funkčnost se 
    spíš přesune na myš a kód (ošetření vstupu)*/
    
    //Tedy jak mam v gui napr. u Skupin, komentaru a lajku vedle tabulek 
    //tlacitka a textfieldy tak to chce asi samostatny formular (pise min 3)
    //tak myslim ze jeden bude dobry v tomhle, dalsi u predmetu - jeden pro
    //vytvoreni nazvu skupiny - dle predmetu, nejakou nabidku, neco jako vyberes
    //predmet a navrhne ti to nazev (predmet.getJmeno() + " - skupina", nebo neco takoveho)
    //treti napr prirazeni techto vytvorenych nazvu skupin k predmetum, tedy tlacitka
    // co jsou pod tabulkou tak do samostatného a myslím ze by mohlo byt hotovo
    
        //Nutno brát v potaz bod 7.:
    /*7. Databázová aplikace umožňuje přidávat, modifikovat a mazat záznamy ve
    všech tabulkách minimálně pro roli administrátor. */
    // TODO: musim nejdriv tabulky udelat ale mam tento napad:
    //nová tabulka role_uzivatele - v uzivatele se prida foreign key a bude ukazovat
    //u kazdeho uzivatele bud 1 nebo 0 - 0 bezny, 1 - admin. takze vsude kde bude 
    //nejaka uprava/mazani udelas neco jako uzivatel.getRoleUzivatele (return 0/1) 
    //a kdyz bude 1 tak mazani bude mozne kdyz 0 alert ze nejsi admin... nebo to
    // proste nebude viditelne? jak budes chtit. 
    
    //Druha zmena bude role na role_na_skupine a nova tabulka opravneni_na_skupine
    //budou to jen ciselniky a zase se jen budeme ptat na foreign key jaky je
    //napr uzivatel bude jako vyucujici na skupine a tim padem bude mit v role_na_skupine
    //zapis vyucujici a foreign key bude na opravneni_na_skupine - zde toho muzem dat docela dost
    //jako treba opravneni_upravovat_prispevky ale ne uz mazat apod tady si muzem vyhrat, 
    //zatim to nech ja to vymyslim a pak se dohodnem
    
    //Pozn. je treba osetrit vstupni pole, vlastne kdekoliv se bere 
    //nejaky textfiedl.getText(); ktery neni pomocny (neviditelny/skryty, coz je
    //napr.textFieldPredmetyIdSkupiny - za tabulkou skryty, z neho
    //beru a ukladam si tam id vybraneho, jednoduche a nesikovne...
    //ale myslim ze moje pojmenovavani je celkem konzistentni, takze textfield
    //co ma v nazvu "Id" by mel byt ten skryty (ID by se nemela nikdy ukazovat uzivateli)
            
    //Editace tabulek by asi taky byla dobra funkce, takze napr. nakliknu si tabulku
    // nejaky radek a vyjede mi mi formular pro editaci, u smazání alert "Urcite chcete..."
    // nove vkladani samsotatny formular viz vyse ale neni podle me potreba u vsech
    //např. chat s kamaradem je podle me lepsi pro uzivatele rovnou v app a 
    //ne samostatne. Tedy rozhodovani zda samostatny nebo ne asi dle slozitosti
    //a/nebo nutnosti oddelenosti od zbytku.
    
    //Jednotný komentář pro celou aplikaci:
    //TODO: bud vymyslet jak psat komentare nebo pouzit nejakou normu treba od divise
    //jeste upresnim, okomentuju zbytek kodu abys taky mel vetsi prehled.
//--------------------------------------SKUPINY---------------------------------

    private void nactiSkupiny() throws SQLException {
        ObservableList<Skupina> sk = null;
        try {
            if (prihlasenyUzivatel == null) {
                prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
            }
            sk = FXCollections.observableList(db.getSkupinyAll(prihlasenyUzivatel));
        } catch (SQLException ex) {
            alertError(ex.toString());
        }
        colSkupinaId.setCellValueFactory(new PropertyValueFactory<>("id_nazev_skupiny"));
        colSkupinaNazev.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        colSkupinaPredmet.setCellValueFactory(new PropertyValueFactory<>("nazevPredmetu"));
        colSkupinaRole.setCellValueFactory(new PropertyValueFactory<>("nazevRole"));
        colSkupinaStudijniObor.setCellValueFactory(new PropertyValueFactory<>("nazevStudijnihoOboru"));
        colSkupinaStudijniPlan.setCellValueFactory(new PropertyValueFactory<>("nazevStudijnihoPlanu"));

        tableViewSkupina.setItems(sk);

        ObservableList<String> list = null;
        list = FXCollections.observableList(db.getPredmety());
        comboBoxSkupinaPredmet.setItems(list);

        ObservableList<String> list1 = null;
        list1 = FXCollections.observableList(db.getRole());
        comboBoxSkupinaRole.setItems(list1);

        ObservableList<String> list2 = null;
        list2 = FXCollections.observableList(db.getStudijniObory());
        comboBoxSkupinaStObor.setItems(list2);

        ObservableList<String> list3 = null;
        list3 = FXCollections.observableList(db.getStudijniPlany());
        comboBoxSkupinaStPlan.setItems(list3);

        ObservableList<String> list4 = null;
        list4 = FXCollections.observableList(db.getKategorie());
        comboBoxLajk.setItems(list4);
        comboBoxLajk.setValue("palec");

        selectedCells = tableViewSkupina.getSelectionModel().getSelectedItems();
        selectedCells.addListener((ListChangeListener.Change c1) -> {
            vybranaSkupina = tableViewSkupina.getSelectionModel().getSelectedItem();
            if (vybranaSkupina != null) {
                textFieldSkupinaId.setText(Integer.toString(vybranaSkupina.getId()));

                textFieldSkupinaNazev.setText(vybranaSkupina.getNazev());

                comboBoxSkupinaPredmet.getSelectionModel().select(vybranaSkupina.getNazevPredmetu());
                comboBoxSkupinaRole.getSelectionModel().select(vybranaSkupina.getNazevRole());
                comboBoxSkupinaStObor.getSelectionModel().select(vybranaSkupina.getNazevStudijnihoOboru());
                comboBoxSkupinaStPlan.getSelectionModel().select(vybranaSkupina.getNazevStudijnihoPlanu());
                try {
                    tableViewLajky.getItems().clear();
                    nactiKomentare();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLSocialNetworkController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        nactiKomentare();

    }

    @FXML
    private void PridejSkupinu(ActionEvent event) throws SQLException {
        if (textFieldPredmetyNazev.getText() != null) {
            db.pridejSkupinu(textFieldPredmetyNazev.getText());
        }
        nactiPredmety();

    }

    @FXML
    private void PriraditSkupinu(ActionEvent event) throws SQLException {
        if (comboBoxSkupinyBezPredmetu.getValue() != null && comboBoxSkupinyRole.getValue() != null) {
            if (prihlasenyUzivatel == null) {
                prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
            }
            String skupiny_bez = comboBoxSkupinyBezPredmetu.getSelectionModel().getSelectedItem();
            int id_skupiny_bez = db.getSkupinaId(skupiny_bez);
            String role = comboBoxSkupinyRole.getSelectionModel().getSelectedItem();
            int id_role = db.getRoleId(role);
            int id_planu = db.getIdPlanuZPredmetu(vybranyPredmet.getId());
            if(vybranyPredmet.getNazev_skupiny() == null){                          
            db.priradSkupinuPredmetu(vybranyPredmet.getId(), prihlasenyUzivatel.getId(), id_planu, id_role, id_skupiny_bez);
            } 
        } else {
            alertError("Vyberte skupinu a roli");
        }
        nactiSkupiny();
        nactiPredmety();
    }

    @FXML
    private void UpravSkupinu(ActionEvent event) throws SQLException {
        if (prihlasenyUzivatel == null) {
            prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
        }
        if (vybranyPredmet != null) {
                        String role = comboBoxSkupinyRole.getSelectionModel().getSelectedItem();
            int id_role = db.getRoleId(role);
            Skupina s = new Skupina(Integer.valueOf(textFieldPredmetyIdSkupiny.getText()),
                    vybranyPredmet.getId(), textFieldPredmetyNazev.getText(), id_role);
            db.upravSkupinu(s);
        } else {
            alertError("Vyberte skupinu pro úpravu");
        }
        nactiSkupiny();
        nactiPredmety();
    }

    @FXML
    private void SmazSkupinu(ActionEvent event) throws SQLException {
        if (vybranyPredmet != null) {
            Skupina s = new Skupina(Integer.valueOf(textFieldPredmetyIdSkupiny.getText()),
                    vybranyPredmet.getId());
            db.smazSkupinu(s);
        } else {
            alertError("Vyberte skupinu pro smazání");
        }
        nactiSkupiny();
        nactiPredmety();
    }

//----------------------------------KOMENTARE-----------------------------------
    private void nactiKomentare() throws SQLException {
        if (prihlasenyUzivatel == null) {
            prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
        }
        String predmet = comboBoxSkupinaPredmet.getSelectionModel().getSelectedItem();
        int id_predmet = db.getPredmetId(predmet);
        ObservableList<Komentar> k = null;
        k = FXCollections.observableList(db.getKomentareSkupiny(prihlasenyUzivatel.getId(), id_predmet));
        colKomentarId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colKomentarObsah.setCellValueFactory(new PropertyValueFactory<>("obsah"));
        colKomentarCas.setCellValueFactory(new PropertyValueFactory<>("cas"));
        colKomentarUzivatel.setCellValueFactory(new PropertyValueFactory<>("uzivatel"));
        colKomentarOdpoved.setCellValueFactory(new PropertyValueFactory<>("id_komentare"));

        tableViewKomentare.setItems(k);
        selectedCells = tableViewKomentare.getSelectionModel().getSelectedItems();
        selectedCells.addListener((ListChangeListener.Change c1) -> {
            vybranyKomentar = tableViewKomentare.getSelectionModel().getSelectedItem();
            if (vybranyKomentar != null) {
                textFieldKomentar.setText(vybranyKomentar.getObsah());
                if (vybranyKomentar.getId() != 0) {
                    textFieldKomentarId.setText(String.valueOf(vybranyKomentar.getId()));
                    try {
                        nactiLajky();
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLSocialNetworkController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        //nactiLajky();
    }

    @FXML
    private void PridejKomentar(ActionEvent event) throws SQLException {
        if (!textFieldKomentar.getText().isEmpty() && vybranaSkupina != null) {
            if (prihlasenyUzivatel == null) {
                prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
            }
            String predmet = comboBoxSkupinaPredmet.getSelectionModel().getSelectedItem();
            int id_predmet = db.getPredmetId(predmet);
            db.pridejKomentar(vybranaSkupina, prihlasenyUzivatel, id_predmet, textFieldKomentar.getText());
            nactiKomentare();
        } else {
            alertError("Vyberte skupinu pro přidání");
        }
    }

    @FXML
    private void OdpovedNaKomentar(ActionEvent event) throws SQLException {
        if (!textFieldKomentar.getText().isEmpty() && vybranaSkupina != null) {
            if (prihlasenyUzivatel == null) {
                prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
            }
            String predmet = comboBoxSkupinaPredmet.getSelectionModel().getSelectedItem();
            int id_predmet = db.getPredmetId(predmet);
            db.pridejOdpovedKomentar(vybranaSkupina, prihlasenyUzivatel, id_predmet,
                    textFieldKomentar.getText(), Integer.valueOf(textFieldKomentarId.getText()));
            nactiKomentare();
        } else {
            alertError("Vyberte komentář pro odpověď");
        }
    }

    @FXML
    private void UpravKomentar(ActionEvent event) throws SQLException {
        if (prihlasenyUzivatel == null) {
            prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
        }
        String predmet = comboBoxSkupinaPredmet.getSelectionModel().getSelectedItem();
        int id = Integer.valueOf(textFieldKomentarId.getText());
        String komentar = textFieldKomentarId.getText();
        int id_komentar = db.getKomentarId(komentar);
        int id_predmet = db.getPredmetId(predmet);
        if (vybranyKomentar != null) {
                        String a = vybranyKomentar.getUzivatel();
            String b= prihlasenyUzivatel.getEmail();
            int compare = a.compareTo(b);
            if (compare == 0) {
                Komentar k = new Komentar(id, textFieldKomentar.getText(), id_komentar, prihlasenyUzivatel.getId(), id_predmet);
                db.upravKomentar(k);
            } else {
                alertError("Pokoušíte se upravit cizí komentář");
            }
        } else {
            alertError("Vyberte komentář pro úpravu");
        }
        nactiKomentare();
    }

    @FXML
    private void OdeberKomentar(ActionEvent event) throws SQLException {
        if (vybranyKomentar != null) {
                        String a = vybranyKomentar.getUzivatel();
            String b= prihlasenyUzivatel.getEmail();
            int compare = a.compareTo(b);
            if (compare == 0) {

                db.smazKomentar(vybranyKomentar);
            } else {
                alertError("Pokoušíte se odstranit cizí komentář");
            }
        } else {
            alertError("Vyberte komentář pro smazání");
        }
        nactiKomentare();
    }

    //-----------------------------------LAJKY----------------------------------
    private void nactiLajky() throws SQLException {
        if (prvniNacteni) {
            prvniNacteni = false;
        } else {
            int id_komentare = Integer.valueOf(textFieldKomentarId.getText());
            ObservableList<Lajk> l = null;
            l = FXCollections.observableList(db.getLajkyNaKomentari(id_komentare));
            colLajkyId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colLajkyKategorie.setCellValueFactory(new PropertyValueFactory<>("kategorie"));
            colLajkyKomentar.setCellValueFactory(new PropertyValueFactory<>("id_komentare"));
            colLajkyUzivatel.setCellValueFactory(new PropertyValueFactory<>("uzivatel"));

            ObservableList<String> list2 = null;
            list2 = FXCollections.observableList(db.getKategorie());
            comboBoxLajk.setItems(list2);

            tableViewLajky.setItems(l);
            selectedCells = tableViewLajky.getSelectionModel().getSelectedItems();
            selectedCells.addListener((ListChangeListener.Change c1) -> {
                vybranyLajk = tableViewLajky.getSelectionModel().getSelectedItem();
                if (vybranyLajk != null) {
                    textFieldLajkyId.setText(String.valueOf(vybranyLajk.getId()));
                    comboBoxLajk.getSelectionModel().select(vybranyLajk.getKategorie());
                }
            });
        }
    }

    @FXML
    private void PridejLajk(ActionEvent event) throws SQLException {
        if (vybranyKomentar != null) {
            if (prihlasenyUzivatel == null) {
                prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
            }

            String kategorie = comboBoxLajk.getSelectionModel().getSelectedItem();
            int id_kategorie = db.getKategorieId(kategorie);
            String a = vybranyKomentar.getUzivatel();
            String b= prihlasenyUzivatel.getEmail();
            int compare = a.compareTo(b);
            if (compare == 0) {
                db.pridejLajk(vybranyKomentar, prihlasenyUzivatel, id_kategorie, Integer.valueOf(textFieldKomentarId.getText()));
            } else {
                alertError("Lze přidat pouze jeden like na komentář");
            }
            nactiLajky();
        }
    }

    @FXML
    private void UpravLajk(ActionEvent event) throws SQLException {
        if (prihlasenyUzivatel == null) {
            prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
        }
        String kategorie = comboBoxLajk.getSelectionModel().getSelectedItem();
        int id_kategorie = db.getKategorieId(kategorie);
        if (vybranyLajk != null) {
            String a = vybranyKomentar.getUzivatel();
            String b= prihlasenyUzivatel.getEmail();
            int compare = a.compareTo(b);
            if (compare == 0) {
                Lajk l = new Lajk(vybranyLajk.getId(), id_kategorie);
                db.upravLajk(vybranyLajk.getId(), l);

            } else {
                alertError("Pokoušíte se upravit cizí lajk");
            }
        } else {
            alertError("Vyberte lajk pro úpravu");
        }
        nactiLajky();
    }

    @FXML
    private void OdeberLajk(ActionEvent event) throws SQLException {
        if (vybranyLajk != null) {
                        String a = vybranyKomentar.getUzivatel();
            String b= prihlasenyUzivatel.getEmail();
            int compare = a.compareTo(b);
            if (compare == 0) {
                db.smazLajk(vybranyLajk);
            } else {
                alertError("Pokoušíte se smazat cizí lajk");
            }
        } else {
            alertError("Vyberte lajk pro odeebrání");
        }
        nactiLajky();
    }

    //---------------------------------KAMARADI---------------------------------
    private void nactiKamarady() throws SQLException {
        if (prihlasenyUzivatel == null) {
            prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
        }
        ObservableList<Uzivatel> l = null;
        l = FXCollections.observableList(db.getKamaradyUzivatele(prihlasenyUzivatel));
        colKamaradId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colKamaradJmeno.setCellValueFactory(new PropertyValueFactory<>("jmeno"));
        colKamaradPrijmeni.setCellValueFactory(new PropertyValueFactory<>("prijmeni"));

        tableViewKamaradi.setItems(l);
        selectedCells = tableViewKamaradi.getSelectionModel().getSelectedItems();
        selectedCells.addListener((ListChangeListener.Change c1) -> {
            vybranyKamarad = tableViewKamaradi.getSelectionModel().getSelectedItem();
            if (vybranyKamarad != null) {
                try {
                    nactiZpravy();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLSocialNetworkController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void OdeberKamarada(ActionEvent event) throws SQLException {
        if (vybranyKamarad != null) {
            if (prihlasenyUzivatel == null) {
                prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
            }
            db.odeberKamarada(vybranyKamarad, prihlasenyUzivatel);
            db.odeberKamarada(prihlasenyUzivatel, vybranyKamarad);
        } else {
            alertError("Vyberte kamaráda pro smazání");
        }
        nactiKamarady();
    }

    //--------------------------------ZPRAVY------------------------------------
    private void nactiZpravy() throws SQLException {
        if (prihlasenyUzivatel == null) {
            prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
        }
        ObservableList<Zprava> l = null;
        l = FXCollections.observableList(db.getZpravySUzivatelem(prihlasenyUzivatel, vybranyKamarad));
        ColZpravyId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colZpravyCas.setCellValueFactory(new PropertyValueFactory<>("cas"));
        colZpravyObsah.setCellValueFactory(new PropertyValueFactory<>("obsah"));
        colZpravyAdresat.setCellValueFactory(new PropertyValueFactory<>("kamarad_jmeno"));
        colZpravyOdesilatel.setCellValueFactory(new PropertyValueFactory<>("uzivatel_jmeno"));

        tableViewZpravy.setItems(l);
        selectedCells = tableViewZpravy.getSelectionModel().getSelectedItems();
        selectedCells.addListener((ListChangeListener.Change c1) -> {
            vybranaZprava = tableViewZpravy.getSelectionModel().getSelectedItem();
            if (vybranaZprava != null) {
                textFieldObsah.setText(vybranaZprava.getObsah());
                textFieldZpravaId.setText(String.valueOf(vybranaZprava.getId()));
            }
        });
    }

    @FXML
    private void OdeslatZpravu(ActionEvent event) throws SQLException {
        if (vybranyKamarad != null) {
            if (prihlasenyUzivatel == null) {
                prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
            }
            db.pridejZpravu(prihlasenyUzivatel, vybranyKamarad, textFieldObsah.getText());
            nactiZpravy();
        } else {
            alertError("Vyberte kamaráda pro odeslání zprávy");
        }
    }

    public void formExit(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage main_stage = new Stage();
            main_stage.setScene(new Scene(root1));
            main_stage.setResizable(false);
            main_stage.show();
            closeMain();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chyba");
            alert.setContentText("Can't load new window" + e);
            alert.showAndWait();
        }
    }

    public void closeMain() {
        Stage stage = new Stage();
        stage = (Stage) buttonOdhlasit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Odhlasit(ActionEvent event) throws IOException {
        formExit(event);
    }

    private void nactiUzivatele() throws SQLException {
        if (prihlasenyUzivatel == null) {
            prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
        }
        ObservableList<Uzivatel> u = null;
        if (vyhledavam) {
            u = FXCollections.observableList(db.hledejPodleJmena(textFieldHledej.getText()));
            vyhledavam = false;
        } else {
            u = FXCollections.observableList(db.nactiVsechnyUzivatele(prihlasenyUzivatel));
        }
        colUzivateleId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUzivateleEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUzivateleFakulta.setCellValueFactory(new PropertyValueFactory<>("Fakulta"));
        colUzivateleJmeno.setCellValueFactory(new PropertyValueFactory<>("jmeno"));
        colUzivatelePracoviste.setCellValueFactory(new PropertyValueFactory<>("pracoviste"));
        colUzivatelePrijmeni.setCellValueFactory(new PropertyValueFactory<>("prijmeni"));
        colUzivateleRocnik.setCellValueFactory(new PropertyValueFactory<>("rokStudia"));
        colUzivateleTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));

        tableViewUzivatele.setItems(u);
        selectedCells = tableViewUzivatele.getSelectionModel().getSelectedItems();
        selectedCells.addListener((ListChangeListener.Change c1) -> {
            vybranyUzivatel = tableViewUzivatele.getSelectionModel().getSelectedItem();
            if (vybranyUzivatel != null) {
                textFieldUzivatelPridejId.setText(String.valueOf(vybranyUzivatel.getId()));
            }
        });
    }

    @FXML
    private void PridejKamarada(ActionEvent event) throws SQLException {
        if (vybranyUzivatel != null) {
            if (prihlasenyUzivatel == null) {
                prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
            }
            db.pridejKamarada(prihlasenyUzivatel, Integer.valueOf(textFieldUzivatelPridejId.getText()));
            db.pridejKamarada(vybranyUzivatel, prihlasenyUzivatel.getId());
            nactiKamarady();
        }
    }

    @FXML
    private void HledejUzivatele(KeyEvent event) throws SQLException {
        if (textFieldHledej.getText() != null) {
            vyhledavam = true;
        }
        nactiUzivatele();
    }

    private void nactiMojeUdaje() throws SQLException {
        if (prihlasenyUzivatel == null) {
            prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
        }
        ObservableList<Uzivatel> u = null;
        u = FXCollections.observableList(db.getMojeUdaje(prihlasenyUzivatel));
        textFieldJmeno.setText(prihlasenyUzivatel.getJmeno());
        textFieldPrijmeni.setText(prihlasenyUzivatel.getPrijmeni());
        textFieldEmail.setText(prihlasenyUzivatel.getEmail());
        textFieldHeslo.setText(prihlasenyUzivatel.getHeslo());
        textFieldRocnik.setText(prihlasenyUzivatel.getRokStudia());
        textFieldPracoviste.setText(prihlasenyUzivatel.getPracoviste());
        textFieldFakulta.setText(prihlasenyUzivatel.getFakulta());
        textFieldTelefon.setText(prihlasenyUzivatel.getTelefon());
    }

    @FXML
    private void UlozNoveUdaje(ActionEvent event) throws SQLException {
        if (textFieldJmeno.getText() != null && textFieldPrijmeni.getText() != null && textFieldEmail.getText() != null && textFieldHeslo.getText() != null) {
            if (prihlasenyUzivatel == null) {
                prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
            }
            Uzivatel u = new Uzivatel(textFieldJmeno.getText(), textFieldPrijmeni.getText(), prihlasenyUzivatel.getId(),
                    textFieldRocnik.getText(), textFieldFakulta.getText(),
                    textFieldHeslo.getText(), textFieldTelefon.getText(),
                    textFieldEmail.getText(), textFieldPracoviste.getText());

            prihlasenyUzivatel = u;
            db.upravaUzivatele(prihlasenyUzivatel);
            nactiMojeUdaje();
            nactiUzivatele();
        } else {
            alertError("Jméno, příjmení, email a heslo nesmí být prázdné");
        }

    }

    @FXML
    private void EditujUdaje(ActionEvent event) {
        if (textFieldJmeno.isEditable()) {
            textFieldJmeno.setEditable(false);
            textFieldPrijmeni.setEditable(false);
            textFieldEmail.setEditable(false);
            textFieldHeslo.setEditable(false);
            textFieldRocnik.setEditable(false);
            textFieldPracoviste.setEditable(false);
            textFieldFakulta.setEditable(false);
            textFieldTelefon.setEditable(false);
        } else {
            textFieldJmeno.setEditable(true);
            textFieldPrijmeni.setEditable(true);
            textFieldEmail.setEditable(true);
            textFieldHeslo.setEditable(true);
            textFieldRocnik.setEditable(true);
            textFieldPracoviste.setEditable(true);
            textFieldFakulta.setEditable(true);
            textFieldTelefon.setEditable(true);
        }
    }

    private void nactiPredmety() throws SQLException {
        if (prihlasenyUzivatel == null) {
            prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
        }
        ObservableList<Predmet> u = null;
        if (vyhledavam) {
            u = FXCollections.observableList(db.hledejPredmet(textFieldPredmetyVyhledavani.getText()));
            vyhledavam = false;
        } else {
            u = FXCollections.observableList(db.nactiVsechnyPredmety());

        }
        colPredmetyId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPredmetyNazev.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        colPredmetyZkratka.setCellValueFactory(new PropertyValueFactory<>("zkratka"));
        colPredmetyNazevSkupiny.setCellValueFactory(new PropertyValueFactory<>("nazev_skupiny"));
        colPredmetyStObor.setCellValueFactory(new PropertyValueFactory<>("nazev_stud_ob"));
        colPredmetyStPlan.setCellValueFactory(new PropertyValueFactory<>("nazev_stud_pl"));

        ObservableList<String> list2 = null;
        list2 = FXCollections.observableList(db.getPredmetyBezSkupin());
        comboBoxSkupinaPredmet.setItems(list2);

        ObservableList<String> list = null;
        list = FXCollections.observableList(db.getSkupinyBezPredmetu());
        comboBoxSkupinyBezPredmetu.setItems(list);
        if (!list.isEmpty()) {
            comboBoxSkupinyBezPredmetu.setValue(list.get(0));
        }

        ObservableList<String> list1 = null;
        list1 = FXCollections.observableList(db.getRole());
        comboBoxSkupinyRole.setItems(list1);
       // comboBoxSkupinyRole.setValue(list1.get(0));

        tableViuewPredmety.setItems(u);
        selectedCells = tableViuewPredmety.getSelectionModel().getSelectedItems();
        selectedCells.addListener((ListChangeListener.Change c1) -> {
            vybranyPredmet = tableViuewPredmety.getSelectionModel().getSelectedItem();
            if (vybranyPredmet != null) {
                String nazev = vybranyPredmet.getNazev_skupiny();
                if (nazev == null) {
                    textFieldPredmetyNazev.setText("");
                } else {
                    textFieldPredmetyNazev.setText(String.valueOf(vybranyPredmet.getNazev_skupiny()));
                }
                textFieldPredmetyIdSkupiny.setText(String.valueOf(vybranyPredmet.getId()));
                textFieldPredmetyIdStOb.setText(String.valueOf(vybranyPredmet.getId_stob()));
                textFieldPredmetyIdStPl.setText(String.valueOf(vybranyPredmet.getId_stpl()));
            }
        });
    }

    @FXML
    private void HledejPredmet(KeyEvent event) throws SQLException {
        if (textFieldPredmetyVyhledavani.getText() != null) {
            vyhledavam = true;
        }
        nactiPredmety();
    }


    private void ZapsatStudijniPlan(ActionEvent event) throws SQLException {
                if (prihlasenyUzivatel == null) {
            prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
        }
          String plan = comboBoxNastaveniPlan.getSelectionModel().getSelectedItem();
            int id_planu = db.getStudijniPlanId(plan);
        int pocetzaznamu = db.getPocetPredmetuStPlanu(id_planu);   
        for (int i = 0; i < pocetzaznamu; i++) {
            
        }
    }

    @FXML
    private void ZapsatSeDoSkupiny(ActionEvent event) throws SQLException {
    if (vybranyPredmet != null && comboBoxSkupinyRole.getValue() != null) {
            if (prihlasenyUzivatel == null) {
                prihlasenyUzivatel = db.vytvorPrihlasenehoUzivatele(uzivatel);
            }
            String skupiny_bez = comboBoxSkupinyBezPredmetu.getSelectionModel().getSelectedItem();
            int id_skupiny_bez = db.getSkupinaId(skupiny_bez);
            String role = comboBoxSkupinyRole.getSelectionModel().getSelectedItem();
            int id_role = db.getRoleId(role);
            int id_planu = db.getIdPlanuZPredmetu(vybranyPredmet.getId());
            if(vybranyPredmet.getNazev_skupiny() != null){    
                int id_skupiny = db.getIdNazvuSkupiny(vybranyPredmet.getNazev_skupiny());
            db.priradSkupinuPredmetu(vybranyPredmet.getId(), prihlasenyUzivatel.getId(), vybranyPredmet.getId_stpl(), id_role, id_skupiny);
            } 
        } else {
            alertError("Vyberte předmět se skupinou a roli");
        }
        nactiSkupiny();
        nactiPredmety();
    }
}
