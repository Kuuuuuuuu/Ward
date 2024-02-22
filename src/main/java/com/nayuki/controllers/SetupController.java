package com.nayuki.controllers;

import jakarta.validation.Valid;
import com.nayuki.dto.SetupDto;
import com.nayuki.services.SetupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SetupController displays responses from rest API
 *
 * @author Rudolf Barbu, Nayuki
 * @version 1.0.1
 */
@RestController
@RequestMapping(value = "/api/setup")
public class SetupController {
    /**
     * Autowired SetupService object
     * Used for posting settings information in ini file
     */
    private final SetupService setupService;

    public SetupController(SetupService setupService) {
        this.setupService = setupService;
    }

    /**
     * Posting setup info in database
     *
     * @param setupDto dto with data
     * @return ResponseEntity to servlet
     */
    @PostMapping
    public ResponseEntity<?> postSetup(@RequestBody @Valid SetupDto setupDto) throws Exception {
        return new ResponseEntity<>(setupService.postSetup(setupDto), HttpStatus.OK);
    }
}