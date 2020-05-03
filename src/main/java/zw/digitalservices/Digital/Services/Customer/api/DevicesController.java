package zw.digitalservices.Digital.Services.Customer.api;

import zw.digitalservices.Digital.Services.Customer.dto.AddDevicesDto;
import zw.digitalservices.Digital.Services.Customer.dto.UpdateDevicesDto;
import zw.digitalservices.Digital.Services.Customer.models.Devices;
import zw.digitalservices.Digital.Services.Customer.models.api.ApiResponse;
import zw.digitalservices.Digital.Services.Customer.services.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value="api/v1/device-types", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "api/v1/device-types",produces = MediaType.APPLICATION_JSON_VALUE)
public class DevicesController {

    private final DeviceService deviceService;
    private final ModelMapper modelMapper;

    @Autowired
    public DevicesController(DeviceService deviceService, ModelMapper modelMapper) {
        this.deviceService = deviceService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all device types", response = ApiResponse.class)
    public ApiResponse getAllDeviceTypes(){
        return new ApiResponse(200, "SUCCESS", deviceService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a device type by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse getDeviceTypeById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", deviceService.getOne(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a device type by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteDeviceType(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", deviceService.delete(id));
    }


    @PostMapping("/by-device-type-name")
    @ApiOperation(value = "Get a loan type by its name.", response = ApiResponse.class)
    public ApiResponse getDeviceTypeByName(@RequestBody AddDevicesDto devicesDto){
        return new ApiResponse(200, "SUCCESS", deviceService.findByType(devicesDto.getTypeName()));
    }

    @PostMapping("/add-device-type")
    @ApiOperation(value = "Add a new device type", response = ApiResponse.class)
    public ApiResponse addDeviceType(@RequestBody AddDevicesDto devicesDto){
        Devices devices = modelMapper.map(devicesDto, Devices.class);
        return new ApiResponse(201, "SUCCESS", deviceService.add(devices));
    }

    @PutMapping("/edit-device-type")
    @ApiOperation(value = "Update an existing device type", response = ApiResponse.class)
    public ApiResponse updateDeviceType(@RequestBody UpdateDevicesDto updateDevicesDto){
        Devices devices = modelMapper.map(updateDevicesDto, Devices.class);
        return new ApiResponse(200, "SUCCESS", deviceService.update(devices));
    }
}
