package com.example.umc10th.global.pagination;

import com.example.umc10th.domain.mission.dto.MissionResDTO;

import java.util.List;

public class PaginationConverter {

    //오프셋 페이지네이션 틀 생성
    public static <T> PaginationResDTO.OffsetPagination<T> toOffsetPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize

    ) {
        return PaginationResDTO.OffsetPagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

    //커서 페이지네이션 틀 생성
    public static <T> PaginationResDTO.CursorPagination<T> toCursorPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize

    ) {
        return PaginationResDTO.CursorPagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
