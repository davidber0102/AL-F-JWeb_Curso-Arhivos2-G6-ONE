package com.med.voll.api.Controller;

import com.med.voll.api.Medico.DatoRegistroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void registarMedicos(@RequestBody DatoRegistroMedico datosregistroMedico) {
        System.out.println("El requeste llego correctamente");
        System.out.println(datosregistroMedico);

    }
}
