package com.neighbor.service;

import com.neighbor.domain.dao.ReplyFileDAO;
import com.neighbor.domain.vo.ReplyFileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyFileService implements FileService{
    private final ReplyFileDAO replyFileDAO;

    public void upload(List<ReplyFileVO> replyFiles){
        /* 파일 하나씩 다 넣기 */
        for (ReplyFileVO replyFileVO : replyFiles) {
            replyFileDAO.save(replyFileVO);
        }
    }

}
