package com.unisoma.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unisoma.model.Jump;
import com.unisoma.model.Readjustment;
import com.unisoma.model.Result;
import com.unisoma.service.JumpService;


/**
 * A Spring {@link RestController} used to modeling of a REST controller for Product CRUD operations
 *
 * @author Odilio Noronha Filho
 */
@Validated
@RestController
@RequestMapping(
        path = "/jump"
)
public class JumpController {

	@Autowired
    private JumpService jumpService;

    
    /**
     * Reads the {@link Readjustment} with the specified id
     *
     * @param id the id of the requested {@link Readjustment}
     *
     * @return the serialized {@link Readjustment}
     */
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/resuts/{jumps}"
    )
    public ResponseEntity<Result> getResult(@Valid @RequestBody List<Jump> jumps) {
    	Optional<Result> prod = jumpService.getResults(jumps);

    	if (prod.isPresent() ) {
    		return ResponseEntity.ok(prod.get());
    	}
    		
        return ResponseEntity.notFound().build();
    }


}
