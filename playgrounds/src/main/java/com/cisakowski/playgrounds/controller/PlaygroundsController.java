package com.cisakowski.playgrounds.controller;

import com.cisakowski.playgrounds.dto.PlaygroundDto;
import com.cisakowski.playgrounds.model.Mapper;
import com.cisakowski.playgrounds.model.Playground;
import com.cisakowski.playgrounds.service.PlaygroundsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("playgrounds")
@RestController
@RequiredArgsConstructor
public class PlaygroundsController {
    @NonNull
    private final PlaygroundsService playgroundsService;

    @NonNull
    private final Mapper mapper;
    private final UriBuilder uriBuilder;

    @GetMapping("{playground-id}")
    public PlaygroundDto getPlaygroundById(@PathVariable("playground-id") Long id) {
        Playground playground = playgroundsService.getPlaygroundById(id);
        return modelToDto(playground);
    }

    @PostMapping
    public ResponseEntity createPlayground(@RequestBody PlaygroundDto playgroundDto) {
        Playground playground = playgroundsService.addPlayground(dtoToModel(playgroundDto));
        URI newPlaygroundUri = uriBuilder.requestUriWithId(playground.getId());
        return ResponseEntity.created(newPlaygroundUri).body(modelToDto(playground));
    }

    @PutMapping("{playground-id}")
    public ResponseEntity updatePlayground(@PathVariable("playground-id")Long id,
                                           @RequestBody PlaygroundDto playgroundDto) {
        Playground playground = dtoToModel(playgroundDto);
        playground.setId(id);
        playgroundsService.updatePlayground(playground);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{playground-id}")
    public ResponseEntity deletePlayground(@PathVariable("playground-id")Long id) {
        playgroundsService.deletePlaygroundWithId(id);
        return ResponseEntity.noContent().build();
    }

    private PlaygroundDto modelToDto(Playground playground) {
        return mapper.map(playground, PlaygroundDto.class);
    }

    private Playground dtoToModel(PlaygroundDto playgroundDto) {
        return mapper.map(playgroundDto, Playground.class);
    }
}
