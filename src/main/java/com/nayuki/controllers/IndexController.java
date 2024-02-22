package com.nayuki.controllers;

import com.nayuki.services.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController displays index page of Ward application
 *
 * @author Rudolf Barbu, Nayuki
 * @version 1.0.2
 */
@Controller
@RequestMapping(value = "/")
public class IndexController
{
    /**
     * Autowired IndexService object
     * Used for getting index page template
     */
    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    /**
     * Get request to display index page
     *
     * @param model used for providing values in to html template
     * @return String name of html template with values from model param
     */
    @GetMapping
    public String getIndex(Model model) throws Exception
    {
        return indexService.getIndex(model);
    }
}