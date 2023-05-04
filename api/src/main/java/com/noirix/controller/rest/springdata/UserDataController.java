package com.noirix.controller.rest.springdata;

import com.noirix.controller.exceptions.IllegalRequestException;
import com.noirix.controller.requests.SearchCriteria;
import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.controller.requests.UserUpdateRequest;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.springdata.UserDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/springdata/users")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataRepository repository;

    private final ConversionService conversionService;

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
    public ResponseEntity<Object> getAllUsers() {
        List<HibernateUser> users = repository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/test")
    public ResponseEntity<Object> testSpringDataMethods() {

        List<HibernateUser> result =
                repository.findByHQLQuery("slava.kalevich.27@noirix.com", "Kalevich");
        //repository.findUsers();
        //List<Object[]> result = repository.findColumnsFromDifferentTables();

        return new ResponseEntity<>(Collections.singletonMap("result", result), HttpStatus.OK);
    }

    @Operation(
            summary = "Spring Data User Search with Pageable Params",
            description = "Load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Users",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> testEndpoint(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {
        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll(PageRequest.of(page, 4))), HttpStatus.OK);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PostMapping
    public ResponseEntity<Object> saveUser(@Valid @RequestBody UserCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        HibernateUser hibernateUser = conversionService.convert(request, HibernateUser.class);
        hibernateUser = repository.save(hibernateUser);
        return new ResponseEntity<>(hibernateUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserUpdateRequest request) {

        HibernateUser hibernateUser = conversionService.convert(request, HibernateUser.class);
        hibernateUser = repository.save(hibernateUser);
        return new ResponseEntity<>(hibernateUser, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchUsersByFullName(@Valid @ModelAttribute SearchCriteria criteria, BindingResult result) {
        System.out.println(result);

        Double parsedWeight;

        try {
            parsedWeight = Double.parseDouble(criteria.getWeight());
        } catch (NumberFormatException e) {
            parsedWeight = 50D;
        }

        List<HibernateUser> searchList = Collections.emptyList();//userService.search(criteria.getQuery(), parsedWeight);

        return new ResponseEntity<>(Collections.singletonMap("users", searchList), HttpStatus.OK);
    }
}
