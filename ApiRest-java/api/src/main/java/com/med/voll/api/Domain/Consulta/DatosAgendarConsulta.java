package com.med.voll.api.Domain.Consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.med.voll.api.Domain.Medico.Especialidad;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record DatosAgendarConsulta(
        @NotNull
        Long idPaciente,
        Long idMedico,
        @NotNull
        @Future
        LocalDateTime fecha,
        Especialidad especialidad
) {
}
