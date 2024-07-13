package com.med.voll.api.Domain.Paciente;

import com.med.voll.api.Domain.Direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record DatosRegistroPaciente(
        @NotBlank String nombre,
        @NotBlank @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") String documento_identidad,
        @NotBlank @Size(min = 0, max = 15) String telefono,
        @NotNull @Valid DatosDireccion direccion) {
}
