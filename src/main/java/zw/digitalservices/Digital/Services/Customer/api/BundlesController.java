package zw.digitalservices.Digital.Services.Customer.api;

import zw.digitalservices.Digital.Services.Customer.dto.AddBundlesDto;
import zw.digitalservices.Digital.Services.Customer.dto.UpdateBundlesDto;
import zw.digitalservices.Digital.Services.Customer.models.Bundles;
import zw.digitalservices.Digital.Services.Customer.models.api.ApiResponse;
import zw.digitalservices.Digital.Services.Customer.services.BundlesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/sought-bundles", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="api/v1/sought-bundles",produces = MediaType.APPLICATION_JSON_VALUE)
public class BundlesController {

    private final BundlesService bundlesService;
    private final ModelMapper modelMapper;

    @Autowired
    public BundlesController(BundlesService bundlesService, ModelMapper modelMapper) {
        this.bundlesService = bundlesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all bundles", response = ApiResponse.class)
    public ApiResponse getBundles(){
        return new ApiResponse(200, "SUCCESS", bundlesService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a bundle by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse getBundleById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", bundlesService.getOne(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a bundle by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteBundle(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", bundlesService.delete(id));
    }


    @GetMapping("/by-bundle-name")
    @ApiOperation(value = "Get a bundle by its name.", response = ApiResponse.class)
    public ApiResponse getBundleByName(@RequestBody AddBundlesDto bundlesDto){
        return new ApiResponse(200, "SUCCESS", bundlesService.findByType(bundlesDto.getBundleTypes()));
    }

    @PostMapping("/add-bundle")
    @ApiOperation(value = "Add a new bundle ", response = ApiResponse.class)
    public ApiResponse addBundle(@RequestBody AddBundlesDto bundlesDto){
        Bundles bundles = modelMapper.map(bundlesDto, Bundles.class);
        return new ApiResponse(201, "SUCCESS", bundlesService.add(bundles));
    }

    @PutMapping("/edit-loan-type")
    @ApiOperation(value = "Update an bundle", response = ApiResponse.class)
    public ApiResponse updateBundle(@RequestBody UpdateBundlesDto updateBundlesDto){
        Bundles bundles = modelMapper.map(updateBundlesDto, Bundles.class);
        return new ApiResponse(200, "SUCCESS", bundlesService.update(bundles));
    }
}
