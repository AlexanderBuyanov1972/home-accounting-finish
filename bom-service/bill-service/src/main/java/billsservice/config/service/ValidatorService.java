package billsservice.config.service;

import billsservice.config.DTO.BillNumbersDto;
import billsservice.config.DTO.OperationDto;
import billsservice.config.enums.CategoryType;
import billsservice.config.feign.CurrencyFromFeign;
import billsservice.config.feign.CurrencyServiceClient;
import billsservice.config.models.Bill;
import billsservice.config.models.Category;
import billsservice.config.models.Operation;
import billsservice.config.models.Subcategory;
import billsservice.config.repository.BillRepository;
import billsservice.config.repository.CategoryRepository;
import billsservice.config.repository.OperationRepository;
import billsservice.config.repository.SubCategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ValidatorService {
    //    TODO check user_exists from user_service
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    BillRepository billRepository;
    @Autowired
    OperationRepository operationRepository;
    @Autowired
    CurrencyServiceClient currencyServiceClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorService.class);
    private ObjectMapper mapper = new ObjectMapper();

    public void checkUniqOfCategory(String userUuid, String categoryName, CategoryType type) throws JsonProcessingException {
        Optional<Category> category = categoryRepository.findByUserUuidAndCategoryNameAndTypeAndDeleted(
                userUuid, categoryName, type, false);
        if (category.isPresent()) {
//            TODO implementation
//            LOGGER.info("Category exists ", mapper.writeValueAsString(category));
//            throw new HomeBuhNotUniqueException("category", "name " + categoryName);

        }
    }

    public void checkCategoryExists(String uuid, String userUuid) throws JsonProcessingException {
        Optional<Category> category = categoryRepository.findByUserUuidAndCategoryUuidAndDeleted(userUuid, uuid, false);
        if (!category.isPresent()) {
            //            TODO implementation
//            LOGGER.info("Category exists ", mapper.writeValueAsString(category));
//            throw new HomeBuhNotFoundException("category", uuid);
        }
    }

    public void checkCategoryAndSubCategoryForSaveSubCategory(String userUuid, String categoryUuid,
                                                              String subcategoryName, String categoryName,
                                                              CategoryType type) {
        Optional<Subcategory> subcategoryOptional = subCategoryRepository
                .findByUserUuidAndCategoryUuidAndSubcategoryNameAndDeletedAndType(
                        userUuid, categoryUuid, subcategoryName, false, type);
        if (subcategoryOptional.isPresent()) {

            //            TODO implementation
//                         LOGGER.info("Subcategory with name {} exists ", subcategoryName);
//            throw new HomeBuhNotUniqueException("subCategory", "name " + subcategoryName);
        }
        Optional<Category> categoryOptional = categoryRepository.findByUserUuidAndCategoryNameAndDeletedAndType(userUuid,
                categoryName, false, type);
        Optional<Category> categoryUuidOptional = categoryRepository.findByUserUuidAndCategoryUuidAndDeleted(
                userUuid, categoryUuid, false);

        if (!categoryOptional.isPresent() || !categoryUuidOptional.isPresent()) {
            //            TODO implementation
//            LOGGER.info("Category {} or subcategory {} dont exists ", categoryName, subcategoryName);
//            throw new HomeBuhNotFoundException("category", " or name " + categoryName + " " + categoryUuid);
        }
    }

    public void checkSubcategoryExists(String userUuid, String subcategoryUuid) throws JsonProcessingException {
        Optional<Subcategory> subcategory = subCategoryRepository.findByUserUuidAndSubcategoryUuidAndDeleted(
                userUuid, subcategoryUuid, false);
        if (!subcategory.isPresent()) {
            //            TODO implementation
//            LOGGER.info("Subcategory exists ", mapper.writeValueAsString(subcategory));
//            throw new HomeBuhNotFoundException("subcategory", subcategoryUuid);
        }
    }

    public void checkBillUniq(String billName, String userUuid) throws JsonProcessingException {
        Optional<Bill> billOptional = billRepository.findByUserUuidAndBillName(userUuid, billName);
        if (billOptional.isPresent()) {
            //            TODO implementation

//            LOGGER.info("Bill exists ", mapper.writeValueAsString(billOptional.get()));
//            throw new HomeBuhNotUniqueException("bill", "name " + billName);
        }
    }

    public void checkBillNumber(String billName, String userUuid, Integer billNumber) {
        Optional<Bill> billOptional = billRepository.findByUserUuidAndBillNumberAndDeleted(userUuid,
                billNumber, false);
        if (billOptional.isPresent()) {
            checkAndChangeNumber(userUuid, billNumber);
        }
    }

    public void checkAndChangeNumber(String userUuid, Integer billNumber) {
        List<BillNumbersDto> billsForChangeNumber = billRepository.findNumbers(billNumber, userUuid, false);
        billsForChangeNumber.stream().forEach(b -> {
            b.setBillNumber(b.getBillNumber() + 1);
        });
        List<Bill> bills = billNumbersToBill(billsForChangeNumber);
        billRepository.saveAll(bills);
    }

    public List<Bill> billNumbersToBill(List<BillNumbersDto> billsForChangeNumber) {
        List<Bill> bill = new ArrayList<>(billsForChangeNumber.size());
        for (BillNumbersDto b : billsForChangeNumber) {
            Bill billFromDto = new Bill().builder()
                    .billNumber(b.getBillNumber())
                    .userUuid(b.getUserUuid())
                    .billName(b.getBillName())
                    .billUuid(b.getBillUuid())
                    .startSum(b.getStartSum())
                    .createDate(b.getCreateDate())
                    .deleted(b.getDeleted())
                    .description(b.getDescription())
                    .id(b.getId())
                    .build();
            bill.add(billFromDto);
        }
        return bill;
    }

    public void checkElementsForCategory(OperationDto operationDto) throws Exception {
        Optional<Bill> bill = billRepository.findByUserUuidAndBillUuidAndDeleted(operationDto.getUserUuid(),
                operationDto.getBillUuid(), false);
        if (!bill.isPresent()) {
            //            TODO implementation
//            LOGGER.info("Bill not exists ", mapper.writeValueAsString(bill));
//            throw new HomeBuhNotFoundException("bill", operationDto.getBillUuid());
        }
        Optional<Category> category = categoryRepository.findByUserUuidAndCategoryUuidAndDeletedAndType(operationDto.getUserUuid(),
                operationDto.getCategoryUuid(), false, operationDto.getType());
        if (!category.isPresent()) {
            //            TODO implementation
//            LOGGER.info("Category not exists ", mapper.writeValueAsString(category));
//            throw new HomeBuhNotFoundException("category", operationDto.getBillUuid());
        }
        if (operationDto.getSubcategoryUuid() != null) {
            Optional<Subcategory> subcategory = subCategoryRepository.findByUserUuidAndSubcategoryUuidAndCategoryUuidAndDeleted(
                    operationDto.getUserUuid(), operationDto.getSubcategoryUuid(), operationDto.getCategoryUuid(), false
            );
            if (!subcategory.isPresent()) {
                //            TODO implementation
//                LOGGER.info("Subcategory not exists ", mapper.writeValueAsString(subcategory));
//                throw new HomeBuhNotFoundException("subcategory", operationDto.getBillUuid());
            }
        }
        List<CurrencyFromFeign> listOfCurrency = currencyServiceClient.getAllCurrencyByUser(
                operationDto.getUserUuid()
        );


        long countOfMatches = listOfCurrency.stream().filter(c -> c.getAbbr()
                .equals(operationDto.getCurrency())).count();

        if (countOfMatches == 0) {
//            TODO implementation Error bemcome system out print error
            System.err.println("WRONG CURRENCY");
        }
        int x = 0;
    }

    public void checkOperation(String userUuid, String operationUuid, CategoryType type) throws JsonProcessingException {
        Optional<Operation> operationOptional = operationRepository.findByUserUuidAndOperationUuidAndTypeAndDeleted(
                userUuid, operationUuid, type, false
        );
        if (!operationOptional.isPresent()) {
            //            TODO implementation
//            LOGGER.info("Operation not exists ", mapper.writeValueAsString(operationOptional.get()));
//            throw new HomeBuhNotFoundException("operation", operationUuid);
        }
    }

    public void checkBillUniqByUuid(String userUuid, String billUuid) throws JsonProcessingException {
        Optional<Bill> bill = billRepository.findByUserUuidAndBillUuidAndDeleted(userUuid, billUuid, false);
        if (bill.isPresent()) {
            //            TODO implementation
//            LOGGER.info("Bill exists ", mapper.writeValueAsString(bill.get()));
//            throw new HomeBuhNotUniqueException("bill", " uuid " + billUuid);
        }
    }

    public void checkBill(String userUuid, String billUuid) throws JsonProcessingException {
        Optional<Bill> bill = billRepository.findByUserUuidAndBillUuidAndDeleted(userUuid, billUuid, false);
        if (!bill.isPresent()) {
            //            TODO implementation
//            LOGGER.info("Bill not exists ", mapper.writeValueAsString(bill.get()));
//            throw new HomeBuhNotFoundException("bill", billUuid);
        }
    }
}
