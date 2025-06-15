package com.example.borolo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.borolo.dto.request.ReportRequestDto;
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

    // 1. 신고 등록
    @PostMapping
    @Operation(summary = "신고 등록")
    public ResponseEntity<Void> submitReport(@RequestParam int reporter_id, @RequestBody @Valid ReportRequestDto dto) {
        try {
            reportService.submitReport(dto, reporter_id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
