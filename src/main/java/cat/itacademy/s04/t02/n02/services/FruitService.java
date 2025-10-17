package cat.itacademy.s04.t02.n02.services;

import cat.itacademy.s04.t02.n02.exceptions.NotFoundException;
import cat.itacademy.s04.t02.n02.models.Fruit;
import cat.itacademy.s04.t02.n02.dto.FruitDTO;
import cat.itacademy.s04.t02.n02.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {

    @Autowired
    private FruitRepository fruitRepository;

    public Fruit createFruit(FruitDTO fruitDTO) {
        Fruit fruit = new Fruit(fruitDTO.getName(), fruitDTO.getQuantityKilos());
        return fruitRepository.save(fruit);
    }

    public Fruit getOneFruit(int id) {
        return fruitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fruit with id " + id + " not found."));
    }

    public List<Fruit> getAllFruits() {
        return fruitRepository.findAll();
    }

    public Fruit updateFruit(int id, FruitDTO fruitDTO) {
        Fruit existingFruit = getOneFruit(id);
        existingFruit.setName(fruitDTO.getName());
        existingFruit.setQuantityKilos(fruitDTO.getQuantityKilos());
        return fruitRepository.save(existingFruit);
    }

    public void deleteFruit(int id) {
        Fruit fruit = getOneFruit(id);
        fruitRepository.delete(fruit);
    }

}
