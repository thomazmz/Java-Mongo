package challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private RecipeFactory recipeFactory;

	@PostMapping("/recipe")
	public Recipe save(@RequestBody Recipe recipe) { return recipeService.save(recipe);}

	@GetMapping("/recipe/{id}")
	public Recipe get(@PathVariable(value = "id") String id) {
		return recipeService.get(id);
	}

	@PutMapping("/recipe/{id}")
	public void update(@PathVariable(value = "id") String id, @RequestBody Recipe recipe) {
		recipeService.update(id, recipe);
	}

	@DeleteMapping("/recipe/{id}")
	public void delete(@PathVariable(value = "id") String id) {
		recipeService.delete(id);
	}

	@PostMapping("/recipe/{id}/like/{userId}")
	public void like(@PathVariable(value = "id") String id, @PathVariable(value = "userId") String userId) {
		recipeService.like(id, userId);
	}

	@DeleteMapping("/recipe/{id}/like/{userId}")
	public void unlike(@PathVariable(value = "id") String id, @PathVariable(value = "userId") String userId) {
		recipeService.unlike(id, userId);
	}

	@PostMapping("/recipe/{id}/comment")
	public RecipeComment addComment(@PathVariable(value = "id") String id, @RequestBody RecipeComment recipeComment) {
		return recipeService.addComment(id, recipeComment);
	}

	@PutMapping("/recipe/{id}/comment/{commentId}")
	public void updateComment(@PathVariable(value = "id") String id, @PathVariable(value = "commentId") String commentId, @RequestBody RecipeComment recipeComment) {
		recipeService.updateComment(id, commentId, recipeComment);
	}

	@DeleteMapping("/recipe/{id}/comment/{commentId}")
	public void deleteComment(@PathVariable(value = "id") String id, @PathVariable(value = "commentId") String commentId) {
		recipeService.deleteComment(id, commentId);
	}

	@GetMapping(value = "recipe/ingredient")
	public List<Recipe> listByIngredient(@RequestParam String ingredient) {
		return recipeService.listByIngredient(ingredient);
	}

	@GetMapping(value = "recipe/search")
	public List<Recipe> search(@RequestParam String search) {
		return recipeService.search(search);
	}

}


