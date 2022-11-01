package academy;

public class Teacher {
	private String id;
	private String pwd;
	private String name;
	private Room majorRoom;
	private Room subRoom;
	
	public Teacher() {}
	public Teacher(String id ,String pwd, String name, Room majorRoom, Room subRoom) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.majorRoom = majorRoom;
		this.subRoom = subRoom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room getMajorRoom() {
		return majorRoom;
	}

	public void setMajorRoom(Room majorRoom) {
		this.majorRoom = majorRoom;
	}

	public Room getSubRoom() {
		return subRoom;
	}

	public void setSubRoom(Room subRoom) {
		this.subRoom = subRoom;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return id + "@" + name + "@" + majorRoom;
	}
}
