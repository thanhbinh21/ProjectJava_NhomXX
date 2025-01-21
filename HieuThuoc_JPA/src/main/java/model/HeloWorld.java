package model;

import entity.DanhMuc;
import entity.KhuyenMai;
import entity.NhaSanXuat;
import entity.Thuoc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class HeloWorld {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();


        Faker faker = new Faker();
        EntityTransaction tr = em.getTransaction();
        Random rd= new Random();

        for(int i=0;i<10;i++){
            Thuoc thuoc = new Thuoc();
            thuoc.setId(faker.number().digits(20));
            thuoc.setTen(faker.commerce().productName());
            thuoc.setDonViTinh(faker.commerce().material());
            thuoc.setThanhPhan(faker.commerce().department());
            thuoc.setSoLuongTon(faker.number().numberBetween(1, 100));
            thuoc.setHinhAnh(new byte[0]);

            tr.begin();
            em.merge(thuoc);
            tr.commit();
        }

        for(int i=0;i<10;i++){
            KhuyenMai khuyenMai = new KhuyenMai();
            khuyenMai.setId(faker.number().digits(20));
            khuyenMai.setTen(faker.commerce().productName());
            khuyenMai.setPhanTramGiamGia(faker.number().randomDouble(2, 5, 50));
            khuyenMai.setThoiGianBatDau(LocalDate.now().minusDays(faker.number().numberBetween(1, 30)));
            khuyenMai.setThoiGianKetThuc(LocalDate.now().plusDays(faker.number().numberBetween(1, 30)));


            String thuocID = String.valueOf(rd.nextInt(100)+1);
//            Thuoc thuoc;
//            thuoc = em.createQuery("select t from Thuoc t", Thuoc.class).getResultList().get(rd.nextInt(10));
//            khuyenMai.setThuoc(new HashSet<>(List.of(thuoc)));
//            thuoc.setKhuyenMai(khuyenMai);
//            tr.begin();
//            em.persist(khuyenMai);
//            tr.commit();


            Thuoc thuoc = em.find(Thuoc.class, thuocID);
            khuyenMai.setThuoc(new HashSet<>(List.of(thuoc)));

            tr.begin();
            em.merge(khuyenMai);
            tr.commit();
        }

        for(int i=0;i<10;i++){
            NhaSanXuat nhaSanXuat = new NhaSanXuat();
            nhaSanXuat.setId(faker.number().digits(4));
            nhaSanXuat.setTen(faker.company().name());

//            String thuocID = String.valueOf(rd.nextInt(100)+1);
//            Thuoc thuoc = em.find(Thuoc.class,thuocID );
//            nhaSanXuat.setThuoc(new HashSet<>(List.of(thuoc)));
            tr.begin();
            em.persist(nhaSanXuat);
            tr.commit();
        }

        for(int i=0;i<10;i++){
            DanhMuc danhMuc = new DanhMuc();
            danhMuc.setId(faker.number().digits(4));
            danhMuc.setTen(faker.commerce().department());
            danhMuc.setViTriKe(faker.address().streetName());

//            String thuocID = String.valueOf(rd.nextInt(100)+1);
//            Thuoc thuoc = em.find(Thuoc.class,thuocID );
//            danhMuc.setThuoc(new HashSet<>(List.of(thuoc)));
            tr.begin();
            em.persist(danhMuc);
            tr.commit();
        }

    }
}
