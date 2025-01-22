package model;

import dao.NhaSanXuatDAO;
import entity.DanhMuc;
import entity.KhuyenMai;
import entity.NhaSanXuat;
import entity.Thuoc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;
import org.checkerframework.checker.units.qual.N;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class HeloWorld {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();

//        Faker faker = new Faker();
//        EntityTransaction tr = em.getTransaction();
//        Random rd = new Random();
//
//
//
//        for (int i = 0; i < 10; i++) {
//            //them khuyenMai
//            KhuyenMai khuyenMai = new KhuyenMai();
//            khuyenMai.setId(faker.number().digits(20));
//            khuyenMai.setTen(faker.commerce().productName());
//            khuyenMai.setPhanTramGiamGia(faker.number().randomDouble(2, 5, 50));
//            khuyenMai.setThoiGianBatDau(LocalDate.now().minusDays(faker.number().numberBetween(1, 30)));
//            khuyenMai.setThoiGianKetThuc(LocalDate.now().plusDays(faker.number().numberBetween(1, 30)));
//
//            tr.begin();
//            em.persist(khuyenMai);
//            tr.commit();
//
//            //them nhaSanXuat
//            NhaSanXuat nhaSanXuat = new NhaSanXuat();
//            nhaSanXuat.setId(faker.number().digits(4));
//            nhaSanXuat.setTen(faker.company().name());
//
//            tr.begin();
//            em.persist(nhaSanXuat);
//            tr.commit();
//
//            //them danhMuc
//            DanhMuc danhMuc = new DanhMuc();
//            danhMuc.setId(faker.number().digits(4));
//            danhMuc.setTen(faker.commerce().department());
//            danhMuc.setViTriKe(faker.address().streetName());
//
//            tr.begin();
//            em.persist(danhMuc);
//            tr.commit();
//
//            //them Thuoc
//            Thuoc thuoc = new Thuoc();
//            thuoc.setId(faker.number().digits(20));
//            thuoc.setTen(faker.commerce().productName());
//            thuoc.setDonViTinh(faker.commerce().material());
//            thuoc.setThanhPhan(faker.commerce().department());
//            thuoc.setSoLuongTon(faker.number().numberBetween(1, 100));
//            thuoc.setHinhAnh(new byte[0]);
//            thuoc.setKhuyenMai(khuyenMai);
//            thuoc.setDanhMuc(danhMuc);
//            thuoc.setNhaSanXuat(nhaSanXuat);
//
//            tr.begin();
//            em.persist(thuoc);//
//            tr.commit();
//        }
//        NhaSanXuatDAO testCRUD = new NhaSanXuatDAO(em);
        // Create
//        NhaSanXuat testCreate1 = new NhaSanXuat("12345","Test Create");
//        NhaSanXuat testCreate2 = new NhaSanXuat("12344","Test Detele");
//        testCRUD.save(testCreate1);
//        testCRUD.save(testCreate2);
//        // Detele
//        testCRUD.delete(testCreate2.getId());
//
//        //Update
//        testCreate1.setTen("Test Update");
//        testCRUD.update(testCreate1);
//        //Read
//        List<NhaSanXuat> list = testCRUD.getAll();
//        for (NhaSanXuat nhaSanXuat : list) {
//            System.out.print(nhaSanXuat);
//        }





        

    }
}

