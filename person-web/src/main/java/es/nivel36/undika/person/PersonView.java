package es.nivel36.undika.person;

import java.io.Serializable;

import es.nivel36.person.api.DuplicatePersonException;
import es.nivel36.person.api.PersonService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class PersonView implements Serializable {

	private static final long serialVersionUID = 1L;

	private @Inject PersonService personService;

	@PostConstruct
	public void init() {
		PersonDto dto = new PersonDto();
		dto.setEmail("aferrer@nivel36.es");
		try {
			personService.insert(dto);
		} catch (DuplicatePersonException e) {
			e.printStackTrace();
		}
	}

	public String getMessage() {
		return "Do it!";
	}

	public String message() {
		return "Do it!";
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
}
