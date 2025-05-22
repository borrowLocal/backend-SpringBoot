package com.example.borolo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.borolo.domain.Rental;
import com.example.borolo.dto.request.RentalRequestDto;
import com.example.borolo.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/rentals")
@Tag(name = "대여 관리", description = "대여 관련 API")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // 1. 대여 신청
    @PostMapping
    @Operation(summary = "대여 신청")
    public ResponseEntity<Void> applyRental(@RequestBody RentalRequestDto dto) {
        try {
            rentalService.applyRental(dto, dto.getUser_id());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 2. 내 대여 상태 조회
    @GetMapping("/status/{user_id}")
    @Operation(summary = "내 대여 상태 조회")
    public ResponseEntity<List<Rental>> getRentalStatus(@PathVariable int user_id) {
        List<Rental> rentals = rentalService.getRentalStatus(user_id);
        return ResponseEntity.ok(rentals);
    }

    // 3. 대여 수락
    @PutMapping("/approve/{rental_id}")
    @Operation(summary = "대여 수락")
    public ResponseEntity<Void> approveRental(@PathVariable int rental_id) {
        try {
            rentalService.approveRental(rental_id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. 대여 완료 처리
    @PutMapping("/complete/{rental_id}")
    @Operation(summary = "대여 완료 처리")
    public ResponseEntity<Void> completeRental(@PathVariable int rental_id) {
        try {
            rentalService.completeRental(rental_id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. 대여 신청자 목록 
    @GetMapping("/applicants/{item_id}")
    @Operation(summary = "대여 신청자 목록 조회")
    public ResponseEntity<List<Rental>> getApplicants(@PathVariable int item_id) {
        List<Rental> applicants = rentalService.getApplicants(item_id);
        return ResponseEntity.ok(applicants);
    }
    
    
}