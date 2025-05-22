package com.example.borolo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.borolo.dto.request.FavoriteRequestDto;
import com.example.borolo.dto.response.FavoriteListResponseDto;
import com.example.borolo.service.FavoriteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/favorites")
@Tag(name = "물품 - 즐겨찾기 관리", description = "물품 - 즐겨찾기 관련 API")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    // 즐겨찾기 추가
    @PostMapping
    @Operation(summary = "즐겨찾기 추가")
    public ResponseEntity<Void> addFavorite(@RequestParam int user_id, @RequestBody FavoriteRequestDto dto) {
        try {
            favoriteService.addFavorite(dto, user_id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 즐겨찾기 삭제
    @DeleteMapping("/{item_id}")
    @Operation(summary = "즐겨찾기 삭제")
    public ResponseEntity<Void> removeFavorite(@PathVariable int item_id, @RequestParam int user_id) {
        try {
            favoriteService.removeFavorite(item_id, user_id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 즐겨찾기 목록 조회
    @GetMapping("/{user_id}")
    @Operation(summary = "즐겨찾기 목록 조회")
    public ResponseEntity<FavoriteListResponseDto> getFavorites(@PathVariable int user_id) {
        return ResponseEntity.ok(favoriteService.getFavorites(user_id));
    }
}
