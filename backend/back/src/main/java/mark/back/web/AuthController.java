package mark.back.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mark.back.entity.RefreshToken;
import mark.back.entity.User;
import mark.back.pojo.AuthRequest;
import mark.back.pojo.AuthResponse;
import mark.back.repository.RefreshTokenRepository;
import mark.back.security.JWTUtil;
import mark.back.service.RefreshTokenService;
import mark.back.service.UserService;
import org.postgresql.plugin.AuthenticationRequestType;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(
        origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}
)
@AllArgsConstructor
public class AuthController {
    AuthenticationManager authenticationManager;
    RefreshTokenService refreshTokenService;
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
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userService.getUser((String) authentication.getPrincipal());

        //this will delete previous refresh token if it already exists
        refreshTokenService.deleteToken(user);
        //create refresh token object
        RefreshToken refreshTokenObj = refreshTokenService.createRefreshToken(user);

        //generate both access and refresh tokens
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user, refreshTokenObj);


        AuthResponse response = new AuthResponse(user.getId(), accessToken, refreshToken);
        return ResponseEntity.ok(response);
    }

    //sign out
    @PostMapping("/signout")
    public ResponseEntity<?> signOut(@RequestBody AuthResponse auth) {
        String refreshToken = auth.getRefreshToken();
        if (jwtUtil.validateRefreshToken(refreshToken) && refreshTokenService.getRefreshToken(jwtUtil.getTokenIdFromRefreshToken(refreshToken)) != null) {
            refreshTokenService.deleteToken(userService.getUser(auth.getId()));
            return ResponseEntity.ok().build();
        }

        throw new BadCredentialsException("invalid token");
    }


}
