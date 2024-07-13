CREATE TABLE respuestas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje VARCHAR(255) NOT NULL,
    topico BIGINT,
    fecha_creacion DATETIME NOT NULL,
    autor BIGINT,
    solucion BOOLEAN NOT NULL,
    activo BOOLEAN NOT NULL,
    FOREIGN KEY (autor) REFERENCES usuarios(id),
    FOREIGN KEY (topico) REFERENCES topicos(id)
);
