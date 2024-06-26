package com.example.mini_project.domain.game.common.service;

import com.example.mini_project.domain.game.common.dto.RankingRequestDto;
import com.example.mini_project.domain.game.common.dto.RecordResponseDto;
import com.example.mini_project.domain.user.entity.User;

import java.util.List;

public interface RecordService {

    void createRecord(User user, RankingRequestDto rankingRequestDto);

    List<RecordResponseDto> getRecords(User user);

    void deleteRecord(User user, Long recordId);
}
