package com.solvd.carina.demo.gui.components.zebrunner.header;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class HeaderMenu extends AbstractUIObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderMenu.class);
    @FindBy(xpath = "//a[@class='md-header-nav__button md-logo']")
    private ExtendedWebElement zebrunnerLogo;
    @FindBy(xpath="//div[@class='md-header-nav__ellipsis']/span[1]")
    private ExtendedWebElement carinaTextOnHeader;
    @FindBy(xpath="//form[@class='md-search__form']")
    private ExtendedWebElement searchComponentOnHeader;
    @FindBy(xpath = "//div[@class='md-header-nav__source']/a[@class='md-source']")
    private ExtendedWebElement gitHubLink;
    @FindBy(xpath="//header")
    private ExtendedWebElement header;
    @FindBy(xpath = "//div[@class='md-header-nav__ellipsis']/span[1]")
    private List<ExtendedWebElement> headerChildElements;

    public HeaderMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickOnZebRunnerLogo() {
        zebrunnerLogo.click();
    }

    public boolean isHeaderVisible() {
        return header.isVisible();
    }

    public boolean isZebRunnerLogoOnLeftSideOfHeader() {
        boolean isZebRunnerLogoPresent = zebrunnerLogo.isPresent();
        if (isZebRunnerLogoPresent) {
            int logoXPosition = zebrunnerLogo.getLocation().getX();
            int firstChildElementLocation = headerChildElements.get(0).getLocation().getX();
            return logoXPosition <= firstChildElementLocation;
        }
        return false;
    }

    public boolean isCarinaBrandPresentOnHeader() {
        LOGGER.info("Attempting to see if Carina text is on the header");
        return carinaTextOnHeader.getText().equals("Carina");
    }

    public boolean isGithubLinkIncludedOnHeader() {
        LOGGER.info("Attempting to see if github link is present on header");
        return gitHubLink.isPresent();
    }

    public void clickOnGithubLinkOnHeader() {
        gitHubLink.click();
    }

    public boolean isSearchComponentOnHeader() {
        LOGGER.info("Attempting to see if header contains search Component");
        return searchComponentOnHeader.isPresent();
    }

    public boolean isHeaderSticky() {
        LOGGER.info("Attempting to see if header is still on the top (is sticky) when we scrolled to the bottom");
        String cssValueOfPosition = header.getElement().getCssValue("position");
        return cssValueOfPosition.equalsIgnoreCase("sticky");
    }

}
