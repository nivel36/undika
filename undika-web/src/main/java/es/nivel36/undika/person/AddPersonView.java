package es.nivel36.undika.person;

import java.io.Serializable;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.nivel36.person.api.DuplicatePersonException;
import es.nivel36.person.api.PersonService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.FacesMessage.Severity;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class AddPersonView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String URL = "/person/add";

	private static final Logger log = LoggerFactory.getLogger(AddPersonView.class);

	private @Inject PersonService personService;

	private @Inject FacesContext facesContext;
	
	private @Inject Translator translator;

	private PersonDto person;

	@PostConstruct
	public void init() {
		log.debug("Add user INIT view");
		person = new PersonDto();
	}

	public void save() {
		try {
			this.personService.insert(person);
		} catch (final DuplicatePersonException e) {
			final UIComponent component = getUIComponent("person:email");
			this.addMessage(component, FacesMessage.SEVERITY_ERROR, "person.error.email_in_use",
					"person.error.email_in_use", null);
			facesContext.validationFailed();
		}
	}
	
	private void addMessage(final UIComponent component, final Severity severity, final String title,
			final String message, final Object[] params) {
		final String translatedTitle = this.translator.message(title, params);
		final String translatedMessage = this.translator.message(message, params);
		final FacesMessage facesMessage = new FacesMessage(severity, translatedTitle, translatedMessage);
		if (component == null) {
			this.facesContext.addMessage(null, facesMessage);
		} else {
			this.facesContext.addMessage(component.getClientId(), facesMessage);
		}
	}
	
	private UIComponent getUIComponent(final String id) {
		return facesContext.getViewRoot().findComponent(id);
	}

	public void setPersonService(final PersonService personService) {
		Objects.requireNonNull(personService);
		this.personService = personService;
	}

	public String getUrl() {
		return URL;
	}
}
