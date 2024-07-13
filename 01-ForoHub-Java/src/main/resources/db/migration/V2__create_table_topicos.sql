CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    estado ENUM('NO_RESPONDIDO', 'NO_SOLUCIONADO', 'SOLUCIONADO', 'CERRADO') NOT NULL,
    usuario_id BIGINT,
    curso VARCHAR(255) NOT NULL,
    activo BOOLEAN NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);