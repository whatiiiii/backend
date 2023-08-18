package hj.backend.service;

import hj.backend.domain.Board;
import hj.backend.dto.BoardListResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PageBoardService {
    Page<Board> findAll(Pageable pageable);
    Board insertS(Board board);
    Board contentListS(long seq);
    void updateS(Board board);
    boolean deleteS(long seq);

    BoardListResult getBoardListResult(Pageable pageable);
}
