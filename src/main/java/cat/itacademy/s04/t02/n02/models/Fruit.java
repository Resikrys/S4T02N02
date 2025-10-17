package cat.itacademy.s04.t02.n02.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fruits", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // CAMBIO: Long

    @Column(nullable = false)
    private String name;

    @Column(name = "quantity_kilos", nullable = false)
    private Integer quantityKilos;

}
