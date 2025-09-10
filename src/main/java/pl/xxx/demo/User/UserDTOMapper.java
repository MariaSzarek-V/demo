package pl.xxx.demo.User;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserDTOMapper {

    public static UserDTO convertToUserDTO(User user) {
        return UserDTO
                .builder()
                .username(user.getUsername())
                .build();
    }

    public static List<UserDTO> convertToUserDTOList(List<User> users) {
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
