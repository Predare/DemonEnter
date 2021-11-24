package com.example.demoEnter.Service.Default;

import java.util.List;

import com.example.demoEnter.DTO.EntryDto;
import com.example.demoEnter.Entity.Entry;
import com.example.demoEnter.EntityConvertor.EntryConvertor;
import com.example.demoEnter.Exception.ValidationException;
import com.example.demoEnter.Repository.EntryRepository;
import com.example.demoEnter.Service.Interfaces.EntryService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DefaultEntryService implements EntryService {


    private final EntryRepository entryRepository;
    private final EntryConvertor entryConvertor;
    
    public EntryDto saveEntry(EntryDto entryDto) throws ValidationException {
        validateEntryDto(entryDto);

        /*Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDefaultDetails){
            entryDto.setUserId( ((UserDefaultDetails)principal).getId());
        }else{
            return null;
        }*/

        Entry savedEntry = entryRepository.save(entryConvertor.fromEntryDtoToEntry(entryDto));

        return entryConvertor.fromEntryToEntryDto(savedEntry);
    }

    private void validateEntryDto(EntryDto entryDto) throws ValidationException {
        if (entryDto == null) {
            throw new ValidationException("Object entry is null");
        }
    }

    public void deleteEntry(Integer id) {
        entryRepository.deleteById(id);
    }

    public EntryDto findById(Integer id){
        if(entryRepository.findById(id) == null){
            throw new NullPointerException();
        }

        Entry foundedEntry =  entryRepository.findById(id).get();
        return entryConvertor.fromEntryToEntryDto(foundedEntry);
    }

    @Override
    public List<EntryDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EntryDto editEntry(EntryDto entryDto) throws ValidationException {
        
        validateEntryDto(entryDto);
        Entry savedEntry = entryRepository.save(entryConvertor.fromEntryDtoToEntry(entryDto));

        return entryConvertor.fromEntryToEntryDto(savedEntry);
    }
}
