package com.example.talent.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Skills {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private String name;
    private Integer Rate;
    private boolean Verified;
    private String grade;
   @ManyToMany(cascade = CascadeType.ALL,mappedBy = "skills")
    private List<Cv> cv;
}
