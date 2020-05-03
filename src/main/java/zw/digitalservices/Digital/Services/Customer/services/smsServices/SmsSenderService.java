package zw.digitalservices.Digital.Services.Customer.services.smsServices;

/*public class SmsSenderService {

    private static final String url = "https://bulksms.econet.co.zw/sms/sendsms.jsp?";

    private final SmsConfig smsConfig;
    private  final User user;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    @Autowired
    public SmsSenderService(SmsConfig smsConfig, User user, ModelMapper modelMapper, RestTemplateBuilder restTemplateBuilder){
        this.smsConfig = smsConfig;
        this.user = user;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplateBuilder.build();
    }

    public SmsRequest SendRegistrationSmsRequest(@Valid @RequestBody SmsRequestBody smsRequestBody ) {
        SmsRequest smsRequest = modelMapper.map(smsRequestBody, SmsRequest.class);

        // Generate the otp for user
        String otp = generateOTP(user.getMobileNumber());

        // Set the user password to the generated password
        user.setOtp(otp);


        smsRequest.setUser(smsConfig.getUserName());
        smsRequest.setPassword(smsRequest.getPassword());
        smsRequest.setSenderId(smsConfig.getSenderId());
        smsRequest.setMobiles(user.getMobileNumber());
        smsRequest.setSms(" Dear " + user.getMobileNumber() + "You have been Signed in  as a on the Digital Services Band Platform your OTP is:\n"  + "OTP\n"+ otp +"Use it within 24 hours to proceed to the questionnaire");

        SmsRequest registrationSms = this.restTemplate.postForObject( url,smsRequest, SmsRequest.class);

        return registrationSms;

    }
    private String generateOTP(String otp) {
        String generatedOTP;
        // Generate random number to be used as the OTP
        Random randomNumber = new Random();
        int n = 1000 + randomNumber.nextInt(9999);
        generatedOTP = (String.valueOf(n));
        return generatedOTP;


    }
}

 */
