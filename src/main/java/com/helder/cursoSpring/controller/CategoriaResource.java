package com.helder.cursoSpring.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.helder.cursoSpring.model.Categoria;
import com.helder.cursoSpring.services.CategoriaService;

@RestController //define essa classe como controller, que recebe requisição
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired// para instancia o objeto de CategoriaService de forma altomatica
	private CategoriaService categoriaService;
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) { // a anotação @PathVariable é para o spring envia o id que recebeu na requisição, para o metodo FIND
		
		Categoria obj = categoriaService.find(id);
		
		return ResponseEntity.ok().body(obj);		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria){
		categoria = categoriaService.insert(categoria);
		
		
		/*RETORNA O ID DA CATEGORIA QUE FOI CRIADA, PARA A URL DA REQUISIÇÃO
		 * 
		 * fromCurrentRequest: PEGA A URL DA REQUISIÇÃO
		 * path("/{id}"): INSERI UM ID NA URL
		 * buildAndExpand(categoria.getId()): INFORMA QUAL É O ID
		 * toUri(): CONVERTE O ID PARA O TIPO URI
		 */
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
