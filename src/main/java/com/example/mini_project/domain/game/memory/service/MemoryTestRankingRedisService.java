package com.example.mini_project.domain.game.memory.service;

import com.example.mini_project.domain.game.common.dto.RankingResponseDto;
import com.example.mini_project.domain.game.common.service.RankingRedisService;
import com.example.mini_project.domain.game.memory.entity.MemoryTestRanking;
import com.example.mini_project.domain.game.memory.repository.MemoryTestRankingRedisRepository;
import com.example.mini_project.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemoryTestRankingRedisService implements RankingRedisService {

    private final MemoryTestRankingRedisRepository memoryTestRankingRedisRepository;

    // 랭킹 생성
    @Override
    public void createRanking(User user, int level, int gameScore) {
        MemoryTestRanking memoryTestRanking = new MemoryTestRanking(user.getEmail(), user.getUsername(), level, gameScore);

        memoryTestRankingRedisRepository.save(memoryTestRanking);
    }

    // 랭킹 반환
    @Override
    public List<RankingResponseDto> getTopRankings(int topN) {
        List<MemoryTestRanking> memoryTestRankings = memoryTestRankingRedisRepository.getRankingRange(0, topN - 1);
        log.info(memoryTestRankings.toString());

        return IntStream.range(0, memoryTestRankings.size()).
                mapToObj(i -> new RankingResponseDto(memoryTestRankings.get(i), i + 1)).toList();
    }
}
