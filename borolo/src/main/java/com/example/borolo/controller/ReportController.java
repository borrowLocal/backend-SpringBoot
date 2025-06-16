package com.example.borolo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.borolo.dto.request.ReportRequestDto;
import com.example.borolo.dto.response.ReviewReportTargetDto;
import com.example.borolo.service.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/reports")
@Tag(name = "신고 관리", description = "신고 관련 API")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

  //1. 신고 모달 조회 rental_id , rental의 user_id
  @GetMapping("/item/{item_id}")
  @Operation(summary = "신고 모달 조회 - 제공자가 대여자 신고")
  public ResponseEntity<ReviewReportTargetDto> getReportTargetFromItem(@PathVariable int item_id) {
      ReviewReportTargetDto dto = reportService.getReportTargetFromItem(item_id);
      return ResponseEntity.ok(dto);
  }

  @GetMapping("/rental/{rental_id}")
  @Operation(summary = "신고 모달 조회 - 대여자가 제공자 신고")
  public ResponseEntity<ReviewReportTargetDto> getReportTargetFromRental(@PathVariable int rental_id) {
	  ReviewReportTargetDto dto = reportService.getReportTargetFromRental(rental_id);
      return ResponseEntity.ok(dto);
  }

  
  // 2. 신고 등록
  @PostMapping("/{reporter_id}")
  @Operation(summary = "신고 등록")
  public ResponseEntity<Void> submitReport(@PathVariable int reporter_id, @RequestBody @Valid ReportRequestDto dto) {
      try {
          reportService.submitReport(dto, reporter_id);
          return ResponseEntity.ok().build();
      } catch (IllegalArgumentException e) {
          return ResponseEntity.badRequest().build();
      }
  }
}
