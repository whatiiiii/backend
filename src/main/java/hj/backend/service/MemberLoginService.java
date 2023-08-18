package hj.backend.service;

import hj.backend.domain.MemberLogin;

public interface MemberLoginService {
    int check(String email, String pwd);
    MemberLogin getLogin(String email);
}
