package com.solvd.carina.demo.gui.pages.desktop.zebrunner;

import com.solvd.carina.demo.gui.components.zebrunner.header.HeaderMenu;
import com.solvd.carina.demo.gui.pages.common.ZebRunnerHomePageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ZebRunnerHomePage extends ZebRunnerHomePageBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZebRunnerHomePage.class);
    @FindBy(css = "header.md-header")
    private HeaderMenu header;
    @FindBy(xpath="//nav[@class='md-nav md-nav--primary']")
    private ZebRunnerNavigationMenu navigationMenu;
    @FindBy(xpath = "//a[@class='md-header-nav__button md-logo']")
    private ExtendedWebElement zebrunnerLogo;

    @FindBy(xpath = "//div[@class='md-header-nav__ellipsis']/span[1]")
    private ExtendedWebElement brandName;
    @FindBy(className = "md-search__form")
    private ExtendedWebElement searchElement;
    @FindBy(className = "md-footer-copyright__highlight")
    private ExtendedWebElement footerElement;
    @FindBy(xpath = "//div[@class='md-header-nav__ellipsis']/span[1]")
    private List<ExtendedWebElement> headerChildElements;
    @FindBy(xpath="//div[@class='md-content']/article/h1")
    private ExtendedWebElement mainBodyHeader;

    public ZebRunnerHomePage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(mainBodyHeader);
    }

    @Override
    public HeaderMenu getHeader() {
        return header;
    }

    @Override
    public ZebRunnerNavigationMenu getNavigationMenu() {
        return navigationMenu;
    }

    @Override
    public boolean isLogoOnLeftSideOfHeader() {
        ExtendedWebElement logo = getHeader().getZebrunnerLogo();
        int logoXPosition = logo.getLocation().getX();
        int firstChildElementLocation = headerChildElements.get(0).getLocation().getX();
        return logoXPosition <= firstChildElementLocation;
    }

    @Override
    public boolean isClickingOnLogoRedirectsToOverviewPage() {
        LOGGER.info("Attempting to see when Zebrunner Logo is clicked, does it redirect to overview page");
        return Objects.equals(mainBodyHeader.getText(), "Overview");
    }

    @Override
    public boolean isCarinaBrandPresentOnHeader() {
        LOGGER.info("Attempting to see if Carina text is on the header");
        ExtendedWebElement carinaBrandElement = getHeader().getCarinaBrand();
        return Objects.equals(carinaBrandElement.getText(), "Carina");
    }

    @Override
    public boolean isSearchComponentOnHeader() {
        LOGGER.info("Attempting to see if header contains search Component");
        ExtendedWebElement searchComponent = getHeader().getSearchComponentOnHeader();
        return searchComponent.isElementPresent();
    }

    @Override
    public boolean isLogoAndInputFormPresent() {
        LOGGER.info("Attempting to get search Icon");
        ExtendedWebElement searchIcon = getHeader().getSearchIcon();
        LOGGER.info("Attempting to get search Input form");
        ExtendedWebElement inputForm = getHeader().getSearchInputForm();
        LOGGER.info("Attempting to get the placeholder text in input form");
        String inputFormPlaceHolder = inputForm.getAttribute("placeholder");
        return searchIcon.isElementPresent() && inputForm.isElementPresent() && inputFormPlaceHolder.equals("Search");
    }

    @Override
    public boolean isSearchComponentIncludeIconAndSearchText() {
        LOGGER.info("Search component is made of icon and input with ‘Search’ text");
        return getHeader().isSearchComponentMadeOfSearchIconAndInputForm();
    }

    @Override
    public boolean isGithubLinkIncluded() {
        LOGGER.info("Attempting to see if github link is present on header");
        return getHeader().getGithubLink().isPresent();
    }

    @Override
    public String getCurrentPageURL() {
        return getDriver().getCurrentUrl();
    }

    @Override
    public boolean isHeaderVisible() {
        return getHeader().isHeaderVisible();
    }

    @Override
    public boolean scrollToBottom() {
        LOGGER.info("Attempting to scroll to the bottom of the home page");
        int randomSelector = new Random().nextInt(2);
        switch (randomSelector) {
            case 0:
                LOGGER.info("Scrolling using Actions Class");
                Actions action = new Actions(getDriver());
                action.moveToElement(footerElement.getElement()).perform();
                return true;
            case 1:
                LOGGER.info("Scrolling using Javascript");
                JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                javascriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
                return true;
        }
        return false;
    }

    @Override
    public boolean isHeaderSticky() {
        LOGGER.info("Attempting to see if header is still on the top (is sticky) when we scrolled to the bottom");
        String cssValueOfPosition = header.getRootExtendedElement().getElement().getCssValue("position");
        return cssValueOfPosition.equalsIgnoreCase("sticky");
    }
}