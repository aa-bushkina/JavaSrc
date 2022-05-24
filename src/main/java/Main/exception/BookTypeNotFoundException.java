package Main.exception;

public class BookTypeNotFoundException extends RuntimeException
{
  public BookTypeNotFoundException(String message)
  {
    super(message);
  }
}
