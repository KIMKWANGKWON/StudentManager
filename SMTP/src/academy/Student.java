package academy;

public class Student {
	private int id;
	private String name;
	private Room room;

	public Student(int id, String name, Room room) {
		this.id = id;
		this.name = name;
		this.room = room;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public String toString() {
		return id + "@" + name + "@" + room;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
}
