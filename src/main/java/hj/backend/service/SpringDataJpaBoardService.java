package hj.backend.service;

import hj.backend.domain.Address;
import hj.backend.domain.Board;
import hj.backend.dto.AddressListResult;
import hj.backend.repository.SpringDataJpaOracleBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class SpringDataJpaBoardService implements BoardService {
   // @Autowired
    private final SpringDataJpaOracleBoardRepository repository;
   // @Autowired
    public SpringDataJpaBoardService(SpringDataJpaOracleBoardRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Board> listS() {
        pln("@listS() by SpringDataJpa");
       return repository.findAll(Sort.by(Sort.Direction.DESC, "seq"));
    }


    @Override
    public Board insertS(Board board) {
        pln("#insertS() by SpringDataJpa");
        board =repository.save(board);
        return board;
    }

    @Override
    public Board contentListS(long seq) {
       Board board = repository.getReferenceById(seq);
        return board;
    }

    @Override
    public void updateS(Board board) {
        pln("#updateS() by SpringDataJpa");
        Board board1 = repository.getReferenceById(board.getSeq());
        board1.setSeq(board.getSeq());
        board1.setEmail(board.getEmail());
        board1.setSubject(board.getSubject());
        board1.setContent(board.getContent());
        //board1.setRdate(board.getRdate());
           repository.save(board1);
    }

    @Override
    public boolean deleteS(long seq) {
        pln("#deleteS() by SpringDataJpa");
        repository.deleteById(seq);
        return false;
    }


    public List<Board> findByWriter(String writer){
        List<Board> list = repository.findByWriter(writer);
        return list;
    }
  //  public List<Board> findByWRITERAndEMAIL(String writer, String email){
  //      List<Board> list = repository.findByWRITERAndEMAIL(writer, email);
  //      return list;
  //  }

//    public List<Board> findByWRITERContaining(String writer){
  //      List<Board> list = repository.findByWRITERContaining(writer);
   //     return list;
   // }
    void pln(String str){
        System.out.println(str);
    }
}
