package hj.backend.service;


import hj.backend.domain.Board;

import java.util.List;

public interface BoardAjaxService {
    List<Board> listS();
    Board insertS(Board board);
    Board contentListS(long seq);
    void updateS(Board board);
    boolean deleteS(long seq);

    Board getBySeqS(long seq);

}
