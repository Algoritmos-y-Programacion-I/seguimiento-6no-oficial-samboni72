package customExceptions;

public class WeekExceededEnrollException extends Exception {
    public WeekExceededEnrollException(int currentWeek) {
        super("No se permiten cancelaciones a partir de la semana 2, y estamos en la semana" + currentWeek);
    }
}
