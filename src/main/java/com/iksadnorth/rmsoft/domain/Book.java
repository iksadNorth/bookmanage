package com.iksadnorth.rmsoft.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter @NoArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
}
