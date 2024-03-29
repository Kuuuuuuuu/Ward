package com.nayuki.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * MachineDto is a value container for presenting machine principal information
 *
 * @author Rudolf Barbu, Nayuki
 * @version 1.0.0
 */
@Getter
@Setter
public class MachineDto {
    /**
     * OS info field
     */
    private String operatingSystem;

    /**
     * Amount of total installed ram field
     */
    private String totalRam;

    /**
     * Ram generation field
     */
    private String ramTypeOrOSBitDepth;

    /**
     * Processes count field
     */
    private String procCount;

    /**
     * Threads count field
     */
    private String threadCount;

    /**
     * Network info field
     */
    private String networkInfo;
}