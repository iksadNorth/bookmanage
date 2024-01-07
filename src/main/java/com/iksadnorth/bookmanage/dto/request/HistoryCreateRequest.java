package com.iksadnorth.bookmanage.dto.request;

import com.iksadnorth.bookmanage.type.HistoryChangeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class HistoryCreateRequest {
    private HistoryChangeType type;
}
