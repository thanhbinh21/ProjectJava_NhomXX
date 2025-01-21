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
@Table(name = "DanhMuc")
public class DanhMuc {

    @Id
    @Column(name = "danhMuc_id",columnDefinition = "varchar(45)", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(columnDefinition = "varchar(45)", unique = true, nullable = false)
    private String ten;

    @Column(columnDefinition = "varchar(45)",nullable = false)
    private String viTriKe;

    @ToString.Exclude
    @OneToMany( mappedBy = "danhMuc")
    private Set<Thuoc> thuoc;
}
