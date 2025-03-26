package idk;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Deltager { 
	
    @NotNull(message = "Fornavn er obligatorisk")
    @Size(min = 2, max = 20, message = "Fornavn må være 2-20 tegn og begynne med en stor bokstav.")
    @Pattern(regexp = "^[A-ZÆØÅ][a-zæøåA-ZÆØÅ\\- ]*$", message = "Fornavn må begynne med stor bokstav og kan inneholde bokstaver og bindestrek.")
    private String fornavn;

    @NotNull(message = "Etternavn er obligatorisk")
    @Size(min = 2, max = 20, message = "Etternavn må være 2-20 tegn og kan ikke inneholde mellomrom.")
    @Pattern(regexp = "^[A-ZÆØÅ][a-zæøåA-ZÆØÅ\\-]*$", message = "Etternavn må begynne med stor bokstav og kan inneholde bokstaver og bindestrek.")
    private String etternavn;

    @NotNull(message = "Mobilnummer er obligatorisk")
    @Pattern(regexp = "^\\d{8}$", message = "Mobilnummeret må bestå av 8 siffer.")
    private String mobil;

    @Override
	public String toString() {
		return "Deltager [fornavn=" + fornavn + ", etternavn=" + etternavn + ", mobil=" + mobil + ", passord="
				+ ", kjonn=" + kjonn + "]";
	}

    private String passord;

    @NotNull(message = "Kjønn er obligatorisk")
    private String kjonn;
	    
	public Deltager() {
		
	}
	
	public Deltager(String fornavn, String etternavn, String mobil, String passord, String kjonn) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.mobil = mobil;
		this.passord = passord;
		this.kjonn = kjonn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public String getMobil() {
		return mobil;
	}
	
	public String getPassord() {
		return passord;
	}
	
	public String getKjonn() {
		return kjonn;
	}
	
	public void setFornavn(String firstName) {
		this.fornavn = firstName;
	}
	
	public void setEtternavn(String lastName) {
		this.etternavn = lastName;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
	
	public void setPassord(String passord) {
		this.passord = passord;
	}
	
	public void setKjonn(String kjonn) {
		this.kjonn = kjonn; 
	}
}
