package neo4j.projetoneo4j.grupo.Controller;


import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import neo4j.projetoneo4j.grupo.Model.Aluno;
import neo4j.projetoneo4j.grupo.Model.Grupo;
import neo4j.projetoneo4j.grupo.Persistence.AlunoRepository;
import neo4j.projetoneo4j.grupo.Persistence.GrupoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/aluno")
@AllArgsConstructor
public class AlunoController {
  private final AlunoRepository alunoRepository;
  private final GrupoRepository grupoRepository;


  @Operation(summary = "Lista de Alunos", description = "Retorna a lista de Alunos ordenada por nome", tags = {
      "Alunos" }, responses = {
          @ApiResponse(responseCode = "200", description = "Get ok")
      }, method = "GET")
  @GetMapping
  public List<Aluno> getAlunos() {
    return alunoRepository.findAll(Sort.by("nome"));
  }


  @Operation(summary = "Aluno", description = "Retorna um Aluno - busca pelo seu ID", tags = { "Alunos" }, responses = {
      @ApiResponse(responseCode = "200", description = "Get ok")
  }, method = "GET", parameters = {
      @Parameter(name = "id", example = "sas5a4s5-s4s54as-sa54sa", allowEmptyValue = false, in = ParameterIn.PATH)
  })
  @GetMapping("/{id}")
  public Aluno getAluno(@PathVariable(name = "id", required = true) String id) {
    Optional<Aluno> oAluno = alunoRepository.findById(id);
    Aluno aluno = null;
    if (oAluno.isPresent())
      aluno = oAluno.get();
    return aluno;
  }


  @Operation(summary = "Lista de Grupos", description = "Retorna a lista de Grupos que um determinado aluno atua. Recebe o id do Aluno", tags = {
    "Alunos" }, responses = {
        @ApiResponse(responseCode = "200", description = "Get ok")
    }, method = "GET", parameters = {
      @Parameter(name = "id", example = "sas5a4s5-s4s54as-sa54sa", allowEmptyValue = false, in = ParameterIn.PATH)
  })
  @GetMapping("/participa/{id}")
  public Set<Grupo> getParticipa(@PathVariable(name = "id", required = true) String id) {
    Optional<Aluno> oAluno = alunoRepository.findById(id);
    if (oAluno.isPresent())
      return oAluno.get().getGrupos();
    return null;
  }


  @Operation(summary = "Insere aluno", description = "Cadastra um aluno e retorna seus dados", tags = {
    "Alunos" }, responses = {
        @ApiResponse(responseCode = "200", description = "Inserção ok")
    }, method = "POST")
  @PostMapping
  public Aluno saveAluno(@RequestBody Aluno aluno) {
    return alunoRepository.save(aluno);
  }


  @Operation(summary = "Vincula Grupo ", description = "Recebe um id de aluno e um id de grupo indicando a atuação do aluno naquele determinado filme", tags = {
    "Alunos" }, responses = {
        @ApiResponse(responseCode = "200", description = "Atuação ok")
    }, method = "POST")
  @PostMapping("/{id}/participam/{idGrupo}")
  public Aluno participam(@PathVariable(name = "id", required = true) String id,
      @PathVariable(name = "idGrupo", required = true) String idGrupo) {
    Aluno aluno = alunoRepository.findById(id).get();
    Grupo grupo = grupoRepository.findById(idGrupo).get();
    if (aluno != null && grupo != null) {
      aluno.getGrupos().add(grupo);
    }
    return alunoRepository.save(aluno);
  }


  @Operation(summary = "Altera aluno", description = "Altera dados de um aluno e retorna seus dados", tags = {
    "Alunos" }, responses = {
        @ApiResponse(responseCode = "200", description = "Alteração ok")
    }, method = "PUT")
  @PutMapping
  public Aluno updAluno(@RequestBody Aluno aluno) {
    return alunoRepository.save(aluno);
  }


  @Operation(summary = "Deleta aluno", description = "Exclui um aluno da base. Recebe seu id para efetuar a exclusão", tags = {
    "Alunos" }, responses = {
        @ApiResponse(responseCode = "200", description = "Exclusão ok")
    }, method = "DELETE")
  @DeleteMapping("/{id}")
  public Aluno deleteAluno(@PathVariable(name = "id", required = true) String id) {
    Aluno aluno = alunoRepository.findById(id).get();
    if (aluno != null)
      alunoRepository.delete(aluno);
    return aluno;
  }
}

