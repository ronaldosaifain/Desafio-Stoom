package com.endereco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endereco.domain.Coordenadas;
import com.endereco.domain.Endereco;
import com.endereco.exception.ObjectNotFoundException;
import com.endereco.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	ConsumeApi conApi;

	public List<Endereco> findAll() {

		return enderecoRepository.findAll();

	}

	public Endereco find(Integer id) {
		Optional<Endereco> obj = enderecoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));

	}

	public Endereco insert(Endereco endereco) {

		if (endereco.getLatitude() == null || endereco.getLongitude() == null) {
			Coordenadas coordenadas = conApi.getLatAndLng(endereco);
			endereco.setLatitude(coordenadas.getLatitude());
			endereco.setLongitude(coordenadas.getLongitude());
		}

		return enderecoRepository.save(endereco);
	}

	public Endereco update(Endereco endereco) {
		find(endereco.getId());

		Endereco newEndereco = find(endereco.getId());
		updateData(newEndereco, endereco);
		return enderecoRepository.save(newEndereco);
	}

	public void delete(Integer id) {
		find(id);
		enderecoRepository.deleteById(id);
	}

	private void updateData(Endereco newEndereco, Endereco endereco) {
		if (endereco.getStreetname() != null)
			newEndereco.setStreetname(endereco.getStreetname());
		if (endereco.getNumber() != null)
			newEndereco.setNumber(endereco.getNumber());
		if (endereco.getComplement() != null)
			newEndereco.setComplement(endereco.getComplement());
		if (endereco.getNeighbourhood() != null)
			newEndereco.setNeighbourhood(endereco.getNeighbourhood());
		if (endereco.getCity() != null)
			newEndereco.setCity(endereco.getCity());
		if (endereco.getState() != null)
			newEndereco.setState(endereco.getState());
		if (endereco.getCountry() != null)
			newEndereco.setCountry(endereco.getCountry());
		if (endereco.getZipcode() != null)
			newEndereco.setZipcode(endereco.getZipcode());
		if (endereco.getLatitude() != null)
			newEndereco.setLatitude(endereco.getLatitude());
		if (endereco.getLongitude() != null)
			newEndereco.setLongitude(endereco.getLongitude());
	}

}