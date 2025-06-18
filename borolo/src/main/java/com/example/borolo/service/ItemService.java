package com.example.borolo.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.borolo.domain.Item;
import com.example.borolo.domain.User;
import com.example.borolo.dto.request.RegisterItemRequestDto;
import com.example.borolo.dto.request.UpdateItemRequestDto;
import com.example.borolo.dto.response.ItemDetailResponseDto;
import com.example.borolo.dto.response.ItemListResponseDto;
import com.example.borolo.dto.response.ItemSummaryDto;
import com.example.borolo.repository.ItemDao;
import com.example.borolo.repository.UserDao;



@Service
public class ItemService {

    private final ItemDao itemDao;
    private final UserDao userDao;

    public ItemService(ItemDao itemDao, UserDao userDao) {
        this.itemDao = itemDao;
        this.userDao = userDao;
    }
    
	// 1. 물품 등록
    public void registerItem(RegisterItemRequestDto dto, MultipartFile file) {
    	
        if (dto.getDeposit_amount() < dto.getPrice_per_day() * 0.1) {
            throw new IllegalArgumentException("보증금은 대여가의 10% 이상이어야 합니다.");
        }

        //이미지 저장
        String imageUrl = null;
        
        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/images";
            Path uploadPath = Paths.get(uploadDir);
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filepath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);

            imageUrl = "/uploads/images/" + filename; 

        } catch (IOException e) {
            e.printStackTrace();
        }

        Item item = new Item();
        item.setTitle(dto.getTitle());
        item.setDescription(dto.getDescription());
        item.setPrice_per_day(dto.getPrice_per_day());
        item.setDeposit_amount(dto.getDeposit_amount());
        item.setQuantity(dto.getQuantity());
        item.setImage_url(imageUrl);
        item.setLocation(dto.getLocation());
        item.setItem_status("거래가능");
        item.setCreate_time(LocalDateTime.now());
        item.setUpdate_time(LocalDateTime.now());
        item.setUser_id(dto.getUser_id());
        item.setCategory_id(dto.getCategory_id());

        itemDao.insert(item);
    }

    //2. 위치 기반 물품 조회 + 카테고리 필터링(home)
    public List<ItemListResponseDto> getItemsByLocation(String location, Integer category_id) {
        List<ItemListResponseDto> items = itemDao.findByLocation(location , category_id);

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("해당 위치에 물품이 존재하지 않습니다.");
        }

        return items;
    }

	// 3. 물품 상세 조회 
    public ItemDetailResponseDto getItemDetail(int item_id) {
        Item item = itemDao.findById(item_id);
        if (item == null) {
            throw new IllegalArgumentException("해당 물품을 찾을 수 없습니다.");
        }
        User owner = userDao.findById(item.getUser_id());

        ItemDetailResponseDto dto = new ItemDetailResponseDto();
        dto.setItem_id(item.getItem_id());
        dto.setTitle(item.getTitle());
        dto.setDescription(item.getDescription());
        dto.setQuantity(item.getQuantity());
        dto.setPrice_per_day(item.getPrice_per_day());
        dto.setDeposit_amount(item.getDeposit_amount());
        dto.setImage_url(item.getImage_url());
        dto.setOwner_nick_name(owner != null ? owner.getNick_name() : null);
        dto.setOwner_rating(owner != null ? owner.getRating() : null);

        return dto;
    }
    
	// 4. 유저 등록 물품 조회
    public List<ItemSummaryDto> getItemListByUser(int user_id) {
        return itemDao.findItemSummaryByUserId(user_id);
    }

    
    //5. 물품 단일 조회 (소유자 확인 포함)
    public Item getItem(int item_id, int user_id) {
        Item item = itemDao.findById(item_id);
        if (item == null || !item.getUser_id().equals(user_id)) {
            throw new IllegalArgumentException("조회 권한 없음");
        }
        return item;
    }

    
    // 6. 등록 물품 수정 (소유자 확인 포함)
    public void updateItem(int item_id, UpdateItemRequestDto dto, int user_id) {
        Item item = itemDao.findById(item_id);
        if (item == null || !item.getUser_id().equals(user_id)) {
            throw new IllegalArgumentException("수정 권한이 없거나 물품이 존재하지 않습니다.");
        }

        item.setTitle(dto.getTitle());
        item.setDescription(dto.getDescription());
        item.setPrice_per_day(dto.getPrice_per_day());
        item.setDeposit_amount(dto.getDeposit_amount());
        item.setQuantity(dto.getQuantity());
        item.setImage_url(dto.getImage_url());
        item.setUpdate_time(LocalDateTime.now());

        itemDao.update(item);
    }

    // 7. 물품 삭제 (소유자 확인 포함)
    public void deleteItem(int item_id, int user_id) {
        Item item = itemDao.findById(item_id);
        if (item == null || !item.getUser_id().equals(user_id)) {
            throw new IllegalArgumentException("삭제 권한이 없거나 물품이 존재하지 않습니다.");
        }

        int deletedCount = itemDao.delete(item_id);
        if (deletedCount == 0) {
            throw new IllegalStateException("물품 상태 상 삭제 할 수 없습니다.");
        }
    }
    
    //8. 신고 시 item_status 변경
    public void reportItem(int item_id) {
        itemDao.updateItemStatusToRequested(item_id, "신고");
    }

}