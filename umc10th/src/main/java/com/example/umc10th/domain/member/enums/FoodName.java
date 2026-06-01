package com.example.umc10th.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum FoodName {
    KOREAN("한식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    WESTERN("양식"),
    CHICKEN("치킨"),
    SNACK_BAR("분식"),
    MEAT("고기/구이"),
    LUNCH_BOX("도시락"),
    NIGHT_FOOD("야식"),
    FAST_FOOD("패스트푸드"),
    DESSERT("디저트"),
    ASIAN_FOOD("아시안푸드");

    private final String description;
}
