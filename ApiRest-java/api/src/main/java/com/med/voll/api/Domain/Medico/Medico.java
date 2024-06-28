package com.med.voll.api.Domain.Medico;

import com.med.voll.api.Domain.Direccion.Direccion;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
   private String documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    //@Column(name = "especialidad")
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatoRegistroMedico datosRegistroMedico) {
        this.activo = true;
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.documento = datosRegistroMedico.documento();
        this.telefono = datosRegistroMedico.telefono();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());    }

    public void actualizarDatos(DatosActualizarMedico datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();        }
        if (datos.documento() != null) {
            this.documento = datos.documento();        }
        if(datos.direccion() != null) {
            this.direccion.actualizarDatos(datos.direccion());        }
    }
    public void desactivarMedico() {        this.activo = false;    }
}
