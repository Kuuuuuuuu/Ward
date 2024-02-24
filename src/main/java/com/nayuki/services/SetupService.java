package com.nayuki.services;

import com.nayuki.Ward;
import com.nayuki.dto.ResponseDto;
import com.nayuki.dto.SetupDto;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        if (Ward.isFirstLaunch()) {
            try {
                File file = new File(Ward.SETUP_FILE_PATH);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("[setup]\n");
                    writer.write("serverName=" + setupDto.getServerName() + "\n");
                    writer.write("theme=" + setupDto.getTheme() + "\n");
                    writer.write("port=" + setupDto.getPort() + "\n");
                } catch (IOException e) {
                    return new ResponseDto("Error occurred while writing to setup file");
                }

                Ward.restart();
            } catch (Exception e) {
                return new ResponseDto("An unexpected error occurred");
            }
        }

        return new ResponseDto("Settings saved correctly");
    }
}