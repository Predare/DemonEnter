package com.example.demoEnter.Service.Interfaces;

import com.example.demoEnter.DTO.Entry.EntryDto;
import com.example.demoEnter.Exception.ValidationException;

import java.util.List;

public interface EntryService {
    EntryDto saveEntry(EntryDto usersDto) throws ValidationException;

    void deleteEntry(Integer entryId);

    List<EntryDto> findAll();

    EntryDto findById(Integer id);

    EntryDto editEntry(EntryDto entryDto) throws ValidationException;
}
