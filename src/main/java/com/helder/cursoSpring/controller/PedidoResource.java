package com.helder.cursoSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.helder.cursoSpring.model.Pedido;
import com.helder.cursoSpring.services.PedidoService;

@RestController //define essa classe como controller, que recebe requisição
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired// para instancia o objeto de PedidoService de forma altomatica
	private PedidoService service;
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) { // a anotação @PathVariable é para o spring envia o id que recebeu na requisição, para o metodo FIND
		
		Pedido obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
						
	}
}
