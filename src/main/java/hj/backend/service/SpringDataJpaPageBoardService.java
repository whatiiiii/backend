package hj.backend.service;

import hj.backend.domain.Board;
import hj.backend.dto.AddressListResult;
import hj.backend.dto.BoardListResult;
import hj.backend.repository.SpringDataJpaOracleBoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SpringDataJpaPageBoardService implements PageBoardService {
    @Autowired
    private final SpringDataJpaOracleBoardRepository repository;
    @Override
    public Page<Board> findAll(Pageable pageable) {
        pln("@findAll() pageable: "+pageable);
        return repository.findByOrderBySeqDesc(pageable);
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

    @Override
    public BoardListResult getBoardListResult(Pageable pageable) {
        Page<Board> list = findAll(pageable);
        int page = pageable.getPageNumber();
        long totalCount = repository.count();
        int size= pageable.getPageSize();
        pln("@getAddressListResult() page: "+page+"totalCount"+totalCount+", size: "+size);
        return new BoardListResult(page, totalCount, size, list);
    }

    void pln(String str){
        System.out.println(str);
    }
}
