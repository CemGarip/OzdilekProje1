package com.testinium.ozdilek;

import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import java.util.List;
import java.util.Random;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;

public class BasePage extends BaseTest {

    final static Logger logger = Logger.getLogger(BasePage.class.getName());

    @Step("id<id> li elemente tikla")
    public void clickId(String id){
        appiumDriver.findElement(By.id(id)).getText();
        appiumDriver.findElement(By.id(id)).click();
        logger.info(id +"tiklandi");

    }

    @Step("<wait> saniye bekle")
    public void waitForsecond(int wait) throws InterruptedException {
        logger.info(wait+ " saniye bekleniyor");
        Thread.sleep(1000*wait);
    }

    @Step("xPath<xpathClick> li elemente tikla")
    public void clickXpath(String xPathClick){
        String text = appiumDriver.findElement(By.xpath(xPathClick)).getText();
        logger.info( text + " butonuna tiklanıyor");
        appiumDriver.findElement(By.xpath(xPathClick)).click();
        logger.info(text + " butonuna tiklandı");
    }

    @Step("id<key> li elementin gorundugunu kontrol et")
    public void isDisplayed(String key){
        String text = appiumDriver.findElement(By.id(key)).getText();
        boolean display = appiumDriver.findElement(By.id(key)).isDisplayed();
        if (display){
            logger.info(text + " elementi gorunuyor mu ? " + display);
        }else{
            logger.info(text + " elementi gorunuyor mu ? " + display);
        }
    }

    @Step("Ekrani <times> defa kaydir ve <wait> saniye bekle")
    public void scroll(int times ,int wait) throws InterruptedException {
        logger.info("Ekran " +times+ " defa asagi kaydiriliyor ");
        for (int i = 0; i < times; i++) {
            TouchAction scroll = new TouchAction(appiumDriver)
                    .press(PointOption.point(530, 1900))
                    .waitAction(waitOptions(ofMillis(800)))
                    .moveTo(PointOption.point(530, 350))
                    .release()
                    .perform();
            Thread.sleep(1000*wait);
        }
        logger.info("Ekran "+times + " defa asagi kaydırıldı ");
    }
    public List<MobileElement> listOfRandomProducts(){
        return appiumDriver.findElements(By.xpath
                ("//*[@resource-id='com.ozdilek.ozdilekteyim:id/imageView']"));
    }

    @Step("Rastgele urun sec ve <wait> saniye bekle")
    public void randomClick(int wait) throws InterruptedException {
        logger.info("Rastgele bir urun seciliyor");
        Random random = new Random();
        listOfRandomProducts().get(random.nextInt(listOfRandomProducts().size())).click();;
        Thread.sleep(1000*wait);
        logger.info("Rastgele bir urun secildi");
    }

    @Step("id<id> li alanini e-posta bilgisiyle id<id2> li alani sifre bilgisiyle doldur")
    public void login(String mail, String psw){
        String text = appiumDriver.findElement(By.id(mail)).getText();
        logger.info(text + " alanina e-mail bilgileri giriliyor");
        MobileElement element = appiumDriver.findElement(By.id(mail));
        element.sendKeys("testinium.ozdilek@gmail.com");
        logger.info(text + " alanina e-mail bilgileri girildi");
        String text2 = appiumDriver.findElement(By.id(psw)).getText();
        logger.info(text2 + " alanina sifre bilgileri giriliyor");
        MobileElement element2 = appiumDriver.findElement(By.id(psw));
        element2.sendKeys("12345Cem");
        logger.info(text2 + " alanina sifre bilgileri yazildi");
    }
    public List<MobileElement> listOfRandomSize() {
        return appiumDriver.findElements(By.xpath
                ("//*[@resource-id='com.ozdilek.ozdilekteyim:id/tvInSizeItem']"));
    }

    @Step("Rastgele beden sec ve <wait> saniye bekle")
    public void randomSizeClick(int wait) throws InterruptedException {
        logger.info("Rastgele beden seciliyor");
        Random random = new Random();
        MobileElement element = listOfRandomSize().get(random.nextInt(listOfRandomSize().size()));
        Thread.sleep(1000*wait);
        if (element.isEnabled()) {
            logger.info("Rastgele beden secildi");
            element.click();
        } else {
            logger.info("Secilen beden stokta yoktur");
        }
        logger.info("Rastgele bir urun secildi");
    }

}
