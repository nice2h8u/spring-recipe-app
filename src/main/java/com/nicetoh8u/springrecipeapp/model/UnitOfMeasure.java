package com.nicetoh8u.springrecipeapp.model;



import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@Table(name = "unit_of_measure")
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;


}
