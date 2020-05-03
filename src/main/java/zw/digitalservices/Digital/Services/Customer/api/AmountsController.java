package zw.digitalservices.Digital.Services.Customer.api;

import zw.digitalservices.Digital.Services.Customer.dto.AddAmountsDto;
import zw.digitalservices.Digital.Services.Customer.dto.UpdateAmountsDto;
import zw.digitalservices.Digital.Services.Customer.models.Amounts;
import zw.digitalservices.Digital.Services.Customer.models.api.ApiResponse;
import zw.digitalservices.Digital.Services.Customer.services.AmountsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/monthly-amounts", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="api/v1/monthly-amounts",produces = MediaType.APPLICATION_JSON_VALUE)
public class AmountsController {
    private final AmountsService amountsService;
    private final ModelMapper modelMapper;

    @Autowired
    public AmountsController(AmountsService amountsService, ModelMapper modelMapper) {
        this.amountsService = amountsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all Amounts", response = ApiResponse.class)
    public ApiResponse getAllAmounts(){
        return new ApiResponse(200, "SUCCESS", amountsService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an Amount by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse getAmountsById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", amountsService.getOne(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete an Amount by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteAmountsRange(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", amountsService.delete(id));
    }


    @PostMapping("/by-amount-range")
    @ApiOperation(value = "Get an amount range", response = ApiResponse.class)
    public ApiResponse getAmountByRange(@RequestBody AddAmountsDto amountsDto){
        return new ApiResponse(200, "SUCCESS", amountsService.findByAmount(amountsDto.getAmount()));
    }

    @PostMapping("/add-amount-range")
    @ApiOperation(value = "Add a new amount range", response = ApiResponse.class)
    public ApiResponse addAmounts(@RequestBody AddAmountsDto amountsDto){
        Amounts amounts = modelMapper.map(amountsDto, Amounts.class);
        return new ApiResponse(201, "SUCCESS", amountsService.add(amounts));
    }

    @PutMapping("/edit-loan-type")
    @ApiOperation(value = "Update an existing amounts-range", response = ApiResponse.class)
    public ApiResponse updateAmounts(@RequestBody UpdateAmountsDto updateAmountsDto){
        Amounts amounts = modelMapper.map(updateAmountsDto, Amounts.class);
        return new ApiResponse(200, "SUCCESS", amountsService.update(amounts));
    }
}
