package com.example.borolo.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.borolo.dto.request.ReportRequestDto;
import com.example.borolo.dto.response.ReportResponseDto;
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

    // 2. 특정 유저가 받은 신고 목록 조회
    @GetMapping("/target/{user_id}")
    @Operation(summary = "신고 목록 조회")
    public ResponseEntity<List<ReportResponseDto>> getReportsAboutUser(@PathVariable int user_id) {
        try {
            List<ReportResponseDto> reports = reportService.getReportsAboutUser(user_id);
            return ResponseEntity.ok(reports);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
