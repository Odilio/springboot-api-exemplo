package com.unisoma.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisoma.model.Readjustment;
import com.unisoma.repository.ReadjustmentRepository;

@Service
public class ReadjustmentService {

	@Autowired
    private ReadjustmentRepository readjustmentRepository;

    public void create(final Readjustment readjustmentDTO) {
        readjustmentRepository.save(readjustmentDTO);
    }

    public Optional<Readjustment> get(Long id) {
        final Optional<Readjustment> readjustment =
                readjustmentRepository.findById(id);
        return readjustment;
    }
    
    public Optional<Readjustment> getBySalary(Double salary) {
    	 final Optional<Readjustment> readjustment =
    			 readjustmentRepository.findBySalary(salary);
    	 
    	 return setSalary(readjustment.get(),salary);
	} 

	public List<Readjustment> getAll() {
        return readjustmentRepository.findAll();
    }
	
    private Optional<Readjustment> setSalary(Readjustment readjustment, Double salary) {
		Double readjustGain = salary * readjustment.getPercent() / 100;
		Double newSalary = salary + readjustGain;
		readjustment.setNewSalary(new DecimalFormat("#,##0.00").format(newSalary));
		readjustment.setReadjustment(new DecimalFormat("#,##0.00").format(readjustGain));
		
		return Optional.of(readjustment);
	}

}
