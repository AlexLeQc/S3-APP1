package menufact.plats.exceptions;

public class PlatException extends Throwable{
    public PlatException(String message){
        super("PlatException: " + message);
    }
}
