package com.helder.cursoSpring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helder.cursoSpring.model.Categoria;
import com.helder.cursoSpring.repositories.CategoriaRepository;
import com.helder.cursoSpring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id) {
		 Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null); //PARA GARANTIR QUE É UMA NOVA CATEGORIA, COM ID NULL
		return categoriaRepository.save(categoria);
	} 
	
}
