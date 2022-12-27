package es.nivel36.person.ejb;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import es.nivel36.person.api.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Indexed
@Entity
@NamedQueries({
		@NamedQuery(name = "Person.findByEmail", query = "SELECT p from PersonEntity p WHERE p.email = :email") })
@Table(name = "PERSON", //
		indexes = { @Index(name = "UX_PERSON_EMAIL", columnList = "email", unique = true) }, //
		uniqueConstraints = { @UniqueConstraint(name = "UQ_PERSON_EMAIL", columnNames = "email") })
public class PersonEntity implements Person, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@FullTextField(name = "_name")
	@Column(nullable = false)
	private String name;

	@NotNull
	@FullTextField(name = "_surname")
	@KeywordField(sortable = Sortable.YES)
	@Column(nullable = false)
	private String surname;

	@NotNull
	@FullTextField(name = "_email")
	@KeywordField(sortable = Sortable.YES)
	@Column(nullable = false)
	private String email;

	private String language;

	private String avatarUrl;

	/**
	 * Default constructor.
	 */
	public PersonEntity() {
	}

	/**
	 * Constructs a person containing the values of the specified person passed by
	 * the parameter.
	 * 
	 * @param person
	 */
	public PersonEntity(final Person person) {
		Objects.requireNonNull(person);
		this.merge(person);
	}

	/**
	 * Replaces the values of the object with the values of the {@link Person}
	 * passed by the parameter.
	 * 
	 * @param person from whom we are going to recover the values.
	 * 
	 * @throws NullPointerException if the person is null
	 */
	public void merge(final Person person) {
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

	public long getId() {
		return id;
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

	public void setId(final long id) {
		this.id = id;
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
		PersonEntity other = (PersonEntity) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return name + " " + surname;
	}
}
