package com.fatec.iec_atv2.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/disciplinas")
@Tag(name = "Disciplinas", description = "Gerenciamento de disciplinas")
public class DisciplinasController {

    private final HashMap<Integer, String> disciplinas = new HashMap<>(Map.of(1, "Integração e Entrega Contínua", 2, "Programação de Dispositivos Mobile"));

    @Operation(summary = "Adiciona uma nova disciplina", description = "Recebe um JSON com código e nome e adiciona ao repositório em memória.")
    @ApiResponse(responseCode = "200", description = "Disciplina adicionada com sucesso!", content = @Content(mediaType = "text/plain"))
    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    @PostMapping
    public ResponseEntity<String> adicionarDisciplina(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto JSON contendo 'codigo' e 'nome'", required = true, content = @Content(schema = @Schema(example = "{\"codigo\": \"3\", \"nome\": \"Banco de Dados\"}"))) @RequestBody Map<String, String> requestDisciplinas) {

        Integer codigo = Integer.parseInt(requestDisciplinas.get("codigo"));
        String nome = requestDisciplinas.get("nome");
        disciplinas.put(codigo, nome);
        return ResponseEntity.ok("Disciplina adicionada com sucesso!");
    }

    @Operation(summary = "Lista todas as disciplinas", description = "Retorna todas as disciplinas registradas.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<String> getDisciplinas() {
        return ResponseEntity.ok(disciplinas.toString());
    }

    @Operation(summary = "Busca disciplina pelo ID", description = "Retorna o nome da disciplina associada ao código informado.")
    @ApiResponse(responseCode = "200", description = "Disciplina encontrada")
    @ApiResponse(responseCode = "404", description = "Disciplina não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<String> getDisciplinasId(@Parameter(description = "Código da disciplina", example = "1") @PathVariable Integer id) {

        String disciplina = disciplinas.get(id);
        if (disciplina == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(disciplina);
    }
}
