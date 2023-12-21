package com.iksadnorth.rmsoft.dto.request;

import com.iksadnorth.rmsoft.type.HistoryChangeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class HistoryCreateRequest {
    private HistoryChangeType type;
}
