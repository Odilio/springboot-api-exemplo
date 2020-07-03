package com.unisoma.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * The persistent class for the users database table.
 * 
 */
@Data
@AllArgsConstructor
public class JumpDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	
	private Double result;

}