package zw.digitalservices.Digital.Services.Customer.api;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import zw.digitalservices.Digital.Services.Customer.config.SmsConfig;
import zw.digitalservices.Digital.Services.Customer.dto.AddUserDto;
import zw.digitalservices.Digital.Services.Customer.dto.LoginDto;
import zw.digitalservices.Digital.Services.Customer.dto.UpdateUserDto;
import zw.digitalservices.Digital.Services.Customer.models.Login;
import zw.digitalservices.Digital.Services.Customer.models.User;
import zw.digitalservices.Digital.Services.Customer.models.api.ApiResponse;
import zw.digitalservices.Digital.Services.Customer.security.JwtTokenProvider;
import zw.digitalservices.Digital.Services.Customer.services.LoginService;
import zw.digitalservices.Digital.Services.Customer.services.RoleService;
import zw.digitalservices.Digital.Services.Customer.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import zw.digitalservices.Digital.Services.Customer.services.smsServices.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/users")
@Api(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";
     private static final String url = "https://bulksms.econet.co.zw/sms/sendsms.jsp?";

    private final UserService userService;
    private final LoginService loginService;
    private final RoleService roleService;
    private  final ModelMapper modelMapper;
   // private final SmsSender smsSender;
    //private final SmsConfig smsConfig;
    //private final SmsService smsService;
    private final RestTemplate restTemplate;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, LoginService loginService, RoleService roleService,ModelMapper modelMapper ,RestTemplate restTemplate,JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.loginService = loginService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        //this.smsSender = smsSender;
        //this.smsConfig = smsConfig;
        //this.smsService = smsService;
        this.restTemplate = restTemplate;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all users", response = ApiResponse.class)
    public ApiResponse getAllUsers(){
        return new ApiResponse(200, "SUCCESS", userService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get one user by their ID", response = ApiResponse.class)
    public ApiResponse getOneUser(@PathVariable("id") Integer id) {
        return new ApiResponse(200, "SUCCESS", userService.getOne(id));
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete user by their ID", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse deleteUser(@PathVariable("id") Integer id){
        return new ApiResponse(200, "SUCCESS", userService.delete(id));
    }

    @PostMapping(value = "/signup/{role-id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Sends sms to a user to join the User Digital Band Platform",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public   ApiResponse SendRegistrationSmsRequest(@Valid @RequestBody SmsRequests smsRequests,AddUserDto addUserDto,
                                                  @PathVariable("role-id") Integer roleId, HttpServletRequest request) {
        User user = modelMapper.map(addUserDto, User.class);
       //SmsRequest smsRequest = modelMapper.map(smsRequestBody, SmsRequest.class);


        // Assign the role of the user
        user.setRoles(Collections.singleton(roleService.getOne(roleId)));

        // Generate the otp for user
        String otp = generateOTP(user.getMobileNumber());

        user.setOtp(otp);

        // Set user active true by default
        user.setActive(true);
        //TODO interchange user.setActive with service.getActiveUser if it fails
        //service.getActiveUser();

        // Send an otp text message
        String appUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath();


            SmsRequest smsRequest = new SmsRequest();
            smsRequest.setUser("Tapuwam");
            smsRequest.setPassword("@Tree123");
            smsRequest.setSms(" Dear " + user.getMobileNumber() + "You have been Signed in  as a on the Digital Services Band Platform your OTP is:\n"  + "OTP\n"+ otp +"Use it within 24 hours to proceed to the questionnaire");
            smsRequest.setSenderId("Digital");
            smsRequest.setMobiles(user.getMobileNumber());
            smsRequest.setClientSmsId("123");

        //SmsRequests smsRequests = new SmsRequests();
        smsRequests.setSmsRequests(smsRequest);
        //smsRequests.getSmsRequests().add(smsRequest);


        String plainCreds = "Tapuwam:@Tree123";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic"  + base64Creds);
        //headers.set("Authorization", String.valueOf(MediaType.TEXT_XML));
        headers.setContentType(MediaType.TEXT_XML);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity  smsRequestHttpEntity = new HttpEntity(smsRequests,headers);



        //restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("Tapuwam", "@Tree123"));
         ResponseEntity<SmsRequests> registrationSms = restTemplate.exchange( url,HttpMethod.POST,smsRequestHttpEntity, SmsRequests.class);
        //SmsRequests registrationSms = restTemplate.postForObject( url,smsRequests, SmsRequests.class);

        //smsService.sendSms(registrationSms);
        userService.add(user);

       return new ApiResponse(201,  "SUCCESS",registrationSms);

        //return  smsRequests;
    }

    //generated OTP to be sent to the user
   private String generateOTP(String otp) {
        String generatedOTP;
        // Generate random number to be used as the OTP
        Random randomNumber = new Random();
        int n = 1000 + randomNumber.nextInt(9999);
        generatedOTP = (String.valueOf(n));
        return generatedOTP;


    }


    @PutMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a current user's details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse updateUser(@RequestBody UpdateUserDto updateUserDto, @PathVariable("id") Integer id){
        User user = modelMapper.map(updateUserDto, User.class);
        return new ApiResponse(200, "SUCCESS", userService.update(user));
    }

    @PostMapping(value = "/signin")
    @ApiOperation("Enables a user to login with phone Number and otp")
    public ResponseEntity loginWithMobileNumberAndOTP(@RequestBody LoginDto accountCredentials) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(
                        accountCredentials.getMobileNumber(),
                        accountCredentials.getOtp()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        //Check if the authentication was successful. If it is, then return the details of the user
        ApiResponse response;
        if (authentication.isAuthenticated()){
            User authenticatedUser = userService.findByMobileNumber(accountCredentials.getMobileNumber());

            // Log user login in database
            Login login = new Login();
            login.setUser(authenticatedUser);
            loginService.add(login);

            response = new ApiResponse(200, "SUCCESS", authenticatedUser);
            return ResponseEntity.ok().header(HEADER_STRING, TOKEN_PREFIX + " " + jwt).body(response);
        }
        else {
            response = new ApiResponse(401,"Invalid mobile number or expired otp");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.TEXT_PLAIN).body(response);

        }
    }

    /*@PostMapping("/generate-credentials")
    @ApiOperation(value = "Generates new otp for user.", response = ApiResponse.class)
    public ApiResponse generateCredentialsForUser(@RequestBody GenerateCredentialsDto credentialsDto)  {
        // Get user by their mobile number
        User user = userService.findByMobileNumber(credentialsDto.getMobileNumber());

        // Generate the otp which the user will use for authentication
        String otp = generateOTP(user.getOtp());

        // Set the user otp to the generated otp
        user.setOtp(otp);

        SmsRequest generatedCredentialsSms = new SmsRequest(user, smsConfig);
        generatedCredentialsSms.setMobiles(generatedCredentialsSms.getMobiles(user.getMobileNumber()));
        generatedCredentialsSms.setSms(" Dear " + user.getMobileNumber() + "You have been Registered  as a "+ user.getRoles().toString() + "on the Digital Services Band Platform your OTP is:\n"  + "OTP\n"+ otp +"Use it within 24 hours to proceed to the questionnaire");
        generatedCredentialsSms.setSenderId(generatedCredentialsSms.getSenderId(smsConfig.getSenderId()));

        //service.Register(user);

       // smsVerification.checkVerification(generatedCredentialsSms.getPhoneNumber(user.getMobileNumber()),"+263");

        smsService.sendSms(generatedCredentialsSms);


        return new ApiResponse(201,  "SUCCESS", userService.add(user));
    }

     */


}




