package com.endereco.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.endereco.domain.ClienteRest;
import com.endereco.domain.Coordenadas;
import com.endereco.domain.Endereco;

@Service
public class ConsumeApi {

	public Coordenadas getLatAndLng(Endereco endereco) {
		System.out.println(endereco.toString());
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + endereco.getNumber() + "+"
				+ endereco.getStreetname().replace(" ", "+") + ",+" + endereco.getCity().replace(" ", "+") + ",+"
				+ endereco.getState() + "&key=AIzaSyDTK0igIQTCi5EYKL9tzOIJ9N6FUASGZos";

		RestTemplate restTemplate = new RestTemplate();

		ClienteRest cliente = restTemplate.getForObject(url, ClienteRest.class);

		Coordenadas coordenadas = new Coordenadas(cliente.getResults().get(0).getGeometry().getLocation().getLat(),
				cliente.getResults().get(0).getGeometry().getLocation().getLng());

		return coordenadas;

	}

}
