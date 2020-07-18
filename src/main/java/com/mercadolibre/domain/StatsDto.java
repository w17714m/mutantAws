package com.mercadolibre.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatsDto {
        private String id;
        private String tipo;
        private Float total;
}
