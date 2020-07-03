package com.unisoma.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unisoma.model.Readjustment;
import com.unisoma.model.dto.ReadjustmentDTO;
import com.unisoma.service.ReadjustmentService;
import com.unisoma.util.Converter;


/**
 * A Spring {@link RestController} used to modeling of a REST controller for Product CRUD operations
 *
 * @author Odilio Noronha Filho
 */
@RestController
@RequestMapping(
        path = "/readjustment"
)
public class ReadjustmentController {

	@Autowired
    private ReadjustmentService readjustmentService;
    
    
    /**
     * Creates a {@link Readjustment} from the referenced {@link ReadjustmentDTO}
     *
     * @param readjustmentDTO the {@link Readjustment} to be created
     *
     * @return a {@link ResponseEntity} with the appropriate {@link HttpStatus}
     */
    @PostMapping
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody Readjustment readjustment) {
        readjustmentService.create(readjustment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Reads the {@link Readjustment} with the specified id
     *
     * @param id the id of the requested {@link Readjustment}
     *
     * @return the serialized {@link Readjustment}
     */
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{id}"
    )
    public ResponseEntity<ReadjustmentDTO> getReadjustment(@PathVariable Long id) {
    	Optional<Readjustment> prod = readjustmentService.get(id);

    	if (prod.isPresent() ) {
    		// TODO Create a Dto using com.unisoma.util.Converter Class
    		ReadjustmentDTO prodDTO = (ReadjustmentDTO) Converter.toModel(prod.get(), ReadjustmentDTO.class);

    		return ResponseEntity.ok(prodDTO);
    	}
    		
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Reads the {@link Readjustment} with the specified id
     *
     * @param id the id of the requested {@link Readjustment}
     *
     * @return the serialized {@link Readjustment}
     */
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/salary/{salary}"
    )
    public ResponseEntity<ReadjustmentDTO> getReadjustmentBySalary(@PathVariable Double salary) {
    	Optional<Readjustment> prod = readjustmentService.getBySalary(salary);

    	if (prod.isPresent() ) {
    		// TODO Create a Dto using com.unisoma.util.Converter Class
    		ReadjustmentDTO prodDTO = (ReadjustmentDTO) Converter.toModel(prod.get(), ReadjustmentDTO.class);

    		return ResponseEntity.ok(prodDTO);
    	}
    		
        return ResponseEntity.notFound().build();
    }

    /**
     * Reads all the existing {@link Readjustment}s
     *
     * @return the serialized {@link ReadjustmentDTO}s
     */
    @RequestMapping(
            method = RequestMethod.GET,
            path = ""
    )
    public List<ReadjustmentDTO> getAll() {
        return (List<ReadjustmentDTO>)Converter.toCollection(readjustmentService.getAll(), ReadjustmentDTO.class);
    }

    /**
     * Updates the {@link Product} with the specified ID with the details from the referenced {@link Product}
     *
     * @return a {@link ResponseEntity} with the appropriate {@link HttpStatus}
     
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable final int id, @RequestBody ProductDTO readjustmentDTO) {
        readjustmentService.update(id, readjustmentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
*/
    /**
     * Deletes the {@link Readjustment} with the specified ID
     *
     * @return a {@link ResponseEntity} with the appropriate {@link HttpStatus}
     */
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/{id}"
    )
    public ResponseEntity<HttpStatus> delete(@PathVariable final int id) {
        readjustmentService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
