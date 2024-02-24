package com.nayuki.components;

import com.nayuki.Ward;
import org.ini4j.Ini;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * UtilitiesComponent provides various functions, which are used in different classes
 *
 * @author Rudolf Barbu, Nayuki
 * @version 1.0.2
 */
@Component
public class UtilitiesComponent {
    /**
     * Gets string data from ini file
     *
     * @param file        ini file
     * @param sectionName section in ini file
     * @param optionName  option in section
     * @return String wth parsed data
     * @throws IOException if file does not exist
     */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public String getFromIniFile(File file, String sectionName, String optionName) throws IOException {
        return file.exists() ? new Ini(file).get(sectionName, optionName, String.class) : null;
    }

    /**
     * Gets theme name from setup ini file
     *
     * @return String wth theme name
     * @throws IOException if file does not exist
     */
    public String getThemeName() throws IOException {
        return getFromIniFile(new File(Ward.SETUP_FILE_PATH), "setup", "theme");
    }
}