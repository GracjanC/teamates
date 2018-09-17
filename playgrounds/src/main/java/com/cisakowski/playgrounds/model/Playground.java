package com.cisakowski.playgrounds.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Playground {

    @GeneratedValue
    @Id
    private Long id;
    @NonNull
    private String sportType;
}
