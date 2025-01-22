package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ChiTietHoaDon")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class ChiTietHoaDon {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Khóa chính tự động tăng
//    private Long id;
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_hoa_don", nullable = false)
    @EqualsAndHashCode.Include
    private HoaDon hoaDon;

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
