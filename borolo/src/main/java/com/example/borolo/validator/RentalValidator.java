package com.example.borolo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.borolo.domain.Item;
import com.example.borolo.dto.request.RentalRequestDto;
import com.example.borolo.repository.ItemDao;
import com.example.borolo.repository.RentalDao;
import com.example.borolo.repository.UserDao;

@Component
public class RentalValidator implements Validator {
    private final ItemDao itemDao;
    private final UserDao userDao;
    private final RentalDao rentalDao;

    public RentalValidator(ItemDao itemDao, UserDao userDao, RentalDao rentalDao) {
        this.itemDao = itemDao;
        this.userDao = userDao;
        this.rentalDao = rentalDao; 
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return RentalRequestDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	System.out.println("RentalValidator called");
        RentalRequestDto dto = (RentalRequestDto) target;

        // 1. 시작일 < 종료일
        if (dto.getStart_date() != null && dto.getEnd_date() != null) {
            if (dto.getStart_date().isAfter(dto.getEnd_date())) {
                errors.rejectValue("start_date", "invalid.start_date", "시작일은 종료일보다 빠를 수 없습니다.");
            }
        }

        // 2. 렌탈 수량 > 아이템 수량 불가
        if (dto.getItem_id() != null && dto.getRental_quantity() != null) {
            Item item = itemDao.findById(dto.getItem_id()); // null 가능성 있음
            if (item == null) {
                errors.rejectValue("item_id", "not.exist.item", "해당 아이템이 존재하지 않습니다.");
            } else if (dto.getRental_quantity() > item.getQuantity()) {
                errors.rejectValue("rental_quantity", "exceed.quantity", "대여 수량이 보유 수량보다 많습니다.");
            }
        }
        
        // 3. 유저 존재 여부
        if (dto.getUser_id() != null) {
            if (userDao.findById(dto.getUser_id()) == null) {
                errors.rejectValue("user_id", "invalid.user_id", "해당 유저가 존재하지 않습니다.");
            }
        }

        // 5. 반환일(expected_return_at)이 종료일(end_date) 이후여야 한다
        if (dto.getExpected_return_at() != null && dto.getEnd_date() != null) {
            if (dto.getExpected_return_at().isBefore(dto.getEnd_date()) || dto.getExpected_return_at().isEqual(dto.getEnd_date())) {
                errors.rejectValue("expected_return_at", "invalid.return_date", "반환일은 종료일 이후여야 합니다.");
            }
        }
        
    }
}
