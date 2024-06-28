package com.med.voll.api.Domain.Medico;

import com.med.voll.api.Domain.Direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatoRegistroMedico(
       @NotBlank(message = "{nombre.obligatorio}")
        String nombre,
        @NotBlank(message = "{email.obligatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank(message = "{phone.obligatorio}")
        String telefono,
        @NotBlank(message ="{docuemento.obligatorio}")
        @Pattern(regexp = "\\d{4,6}", message = "{docuemento.invalido}")
        String documento,
        @NotNull(message = "{especialidad.obligatorio}")
        Especialidad especialidad,
        @NotNull (message = "{address.obligatorio}")
        @Valid DatosDireccion direccion        ) {
}
