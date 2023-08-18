package hj.backend.repository;

import hj.backend.domain.Address;
import hj.backend.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaOracleBoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByWriter(String writer);

   // List<Board> findByWRITERAndEMAIL(String writer, String email);

//    List<Board> findByWRITERContaining(String writer);
    Page<Board> findByOrderBySeqDesc(Pageable pageable); //for 페이징 //추가
}
