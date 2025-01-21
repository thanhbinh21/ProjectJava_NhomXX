package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Table(name = "Thuoc")
public class Thuoc {
    @Id
    @Column(name = "thuoc_id",columnDefinition = "varchar(45)", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(columnDefinition = "varchar(45)", unique = true, nullable = false)
    private String ten;

    @Column(columnDefinition = "varchar(45)",nullable = false)
    private String donViTinh;

    @Column(columnDefinition = "varchar(45)",nullable = false)
    private String thanhPhan;


    @Column(columnDefinition = "INT", nullable = false)
    private int soLuongTon;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] hinhAnh;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "nhaSanXuat_id", nullable = false)
    private NhaSanXuat nhaSanXuat;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "danhMuc_id", nullable = false)
    private DanhMuc danhMuc;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "khuyenMai_id", nullable = false)
    private KhuyenMai khuyenMai;


//    @ToString.Exclude
//    @ManyToOne
//    @JoinColumn(name = "phieuDatThuoc_id", nullable = false)
//    private Set<PhieuDatThuoc> phieuDatThuoc;
//
//    @ToString.Exclude
//    @ManyToOne
//    @JoinColumn(name = "hoaDon_id", nullable = false)
//    private Set<HoaDon> hoaDon;
//
//    @ToString.Exclude
//    @ManyToOne
//    @JoinColumn(name = "phieuNhap_id", nullable = false)
//    private Set<PhieuNhap> phieuNhap;
}
