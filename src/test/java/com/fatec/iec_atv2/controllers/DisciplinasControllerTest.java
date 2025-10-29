package com.fatec.iec_atv2.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DisciplinasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornarTodasAsDisciplinas() throws Exception {
        mockMvc.perform(get("/disciplinas")).andExpect(status().isOk());
    }

    @Test
    void deveRetornarUmaUnicaDisciplina() throws Exception {
        mockMvc.perform(get("/disciplinas/1")).andExpect(status().isOk()).andExpect(content().string("Integração e Entrega Contínua"));
    }

    @Test
    void deveAdicionarDisciplinasComSucesso() throws Exception {
        String json = """
                {
                  "codigo": "3",
                  "nome": "Laboratório de Desenvolvimento Web"
                }
                """;

        mockMvc.perform(post("/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Disciplina adicionada com sucesso!"));
    }


}
