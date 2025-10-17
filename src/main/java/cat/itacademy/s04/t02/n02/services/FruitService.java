package cat.itacademy.s04.t02.n02.services;

import cat.itacademy.s04.t02.n02.dto.FruitRequest;
import cat.itacademy.s04.t02.n02.dto.FruitResponse;
import cat.itacademy.s04.t02.n02.exceptions.DuplicateFruitException;
import cat.itacademy.s04.t02.n02.exceptions.NotFoundException;
import cat.itacademy.s04.t02.n02.mapper.FruitMapper;
import cat.itacademy.s04.t02.n02.models.Fruit;
import cat.itacademy.s04.t02.n02.repository.FruitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {

    private final FruitRepository repository;

    public FruitService(FruitRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public FruitResponse createFruit(FruitRequest request) {
        if (repository.existsByNameIgnoreCase(request.name())) {
            throw new DuplicateFruitException("Fruit with name '" + request.name() + "' already exists");
        }
        Fruit toSave = FruitMapper.fromRequest(request);
        Fruit saved = repository.save(toSave);
        return FruitMapper.toResponse(saved);
    }

    public FruitResponse findById(Long id) {
        Fruit f = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fruit with id " + id + " not found"));
        return FruitMapper.toResponse(f);
    }

    public List<FruitResponse> findAll() {
        return repository.findAll().stream().map(FruitMapper::toResponse).toList();
    }

    @Transactional
    public FruitResponse update(Long id, FruitRequest request) {
        Fruit existing = repository.findById(id).orElseThrow(() -> new NotFoundException("Fruit with id " + id + " not found"));
        if (!existing.getName().equalsIgnoreCase(request.name()) && repository.existsByNameIgnoreCase(request.name())) {
            throw new DuplicateFruitException("Another fruit with name '" + request.name() + "' already exists");
        }
        existing.setName(request.name());
        existing.setQuantityKilos(request.quantityKilos());
        Fruit updated = repository.save(existing);
        return FruitMapper.toResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Fruit with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
