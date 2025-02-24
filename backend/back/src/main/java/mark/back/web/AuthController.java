package mark.back.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mark.back.entity.RefreshToken;
import mark.back.entity.User;
import mark.back.pojo.AuthRequest;
import mark.back.pojo.AuthResponse;
import mark.back.repository.RefreshTokenRepository;
import mark.back.security.JWTUtil;
import mark.back.service.UserService;
import org.postgresql.plugin.AuthenticationRequestType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(
        origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}
)
@AllArgsConstructor
public class AuthController {
    AuthenticationManager authenticationManager;
    RefreshTokenRepository refreshTokenRepository;
    UserService userService;
    JWTUtil jwtUtil;

    //register account
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //sign in
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        User user = userService.getUser((String) authentication.getPrincipal());

        RefreshToken refreshTokenObj = new RefreshToken();
        //generate both access and refresh tokens
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user, refreshTokenObj);

        AuthResponse response = new AuthResponse(user.getId(), accessToken, refreshToken);
        return ResponseEntity.ok(response);

    }


}
