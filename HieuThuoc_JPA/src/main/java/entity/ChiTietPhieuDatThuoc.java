package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "ChiTietPhieuDatThuoc")
public class ChiTietPhieuDatThuoc {
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_phieu_dat_thuoc", nullable = false, insertable=false, updatable=false)
    private PhieuDatThuoc phieuDatThuoc;
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_thuoc", nullable = false, insertable=false, updatable=false)
    private Thuoc thuoc;
    @Column(name = "so_luong", nullable = false)
    private int soLuong;
    @Column(name = "don_gia", nullable = false)
    private double donGia;
}
