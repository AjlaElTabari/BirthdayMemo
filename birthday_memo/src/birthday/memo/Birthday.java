package birthday.memo;

public class Birthday {
	private String name;
	private String lastname;
	private int month;
	private int day;
	private int year;

	/**
	 * @param name
	 * @param lastname
	 * @param month
	 * @param day
	 * @param year
	 */
	public Birthday(String name, String lastname, int month, int day, int year) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.month = month;
		this.day = day;
		this.year = year;
	}

	/**
	 * @param name
	 * @param month
	 * @param day
	 */
	public Birthday(String name, int month, int day) {
		this(name, "", month, day, 0);
	}

	/**
	 * @param name
	 * @param lastname
	 * @param month
	 * @param day
	 */
	public Birthday(String name, String lastname, int month, int day) {
		this(name, lastname, month, day, 0);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day
	 *            the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	public String toString() {
		return name + " " + lastname + " " + month + " " + day + " " + year;
	}
}
