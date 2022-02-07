//강경민
package com.example.webkiosk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "category_categoryId") // "articleid" 컬럼에 Article의 대표값을 저장
    private Category categoryId;

    @Column(length = 30)
    private String productName;

    @Column
    private Integer productPrice;

    @Column(length = 255)
    private String productInfo;

    @Column(length = 50)
    private String productImage;
}
