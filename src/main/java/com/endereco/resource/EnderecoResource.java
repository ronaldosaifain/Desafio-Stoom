package com.endereco.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.endereco.domain.Endereco;
import com.endereco.service.EnderecoService;


@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@Autowired
	EnderecoService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Endereco>> findAll() {

		List<Endereco> listEnderecos = service.findAll();

		return ResponseEntity.ok(listEnderecos);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Endereco> find(@PathVariable Integer id) {

		Endereco endereco = service.find(id);

		return ResponseEntity.ok(endereco);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Endereco endereco) {

		Endereco obj = service.insert(endereco);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Endereco endereco, @PathVariable Integer id) {
		endereco.setId(id);
		service.insert(endereco);

		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Void> partialUpdate(@RequestBody Endereco endereco, @PathVariable Integer id) {
		endereco.setId(id);
		service.update(endereco);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
