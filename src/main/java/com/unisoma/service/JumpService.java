package com.unisoma.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.unisoma.model.Jump;
import com.unisoma.model.Result;
import com.unisoma.model.dto.JumpDTO;

@Service
public class JumpService {
    
    public Optional<Result> getResults(List<Jump> jumps) {
    	 Result result = new Result(calcNotes(jumps));
    	 return Optional.of(result);
	}

	private List<JumpDTO> calcNotes(List<Jump> jumps) {
		List<JumpDTO> jumpsDto = new ArrayList<JumpDTO>();
		
		for (Jump jump : jumps) {
			Collections.sort(jump.getNotes());
			jump.getNotes().remove(jump.getNotes().size()-1);
			jump.getNotes().remove(0);
			Double sum = jump.getNotes().stream()
					  .collect(Collectors.summingDouble(Double::doubleValue));
			jumpsDto.add(new JumpDTO(jump.getName(),sum * jump.getDifficult()));
		}
		return jumpsDto;
	}

 

  
}
