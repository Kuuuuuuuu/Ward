package com.nayuki.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * MavenDto is a values container for presenting maven fields information
 *
 * @author Rudolf Barbu, Nayuki
 * @version 1.0.0
 */
@Getter
@Setter
public class ProjectDto {
    /**
     * Project version field
     */
    private String version;
}