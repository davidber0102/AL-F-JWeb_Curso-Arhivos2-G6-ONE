package com.med.voll.api.Domain.Consulta.Validaciones;

import com.med.voll.api.Domain.Consulta.DatosAgendarConsulta;
import com.med.voll.api.Domain.Medico.MedicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoActivo implements ValidadorDeConsultas {
    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosAgendarConsulta datos){
        if(datos.idMedico() == null){
            return;        }

        var medicoActivo = medicoRepository.findActivoById(datos.idMedico());

        if(!medicoActivo){
            throw new ValidationException("No se puede permitir programar citas a Medicos inactivos en el sistema");
        }
    }
}
