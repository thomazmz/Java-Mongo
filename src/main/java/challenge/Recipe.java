package challenge;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Document(collection = "recipe")
public class Recipe implements Serializable {

    @Id
    private String id;

    private String title;

    private String description;

    private List<String> ingredients;

    private List<String> likes;

    private List<RecipeComment> comments;

    // Constructors

    public Recipe(String title, String description, List<String> ingredients) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
    }

    public Recipe() {
    }

    // Getters

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public List<String> getIngredients() {
        return this.ingredients;
    }

    public List<String> getLikes() {
        return this.likes;
    }

    public List<RecipeComment> getComments() {
        return this.comments;
    }

//    public List<RecipeComment> getCommentsAsList() {
//        List<RecipeComment> comments = new ArrayList<RecipeComment>(this.comments.values());
//        return comments;
//    }

    // Setters

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public void setComments(List<RecipeComment> comments) {
        this.comments = comments;
    }

    // Methods

    public void addLike (String userId) {
        if(this.likes != null) {
            this.likes.add(userId);
        } else {
            this.setLikes(Arrays.asList(userId));
        }
    }

    public void removeLike (String userId) {
        this.likes.remove(userId);
    }

    public void addRecipeComment (RecipeComment recipeComment) {
        if(this.comments != null) {
            this.comments.add(recipeComment);
        } else {
            this.setComments(Arrays.asList(recipeComment));
        }
    }

    public void removeRecipeComment (String commentId) {
        this.comments.removeIf(obj -> obj.getId() == commentId);
    }

}