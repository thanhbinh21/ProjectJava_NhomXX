package model;

import dao.NhaSanXuatDAO;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;
import org.checkerframework.checker.units.qual.N;

import java.sql.Timestamp;
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
        Random rd = new Random();



        for (int i = 0; i < 10; i++) {
            //them khuyenMai
//            KhuyenMai khuyenMai = new KhuyenMai();
//            khuyenMai.setId(faker.number().digits(20    ));
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
//            em.persist(thuoc);
//            tr.commit();

            // Thêm VaiTro
//            VaiTro vaiTro = new VaiTro();
//            vaiTro.setId("VT" + faker.number().digits(2));
//            String tenVaiTro = i % 2 == 0 ? "Nhân viên bán thuốc" : "Quản lý";
//            vaiTro.setTenVaiTro(tenVaiTro);
//
//            tr.begin();
//            em.persist(vaiTro);
//            tr.commit();
////            them TK
//            TaiKhoan taiKhoan = new TaiKhoan();
//            taiKhoan.setId("TK" + faker.number().digits(2));
//            taiKhoan.setPassword(faker.internet().password(8, 12));
//            NhanVien nhanVien = em.find(NhanVien.class, "NV" + i);
//            taiKhoan.setNhanVien(nhanVien);
////            VaiTro vaiTro = em.find(VaiTro.class, i % 2 == 0 ? "VT001" : "VT002");
//            taiKhoan.setVaiTro(vaiTro);
//
//            tr.begin();
//            em.persist(taiKhoan);
//            tr.commit();

            // Them NV
//            NhanVien nhanVien = new NhanVien();
//            nhanVien.setId("NV" + String.format("%03d", i + 1));
//            nhanVien.setHoTen(faker.name().fullName());
//            nhanVien.setGioiTinh(faker.bool().bool());
//            nhanVien.setNamSinh(faker.number().numberBetween(2000, 2004));
//            nhanVien.setNgayVaoLam(LocalDate.now().minusDays(faker.number().numberBetween(1, 365)));
//            nhanVien.setHoaDons(new HashSet<>());
//
//            tr.begin();
//            em.persist(nhanVien);
//            tr.commit();

            //Them KH
//            KhachHang khachHang = new KhachHang();
//            khachHang.setId("KH" + String.format("%03d", i + 1));
//            khachHang.setHoTen(faker.name().fullName());
//            khachHang.setSoDienThoai(faker.phoneNumber().phoneNumber());
//            khachHang.setGioiTinh(faker.bool().bool());
//            khachHang.setNgayThamGia(LocalDate.now().minusDays(faker.number().numberBetween(1, 365)));
//            khachHang.setHoaDons(null);
//            khachHang.setPhieuDatThuocs(null);
//
//            tr.begin();
//            em.persist(khachHang);
//            tr.commit();
            //Them NCC
//            NhaCungCap nhaCungCap = new NhaCungCap();
//            nhaCungCap.setId("NCC" + String.format("%03d", i + 1));
//            nhaCungCap.setTen(faker.company().name());
//            nhaCungCap.setDiaChi(faker.address().streetAddress());
//            nhaCungCap.setSdt(faker.phoneNumber().phoneNumber());
//
//            tr.begin();
//            em.persist(nhaCungCap);
//            tr.commit();

        }
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

