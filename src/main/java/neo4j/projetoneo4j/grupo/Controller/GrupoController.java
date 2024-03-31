package neo4j.projetoneo4j.grupo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import neo4j.projetoneo4j.grupo.Model.Grupo;
import neo4j.projetoneo4j.grupo.Persistence.GrupoRepository;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/grupo")
@AllArgsConstructor
public class GrupoController {


 private final GrupoRepository grupoRepository;

@Operation(summary = "Lista de Grupos", description = "Retorna a lista de Grupos ", tags = {
      "Grupos" }, responses = {
          @ApiResponse(responseCode = "200", description = "Get ok")
      }, method = "GET")
 @GetMapping
 public List<Grupo> getGrupos() {
  return grupoRepository.findGrupos();
 }

 @Operation(summary = "Grupo", description = "Retorna um Grupo - busca pelo seu ID", tags = { "Grupos" }, responses = {
      @ApiResponse(responseCode = "200", description = "Get ok")
  }, method = "GET", parameters = {
      @Parameter(name = "id", example = "sas5a4s5-s4s54as-sa54sa", allowEmptyValue = false, in = ParameterIn.PATH)
  })
 @GetMapping("/{id}")
 public Grupo getGrupo(@PathVariable(name = "id", required = true) String id) {
  Grupo grupo = grupoRepository.findOneById(id);
  return grupo;
 }


 @Operation(summary = "Insere Grupo", description = "Cadastra um Grupo e retorna seus dados", tags = {
  "Grupos" }, responses = {
      @ApiResponse(responseCode = "200", description = "Inserção ok")
  }, method = "POST")
 @PostMapping
 public Grupo saveGrupo(@RequestBody Grupo grupo) {
  return grupoRepository.save(grupo);
 }

 @Operation(summary = "Altera Grupo", description = "Altera dados de um Grupo e retorna seus dados", tags = {
  "Grupos" }, responses = {
      @ApiResponse(responseCode = "200", description = "Alteração ok")
  }, method = "PUT")
 @PutMapping
public Grupo updGrupo(@RequestBody Grupo grupo) {
  return grupoRepository.save(grupo);
 }

 @Operation(summary = "Deleta Grupo", description = "Exclui um Grupo da base. Recebe seu id para efetuar a exclusão", tags = {
  "Grupos" }, responses = {
      @ApiResponse(responseCode = "200", description = "Exclusão ok")
  }, method = "DELETE")
 @DeleteMapping("/{id}")
 public Grupo deleteGrupo(@PathVariable(name = "id", required = true) String id) {
  Grupo grupo = grupoRepository.findOneById(id);
  if (grupo != null)
   grupoRepository.delete(grupo);
  return grupo;
 }
}

