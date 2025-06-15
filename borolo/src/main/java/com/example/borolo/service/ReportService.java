package com.example.borolo.service;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.borolo.domain.Report;
import com.example.borolo.dto.request.ReportRequestDto;
import com.example.borolo.repository.ReportDao;



@Service
public class ReportService {
    private final ReportDao reportDao;

    public ReportService(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    // 1. 신고 등록
    public void submitReport(ReportRequestDto dto, int reporter_id) {
    	
        if (reporter_id == dto.getTarget_user_id()) {
            throw new IllegalArgumentException("자기 자신은 신고할 수 없습니다.");
        }
        
        Report report = new Report();
        report.setReporter_id(reporter_id);
        report.setTarget_user_id(dto.getTarget_user_id());
        report.setRental_id(dto.getRental_id());
        report.setReason(dto.getReason());
        report.setCreated_at(LocalDateTime.now());

        reportDao.insert(report);
    }

    // 2. 신고 모달 정보 조회

}
