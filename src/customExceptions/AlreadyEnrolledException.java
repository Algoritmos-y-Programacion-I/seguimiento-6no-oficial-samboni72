package customExceptions;

public class AlreadyEnrolledException extends Exception {
    public AlreadyEnrolledException(String id) {
        super("Estudiante con el id" + id + " ya esta matriculado");
    }
}
