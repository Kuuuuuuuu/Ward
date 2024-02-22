package com.nayuki.controllers;

import com.nayuki.services.InfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InfoController displays responses from rest API
 *
 * @author Rudolf Barbu, Nayuki
 * @version 1.0.1
 */
@RestController
@RequestMapping(value = "/api/info")
public class InfoController {
    /**
     * Autowired InfoService object
     * Used for getting information about server
     */
    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    /**
     * Get request to display current usage information for processor, RAM and storage
     *
     * @return ResponseEntity to servlet
     */
    @GetMapping
    public ResponseEntity<?> getInfo() throws Exception {
        return new ResponseEntity<>(infoService.getInfo(), HttpStatus.OK);
    }
}