package cat.itacademy.s04.t02.n02.models;

import java.time.OffsetDateTime;

public record ErrorDTO(String message, String path, OffsetDateTime timestamp, int status) {}