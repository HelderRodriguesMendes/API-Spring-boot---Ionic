package com.helder.cursoSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.helder.cursoSpring.model.Categoria;
import com.helder.cursoSpring.services.CategoriaService;

@RestController //define essa classe como controller, que recebe requisição
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired// para instancia o objeto de CategoriaService de forma altomatica
	private CategoriaService service;
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) { // a anotação @PathVariable é para o spring envia o id que recebeu na requisição, para o metodo FIND
		
		Categoria obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
						
	}
}
