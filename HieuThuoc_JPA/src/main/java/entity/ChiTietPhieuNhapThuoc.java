package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false, onlyExplicitlyIncluded=true)
@Entity
@Table(name = "ChiTietPhieuNhapThuoc")
public class ChiTietPhieuNhapThuoc {
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_phieu_nhap_thuoc", nullable = false)
    @EqualsAndHashCode.Include
    private PhieuNhapThuoc phieuNhapThuoc;
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_thuoc", nullable = false)
    @EqualsAndHashCode.Include
    private Thuoc thuoc;
    @Column(name = "so_luong", nullable = false)
    private int soLuong;
    @Column(name = "don_gia", nullable = false)
    private double donGia;
}
