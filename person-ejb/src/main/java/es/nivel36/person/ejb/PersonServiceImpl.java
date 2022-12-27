package es.nivel36.person.ejb;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.nivel36.person.api.DuplicatePersonException;
import es.nivel36.person.api.Person;
import es.nivel36.person.api.PersonNotFoundException;
import es.nivel36.person.api.PersonService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class PersonServiceImpl implements PersonService {

	private final static Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

	private @Inject PersonRepository personRepository;

	@Override
	public void insert(final Person person) throws DuplicatePersonException {
		Objects.requireNonNull(person);
		final String email = person.getEmail();
		log.debug("Insert person {}", email);
		final PersonEntity personEntity = this.findByEmail(email);
		if (personEntity != null) {
			log.warn("Email {} alredy in use", email);
			throw new DuplicatePersonException("Email " + email + " alredy in use");
		}
		final PersonEntity entity = new PersonEntity(person);
		personRepository.insert(entity);
	}

	@Override
	public void update(final Person person) {
		Objects.requireNonNull(person);
		final String email = person.getEmail();
		log.debug("Update person {}", email);
		final PersonEntity personEntity = this.findByEmail(email);
		personEntity.merge(person);
	}

	@Override
	public void delete(final String email) {
		Objects.requireNonNull(email);
		log.debug("Delete person {}", email);
		final PersonEntity personEntity = this.findByEmail(email);
		if (personEntity == null) {
			log.warn("Email {} not found", email);
			throw new PersonNotFoundException();
		}
		personRepository.delete(email);
	}

	@Override
	public PersonEntity findByEmail(final String email) {
		Objects.requireNonNull(email);
		log.debug("Find person by email {}", email);
		return personRepository.findByEmail(email);
	}

	@Override
	public List<PersonEntity> search(final String query, final int offset, final int limit, final String sortBy,
			final Boolean sortAsc) {
		Objects.requireNonNull(query);
		log.debug("Search person by query {}", query);
		return personRepository.search(query, offset, limit, sortBy, sortAsc);
	}
}
