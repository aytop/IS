package crud;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import klase.*;
import utils.*;

import java.util.Iterator;
import java.util.List;

public class Crud {
    public Crud() {
    }

    //ADMIN DEO

    public void insertUcenik(Ucenik u) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(u);
            em.flush();
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }

        }

    }

    public List<Roditelj> listRoditelj() {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select r from Roditelj r";
        List<Roditelj> roditelji = em.createQuery(upit).getResultList();
        em.close();
        return roditelji;
    }

    public void insertRoditelj(Roditelj r) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(r);
            em.flush();
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }

        }

    }

    //PROFESOR DEO

    public List<Ucenik> listUcenik() {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select u from Ucenik u order by u.razred";
        List<Ucenik> ucenici = em.createQuery(upit).getResultList();
        em.close();
        return ucenici;
    }

    public List<Ucenik> listRazred(String razred) {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select u from Ucenik u where u.razred=:x";
        Query q = em.createQuery(upit);
        q.setParameter("x", razred);
        List<Ucenik> ucenici = q.getResultList();
        em.close();
        return ucenici;
    }

    public void insertOcena(Ocena o) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(o);
            em.flush();
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }

        }

    }

    public void insertNapomena(Napomena n) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(n);
            em.flush();
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }

        }

    }

    //RAZREDNI DEO

    //ne znam kako tacno izgleda Roditeljski tabela, samo copy paste od ovih
    //takodje treba povezati sa bazom da ne bi iskakali errori za tabele iz baze

    //DJAK DEO

    public List<Ocena> listOcene(Ucenik ucenik) {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select o from Ocene o where o.razred_djaka=:x and o.redni_broj_djaka=:y";
        Query q = em.createQuery(upit);
        q.setParameter("x", ucenik.getRazred());
        q.setParameter("y", ucenik.getRedniBroj());
        List<Ocena> ocene = q.getResultList();
        em.close();
        return ocene;
    }

    //RODITELJ DEO

    public List<Izostanak> listIzostanci(Ucenik ucenik) {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select i from Izostanak i where i.razred_djaka=:x and i.redni_broj_djaka=:y";
        Query q = em.createQuery(upit);
        q.setParameter("x", ucenik.getRazred());
        q.setParameter("y", ucenik.getRedniBroj());
        List<Izostanak> izostanci = q.getResultList();
        em.close();
        return izostanci;
    }

    public List<Napomena> listNapomene(Ucenik ucenik) {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select n from Napomena n where n.razred_djaka=:x and n.redni_broj_djaka=:y";
        Query q = em.createQuery(upit);
        q.setParameter("x", ucenik.getRazred());
        q.setParameter("y", ucenik.getRedniBroj());
        List<Napomena> napomene = q.getResultList();
        em.close();
        return napomene;
    }

    public List<RoditeljskiSastanak> listRoditeljski(Ucenik ucenik) {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select r from RoditeljskiSastanak r where r.razred=:x ";
        Query q = em.createQuery(upit);
        q.setParameter("x", ucenik.getRazred());
        List<RoditeljskiSastanak> roditeljski = q.getResultList();
        em.close();
        return roditeljski;
    }
}

    //ukoliko treba nesto da se doda dole ima par primera sa baza 2


//    public void poveziNastavnikIPredmet(Nastavnik n, Predmet p) {
//        EntityManager em = PersistenceUtil.getEntityManager();
//        EntityTransaction et = null;
//
//        try {
//            et = em.getTransaction();
//            et.begin();
//            n = (Nastavnik)em.merge(n);
//            p = (Predmet)em.merge(p);
//            n.getPredmets().add(p);
//            p.getNastavniks().add(n);
//            em.merge(n);
//            em.merge(p);
//            em.flush();
//            et.commit();
//        } catch (Exception var9) {
//            var9.printStackTrace();
//            if (et != null) {
//                et.rollback();
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//
//        }
//
//    }
//
//    public Razred findRazred(String idr) {
//        EntityManager em = PersistenceUtil.getEntityManager();
//        Razred razred = (Razred) em.find(Razred.class, idr);
//        em.close();
//        return razred;
//    }
//
//    public List<Nastavnik> getNastavnici(String ime, String prezime) {
//        EntityManager em = PersistenceUtil.getEntityManager();
//        String upit = "select n from Nastavnik n where n.ime=:x and n.prezime=:y";
//        Query q = em.createQuery(upit);
//        q.setParameter("x", ime);
//        q.setParameter("y", prezime);
//        List<Nastavnik> nastavnici = q.getResultList();
//        em.close();
//        return nastavnici;
//    }
//
//    public Nastavnik getNastavnik(Nastavnik n) {
//        EntityManager em = PersistenceUtil.getEntityManager();
//        String upit = "select n from Nastavnik n where n.ime=:x and n.prezime=:y";
//        Query q = em.createQuery(upit);
//        q.setParameter("x", n.getIme());
//        q.setParameter("y", n.getPrezime());
//        List<Nastavnik> lista = q.getResultList();
//        System.out.println(((Nastavnik)lista.get(lista.size() - 1)).getNastavnikId());
//        return (Nastavnik)lista.get(lista.size() - 1);
//    }
//
//    public Predmet findPredmet(Integer id) {
//        EntityManager em = PersistenceUtil.getEntityManager();
//        Predmet retVal = (Predmet)em.find(Predmet.class, new Long((long)id));
//        em.close();
//        return retVal;
//    }
//
//    public List<Nastavnik> listaPredavaca(Predmet p) {
//        EntityManager em = PersistenceUtil.getEntityManager();
//        List<Nastavnik> lista = p.getNastavniks();
//        em.close();
//        return lista;
//    }
//
//    public Predmet getPredmet(Predmet p) {
//        EntityManager em = PersistenceUtil.getEntityManager();
//        String upit = "select p from Predmet p where p.naziv=:x";
//        Query q = em.createQuery(upit);
//        q.setParameter("x", p.getNaziv());
//        List<Predmet> lista = q.getResultList();
//        return (Predmet)lista.get(lista.size() - 1);
//    }
//
//    public List<Predmet> listaPredmeta(Nastavnik n) {
//        EntityManager em = PersistenceUtil.getEntityManager();
//        String upit = "select n from Nastavnik n join fetch n.predmets where n.id=:id";
//        Query q = em.createQuery(upit);
//        q.setParameter("id", n.getNastavnikId());
//        Nastavnik nast = (Nastavnik)q.getSingleResult();