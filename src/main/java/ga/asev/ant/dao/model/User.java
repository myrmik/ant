package ga.asev.ant.dao.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {
    private Long id;
    private String name;
}
