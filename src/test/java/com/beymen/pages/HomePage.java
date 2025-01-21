package com.beymen.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.beymen.utils.Excel;
import com.beymen.utils.Log;
import com.beymen.utils.TxtResponse;

import static com.beymen.constant.ConstantTest.*;

import java.io.IOException;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final String baseURL = "https://www.beymen.com";
    private String productDetailPrice;

    public HomePage goToBeymen() {
        Log.info("Opening Beymen Website.");
        driver.get(baseURL);
        waitForLoad(driver);
        return this;
    }

    public HomePage checkCorrectURL(String baseUri) {
        String currentURL = driver.getCurrentUrl();
    
        if (!currentURL.startsWith(baseUri)) {
            Assert.fail(baseUri + " gelmesi beklenirken gelen url : " + currentURL);
        }
        Log.info("Correct URL loaded: " + currentURL);
        return this;
    }

    public HomePage acceptCookie() {
        click(ACCEPT_COOKIES);
        return this;
    }

    public HomePage chooseGender() {
        click(GENDER_MEN);
        return this;
    }

    public HomePage writeValueToElementFromExcel(String sheetName, int row, int cell) {
        try {
            // Excel dosyasını ve sayfasını yükle
            Excel.setExcelFileSheet(sheetName);
    
            // Belirtilen hücreden veri al
            String getValue = Excel.getCellData(row, cell);
            
            // Değeri input alanına yaz
            if (!isElementVisible(FOCUSED_SEARCH_INPUT)) {
                click(SEARCH_INPUT);
            }
            setText(FOCUSED_SEARCH_INPUT, getValue);
            
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load Excel file or sheet: " + sheetName);
        }
        return this;
    }

    public HomePage clearSearch() {
        click(SEARCH_CLEAR_BUTTON);
        return this;
    }

    public HomePage pressEnter(){
        keyEnter(SEARCH_INPUT);
        return this;
    }

    public HomePage getProductFromList(){
        List<WebElement> elementList = driver.findElements(PRODUCT_CARD);
        int randomIndex = new Random().nextInt(elementList.size());
        WebElement selectedElement = elementList.get(randomIndex);
        selectedElement.click();
        return this;
    }

    public HomePage selectRandomVariant(){
        List<WebElement> elements = driver.findElements(VARIANT);
        int randomIndex = new Random().nextInt(elements.size());
        WebElement selectedElement = elements.get(randomIndex);
        selectedElement.click();

        return this;
    }

    public HomePage getBrandName() {
        // Elementleri bul
        List<WebElement> elements = driver.findElements(PRODUCT_BRAND);
    
        if (!elements.isEmpty()) {
            // İlk elemanın metnini al
            String textValue = elements.get(0).getText();
            // Txt dosyasına yaz
            TxtResponse.writeToTxt(textValue);
            Log.info(PRODUCT_BRAND + " elementinde bulunan '" + textValue + "' değeri txt dosyasına yazıldı.");
        } else {
            // Eğer element bulunamazsa
            Log.error("Belirtilen key'e sahip element bulunamadı: " + PRODUCT_BRAND);
        }
        return this;
    }

    public HomePage getProductName() {
        // Elementleri bul
        List<WebElement> elements = driver.findElements(PRODUCT_NAME);
    
        if (!elements.isEmpty()) {
            // İlk elemanın metnini al
            String textValue = elements.get(0).getText();
            // Txt dosyasına yaz
            TxtResponse.writeToTxt(textValue);
            Log.info(PRODUCT_NAME + " elementinde bulunan '" + textValue + "' değeri txt dosyasına yazıldı.");
        } else {
            // Eğer element bulunamazsa
            Log.error("Belirtilen key'e sahip element bulunamadı: " + PRODUCT_NAME);
        }
        return this;
    }

    private String formatPrice(String priceText) {
        // Boşlukları ve "TL" gibi birimleri kaldır
        priceText = priceText.replaceAll("\\s+|TL", "");
        // Virgülden sonraki kısmı kaldır (örneğin kuruşları atlamak için)
        priceText = priceText.replaceAll(",.*", "");
        return priceText;
    }

    public HomePage getProductPrice(){
        List<WebElement> elements = driver.findElements(PRODUCT_PRICE);
        if (!elements.isEmpty()) {
        // İlk ürün fiyatını al
        String productPriceText = elements.get(0).getText().trim();
    
        // Fiyatı temizle ve biçimlendir
        String productPrice = formatPrice(productPriceText);
            TxtResponse.writeToTxt(productPrice);
            Log.info(PRODUCT_PRICE + " elementinde bulunan '" + productPrice + "' değeri txt dosyasına yazıldı.");
            productDetailPrice = productPrice;
        } else {
            Log.error("Belirtilen key'e sahip element bulunamadı: " + PRODUCT_PRICE);
        }
        return this;
    }

    public HomePage addToCart(){
        click(ADD_TO_CART);
        click(NOTIFICATION);
        return this;
    }

public HomePage goToCart() {
    click(GO_TO_CART);
    return this;
}

    public HomePage compareProductPrice() {
        // Sepetteki fiyat görünür olana kadar bekle
        waitVisibility(CART_PRICE);
    
        // Ürün fiyatını bul ve kontrol et
        List<WebElement> elements = driver.findElements(CART_PRICE);
        if (elements.isEmpty()) {
            throw new RuntimeException("Ürün fiyatı bulunamadı.");
        }
    
        // İlk ürün fiyatını al
        String cartPriceText = elements.get(0).getText().trim();
    
        // Fiyatı temizle ve biçimlendir
        String cartPrice = formatPrice(cartPriceText);
    
        // Ürün detayındaki fiyat ile karşılaştır
        System.out.println("Ürün Detay Fiyatı: " + productDetailPrice);
        System.out.println("Sepet Fiyatı: " + cartPrice);
        Assert.assertEquals("Fiyatlar eşleşmiyor!", productDetailPrice, cartPrice);
    
        return this;
    }
    
    public HomePage increaseQuantity(int quantity){
    WebElement dropdown = driver.findElement(PRODUCT_QUANTITY);
    Select select = new Select(dropdown);
    List<WebElement> options = select.getOptions();

    boolean valueFound = false;
    for (WebElement option : options) {
        if (option.getText().trim().equals(quantity + " adet")) {
            select.selectByVisibleText(quantity + " adet");
            Log.info(quantity + " adet seçildi.");
            valueFound = true;
            break;
        }
    }
    if (!valueFound) {
        Log.info(quantity + " adet değeri bulunamadı.");
    }
        return this;
    }

    public HomePage deleteProductFromCart(){
        click(REMOVE_FROM_CART);
        return this;
    }

    public HomePage checkEmptyCart() {
        waitVisibility(EMPTY_STATE);
        return this;
    }
}
