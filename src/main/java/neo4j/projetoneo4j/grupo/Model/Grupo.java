package neo4j.projetoneo4j.grupo.Model;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data

@NoArgsConstructor
@Node("Grupo")
public class Grupo {
 @Id
 @GeneratedValue
 private String id;
 @NonNull
 private String nome;
 private Integer NmMembros;
}
