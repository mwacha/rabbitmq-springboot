package tk.mwacha.exceptions;

public class EntityNotFoundException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Registro não foi encontrado.";

  public EntityNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  public EntityNotFoundException(String message) {
    super(message);
  }
}
