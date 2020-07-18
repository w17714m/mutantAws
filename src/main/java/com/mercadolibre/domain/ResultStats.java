package com.mercadolibre.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ResultStats {
    public float count_mutant_dna;
    public float count_human_dna;
    public float ratio;
}
