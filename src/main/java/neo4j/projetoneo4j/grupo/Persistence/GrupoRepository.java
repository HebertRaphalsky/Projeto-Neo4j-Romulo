package neo4j.projetoneo4j.grupo.Persistence;


import java.util.List;


import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;


 import neo4j.projetoneo4j.grupo.Model.Grupo;


public interface GrupoRepository extends Neo4jRepository<Grupo, String> {
 List<Grupo> findFirst10By();
 
 /*
 //uma opção de usar query para neo4j
 @Query("MATCH (f:Filme) WHERE ElementId(f) = $id RETURN f")
 Filme findOneById(@Param("id") String id);*/
 //outra opção de usar query para neo4j
 @Query("MATCH (f:Grupo) WHERE ElementId(f) = :#{#id} RETURN f")
 Grupo findOneById(String id);


 @Query("MATCH (f:Grupo) RETURN f;")
 List<Grupo> findGrupos();


 @Query("MATCH (f:Grupo)\n" + //
   " RETURN count(f)")
 Long getTotalGrupos();
}

