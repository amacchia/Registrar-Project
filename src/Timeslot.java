import java.time.DayOfWeek;
import java.time.LocalTime;
// Represents a meeting time of section
public class Timeslot {
	DayOfWeek weekday;	// The day of the week the section meets
	LocalTime startTime;	// The start time of the meeting
	LocalTime endTime;	// The end time of the meeting
	
	public Timeslot(DayOfWeek weekday, LocalTime startTime, LocalTime endTime) {
		this.weekday = weekday;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String toString(){
		return weekday.toString() + ": " + startTime.toString() + " - " + endTime.toString();
	}

	public DayOfWeek getWeekday() {
		return weekday;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}
	
}
