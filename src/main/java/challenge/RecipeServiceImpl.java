package challenge;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class RecipeServiceImpl implements RecipeService {

	// Autowired properties

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	RecipeCommentService recipeCommentService;

	// Service Methods

//	@Override
//	public Recipe save(Recipe recipe) {
//
//		return recipeRepository.save(recipe);
//
//	}

	@Override
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public Recipe get(String id) {

		return recipeRepository.findById(id).orElse(null);

	}

	@Override
	public void update(String id, Recipe recipe) {

		Optional<Recipe> recipeInstance = recipeRepository.findById(id);

		if(recipeInstance.isPresent()){
			Recipe updatedRecipe = recipeInstance.get();
			updatedRecipe.setTitle(recipe.getTitle());
			updatedRecipe.setDescription(recipe.getDescription());
			updatedRecipe.setIngredients(recipe.getIngredients());
			recipeRepository.save(updatedRecipe);
		}

	}

	@Override
	public void delete(String id) {

		Recipe recipeInstance = recipeRepository.findById(id).orElse(null);

		if (recipeInstance != null) recipeRepository.delete(recipeInstance);

	}

	@Override
	public void like(String id, String userId) {

		Recipe recipeInstance = recipeRepository.findById(id).orElse(null);

		if(recipeInstance != null) {
			recipeInstance.addLike(userId);
			recipeRepository.save(recipeInstance);
		}
	}

	@Override
	public void unlike(String id, String userId) {

		Recipe recipeInstance = recipeRepository.findById(id).orElse(null);

		if (recipeInstance != null) {
			recipeInstance.removeLike(userId);
			recipeRepository.save(recipeInstance);
		}
	}

	@Override
	public RecipeComment addComment(String id, RecipeComment recipeComment) {

		Recipe recipeInstance = recipeRepository.findById(id).orElse(null);

		if (recipeInstance != null) {
			String idRecipeComment = ObjectId.get().toHexString();
			recipeComment.setId(idRecipeComment);
			recipeInstance.addRecipeComment(recipeComment);
			recipeRepository.save(recipeInstance);
			return recipeComment;

		}

		return null;

	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment recipeComment) {

		Recipe recipeInstance = recipeRepository.findById(id).orElse(null);

		if (recipeInstance != null) {
			RecipeComment recipeCommentInstance = recipeCommentService.update(commentId, recipeComment);

			if (recipeCommentInstance != null) {
				recipeInstance.addRecipeComment(recipeCommentInstance);
				recipeRepository.save(recipeInstance);

			}
		}
	}

	@Override
	public void deleteComment(String id, String commentId) {

		Recipe recipeInstance = recipeRepository.findById(id).orElse(null);

		if (recipeInstance != null) {
			recipeInstance.removeRecipeComment(commentId);
			recipeRepository.save(recipeInstance);
			recipeCommentService.delete(commentId);

		}
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		return recipeRepository.findAllByIngredientsEqualsOrderByTitleAsc(ingredient);
	}

	@Override
	public List<Recipe> search(String search) {

		// TO DO : Implementar método

		return null;
	}

}

