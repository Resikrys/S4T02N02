package cat.itacademy.s04.t02.n02.services;

import cat.itacademy.s04.t02.n02.dto.FruitRequest;
import cat.itacademy.s04.t02.n02.exceptions.DuplicateFruitException;
import cat.itacademy.s04.t02.n02.exceptions.NotFoundException;
import cat.itacademy.s04.t02.n02.models.Fruit;
import cat.itacademy.s04.t02.n02.repository.FruitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FruitServiceTest {

    @Mock
    private FruitRepository repository;

    @InjectMocks
    private FruitService service;

    @Test
    void createFruit_throwsDuplicateFruitException_whenExistsByName() {
        when(repository.existsByNameIgnoreCase("Apple")).thenReturn(true);
        var req = new FruitRequest("Apple", 10);

        assertThrows(DuplicateFruitException.class, () -> service.createFruit(req));
    }

    @Test
    void findById_throwsNotFound_whenMissing() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void update_throwsDuplicate_whenNameExists() {
        Fruit existing = new Fruit(1L, "Orange", 4);
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.existsByNameIgnoreCase("Apple")).thenReturn(true);

        var req = new FruitRequest("Apple", 10);
        assertThrows(DuplicateFruitException.class, () -> service.update(1L, req));
    }

    @Test
    void delete_throwsNotFound_whenIdDoesNotExist() {
        when(repository.existsById(99L)).thenReturn(false);
        assertThrows(NotFoundException.class, () -> service.delete(99L));
    }
}
