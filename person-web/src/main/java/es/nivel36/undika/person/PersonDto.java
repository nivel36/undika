package es.nivel36.undika.person;

import java.io.Serializable;
import java.util.Objects;

import es.nivel36.person.api.Person;
import jakarta.validation.constraints.NotNull;

public class PersonDto implements Person, Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;

	@NotNull
	private String surname;

	@NotNull
	private String email;

	private String language;

	private String avatarUrl;

	/**
	 * Default constructor.
	 */
	public PersonDto() {
	}

	/**
	 * Constructs a person containing the values of the specified person passed by
	 * the parameter.
	 * 
	 * @param person
	 */
	public PersonDto(final Person person) {
		Objects.requireNonNull(person);
		this.avatarUrl = person.getAvatarUrl();
		this.email = person.getEmail();
		this.language = person.getLanguage();
		this.name = person.getName();
		this.surname = person.getSurname();
	}

	@Override
	public String getAvatarUrl() {
		return avatarUrl;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getLanguage() {
		return language;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	public void setAvatarUrl(final String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		PersonDto other = (PersonDto) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return name + " " + surname;
	}
}
