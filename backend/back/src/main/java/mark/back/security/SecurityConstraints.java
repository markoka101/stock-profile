package mark.back.security;

public class SecurityConstraints {
    public static final String SECRET_KEY = "bQeThWmZq4t7w!z$C&F)J@NcRertd2r5u8x/A?D*G-KaPdSgVkYp3s6v9y$B&E)";
    public static final String BEARER = "Bearer ";
    public static final String REGISTER_PATH = "/auth/signup";
    public static final String LOGIN_PATH = "/auth/login";
    public static final int TOKEN_EXPIRATION = 3600000;
}