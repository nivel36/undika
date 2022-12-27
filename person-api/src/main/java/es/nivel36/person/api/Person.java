package es.nivel36.person.api;

/**
 * A Person class represents a registered user of the application. The word user
 * is not used because it is registered in some databases and may cause
 * problems.
 * <p>
 * From here you can obtain all the data of a person.
 * <p>
 * A person has the email as a natural key. This value cannot be null nor be
 * used by another person. Two persons with the same email address are the same
 * person.
 * <p>
 * The first and last name of the person cannot be null but there can exist two
 * different people with the same name.
 * <p>
 * The avatar url and language can be <tt>null</tt>. If the language is
 * <tt>null</tt> the default language of the application can be returned instead
 * 
 * @author Abel Ferrer
 *
 */
public interface Person {

	/**
	 * Returns the person's email address. This email is the natural key of the
	 * person and on which the finds will be performed, so there cannot be two
	 * different people objects with the same email address.
	 * <p>
	 * The email can be used to send an email to communicate with the person
	 * represented by this class so it has been validated.
	 * 
	 * @return the email of the person
	 */
	String getEmail();

	/**
	 * Returns the name of the person. In case the person has a compound name, this
	 * field will return both.
	 * 
	 * @return the name of the person
	 */
	String getName();

	/**
	 * Returns the surname of the person. If the person has two surnames, this field
	 * will return both.
	 * 
	 * @return the surname of the person
	 */
	String getSurname();

	/**
	 * Returns the url of the person's avatar, or a <tt>null</tt> if the person has
	 * no avatar.
	 * 
	 * @return the url of the person's avatar. <tt>null</tt> if the person has no
	 *         avatar.
	 */
	String getAvatarUrl();

	/**
	 * Returns the language in which the person has chosen to communicate. This will
	 * be returned using the format defined in ISO 639.
	 * 
	 * @return the person's language in the ISO 639 format.
	 */
	String getLanguage();
}
