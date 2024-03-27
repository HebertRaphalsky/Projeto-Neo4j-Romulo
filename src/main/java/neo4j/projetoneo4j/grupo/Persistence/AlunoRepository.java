package neo4j.projetoneo4j.grupo.Persistence;

import org.springframework.data.neo4j.repository.Neo4jRepository;

 import neo4j.projetoneo4j.grupo.Model.Aluno;



public interface AlunoRepository extends Neo4jRepository<Aluno, String> {
    
}
