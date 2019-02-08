package challenge;

import org.springframework.stereotype.Component;

@Component
public class RecipeFactory {

    public RecipeDto getInstance(Recipe recipe) {

        RecipeDto recipeDto = new RecipeDto();

        recipeDto.setTitle(recipe.getTitle());
        recipeDto.setDescription(recipe.getDescription());
        recipeDto.setIngredients(recipe.getIngredients());
        recipeDto.setLikes(recipe.getLikes());
        recipeDto.setComments(recipe.getComments());

        return recipeDto;

    }

    public Recipe getInstance(RecipeDto recipeDto) {

        Recipe recipe = new Recipe();

        recipe.setTitle(recipeDto.getTitle());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setIngredients(recipeDto.getIngredients());
        recipe.setLikes(recipeDto.getLikes());

        return recipe;

    }

}
