package model;

import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            KhuyenMai khuyenMai = new KhuyenMai();
            khuyenMai.setId(faker.number().digits(20));
            khuyenMai.setTen(faker.commerce().productName());
            khuyenMai.setPhanTramGiamGia(faker.number().randomDouble(2, 5, 50));
            khuyenMai.setThoiGianBatDau(LocalDate.now().minusDays(faker.number().numberBetween(1, 30)));
            khuyenMai.setThoiGianKetThuc(LocalDate.now().plusDays(faker.number().numberBetween(1, 30)));

            tr.begin();
            em.persist(khuyenMai);
            tr.commit();

            //them nhaSanXuat
            NhaSanXuat nhaSanXuat = new NhaSanXuat();
            nhaSanXuat.setId(faker.number().digits(4));
            nhaSanXuat.setTen(faker.company().name());

            tr.begin();
            em.persist(nhaSanXuat);
            tr.commit();

            //them danhMuc
            DanhMuc danhMuc = new DanhMuc();
            danhMuc.setId(faker.number().digits(4));
            danhMuc.setTen(faker.commerce().department());
            danhMuc.setViTriKe(faker.address().streetName());

            tr.begin();
            em.persist(danhMuc);
            tr.commit();

            //them Thuoc
            Thuoc thuoc = new Thuoc();
            thuoc.setId(faker.number().digits(20));
            thuoc.setTen(faker.commerce().productName());
            thuoc.setDonViTinh(faker.commerce().material());
            thuoc.setThanhPhan(faker.commerce().department());
            thuoc.setSoLuongTon(faker.number().numberBetween(1, 100));
            try {
                thuoc.setHinhAnh(new FileInputStream("D:\\wp_appJAVA\\NhomXX_PTJava\\HieuThuoc_JPA\\product-image\\bai-trang-truong-phuc.jpg").readAllBytes());
            }catch (Exception e){
                e.printStackTrace();
            }
            thuoc.setKhuyenMai(khuyenMai);
            thuoc.setDanhMuc(danhMuc);
            thuoc.setNhaSanXuat(nhaSanXuat);

            tr.begin();
            em.persist(thuoc);
            tr.commit();
        }

        for (int i = 0; i < 10; i++) {
            KhachHang khachHang = new KhachHang();
            khachHang.setId(faker.number().digits(5));
            khachHang.setGioiTinh(faker.bool().bool());
            khachHang.setHoTen(faker.name().fullName());
            khachHang.setSoDienThoai(faker.phoneNumber().cellPhone());
            khachHang.setNgayThamGia(LocalDate.now());

            tr.begin();
            em.persist(khachHang);
            tr.commit();
        }
        for (int i = 0; i < 10; i++) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setId(faker.number().digits(5));
            nhanVien.setGioiTinh(faker.bool().bool());
            nhanVien.setHoTen(faker.name().fullName());
            nhanVien.setNamSinh(2004);
            nhanVien.setNgayVaoLam(LocalDate.now());

            tr.begin();
            em.persist(nhanVien);
            tr.commit();
        }
        List<KhachHang> list1 = em.createQuery("from KhachHang").getResultList();
        List<NhanVien> list2 = em.createQuery("from NhanVien").getResultList();
        for (int i = 0; i < 10; i++) {
            PhieuDatThuoc phieuDatThuoc = new PhieuDatThuoc();
            phieuDatThuoc.setId(faker.regexify("PDT") + i);
            phieuDatThuoc.setThoiGian(Timestamp.valueOf(LocalDateTime.now()));
            phieuDatThuoc.setTrangThai(faker.bool().bool());
            KhachHang khachHang1 = list1.get(rd.nextInt(list1.size()));
            phieuDatThuoc.setKhachHang(khachHang1);
            if(khachHang1.getPhieuDatThuocs() == null)
                khachHang1.setPhieuDatThuocs(new HashSet<>());
            khachHang1.getPhieuDatThuocs().add(phieuDatThuoc);
            NhanVien nhanVien1 = list2.get(rd.nextInt(list2.size()));
            phieuDatThuoc.setNhanVien(nhanVien1);

            tr.begin();
            em.persist(phieuDatThuoc);
            tr.commit();
        }
    }
}

