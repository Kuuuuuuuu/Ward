package com.nayuki.services;

import com.nayuki.Ward;
import com.nayuki.dto.ResponseDto;
import com.nayuki.dto.SetupDto;
import org.ini4j.Wini;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * SetupService manipulating setup data
 *
 * @author Rudolf Barbu, Nayuki
 * @version 1.0.2
 */
@Service
public class SetupService {
    /**
     * Fills setup data in ini file
     *
     * @param setupDto user settings data
     * @return ResponseDto with a message
     */
    public ResponseDto postSetup(SetupDto setupDto) {
        try {
            File file = new File(Ward.SETUP_FILE_PATH);

            if (Ward.isFirstLaunch()) {
                Map<String, String> setupProperties = Map.of(
                        "serverName", setupDto.getServerName(),
                        "theme", setupDto.getTheme(),
                        "port", setupDto.getPort()
                );

                putInIniFile(file, setupProperties);

                CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(Ward::restart);

                return new ResponseDto("Settings saved correctly");
            } else {
                return new ResponseDto("Settings already exist");
            }
        } catch (IOException e) {
            System.err.println("Error while saving settings");
            return new ResponseDto("Error while saving settings");
        }
    }

    /**
     * Puts new data in ini file
     *
     * @param file       ini file
     * @param properties map with properties
     * @throws IOException if there's an issue with file operations
     */
    private void putInIniFile(File file, Map<String, String> properties) throws IOException {
        if (!file.exists()) {
            boolean isFileCreated = file.createNewFile();
            if (!isFileCreated) {
                throw new IOException("Error while creating setup.ini file");
            }
        }

        Wini wini = new Wini(file);

        for (Map.Entry<String, String> entry : properties.entrySet()) {
            System.out.println("[INFO] Setup data: " + entry.getKey() + " = " + entry.getValue());
            wini.put("setup", entry.getKey(), entry.getValue());
        }

        wini.store();
        System.out.println("[INFO] Setup data saved in setup.ini file");
    }
}