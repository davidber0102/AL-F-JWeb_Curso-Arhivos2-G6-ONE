package com.med.voll.api.Controller;

import com.med.voll.api.Domain.Consulta.AgendaDeConsultaService;
import com.med.voll.api.Domain.Consulta.DatosAgendarConsulta;
import com.med.voll.api.Infra.Errores.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
@SuppressWarnings("all")
public class ConsultaController {
    @Autowired
    private AgendaDeConsultaService service;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra una consulta en la base de datos",
            description = "",
            tags = { "consulta", "post" })
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datosConsulta)  throws ValidacionDeIntegridad {
        System.out.println(datosConsulta);
        var response= service.agendar(datosConsulta);
        return ResponseEntity.ok(response);
    }
  /*  public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datosConsulta){
        service.cancelar(datosConsulta);
        return  ResponseEntity.noContent().build();    }*/
}
