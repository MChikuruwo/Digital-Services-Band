package com.digitalservices.Digital.Services.Customer.api;

import com.digitalservices.Digital.Services.Customer.dto.AddServicesDto;
import com.digitalservices.Digital.Services.Customer.dto.UpdateServicesDto;
import com.digitalservices.Digital.Services.Customer.models.Services;
import com.digitalservices.Digital.Services.Customer.models.api.ApiResponse;
import com.digitalservices.Digital.Services.Customer.services.ServiceOfferedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/service-offered",produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "api/v1/service-offered", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServicesController {

    private final ServiceOfferedService serviceOfferedService;
    private final ModelMapper modelMapper;

    @Autowired
    public ServicesController(ServiceOfferedService serviceOfferedService, ModelMapper modelMapper) {
        this.serviceOfferedService = serviceOfferedService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all services offered", response = ApiResponse.class)
    public ApiResponse getAllServices(){
        return new ApiResponse(200, "SUCCESS", serviceOfferedService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a service offered by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse getServiceById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", serviceOfferedService.getOne(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a service offered by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteService(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", serviceOfferedService.delete(id));
    }


    @PostMapping("/by-service-offered-name")
    @ApiOperation(value = "Get a service offered by its name.", response = ApiResponse.class)
    public ApiResponse getServiceByName(@RequestBody AddServicesDto servicesDto){
        return new ApiResponse(200, "SUCCESS", serviceOfferedService.findByType(servicesDto.getServiceType()));
    }

    @PostMapping("/add-service")
    @ApiOperation(value = "Add a new service to offer", response = ApiResponse.class)
    public ApiResponse addService(@RequestBody AddServicesDto servicesDto){
        Services services = modelMapper.map(servicesDto, Services.class);
        return new ApiResponse(201, "SUCCESS", serviceOfferedService.add(services));
    }

    @PutMapping("/edit-service")
    @ApiOperation(value = "Update an existing service offered", response = ApiResponse.class)
    public ApiResponse updateService(@RequestBody UpdateServicesDto updateServicesDto){
        Services services = modelMapper.map(updateServicesDto, Services.class);
        return new ApiResponse(200, "SUCCESS", serviceOfferedService.update(services));
    }
}
