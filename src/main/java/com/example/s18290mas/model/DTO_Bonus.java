package com.example.s18290mas.model;

public class DTO_Bonus {


    private Long id;
    private float bonus_already;

    public Long getId() {
        return id;
    }

    public float getBonus_already() {
        return bonus_already;
    }

    public float getBonus_max() {
        return bonus_max;
    }

    public void setBonustoadd(float bonustoadd) {
        this.bonustoadd = bonustoadd;
    }

    public float getBonustoadd() {
        return bonustoadd;
    }

    private float bonus_max;

    private float bonustoadd;


    public DTO_Bonus(Long id, float bonus_already, float bonus_max, float bonustoadd){
        this.id =id;
        this.bonus_already = bonus_already;
        this.bonus_max = bonus_max;
        this.bonustoadd = bonustoadd;
    }
}
