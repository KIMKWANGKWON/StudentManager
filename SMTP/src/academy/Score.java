package academy;

public class Score {
	private String subjectName;
	private int studnetId;
	private int score;
	public Score(String subjectName, int studnetId, int score) {
		this.subjectName = subjectName;
		this.studnetId = studnetId;
		this.score = score;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getStudnetId() {
		return studnetId;
	}
	public void setStudnetId(int studnetId) {
		this.studnetId = studnetId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
