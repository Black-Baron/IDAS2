/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.Komentar;
import entities.Lajk;
import entities.Predmet;
import entities.Skupina;
import entities.StudijniObor;
import entities.Uzivatel;
import entities.Zprava;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;
import javafx.scene.control.Alert;

/**
 *
 * @author qwasyx0-ntb
 */
public class DatabaseHelper {

    //--------------------------------TABLES AND PKs----------------------------
    final String CREATE_TRP = "create table user_tables(table_name varchar2(50) not null)";
    final String ALTERT_TRP = "CREATE TABLE kamaradi_uzivatele ( id_uzivatele   INTEGER NOT NULL,id_kamarada INTEGER NOT NULL,aktivni CHAR(1) NOT NULL)";
    final String ALTERT_TRP_DATUM_NAR = "ALTER TABLE kamaradi_uzivatele ADD CONSTRAINT kamaradi_uzivatele_pk PRIMARY KEY ( id_uzivatele,id_kamarada )";
    final String ALTERT_TRP_NAROZEN = "CREATE TABLE kategorie (     id      INTEGER NOT NULL,     nazev   VARCHAR2(20) NOT NULL,     popis   varchar2(60) null )";
    final String ALTERT_TRP_OBRAZEK = "ALTER TABLE kategorie ADD CONSTRAINT kategorie_pk PRIMARY KEY ( id )";
    final String CREATE_VLAST = "CREATE UNIQUE INDEX KATEGORIE_INDEX ON KATEGORIE(NAZEV)";
    final String ALTERT_VLAST = "CREATE TABLE komentare (     id             INTEGER NOT NULL,     obsah          VARCHAR2(300) NOT NULL,     cas            TIMESTAMP NOT NULL,     id_komentare   INTEGER NULL,     id_uzivatele   INTEGER NOT NULL,     id_predmety    INTEGER not null)";
    final String CREATE_POZ = "ALTER TABLE komentare ADD CONSTRAINT komentare_pk PRIMARY KEY ( id )";
    final String ALTERT_FK_POZ_TRP = "CREATE TABLE lajky (     id             INTEGER NOT NULL,     id_uzivatele   INTEGER NOT NULL,     id_kategorie   INTEGER NOT NULL,     id_komentare   INTEGER NOT NULL )";
    final String ALTERT_FK_POZ_VLAST = "ALTER TABLE lajky ADD CONSTRAINT lajky_pk PRIMARY KEY ( id )";
    final String CREATE_SEQ_TRP_ID = "CREATE UNIQUE INDEX LAJKY_INDEX ON LAJKY(ID_UZIVATELE, ID_KOMENTARE)";
    final String CREATE_SEQ_VLAST_ID = "CREATE TABLE predmety (     id        INTEGER NOT NULL,     nazev     VARCHAR2(50) NOT NULL,     zkratka   VARCHAR2(20) NOT NULL,     popis     VARCHAR2(250) )";
    final String a = "ALTER TABLE predmety ADD CONSTRAINT predmety_pk PRIMARY KEY ( id )";
    final String b = "CREATE TABLE NAZVY_SKUPIN (    id        INTEGER NOT NULL,    NAZEV     VARCHAR2(50) NOT NULL,    POPIS     VARCHAR2(300) NULL )";
    final String c = "ALTER TABLE NAZVY_SKUPIN ADD CONSTRAINT NAZVY_SKUPIN_PK PRIMARY KEY (ID)";
    final String d = "CREATE TABLE predmety_planu (     id_predmety         INTEGER NOT NULL,     id_studijni_plany   INTEGER NOT NULL )";
    final String e = "ALTER TABLE predmety_planu ADD CONSTRAINT predmety_planu_pk PRIMARY KEY ( id_predmety,id_studijni_plany )";
    final String f = "CREATE TABLE role (     id      INTEGER NOT NULL,     nazev   VARCHAR2(20) NOT NULL,     popis   VARCHAR2(250) )";
    final String g = "ALTER TABLE role ADD CONSTRAINT role_pk PRIMARY KEY ( id )";
    final String h = "CREATE UNIQUE INDEX ROLE_INDEX ON ROLE(NAZEV)";
    final String j = "CREATE TABLE skupiny (     id_uzivatele        INTEGER NOT NULL,     id_role             INTEGER NOT NULL,     id_predmety         INTEGER NOT NULL,     id_studijni_plany   INTEGER NOT NULL,     id_nazvy_skupin     INTEGER NOT NULL )";
    final String hj = "ALTER TABLE skupiny ADD CONSTRAINT skupiny_pk PRIMARY KEY ( id_uzivatele, id_role, id_predmety, id_studijni_plany)";
    final String ch = "CREATE TABLE studijni_obory (     id      INTEGER NOT NULL,     nazev   VARCHAR2(40) NOT NULL,     popis   VARCHAR2(100) )";
    final String i = "ALTER TABLE studijni_obory ADD CONSTRAINT studijni_obory_pk PRIMARY KEY ( id )";
    final String sadf = "CREATE UNIQUE INDEX STUDIJNI_OBORY_INDEX ON STUDIJNI_OBORY(NAZEV)";
    final String asfg = "CREATE TABLE studijni_plany (     id                  INTEGER NOT NULL,     nazev               VARCHAR2(40) NOT NULL,     popis               VARCHAR2(250),     id_studijni_obory   INTEGER NOT NULL )";
    final String asdfgsd = "ALTER TABLE studijni_plany ADD CONSTRAINT studijni_plany_pk PRIMARY KEY ( id )";
    final String asad = "CREATE TABLE uzivatele (     id           INTEGER NOT NULL,     jmeno        VARCHAR2(20) NOT NULL,     prijmeni     VARCHAR2(30) NOT NULL,     rok_studia   VARCHAR2(10),     pracoviste   VARCHAR2(50),     fakulta      VARCHAR2(50),     heslo        VARCHAR2(20) NOT NULL,telefon      VARCHAR2(20),email        VARCHAR2(60) NOT NULL)";
    final String ht = "ALTER TABLE uzivatele ADD CONSTRAINT uzivatele_pk PRIMARY KEY ( id )";
    final String gew = "CREATE UNIQUE INDEX UZIVATELE_INDEX ON UZIVATELE(JMENO, PRIJMENI, EMAIL)";
    final String sfasdf = " CREATE TABLE zpravy (     id                      INTEGER NOT NULL,     cas                     TIMESTAMP NOT NULL,     obsah                   VARCHAR2(300) NOT NULL,     kam_uziv_id_uzivatele   INTEGER NOT NULL,     kam_uziv_id_kamarada INTEGER NOT NULL)";
    final String htrh = "ALTER TABLE zpravy ADD CONSTRAINT zpravy_pk PRIMARY KEY ( id )";

    //---------------------------------FOREIGN KEYS-----------------------------
    final String rdgerg = "ALTER TABLE kamaradi_uzivatele ADD CONSTRAINT kamaradi_uziv_uzivatele_fk FOREIGN KEY ( id_uzivatele ) REFERENCES uzivatele ( id )  ON DELETE CASCADE";
    final String rgereg = "ALTER TABLE kamaradi_uzivatele ADD CONSTRAINT kamaradi_uziv_uzivatele_fkv2 FOREIGN KEY ( id_kamarada ) REFERENCES uzivatele ( id ) ON DELETE CASCADE";
    final String fwefg = "ALTER TABLE komentare ADD CONSTRAINT komentare_komentare_fk FOREIGN KEY ( id_komentare ) REFERENCES komentare ( id )  ON DELETE CASCADE";
    final String wefwef = "ALTER TABLE komentare  ADD CONSTRAINT komentare_predmety_fk FOREIGN KEY ( id_predmety ) REFERENCES PREDMETY ( id )  ON DELETE CASCADE";
    final String wefewf = "ALTER TABLE komentare ADD CONSTRAINT komentare_uzivatele_fk FOREIGN KEY ( id_uzivatele ) REFERENCES uzivatele ( id ) ON DELETE CASCADE";
    final String edwdwe = "ALTER TABLE lajky ADD CONSTRAINT lajky_kategorie_fk FOREIGN KEY ( id_kategorie ) REFERENCES kategorie ( id )  ON DELETE CASCADE";
    final String dsfa = "ALTER TABLE lajky ADD CONSTRAINT lajky_komentare_fk FOREIGN KEY ( id_komentare ) REFERENCES komentare ( id ) ON DELETE CASCADE";
    final String asdfsa = "ALTER TABLE lajky ADD CONSTRAINT lajky_uzivatele_fk FOREIGN KEY ( id_uzivatele ) REFERENCES uzivatele ( id )  ON DELETE CASCADE";
    final String asdfs = "ALTER TABLE predmety_planu ADD CONSTRAINT predm_planu_predmety_fk FOREIGN KEY ( id_predmety ) REFERENCES predmety ( id )  ON DELETE CASCADE";
    final String asdf = "ALTER TABLE predmety_planu ADD CONSTRAINT predm_planu_studij_plany_fk FOREIGN KEY ( id_studijni_plany ) REFERENCES studijni_plany ( id ) ON DELETE CASCADE";
    final String fhds = "ALTER TABLE skupiny ADD CONSTRAINT skupiny_predm_planu_fk FOREIGN KEY ( id_predmety,id_studijni_plany ) REFERENCES predmety_planu ( id_predmety,id_studijni_plany ) ON DELETE CASCADE";
    final String sfse = "ALTER TABLE skupiny ADD CONSTRAINT skupiny_role_fk FOREIGN KEY ( id_role ) REFERENCES role ( id ) ON DELETE CASCADE";
    final String wedw = "ALTER TABLE skupiny ADD CONSTRAINT skupiny_uzivatele_fk FOREIGN KEY ( id_uzivatele ) REFERENCES uzivatele ( id ) ON DELETE CASCADE";
    final String wedwe = "ALTER TABLE skupiny ADD CONSTRAINT skupiny_nazvy_skupin_fk FOREIGN KEY ( id_nazvy_skupin ) REFERENCES nazvy_skupin ( id ) ON DELETE CASCADE";
    final String wedwef = "ALTER TABLE studijni_plany ADD CONSTRAINT studij_pl_studij_obory_fk FOREIGN KEY ( id_studijni_obory ) REFERENCES studijni_obory ( id )ON DELETE CASCADE";
    final String hzthz = "ALTER TABLE zpravy  ADD CONSTRAINT zpravy_kam_uzivatele_fk FOREIGN KEY ( kam_uziv_id_uzivatele,kam_uziv_id_kamarada )  REFERENCES kamaradi_uzivatele ( id_uzivatele,id_kamarada ) ON DELETE CASCADE";

    //--------------------------------SEQUENCES---------------------------------
    final String sefqw = "CREATE SEQUENCE kategorie_id_seq START WITH 7 NOCACHE ORDER";
    final String wedwed = "CREATE SEQUENCE komentare_id_seq START WITH 1 NOCACHE ORDER";
    final String erger = "CREATE SEQUENCE lajky_id_seq START WITH 1 NOCACHE ORDER";
    final String efwefw = "CREATE SEQUENCE nazvy_skupin_id_seq START WITH 6 NOCACHE ORDER";
    final String jkumz = "CREATE SEQUENCE predmety_id_seq START WITH 21 NOCACHE ORDER";
    final String tzrjtz = "CREATE SEQUENCE role_id_seq START WITH 4 NOCACHE ORDER";
    final String fewfwe = "CREATE SEQUENCE studijni_obory_id_seq START WITH 3 NOCACHE ORDER";
    final String ewfewfgweg = "CREATE SEQUENCE studijni_plany_id_seq START WITH 5 NOCACHE ORDER";
    final String hztjkuk = "CREATE SEQUENCE uzivatele_id_seq START WITH 22 NOCACHE ORDER";
    final String defwwedqw = "CREATE SEQUENCE zpravy_id_seq START WITH 7 NOCACHE ORDER";

    //-----------------------------VIEWS----------------------------------------
    final String efgrh = "CREATE OR REPLACE FORCE EDITIONABLE VIEW VIEW_KOMENTARE AS SELECT k.id, k.obsah, k.cas, u.email, p.id as id_predmety, u.id as id_uzivatele, k.id_komentare, p.nazev, u.jmeno,u.prijmeni FROM komentare k left JOIN uzivatele u ON k.id_uzivatele = u.id left join predmety p on k.id_predmety = p.id";
    final String htzjzu = "CREATE OR REPLACE FORCE EDITIONABLE VIEW VIEW_LAJKY AS select l.id, l.id_uzivatele, l.id_kategorie, l.id_komentare, k.nazev as nazev_kategorie, kom.obsah, kom.cas, u.jmeno, u.prijmeni, u.email, p.nazev as nazev_predmetu from lajky l left join kategorie k on l.id_kategorie = k.id left join komentare kom on l.id_komentare = kom.id left join uzivatele u on l.id_uzivatele = u.id left join predmety p on kom.id_predmety = p.id";
    final String edwfgwg = "CREATE OR REPLACE FORCE EDITIONABLE VIEW VIEW_SKUPINY AS SELECT NS.NAZEV, S.ID_NAZVY_SKUPIN, S.ID_UZIVATELE, U.EMAIL, S.ID_ROLE, r.nazev AS ROLE_NAZEV, S.ID_PREDMETY, P.NAZEV AS PREDMET_NAZEV, S.ID_STUDIJNI_PLANY, SP.NAZEV AS STUDIJNI_PLAN_NAZEV, SP.ID_STUDIJNI_OBORY, SO.NAZEV AS STUDIJNI_OBOR_NAZEV FROM SKUPINY S LEFT JOIN NAZVY_SKUPIN NS ON S.ID_NAZVY_SKUPIN = NS.ID LEFT JOIN UZIVATELE U ON s.id_uzivatele = u.id LEFT JOIN ROLE R ON s.id_role = r.id LEFT JOIN PREDMETY_PLANU PP ON s.id_predmety = pp.id_predmety LEFT JOIN PREDMETY P ON pp.id_predmety = p.id LEFT JOIN STUDIJNI_PLANY SP ON pp.id_studijni_plany = sp.id LEFT JOIN STUDIJNI_OBORY SO ON sp.id_studijni_obory = so.id";
    final String htzujk = "CREATE OR REPLACE FORCE EDITIONABLE VIEW VIEW_KAMARADI AS SELECT K.ID_KAMARADA, k.id_uzivatele, uk.jmeno AS KAMARAD_JMENO, uk.prijmeni AS KAMARAD_PRIJMENI, k.aktivni FROM KAMARADI_UZIVATELE K LEFT JOIN UZIVATELE UU ON k.id_uzivatele = UU.id LEFT JOIN UZIVATELE UK ON k.id_kamarada = UK.ID";
    final String juztjkziu = "CREATE OR REPLACE FORCE EDITIONABLE VIEW VIEW_ZPRAVY AS SELECT Z.ID, Z.CAS, Z.OBSAH, z.kam_uziv_id_kamarada AS ID_KAMARADA, z.kam_uziv_id_uzivatele AS ID_UZIVATELE, K.AKTIVNI, u.jmeno AS KAMARAD_JMENO, u.email AS KAMARAD_EMAIL, u.prijmeni AS KAMARAD_PRIJMENI, u1.jmeno as UZIVATEL_JMENO,u1.prijmeni AS UZIVATEL_PRIJMENI FROM ZPRAVY Z LEFT JOIN KAMARADI_UZIVATELE K ON z.kam_uziv_id_kamarada = k.id_kamarada LEFT JOIN UZIVATELE U ON k.id_kamarada = u.id LEFT JOIN KAMARADI_UZIVATELE K1 ON z.kam_uziv_id_uzivatele = k1.id_UZIVATELE LEFT JOIN UZIVATELE U1 ON k1.id_uzivatele = u1.id ";
    final String wdwedf = "CREATE OR REPLACE FORCE EDITIONABLE VIEW VIEW_PREDMETY  AS SELECT P.NAZEV AS NAZEV_PREDMETU,P.ID, P.ZKRATKA,S.ID_NAZVY_SKUPIN,  ns.nazev AS NAZEV_SKUPINY, so.nazev AS NAZEV_STUDIJNI_OBOR,so.id as ID_STUDIJNIHO_OBORU, sp.nazev AS NAZEV_STUDIJNI_PLAN, SP.ID AS ID_STUDIJNIHO_PLANU FROM PREDMETY P LEFT JOIN PREDMETY_PLANU PP ON P.ID = pp.id_predmety LEFT JOIN SKUPINY S ON s.id_predmety = P.ID LEFT JOIN NAZVY_SKUPIN NS ON S.ID_NAZVY_SKUPIN = ns.id LEFT JOIN STUDIJNI_PLANY SP ON PP.ID_STUDIJNI_PLANY = sp.id LEFT JOIN STUDIJNI_OBORY SO ON SP.ID_STUDIJNI_OBORY = so.id";
    final String wdwedfD = "CREATE OR REPLACE FORCE EDITIONABLE VIEW VIEW_NAZVY AS SELECT NS.ID, NS.NAZEV , s.id_predmety FROM NAZVY_SKUPIN NS LEFT JOIN SKUPINY S ON s.id_nazvy_skupin = NS.ID";

    //-----------------------------TRIGGERS-------------------------------------
    //------------------------------DROPS---------------------------------------
    final String DROP_POZ = "DROP VIEW VIEW_KOMENTARE";
    final String DROP_TRP = "DROP VIEW VIEW_LAJKY";
    final String DROP_VLAST = "DROP VIEW VIEW_SKUPINY";
    final String DROP_SEQ_TRP_ID = "DROP VIEW VIEW_KAMARADI";
    final String DROP_SEQ_VLAST_ID = "DROP VIEW VIEW_ZPRAVY";
    final String DROP_SEQ_VLAST_IDD = "DROP VIEW VIEW_NAZVY";

    final String ouihuioh = "DROP VIEW VIEW_PREDMETY";
    final String uzguzgz = "DROP INDEX KATEGORIE_INDEX";
    final String ztfztf = "DROP INDEX LAJKY_INDEX";
    final String oijoj = "DROP INDEX ROLE_INDEX";
    final String fztfzt = "DROP INDEX STUDIJNI_OBORY_INDEX";
    final String oijoijoi = "DROP INDEX UZIVATELE_INDEX";

    final String tzfztfztd = "DROP TABLE ZPRAVY";
    final String uzuu = "DROP TABLE KAMARADI_UZIVATELE";
    final String izugtzuf = "DROP TABLE LAJKY";
    final String fztuzui = "DROP TABLE KATEGORIE";
    final String ruihoi = "DROP TABLE KOMENTARE";
    final String ihuhh = "DROP TABLE SKUPINY";
    final String rztfzf = "DROP TABLE ROLE";
    final String oij = "DROP TABLE PREDMETY_PLANU";
    final String zfdf = "DROP TABLE PREDMETY";
    final String oijoij = "DROP TABLE STUDIJNI_PLANY";
    final String guzf = "DROP TABLE STUDIJNI_OBORY";
    final String tfzfzt = "DROP TABLE UZIVATELE";
    final String jioij = "DROP TABLE NAZVY_SKUPIN";
    final String uzguf = "DROP TABLE USER_TABLES";

    final String rhtfrfj = "DROP SEQUENCE KATEGORIE_ID_SEQ";
    final String rtzjtzu = "DROP SEQUENCE KOMENTARE_ID_SEQ";
    final String wefedw = "DROP SEQUENCE LAJKY_ID_SEQ";
    final String kloiul = "DROP SEQUENCE PREDMETY_ID_SEQ";
    final String ewfdf = "DROP SEQUENCE ROLE_ID_SEQ";
    final String jzukz = "DROP SEQUENCE NAZVY_SKUPIN_ID_SEQ";
    final String kiukui = "DROP SEQUENCE STUDIJNI_OBORY_ID_SEQ";
    final String hjtzujk = "DROP SEQUENCE STUDIJNI_PLANY_ID_SEQ";
    final String hjzutku = "DROP SEQUENCE UZIVATELE_ID_SEQ";
    final String ferf = "DROP SEQUENCE ZPRAVY_ID_SEQ";

    //-----------------------------INSEERTS-------------------------------------
    //STUDIJNI_OBORY
    final String dfg = " Insert into ST49636.STUDIJNI_OBORY (ID,NAZEV,POPIS) values ('1','Informační technologie','IT')";
    final String dfgfew = " Insert into ST49636.STUDIJNI_OBORY (ID,NAZEV,POPIS) values ('2','Řízení procesů','ŘP')";

//STUDIJNI_PLANY
    final String dfgfsew = " Insert into ST49636.STUDIJNI_PLANY (ID,NAZEV,POPIS,ID_STUDIJNI_OBORY) values ('1','Elektrotechnika a informatika','Elektrotechnika a informatika','1')";
    final String dfgef = " Insert into ST49636.STUDIJNI_PLANY (ID,NAZEV,POPIS,ID_STUDIJNI_OBORY) values ('2','Informační technologie','Informační technologie','1')";
    final String sdf = " Insert into ST49636.STUDIJNI_PLANY (ID,NAZEV,POPIS,ID_STUDIJNI_OBORY) values ('3','Komunikační a mikroprocesor technika','Komunikační a mikroprocesorová technika','2')";
    final String sdfsdf = " Insert into ST49636.STUDIJNI_PLANY (ID,NAZEV,POPIS,ID_STUDIJNI_OBORY) values ('4','Řízení procesů','Řízení procesů','2')";

//UZIVATELE
    final String sdfasd = " Insert into ST49636.UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('1','Lubomír','Andrle',null,'KIT','fei','a','390851605','lubomir.andrle@upce.cz')";
    final String dfgfd = " Insert into ST49636.UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('2','Eduard','Babulak',null,'KIT','fei','a','924853232','eduard.babulak@upce.cz')";
    final String dfgd = " Insert into ST49636.UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('3','Michael','Bažant',null,'KST','fei','a','458719395','michael.bazant@upce.cz')";
    final String asdfg = " Insert into ST49636.UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('4','Miroslav','Benedikovič',null,'KST','fei','a','182937241','bene@fria.fri.utc.sk')";
    final String fewdfg = " Insert into ST49636.UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('5','Ladislav','Beran',null,'KE','fei','a','960114408','st25775@student.upce.cz')";
    final String adsgasd = " Insert into ST49636.UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('35','Tomáš','Holý','3',null ,null,'a','787548175','st49636@student.upce.cz')";

//PREDMETY
    final String gadsg = " Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('1','Odborná praxe - exkurze I','IOPEX','Odborná praxe - exkurze I')";
    final String wefaw = " Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('2','Architektura počítačů','IUDP','Architektura počítačů')";
    final String asdfdas = " Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('3','Základy algoritmizace','IZALG','Základy algoritmizace')";
    final String gasdg = " Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('4','Základy ekonomie','IZEK','Základy ekonomie')";
    final String wedfw = " Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('5','Lineární algebra','ILALG','Lineární algebra')";

//ROLE
    final String gdsag = " Insert into ST49636.ROLE (ID,NAZEV,POPIS) values ('1','ucitel','Učitel')";
    final String wefwe = " Insert into ST49636.ROLE (ID,NAZEV,POPIS) values ('2','student','Student')";
    final String gtrg = " Insert into ST49636.ROLE (ID,NAZEV,POPIS) values ('3','doktorant','Doktorant')";

//KATEGORIE
    final String wefwes = " Insert into ST49636.KATEGORIE (ID,NAZEV,POPIS) values ('1','palec',null)";
    final String hrtw = " Insert into ST49636.KATEGORIE (ID,NAZEV,POPIS) values ('2','smich',null)";
    final String gtr = " Insert into ST49636.KATEGORIE (ID,NAZEV,POPIS) values ('3','mracoun',null)";
    final String wefg = " Insert into ST49636.KATEGORIE (ID,NAZEV,POPIS) values ('4','srdce',null)";
    final String wefgh = " Insert into ST49636.KATEGORIE (ID,NAZEV,POPIS) values ('5','plac',null)";
    final String gwefwe = " Insert into ST49636.KATEGORIE (ID,NAZEV,POPIS) values ('6','udiveni',null)";

//PREDMETY_PLANU
    final String qwd = " Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('1','1')";
    final String wefweff = " Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('2','1')";
    final String qwdd = " Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('3','1')";
    final String gre = " Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('4','1')";
    final String qwds = " Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('5','1')";

//NAZVY_SKUPIN
    final String uik = " Insert into ST49636.NAZVY_SKUPIN (ID,NAZEV,POPIS) values ('1','Praxe - skupina','Skupina1')";
    final String uiklui = " Insert into ST49636.NAZVY_SKUPIN (ID,NAZEV,POPIS) values ('2','Architektura - skupins','Skupina2')";
    final String loi = " Insert into ST49636.NAZVY_SKUPIN (ID,NAZEV,POPIS) values ('3','Algoritmizace - skupina','Skupina3')";
    final String zuj = " Insert into ST49636.NAZVY_SKUPIN (ID,NAZEV,POPIS) values ('4','Ekonomie - skupina','Skupina4')";
    final String tzj = " Insert into ST49636.NAZVY_SKUPIN (ID,NAZEV,POPIS) values ('5','Algebra - skupina','Skupina5')";

//KAMARADI_UZIVATELE
    final String iol = " Insert into ST49636.KAMARADI_UZIVATELE (ID_UZIVATELE,ID_KAMARADA,AKTIVNI) values ('21','1','A')";
    final String oil = " Insert into ST49636.KAMARADI_UZIVATELE (ID_UZIVATELE,ID_KAMARADA,AKTIVNI) values ('1','21','A')";

//SKUPINY
    final String ioljtz = " Insert into ST49636.SKUPINY (ID_UZIVATELE,ID_ROLE,ID_PREDMETY,ID_STUDIJNI_PLANY,ID_NAZVY_SKUPIN) values ('21','2','1','1','1')";
    final String iolz = " Insert into ST49636.SKUPINY (ID_UZIVATELE,ID_ROLE,ID_PREDMETY,ID_STUDIJNI_PLANY,ID_NAZVY_SKUPIN) values ('21','2','2','1','2')";
    final String lio = " Insert into ST49636.SKUPINY (ID_UZIVATELE,ID_ROLE,ID_PREDMETY,ID_STUDIJNI_PLANY,ID_NAZVY_SKUPIN) values ('21','2','3','1','3')";
    final String iolp = " Insert into ST49636.SKUPINY (ID_UZIVATELE,ID_ROLE,ID_PREDMETY,ID_STUDIJNI_PLANY,ID_NAZVY_SKUPIN) values ('21','2','4','1','4')";
    final String wefgpo = " Insert into ST49636.SKUPINY (ID_UZIVATELE,ID_ROLE,ID_PREDMETY,ID_STUDIJNI_PLANY,ID_NAZVY_SKUPIN) values ('21','2','5','1','5')";

//ZPRAVY
    final String zjt = " Insert into ST49636.ZPRAVY (ID,CAS,OBSAH,KAM_UZIV_ID_UZIVATELE,KAM_UZIV_ID_KAMARADA) values ('1',to_timestamp('16.05.19 16:33:33,237000000','DD.MM.RR HH24:MI:SSXFF'),'Prvni zprava','21','1')";
    final String optr = " Insert into ST49636.ZPRAVY (ID,CAS,OBSAH,KAM_UZIV_ID_UZIVATELE,KAM_UZIV_ID_KAMARADA) values ('2',to_timestamp('16.05.19 16:35:33,237000000','DD.MM.RR HH24:MI:SSXFF'),'druha zprava','21','1')";
    final String erh = " Insert into ST49636.ZPRAVY (ID,CAS,OBSAH,KAM_UZIV_ID_UZIVATELE,KAM_UZIV_ID_KAMARADA) values ('3',to_timestamp('16.05.19 16:36:33,237000000','DD.MM.RR HH24:MI:SSXFF'),'Treti zprava','21','1')";
    final String ewfwg = " Insert into ST49636.ZPRAVY (ID,CAS,OBSAH,KAM_UZIV_ID_UZIVATELE,KAM_UZIV_ID_KAMARADA) values ('4',to_timestamp('16.05.19 16:34:33,237000000','DD.MM.RR HH24:MI:SSXFF'),'Prvni odpoved','1','21')";
    final String wegweg = " Insert into ST49636.ZPRAVY (ID,CAS,OBSAH,KAM_UZIV_ID_UZIVATELE,KAM_UZIV_ID_KAMARADA) values ('5',to_timestamp('16.05.19 16:37:33,237000000','DD.MM.RR HH24:MI:SSXFF'),'Druha odpoved','1','21')";
    final String egwgweg = " Insert into ST49636.ZPRAVY (ID,CAS,OBSAH,KAM_UZIV_ID_UZIVATELE,KAM_UZIV_ID_KAMARADA) values ('6',to_timestamp('16.05.19 16:38:33,237000000','DD.MM.RR HH24:MI:SSXFF'),'treti odpoved','1','21')";

//USER_TABLES
    final String ewgwe = " Insert into ST49636.USER_TABLES (TABLE_NAME) values ('ROLE')";
    final String wef = " Insert into ST49636.USER_TABLES (TABLE_NAME) values ('KATEGORIE')";
    final String qwdwef = " Insert into ST49636.USER_TABLES (TABLE_NAME) values ('PREDMETY')";
    final String weff = " Insert into ST49636.USER_TABLES (TABLE_NAME) values ('UZIVATELE')";
    final String gwe = " Insert into ST49636.USER_TABLES (TABLE_NAME) values ('STUDIJNI_PLANY')";
    final String gewg = " Insert into ST49636.USER_TABLES (TABLE_NAME) values ('STUDIJNI_OBORY')";
    final String weg = " Insert into ST49636.USER_TABLES (TABLE_NAME) values ('PREDMETY_PLANU')";
    final String wefwweff = " Insert into ST49636.USER_TABLES (TABLE_NAME) values ('SKUPINY')";
    final String gwegw = " Insert into ST49636.USER_TABLES (TABLE_NAME) values ('KOMENTARE')";
    final String gweg = " Insert into ST49636.USER_TABLES (TABLE_NAME) values ('LAJKY')";
    final String weeef = " Insert into ST49636.USER_TABLES (TABLE_NAME) values ('KAMARADI_UZIVATELE')";
    final String wdqq = " Insert into ST49636.USER_TABLES (TABLE_NAME) values ('ZPRAVY')";

    private int lastIdZpravy = 0;
    private int lastIdSkupiny = 0;
    private int lastIdUzivatele = 0;

    public DatabaseHelper(String login, String pswd) throws SQLException {
        myInit(login, pswd);
    }

    public static void myInit(String login, String pswd) throws SQLException {
        OracleConnector.setUpConnection("fei-sql1.upceucebny.cz", 1521, "IDAS", login, pswd);
    }

    //-------- DDL --------
    public void insertScript() throws SQLException {
        Connection conn = OracleConnector.getConnection();
        Statement stmt = conn.createStatement();
        stmt.addBatch(dfg);
        stmt.addBatch(dfgfew);

        //STUDIJNI_PLANY
        stmt.addBatch(dfgfsew);
        stmt.addBatch(dfgef);
        stmt.addBatch(sdf);
        stmt.addBatch(sdfsdf);

        //UZIVATELE
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('1','Lubomír','Andrle',null,'KIT','fei','a','870260213','lubomir.andrle@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('2','Eduard','Babulak',null,'KIT','fei','a','402961371','eduard.babulak@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('3','Michael','Bažant',null,'KST','fei','a','729027757','michael.bazant@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('4','Miroslav','Benedikovič',null,'KST','fei','a','311255906','bene@fria.fri.utc.sk')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('5','Ladislav','Beran',null,'KE','fei','a','143973755','st25775@student.upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('6','Pavel','Bezoušek',null,'KE','fei','a','769060778','pavel.bezousek@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('7','Vojtěch','Biberle',null,'KIT','fei','a','700614818','vojtech.biberle@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('8','Jiří','Blecha',null,'KE','fei','a','183690535','jiri.blecha@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('9','Agáta','Bodnárová',null,'KE','fei','a','278012381','agata.bodnarova@gmail.com')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('10','Jiří','Boch',null,'KE','fei','a','838551238','jiri.boch@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('11','Monika','Borkovcová',null,'KIT','fei','a','932800447','monika.borkovcova@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('12','Filip','Borovec',null,'KST','fei','a','894326169','filip.borovec@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('13','Tereza','Bosáková',null,'KRP','fei','a','168943718','tereza.bosakova@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('14','Tomáš','Brandejský',null,'KST','fei','a','668042617','Tomas.Brandejsky@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('15','Vladimír','Brázda',null,'KE','fei','a','287714554','vladimir.brazda@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('16','Pavel','Brom',null,'KST','fei','a','889134176','pavel.brom@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('17','Josef','Brožek',null,'KIT','fei','a','847435588','josef.brozek@student.upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('18','Bohumil','Brtník',null,'KE','fei','a','675683965','bohumil.brtnik@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('19','David','Budáč',null,'KIT','fei','a','488924788','david.budac@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('20','Lukáš','Cír',null,'KIT','fei','a','635563899','lukas.cir@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('21','Tomáš','Holý','3',null,null,'a','776570423','st49636@student.upce.cz')");


        //PREDMETY
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('1','Odborná praxe - exkurze I','IOPEX','Odborná praxe - exkurze I')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('2','Architektura počítačů','IUDP','Architektura počítačů')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('3','Základy algoritmizace','IZALG','Základy algoritmizace')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('4','Základy ekonomie','IZEK','Základy ekonomie')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('5','Lineární algebra','ILALG','Lineární algebra')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('6','Matematika 1','IMAT1','Matematika 1')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('7','Návrh a modelování SW systémů','IMSWS','Návrh a modelování SW systémů')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('8','Základy slaboproudé elektrotechniky','IZSE','Základy slaboproudé elektrotechniky')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('9','Řízená prázdninová praxe I','IRPP','Řízená prázdninová praxe I')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('10','Matematika 2','IMAT2','Matematika 2')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('11','Teorie grafů','ITEGR','Teorie grafů')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('12','Praktikum z algoritmizace a programování','IPALP','Praktikum z algoritmizace a programování')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('13','Základy programování','IZAPR','Základy programování')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('14','Sociální psychologie','ISPSY','Sociální psychologie')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('15','Informační zdroje a jejich využívání','IIFZ','Informační zdroje a jejich využívání')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('16','Základy informačních technologií','IZIT','Základy informačních technologií')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('17','Právo','IPRAV','Právo')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('18','Základy managementu','IZKMA','Základy managementu')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('19','Textové editory a tab. procesory','ITETP','Textové editory a tab. procesory')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('20','Roboti','IROB','Roboti')");


        //ROLE
        stmt.addBatch(gdsag);
        stmt.addBatch(wefwe);
        stmt.addBatch(gtrg);

        //KATEGORIE
        stmt.addBatch(wefwes);
        stmt.addBatch(hrtw);
        stmt.addBatch(gtr);
        stmt.addBatch(wefg);
        stmt.addBatch(wefgh);
        stmt.addBatch(gwefwe);

        //PREDMETY_PLANU
        stmt.addBatch(qwd);
        stmt.addBatch(wefweff);
        stmt.addBatch(qwdd);
        stmt.addBatch(gre);
        stmt.addBatch(qwds);

        //NAZVY_SKUPIN
        stmt.addBatch(uik);
        stmt.addBatch(uiklui);
        stmt.addBatch(loi);
        stmt.addBatch(zuj);
        stmt.addBatch(tzj);

        //KAMARADI_UZIVATELE
        stmt.addBatch(iol);
        stmt.addBatch(oil);

        //SKUPINY
        stmt.addBatch(ioljtz);
        stmt.addBatch(iolz);
        stmt.addBatch(lio);
        stmt.addBatch(iolp);
        stmt.addBatch(wefgpo);

        //ZPRAVY
        stmt.addBatch(zjt);
        stmt.addBatch(optr);
        stmt.addBatch(erh);
        stmt.addBatch(ewfwg);
        stmt.addBatch(wegweg);
        stmt.addBatch(egwgweg);

        //USER_TABLES
        stmt.addBatch(ewgwe);
        stmt.addBatch(wef);
        stmt.addBatch(qwdwef);
        stmt.addBatch(weff);
        stmt.addBatch(gwe);
        stmt.addBatch(gewg);
        stmt.addBatch(weg);
        stmt.addBatch(wefwweff);
        stmt.addBatch(gwegw);
        stmt.addBatch(gweg);
        stmt.addBatch(weeef);
        stmt.addBatch(wdqq);
        stmt.executeBatch();
    }

    public void createScript() throws SQLException {
        Connection conn = OracleConnector.getConnection();
        Statement stmt = conn.createStatement();
        stmt.addBatch(CREATE_TRP);
        stmt.addBatch(ALTERT_TRP);
        stmt.addBatch(ALTERT_TRP_DATUM_NAR);
        stmt.addBatch(ALTERT_TRP_NAROZEN);
        stmt.addBatch(ALTERT_TRP_OBRAZEK);
        stmt.addBatch(CREATE_VLAST);
        stmt.addBatch(ALTERT_VLAST);
        stmt.addBatch(CREATE_POZ);
        stmt.addBatch(ALTERT_FK_POZ_TRP);
        stmt.addBatch(ALTERT_FK_POZ_VLAST);

        stmt.addBatch(CREATE_SEQ_TRP_ID);
        stmt.addBatch(CREATE_SEQ_VLAST_ID);

        stmt.addBatch(a);
        stmt.addBatch(b);
        stmt.addBatch(c);
        stmt.addBatch(d);
        stmt.addBatch(e);
        stmt.addBatch(f);
        stmt.addBatch(g);
        stmt.addBatch(h);
        stmt.addBatch(j);
        stmt.addBatch(hj);
        stmt.addBatch(ch);
        stmt.addBatch(i);
        stmt.addBatch(sadf);
        stmt.addBatch(asfg);
        stmt.addBatch(asdfgsd);
        stmt.addBatch(asad);
        stmt.addBatch(ht);
        stmt.addBatch(gew);
        stmt.addBatch(sfasdf);
        stmt.addBatch(htrh);

        //---------------------------------FOREIGN KEYS-------------------------
        stmt.addBatch(rdgerg);
        stmt.addBatch(rgereg);
        stmt.addBatch(fwefg);
        stmt.addBatch(wefwef);
        stmt.addBatch(wefewf);
        stmt.addBatch(edwdwe);
        stmt.addBatch(dsfa);
        stmt.addBatch(asdfsa);
        stmt.addBatch(asdfs);
        stmt.addBatch(asdf);
        stmt.addBatch(fhds);
        stmt.addBatch(sfse);
        stmt.addBatch(wedw);
        stmt.addBatch(wedwe);
        stmt.addBatch(wedwef);
        stmt.addBatch(hzthz);

        //--------------------------------SEQUENCES-----------------------------
        stmt.addBatch(sefqw);
        stmt.addBatch(wedwed);
        stmt.addBatch(erger);
        stmt.addBatch(efwefw);
        stmt.addBatch(jkumz);
        stmt.addBatch(tzrjtz);
        stmt.addBatch(fewfwe);
        stmt.addBatch(ewfewfgweg);
        stmt.addBatch(hztjkuk);
        stmt.addBatch(defwwedqw);

        //-----------------------------VIEWS------------------------------------
        stmt.addBatch(efgrh);
        stmt.addBatch(htzjzu);
        stmt.addBatch(edwfgwg);
        stmt.addBatch(htzujk);
        stmt.addBatch(juztjkziu);
        stmt.addBatch(wdwedf);
        stmt.addBatch(wdwedfD);
        //----------------------------TRIGGERS----------------------------------
        
stmt.addBatch("CREATE OR REPLACE TRIGGER kategorie_id_trg BEFORE INSERT ON kategorie FOR EACH ROW WHEN ( new.id IS NULL ) BEGIN  :new.id := kategorie_id_seq.nextval; END;");
//stmt.addBatch("/");


stmt.addBatch("CREATE OR REPLACE TRIGGER komentare_id_trg BEFORE  INSERT ON komentare  FOR EACH ROW    WHEN ( new.id IS NULL ) BEGIN     :new.id := komentare_id_seq.nextval; END;");
//stmt.addBatch("/");


stmt.addBatch("CREATE OR REPLACE TRIGGER lajky_id_trg BEFORE   INSERT ON lajky     FOR EACH ROW    WHEN ( new.id IS NULL ) BEGIN     :new.id := lajky_id_seq.nextval; END;");
//stmt.addBatch("/");


stmt.addBatch("CREATE OR REPLACE TRIGGER nazvy_skupin_id_trg BEFORE  INSERT ON nazvy_skupin    FOR EACH ROW  WHEN ( new.id IS NULL ) BEGIN     :new.id := nazvy_skupin_id_seq.nextval; END;");
//stmt.addBatch("/");

stmt.addBatch("CREATE OR REPLACE TRIGGER predmety_id_trg BEFORE   INSERT ON predmety   FOR EACH ROW  WHEN ( new.id IS NULL ) BEGIN    :new.id := predmety_id_seq.nextval; END;");
//stmt.addBatch("/");


stmt.addBatch("CREATE OR REPLACE TRIGGER role_id_trg BEFORE  INSERT ON role  FOR EACH ROW  WHEN ( new.id IS NULL ) BEGIN     :new.id := role_id_seq.nextval; END;");
//stmt.addBatch("/");



stmt.addBatch("CREATE OR REPLACE TRIGGER studijni_obory_id_trg BEFORE  INSERT ON studijni_obory   FOR EACH ROW    WHEN ( new.id IS NULL ) BEGIN    :new.id := studijni_obory_id_seq.nextval; END;");
//stmt.addBatch("/");

stmt.addBatch("CREATE OR REPLACE TRIGGER studijni_plany_id_trg BEFORE    INSERT ON studijni_plany    FOR EACH ROW   WHEN ( new.id IS NULL ) BEGIN     :new.id := studijni_plany_id_seq.nextval; END;");
//stmt.addBatch("/");


stmt.addBatch("CREATE OR REPLACE TRIGGER uzivatele_id_trg BEFORE    INSERT ON uzivatele     FOR EACH ROW   WHEN ( new.id IS NULL ) BEGIN     :new.id := uzivatele_id_seq.nextval; END;");
//stmt.addBatch("/");



stmt.addBatch("CREATE OR REPLACE TRIGGER zpravy_id_trg BEFORE   INSERT ON zpravy    FOR EACH ROW   WHEN ( new.id IS NULL ) BEGIN   :new.id := zpravy_id_seq.nextval; END;");
//stmt.addBatch("/");



        //--------------------------INSERTS-------------------------------------
        stmt.executeBatch();
        stmt.addBatch(dfg);
        stmt.addBatch(dfgfew);

        //STUDIJNI_PLANY
        stmt.addBatch(dfgfsew);
        stmt.addBatch(dfgef);
        stmt.addBatch(sdf);
        stmt.addBatch(sdfsdf);

stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('1','Lubomír','Andrle',null,'KIT','fei','a','870260213','lubomir.andrle@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('2','Eduard','Babulak',null,'KIT','fei','a','402961371','eduard.babulak@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('3','Michael','Bažant',null,'KST','fei','a','729027757','michael.bazant@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('4','Miroslav','Benedikovič',null,'KST','fei','a','311255906','bene@fria.fri.utc.sk')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('5','Ladislav','Beran',null,'KE','fei','a','143973755','st25775@student.upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('6','Pavel','Bezoušek',null,'KE','fei','a','769060778','pavel.bezousek@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('7','Vojtěch','Biberle',null,'KIT','fei','a','700614818','vojtech.biberle@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('8','Jiří','Blecha',null,'KE','fei','a','183690535','jiri.blecha@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('9','Agáta','Bodnárová',null,'KE','fei','a','278012381','agata.bodnarova@gmail.com')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('10','Jiří','Boch',null,'KE','fei','a','838551238','jiri.boch@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('11','Monika','Borkovcová',null,'KIT','fei','a','932800447','monika.borkovcova@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('12','Filip','Borovec',null,'KST','fei','a','894326169','filip.borovec@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('13','Tereza','Bosáková',null,'KRP','fei','a','168943718','tereza.bosakova@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('14','Tomáš','Brandejský',null,'KST','fei','a','668042617','Tomas.Brandejsky@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('15','Vladimír','Brázda',null,'KE','fei','a','287714554','vladimir.brazda@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('16','Pavel','Brom',null,'KST','fei','a','889134176','pavel.brom@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('17','Josef','Brožek',null,'KIT','fei','a','847435588','josef.brozek@student.upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('18','Bohumil','Brtník',null,'KE','fei','a','675683965','bohumil.brtnik@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('19','David','Budáč',null,'KIT','fei','a','488924788','david.budac@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('20','Lukáš','Cír',null,'KIT','fei','a','635563899','lukas.cir@upce.cz')");
stmt.addBatch("Insert into UZIVATELE (ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) values ('21','Tomáš','Holý','3',null,null,'a','776570423','st49636@student.upce.cz')");


        //PREDMETY
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('1','Odborná praxe - exkurze I','IOPEX','Odborná praxe - exkurze I')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('2','Architektura počítačů','IUDP','Architektura počítačů')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('3','Základy algoritmizace','IZALG','Základy algoritmizace')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('4','Základy ekonomie','IZEK','Základy ekonomie')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('5','Lineární algebra','ILALG','Lineární algebra')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('6','Matematika 1','IMAT1','Matematika 1')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('7','Návrh a modelování SW systémů','IMSWS','Návrh a modelování SW systémů')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('8','Základy slaboproudé elektrotechniky','IZSE','Základy slaboproudé elektrotechniky')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('9','Řízená prázdninová praxe I','IRPP','Řízená prázdninová praxe I')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('10','Matematika 2','IMAT2','Matematika 2')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('11','Teorie grafů','ITEGR','Teorie grafů')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('12','Praktikum z algoritmizace a programování','IPALP','Praktikum z algoritmizace a programování')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('13','Základy programování','IZAPR','Základy programování')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('14','Sociální psychologie','ISPSY','Sociální psychologie')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('15','Informační zdroje a jejich využívání','IIFZ','Informační zdroje a jejich využívání')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('16','Základy informačních technologií','IZIT','Základy informačních technologií')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('17','Právo','IPRAV','Právo')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('18','Základy managementu','IZKMA','Základy managementu')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('19','Textové editory a tab. procesory','ITETP','Textové editory a tab. procesory')");
stmt.addBatch("Insert into ST49636.PREDMETY (ID,NAZEV,ZKRATKA,POPIS) values ('20','Roboti','IROB','Roboti')");

        //ROLE
        stmt.addBatch(gdsag);
        stmt.addBatch(wefwe);
        stmt.addBatch(gtrg);

        //KATEGORIE
        stmt.addBatch(wefwes);
        stmt.addBatch(hrtw);
        stmt.addBatch(gtr);
        stmt.addBatch(wefg);
        stmt.addBatch(wefgh);
        stmt.addBatch(gwefwe);

        //PREDMETY_PLANU
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('1','1')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('2','1')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('3','1')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('4','1')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('5','1')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('6','1')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('7','1')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('8','1')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('9','1')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('10','1')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('11','2')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('12','2')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('13','2')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('14','2')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('15','2')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('16','2')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('17','2')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('18','2')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('19','2')");
stmt.addBatch("Insert into ST49636.PREDMETY_PLANU (ID_PREDMETY,ID_STUDIJNI_PLANY) values ('20','2')");


        //NAZVY_SKUPIN
        stmt.addBatch(uik);
        stmt.addBatch(uiklui);
        stmt.addBatch(loi);
        stmt.addBatch(zuj);
        stmt.addBatch(tzj);

        //KAMARADI_UZIVATELE
        stmt.addBatch(iol);
        stmt.addBatch(oil);

        //SKUPINY
        stmt.addBatch(ioljtz);
        stmt.addBatch(iolz);
        stmt.addBatch(lio);
        stmt.addBatch(iolp);
        stmt.addBatch(wefgpo);

        //ZPRAVY
        stmt.addBatch(zjt);
        stmt.addBatch(optr);
        stmt.addBatch(erh);
        stmt.addBatch(ewfwg);
        stmt.addBatch(wegweg);
        stmt.addBatch(egwgweg);

        //USER_TABLES
        stmt.addBatch(ewgwe);
        stmt.addBatch(wef);
        stmt.addBatch(qwdwef);
        stmt.addBatch(weff);
        stmt.addBatch(gwe);
        stmt.addBatch(gewg);
        stmt.addBatch(weg);
        stmt.addBatch(wefwweff);
        stmt.addBatch(gwegw);
        stmt.addBatch(gweg);
        stmt.addBatch(weeef);
        stmt.addBatch(wdqq);
        stmt.executeBatch();
        conn.commit();
    }

    //Stejne, nakonec
    public void destroyScript() throws SQLException {
        Connection conn = OracleConnector.getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(DROP_POZ);
        stmt.execute(DROP_TRP);
        stmt.execute(DROP_VLAST);

        stmt.execute(DROP_SEQ_TRP_ID);
        stmt.execute(DROP_SEQ_VLAST_ID);
        stmt.addBatch(DROP_SEQ_VLAST_IDD);
        stmt.addBatch(ouihuioh);
        stmt.addBatch(uzguzgz);
        stmt.addBatch(ztfztf);
        stmt.addBatch(oijoj);
        stmt.addBatch(fztfzt);
        stmt.addBatch(oijoijoi);

        stmt.addBatch(tzfztfztd);
        stmt.addBatch(uzuu);
        stmt.addBatch(izugtzuf);
        stmt.addBatch(fztuzui);
        stmt.addBatch(ruihoi);
        stmt.addBatch(ihuhh);
        stmt.addBatch(rztfzf);
        stmt.addBatch(oij);
        stmt.addBatch(zfdf);
        stmt.addBatch(oijoij);
        stmt.addBatch(guzf);
        stmt.addBatch(tfzfzt);
        stmt.addBatch(jioij);
        stmt.addBatch(uzguf);

        stmt.addBatch(rhtfrfj);
        stmt.addBatch(rtzjtzu);
        stmt.addBatch(wefedw);
        stmt.addBatch(kloiul);
        stmt.addBatch(ewfdf);
        stmt.addBatch(jzukz);
        stmt.addBatch(kiukui);
        stmt.addBatch(hjtzujk);
        stmt.addBatch(hjzutku);

    }

    public ArrayList<String> prihlaseniUzivatele(String login, String password) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        ArrayList<String> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT EMAIL, HESLO FROM UZIVATELE WHERE EMAIL = ? AND HESLO = ?");
        stmt.setString(1, login);
        stmt.setString(2, password);
        ResultSet rset = stmt.executeQuery();
        if (rset.next()) {
            list.add(rset.getString("EMAIL"));
            list.add(rset.getString("HESLO"));
        } else {
            return null;
        }
        return list;
    }

    public void pridejKomentar(Skupina skupina, Uzivatel uzivatel, int id, String obsah) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO KOMENTARE(ID_KOMENTARE,OBSAH,CAS,"
                + "ID_PREDMETY, ID_UZIVATELE) VALUES(?,?,?,?,?)");
        stmt.setString(1, null);
        stmt.setString(2, obsah);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        stmt.setTimestamp(3, timestamp);
        stmt.setInt(4, id);
        stmt.setInt(5, uzivatel.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    public void pridejLajk(Komentar k, Uzivatel u, int id_kategorie, int id_komentare) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO LAJKY(ID_UZIVATELE, ID_KATEGORIE, ID_KOMENTARE)"
                + " VALUES(?,?,?)");
        stmt.setInt(1, u.getId());
        stmt.setInt(2, id_kategorie);
        stmt.setInt(3, id_komentare);
        stmt.executeUpdate();
        conn.commit();
    }

    public void zapisStudijniObor(Uzivatel u, StudijniObor o) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO SKUPINY (ID_UZIVATELE, ID_ROLE, ID_");
        stmt.setInt(1, u.getId());
        //stmt.setInt(2, id_kategorie);
        // stmt.setInt(3, id_komentare);
        stmt.executeUpdate();
        conn.commit();
    }

    public int getLajkyNaKomentari(Komentar k) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        int i = 0;
        ArrayList<String> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM KOMENTARE WHERE email = ?");
        stmt.setString(1, k.getUzivatel());
        ResultSet rset = stmt.executeQuery();
        if (rset.next()) {
            i = rset.getInt("ID_UZIVATELE");
        }
        return i;
    }

    public int getIdPlanuZPredmetu(int id_predmetu) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        int i = 0;
        ArrayList<String> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT ID_studijni_plany FROM predmety_planu WHERE ID_predmety = ?");
        stmt.setInt(1, id_predmetu);
        ResultSet rset = stmt.executeQuery();
        if (rset.next()) {
            i = rset.getInt("ID_studijni_plany");
        }
        return i;
    }

        public int getIdNazvuSkupiny(String nazev) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        int i = 0;
        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM NAZVY_SKUPIN WHERE NAZEV = ?");
        stmt.setString(1, nazev);
        ResultSet rset = stmt.executeQuery();
        if (rset.next()) {
            i = rset.getInt("id");
        }
        return i;
    }
    public int getLastIdZpravy() throws SQLException {
        Connection conn = OracleConnector.getConnection();
        int i = 0;
        PreparedStatement stmt = conn.prepareStatement("SELECT max(id) as max FROM zpravy");
        ResultSet rset = stmt.executeQuery();
        if (rset.next()) {
            i = rset.getInt("max");
        }
        return i;
    }

    public void pridejZpravu(Uzivatel u, Uzivatel k, String zprava) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO ZPRAVY(CAS, OBSAH, KAM_UZIV_ID_UZIVATELE, KAM_UZIV_ID_KAMARADA, ID)"
                + " VALUES(?,?,?,?,?)");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        stmt.setTimestamp(1, timestamp);
        stmt.setString(2, zprava);
        stmt.setInt(3, u.getId());
        stmt.setInt(4, k.getId());
        int i = getLastIdZpravy() + 1;
        stmt.setInt(5, i);
        stmt.executeUpdate();
        conn.commit();
    }

    public void pridejOdpovedKomentar(Skupina skupina, Uzivatel uzivatel, int id, String obsah, int id_komentare) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        int aa = skupina.getId_predmety();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO KOMENTARE(ID_KOMENTARE,OBSAH,CAS,"
                + "ID_PREDMETY, ID_UZIVATELE) VALUES(?,?,?,?,?)");
        stmt.setInt(1, id_komentare);
        stmt.setString(2, obsah);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        stmt.setTimestamp(3, timestamp);
        stmt.setInt(4, id);
        stmt.setInt(5, uzivatel.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    public Uzivatel vytvorPrihlasenehoUzivatele(String email) throws SQLException {
        Uzivatel u = null;
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("select ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL,PRACOVISTE from uzivatele where email = ?");
        stmt.setString(1, email);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            u = new Uzivatel(rset.getString("JMENO"), rset.getString("PRIJMENI"), rset.getInt("ID"), rset.getString("ROK_STUDIA"), rset.getString("FAKULTA"), rset.getString("HESLO"), rset.getString("TELEFON"), rset.getString("EMAIL"), rset.getString("PRACOVISTE"));

        }
        return u;
    }

    public ArrayList<Skupina> getSkupinyAll(Uzivatel u) throws SQLException {
        ArrayList<Skupina> sk = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT ID_NAZVY_SKUPIN, NAZEV, ROLE_NAZEV, EMAIL, PREDMET_NAZEV, STUDIJNI_OBOR_NAZEV, STUDIJNI_PLAN_NAZEV FROM VIEW_SKUPINY WHERE ID_UZIVATELE = ?");
        stmt.setInt(1, u.getId());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            Skupina s = new Skupina(rset.getInt("ID_NAZVY_SKUPIN"), rset.getString("NAZEV"), rset.getString("PREDMET_NAZEV"), rset.getString("STUDIJNI_OBOR_NAZEV"), rset.getString("ROLE_NAZEV"), rset.getString("STUDIJNI_PLAN_NAZEV"));
            sk.add(s);
        }
        return sk;
    }

    public ArrayList<String> getSkupinyBezPredmetu() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT ID, nazev from view_nazvy WHERE ID_predmety is null");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(rset.getString("NAZEV"));
        }
        return list;
    }

    public ArrayList<Komentar> getKomentareSkupiny(int id_uzivatele, int id_predmetu) throws SQLException {
        ArrayList<Komentar> k = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("select * from view_komentare where id_predmety = ?");
        stmt.setInt(1, id_predmetu);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            Komentar kom = new Komentar(rset.getInt("ID"), rset.getString("OBSAH"), rset.getString("CAS"), rset.getInt("ID_KOMENTARE"), rset.getString("EMAIL"));
            k.add(kom);
        }

        return k;
    }

    public ArrayList<String> getPredmety() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT NAZEV FROM PREDMETY");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(rset.getString("NAZEV"));
        }

        return list;
    }

    public ArrayList<String> getPlany() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT nazev FROM studijni_plany");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(rset.getString("NAZEV"));
        }

        return list;
    }

    public ArrayList<String> getStudijniObory() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT NAZEV FROM STUDIJNI_OBORY");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(rset.getString("NAZEV"));
        }

        return list;
    }

    public ArrayList<String> getStudijniPlany() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT NAZEV FROM STUDIJNI_PLANY");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(rset.getString("NAZEV"));
        }

        return list;
    }

    public ArrayList<String> getRole() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT NAZEV FROM ROLE");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(rset.getString("NAZEV"));
        }

        return list;
    }

    public int getRoleId(String nazev) throws SQLException {
        int i = 0;
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT ID FROM ROLE WHERE NAZEV = ?");
        stmt.setString(1, nazev);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            i = rset.getInt("ID");
        }
        return i;
    }

    public int getPredmetId(String nazev) throws SQLException {
        int i = 0;
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT ID FROM PREDMETY WHERE NAZEV = ?");
        stmt.setString(1, nazev);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            i = rset.getInt("ID");
        }
        return i;
    }

    public int getSkupinaId(String nazev) throws SQLException {
        int i = 0;
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT ID FROM nazvy_skupin WHERE NAZEV = ?");
        stmt.setString(1, nazev);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            i = rset.getInt("ID");
        }
        return i;
    }

    public int getStudijniPlanId(String nazev) throws SQLException {
        int i = 0;
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT ID FROM STUDIJNI_PLANY WHERE NAZEV = ?");
        stmt.setString(1, nazev);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            i = rset.getInt("ID");
        }
        return i;
    }

    public int getKomentarId(String nazev) throws SQLException {
        int i = 0;
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT ID_KOMENTARE FROM komentare WHERE ID = ?");
        stmt.setString(1, nazev);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            i = rset.getInt("ID_KOMENTARE");
        }
        return i;
    }

    public int getKategorieId(String nazev) throws SQLException {
        int i = 0;
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT ID FROM kategorie WHERE NAZEV = ?");
        stmt.setString(1, nazev);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            i = rset.getInt("ID");
        }
        return i;
    }
        public int getPocetPredmetuStPlanu(int id_planu) throws SQLException {
        int i = 0;
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT ID_predmety FROM PREDMETY_PLANU WHERE ID_STUDIJNI_PLANY = ?");
        stmt.setInt(1, id_planu);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            i = rset.getInt("ID_predmety");
        }
        return i;
    }
 public void zapisPredmetPlanu(Uzivatel u) throws SQLException {
     
 }
    public int getIdStudijnihoPlanuUzivatele(Uzivatel u) throws SQLException {
        int i = 0;
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT SP.ID FROM STUDIJNI_PLANY SP LEFT JOIN PREDMETY_PLANU PP ON sp.iD = pp.id_studijni_plany LEFT JOIN SKUPINY S ON pp.id_studijni_plany = s.id_studijni_plany LEFT JOIN UZIVATELE U ON s.id_uzivatele = u.id WHERE ID_UZIVATELE = ?");
        stmt.setInt(1, u.getId());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            i++;
        }
        return i;
    }

    public void upravaUzivatele(Uzivatel u) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE UZIVATELE SET JMENO = ?,PRIJMENI = ?,ROK_STUDIA = ?,PRACOVISTE = ?,FAKULTA = ?,HESLO = ?,TELEFON = ?,EMAIL = ? WHERE ID = ?");
        stmt.setString(1, u.getJmeno());
        stmt.setString(2, u.getPrijmeni());
        stmt.setString(3, u.getRokStudia());
        stmt.setString(4, u.getPracoviste());
        stmt.setString(5, u.getFakulta());
        stmt.setString(6, u.getHeslo());
        stmt.setString(7, u.getTelefon());
        stmt.setString(8, u.getEmail());
        stmt.setInt(9, u.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    public void upravSkupinu(Skupina s) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE NAZVY_SKUPIN SET NAZEV = ? WHERE ID = ?");
        stmt.setString(1, s.getNazev());
        stmt.setInt(2, s.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    public void pridejSkupinu(String s) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO NAZVY_SKUPIN (NAZEV, ID) VALUES(?,?)");
        stmt.setString(1, s);
        stmt.setInt(2, ++lastIdSkupiny);
        stmt.executeUpdate();
        conn.commit();
    }

    public void priradSkupinuPredmetu(int id_predmetu, int id_uzivatele, int id_studijni_plany, int role, int nazev_skupiny) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO skupiny (ID_UZIVATELE, ID_ROLE, ID_PREDMETY, ID_STUDIJNI_PLANY, ID_NAZVY_SKUPIN) "
                + "VALUES(?,?,?,?,?)");
        stmt.setInt(1, id_uzivatele);
        if (role == 0) {
            stmt.setInt(2, 2);
        } else {
            stmt.setInt(2, role);
        }
        stmt.setInt(3, id_predmetu);
        stmt.setInt(4, id_studijni_plany);
        stmt.setInt(5, nazev_skupiny);
        stmt.executeUpdate();
        conn.commit();
    }

    public void smazSkupinu(Skupina s) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("delete from SKUPINY WHERE ID_PREDMETY = ? and ID_NAZVY_SKUPIN = ?");
        stmt.setInt(1, s.getId_predmety());
        stmt.setInt(2, s.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    public void smazLajk(Lajk s) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM lajky WHERE ID = ?");
        stmt.setInt(1, s.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    public void smazKomentarNaSkupine(int id_predmet) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt1 = conn.prepareStatement("SELECT ID_PREDMETY FROM KOMENTARE WHERE ID_PREDMETY = ?");
        stmt1.setInt(1, id_predmet);
        ResultSet rset = stmt1.executeQuery();
        if (rset.next()) {
            int id_mazaneho_komentare = rset.getInt("ID_PREDMETY");
            smazLajkyNaKomentarichSkupiny(id_mazaneho_komentare);
        }
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM komentare WHERE ID_PREDMETY = ?");
        stmt.setInt(1, id_predmet);
        stmt.executeUpdate();
        conn.commit();
    }

    public void smazLajkyNaKomentarichSkupiny(int id_komentare) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM LAJKY WHERE ID_KOMENTARE = ?");
        stmt.setInt(1, id_komentare);
        stmt.executeUpdate();
        conn.commit();
    }

    public void upravKomentar(Komentar k) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE komentare SET OBSAH = ?,CAS = ?,ID_KOMENTARE= ?,ID_PREDMETY = ?,ID_UZIVATELE = ? WHERE ID = ?");
        stmt.setString(1, k.getObsah());
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        stmt.setTimestamp(2, timestamp);
        if (k.getId_komentare() == 0) {
            stmt.setString(3, null);
        } else {
            stmt.setInt(3, k.getId_komentare());
        }
        stmt.setInt(4, k.getId_predmety());
        stmt.setInt(5, k.getId_uzivatele());
        stmt.setInt(6, k.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    public void smazKomentar(Komentar k) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM komentare WHERE ID = ?");
        stmt.setInt(1, k.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    public void smazLajkNaKomentari(int id_komentare) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt1 = conn.prepareStatement("SELECT ID FROM KOMENTARE WHERE ID_KOMENTARE = ?");
        stmt1.setInt(1, id_komentare);
        ResultSet rset = stmt1.executeQuery();
        if (rset.next()) {
            int id_mazaneho_lajku = rset.getInt("ID");
            smazLajk(id_mazaneho_lajku);
        }
    }

    public void smazLajk(int id_lajku) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM LAJKY WHERE ID = ?");
        stmt.setInt(1, id_lajku);
        stmt.executeUpdate();
        conn.commit();
    }

    public void odeberKamarada(Uzivatel k, Uzivatel uz) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE KAMARADI_UZIVATELE SET AKTIVNI = 'N' WHERE ID_UZIVATELE = ? AND ID_KAMARADA = ?");
        stmt.setInt(1, uz.getId());
        stmt.setInt(2, k.getId());
        stmt.executeUpdate();
        conn.commit();
    }

    public ArrayList<Lajk> getLajkyNaKomentari(int id_komentare) throws SQLException {
        ArrayList<Lajk> l = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("select id, NAZEV_kategorie, id_komentare, EMAIL from view_lajky WHERE id_komentare = ?");
        stmt.setInt(1, id_komentare);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            Lajk lajk = new Lajk(rset.getInt("ID"), rset.getString("NAZEV_KATEGORIE"), rset.getString("EMAIL"), rset.getInt("ID_KOMENTARE"));
            l.add(lajk);
        }
        return l;
    }

    public ArrayList<Uzivatel> getKamaradyUzivatele(Uzivatel uzivatel) throws SQLException {
        ArrayList<Uzivatel> u = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("select ID_KAMARADA, KAMARAD_JMENO, KAMARAD_PRIJMENI from view_kamaradi WHERE ID_UZIVATELE = ? AND AKTIVNI = 'A'");
        stmt.setInt(1, uzivatel.getId());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            Uzivatel kamarad = new Uzivatel(rset.getString("KAMARAD_JMENO"), rset.getString("KAMARAD_PRIJMENI"), rset.getInt("ID_KAMARADA"));
            u.add(kamarad);
        }
        return u;
    }

    public ArrayList<Zprava> getZpravySUzivatelem(Uzivatel uzivatel, Uzivatel kamarad) throws SQLException {
        ArrayList<Zprava> u = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("select distinct ID, CAS, OBSAH, KAMARAD_JMENO, KAMARAD_PRIJMENI, UZIVATEL_JMENO, UZIVATEL_PRIJMENI from VIEW_ZPRAVY WHERE ID_UZIVATELE = ? AND ID_KAMARADA = ? OR ID_UZIVATELE = ? AND ID_KAMARADA = ? ORDER BY CAS");
        stmt.setInt(1, uzivatel.getId());
        stmt.setInt(2, kamarad.getId());
        stmt.setInt(3, kamarad.getId());
        stmt.setInt(4, uzivatel.getId());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            Zprava zprava = new Zprava(rset.getInt("ID"), rset.getString("CAS"), rset.getString("OBSAH"), rset.getString("UZIVATEL_JMENO"), rset.getString("UZIVATEL_PRIJMENI"), rset.getString("KAMARAD_JMENO"), rset.getString("KAMARAD_PRIJMENI"));
            if (zprava.getId() > lastIdZpravy) {
                lastIdZpravy = zprava.getId();
            }
            u.add(zprava);

        }

        return u;
    }

    public ArrayList<String> getKategorie() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT NAZEV FROM KATEGORIE ORDER BY NAZEV");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(rset.getString("NAZEV"));
        }
        return list;
    }

    public ArrayList<String> getPredmetyBezSkupin() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT NAZEV_PREDMETU FROM VIEW_PREDMETY WHERE NAZEV_SKUPINY IS NULL");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(rset.getString("NAZEV_PREDMETU"));
        }
        return list;
    }

    public ArrayList<String> getKamarady(Uzivatel u) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT KAMARAD_JMENO, KAMARAD_PRIJMENI FROM VIEW_KAMARADI WHERE ID_UZIVATELE = ? and aktivni = 'A' ORDER BY KAMARAD_PRIJMENI");
        stmt.setInt(1, u.getId());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            list.add(rset.getString("KAMARAD_JMENO") + " " + rset.getString("KAMARAD_PRIJMENI"));
        }
        return list;
    }

    public void upravLajk(int id, Lajk l) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE LAJKY SET ID_KATEGORIE = ? WHERE ID = ?");
        stmt.setInt(1, l.getId_kategorie());
        stmt.setInt(2, id);
        stmt.executeUpdate();
        conn.commit();
    }

    public ArrayList<Uzivatel> nactiVsechnyUzivatele(Uzivatel uz) throws SQLException {
        ArrayList<Uzivatel> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL FROM UZIVATELE where id != ?");
        stmt.setInt(1, uz.getId());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            Uzivatel u = new Uzivatel(rset.getString("JMENO"), rset.getString("PRIJMENI"), rset.getInt("ID"), rset.getString("ROK_STUDIA"), rset.getString("FAKULTA"), rset.getString("HESLO"), rset.getString("TELEFON"), rset.getString("EMAIL"), rset.getString("PRACOVISTE"));
            if (u.getId() > lastIdUzivatele) {
                lastIdUzivatele = u.getId();
            }
            list.add(u);
        }

        return list;
    }
    public ArrayList<Uzivatel> nactiVsechnyUzivatele() throws SQLException {
        ArrayList<Uzivatel> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL FROM UZIVATELE");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            Uzivatel u = new Uzivatel(rset.getString("JMENO"), rset.getString("PRIJMENI"), rset.getInt("ID"), rset.getString("ROK_STUDIA"), rset.getString("FAKULTA"), rset.getString("HESLO"), rset.getString("TELEFON"), rset.getString("EMAIL"), rset.getString("PRACOVISTE"));
            if (u.getId() > lastIdUzivatele) {
                lastIdUzivatele = u.getId();
            }
            list.add(u);
        }

        return list;
    }
    public ArrayList<Predmet> nactiVsechnyPredmety() throws SQLException {
        ArrayList<Predmet> list = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();

        PreparedStatement stmt = conn.prepareStatement("SELECT distinct * FROM VIEW_PREDMETY");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            Predmet p = new Predmet(rset.getInt("ID"), rset.getInt("ID_STUDIJNIHO_OBORU"), rset.getInt("ID_STUDIJNIHO_PLANU"), rset.getString("NAZEV_PREDMETU"), rset.getString("ZKRATKA"), rset.getString("NAZEV_SKUPINY"), rset.getString("NAZEV_STUDIJNI_OBOR"), rset.getString("NAZEV_STUDIJNI_PLAN"));
            if (p.getId() > lastIdSkupiny) {
                lastIdSkupiny = p.getId();
            }
            list.add(p);
        }

        return list;
    }

    public void pridejKamarada(Uzivatel u, int id_kamarada) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt1 = conn.prepareStatement("SELECT AKTIVNI FROM KAMARADI_UZIVATELE WHERE ID_UZIVATELE = ? AND ID_KAMARADA = ?");
        stmt1.setInt(1, u.getId());
        stmt1.setInt(2, id_kamarada);
        ResultSet rset1 = stmt1.executeQuery();
        if (rset1.next()) {
            String a = rset1.getString("AKTIVNI");
            if (a.equals("A")) {
                return;
            } else {
                stmt1 = conn.prepareStatement("UPDATE KAMARADI_UZIVATELE SET AKTIVNI = 'A' WHERE ID_UZIVATELE = ? AND ID_KAMARADA = ?");
                stmt1.setInt(1, u.getId());
                stmt1.setInt(2, id_kamarada);
                stmt1.executeQuery();
                conn.commit();
            }
        } else {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO KAMARADI_UZIVATELE(ID_UZIVATELE, ID_KAMARADA,AKTIVNI) VALUES(?,?,'A')");
            stmt.setInt(1, u.getId());
            stmt.setInt(2, id_kamarada);
            stmt.executeUpdate();
            conn.commit();
        }
    }

    public ArrayList<Uzivatel> getMojeUdaje(Uzivatel uz) throws SQLException {
        ArrayList<Uzivatel> u = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM uzivatele where id = ?");
        stmt.setInt(1, uz.getId());
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            u.add(new Uzivatel(rset.getString("JMENO"), rset.getString("PRIJMENI"), rset.getInt("ID"), rset.getString("ROK_STUDIA"), rset.getString("FAKULTA"), rset.getString("HESLO"), rset.getString("TELEFON"), rset.getString("EMAIL"), rset.getString("PRACOVISTE")));
        }
        return u;
    }

    public boolean jeZapsanVPlanu(int id) throws SQLException {
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM studijni_plany where id = ?");
        return true;
    }

    public ArrayList<Uzivatel> hledejPodleJmena(String name) throws SQLException {
        ArrayList<Uzivatel> u = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM uzivatele where lower(jmeno) like lower(?) or lower(prijmeni) like lower(?)");
        stmt.setString(1, "%" + name + "%");
        stmt.setString(2, "%" + name + "%");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            u.add(new Uzivatel(rset.getString("JMENO"), rset.getString("PRIJMENI"), rset.getInt("ID"), rset.getString("ROK_STUDIA"), rset.getString("FAKULTA"), rset.getString("HESLO"), rset.getString("TELEFON"), rset.getString("EMAIL"), rset.getString("PRACOVISTE")));
        }
        return u;
    }

    public ArrayList<Predmet> hledejPredmet(String name) throws SQLException {
        ArrayList<Predmet> u = new ArrayList<>();
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM view_predmety where lower(nazev_predmetu) like lower(?) or lower(zkratka) like lower(?)");
        stmt.setString(1, "%" + name + "%");
        stmt.setString(2, "%" + name + "%");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            u.add(new Predmet(rset.getInt("ID"), rset.getString("NAZEV_PREDMETU"), rset.getString("ZKRATKA"), rset.getString("NAZEV_SKUPINY"), rset.getString("NAZEV_STUDIJNI_OBOR"), rset.getString("NAZEV_STUDIJNI_PLAN")));
        }
        return u;
    }
    //------------ HELP ----

    public boolean controllDB() throws SQLException {
        Connection conn = OracleConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT count(table_name) pocet FROM user_tables where table_name like '%'");
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            if (rset.getInt("pocet") == 12) {
                return true;
            }
        }
        return false;
    }

    public void registraceUzivatele(Uzivatel u) throws SQLException {
        Connection conn;
        conn = OracleConnector.getConnection();
        PreparedStatement stmt1 = conn.prepareStatement("SELECT email from uzivatele where email = ?");
        stmt1.setString(1, u.getEmail());
        ResultSet rset1 = stmt1.executeQuery();
        if (rset1.next()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Chyba");
            alert.setContentText("Uzivatel pod timto emailem jiz existuje");
            alert.showAndWait();
            return;
        } else {
            nactiVsechnyUzivatele();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO uzivatele(ID,JMENO,PRIJMENI,ROK_STUDIA,PRACOVISTE,FAKULTA,HESLO,TELEFON,EMAIL) "
                    + "values (?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, ++lastIdUzivatele);
            stmt.setString(2, u.getJmeno());
            stmt.setString(3, u.getPrijmeni());
            stmt.setString(4, u.getRokStudia());
            stmt.setString(5, u.getPracoviste());
            stmt.setString(6, u.getFakulta());
            stmt.setString(7, u.getHeslo());
            stmt.setString(8, u.getTelefon());
            stmt.setString(9, u.getEmail());
            stmt.executeUpdate();
            conn.commit();
        }
    }
}
