package com.example.demoEnter.EntityConvertor;

import com.example.demoEnter.DTO.EntryDto;
import com.example.demoEnter.Entity.Entry;

import org.springframework.stereotype.Component;

@Component
public class EntryConvertor {

    public Entry fromEntryDtoToEntry(EntryDto entryDto) {
        return Entry.builder()
            .id(entryDto.getId())
            .name(entryDto.getName())
            .createDate(entryDto.getCreateDate())
            .entryAuthor(entryDto.getEntryAuthor())
            .build();
    }

    public EntryDto fromEntryToEntryDto(Entry entry) {
        return EntryDto.builder()
            .id(entry.getId())
            .name(entry.getName())
            .createDate(entry.getCreateDate())
            .entryAuthor(entry.getEntryAuthor())
            .build();
    }

}
