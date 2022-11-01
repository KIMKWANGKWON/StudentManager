package academy;

import java.util.ArrayList;
import java.util.HashSet;

public class Academy {
	static Academy instance = new Academy();
	private HashSet<Teacher> teachers = new HashSet();
	private HashSet<Student> students = new HashSet();
	private HashSet<Room> rooms = new HashSet();
	private Academy() {}
	public static Academy getInstance() {
		if(instance == null) {
			instance = new Academy();
		}
		return instance;
	}
}
