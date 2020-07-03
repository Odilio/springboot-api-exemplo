package com.unisoma.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * The persistent class for the users database table.
 * 
 */
@Data
public class Jump implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	@DecimalMin(value = "1.2", message="Valor mínimo é 1.2")
	@DecimalMax(value = "3.6", message="Valor máximo é 3.6")
	private Double difficult;
	
	
	@Size(min = 7, max = 7, message = "Quantidade de notas informadas deve ser 7")
	private List<
	@DecimalMin(value = "0.0", message="Valor mínimo é 0.0")
	@DecimalMax(value = "10.0", message="Valor máximo é 10.0")
	Double> notes;
	
	private Double result;

}