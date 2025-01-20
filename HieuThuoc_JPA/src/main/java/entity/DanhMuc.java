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
@Table(name = "danh_muc")
public class DanhMuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "danhMuc_id", nullable = false, unique = true)
    private String id;

    @Column(columnDefinition = "varchar(45)", unique = true, nullable = false)
    private String ten;

    @Column(columnDefinition = "varchar(45)",nullable = false)
    private String viTriKe;

    @ToString.Exclude
    @OneToMany( mappedBy = "danhMuc")
    private Set<Thuoc> thuoc;
}
