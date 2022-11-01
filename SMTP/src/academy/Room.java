package academy;

public class Room {
	private int no;
	private String name;
	public Room(int no) {
		this.no = no;
	}
	public Room(int no, String name) {
		this.no = no;
		this.name = name;
	}

	@Override
	public int hashCode() {
		return no;
	}

	@Override
	public String toString() {
		return no+"";
	}
}
