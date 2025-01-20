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
@Table(name = "nha_san_xuat")
public class NhaSanXuat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nhaSanXuat_id", nullable = false, unique = true)
    private String id;

    @Column(columnDefinition = "varchar(45)", unique = true, nullable = false)
    private String ten;

    @ToString.Exclude
    @OneToMany( mappedBy = "nhaSanXuat")
    private Set<Thuoc> thuoc;
}
