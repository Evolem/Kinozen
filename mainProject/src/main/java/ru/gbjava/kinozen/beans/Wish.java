package ru.gbjava.kinozen.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Wish {
    private String id;
    private String userId;
    private String contentId;
    private String added;
}