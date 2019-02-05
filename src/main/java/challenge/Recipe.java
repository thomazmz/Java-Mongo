package challenge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Document(collection = "Recipe")
public class Recipe implements Serializable {

    @Id
    private String id;

    private String title;

    private String description;

    private List<String> ingredients;

    private List<String> likes = new ArrayList<>();

    private LinkedHashMap<String, RecipeComment> recipeComments = new LinkedHashMap<>();

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

    @JsonIgnore
    public List<String> getLikes() {
        return this.likes;
    }

    @JsonIgnore
    public LinkedHashMap<String, RecipeComment> getRecipeComments() {
        return this.recipeComments;
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

    // Methods

    public void addLike (String userId) {
        this.likes.add(userId);
    }

    public void removeLike (String userId) {
        this.likes.remove(userId);
    }

    public void addRecipeComment (RecipeComment recipeComment) {
        this.recipeComments.put(recipeComment.getId(), recipeComment);
    }

    public void removeRecipeComment (String commentId) {
        this.recipeComments.remove(commentId);
    }

}