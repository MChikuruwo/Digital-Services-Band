package com.digitalservices.Digital.Services.Customer.api;


import com.digitalservices.Digital.Services.Customer.config.SmsConfig;
import com.digitalservices.Digital.Services.Customer.dto.AddOptInDto;
import com.digitalservices.Digital.Services.Customer.models.*;
import com.digitalservices.Digital.Services.Customer.models.api.ApiResponse;
import com.digitalservices.Digital.Services.Customer.services.*;
import com.digitalservices.Digital.Services.Customer.services.smsServices.RegistrationSmsRequest;
import com.digitalservices.Digital.Services.Customer.services.smsServices.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/opt-in", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "api/v1/opt-in", produces = MediaType.APPLICATION_JSON_VALUE)
public class OptInController {

    private final OptInService optInService;
    private final AmountsService amountsService;
    private final DeviceService deviceService;
    private final ServiceOfferedService serviceOfferedService;
    private final BundlesService bundlesService;
    private  final ModelMapper modelMapper;
    private final SmsConfig smsConfig;
    private final SmsService smsService;


    @Autowired
    public OptInController(OptInService optInService,AmountsService amountsService,DeviceService deviceService,ServiceOfferedService serviceOfferedService,BundlesService bundlesService, ModelMapper modelMapper,SmsConfig smsConfig,SmsService smsService){
        this.optInService = optInService;
        this.amountsService = amountsService;
        this.deviceService = deviceService;
        this.serviceOfferedService = serviceOfferedService;
        this.bundlesService = bundlesService;
        this.modelMapper = modelMapper;
        this.smsConfig = smsConfig;
        this.smsService = smsService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all loan opt-in requests", response = ApiResponse.class)
    public ApiResponse getAllLoanApplications(){
        return new ApiResponse(200, "SUCCESS", optInService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a opt-on record by its id. Takes id as a path variable",
            response = ApiResponse.class)
    public ApiResponse getLoanApplicationById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", optInService.getOne(id));
    }

    @GetMapping("/by-device-type/{deviceType-id}")
    @ApiOperation(value = "Get opt-in requests by device type. Takes Device type Id as a path variable", response = ApiResponse.class)
    public ApiResponse getOptInByDeviceType(@PathVariable("deviceType-id") Long deviceTypeId){
        Devices devices =  deviceService.getOne(deviceTypeId);
        return new ApiResponse(200, "SUCCESS", optInService.findAllByDeviceType(devices));
    }
    @GetMapping("/by-sought-bundle/{soughtBundle-id}")
    @ApiOperation(value = "Get opt-in requests by sought bundle. Takes sought bundle Id as a path variable", response = ApiResponse.class)
    public ApiResponse getOptInsBySoughtBundle(@PathVariable("soughtBundle-id") Long soughtBundleId){
        Bundles bundles =  bundlesService.getOne(soughtBundleId);
        return new ApiResponse(200, "SUCCESS", optInService.findAllByBundleType(bundles));
    }
    @GetMapping("/by-amounts-spent-on-data/{amounts-id}")
    @ApiOperation(value = "Get opt-in requests by amounts spent on Data. Takes Amounts Id as a path variable", response = ApiResponse.class)
    public ApiResponse getOptInsByAmountsSpentOnData(@PathVariable("amounts-id") Long amountsId){
        Amounts amounts =  amountsService.getOne(amountsId);
        return new ApiResponse(200, "SUCCESS", optInService.findAllByAmountsOnData(amounts));
    }
    @GetMapping("/by-amounts-spent-on-sms/{amounts-id}")
    @ApiOperation(value = "Get opt-in requests by amounts spent on Sms. Takes Amounts Id as a path variable", response = ApiResponse.class)
    public ApiResponse getOptInsByAmountsSpentOnSms(@PathVariable("amounts-id") Long amountsId){
        Amounts amounts =  amountsService.getOne(amountsId);
        return new ApiResponse(200, "SUCCESS", optInService.findAllByAmountsOnSms(amounts));
    }
    @GetMapping("/by-amounts-spent-on-voice/{amounts-id}")
    @ApiOperation(value = "Get opt-in requests by amounts spent on voice. Takes Amounts Id as a path variable", response = ApiResponse.class)
    public ApiResponse getOptInsByAmountsSpentOnVoice(@PathVariable("amounts-id") Long amountsId){
        Amounts amounts =  amountsService.getOne(amountsId);
        return new ApiResponse(200, "SUCCESS", optInService.findAllByAmountsOnVoice(amounts));
    }
    @GetMapping("/by-service-offered/{service-id}")
    @ApiOperation(value = "Get opt-in requests by services offered. Takes serviceId as a path variable", response = ApiResponse.class)
    public ApiResponse getOptInsByServiceOffered(@PathVariable("service-id") Long serviceId){
        Services services =  serviceOfferedService.getOne(serviceId);
        return new ApiResponse(200, "SUCCESS", optInService.findAllByServiceType(services));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a opt-in record. Takes id as a path variable",
            response = ApiResponse.class)
    public ApiResponse deleteOptIn(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", optInService.delete(id));
    }

    @PostMapping("/create/{amount-on-sms-id}/{amount-on-data-id}/{amount-on-voice-id}/{device-type-id}/{bundle-type-id}/{service-type-id}")
    @ApiOperation(value = "Create a new opt-in record. " +
            "Takes amounts Ids, device Id,bundle Id and service Id  as path variables",
            response = ApiResponse.class)
    public ApiResponse createOptIn(@RequestBody AddOptInDto optInDto,
                                   @PathVariable("amount-on-sms-id") Long amountOnSmsId,
                                   @PathVariable("amount-on-data-id") Long amountOnDataId,
                                   @PathVariable("amount-on-voice-id") Long amountOnVoiceId,
                                   @PathVariable("device-type-id") Long deviceTypeId,
                                   @PathVariable("bundle-type-id") Long bundleTypeId,
                                   @PathVariable("service-type-id") Long serviceTypeId, HttpServletRequest request)


    {
        OptIn optIn = modelMapper.map(optInDto, OptIn.class);
        optIn.setAmountsOnSms(amountsService.getOne(amountOnSmsId));
        optIn.setAmountsOnData(amountsService.getOne(amountOnDataId));
        optIn.setAmountsOnVoice(amountsService.getOne(amountOnVoiceId));
        optIn.setDeviceType(deviceService.getOne(deviceTypeId));
        optIn.setSoughtBundle(bundlesService.getOne(bundleTypeId));
        optIn.setServiceOffered(serviceOfferedService.getOne(serviceTypeId));
        optIn.setHasBeenApproved(false);

        // Send confirmation text message
        String appUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath();

        RegistrationSmsRequest registrationSms = new RegistrationSmsRequest(optIn);
        registrationSms.setPhoneNumber(registrationSms.getPhoneNumber(optIn.getMobileNumber()));
        registrationSms.setMessage(" Dear " + optIn.getMobileNumber() + "You have been Successfully Registered on the Digital Services Band Platform ");
        registrationSms.setFrom(registrationSms.getPhoneNumber(smsConfig.getTrialNumber()));

        //smsVerification.checkVerification(registrationSms.getPhoneNumber(opt.getMobileNumber()),"+263");


        smsService.sendRegistrationSms(registrationSms);



        return new ApiResponse(201, "SUCCESS", optInService.add(optIn));
    }


    @GetMapping("/opt-in-approval-status")
    @ApiOperation(value = "Get opt-in records by approval status. Takes approved status as a request parameter",
            response = ApiResponse.class)
    public ApiResponse getOptInsByApprovalStatus(@RequestParam("approved") Boolean hasBeenApproved){
        return new ApiResponse(200, "SUCCESS",
                optInService.findAllByHasBeenApproved(hasBeenApproved));
    }

}
