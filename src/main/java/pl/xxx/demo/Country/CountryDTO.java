package pl.xxx.demo.Country;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CountryDTO {
    private Long id;
    private String name;
    private String code;
}
