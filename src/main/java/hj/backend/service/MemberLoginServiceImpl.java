package hj.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import hj.backend.domain.MemberLogin;
import hj.backend.repository.MemberLoginRepository;
import static hj.backend.service.MemberLoginConst.NO_ID;
import static hj.backend.service.MemberLoginConst.NO_PWD;
import static hj.backend.service.MemberLoginConst.YES_ID_PWD;

@RequiredArgsConstructor
@Service
public class MemberLoginServiceImpl implements MemberLoginService {
    private final MemberLoginRepository memberLoginRepository;
    @Override
    public int check(String email, String pwd) {
        MemberLogin memberLogin = memberLoginRepository.findByEmail(email);
        if(memberLogin == null){
            return NO_ID;
        }else{
            String dbPwd = memberLogin.getPwd();
            if(dbPwd != null) dbPwd = dbPwd.trim();

            if(!dbPwd.equals(pwd)){
                return NO_PWD;
            }else{
                return YES_ID_PWD;
            }
        }
    }
    @Override
    public MemberLogin getLogin(String email) {
        MemberLogin memberLogin = memberLoginRepository.findByEmail(email);
        memberLogin.setPwd("");

        return memberLogin;
    }
}
