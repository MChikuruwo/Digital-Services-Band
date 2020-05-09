package zw.digitalservices.Digital.Services.Customer.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import zw.digitalservices.Digital.Services.Customer.dto.AddUserDto;
import zw.digitalservices.Digital.Services.Customer.dto.GenerateCredentialsDto;
import zw.digitalservices.Digital.Services.Customer.dto.LoginDto;
import zw.digitalservices.Digital.Services.Customer.dto.UpdateUserDto;
import zw.digitalservices.Digital.Services.Customer.models.Login;
import zw.digitalservices.Digital.Services.Customer.models.User;
import zw.digitalservices.Digital.Services.Customer.models.api.ApiResponse;
import zw.digitalservices.Digital.Services.Customer.security.JwtTokenProvider;
import zw.digitalservices.Digital.Services.Customer.services.LoginService;
import zw.digitalservices.Digital.Services.Customer.services.RoleService;
import zw.digitalservices.Digital.Services.Customer.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Random;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/users")
@Api(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    private final UserService userService;
    private final LoginService loginService;
    private final RoleService roleService;
    private  final ModelMapper modelMapper;
    //private final SmsConfig smsConfig;
    private final RestTemplate restTemplate;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, LoginService loginService, RoleService roleService,ModelMapper modelMapper ,RestTemplate restTemplate,JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.loginService = loginService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        //this.smsConfig = smsConfig;
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

    @PostMapping(value = "/signup/{role-id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Sends OTP to a user to proceed to the questions",produces = MediaType.APPLICATION_XML_VALUE)
    public String SendRegistrationOTP(@Valid @RequestBody  AddUserDto addUserDto,
                                                       @PathVariable("role-id") Integer roleId, HttpServletRequest request)  {

        User user = modelMapper.map(addUserDto, User.class);

        // Assign the role of the user
        user.setRoles(Collections.singleton(roleService.getOne(roleId)));

        // Set user active true by default
        user.setActive(true);

        // Generate the otp which the user will use for authentication
        String otp = generateOTP(user.getOtp());

        // Set the user otp to the generated otp
        user.setOtp(otp);


        // Send an otp text message
        String appUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath();

        String uri = "https://bulksms.econet.co.zw/sms/sendsms.jsp?user=Tapuwam&password=@Tree123&mobiles="+user.getMobileNumber()+"&sms="+"Dear User Your OTP is:\n"+otp+"&senderid=Digital";


        String response = restTemplate.getForObject( uri, String.class);

        userService.add(user);


        return response;

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
    public ResponseEntity<ApiResponse> loginWithMobileNumberAndOTP(@RequestBody LoginDto accountCredentials) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(
                        accountCredentials.getMobileNumber(),
                        accountCredentials.getOtp()));
        // SecurityContextHolder.getContext().setAuthentication(authentication);
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

    @PostMapping("/generate-credentials")
    @ApiOperation(value = "Generates new OTP for user.", response = ApiResponse.class)
    public String generateCredentialsForUser(@RequestBody GenerateCredentialsDto credentialsDto)  {
        // Get user by their mobile number
        User user = userService.findByMobileNumber(credentialsDto.getMobileNumber());

        // Generate the otp which the user will use for authentication
        String otp = generateOTP(user.getOtp());

        // Set the user otp to the generated otp
        user.setOtp(otp);

        String uri = "https://bulksms.econet.co.zw/sms/sendsms.jsp?user=Tapuwam&password=@Tree123&mobiles="+user.getMobileNumber()+"&sms="+"Dear User Your One Time Pin is:\n"+otp+"&senderid=Digital";


        userService.add(user);

        String response = restTemplate.getForObject(uri,  String.class);

        return response;


    }




}




