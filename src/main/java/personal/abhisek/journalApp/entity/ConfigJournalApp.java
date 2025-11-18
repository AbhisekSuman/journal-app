package personal.abhisek.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("config_journal_app")
@Data
@Getter
@Setter
public class ConfigJournalApp {
    @Id
    private ObjectId id;

    @Field
    private String key;

    @Field
    private String value;
}
