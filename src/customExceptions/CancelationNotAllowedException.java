package customExceptions;

public class CancelationNotAllowedException extends Exception {
	private int currentWeek;
	private int totalWeeks;

	public CancelationNotAllowedException(int currentWeek, int totalWeeks) {
		super("NO se puede cancelar despues de de una semana" + (totalWeeks / 2) + ". estamos en la semana: " + currentWeek);
		this.currentWeek = currentWeek;
		this.totalWeeks = totalWeeks;
	}

	public int getCurrentWeek() {
		return currentWeek;
	}

	public int getTotalWeeks() {
		return totalWeeks;
	}
}
