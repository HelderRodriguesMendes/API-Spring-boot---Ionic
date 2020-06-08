package com.helder.cursoSpring.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.helder.cursoSpring.dto.CategoriaDTO;
import com.helder.cursoSpring.model.Categoria;
import com.helder.cursoSpring.services.CategoriaService;

@RestController //define essa classe como controller, que recebe requisição
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired// para instancia o objeto de CategoriaService de forma altomatica
	private CategoriaService categoriaService;
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) { // a anotação @PathVariable é para o spring envia o id que recebeu na requisição, para o metodo FIND
		
		Categoria obj = categoriaService.find(id);
		
		return ResponseEntity.ok().body(obj);		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO){
		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
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
	@RequestMapping(value= "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id){
		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoria.setId(id);
		categoria  = categoriaService.update(categoria);
		return ResponseEntity.noContent().build(); //noContent: QUE A RESPONSA DA REQUISIÇÃO N TEM CONTEUDO, CORPO
	}
	
	
	@RequestMapping(value= "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id); 
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() { // a anotação @PathVariable é para o spring envia o id que recebeu na requisição, para o metodo FIND		
		List<Categoria> list = categoriaService.findAll();						
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); //convertendo listas		
		return ResponseEntity.ok().body(listDTO);		
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) { //@RequestParam parametro opcional
		Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);						
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj)); //convertendo listas		
		return ResponseEntity.ok().body(listDTO);		
	}
}
