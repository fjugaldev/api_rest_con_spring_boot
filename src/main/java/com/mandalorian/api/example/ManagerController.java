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
@RequestMapping(path = "/v1/manager")
@Tag(name = "Manager", description = "Endpoints para operaciones de usuarios Manager")
public class ManagerController {

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
        return ResponseEntity.ok("MANAGER OK CREATE");
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
        return ResponseEntity.ok("MANAGER OK READ");
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
        return ResponseEntity.ok("MANAGER OK UPDATE");
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
        return ResponseEntity.ok("MANAGER OK DELETE");
    }

}
