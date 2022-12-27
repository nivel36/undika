package es.nivel36.person.api;

import java.util.List;

import jakarta.ejb.Local;

@Local
public interface PersonService {

	/**
	 * Insert a person in the repository.
	 * 
	 * <p>
	 * In case this person has already been inserted, i.e. the e-mail address is
	 * already registered, an {@code DuplicatePersonException} will be thrown.
	 * 
	 * @param person to insert.
	 * 
	 * @throws NullPointerException     if the person is <tt>null</tt>
	 * @throws DuplicatePersonException if the person's email address has already
	 *                                  been registered.
	 */
	void insert(Person person) throws DuplicatePersonException;

	/**
	 * Update a person in the repository.
	 * 
	 * <p>
	 * In case this person has already been inserted, i.e. the e-mail address is
	 * already registered, an {@code DuplicatePersonException} will be thrown.
	 * 
	 * @param person to update.
	 * 
	 * @throws NullPointerException     if the person is <tt>null</tt>
	 * @throws DuplicatePersonException if the person's email address has already
	 *                                  been registered.
	 * @throws PersonNotFoundException  if the person to be modified does not exist
	 *                                  in the repository.
	 */
	void update(Person person) throws DuplicatePersonException;

	/**
	 * Delete a person in the repository.
	 * 
	 * <p>
	 * In case the email does not correspond to a registered person, a
	 * {@code PersonNotFoundException} will be thrown.
	 * 
	 * @param email of the person to delete.
	 * 
	 * @throws NullPointerException    if the email is <tt>null</tt>
	 * @throws PersonNotFoundException if the person to be deleted does not exist in
	 *                                 the repository.
	 */
	void delete(String email);

	/**
	 * Find a person in the repository by their email address.
	 * 
	 * <p>
	 * In case the email does not correspond to a registered person, a
	 * {@code PersonNotFoundException} will be thrown.
	 * 
	 * @param email of the person to be searched
	 *
	 * @return <tt>Person</tt> with the requested email address
	 * 
	 * @throws NullPointerException    if the email is <tt>null</tt>
	 * @throws PersonNotFoundException if the person with that email does not exist
	 *                                 in the repository.
	 */
	Person findByEmail(String email);

	/**
	 * Searches for people in the repository whose first name, last name or email
	 * match the search criteria passed in the query, i.e. if the search string is
	 * "abel" it will return results for a person called Abel but also for a person
	 * called Isabel. If no person matching these criteria is found, an empty list
	 * will be returned.
	 * <p>
	 * The query string cannot be null. If empty, a list of all persons in the
	 * repository will be returned. If it is not empty, the minimum string size for
	 * the search is 3 characters. A one or two character search will return all the
	 * results.
	 * 
	 * <p>
	 * If the query string is n space-separated text strings, for example "foo ofo
	 * oof", the query will return results for people who have at least one of the
	 * strings in their first name, last name or email.
	 * 
	 * @param query    a <tt>String<tt> with the text that the person must have,
	 *                 either in the first name, surname or email. This parameter
	 *                 cannot be null
	 *                 
	 * @param offset   the offset of the results. this parameter can not be null;
	 * 
	 * @param limit    the limit of search results. This value cannot be greater than
	 *                 150. If a higher number is set, the system will only return a
	 *                 maximum of 150 results. This parameter cannot negative.
	 *                 
	 * @param orderBy is the name of the parameter to be sorted on in the search.
	 * 
	 * @param orderAsc if an order is specified, it shall be in ascending order if this parameter is <tt>true</tt>.
	 *                 If <tt>null<tt> or <tt>false</tt>, the order shall be in
	 *                 descending order.
	 *                 
	 * @return List of Person who meet the search criteria.
	 * 
	 * @throws NullPointerException     if the query is <tt>null</tt>
	 * @throws IllegalArgumentException if the offset is less than zero
	 * @throws IllegalArgumentException if the limit is less or equals than zero.
	 * 
	 */
	List<? extends Person> search(String query, int offset, int limit, String orderBy, Boolean orderAsc);
}
