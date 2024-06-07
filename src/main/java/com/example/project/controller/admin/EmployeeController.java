package com.example.project.controller.admin;

import com.example.project.dto.response.EmployeeResponse;
import com.example.project.dto.response.RoleResponse;
import com.example.project.service.impl.EmployeeService;
import com.example.project.service.impl.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    EmployeeService employeeService;
    RoleService roleService;

    @GetMapping("/employee")
    public String showEmployees(Model model) {
        List<EmployeeResponse> employeeResponses = employeeService.getAll();
        model.addAttribute("employees", employeeResponses);
        return "/admin/employee/index";
    }

    @GetMapping("/add-employee")
    public String addEmployee(Model model) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        List<RoleResponse> roleResponses = roleService.getAll();
        model.addAttribute("employee", employeeResponse);
        model.addAttribute("roles", roleResponses);
        return "/admin/employee/edit";
    }

    @GetMapping("/edit-employee")
    public String updateEmployee(Model model, @RequestParam(value = "id", required = false) String id) {
        EmployeeResponse employeeResponse = employeeService.findById(id);
        List<RoleResponse> roleResponses = roleService.getAll();
        model.addAttribute("employee", employeeResponse);
        model.addAttribute("roles", roleResponses);
        return "/admin/employee/edit";
    }
}
