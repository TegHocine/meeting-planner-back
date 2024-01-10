package com.formation.meetingplanner.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginationDto<T> {
    private Integer currentPage;
    private boolean hasNextPage;
    private Integer totalPages;
    private Long totalItems;
    private List<T> page;
}
