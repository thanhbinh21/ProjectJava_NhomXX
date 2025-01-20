package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Table(name = "khuyen_mai")
public class KhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "khuyenMai_id", nullable = false, unique = true)
    private String id;

    @Column(columnDefinition = "varchar(45)", unique = true, nullable = false)
    private String ten;

    @Column(columnDefinition = "DECIMAL(10,2)", nullable = false)
    private double phanTramGiamGia;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate thoiGianBatDau;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate thoiGianKetThuc;


    @ToString.Exclude
    @OneToMany( mappedBy = "khuyenMai")
    private Set<Thuoc> thuoc;


}
