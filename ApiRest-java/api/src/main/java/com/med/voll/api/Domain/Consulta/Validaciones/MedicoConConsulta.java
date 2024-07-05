package com.med.voll.api.Domain.Consulta.Validaciones;

import com.med.voll.api.Domain.Consulta.ConsultaRepository;
import com.med.voll.api.Domain.Consulta.DatosAgendarConsulta;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datos){
        if(datos.idMedico()== null)
            return;

        var medicoConConsulta = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());

        if (medicoConConsulta){
            throw new ValidationException("Este Medico ya tiene registrada una consulta para ese mismo horario y dia");
        }
    }
}
