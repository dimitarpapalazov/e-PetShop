package mk.ukim.finki.wp.project.epetshop.demo.model;

import lombok.Data;
import mk.ukim.finki.wp.project.epetshop.demo.model.enumerations.MemberRole;
import mk.ukim.finki.wp.project.epetshop.demo.model.enumerations.VerificationStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
public class Member {

    @Id
    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(value = EnumType.STRING)
    private MemberRole role;

    @Enumerated(value = EnumType.STRING)
    private VerificationStatus status;

}
