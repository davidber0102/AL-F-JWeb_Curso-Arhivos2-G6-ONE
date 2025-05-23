package com.med.voll.api.Controller;

import com.med.voll.api.Domain.Consulta.AgendaDeConsultaService;
import com.med.voll.api.Domain.Consulta.DatosAgendarConsulta;
import com.med.voll.api.Domain.Consulta.DatosDetalleConsulta;
import com.med.voll.api.Domain.Medico.Especialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
@SuppressWarnings("all")
class ConsultaControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosAgendarConsulta> agendarConsultaJacksonTester;

    @Autowired
    private JacksonTester<DatosDetalleConsulta> detalleConsultaJacksonTester;

    @Autowired
    private AgendaDeConsultaService agendaDeConsultaService;

    @Test
    @DisplayName("deberia retornar estado htto 400 cuando los datos ingresados son invalidos")
    @WithMockUser
    void agendarEscenario1() throws Exception {
        //given    // when
      var response =  mvc.perform(post("/consultas")).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("deberia retornar estado htto 200 cuando los datos ingresados son validos")
    @WithMockUser
    void agendarEscenario2() throws Exception {
        //given    // when
        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.GINECOLOGIA;
        var datos =new DatosDetalleConsulta(null, 2l, 5l, fecha);

        //when
        when(agendaDeConsultaService.agendar(any())).thenReturn(datos);

        var response =  mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(agendarConsultaJacksonTester.write(new DatosAgendarConsulta(2l,  5l, fecha, especialidad)).getJson())
        ) .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = detalleConsultaJacksonTester.write(datos).getJson();

        assertThat(response).isEqualTo(jsonEsperado);
    }
}