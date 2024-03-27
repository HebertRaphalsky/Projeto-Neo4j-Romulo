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


import neo4j.projetoneo4j.grupo.Model.Grupo;
import neo4j.projetoneo4j.grupo.Persistence.GrupoRepository;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/grupo")
@AllArgsConstructor
public class GrupoController {


 private final GrupoRepository grupoRepository;


 @GetMapping
 public List<Grupo> getGrupos() {
  return grupoRepository.findGrupos();
 }


 @GetMapping("/{id}")
 public Grupo getGrupo(@PathVariable(name = "id", required = true) String id) {
  Grupo grupo = grupoRepository.findOneById(id);
  return grupo;
 }


 @PostMapping
 public Grupo saveGrupo(@RequestBody Grupo grupo) {
  return grupoRepository.save(grupo);
 }


 @PutMapping
public Grupo updGrupo(@RequestBody Grupo grupo) {
  return grupoRepository.save(grupo);
 }


 @DeleteMapping("/{id}")
 public Grupo deleteGrupo(@PathVariable(name = "id", required = true) String id) {
  Grupo grupo = grupoRepository.findOneById(id);
  if (grupo != null)
   grupoRepository.delete(grupo);
  return grupo;
 }
}

