package orsk.compli.service.neo4j;

import org.hibernate.event.service.spi.EventListenerRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orsk.compli.dtos.*;
import orsk.compli.entities.neo4j.neo4jPasswordResetToken;
import orsk.compli.entities.neo4j.neo4jRefreshToken;
import orsk.compli.entities.neo4j.User;
import orsk.compli.entities.neo4j.neo4jVerificationToken;
import orsk.compli.repository.neo4j.PasswordResetTokenneo4jRepository;
import orsk.compli.repository.neo4j.Roleneo4jRepository;
import orsk.compli.repository.neo4j.Userneo4jRepository;
import orsk.compli.repository.neo4j.VerificationTokenneo4jRepository;
import orsk.compli.security.JwtTokenProvider;
import orsk.compli.service.neo4j.RefreshTokenneo4jService;

import javax.management.relation.RoleNotFoundException;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Service("neo4jAuthService")
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Userneo4jRepository userRepository;

    @Autowired
    private Roleneo4jRepository roleRepository;

    @Autowired
    private VerificationTokenneo4jRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenneo4jRepository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private RefreshTokenneo4jService refreshTokenService;



    @Transactional
    public void registerUser(RegistrationRequest registrationRequest) {
        if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }

        try {
            neo4jUser user = new neo4jUser();
            user.setUsername(registrationRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setEmail(registrationRequest.getEmail());
            user.setConsentToDataUsage(registrationRequest.getConsentToDataUsage());
            user.setEnabled(false);

            user.setRoles(Set.of(roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RoleNotFoundException("ROLE_USER not found"))));

            userRepository.save(user);

            String token = generateVerificationToken(user);
            // emailService.sendVerificationEmail(user.getEmail(), token);

        } catch (Exception e) {
            throw new EventListenerRegistrationException("Registration failed: " + e.getMessage(), e);
        }
    }

    @Transactional
    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        String jwt = tokenProvider.generateToken(authentication);

        neo4jRefreshToken refreshToken = refreshTokenService.createRefreshToken(authentication.getName());

        return new JwtAuthenticationResponse(jwt, refreshToken.getToken());
    }

    @Transactional
    public JwtAuthenticationResponse refreshToken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(neo4jRefreshToken::getUser)
                .map(user -> {
                    String token = tokenProvider.generateTokenFromUsername(user.getUsername());
                    return new JwtAuthenticationResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new RuntimeException("Refresh token not found or expired"));
    }

    @Transactional
    public void logoutUser(LogoutRequest logoutRequest) {
        refreshTokenService.deleteByToken(logoutRequest.getRefreshToken());
    }

    @Transactional
    public void initiatePasswordReset(PasswordResetRequest passwordResetRequest) {
        neo4jUser user = userRepository.findByEmail(passwordResetRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        String token = UUID.randomUUID().toString();
        neo4jPasswordResetToken neo4jPasswordResetToken = new neo4jPasswordResetToken();
        neo4jPasswordResetToken.setToken(token);
        neo4jPasswordResetToken.setUser(user);
        neo4jPasswordResetToken.setExpiryDate(Instant.now().plusSeconds(3600)); // 1 hour expiry

        passwordResetTokenRepository.save(neo4jPasswordResetToken);

      }

    @Transactional
    public void changePassword(PasswordChangeRequest passwordChangeRequest) {
        validatePassword(String.valueOf(passwordChangeRequest));
        neo4jPasswordResetToken neo4jPasswordResetToken = passwordResetTokenRepository.findByToken(passwordChangeRequest.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid password reset token"));

        if (neo4jPasswordResetToken.getExpiryDate().isBefore(Instant.now())) {
            throw new RuntimeException("Password reset token expired");
        }

        neo4jUser user = neo4jPasswordResetToken.getUser();
        user.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));

        userRepository.save(user);

        passwordResetTokenRepository.delete(neo4jPasswordResetToken);
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter");
        }
        if (!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Password must contain at least one number");
        }
    }

    @Transactional
    public void verifyEmail(String token) {
        neo4jVerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid verification token"));

        neo4jUser user = verificationToken.getUser();
        user.setEnabled(true);

        userRepository.save(user);

        verificationTokenRepository.delete(verificationToken);
    }

    private String generateVerificationToken(neo4jUser user) {
        String token = UUID.randomUUID().toString();

        neo4jVerificationToken verificationToken = new neo4jVerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(Instant.now().plusSeconds(86400)); // 24 hours expiry

        verificationTokenRepository.save(verificationToken);

        return token;
    }
}
