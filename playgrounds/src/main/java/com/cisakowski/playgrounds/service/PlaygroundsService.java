package com.cisakowski.playgrounds.service;

import com.cisakowski.playgrounds.model.Playground;
import com.cisakowski.playgrounds.repository.PlaygroundRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaygroundsService {
    @NonNull
    private final PlaygroundRepository playgroundRepository;

    public Playground getPlaygroundById(Long id) {
        return playgroundRepository.getById(id)
                .orElseThrow(PlaygroundNotFoundException::new);
    }

    public Playground addPlayground(Playground playground) {
        playgroundRepository.saveAndFlush(playground);
        return playground;
    }

    public void updatePlayground(Playground playground) {
        getPlaygroundById(playground.getId());
        playgroundRepository.save(playground);
    }

    public void deletePlaygroundWithId(Long id) {
        playgroundRepository.deleteById(id);
    }
}
