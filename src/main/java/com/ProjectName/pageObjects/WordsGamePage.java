package com.ProjectName.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WordsGamePage extends Page{

    @FindBy(className="ab_widget_container_google-form-modal_close_button")
    private WebElement modalCloseButton;

    @FindBy(css = "section.top-md.m-bottom-md  a")
    private List<WebElement> cardLinks;

    public boolean textIsReadable(){
        String textColor = ColorVerify(cardLinks.get(1),"color");
        System.out.println(textColor);

        return !textColor.equals("#ffffff");
    }



}
