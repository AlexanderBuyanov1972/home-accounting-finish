package billsservice.config.controller;

import billsservice.config.DTO.SubCategoryDto;
import billsservice.config.service.SubCategoryService;
import billsservice.config.service.ValidatorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/subcategory")
public class SubcategoryController {
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    ValidatorService validatorService;

    @PostMapping()
    public SubCategoryDto addSubCategory (@RequestBody final SubCategoryDto subCategoryDto) throws JsonProcessingException {
         validatorService.checkCategoryAndSubCategoryForSaveSubCategory(subCategoryDto.getUserUuid(),
                 subCategoryDto.getCategory().getCategoryUuid(), subCategoryDto.getSubCategoryName(),
                 subCategoryDto.getCategory().getCategoryName(), subCategoryDto.getType());
        subCategoryDto.setType(subCategoryDto.getType());
        if(subCategoryDto.getDecryption() == null){
            subCategoryDto.setDecryption("");
        }
        return subCategoryService.addSubcategory(subCategoryDto);
    }

    @DeleteMapping("/{userUuid}/{subcategoryUuid}")
    public SubCategoryDto deleteSubCategory(@PathVariable String userUuid, @PathVariable String subcategoryUuid) throws JsonProcessingException {
        validatorService.checkSubcategoryExists(userUuid, subcategoryUuid);
        return subCategoryService.deleteSubcategory(userUuid, subcategoryUuid);
    }
}
