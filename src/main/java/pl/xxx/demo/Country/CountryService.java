package pl.xxx.demo.Country;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public List<CountryDTO> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return CountryDTOMapper.convertToDTOList(countries);
    }
}
