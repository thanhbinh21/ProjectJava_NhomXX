package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Table(name = "HoaDon")
public class HoaDon {
    @Id
    @Column(name = "ma_hoa_don",columnDefinition = "nvarchar(45)",nullable = false)
    private String id;
    @Column(name = "thoi_gian",nullable = false)
    private Timestamp thoiGian;
    @Column(name = "trang_thai",nullable = false)
    private boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien", nullable = false) // Cột khóa ngoại
    private NhanVien nhanVien;
    @ManyToOne
    @JoinColumn(name = "ma_khach_hang", nullable = false)
    private KhachHang khachHang;
    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChiTietHoaDon> chiTietHoaDons;
}
