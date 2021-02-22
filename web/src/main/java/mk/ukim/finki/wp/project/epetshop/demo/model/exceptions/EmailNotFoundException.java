package mk.ukim.finki.wp.project.epetshop.demo.model.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String email) {
        super(String.format("Email %s does not exists", email));
    }
}
