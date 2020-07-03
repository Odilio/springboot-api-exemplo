package com.unisoma.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="readjustments")
@NoArgsConstructor
public class Readjustment implements Serializable {

	private static final long serialVersionUID = -5752805178427746354L;

	@Id
	@Column
	@NotNull
    private Long id;

	@Transient
    private String newSalary;
	
	@Transient
    private String readjustment;
	
	@Column
    private Long percent;
	
	@Column(name="minsalary")
    private Double minSalary;
    
	@Column(name="maxsalary")
    private Double maxSalary;
	
	@Transient
    private Double salary;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	public Readjustment(Long number, String string) {
		// TODO Auto-generated constructor stub
	}
	
}
