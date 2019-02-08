package crud;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import klase.*;
import org.apache.catalina.User;
import utils.*;

import java.util.List;

public class Crud {
    public Crud() {
    }

    //LOG IN DEO

    public UserInfo login(String username, String password) {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select u from UserInfo u where u.username=:x and u.password=:y";
        Query q = em.createQuery(upit);
        q.setParameter("x", username);
        q.setParameter("y", password);
        UserInfo user = (UserInfo) q.getSingleResult();
        em.close();
        return user;
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

    public Ucenik findUcenik(String razred, String redniBroj){
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select u from Ucenik u where u.razred=:x and u.redniBroj=:y";
        Query q = em.createQuery(upit);
        q.setParameter("x", razred);
        q.setParameter("y", redniBroj);
        Ucenik ucenik = (Ucenik) q.getSingleResult();
        em.close();
        return ucenik;
    }

    public List<Ucenik> listRazredUcenici(String razred) {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select u from Ucenik u where u.razred=:x";
        Query q = em.createQuery(upit);
        q.setParameter("x", razred);
        List<Ucenik> ucenici = q.getResultList();
        em.close();
        return ucenici;
    }

    public List<Razred> listRazred() {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select r from Razred r order by godina, odelenje";
        Query q = em.createQuery(upit);
        List<Razred> razredi = q.getResultList();
        em.close();
        return razredi;
    }

    public long findIdp(String naziv) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Predmeti predmeti = (Predmeti)em.find(Predmeti.class, naziv);
        em.close();
        return predmeti.getIdp();
    }

    public void insertOcena(Ocene o) {
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

    public void insertIzostanak(Izostanak i) {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(i);
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

    public List<Ocene> listOcene(Ucenik ucenik) {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select o from Ocene o where o.razredDjaka=:x and o.redniBrojDjaka=:y";
        Query q = em.createQuery(upit);
        q.setParameter("x", ucenik.getRazred());
        q.setParameter("y", ucenik.getRedniBroj());
        List<Ocene> ocene = q.getResultList();
        em.close();
        return ocene;
    }

    public List<Predmeti> listPredmeti() {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select p from Predmeti p order by idp";
        Query q = em.createQuery(upit);
        List<Predmeti> predmeti = q.getResultList();
        em.close();
        return predmeti;
    }

    public Ucenik findUcenik(String username) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Ucenik u = (Ucenik)em.find(Ucenik.class, username);
        em.close();
        return u;
    }

    public List<Ocene> oceneIzPredmeta(long idp, Ucenik ucenik){
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select o from Ocene o where o.razredDjaka=:x and o.redniBrojDjaka=:y and o.idp=:z";
        Query q = em.createQuery(upit);
        q.setParameter("x", ucenik.getRazred());
        q.setParameter("y", ucenik.getRedniBroj());
        q.setParameter("z", idp);
        List<Ocene> ocene = q.getResultList();
        em.close();
        return ocene;
    }

    //RODITELJ DEO

    public List<Izostanak> listIzostanci(Ucenik ucenik) {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select i from Izostanak i where i.razredDjaka=:x and i.redniBrojDjaka=:y";
        Query q = em.createQuery(upit);
        q.setParameter("x", ucenik.getRazred());
        q.setParameter("y", ucenik.getRedniBroj());
        List<Izostanak> izostanci = q.getResultList();
        em.close();
        return izostanci;
    }

    public List<Napomena> listNapomene(Ucenik ucenik) {
        EntityManager em = PersistenceUtil.getEntityManager();
        String upit = "select n from Napomena n where n.razredDjaka=:x and n.redniBrojDjaka=:y";
        Query q = em.createQuery(upit);
        q.setParameter("x", ucenik.getRazred());
        q.setParameter("y", ucenik.getRedniBroj());
        List<Napomena> napomene = q.getResultList();
        em.close();
        return napomene;
    }

}