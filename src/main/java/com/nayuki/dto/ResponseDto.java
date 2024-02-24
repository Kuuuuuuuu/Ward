package com.nayuki.dto;

/**
 * ResponseDto is a values container for presenting response info
 *
 * @param message Response message field
 * @author Rudolf Barbu, Nayuki
 * @version 1.0.0
 */

public record ResponseDto(String message) {
    /**
     * Setter for message field
     *
     * @param message message to display
     */
    public ResponseDto {
    }
}