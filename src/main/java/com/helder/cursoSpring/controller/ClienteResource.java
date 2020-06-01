package com.helder.cursoSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.helder.cursoSpring.model.Cliente;
import com.helder.cursoSpring.repositories.ClienteRepository;
import com.helder.cursoSpring.services.ClienteService;

@RestController //define essa classe como controller, que recebe requisição
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired// para instancia o objeto de ClienteService de forma altomatica
	private ClienteService service;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) { // a anotação @PathVariable é para o spring envia o id que recebeu na requisição, para o metodo FIND
		
		Cliente obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
						
	}
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
}
