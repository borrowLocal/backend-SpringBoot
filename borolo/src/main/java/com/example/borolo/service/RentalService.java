package com.example.borolo.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.borolo.domain.Item;
import com.example.borolo.domain.Rental;
import com.example.borolo.dto.request.RentalRequestDto;
import com.example.borolo.dto.response.RentalApplicationListResponseDto;
import com.example.borolo.dto.response.RentalPaymentResponseDto;
import com.example.borolo.dto.response.RentalStatusResponseDto;
import com.example.borolo.repository.ItemDao;
import com.example.borolo.repository.RentalDao;
import com.example.borolo.repository.UserDao;
import com.example.borolo.repository.UserLocationDao;


@Service
public class RentalService {
    private final RentalDao rentalDao;
    private final ItemDao itemDao;
    private final UserDao userDao;
    private final UserLocationDao userLocationDao;
    
    public RentalService(RentalDao rentalDao, ItemDao itemDao, UserDao userDao, UserLocationDao userLocationDao) {
        this.rentalDao = rentalDao;
        this.itemDao = itemDao;
        this.userDao = userDao;
        this.userLocationDao = userLocationDao;
    }

    // 1. 대여 신청 (모달)
    public void applyRental(RentalRequestDto dto, int user_id) {
        Item item = itemDao.findById(dto.getItem_id());
        if (item == null) {
            throw new IllegalArgumentException("해당 물품이 존재하지 않습니다.");
        }

        Rental rental = new Rental();
        rental.setItem_id(dto.getItem_id());
        rental.setUser_id(user_id);
        rental.setStart_date(dto.getStart_date());
        rental.setEnd_date(dto.getEnd_date());
        rental.setMeeting_location(dto.getMeeting_location());
        rental.setMeeting_time(dto.getMeeting_time());
        rental.setRental_quantity(dto.getRental_quantity()); //수량 추가
        rental.setExpected_return_at(dto.getExpected_return_at()); //반납일 추가
        rental.setRental_status("신청완료"); // 신청완료 , 대여중, 거절, 결제요청, 거래완료
        rental.setIs_approved(false); //수락 여부
        rental.setIs_completed(false); //거래 완료 여부

        rentalDao.insert(rental);
    }

    // 2. 대여 내역 조회 
    public List<RentalStatusResponseDto> getRentalStatus(int user_id) {
    	
        return rentalDao.findByUserId(user_id);
    }

    // 3. 대여 수락
	  public void approveRental(int rental_id) {
		  
	      Rental rental = rentalDao.findById(rental_id);
	          if (rental == null) {
	             throw new IllegalArgumentException("대여 신청이 존재하지 않습니다.");
	          }
	
	      rentalDao.approveRental(rental_id);
	 }
	  
	// 4. 대여 거절 / 대여 거절 버튼 누르면 실행
	  public void rejectRental(int rental_id) {
		  
	    Rental rental = rentalDao.findById(rental_id);
	    if (rental == null) {
	        throw new IllegalArgumentException("대여 신청이 존재하지 않습니다.");
	    }

	    rentalDao.rejectRental(rental_id);
	}

    // 5. 대여 상태 (rental_status -> "거래 완료") / 거래 완료 버튼을 누르면 실행 & 물품 관리 상태 영향
    public void completeRental(int rental_id) {
    	
        Rental rental = rentalDao.findById(rental_id);
        if (rental == null) {
            throw new IllegalArgumentException("대여 정보가 없습니다.");
        }
        rentalDao.completeRental(rental_id);
    }
    
    // 6. 대여 상태 (rental_status -> "대여중") / 결제 완료 버튼을 누르면 실행
    public void startRenting(int rental_id) {
    	
        Rental rental = rentalDao.findById(rental_id);
        if (rental == null) throw new IllegalArgumentException("대여 없음");
        rentalDao.updateStatus(rental_id, "대여중");
    }
    
    // 7. 대여 상태 (rental_status -> "대여 완료") / 물품 관리 상태에 영향을 받음
    public void finishRenting(int rental_id) {
    	
        Rental rental = rentalDao.findById(rental_id);
        if (rental == null) throw new IllegalArgumentException("대여 완료가 제대로 수행 되지 않음");
        rentalDao.updateStatus(rental_id, "대여완료");
    }
    
    // 8. 물품 요청(신청자) 목록 조회
    public List<RentalApplicationListResponseDto> getApplicants(int item_id) {
        return rentalDao.findByItemId(item_id);
    }

    // 9. 결제 물품 정보 조회
    public RentalPaymentResponseDto getRentalPaymentInfo(int rental_id) {
    	
        RentalPaymentResponseDto dto = rentalDao.findPaymentInfoByRentalId(rental_id);
        if (dto == null) {
            throw new IllegalArgumentException("대여 정보를 찾을 수 없습니다.");
        }

        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
        int totalAmount = dto.getPricePerDay() * (int) days + dto.getDepositAmount();
        dto.setTotalAmount(totalAmount);

        return dto;
    }
    

    
}
