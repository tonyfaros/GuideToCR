package com.example.anthony_pc.guidetocr.Class;

import android.graphics.Color;

/**
 * Created by mariapizarro on 6/11/18.
 */

public class ItemDic {
    private String letra;
    private String color;

    public ItemDic(String letra, String color) {
        this.letra = letra;
        this.color = color;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
