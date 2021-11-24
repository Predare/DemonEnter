package com.example.demoEnter.Controller;

import javax.annotation.security.RolesAllowed;

import com.example.demoEnter.DTO.Entry.EntryDto;
import com.example.demoEnter.Exception.ValidationException;
import com.example.demoEnter.Security.Role;
import com.example.demoEnter.Service.Interfaces.EntryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping(path = "entry")
@AllArgsConstructor
@Log
@RolesAllowed(Role.ENTRY_ADMIN)
public class EntryController {
    private final EntryService entryService;

    @PostMapping("/save")
    public EntryDto saveEntry(@RequestBody EntryDto entryDto) throws ValidationException {
        log.info("Handling save entry: " + entryDto);

        return entryService.saveEntry(entryDto);
    }

    @PostMapping("/edit")
    public EntryDto editEntry(@RequestBody EntryDto entryDto) throws ValidationException {
        log.info("Handling save entry: " + entryDto);

        return entryService.editEntry(entryDto);
    }

    @GetMapping("/findById/{id}")
    public EntryDto findById(@PathVariable Integer id) {
        log.info("Handling find by id request: " + id);
        return entryService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Integer id) {
        log.info("Handling delete entry request: " + id);
        entryService.deleteEntry(id);
        return ResponseEntity.ok().build();
    }

}
