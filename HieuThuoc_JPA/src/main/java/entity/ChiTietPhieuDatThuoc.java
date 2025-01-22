package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "ChiTietPhieuDatThuoc")
public class ChiTietPhieuDatThuoc {
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_phieu_dat_thuoc", nullable = false)
    @EqualsAndHashCode.Include
    private PhieuDatThuoc phieuDatThuoc;
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
