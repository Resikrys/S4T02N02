package cat.itacademy.s04.t02.n02.mapper;

import cat.itacademy.s04.t02.n02.dto.FruitRequest;
import cat.itacademy.s04.t02.n02.dto.FruitResponse;
import cat.itacademy.s04.t02.n02.models.Fruit;

public final class FruitMapper {
    private FruitMapper() {}

    public static Fruit fromRequest(FruitRequest req) {
        if (req == null) return null;
        return new Fruit((Long) null, req.name(), req.quantityKilos());
    }

    public static FruitResponse toResponse(Fruit f) {
        if (f == null) return null;
        return new FruitResponse(f.getId(), f.getName(), f.getQuantityKilos());
    }
}