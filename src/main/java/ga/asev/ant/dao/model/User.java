package ga.asev.ant.dao.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {
    private String id;
    private String name;
}
