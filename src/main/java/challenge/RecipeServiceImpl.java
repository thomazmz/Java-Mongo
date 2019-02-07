package challenge;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


@Service
public class RecipeServiceImpl implements RecipeService {

	// Autowired properties

	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	RecipeRepository recipeRepository;

	@Override
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public Recipe get(String id) {
		return recipeRepository.findById(id).get();
	}

	@Override
	public void update(String id, Recipe recipe) {
		mongoOperations.updateFirst(
				Query.query(Criteria.where("id").is(id)),
				Update.update("title", recipe.getTitle()).set("description", recipe.getDescription()).set("ingredients", recipe.getIngredients()),
				Recipe.class);
	}

	@Override
	public void delete(String id) {

		recipeRepository.deleteById(id);

	}

	@Override
	public void like(String id, String userId) {

		// Ambas as implementações abaixo passam nos testes.

		mongoOperations.updateFirst(
				Query.query(Criteria.where("id").is(id)),
				new Update().addToSet("likes", userId),
				Recipe.class);

//		Recipe recipeInstance = recipeRepository.findById(id).orElse(null);
//
//		if(recipeInstance != null) {
//			recipeInstance.addLike(userId);
//			recipeRepository.save(recipeInstance);
//		}

	}

	@Override
	public void unlike(String id, String userId) {

		// Ambas as implementações abaixo passam nos testes.

		mongoOperations.updateFirst(
				Query.query(Criteria.where("id").is(id)),
				new Update().pull("likes", userId),
				Recipe.class);

//		Recipe recipeInstance = recipeRepository.findById(id).orElse(null);
//
//		if (recipeInstance != null) {
//			recipeInstance.removeLike(userId);
//			recipeRepository.save(recipeInstance);
//		}

	}

	@Override
	public RecipeComment addComment(String id, RecipeComment recipeComment) {

		recipeComment.setId(ObjectId.get().toHexString());

		mongoOperations.updateFirst(
			Query.query(Criteria.where("id").is(id)),
			new Update().addToSet("recipeComments", recipeComment),
			Recipe.class);

		return recipeComment;

	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment recipeComment) {

		mongoOperations.findAndModify(
			Query.query(Criteria.where("id").is(id)),
			new Update().set("recipe.recipeComments", recipeComment),
			Recipe.class);

	}

	@Override
	public void deleteComment(String id, String commentId) {

		mongoOperations.updateFirst(
			Query.query(Criteria.where("id").is(id)),
			new Update().pull("recipeComments", Query.query(Criteria.where("id").is(commentId))),
			Recipe.class
		);

	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {

		return mongoOperations.find(
			Query.query(Criteria.where("ingredients").is(ingredient))
			.with(Sort.by("title").ascending()),
			Recipe.class);

	}


	@Override
	public List<Recipe> search(String search) {

		return mongoOperations.find(
			Query.query(new Criteria().orOperator(
			Criteria.where("title").regex(search, "i"),
			Criteria.where("description").regex(search, "i"))).with(Sort.by("title").ascending()),
			Recipe.class);

	}

}

