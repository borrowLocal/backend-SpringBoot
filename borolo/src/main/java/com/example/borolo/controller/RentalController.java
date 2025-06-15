package com.example.borolo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.borolo.dto.request.RentalRequestDto;
import com.example.borolo.dto.response.RentalApplicationListResponseDto;
import com.example.borolo.dto.response.RentalPaymentResponseDto;
import com.example.borolo.dto.response.RentalStatusResponseDto;
import com.example.borolo.service.RentalService;
import com.example.borolo.validator.RentalValidator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/rentals")
@Tag(name = "대여 관리", description = "대여 관련 API")
public class RentalController {
	
    private final RentalService rentalService;
    private final RentalValidator rentalValidator;
    
    public RentalController(RentalService rentalService, RentalValidator rentalValidator) {
        this.rentalService = rentalService;
        this.rentalValidator = rentalValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(rentalValidator);
    }
    
    // 1. 대여 신청
    @PostMapping
    @Operation(summary = "대여 신청")
    public ResponseEntity<?> applyRental(@Valid @RequestBody RentalRequestDto dto, Errors errors) {
    	 if (errors.hasErrors()) {
		    String errorMsg = errors.getAllErrors().stream()
		            .map(DefaultMessageSourceResolvable::getDefaultMessage)
		            .collect(Collectors.joining(", "));
		    return ResponseEntity.badRequest().body(errorMsg);
    	}
    	 
    	try {
            rentalService.applyRental(dto, dto.getUser_id());
            return ResponseEntity.ok("대여 신청 처리되었습니다.");
    	} catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 상세 메시지 반환
        } catch (Exception e) {
            e.printStackTrace(); // 서버 콘솔에 전체 로그 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생: " + e.getMessage());
        }
    }

    // 2. 내 대여 내역 조회 
    @GetMapping("/status/{user_id}")
    @Operation(summary = "내 대여 내역 조회")
    public ResponseEntity<List<RentalStatusResponseDto>> getRentalStatus(@PathVariable int user_id) {
    	try {
	        List<RentalStatusResponseDto> rentals = rentalService.getRentalStatus(user_id);
	        return ResponseEntity.ok(rentals);
	    }catch (IllegalArgumentException e) {
			 return ResponseEntity.notFound().build();
		 }
    }

    // 3. 대여 수락
    @PutMapping("/status/approve/{rental_id}")
    @Operation(summary = "대여 수락")
    public ResponseEntity<Void> approveRental(@PathVariable int rental_id) {
        try {
            rentalService.approveRental(rental_id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. 대여 거절
    @PutMapping("/status/reject/{rental_id}")
    @Operation(summary = "대여 거절")
    public ResponseEntity<Void> rejectRental(@PathVariable int rental_id) {
        try {
            rentalService.rejectRental(rental_id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. 대여중 처리 (결제 완료 시점)
    @PutMapping("/status/start/{rental_id}")
    @Operation(summary = "대여중 상태 처리")
    public ResponseEntity<Void> startRenting(@PathVariable int rental_id) {
    	try {
            rentalService.startRenting(rental_id); // 상태만 '대여중'
            return ResponseEntity.ok().build();
    	} catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // 8. 대여 요청(신청자) 목록
    @GetMapping("/applicants/{item_id}")
    @Operation(summary = "대여 요청(신청자) 목록 조회")
    public ResponseEntity<List<RentalApplicationListResponseDto>> getApplicants(@PathVariable int item_id) {
    	 try {
	        List<RentalApplicationListResponseDto> applicants = rentalService.getApplicants(item_id);
	        return ResponseEntity.ok(applicants);
    	 }catch (IllegalArgumentException e) {
    		 return ResponseEntity.notFound().build();
    	 }
    }
    
    // 9. 결제 정보 조회
    @GetMapping("/payments/{rental_id}")
    @Operation(summary = "결제 정보 조회")
    public ResponseEntity<RentalPaymentResponseDto> getPaymentInfo(@PathVariable int rental_id) {
        try {
            RentalPaymentResponseDto dto = rentalService.getRentalPaymentInfo(rental_id);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


}