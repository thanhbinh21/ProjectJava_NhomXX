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
@Table(name = "NhaSanXuat")
public class NhaSanXuat {

    @Id
    @Column(name = "ma_nha_san_xuat",columnDefinition = "varchar(45)")
    private String id;

    @Column(columnDefinition = "varchar(45)",name = "ten_nha_san_xuat", nullable = false)
    private String ten;

    @ToString.Exclude
    @OneToMany( mappedBy = "nhaSanXuat")
    private Set<Thuoc> thuoc;
}
