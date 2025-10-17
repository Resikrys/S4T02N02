package cat.itacademy.s04.t02.n02.controllers;

import cat.itacademy.s04.t02.n02.dto.FruitRequest;
import cat.itacademy.s04.t02.n02.dto.FruitResponse;
import cat.itacademy.s04.t02.n02.models.Fruit;
import cat.itacademy.s04.t02.n02.dto.FruitDTO;
import cat.itacademy.s04.t02.n02.services.FruitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/fruits-api")
public class FruitController {

    private final FruitService service;

    public FruitController(FruitService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FruitResponse> create(@Valid @RequestBody FruitRequest request) {
        FruitResponse created = service.createFruit(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id()) //record accessor (instead of getId)
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FruitResponse> getById(@PathVariable Long id) {
        FruitResponse resp = service.findById(id);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FruitResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FruitResponse> update(@PathVariable Long id, @Valid @RequestBody FruitRequest request) {
        FruitResponse updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping
//    public ResponseEntity<Fruit> createFruit(@RequestBody FruitDTO fruitDTO) {
//        Fruit newFruit = fruitService.createFruit(fruitDTO);
//        return new ResponseEntity<>(newFruit, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Fruit> getOneFruit(@PathVariable int id) {
//        Fruit fruit = fruitService.getOneFruit(id);
//        return ResponseEntity.ok(fruit);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Fruit>> getAllFruits() {
//        List<Fruit> fruits = fruitService.getAllFruits();
//        return ResponseEntity.ok(fruits);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Fruit> updateFruit(@PathVariable int id, @RequestBody FruitDTO fruitDTO) {
//        Fruit updatedFruit = fruitService.updateFruit(id, fruitDTO);
//        return ResponseEntity.ok(updatedFruit);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteFruit(@PathVariable int id) {
//        fruitService.deleteFruit(id);
//        return ResponseEntity.noContent().build();
//    }

}
