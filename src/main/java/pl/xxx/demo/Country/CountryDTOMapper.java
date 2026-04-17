package pl.xxx.demo.Country;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryDTOMapper {

    public static CountryDTO convertToDTO(Country country) {
        return CountryDTO
                .builder()
                .id(country.getId())
                .name(country.getName())
                .code(country.getCode())
                .build();
    }

    public static List<CountryDTO> convertToDTOList(List<Country> countries) {
        return countries
                .stream()
                .map(CountryDTOMapper::convertToDTO)
                .toList();
    }
}
