package com.nayuki.controllers;

import com.nayuki.exceptions.ApplicationNotSetUpException;
import com.nayuki.services.UsageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UsageController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.1
 */
@RestController
@RequestMapping(value = "/api/usage")
public class UsageController
{
    /**
     * Autowired UsageService object
     * Used for getting usage information
     */
    private final UsageService usageService;

    public UsageController(UsageService usageService) {
        this.usageService = usageService;
    }

    /**
     * Get request to display current usage information for processor, RAM and storage
     *
     * @return ResponseEntity to servlet
     */
    @GetMapping
    public ResponseEntity<?> getUsage() throws ApplicationNotSetUpException
    {
        return new ResponseEntity<>(usageService.getUsage(), HttpStatus.OK);
    }
}