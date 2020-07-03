package com.unisoma.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.unisoma.model.Readjustment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO (Data Transfer Object) used to serialize / deserialize {@link Readjustment} objects
 *
 * @author Odilio Noronha Filho
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadjustmentDTO implements Serializable {
   
	private static final long serialVersionUID = 1L;
	
	private String newSalary;
	
    private String readjustment;
	
    private Long percent;
    
    


}
