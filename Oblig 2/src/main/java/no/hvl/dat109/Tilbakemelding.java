package no.hvl.dat109;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tilbakemelding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int vurdering;

    @ManyToOne
    private Person student;

    @ManyToOne
    private Forelesning forelesning;
    
    public Tilbakemelding(Long id, int vurdering, Person student, Forelesning forelesning) {
    	this.id = id;
    	this.vurdering = vurdering;
    	this.student = student;
    	this.forelesning = forelesning;
    }
    
    public Tilbakemelding() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVurdering() {
		return vurdering;
	}

	public void setVurdering(int vurdering) {
		this.vurdering = vurdering;
	}

	public Person getStudent() {
		return student;
	}

	public void setStudent(Person student) {
		this.student = student;
	}

	public Forelesning getForelesning() {
		return forelesning;
	}

	public void setForelesning(Forelesning forelesning) {
		this.forelesning = forelesning;
	}
    
}
