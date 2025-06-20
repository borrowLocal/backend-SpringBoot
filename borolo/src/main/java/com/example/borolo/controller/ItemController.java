package com.example.borolo.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.borolo.domain.Item;
import com.example.borolo.dto.request.RegisterItemRequestDto;
import com.example.borolo.dto.response.ItemDetailResponseDto;
import com.example.borolo.dto.response.ItemListResponseDto;
import com.example.borolo.dto.response.ItemSummaryDto;
import com.example.borolo.service.ItemService;
import com.example.borolo.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/items")
@Tag(name = "물품 관리", description = "물품 관련 API")
public class ItemController {

    private final ItemService itemService;
    private final RentalService rentalService;
    
    public ItemController(ItemService itemService, RentalService rentalService ) {
        this.itemService = itemService;
        this.rentalService = rentalService;
    }

    // 1. 물품 등록
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "물품 등록 (이미지 포함)")
    public ResponseEntity<Void> registerItem(
            @RequestPart("dto") RegisterItemRequestDto dto,
            @RequestPart("file") MultipartFile file) {
    	
        System.out.println("==== DTO 값 확인 ====");
        System.out.println(dto.toString());
        
        try {
            itemService.registerItem(dto, file);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 2. 위치 기반 물품 조회 (home) + 카테고리 필터링
    @GetMapping
    @Operation(summary = "위치 기반 물품 목록 조회 및 카테고리 필터링(home)")
    public ResponseEntity<List<ItemListResponseDto>> getItemsByLocation(
    		@RequestParam String location , 
    		@RequestParam(required = false) Integer category_id) {
        List<ItemListResponseDto> items = itemService.getItemsByLocation(location , category_id);
        return ResponseEntity.ok(items);
    }

    //3. 물품 단건 조회 (수정 전 조회)
    @GetMapping("/register/{item_id}")
    @Operation(summary = "물품 단건 조회")
    public ResponseEntity<Item> getItem(@PathVariable int item_id,
                                        @RequestParam int user_id) {
        try {
            Item item = itemService.getItem(item_id, user_id);
            return ResponseEntity.ok(item);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    // 4. 물품 수정
    @PutMapping(value = "/register/{item_id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "물품 수정 (이미지 포함)")
    public ResponseEntity<Void> updateItem(@PathVariable int item_id,
    				@RequestPart("dto") RegisterItemRequestDto dto,
                    @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            itemService.updateItem(item_id, dto, dto.getUser_id(), file);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 5. 물품 삭제
    @DeleteMapping("/register/{item_id}")
    @Operation(summary = "물품 삭제")
    public ResponseEntity<Void> deleteItem(@PathVariable int item_id,
                                           @RequestParam int user_id) {
        try {
            itemService.deleteItem(item_id, user_id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    
    // 6. 물품 상세 조회
    @GetMapping("/{item_id}")
    @Operation(summary = "물품 상세 조회")
    public ResponseEntity<ItemDetailResponseDto> getItemDetail(@PathVariable int item_id,
            @RequestParam int user_id) {
        try {
            ItemDetailResponseDto dto = itemService.getItemDetail(user_id, item_id);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 7. 물품제공자의 등록 물품 목록 조회
    @GetMapping("/user/{user_id}")
    @Operation(summary = "등록 물품 목록 조회")
    public ResponseEntity<List<ItemSummaryDto>> getItemListByUser(@PathVariable int user_id) {
        List<ItemSummaryDto> items = itemService.getItemListByUser(user_id);
        return ResponseEntity.ok(items);
    }

    //8. 완료 버튼 눌렀을 때
    @PutMapping("/complete")
    @Operation(summary = "item 상태 변경 (대여 완료)")
    public ResponseEntity<String> completeRental(@RequestParam int item_id) {
        try {
        	rentalService.completeRentalByItemId(item_id);
            return ResponseEntity.ok("대여거래가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    

}
