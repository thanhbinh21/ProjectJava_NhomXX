package run;

import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatThuoc;
import entity.Thuoc;
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
import java.util.Map;
import java.util.Random;

public class Runner {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu").createEntityManager();
        EntityTransaction et = em.getTransaction();
        Faker faker = new Faker();
        Random rand = new Random();
        for(int i = 0; i < 10; i++) {
            Thuoc thuoc = new Thuoc();
            thuoc.setId(faker.regexify("T") + i);
            thuoc.setTen(faker.name().fullName());
            thuoc.setDonViTinh("Vỉ");
            thuoc.setThanhPhan(faker.dragonBall().character());
            thuoc.setSoLuongTon(rand.nextInt(100));
            try {
                thuoc.setHinhAnh(new FileInputStream("C:\\Users\\QUANG MINH\\Pictures\\Saved Pictures\\B\\videos\\video3\\pic1.png").readAllBytes());
            }catch (Exception e){
                e.printStackTrace();
            }
            et.begin();
            em.persist(thuoc);
            et.commit();
        }
        for (int i = 0; i < 10; i++) {
            KhachHang khachHang = new KhachHang();
            khachHang.setId(faker.number().digits(5));
            khachHang.setGioiTinh(faker.bool().bool());
            khachHang.setHoTen(faker.name().fullName());
            khachHang.setSoDienThoai(faker.phoneNumber().cellPhone());
            khachHang.setNgayThamGia(LocalDate.now());

            et.begin();
            em.persist(khachHang);
            et.commit();
        }
        for (int i = 0; i < 10; i++) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setId(faker.number().digits(5));
            nhanVien.setGioiTinh(faker.bool().bool());
            nhanVien.setHoTen(faker.name().fullName());
            nhanVien.setNamSinh(2004);
            nhanVien.setNgayVaoLam(LocalDate.now());

            et.begin();
            em.persist(nhanVien);
            et.commit();
        }
        List<KhachHang> list1 = em.createQuery("from KhachHang").getResultList();
        List<NhanVien> list2 = em.createQuery("from NhanVien").getResultList();
        for (int i = 0; i < 10; i++) {
            PhieuDatThuoc phieuDatThuoc = new PhieuDatThuoc();
            phieuDatThuoc.setId(faker.regexify("PDT") + i);
            phieuDatThuoc.setThoiGian(Timestamp.valueOf(LocalDateTime.now()));
            phieuDatThuoc.setTrangThai(faker.bool().bool());
            KhachHang khachHang1 = list1.get(rand.nextInt(list1.size()));
            phieuDatThuoc.setKhachHang(khachHang1);
            if(khachHang1.getPhieuDatThuocs() == null)
                khachHang1.setPhieuDatThuocs(new HashSet<>());
            khachHang1.getPhieuDatThuocs().add(phieuDatThuoc);
            NhanVien nhanVien1 = list2.get(rand.nextInt(list2.size()));
            phieuDatThuoc.setNhanVien(nhanVien1);

            et.begin();
            em.persist(phieuDatThuoc);
            et.commit();
            //
        }
    }
}
