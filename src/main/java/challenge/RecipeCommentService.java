package challenge;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class RecipeCommentService {

    // Autowired properties

    @Autowired
    RecipeCommentRepository recipeCommentRepository;

    // Service Methods

    public RecipeComment save(RecipeComment recipeComment) {

        return recipeCommentRepository.save(recipeComment);

    }

    public RecipeComment update(String id, RecipeComment recipeComment) {

        RecipeComment commentInstance = recipeCommentRepository.findById(id).orElse(null);

        if(commentInstance != null) {

            commentInstance.setComment(recipeComment.getComment());

            recipeCommentRepository.save(commentInstance);

        }

        return commentInstance;

    }

    public void delete(String id) {

        RecipeComment commentInstance = recipeCommentRepository.findById(id).orElse(null);

        if (commentInstance != null) {

            recipeCommentRepository.delete(commentInstance);

        }
    }


}