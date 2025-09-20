package cat.itacademy.s04.t02.n02.controllers;

import cat.itacademy.s04.t02.n02.models.Fruit;
import cat.itacademy.s04.t02.n02.models.FruitDTO;
import cat.itacademy.s04.t02.n02.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruits-api")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    @PostMapping
    public ResponseEntity<Fruit> createFruit(@RequestBody FruitDTO fruitDTO) {
        Fruit newFruit = fruitService.createFruit(fruitDTO);
        return new ResponseEntity<>(newFruit, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fruit> getOneFruit(@PathVariable int id) {
        Fruit fruit = fruitService.getOneFruit(id);
        return ResponseEntity.ok(fruit);
    }

    @GetMapping
    public ResponseEntity<List<Fruit>> getAllFruits() {
        List<Fruit> fruits = fruitService.getAllFruits();
        return ResponseEntity.ok(fruits);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fruit> updateFruit(@PathVariable int id, @RequestBody FruitDTO fruitDTO) {
        Fruit updatedFruit = fruitService.updateFruit(id, fruitDTO);
        return ResponseEntity.ok(updatedFruit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFruit(@PathVariable int id) {
        fruitService.deleteFruit(id);
        return ResponseEntity.noContent().build();
    }

}
