package customExceptions;

public class StudentNotEnrolledException extends Exception {
    public StudentNotEnrolledException(String id) {
        super("El estudiante con el id " + id + " No esta matriculado.");
    }
}
