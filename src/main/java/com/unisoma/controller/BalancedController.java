package com.unisoma.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unisoma.service.BalancedService;


/**
 * A Spring {@link RestController} used to modeling of a REST controller for Product CRUD operations
 *
 * @author Odilio Noronha Filho
 */
@Validated
@RestController
@RequestMapping(
        path = "/balanced"
)
public class BalancedController {

	@Autowired
    private BalancedService balancedService;

    
    /**
     */
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/balanced"
    )
    public ResponseEntity<List<String>> getResult(@Valid @RequestBody List<@Size(min = 1, max = 1000)String> expressions) {
    	List<String> prod = balancedService.getBalanced(expressions);

    	if (prod != null && prod.size() > 0 ) {
    		return ResponseEntity.ok(prod);
    	}
    		
        return ResponseEntity.notFound().build();
    }


}
