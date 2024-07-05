package com.med.voll.api.Domain.Consulta.Validaciones;

import com.med.voll.api.Domain.Consulta.DatosAgendarConsulta;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas {
    public void validar(DatosAgendarConsulta datos){
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDEApertura = datos.fecha().getHour()<7;
        var despuesDeCierre= datos.fecha().getHour()>19;

        if(domingo || antesDEApertura || despuesDeCierre){
            throw new ValidationException("Horario de atención de la clínica es de lunes- sábado, en horario de 07:00 a 19:00.");
        }
    }
}
