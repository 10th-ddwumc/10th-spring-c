package com.example.umc10th.global.pagination;

import lombok.Builder;

import java.util.List;

public class PaginationResDTO {

    //페이지네이션 틀(오프셋 페이징)
    @Builder
    public record OffsetPagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
    }

    //페이지네이션 틀(커서 페이징)
    @Builder
    public record CursorPagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
    }
}
