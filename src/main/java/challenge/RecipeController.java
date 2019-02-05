package challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private RecipeFactory recipeFactory;

	@GetMapping("/")
	public String returnHello() {
		return "Hello World";
	}

	@PostMapping("/recipe")
	public ResponseEntity<?> save(@RequestBody Recipe recipe) {
		return new ResponseEntity<>(recipeService.save(recipe), HttpStatus.OK);
	}

	@GetMapping("/recipe/{id}")
	public ResponseEntity<?> get(@PathVariable(value = "id") String id) {
		Recipe recipeInstance = recipeService.get(id);
		if (recipeInstance == null) return new ResponseEntity<>("Conteúdo não encontrado", HttpStatus.OK);
		else return new ResponseEntity<>(recipeFactory.getInstance(recipeInstance), HttpStatus.OK);
	}

	@PutMapping("/recipe/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") String id, @RequestBody Recipe recipe) {
		recipeService.update(id, recipe);
		return new ResponseEntity(HttpStatus.OK);
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


//	public List<Recipe> listByIngredient() {
//		return recipeService.listByIngredient(null);
//	}

//	public List<Recipe> search() {
//		return recipeService.search(null);
//	}

}