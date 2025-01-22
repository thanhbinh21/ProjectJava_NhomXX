package test;

import dao.NhaSanXuatDAO;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class TestCRUD {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();

        Faker faker = new Faker();
        EntityTransaction tr = em.getTransaction();
        Random rd = new Random();



        for (int i = 0; i < 10; i++) {
            //them khuyenMai
            KhuyenMai khuyenMai = new KhuyenMai();
            khuyenMai.setId(faker.number().digits(20    ));
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
            thuoc.setHinhAnh(new byte[0]);
            thuoc.setKhuyenMai(khuyenMai);
            thuoc.setDanhMuc(danhMuc);
            thuoc.setNhaSanXuat(nhaSanXuat);

            tr.begin();
            em.persist(thuoc);
            tr.commit();

            // Thêm VaiTro
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId("VT" + String.format("%03d", i + 1));
            String tenVaiTro = i % 2 == 0 ? "Nhân viên bán thuốc" : "Quản lý";
            vaiTro.setTenVaiTro(tenVaiTro);

            tr.begin();
            em.persist(vaiTro);
            tr.commit();
//            them TK
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setId("TK" + String.format("%03d", i + 1));
            taiKhoan.setPassword(faker.internet().password(8, 12));
            NhanVien nhanVien = em.find(NhanVien.class, "NV" + i);
            taiKhoan.setNhanVien(nhanVien);
            VaiTro vaiTro1 = em.find(VaiTro.class, i % 2 == 0 ? "VT001" : "VT002");
            taiKhoan.setVaiTro(vaiTro1);

            tr.begin();
            em.persist(taiKhoan);
            tr.commit();

            // Them NV
            NhanVien nhanVien1 = new NhanVien();
            nhanVien1.setId("NV" + String.format("%03d", i + 1));
            nhanVien1.setHoTen(faker.name().fullName());
            nhanVien1.setGioiTinh(faker.bool().bool());
            nhanVien1.setNamSinh(faker.number().numberBetween(2000, 2004));
            nhanVien1.setNgayVaoLam(LocalDate.now().minusDays(faker.number().numberBetween(1, 365)));
            nhanVien1.setHoaDons(new HashSet<>());

            tr.begin();
            em.persist(nhanVien1);
            tr.commit();

            //Them KH
            KhachHang khachHang = new KhachHang();
            khachHang.setId("KH" + String.format("%03d", i + 1));
            khachHang.setHoTen(faker.name().fullName());
            khachHang.setSoDienThoai(faker.phoneNumber().phoneNumber());
            khachHang.setGioiTinh(faker.bool().bool());
            khachHang.setNgayThamGia(LocalDate.now().minusDays(faker.number().numberBetween(1, 365)));
            khachHang.setHoaDons(null);
            khachHang.setPhieuDatThuocs(null);

            tr.begin();
            em.persist(khachHang);
            tr.commit();
            //Them NCC
            NhaCungCap nhaCungCap = new NhaCungCap();
            nhaCungCap.setId("NCC" + String.format("%03d", i + 1));
            nhaCungCap.setTen(faker.company().name());
            nhaCungCap.setDiaChi(faker.address().streetAddress());
            nhaCungCap.setSdt(faker.phoneNumber().phoneNumber());

            tr.begin();
            em.persist(nhaCungCap);
            tr.commit();

        }
        NhaSanXuatDAO testCRUD = new NhaSanXuatDAO(em);
        // Create
        NhaSanXuat testCreate1 = new NhaSanXuat("12345","Test Create");
        NhaSanXuat testCreate2 = new NhaSanXuat("12344","Test Detele");
        testCRUD.save(testCreate1);
        testCRUD.save(testCreate2);
        // Detele
        testCRUD.delete(testCreate2.getId());

        //Update
        testCreate1.setTen("Test Update");
        testCRUD.update(testCreate1);
        //Read
        List<NhaSanXuat> list = testCRUD.getAll();
        for (NhaSanXuat nhaSanXuat : list) {
            System.out.print(nhaSanXuat);
        }
//        Làm cho phiếu nhập thuốc
        List<NhanVien> nhanVienList = em.createQuery("from NhanVien").getResultList();
        List<NhaCungCap> nhaCungCapList = em.createQuery("from NhaCungCap").getResultList();
        List<Thuoc> thuocList = em.createQuery("from Thuoc").getResultList();
        for (int i = 0; i < 20; i++) {
            PhieuNhapThuoc phieuNhapThuoc = new PhieuNhapThuoc();
            phieuNhapThuoc.setId("PNT" + String.format("%06d", i + 1));
            phieuNhapThuoc.setNhanVien(nhanVienList.get(rd.nextInt(10)));
            phieuNhapThuoc.setNhaCungCap(nhaCungCapList.get(rd.nextInt(10)));
            LocalDateTime thoiGian = LocalDateTime.of(rd.nextInt(2025 - 2023) + 2023, rd.nextInt(12) + 1, rd.nextInt(28) + 1, 5, 30);
            phieuNhapThuoc.setThoiGian(thoiGian);
            for (int j = 0; j < rd.nextInt(4) + 1; j++) {
                ChiTietPhieuNhapThuoc chiTietPhieuNhapThuoc = new ChiTietPhieuNhapThuoc();
                Thuoc thuoc = thuocList.get( (i + j) % thuocList.size() );
                chiTietPhieuNhapThuoc.setThuoc(thuoc);
                chiTietPhieuNhapThuoc.setDonGia(600000000);
                chiTietPhieuNhapThuoc.setSoLuong(thuoc.getSoLuongTon());
                chiTietPhieuNhapThuoc.setPhieuNhapThuoc(phieuNhapThuoc);
                if(phieuNhapThuoc.getChiTietPhieuNhapThuocs() == null) {
                    phieuNhapThuoc.setChiTietPhieuNhapThuocs(new HashSet<>());
                }
                phieuNhapThuoc.getChiTietPhieuNhapThuocs().add(chiTietPhieuNhapThuoc);
            }
            tr.begin();
            em.persist(phieuNhapThuoc);
            tr.commit();
        }
        List<KhachHang> khachHangList = em.createQuery("from KhachHang").getResultList();
        List<Thuoc> thuocList1 = em.createQuery("from Thuoc where id in (select h.thuoc.id from ChiTietPhieuNhapThuoc h group by h.thuoc having count(h) > 1)", Thuoc.class).getResultList();
        for (int i = 0; i < thuocList1.size(); i++) {
            PhieuDatThuoc phieuDatThuoc = new PhieuDatThuoc();
            phieuDatThuoc.setId("PDT" + String.format("%06d", i + 1));
            phieuDatThuoc.setNhanVien(nhanVienList.get(rd.nextInt(10)));
            KhachHang khachHang = khachHangList.get(rd.nextInt(10));
            phieuDatThuoc.setKhachHang(khachHang);
            if(khachHang.getPhieuDatThuocs() == null) {
                khachHang.setPhieuDatThuocs(new HashSet<>());
            }
            khachHang.getPhieuDatThuocs().add(phieuDatThuoc);
            LocalDateTime thoiGian = LocalDateTime.of(rd.nextInt(2025 - 2023) + 2023, rd.nextInt(12) + 1, rd.nextInt(28) + 1, 5, 30);
            phieuDatThuoc.setThoiGian(Timestamp.valueOf(thoiGian));
            phieuDatThuoc.setTrangThai(faker.bool().bool());

            ChiTietPhieuDatThuoc chiTietPhieuDatThuoc = new ChiTietPhieuDatThuoc();
            chiTietPhieuDatThuoc.setThuoc(thuocList1.get(i));
            chiTietPhieuDatThuoc.setDonGia(600000000);
            chiTietPhieuDatThuoc.setSoLuong(thuocList1.get(i).getSoLuongTon());
            chiTietPhieuDatThuoc.setPhieuDatThuoc(phieuDatThuoc);
            phieuDatThuoc.setChiTietPhieuDatThuocs(new HashSet<>());
            phieuDatThuoc.getChiTietPhieuDatThuocs().add(chiTietPhieuDatThuoc);
            tr.begin();
            em.persist(phieuDatThuoc);
            tr.commit();
        }
        thuocList1 = em.createQuery("from Thuoc where id in (select h.thuoc.id from ChiTietPhieuNhapThuoc h group by h.thuoc having count(h) > 2)", Thuoc.class).getResultList();
        for (int i = 0; i < thuocList1.size(); i++) {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setId("HD" + String.format("%06d", i + 1));
            hoaDon.setNhanVien(nhanVienList.get(i));
            NhanVien nhanVien = nhanVienList.get(rd.nextInt(10));
            if(nhanVien.getHoaDons() == null) {
                nhanVien.setHoaDons(new HashSet<>());
            }
            nhanVien.getHoaDons().add(hoaDon);
            KhachHang khachHang = khachHangList.get(rd.nextInt(10));
            hoaDon.setKhachHang(khachHang);
            if(khachHang.getHoaDons() == null) {
                khachHang.setHoaDons(new HashSet<>());
            }
            khachHang.getHoaDons().add(hoaDon);
            hoaDon.setTrangThai(faker.bool().bool());
            LocalDateTime thoiGian = LocalDateTime.of(rd.nextInt(2025 - 2023) + 2023, rd.nextInt(12) + 1, rd.nextInt(28) + 1, 5, 30);
            hoaDon.setThoiGian(Timestamp.valueOf(thoiGian));

            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setHoaDon(hoaDon);
            hoaDon.setChiTietHoaDons(new HashSet<>());
            hoaDon.getChiTietHoaDons().add(chiTietHoaDon);
            chiTietHoaDon.setDonGia(600000000);
            Thuoc thuoc = thuocList1.get(i);
            chiTietHoaDon.setThuoc(thuoc);
            int soLuongNhap = em.createQuery("select sum(n.soLuong) from ChiTietPhieuNhapThuoc n where n.thuoc.id = :n group by n.thuoc", Long.class).setParameter("n", thuoc.getId()).getSingleResult().intValue();
            chiTietHoaDon.setSoLuong(soLuongNhap - thuoc.getSoLuongTon() * 2);
            if(thuoc.getChiTietHoaDons() == null) {
                thuoc.setChiTietHoaDons(new HashSet<>());
            }
            thuoc.getChiTietHoaDons().add(chiTietHoaDon);

            tr.begin();
            em.persist(hoaDon);
            tr.commit();
        }
    }
}

