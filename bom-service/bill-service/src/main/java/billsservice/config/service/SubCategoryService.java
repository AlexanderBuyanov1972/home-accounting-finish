package billsservice.config.service;

import billsservice.config.DTO.SubCategoryDto;
import billsservice.config.models.Subcategory;
import billsservice.config.repository.SubCategoryRepository;
import billsservice.config.utils.Create;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SubCategoryService {
    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    Create create;
    private static final Logger LOGGER = LoggerFactory.getLogger(SubCategoryService.class);
    private ObjectMapper mapper = new ObjectMapper();
    public SubCategoryDto addSubcategory(SubCategoryDto subCategoryDto) throws JsonProcessingException {
        Subcategory subCategory = DtoToSubCategory(subCategoryDto);
        subCategory.setDeleted(false);
        subCategoryRepository.save(subCategory);
        LOGGER.info("Subcategory created {}", mapper.writeValueAsString(subCategory));
        subCategoryDto.setSubCategoryUuid(subCategory.getSubcategoryUuid());
        return subCategoryDto;
    }

    private Subcategory DtoToSubCategory(SubCategoryDto subCategoryDto) {
        return new Subcategory().builder()
                .categoryUuid(subCategoryDto.getCategory().getCategoryUuid())
                .subcategoryName(subCategoryDto.getSubCategoryName())
                .subcategoryUuid(create.createUuid())
                .userUuid(subCategoryDto.getUserUuid())
                .type(subCategoryDto.getType())
                .description(subCategoryDto.getDecryption())
                .build();
    }

    public SubCategoryDto deleteSubcategory(String userUuid, String subcategoryUuid) throws JsonProcessingException {
        Optional<Subcategory> subcategoryOptional = subCategoryRepository.findByUserUuidAndSubcategoryUuidAndDeleted(
                userUuid, subcategoryUuid, false
        );
        Subcategory subcategory = subcategoryOptional.get();
        subcategory.setDeleted(true);
        SubCategoryDto subCategoryDto = SubcategoryToDto(subcategory);
        subCategoryRepository.save(subcategory);
        LOGGER.info("Subcategory deleted {}", mapper.writeValueAsString(subcategory));
        return subCategoryDto;
    }

    private SubCategoryDto SubcategoryToDto(Subcategory subcategory) {
    return new SubCategoryDto().builder()
            .decryption(subcategory.getDescription())
            .subCategoryName(subcategory.getSubcategoryName())
            .subCategoryUuid(subcategory.getSubcategoryUuid())
            .userUuid(subcategory.getUserUuid())
            .type(subcategory.getType())
            .build();
    }
}
