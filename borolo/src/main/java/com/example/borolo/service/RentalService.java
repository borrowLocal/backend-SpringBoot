package com.example.borolo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.borolo.domain.Item;
import com.example.borolo.domain.Rental;
import com.example.borolo.domain.User;
import com.example.borolo.domain.UserLocation;
import com.example.borolo.dto.request.RentalRequestDto;
import com.example.borolo.dto.response.RentalPaymentResponseDto;
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

    // 1. 대여 신청
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
        rental.setRental_status("대기 중");
        rental.setIs_approved(false);
        rental.setIs_completed(false);

        rentalDao.insert(rental);
    }

    // 2. 대여 상태 확인
    public List<Rental> getRentalStatus(int user_id) {
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
	
    // 4. 대여 완료
    public void completeRental(int rental_id) {
        Rental rental = rentalDao.findById(rental_id);
        if (rental == null) {
            throw new IllegalArgumentException("대여 정보가 없습니다.");
        }
        rentalDao.completeRental(rental_id);
        rentalDao.updateStatus(rental_id, "완료됨");
    }

    // 5. 물품의 신청자 목록 조회
    public List<Rental> getApplicants(int item_id) {
        return rentalDao.findByItemId(item_id);
    }

    // 6. 결제 물품 정보 조회
	public RentalPaymentResponseDto getRentalPaymentInfo(int rental_id) {
	    Rental rental = rentalDao.findById(rental_id);
	    if (rental == null) {
	        throw new IllegalArgumentException("대여 정보를 찾을 수 없습니다.");
	    }

	    Item item = itemDao.findById(rental.getItem_id());
	    if (item == null) {
	        throw new IllegalArgumentException("물품 정보를 찾을 수 없습니다.");
	    }

	    User user = userDao.findById(item.getUser_id());
	    if (user == null) {
	        throw new IllegalArgumentException("대여자 정보를 찾을 수 없습니다.");
	    }

	    UserLocation location = userLocationDao.findByUserId(user.getUser_id());

	    // 대여일 수 계산 (종료일 - 시작일)
	    long days = java.time.temporal.ChronoUnit.DAYS.between(
	        rental.getStart_date(), rental.getEnd_date());

	    int totalAmount = item.getPrice_per_day() * (int) days + item.getDeposit_amount();

	    RentalPaymentResponseDto dto = new RentalPaymentResponseDto();
	    dto.setRenterNickName(user.getNick_name());
	    dto.setItemTitle(item.getTitle());
	    dto.setPricePerDay(item.getPrice_per_day());
	    dto.setDepositAmount(item.getDeposit_amount());
	    dto.setTotalAmount(totalAmount);
	    dto.setStartDate(rental.getStart_date());
	    dto.setEndDate(rental.getEnd_date());
	    dto.setMeetingTime(rental.getMeeting_time());
	    dto.setMeetingLocation(rental.getMeeting_location());

	    if (location != null) {
	        dto.setDistrict(location.getDistrict());
	        dto.setLat(location.getLat());
	        dto.setLng(location.getLng());
	    }

	    return dto;
	}
    
    
}
