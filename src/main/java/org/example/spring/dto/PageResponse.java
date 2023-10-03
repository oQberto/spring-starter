package org.example.spring.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.domain.Page;

import java.util.List;

@Value
@Builder
public class PageResponse<T> {
    List<T> content;
    MetaData metadata;


    public static <T> PageResponse<T> of(Page<T> page) {
        MetaData metadata = MetaData.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .build();

        return PageResponse.<T>builder()
                .content(page.getContent())
                .metadata(metadata)
                .build();
    }

    @Value
    @Builder
    public static class MetaData {
        int page;
        int size;
        long totalElements;
    }
}
