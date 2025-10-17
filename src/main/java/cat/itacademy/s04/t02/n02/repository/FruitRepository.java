package cat.itacademy.s04.t02.n02.repository;

import cat.itacademy.s04.t02.n02.models.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
    boolean existsByNameIgnoreCase(String name);
}
