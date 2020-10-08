package com.endereco;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.endereco.domain.Endereco;
import com.endereco.service.EnderecoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackageClasses = EnderecoApplication.class)
@AutoConfigureMockMvc
class EnderecoApplicationTests {

	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	EnderecoService service;
	
	@Test
	public void shouldReturnSuccess_WhenInsertBeer() throws JsonProcessingException, Exception {

		Endereco endereco = new Endereco(1, "R. Zuneide Aparecida Marin", "43", null, "Santa Genebra II", "Campinas", "SP", "Brasil", "13084-780", null, null);

		mockMvc.perform(post("/enderecos").contentType("application/json").content(objectMapper.writeValueAsString(endereco)))
				.andExpect(status().isCreated());
		
		Endereco enderecoReturn = service.find(endereco.getId());
		
		Assertions.assertEquals(enderecoReturn.getId(), endereco.getId());
		Assertions.assertEquals(enderecoReturn.getStreetname(), endereco.getStreetname());
		Assertions.assertNotEquals(enderecoReturn.getLatitude(), endereco.getLatitude());
		Assertions.assertNotEquals(enderecoReturn.getLongitude(), endereco.getLongitude());
		
	}
	
	@Test
	public void shouldReturnSuccess_WhenFindBeer() throws Exception {

		Endereco endereco = new Endereco(1, "R. Zuneide Aparecida Marin", "43", "Barão Geraldo", "Santa Genebra II", "Campinas", "SP", "Brasil", "13084-780", null, null);
		service.insert(endereco);
		
		mockMvc.perform(get("/enderecos/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.city", is(endereco.getCity())));
	}
	
	@Test
	public void shouldReturnSuccess_WhenUpdateBeer() throws JsonProcessingException, Exception {

		Endereco endereco = new Endereco(1, "R. Luiza Rodrigues da Silva", "407", "nothing", "PQ. Oziel", "Campinas", "SP", "Brasil", "13049-020", null, null);
		Endereco enderecoUpdate = service.insert(endereco);
		
		enderecoUpdate.setStreetname("R. Oscár Alves Costa");
		enderecoUpdate.setNumber("101");
		enderecoUpdate.setComplement("Barão Geraldo");
		enderecoUpdate.setNeighbourhood("Santa Genebra II");
		enderecoUpdate.setCity("Campinas");
		enderecoUpdate.setState("SP");
		enderecoUpdate.setCountry("Brasil");
		enderecoUpdate.setZipcode("13084-762");
		enderecoUpdate.setLatitude("123456");
		enderecoUpdate.setLongitude("654321");
		
		mockMvc.perform(put("/enderecos/{id}", enderecoUpdate.getId()).contentType("application/json").content(objectMapper.writeValueAsString(enderecoUpdate)))
				.andExpect(status().isOk());

		Endereco enderecoReturn = service.find(endereco.getId());

		Assertions.assertEquals(enderecoReturn.getId(), enderecoUpdate.getId());
		Assertions.assertEquals(enderecoReturn.getStreetname(), enderecoUpdate.getStreetname());

	}
	
	@Test
	public void shouldReturnSuccess_WhenDeleteBeer() throws Exception {
		mockMvc.perform(delete("/enderecos/{id}", 1)).andExpect(status().isNoContent());
			
	}
	

}
