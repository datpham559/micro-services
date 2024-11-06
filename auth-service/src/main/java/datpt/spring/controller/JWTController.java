package datpt.spring.controller;

import datpt.spring.dto.AuthRequest;
import datpt.spring.dto.AuthResponse;
import datpt.spring.security.UserDetailService;
import datpt.spring.security.jwt.TokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class JWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailsService;

    public JWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager, UserDetailService userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String accessToken = tokenProvider.generateAccessToken(userDetails.getUsername());
        final String refreshToken = tokenProvider.generateRefreshToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }
}
