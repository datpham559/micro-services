package datpt.spring.controller;

import datpt.spring.dto.AuthRequest;
import datpt.spring.dto.AuthResponse;
import datpt.spring.security.UserDetailService;
import datpt.spring.security.jwt.TokenProvider;
import datpt.spring.service.utils.UtilsService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class JWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;
    private final UtilsService utilsService;

    public JWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager, UtilsService utilsService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.utilsService = utilsService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        final String accessToken = tokenProvider.generateAccessToken(authRequest.getUsername());
        final String refreshToken = tokenProvider.generateRefreshToken(authRequest.getUsername());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/add-token-blacklist")
    public ResponseEntity<String> addBlackListToken(@RequestParam String token) {
        String key = "blacklist:" + token.substring(7);
        if (token == null || token.length() < 8 || !token.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token không hợp lệ", HttpStatus.BAD_REQUEST);
        }

        token = token.substring(7);
        long ttl = tokenProvider.getTokenExpirationInSeconds(token);
        if (ttl < 0) {
            return new ResponseEntity<>( "Token đã hết hạn", HttpStatus.OK);
        }
        utilsService.setKeyRedis(key, "1", ttl);

        return new ResponseEntity<>("Thành công", HttpStatus.OK);
    }

}
