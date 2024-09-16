package com.mandalorian.api.auth;

import com.mandalorian.api.shared.responses.http.KOResponse;
import com.mandalorian.api.shared.responses.http.OKResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Authentication")
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@RestController
public class AuthenticationController {

    private final AuthenticationService service;

    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = OKResponse.class)))
                }
            ),
            @ApiResponse(
                responseCode = "400",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = KOResponse.class)))
                }
            ),
            @ApiResponse(
                responseCode = "500",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = KOResponse.class)))
                }
            )
        }
    )
    @PostMapping("/sign-up")
    public ResponseEntity<OKResponse> register(
            @RequestBody SignUpRequest request
    ) {
        return ResponseEntity.ok(
            OKResponse.builder()
                .data(service.signUp(request))
                .build()
            );
    }

    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = AuthenticationResponse.class)))
                }
            ),
            @ApiResponse(
                responseCode = "400",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = KOResponse.class)))
                }
            ),
            @ApiResponse(
                responseCode = "500",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = KOResponse.class)))
                }
            )
        }
    )
    @PostMapping("/sign-in")
    public ResponseEntity<OKResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(
            OKResponse.builder()
                .data(service.authenticate(request))
                .build()
        );
    }

    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = AuthenticationResponse.class)))
                }
            ),
            @ApiResponse(
                responseCode = "400",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = KOResponse.class)))
                }
            ),
            @ApiResponse(
                responseCode = "403",
                description = "Forbidden Error"
            ),
            @ApiResponse(
                responseCode = "500",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = KOResponse.class)))
                }
            )
        }
    )
    @PutMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        return ResponseEntity.ok(service.refreshToken(request, response));
    }
}
