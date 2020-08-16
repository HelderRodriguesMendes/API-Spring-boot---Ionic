package com.helder.cursoSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.helder.cursoSpring.dto.ClienteDTO;
import com.helder.cursoSpring.model.Cliente;
import com.helder.cursoSpring.repositories.ClienteRepository;
import com.helder.cursoSpring.services.exceptions.DataIntegrityException;
import com.helder.cursoSpring.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository ClienteRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = ClienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente pesquisarNome(String nome) {
		Cliente obj = ClienteRepository.findByNome(nome).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! nome: " + nome + ", Tipo: " + Cliente.class.getName()));
		return obj;
	}

	public Cliente update(Cliente categoria) {
		find(categoria.getId());
		return ClienteRepository.save(categoria);
	}

	public void delete(Integer id) {
		try {
			ClienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que contem produtos");
		}
	}

	public List<Cliente> findAll() {
		return ClienteRepository.findAll();
	}

	/*
	 * Paginação de dados
	 */
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return ClienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO categoriaDTO) {
		throw new UnsupportedOperationException();
	}
}
