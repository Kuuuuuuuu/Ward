package com.nayuki.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * SetupDto is a values container for setup data
 *
 * @author Rudolf Barbu, Nayuki
 * @version 1.0.3
 */
@Getter
@Setter
@JsonIgnoreProperties(value = {"theme", "port"}, allowSetters = true)
public class SetupDto
{
    /**
     * Server name Field
     */
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 10)
    private String serverName;

    /**
     * Theme name field
     */
    @NotNull
    @NotEmpty
    @Pattern(regexp = "light|dark")
    private String theme;

    /**
     * Port port field
     */
    @NotNull
    @NotEmpty
    @Min(value = 10)
    @Max(value = 65535)
    private String port;
}