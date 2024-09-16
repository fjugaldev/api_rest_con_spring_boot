package com.mandalorian.api.example;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/admin")
@Tag(name = "Admin", description = "Endpoints para operaciones de usuarios ADMIN")
public class AdminController {

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK - Usuario con Accesso",
            content = {
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = String.class)))
            }
        ),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN - Usuario sin acceso al recurso")
    })
    @PostMapping
    public ResponseEntity<?> create() {
        return ResponseEntity.ok("ADMIN OK CREATE");
    }

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK - Usuario con Accesso",
            content = {
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = String.class)))
            }
        ),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN - Usuario sin acceso al recurso")
    })
    @GetMapping
    public ResponseEntity<?> read() {
        return ResponseEntity.ok("ADMIN OK READ");
    }

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK - Usuario con Accesso",
            content = {
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = String.class)))
            }
        ),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN - Usuario sin acceso al recurso")
    })
    @PutMapping
    public ResponseEntity<?> update() {
        return ResponseEntity.ok("ADMIN OK UPDATE");
    }

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK - Usuario con Accesso",
            content = {
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = String.class)))
            }
        ),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN - Usuario sin acceso al recurso")
    })
    @DeleteMapping
    public ResponseEntity<?> delete() {
        return ResponseEntity.ok("ADMIN OK DELETE");
    }

}
