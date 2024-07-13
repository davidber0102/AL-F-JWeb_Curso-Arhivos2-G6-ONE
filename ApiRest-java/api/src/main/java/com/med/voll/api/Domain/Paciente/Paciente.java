package com.med.voll.api.Domain.Paciente;

import com.med.voll.api.Domain.Direccion.Direccion;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento_identidad;
    @Embedded
    private Direccion direccion;
    private Boolean activo;

    public Paciente(DatosRegistroPaciente datos) {
        this.activo = true;
        this.nombre = datos.nombre();
        this.email= datos.email();
        this.telefono = datos.telefono();
        this.documento_identidad = datos.documento_identidad();
        this.direccion = new Direccion(datos.direccion());
    }

    public void atualizarInformacion(DatosActualizacionPaciente datos) {
        if (datos.nombre() != null){
            this.nombre = datos.nombre();        }
        if (datos.telefono() != null) {
            this.telefono = datos.telefono();        }
        if (datos.direccion() != null) {
            direccion.actualizarDatos(datos.direccion());        }
    }

    public void inactivar() {        this.activo = false;    }
}
