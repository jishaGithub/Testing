package com.solvd.carina.demo.gui.components.zebrunner.search;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchComponentMenu extends AbstractUIObject {
    @FindBy(xpath="//form[@class='md-search__form']/label")
    private ExtendedWebElement searchIcon;
    @FindBy(xpath="//form[@class='md-search__form']/input")
    private ExtendedWebElement searchInputForm;
    @FindBy(xpath="//input[@placeholder='Search']")
    private ExtendedWebElement searchInputFormText;
    public SearchComponentMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isSearchComponentMadeOfSearchIconAndInputForm() {
        boolean isSearchIconPresent = searchIcon.isElementPresent();
        boolean isSearchInputFormPresent = searchInputForm.isElementPresent();
        return isSearchIconPresent && isSearchInputFormPresent;
    }
    public boolean isPlaceholderPresentInInputForm() {
        return searchInputFormText.isElementPresent();
    }

    public String getInputFormPlaceHolder() {
        return searchInputFormText.getAttribute("placeholder");
    }

}
