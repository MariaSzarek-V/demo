package pl.xxx.demo.User;

import lombok.Builder;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public UserDTO convertToUserDTO(User user) {
        return UserDTO
                .builder()
                .username(user.getUsername())
                .build();
    }



//    public static List<EmployeeLocationDTO> mapEmployeeToDTO(List<Employee> employees) {
//        return employees
//                .stream()
//                .map(employee -> convertToEmployeeDTO(employee))
//                .collect(Collectors.toList());
//    }
//    private static EmployeeLocationDTO convertToEmployeeDTO(Employee employee) {
//        EmployeeLocationDTO employeeLocationDTO = new EmployeeLocationDTO();
//        employeeLocationDTO.setEmployeeId(employee.getId());
//        employeeLocationDTO.setEmail(employee.getEmail());
//        employeeLocationDTO.setCity(Optional.ofNullable(employee.getLocation())
//                .map(Department::getCity).orElse("empty location"));
//        return employeeLocationDTO;
//    }
}
