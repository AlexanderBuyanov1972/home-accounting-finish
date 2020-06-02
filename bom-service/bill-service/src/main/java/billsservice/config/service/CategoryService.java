package billsservice.config.service;

import billsservice.config.DTO.CategoryDto;
import billsservice.config.models.Category;
import billsservice.config.repository.CategoryRepository;
import billsservice.config.utils.Create;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    Create create;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
    private ObjectMapper mapper = new ObjectMapper();

    public CategoryDto saveCategory(CategoryDto categoryDto) throws JsonProcessingException {
        Category category = DtoToCategory(categoryDto);
        category.setDeleted(false);
        String category_uuid = categoryRepository.save(category).getCategoryUuid();
        LOGGER.info("Category created {} ", mapper.writeValueAsString(category));
        categoryDto.setCategoryUuid(category_uuid);
        return categoryDto;
    }

    public CategoryDto deleteCategory(String uuid, String userUuid) throws JsonProcessingException {
        Optional<Category> categoryOptional = categoryRepository.findByUserUuidAndCategoryUuid(userUuid, uuid);
        Category category = categoryOptional.get();
        category.setDeleted(true);
        categoryRepository.save(category);
        LOGGER.info("Category deleted {} ", mapper.writeValueAsString(category));
        CategoryDto categoryDto = categoryToDto(category);
        return categoryDto;
    }

    private CategoryDto categoryToDto(Category category) {
        return new CategoryDto().builder()
                .categoryUuid(category.getCategoryUuid())
                .categoryName(category.getCategoryName())
                .userUuid(category.getUserUuid())
                .type(category.getType())
                .build();
    }

    private Category DtoToCategory(CategoryDto categoryDto) {
        return new Category().builder()
                .categoryName(categoryDto.getCategoryName())
                .userUuid(categoryDto.getUserUuid())
                .categoryUuid(create.createUuid())
                .type(categoryDto.getType())
                .description(categoryDto.getDecryption())
                .build();
    }

}