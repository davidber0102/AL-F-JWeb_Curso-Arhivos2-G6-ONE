package com.med.voll.api.Domain.Paciente;

import com.med.voll.api.Domain.Direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroPaciente(
        @NotBlank String nombre,
        @NotBlank String email,
        @NotBlank @Pattern(regexp = "\\d{4,6}") String documento_identidad,
        @NotBlank String telefono,
        @NotNull @Valid DatosDireccion direccion
) {
}
