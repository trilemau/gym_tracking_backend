package com.trilemau.gymtracking.domain.entity;

import com.trilemau.gymtracking.domain.enums.MuscleGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "EXERCISES")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column
    @Enumerated
    @ElementCollection(targetClass = MuscleGroup.class)
    private List<MuscleGroup> muscleGroups;

    @Lob
    @Column(name = "IMAGE")
    private byte[] image;
}
