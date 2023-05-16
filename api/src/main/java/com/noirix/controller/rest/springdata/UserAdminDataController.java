package com.noirix.controller.rest.springdata;

import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.springdata.UserDataRepository;
import com.noirix.security.util.PrincipalUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAdminDataController {

    private final UserDataRepository repository;

    private final PrincipalUtils principalUtils;

    @Operation(
            summary = "Spring Data User Find All Search",
            description = "Find All Users without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Users",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HibernateUser.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllUsers(@Parameter(hidden = true) Principal principal) {

        String username = principalUtils.getUsername(principal);
        Optional<HibernateUser> byAuthenticationInfoEmail = repository.findByAuthenticationInfoEmail(username);

        List<HibernateUser> users = repository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
