package billsservice.config.controller;

import billsservice.config.DTO.BillAllDetailsDto;
import billsservice.config.DTO.BillAllDto;
import billsservice.config.DTO.BillDto;
import billsservice.config.DTO.UpdateBillDto;
import billsservice.config.service.BillService;
import billsservice.config.service.ValidatorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    ValidatorService validatorService;

    @Autowired
    BillService billService;

    @PostMapping()
//    TODO - if user not enter bill-number - create last number + 1
    public BillDto addBill(@RequestBody final BillDto billDto) throws JsonProcessingException {
        validatorService.checkBillUniq(billDto.getBillName(), billDto.getUserUuid());
        validatorService.checkBillNumber(billDto.getBillName(), billDto.getUserUuid(), billDto.getBillNumber());
        if(billDto.getDescription() == null){
            billDto.setDescription("");
        }
        if(billDto.getStartSum() == null){
            billDto.setStartSum(0.0);
        }
        if(billDto.getBillNumber() == null){
            billDto.setBillNumber(billService.createBillNumber(billDto));
        }
        return billService.addBill(billDto);
    }

    @PreAuthorize("principal == #userUuid")
    @GetMapping("/allBills/{userUuid}")
    public List<BillAllDto> getAllBills(@PathVariable String userUuid){
        return billService.getAllBills(userUuid);
    }

    @GetMapping("/allBills/details/{userUuid}")
    public Map<LocalDate,List<BillAllDetailsDto>> getAllDetailsBills(@PathVariable String userUuid){
        return billService.getAllDetailsBills(userUuid);
    }

    @DeleteMapping("/{userUuid}/{billUuid}")
    public BillDto deletedBill(@PathVariable String userUuid, @PathVariable String billUuid, @RequestHeader HttpHeaders headers) throws JsonProcessingException {
        headers.get("userUuid");
        validatorService.checkBillUniqByUuid(userUuid, billUuid);
        return billService.deleteBill(userUuid, billUuid);
    }

    @PutMapping("/{userUuid}/{billUuid}")
    public BillDto updateBill(@PathVariable String userUuid, @PathVariable String billUuid, @RequestBody
   final UpdateBillDto billDto ) throws JsonProcessingException {
        validatorService.checkBill(userUuid, billUuid);
        return billService.updateBill(userUuid, billUuid, billDto);
    }
}

