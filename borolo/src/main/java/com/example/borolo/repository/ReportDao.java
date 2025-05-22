package com.example.borolo.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.borolo.domain.Report;

@Mapper
public interface ReportDao {
    Report findById(int report_id);
    List<Report> findByReporterId(int reporter_id);
    List<Report> findByTargetUserId(int target_user_id);
    List<Report> findAll();
   
    void insert(Report report);
}
