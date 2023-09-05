package com.trilemau.gymtracking.domain.entity;

import com.trilemau.gymtracking.domain.enums.MuscleGroup;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

@EqualsAndHashCode
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
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = MuscleGroup.class, fetch = FetchType.EAGER)
    private Set<MuscleGroup> muscleGroups = new HashSet<>();

    @Lob
    @Column(name = "IMAGE")
    private byte[] image;

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("name", name)
                .append("muscleGroups", muscleGroups)
                .append("imageLength", image == null ? "null" : image.length)
                .toString();
    }
}
