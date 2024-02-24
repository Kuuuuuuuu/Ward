package com.nayuki.services;

import com.nayuki.Ward;
import com.nayuki.components.UtilitiesComponent;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * IndexService displays index page of Ward application
 *
 * @author Rudolf Barbu, Nayuki
 * @version 1.0.1
 */
@Service
public class IndexService {
    /**
     * Autowired InfoService object
     * Used for getting machine information for html template
     */
    private final InfoService infoService;

    /**
     * Autowired UtilitiesComponent object
     * Used for various utility functions
     */
    private final UtilitiesComponent utilitiesComponent;

    public IndexService(InfoService infoService, UtilitiesComponent utilitiesComponent) {
        this.infoService = infoService;
        this.utilitiesComponent = utilitiesComponent;
    }

    /**
     * Fills model and returns template name
     *
     * @param model strings container
     * @return template name
     */
    public String getIndex(Model model) throws Exception {
        if (Ward.isFirstLaunch()) {
            return "setup";
        }

        model.addAttribute("info", infoService.getInfo());
        model.addAttribute("theme", utilitiesComponent.getThemeName());

        return "index";
    }
}