package challenge;

import java.util.ArrayList;
import java.util.List;

public class RecipeDto {

    private String title;

    private String description;

    private List<String> ingredients;

    private List<String> likes = new ArrayList<>();

    private List<RecipeComment> recipeComments = new ArrayList<>();

    //Constructors

    public RecipeDto() {
    }

    // Getters

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getLikes() {
        return likes;
    }

    public List<RecipeComment> getRecipeComments() {
        return this.recipeComments;
    }

    // Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public void setComments(List<RecipeComment> recipeComments) {
        this.recipeComments = recipeComments;
    }

}