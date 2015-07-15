package birthday.memo;

/**
 * Manipulates data from the file. File must contain names, last names and
 * birthdays. First line in file represents number of date entries in the file.
 * 
 * @author ajla.eltabari
 *
 */
public class DataHelper {
	/**
	 * Reads data from specified file. Creates array of Birthday(s) and populate
	 * it with Birthday objects, gained from the file, using <b> public static
	 * Birthday getBirthday(String s) method </b>.
	 * 
	 * @param fileName
	 *            Full name of the file with birthdays data.
	 * @return array of Birthdays
	 */
	public static Birthday[] getBirthdays(String fileName) {
		try {
			TextIO.readFile(fileName);
		} catch (IllegalArgumentException e) {
			System.out.println("File cannot be found.");
		}

		int fileLength = TextIO.getInt();
		System.out.println(fileLength);
		Birthday[] birthdays = new Birthday[fileLength];

		TextIO.getln();
		for (int i = 0; i < fileLength; i++) {
			birthdays[i] = getBirthday(TextIO.getln());
		}

		TextIO.readStandardInput();
		return birthdays;
	}

	/**
	 * Returns string representation of birthday array
	 * 
	 * @param fileName
	 * @return
	 */
	public static String printBirthdays(String fileName) {
		Birthday[] birthdaysToPrint = getBirthdays(fileName);
		String s = "";
		for (int i = 0; i < birthdaysToPrint.length; i++) {
			s += birthdaysToPrint[i] + "\n";
		}
		return s;
	}

	/**
	 * Splits String from the Birthday array into separate variables. Creates
	 * Birthday object with gained information.
	 * 
	 * @param s
	 *            String that represents combined info of a birthday from the
	 *            Birthdays array
	 * @return Birthday object
	 */
	public static Birthday getBirthday(String s) {
		String name = "", lastname = "";
		int day = 0, month = 0, year = 0;

		String[] splited = s.split("\\s");
		name = splited[0];
		lastname = splited[1];
		day = Integer.parseInt(splited[2]);
		month = Integer.parseInt(splited[3]);
		year = Integer.parseInt(splited[4]);

		Birthday birthday = new Birthday(name, lastname, month, day, year);
		return birthday;
	}

	/**
	 * Creates new Birthday array, larger by one from the original array. Copies
	 * all birthdays from the original array to the new one. Adds new birthday
	 * at the end of the new array. Adds count of birthdays at the beginning of
	 * the file Writes all birthdays back to the file
	 * 
	 * @param filename
	 */
	public static void insertBirtdhday(String fileName, Birthday[] birthdays,
			String newEntry) {
		TextIO.writeFile(fileName);
		TextIO.putln(birthdays.length + 1);

		Birthday[] newBirthdays = new Birthday[birthdays.length + 1];

		for (int i = 0; i < birthdays.length; i++) {
			newBirthdays[i] = birthdays[i];
		}

		newBirthdays[newBirthdays.length - 1] = getBirthday(newEntry);

		for (int i = 0; i < newBirthdays.length; i++) {
			TextIO.putln(newBirthdays[i]);
		}
	}

	/**
	 * Returns birthdays for today
	 * 
	 * @param day
	 * @param month
	 * @param filename
	 * @return
	 */
	public static String todaysBirthdays(int day, int month, String filename) {
		Birthday[] birthdays = getBirthdays(filename);
		String s = "";
		for (int i = 0; i < birthdays.length; i++) {
			if (birthdays[i].getDay() == day
					&& birthdays[i].getMonth() == month) {
				s += birthdays[i].toString() + "\n";
			}
		}
		return s;
	}

	/**
	 * Returns birthdays by name
	 * 
	 * @param name
	 * @param filename
	 * @return
	 */
	public static String birthdaysByName(String name, String filename) {
		Birthday[] birthdays = getBirthdays(filename);
		String s = "";
		for (int i = 0; i < birthdays.length; i++) {
			if (birthdays[i].getName().equals(name)) {
				s += birthdays[i].toString() + "\n";
			}
		}
		return s;
	}

	/**
	 * Returns birthdays by entered date
	 * 
	 * @param day
	 * @param month
	 * @param filename
	 * @return
	 */
	public static String birthdaysByDate(String day, String month,
			String filename) {
		Birthday[] birthdays = getBirthdays(filename);
		String s = "";
		for (int i = 0; i < birthdays.length; i++) {
			if (birthdays[i].getDay() == (Integer.parseInt(day))
					&& birthdays[i].getMonth() == (Integer.parseInt(month))) {
				s += birthdays[i].toString() + "\n";
			}
		}
		return s;
	}

}
