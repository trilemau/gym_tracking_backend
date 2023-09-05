package com.trilemau.gymtracking.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "exercise_sets")
public class ExerciseSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EXERCISE_ID")
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORKOUT_ID", referencedColumnName = "ID")
    private Workout workout;

    @Column(name = "REPS", nullable = false)
    private int reps;

    @Column(name = "WEIGHT", nullable = false)
    private double weight;

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("exercise", exercise)
                .append("workout", workout)
                .append("reps", reps)
                .append("weight", weight)
                .toString();
    }
}
