package com.beymen.tests;

import org.junit.Test;

import com.beymen.pages.HomePage;

public class Tests extends BaseTest {

    @Test
    public void test_scenario_01() {
        HomePage homePage = new HomePage(driver);
        homePage.goToBeymen()
        .checkCorrectURL("https://www.beymen.com") //URL Kontrolü
        .acceptCookie() // Çerez politikası kabul işlemi
        .chooseGender() // Cinsiyet seçimi, parametrik yapılmadı. Yapılabilir.
        .writeValueToElementFromExcel("Sheet",0,0) // Excel'deki search kelimesini alıp aratma
        .clearSearch() // Search input temizliği
        .writeValueToElementFromExcel("Sheet", 0, 1) // Excel'deki search kelimesini alıp aratma
        .pressEnter() // Klavye'den enter'a basma işlemi
        .checkCorrectURL("https://www.beymen.com/tr/search?q=g%C3%B6mlek") // Search sonrası URL kontrolü
        .getProductFromList() // Search listten rastgele ürün seçilir.
        .getBrandName() // ürünün markası okunur ve txt dosyasına yazılır.
        .getProductName() // ürünün adı okunur ve txt dosyasına yazılır.
        .getProductPrice()  // ürünün fiyatı okunur ve txt dosyasına yazılır.
        .selectRandomVariant() // Varyant seçimi yapılır.
        .addToCart() // Ürün sepete eklenir.
        .goToCart() // Sepete gidilir.
        .compareProductPrice() // ürün detay ile sepet arasında fiyat kıyaslaması yapılır.
        .increaseQuantity(2) // ürünün qty değeri arttırılır.
        .deleteProductFromCart() // ürün sepetten silinir.
        .checkEmptyCart(); // empty state kontrolü yapılır.


    }
    
}
