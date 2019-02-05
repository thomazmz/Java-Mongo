package challenge;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "RecipeComment")
public class RecipeComment implements Serializable {

    // Properties

    @Id
    private String id;

    private String comment;

    // Constructors

    public RecipeComment(String commment) {
        this.comment = commment;
    }

    public RecipeComment() {
    }

    // Getters

    public String getComment() {
        return comment;
    }

    public String getId() {
        return this.id;
    }

    // Setters

    public void setComment(String comment) {
        this.comment = comment;
    }

}
