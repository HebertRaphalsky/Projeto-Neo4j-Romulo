package neo4j.projetoneo4j.grupo.Model;

import java.util.HashSet;
import java.util.Set;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;


import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@Node("Aluno")
public class Aluno {
 @Id
 @GeneratedValue
 private String id;
 @NonNull
 private String nome;
 @JsonIgnore
 @Relationship(type = "Pertence_ao_Grupo", direction = Direction.OUTGOING)
 private Set<Grupo> grupos = new HashSet<>();
}


