package com.endereco.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "Campo streetName deve ser preenchido.")
	private String streetname;
	@NotEmpty(message = "Campo number deve ser preenchido.")
	private String number;
	private String complement;
	@NotEmpty(message = "Campo neighbourhood deve ser preenchido.")
	private String neighbourhood;
	@NotEmpty(message = "Campo city deve ser preenchido.")
	private String city;
	@NotEmpty(message = "Campo state deve ser preenchido.")
	private String state;
	@NotEmpty(message = "Campo country deve ser preenchido.")
	private String country;
	@NotEmpty(message = "Campo zipcode deve ser preenchido.")
	private String zipcode;
	private String latitude;
	private String longitude;
	
	public Endereco() {
		
	}

	public Endereco(Integer id, String streetname, String number, String complement, String neighbourhood, String city,
			String state, String country, String zipcode, String latitude, String longitude) {
		super();
		this.id = id;
		this.streetname = streetname;
		this.number = number;
		this.complement = complement;
		this.neighbourhood = neighbourhood;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreetname() {
		return streetname;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", streetName=" + streetname + ", number=" + number + ", complement=" + complement
				+ ", neighbourhood=" + neighbourhood + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", zipcode=" + zipcode + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
	
}
