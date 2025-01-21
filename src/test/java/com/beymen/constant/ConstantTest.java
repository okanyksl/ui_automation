package com.beymen.constant;

import org.openqa.selenium.By;

public class ConstantTest {
    public static final By ACCEPT_COOKIES = By.cssSelector("button[id='onetrust-accept-btn-handler']");
    public static final By GENDER_MEN = By.id("genderManButton");
    public static final By SEARCH_INPUT = By.cssSelector("div[class='o-header__search--wrapper'] > input");
    public static final By SEARCH_CLEAR_BUTTON = By.cssSelector("button[class='o-header__search--close -hasButton']");
    public static final By FOCUSED_SEARCH_INPUT = By.cssSelector("input[id='o-searchSuggestion__input']");
    public static final By PRODUCT_CARD = By.cssSelector("div[class='m-productCard__photo'] ");
    public static final By PRODUCT_BRAND = By.cssSelector("a[class='o-productDetail__brandLink']");
    public static final By PRODUCT_NAME = By.cssSelector("span[class='o-productDetail__description']");
    public static final By PRODUCT_PRICE = By.cssSelector("ins[id='priceNew']");
    public static final By VARIANT = By.xpath("//span[contains(@class, 'm-variation__item') and not(contains(@class, '-disabled'))]");
    public static final By ADD_TO_CART = By.cssSelector("button[id='addBasket']");
    public static final By NOTIFICATION = By.className("m-notification__close");
    public static final By GO_TO_CART = By.className("bwi-cart-o");
    public static final By CART_PRICE = By.cssSelector(".priceBox__salePrice");
    public static final By PRODUCT_QUANTITY = By.id("quantitySelect0-key-0");
    public static final By REMOVE_FROM_CART = By.cssSelector("button[id='removeCartItemBtn0-key-0']");
    public static final By EMPTY_STATE = By.cssSelector("div[id='emtyCart']");
}
