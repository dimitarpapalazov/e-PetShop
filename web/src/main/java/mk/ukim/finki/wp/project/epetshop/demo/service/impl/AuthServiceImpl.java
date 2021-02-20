package mk.ukim.finki.wp.project.epetshop.demo.service.impl;

import mk.ukim.finki.wp.project.epetshop.demo.model.Member;
import mk.ukim.finki.wp.project.epetshop.demo.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.project.epetshop.demo.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.project.epetshop.demo.repository.MemberRepo;
import mk.ukim.finki.wp.project.epetshop.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final MemberRepo memberRepo;

    public AuthServiceImpl(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    @Override
    public Member login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return memberRepo.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);

    }
}
