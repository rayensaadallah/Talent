package com.example.talent.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Carrier  implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Integer id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String Level;
    private int needed_days;
    private Date date_Start;
    @OneToMany(mappedBy = "carrier", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Users> users;
}
