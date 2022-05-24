package Main.exception;

public class BookTypeNotAllowException extends RuntimeException
{
  public BookTypeNotAllowException(String message)
  {
    super(message);
  }
}
