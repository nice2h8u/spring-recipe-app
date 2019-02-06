package com.nicetoh8u.springrecipeapp.model;



import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"recipe"})
@Table(name = "notes")
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Recipe recipe;
    @Lob //more then
    private String recipeNotes;


}
