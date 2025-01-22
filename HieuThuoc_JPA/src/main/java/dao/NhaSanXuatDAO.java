package dao;

import entity.NhaSanXuat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class NhaSanXuatDAO {
    private EntityManager em;
    public NhaSanXuatDAO(EntityManager em) {
        this.em = em;

    }
    //CRUD
    public boolean save(NhaSanXuat nsx) {
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(nsx);
            tr.commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean update(NhaSanXuat nsx){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(nsx);
            tr.commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean delete(String id){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            NhaSanXuat nsx = em.find(NhaSanXuat.class, id);
            em.remove(nsx);
            tr.commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }
    public List<NhaSanXuat> getAll(){
        return em.createQuery("SELECT nsx FROM NhaSanXuat nsx", NhaSanXuat.class).getResultList();
    }
}
