package crud;

import klase.Ocena;
import klase.Razred;
import klase.Roditelj;
import klase.Ucenik;
import java.sql.*;
import java.util.List;

public class DnevnikDAO {

    //Connection conn = DriverManager.getConnection();

    //ADMIN DEO

    //to-do
    //put dodaj ucenika, ucenik u pozivu, vraca boolean za izmenu
    public boolean dodajUcenika(Ucenik ucenik){
        System.out.println("INSERT INTO Ucenik " +
                "(redni_broj, razred, ime, ime_oca, prezime, datum_rodjenja, mesto_prebivalisa, broj_telefona, username) " +
                "VALUES (rb, raz, ime, imeOca, prz, dr, mp, bt, user)");
        return true;
    }

    //to-do
    //select prikazuje sve roditelje, u pozivu prazno, vraca listu roditelja
    //na frontu se bira roditelj
    public List<Roditelj> prikaziRoditelje(){
        System.out.println("SELECT * FROM Roditelj");
        return null;
    }

    //to-do
    //put dodaje roditelja, roditelj u pozivu, vraca boolean za izmenu
    public boolean dodajRoditelja(Roditelj r){
        System.out.println("INSERT INTO Roditelj " +
                "(idr, ime, prezime, username) VALUES (id, ime, prz, user)");
        return true;
    }

    //PROFESOR DEO

    //to-do
    //select za sve ucenike, prazan poziv, vraca listu ucenika
    public List<Ucenik> prikaziUcenike(){
        System.out.println("SELECT * FROM Ucenik");
        return null;
    }

    //to-do
    //select za odabrani razred, u pozivu se zadaje razred, vraca listu ucenika
    public List<Ucenik> prikaziRazred(Razred r){
        System.out.println("SELECT * FROM Ucenik WHERE razred = idr");
        return null;
    }


    //to-do
    //put upisuju ocenu, u pozivu se zadaje ocena, cas, i ucenik, vraca boolean za izmenu
    //treba uraditi ono za generate next available key, za sada sam ostavio id, isto u sledeca 2 upita
    public boolean upisiOcenu(long o, Ucenik u, Date cas){
        System.out.println("INSERT INTO Ocena (ido, razred_djaka, redni_broj_djaka, ocena, idp, datum) VALUES " +
                "(id, raz, rbdj, oce, idp, date)");
        return true;
    }


    //to-do
    //put upisuje izostanak, u pozivu se zadaje ucenik i cas i opcionalno opis, vraca boolean za izmenu
    public boolean upisiIzostanak(Ucenik u, String opis, Date cas){
        System.out.println("INSERT INTO Izostanak (idi, razred_djaka, redni_broj_djaka, opis, idp, datum) VALUES " +
                "(idi, raz, rbdj, opis, idp, date)");
        return true;
    }


    //to-do
    //put upisuje napomenu, u pozivu se zadaje ucenik i cas i opis, vraca boolean za izmenu
    public boolean upisiNapomenu(Ucenik u, String opis, Date cas){
        System.out.println("INSERT INTO Napomena (idn, razred_djaka, redni_broj_djaka, opis, idp, datum) VALUES " +
                "(id, raz, rbdj, opis, idp, date)");
        return true;
    }


    //RAZREDNI STARESINA DEO

    //to-do
    //put zakazuje roditeljski sastanak, u pozivu se zadaje samo datum, void
    //treba dodati u bazu tabelu roditeljski sastanak, sa atributima {idrs}, {idrs, idr, datum}.
    //treba dodati novi panel, roditeljski sastanak u swingu, moze ga videti samo roditelj, panel uzima idr od
    //svog deteta i trazi da li postoji roditeljski sastanak za razred deteta, ukoliko postoji ispisuje podatke

    //DJAK + RODITELJ DEO

    //to-do
    //select prikaz svih ocena, u pozivu se zadaje dete, vraca se lista svih ocena sa istim id-om deteta
    public List<Ocena> prikaziOcene(Ucenik u){
        System.out.println("SELECT * FROM Ocena WHERE razred_djaka = raz AND redni_broj_djaka = rbdj");
        return null;
    }

    public List<Ocena> prikaziOcene(Roditelj r){
        System.out.println("SELECT * FROM Ocena WHERE razred_djaka = raz AND redni_broj_djaka = rbdj");
        return null;
    }

    //RODITELJ DEO

    //to-do
    //select prikaz dece roditelja, u pozivu se zadaje roditelj, vraca se lista dece
    //bira se dete, i dalji pozivi se rade za selektovano dete, ovo biranje se radi na frontu
    //treba dodati polje roditelj u tabeli ucenik

    //to-do
    //select prikaz svih napomena, u pozivu se zadaje dete, vraca se lista napomena

    //to-do
    //select prikaz svih izostanaka, u pozivu se zadaje dete, vraca se lista izostanaka

    //to-do
    //select prikaz roditeljskog sastanka, u pozivu se zadaje dete, vraca se zakazan roditeljski ili null
}
