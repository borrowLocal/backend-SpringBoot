package com.example.borolo.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.borolo.dto.request.RegisterItemRequestDto;
import com.example.borolo.dto.request.UpdateItemRequestDto;
import com.example.borolo.dto.response.ItemDetailResponseDto;
import com.example.borolo.dto.response.ItemSummaryDto;
import com.example.borolo.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/items")
@Tag(name = "물품 관리", description = "물품 관련 API")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 1. 물품 등록
    @PostMapping
    @Operation(summary = "물품 등록")
    public ResponseEntity<Void> registerItem(@RequestBody RegisterItemRequestDto dto) {
        try {
            itemService.registerItem(dto);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 2. 물품 상세 조회
    @GetMapping("/{item_id}")
    @Operation(summary = "물품 상세 조회")
    public ResponseEntity<ItemDetailResponseDto> getItemDetail(@PathVariable int item_id) {
        try {
            ItemDetailResponseDto dto = itemService.getItemDetail(item_id);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 3. 특정 사용자의 등록 물품 목록
    @GetMapping("/user/{user_id}")
    @Operation(summary = "등록 물품 목록 조회")
    public ResponseEntity<List<ItemSummaryDto>> getItemListByUser(@PathVariable int user_id) {
        List<ItemSummaryDto> items = itemService.getItemListByUser(user_id);
        return ResponseEntity.ok(items);
    }

    // 4. 물품 수정
    @PutMapping("/{item_id}")
    @Operation(summary = "물품 수정")
    public ResponseEntity<Void> updateItem(@PathVariable int item_id,
                                           @RequestBody UpdateItemRequestDto dto) {
        try {
            itemService.updateItem(item_id, dto, dto.getUser_id());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 5. 물품 삭제
    @DeleteMapping("/{item_id}")
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
}
