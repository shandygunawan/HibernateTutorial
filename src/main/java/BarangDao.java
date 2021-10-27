import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class BarangDao {

    public List<Barang> selectAll() {
        Session session = null;
        List<Barang> listBarang = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            listBarang = session.createQuery("from Barang u").list();
            if (listBarang.size() > 0) {
                System.out.println("All Barang -->" + listBarang);
            } else {
                System.out.println("All Barang --> empty");
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return listBarang;
    }

    public Barang selectById(String id_barang) {
        Session session = null;
        Barang barang = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            barang = (Barang) session.get(Barang.class, id_barang);
            if (barang != null) {
                System.out.println("Barang --> " + barang);
            } else {
                System.out.println("Barang --> Empty");
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return barang;
    }

    public Boolean insert(Barang bar) {
        String id_barang = null;
        Session session = null;
        Boolean isSuccess = false;
        Transaction trans = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            trans = session.beginTransaction();
            id_barang = (String) session.save(bar);
            trans.commit();
            isSuccess = true;
        } catch (HibernateException e) {
            if (trans != null) {
                trans.rollback();
                isSuccess = false;
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return isSuccess;
    }

    public Boolean update(Barang bar) {
        Session session = null;
        Boolean isSuccess = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(bar);
            session.getTransaction().commit();
            isSuccess = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return isSuccess;
    }

    public Boolean delete(Barang bar) {
        Session session = null;
        Boolean isSuccess = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(bar);
            session.getTransaction().commit();
            isSuccess = true;
        } catch(HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return isSuccess;
    }

    public void callStoredProcedure() {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            EntityManagerFactory emf = session.getEntityManagerFactory();
            EntityManager entityManager = emf.createEntityManager();

            StoredProcedureQuery spQuery = entityManager.createStoredProcedureQuery("contoh_procedure", Barang.class)
                    .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                    .setParameter(1, "Show Barang");

            spQuery.execute();
            List<Barang> result = spQuery.getResultList();

            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i).getNama_barang());
            }

        } catch(HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
