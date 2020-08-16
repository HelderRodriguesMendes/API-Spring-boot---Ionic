package com.helder.cursoSpring.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helder.cursoSpring.dto.ClienteDTO;
import com.helder.cursoSpring.model.Cliente;
import com.helder.cursoSpring.services.ClienteService;

@RestController //define essa classe como controller, que recebe requisição
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired// para instancia o objeto de ClienteService de forma altomatica
	private ClienteService clienteService;
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) { // a anotação @PathVariable é para o spring envia o id que recebeu na requisição, para o metodo FIND
		
		Cliente obj = clienteService.find(id);
		
		return ResponseEntity.ok().body(obj);
						
	}
	
	@RequestMapping(value= "/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarNome(@RequestParam("nome") String nome) { // a anotação @PathVariable é para o spring envia o id que recebeu na requisição, para o metodo FIND
		
		Cliente obj = clienteService.pesquisarNome(nome);
		
		return ResponseEntity.ok().body(obj);
						
	}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO categoriaDTO, @PathVariable Integer id){
		Cliente categoria = clienteService.fromDTO(categoriaDTO);
		categoria.setId(id);
		categoria = clienteService.update(categoria);
		return ResponseEntity.noContent().build(); //noContent: QUE A RESPONSA DA REQUISIÇÃO N TEM CONTEUDO, CORPO
	}
	
	
	@RequestMapping(value= "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id); 
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() { // a anotação @PathVariable é para o spring envia o id que recebeu na requisição, para o metodo FIND		
		List<Cliente> list = clienteService.findAll();						  
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); //convertendo listas		
		return ResponseEntity.ok().body(listDTO);		
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) { //@RequestParam parametro opcional
		Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);						
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj)); //convertendo listas		
		return ResponseEntity.ok().body(listDTO);		
	}
}
