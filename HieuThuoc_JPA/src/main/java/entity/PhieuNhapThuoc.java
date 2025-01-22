package entity;

import jakarta.persistence.*;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "PhieuNhapThuoc")
public class PhieuNhapThuoc {
    @Id
    @Column(name = "ma_phieu_nhap_thuoc", unique = true, nullable = false)
    private String id;
    @Column(name = "thoi_gian")
    private LocalDateTime thoiGian;

    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "ma_nha_cung_cap")
    private NhaCungCap nhaCungCap;





}
