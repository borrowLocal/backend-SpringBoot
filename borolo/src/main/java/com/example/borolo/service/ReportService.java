package com.example.borolo.service;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.borolo.domain.Rental;
import com.example.borolo.domain.Report;
import com.example.borolo.dto.request.ReportRequestDto;
import com.example.borolo.dto.response.ReviewReportTargetDto;
import com.example.borolo.repository.ItemDao;
import com.example.borolo.repository.RentalDao;
import com.example.borolo.repository.ReportDao;

import jakarta.persistence.EntityNotFoundException;



@Service
public class ReportService {
    private final ReportDao reportDao;
    private final RentalDao rentalDao;
    private final ItemDao itemDao;

    public ReportService(ReportDao reportDao, RentalDao rentalDao, ItemDao itemDao) {
        this.reportDao = reportDao;
        this.rentalDao = rentalDao;
        this.itemDao = itemDao;
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
        
        Rental rental = rentalDao.findById(dto.getRental_id());
        if (rental == null) {
            throw new EntityNotFoundException("해당 대여 정보가 없습니다.");
        }
        
        // Item 상태를 '신고'으로 업데이트
        itemDao.updateItemStatusToRequested(rental.getItem_id() , "신고");
    }

    // 2. 신고 모달 정보 조회 (제공자 -> 대여자)
    public ReviewReportTargetDto getReportTargetFromItem(int itemId) {
        // item_id를 이용해 대여자(rental_id, user_id, nickname)를 조회
        return rentalDao.findUserInfoByItemId(itemId)
                .orElseThrow(() -> new EntityNotFoundException("해당 item의 대여 정보가 없습니다."));
    }
    
    // 2. 신고 모달 정보 조회 (대여자 -> 제공자)
    public ReviewReportTargetDto getReportTargetFromRental(int rentalId) {
        // rental_id를 이용해 제공자(user_id, nickname)를 조회
        return rentalDao.findUserInfoByRentalId(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("해당 rental의 제공자 정보가 없습니다."));
    }

}
