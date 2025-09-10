package pl.xxx.demo.User;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserResponseDTOMapper {

    public static UserResponseDTO convertToUserDTO(User user) {
        return UserResponseDTO
                .builder()
                .username(user.getUsername())
                .build();
    }

    public static List<UserResponseDTO> convertToUserDTOList(List<User> users) {
        return users
                .stream()
                .map(user -> convertToUserDTO(user))
                .collect(Collectors.toList());
    }



//    private static EmployeeLocationDTO convertToEmployeeDTO(Employee employee) {
//        EmployeeLocationDTO employeeLocationDTO = new EmployeeLocationDTO();
//        employeeLocationDTO.setEmployeeId(employee.getId());
//        employeeLocationDTO.setEmail(employee.getEmail());
//        employeeLocationDTO.setCity(Optional.ofNullable(employee.getLocation())
//                .map(Department::getCity).orElse("empty location"));
//        return employeeLocationDTO;
//    }
}
