package es.nivel36.person.ejb;

import java.util.List;
import java.util.Objects;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

class PersonRepository {

	private final static Logger log = LoggerFactory.getLogger(PersonRepository.class);

	private @PersistenceContext EntityManager entityManager;

	public void insert(final PersonEntity person) {
		Objects.requireNonNull(person);
		entityManager.persist(person);
	}

	public void delete(final String email) {
		Objects.requireNonNull(email);
		PersonEntity personEntity = this.findByEmail(email);
		entityManager.remove(personEntity);
	}

	public PersonEntity findByEmail(final String email) {
		Objects.requireNonNull(email);
		TypedQuery<PersonEntity> query = this.entityManager.createNamedQuery("Person.findByEmail", PersonEntity.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

	public List<PersonEntity> search(final String query, final int offset, final int limit, final String orderBy,
			final Boolean orderAsc) {
		Objects.requireNonNull(query);
		if (offset < 0) {
			throw new IllegalArgumentException("offset: " + offset);
		}
		if (limit <= 0) {
			throw new IllegalArgumentException("limit: " + offset);
		}
		log.trace("Searching with an offset of an a limit of {}", offset, limit);

		final String orderField;
		if (orderBy == null) {
			orderField = "name";
		} else {
			orderField = orderBy;
		}
		final boolean isAsc;
		if (orderAsc == null || orderAsc == Boolean.FALSE) {
			isAsc = false;
		} else {
			isAsc = true;
		}

		log.trace("Ordering by the field {}", orderField);

		final SearchSession searchSession = Search.session(entityManager);
		final SearchResult<PersonEntity> result = searchSession.search(PersonEntity.class)
				.where(f -> f.match().fields("_name", "_surname", "_email").matching(query))
				.sort(isAsc ? f -> f.field(orderBy).asc() : f -> f.field(orderField).desc()).fetch(offset, limit);
		return result.hits();
	}

	public void setEntityManager(final EntityManager entityManager) {
		Objects.requireNonNull(entityManager);
		this.entityManager = entityManager;
	}
}
