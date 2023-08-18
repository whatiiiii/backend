package hj.backend.repository;

import hj.backend.domain.MemberLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLoginRepository extends JpaRepository<MemberLogin, Long> {
    MemberLogin findByEmail(String email);
}
