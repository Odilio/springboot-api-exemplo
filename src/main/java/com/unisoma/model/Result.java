package com.unisoma.model;

import java.io.Serializable;
import java.util.List;

import com.unisoma.model.dto.JumpDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The persistent class for the users database table.
 * 
 */
@Data
@AllArgsConstructor
public class Result implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<JumpDTO> results;

}