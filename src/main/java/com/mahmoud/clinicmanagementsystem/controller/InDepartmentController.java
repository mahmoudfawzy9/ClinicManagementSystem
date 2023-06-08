package com.mahmoud.clinicmanagementsystem.controller;

import com.mahmoud.clinicmanagementsystem.dto.InDepartmentDto;
import com.mahmoud.clinicmanagementsystem.helper.message.ControllerLogMessage;
import com.mahmoud.clinicmanagementsystem.request.indepartment.CreateInDepartmentRequest;
import com.mahmoud.clinicmanagementsystem.request.indepartment.UpdateInDepartmentRequest;
import com.mahmoud.clinicmanagementsystem.service.InDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/in-departments")
@Slf4j
public class InDepartmentController {
    private final InDepartmentService inDepartmentService;

    public InDepartmentController(InDepartmentService inDepartmentService) {
        this.inDepartmentService = inDepartmentService;
    }

    @PostMapping
    public ResponseEntity<Void> createInDepartment(@RequestBody CreateInDepartmentRequest request){
        inDepartmentService.createInDepartment(request);

        log.info(ControllerLogMessage.InDepartment.IN_DEPARTMENT_CREATE);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInDepartment(@PathVariable String id,
                                                   @RequestBody UpdateInDepartmentRequest request){
        inDepartmentService.updateInDepartment(id, request);

        log.info(ControllerLogMessage.InDepartment.IN_DEPARTMENT_UPDATE + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInDepartment(@PathVariable String id){
        inDepartmentService.deleteInDepartment(id);

        log.info(ControllerLogMessage.InDepartment.IN_DEPARTMENT_DELETE + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InDepartmentDto> findInDepartmentById(@PathVariable String id){
        InDepartmentDto inDepartment = inDepartmentService.findInDepartmentById(id);

        log.info(ControllerLogMessage.InDepartment.IN_DEPARTMENT_FIND + id);
        return ResponseEntity.ok(inDepartment);
    }

    @GetMapping
    public ResponseEntity<List<InDepartmentDto>> findAllInDepartments(){
        List<InDepartmentDto> inDepartments = inDepartmentService.findAllInDepartments();

        log.info(ControllerLogMessage.InDepartment.IN_DEPARTMENT_FIND_ALL);
        return ResponseEntity.ok(inDepartments);
    }
}
