package ru.gbjava.kinozen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbjava.kinozen.persistence.entities.Content;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class HistoryDto {
    private Date historyDate;
    private Content historyContent;
}
