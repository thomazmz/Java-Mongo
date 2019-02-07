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

//	@PostMapping("/recipe")
//	public ResponseEntity<?> save(@RequestBody Recipe recipe) {
//		return new ResponseEntity<>(recipeService.save(recipe), HttpStatus.OK);
//	}

	@PostMapping("/recipe")
	public Recipe save(@RequestBody Recipe recipe) { return recipeService.save(recipe);}

	@GetMapping("/recipe/{id}")
	public RecipeDto get(@PathVariable(value = "id") String id) {
//	public ResponseEntity<?> get(@PathVariable(value = "id") String id) {
		Recipe recipeInstance = recipeService.get(id);
//		if (recipeInstance == null) return new ResponseEntity<>(HttpStatus.OK);
//		else return new ResponseEntity<>(recipeFactory.getInstance(recipeInstance), HttpStatus.OK);
		return recipeFactory.getInstance(recipeInstance);
	}

	@PutMapping("/recipe/{id}")
	public void update(@PathVariable(value = "id") String id, @RequestBody Recipe recipe) {
//	public ResponseEntity<?> update(@PathVariable(value = "id") String id, @RequestBody Recipe recipe) {
		recipeService.update(id, recipe);
//		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("/recipe/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		recipeService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/recipe/{id}/like/{userId}")
	public ResponseEntity<?> like(@PathVariable(value = "id") String id, @PathVariable(value = "userId") String userId) {
		recipeService.like(id, userId);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("/recipe/{id}/like/{userId}")
	public ResponseEntity<?> unlike(@PathVariable(value = "id") String id, @PathVariable(value = "userId") String userId) {
		recipeService.unlike(id, userId);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/recipe/{id}/comment")
	public ResponseEntity<?> addComment(@PathVariable(value = "id") String id, @RequestBody RecipeComment recipeComment) {
		return new ResponseEntity<>(recipeService.addComment(id, recipeComment), HttpStatus.OK);
	}

	@PutMapping("/recipe/{id}/comment/{commentId}")
	public ResponseEntity<?> updateComment(@PathVariable(value = "id") String id, @PathVariable(value = "commentId") String commentId, @RequestBody RecipeComment recipeComment) {
		recipeService.updateComment(id, commentId, recipeComment);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("/recipe/{id}/comment/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable(value = "id") String id, @PathVariable(value = "commentId") String commentId) {
		recipeService.deleteComment(id, commentId);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/recipe/ingredient")
	public ResponseEntity<?>  listByIngredient(@RequestParam("ingredient") String ingredient) {
		return new ResponseEntity<>(recipeService.listByIngredient(ingredient), HttpStatus.OK);
	}

	@GetMapping("/recipe/search")
	public List<Recipe> search() {
		return null;
	}

}