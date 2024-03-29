package com.usv.casino.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Beneficiu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBeneficiu;

    private Long procentDepunere;

    private Long bani;

    @JsonIgnore
    @OneToMany(
            targetEntity = Bonus.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="idBeneficiu", referencedColumnName = "idBeneficiu")
    private List<Bonus> bonusuri = new ArrayList<>();
}
