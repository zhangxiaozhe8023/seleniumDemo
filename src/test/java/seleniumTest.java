import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

 import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class seleniumTest {


    /**
     * Selenium提供了8种定位方式。
     *
     * id
     * name
     * class name
     * tag name
     * link text
     * partial link text
     * xpath
     * css selector
     * 这8种定位方式在java selenium中所对应的方法为：
     *
     * findElement(By.id())
     * findElement(By.name())
     * findElement(By.className())
     * findElement(By.tagName())
     * findElement(By.linkText())
     * findElement(By.partialLinkText())
     * findElement(By.xpath())
     * findElement(By.cssSelector())
     *
     * <a class="mnav" href="http://news.baidu.com" name="tj_trnews">新闻</a>
     * <a class="mnav" href="http://www.hao123.com" name="tj_trhao123">hao123</a>
     * 通过link text定位:
     *driver.findElement(By.linkText("新闻")
     * driver.findElement(By.linkText("hao123")
     * 通过link text定位:
     * driver.findElement(By.partialLinkText("新")
     * driver.findElement(By.partialLinkText("hao")
     * driver.findElement(By.partialLinkText("123")
     * @throws InterruptedException
     */

    /**
     * 控制浏览器窗口大小
     * 有时候我们希望能以某种浏览器尺寸找开，访问的页面在这种尺寸下运行。例如可以将浏览器设置成移动端大小(480* 800)，然后访问移动站点，对其样式进行评估；WebDriver 提供了 manage().window().setSize()方法来设置浏览器的大小。
     *
     * maximize() 设置浏览器最大化
     *
     * setSize() 设置浏览器宽高
     *
     * 控制浏览器后退、前进
     * 在使用浏览器浏览网页时，浏览器提供了后退和前进按钮，可以方便地在浏览过的网页之间切换，WebDriver也提供了对应的back()和forward()方法来模拟后退和前进按钮。下面通过例子来演示这两个方法的使用。
     *
     * back() 模拟浏览器后退按钮
     *
     * forward() 模拟浏览器前进按钮
     * @throws InterruptedException
     */
    @Test
    public void BrowserK() throws InterruptedException {
        //控制浏览器窗口大小
        WebDriver driver= new ChromeDriver();
        driver.get("https://www.baidu.cn");

        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.get("https://m.baidu.cn");
        driver.manage().window().setSize(new Dimension(480, 800));
        Thread.sleep(2000);

        //控制浏览器前进后退
        //get 到百度首页
        driver.get("https://www.baidu.com/");
        System.out.printf("now accesss %s \n", driver.getCurrentUrl());
        Thread.sleep(2000);

        //点击“新闻” 链接
        driver.findElement(By.linkText("新闻")).click();
        System.out.printf("now accesss %s \n", driver.getCurrentUrl());
        Thread.sleep(2000);

        //执行浏览器后退
        driver.navigate().back();
        System.out.printf("back to %s \n", driver.getCurrentUrl());
        Thread.sleep(2000);

        //执行浏览器前面
        driver.navigate().forward();
        System.out.printf("forward to %s \n", driver.getCurrentUrl());
        Thread.sleep(2000);

        //刷新页面
        driver.navigate().refresh();
        Thread.sleep(5000);

        driver.quit();
    }

    /**
     * WebDriver 常用方法
     * 下面先来认识 WebDriver 中最常用的几个方法：
     *
     * clear() 清除文本。
     *
     * sendKeys(*value) 模拟按键输入。
     *
     * click() 单击元素
     *
     *
     * 其它常用方法
     * submit()
     * submit()方法用于提交表单。 例如，在搜索框输入关键字之后的“回车” 操作， 就可以通过 submit()方法模拟.
     * @throws InterruptedException
     */
    @Test
    public void baiduDemo() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        //模拟输入清空点击事件
        driver.get("https://www.baidu.com/");
//        WebElement search_text = driver.findElement(By.id("kw"));
//        WebElement search_button = driver.findElement(By.id("su"));
//
//        search_text.sendKeys("Java");
//        search_text.clear();
//        search_text.sendKeys("Selenium");
//        search_button.click();
       //模拟提交
//        search_text.sendKeys("Selenium");
//        search_text.submit();
        //获得百度输入框的尺寸
        WebElement size = driver.findElement(By.id("kw"));
        System.out.println(size.getSize());

        //返回百度页面底部备案信息
        WebElement text = driver.findElement(By.id("cp"));
        System.out.println(text.getText());

        //返回元素的属性值， 可以是 id、 name、 type 或元素拥有的其它任意属性
        WebElement ty = driver.findElement(By.id("kw"));
        System.out.println(ty.getAttribute("autocomplete"));

        //返回元素的结果是否可见， 返回结果为 True 或 False
        WebElement display = driver.findElement(By.id("kw"));
        System.out.println(display.isDisplayed());

        driver.quit();
    }

    /**
     * Actions 类提供了鼠标操作的常用方法：
     *
     * contextClick() 右击
     *
     * clickAndHold() 鼠标点击并控制
     *
     * doubleClick() 双击
     *
     * dragAndDrop() 拖动
     *
     * release() 释放鼠标
     *
     * perform() 执行所有Actions中存储的行为
     */
    @Test
    public void shubiao() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com/");

        WebElement search_setting = driver.findElement(By.linkText("设置"));
        Actions action = new Actions(driver);
        action.clickAndHold(search_setting).perform();
        Thread.sleep(8000);
//        导入提供鼠标操作的 ActionChains 类
//
//        Actions(driver) 调用Actions()类，将浏览器驱动driver作为参数传入。
//
//        clickAndHold() 方法用于模拟鼠标悬停操作， 在调用时需要指定元素定位。
//
//        perform() 执行所有ActionChains中存储的行为， 可以理解成是对整个操作的提交动作。




// 鼠标右键点击指定的元素
        action.contextClick(driver.findElement(By.id("element"))).perform();

// 鼠标右键点击指定的元素
        action.doubleClick(driver.findElement(By.id("element"))).perform();

// 鼠标拖拽动作， 将 source 元素拖放到 target 元素的位置。
        WebElement source = driver.findElement(By.name("element"));
        WebElement target = driver.findElement(By.name("element"));
        action.dragAndDrop(source,target).perform();

// 释放鼠标
        action.release().perform();
        driver.quit();
    }

    /**
     * Keys()类提供了键盘上几乎所有按键的方法。 前面了解到， sendKeys()方法可以用来模拟键盘输入， 除此之 外， 我们还可以用它来输入键盘上的按键， 甚至是组合键， 如 Ctrl+A、 Ctrl+C 等。
     * @throws InterruptedException
     */
    @Test
    public void Keyboard() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");

        WebElement input = driver.findElement(By.id("kw"));

        //输入框输入内容
        input.sendKeys("seleniumm");
        Thread.sleep(2000);

        //删除多输入的一个 m
        input.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(2000);

        //输入空格键+“教程”
        input.sendKeys(Keys.SPACE);
        input.sendKeys("教程");
        Thread.sleep(2000);

        //ctrl+a 全选输入框内容
        input.sendKeys(Keys.CONTROL,"a");
        Thread.sleep(2000);

        //ctrl+x 剪切输入框内容
        input.sendKeys(Keys.CONTROL,"x");
        Thread.sleep(2000);

        //ctrl+v 粘贴内容到输入框
        input.sendKeys(Keys.CONTROL,"v");
        Thread.sleep(2000);

        //通过回车键盘来代替点击操作
        input.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        driver.quit();
//        sendKeys(Keys.BACK_SPACE) 回格键（BackSpace）
//
//        sendKeys(Keys.SPACE) 空格键(Space)
//
//        sendKeys(Keys.TAB) 制表键(Tab)
//
//        sendKeys(Keys.ESCAPE) 回退键（Esc）
//
//        sendKeys(Keys.ENTER) 回车键（Enter）
//
//        sendKeys(Keys.CONTROL,‘a’) 全选（Ctrl+A）
//
//        sendKeys(Keys.CONTROL,‘c’) 复制（Ctrl+C）
//
//        sendKeys(Keys.CONTROL,‘x’) 剪切（Ctrl+X）
//
//        sendKeys(Keys.CONTROL,‘v’) 粘贴（Ctrl+V）
//
//        sendKeys(Keys.F1) 键盘 F1
//
//……
//
//        sendKeys(Keys.F12) 键盘 F12
    }

    /**
     * 获取断言信息
     * getTitle()： 用于获得当前页面的title。
     *
     * getCurrentUrl() ： 用户获得当前页面的URL。
     *
     * getText() 获取页面文本信息。
     * @throws InterruptedException
     */
    @Test
    public void dd2() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");

        System.out.println("Search before================");

        //获取当前的 title 和 url
        System.out.printf("title of current page is %s\n", driver.getTitle());
        System.out.printf("url of current page is %s\n", driver.getCurrentUrl());

        //百度搜索
        WebElement search = driver.findElement(By.id("kw"));
        search.sendKeys("Selenium");
        search.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        System.out.println("Search after================");

        //获取当前的 title 和 url
        System.out.printf("title of current page is %s\n", driver.getTitle());
        System.out.printf("url of current page is %s\n", driver.getCurrentUrl());

        //获取第一条搜索结果的标题
        WebElement result = driver.findElement(By.xpath("//div[@id='content_left']/div/h3/a"));
        System.out.println(result.getText());
        Thread.sleep(5000);
        driver.quit();
    }

    /**
     * 显式等待
     * WebDriverWait(driver, 10, 1)
     *
     * driver： 浏览器驱动。 10： 最长超时时间， 默认以秒为单位。 1： 检测的的间隔（步长） 时间， 默认为 0.5s。
     * @throws InterruptedException
     */
    @Test
    public void dd3() throws InterruptedException{
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");

        //显式等待， 针对某个元素等待
        WebDriverWait wait = new WebDriverWait(driver,10,1);

        wait.until(new ExpectedCondition<WebElement>(){
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.id("kw"));
            }
        }).sendKeys("selenium");

        driver.findElement(By.id("su")).click();
        Thread.sleep(8000);

        driver.quit();
    }

    /**
     * 隐式等待
     *
     * implicitlyWait。识别对象时的超时时间。过了这个时间如果对象还没找到的话就会抛出NoSuchElement异常。
     *
     * setScriptTimeout。异步脚本的超时时间。WebDriver可以异步执行脚本，这个是设置异步执行脚本脚本返回结果的超时时间。
     *
     * pageLoadTimeout。页面加载时的超时时间。因为WebDriver会等页面加载完毕再进行后面的操作，所以如果页面超过设置时间依然没有加载完成，那么WebDriver就会抛出异常。
     */
    @Test
    public void timeout2(){
        WebDriver driver = new ChromeDriver();

        //页面加载超时时间设置为 5s
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.get("https://www.baidu.com/");

        //定位对象时给 10s 的时间, 如果 10s 内还定位不到则抛出异常
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("kw")).sendKeys("selenium");

        //异步脚本的超时时间设置成 3s
        driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);

    }

    /**
     * 定位一组元素
     * 定位一组元素的方法与定位单个元素的方法类似，唯一的区别是在单词 findElement 后面多了一个 s 表示复数。
     * @throws InterruptedException
     */
    @Test
    public void ElementsDemo() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com/");

        WebElement search_text = driver.findElement(By.id("kw"));
        search_text.sendKeys("selenium");
        search_text.submit();
        Thread.sleep(2000);

        //匹配第一页搜索结果的标题， 循环打印
        List<WebElement> search_result = driver.findElements(By.xpath("//div/div/h3"));

        //打印元素的个数
        System.out.println(search_result.size());

        // 循环打印搜索结果的标题
        for(WebElement result : search_result){
            System.out.println(result.getText());
        }

        System.out.println("-------我是分割线---------");

        //打印第n结果的标题
        WebElement text = search_result.get(search_result.size() - 10);
        System.out.println(text.getText());
        Thread.sleep(10000);
        driver.quit();
    }

    /**
     * 多窗口切换
     * getWindowHandle()： 获得当前窗口句柄。
     *
     * getWindowHandles()： 返回的所有窗口的句柄到当前会话。
     *
     * switchTo().window()： 用于切换到相应的窗口，与上一节的switchTo().frame()类似，前者用于不同窗口的切换， 后者用于不同表单之间的切换。
     * @throws InterruptedException
     */
    @Test
    public void registerbaidu() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");

        //获得当前窗口句柄
        String search_handle = driver.getWindowHandle();

        //打开百度注册窗口
        driver.findElement(By.linkText("登录")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("立即注册")).click();

        //获得所有窗口句柄
        Set<String> handles = driver.getWindowHandles();

        //判断是否为注册窗口， 并操作注册窗口上的元素
        for(String handle : handles){
            if (handle.equals(search_handle)==false){
                //切换到注册页面
                driver.switchTo().window(handle);
                System.out.println("now register window!");
                Thread.sleep(2000);
                driver.findElement(By.name("userName")).clear();
                driver.findElement(By.name("userName")).sendKeys("user name");
                driver.findElement(By.name("phone")).clear();
                driver.findElement(By.name("phone")).sendKeys("phone number");
                //......
                Thread.sleep(2000);
                //关闭当前窗口
                driver.close();
            }
        }
        Thread.sleep(2000);

        driver.quit();
    }

    /**下拉框选择
     * Select类用于定位select标签。 selectByValue()方法符用于选取<option>标签的value值。
     * @throws InterruptedException
     */
    @Test
    public void xiala() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");

        driver.findElement(By.linkText("设置")).click();
        driver.findElement(By.linkText("搜索设置")).click();
        Thread.sleep(2000);

        //<select>标签的下拉框选择
        WebElement el = driver.findElement(By.xpath("//select"));
        Select sel = new Select(el);
        sel.selectByValue("50");
        Thread.sleep(10000);

        driver.quit();
    }

    /**
     * 在 WebDriver中处理JavaScript所生成的alert、confirm以及prompt十分简单，具体做法是使用switch_to_alert()方法定位到alert/confirm/prompt，然后使用text/accept/dismiss/sendKeys等方法进行操作。
     *
     * getText()： 返回 alert/confirm/prompt 中的文字信息。
     *
     * accept()： 接受现有警告框。
     *
     * dismiss()： 解散现有警告框。
     *
     * sendKeys(keysToSend)： 发送文本至警告框。
     *
     * keysToSend： 将文本发送至警告框。
     *
     * 如下图，百度搜索设置弹出的窗口是不能通过前端工具对其进行定位的，这个时候就可以通过switchTo().alert()方法接受这个弹窗。
     * @throws InterruptedException
     */
    @Test
    public void AlertDemo90() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");

        driver.findElement(By.linkText("设置")).click();
        driver.findElement(By.linkText("搜索设置")).click();
        Thread.sleep(2000);

        //保存设置
        driver.findElement(By.className("prefpanelgo")).click();

        //接收弹窗
//        driver.switchTo().alert().accept();
        driver.switchTo().alert().getText();
        System.out.println( driver.switchTo().alert().getText());
        Thread.sleep(8000);

        driver.quit();

    }

    /**
     * 有时候我们需要验证浏览器中Cookie是否正确， 因为基于真实Cookie的测试是无法通过白盒测试和集成测试进行的。WebDriver提供了操作Cookie的相关方法可以读取、 添加和删除Cookie信息。
     *
     * WebDriver 操作Cookie的方法：
     *
     * getCookies() 获得所有 cookie 信息。
     *
     * getCookieNamed(String name) 返回字典的key为“name”的Cookie信息。
     *
     * addCookie(cookie dict) 添加Cookie。“cookie_dict”指字典对象，必须有 name和value值。
     *
     * deleteCookieNamed(String name) 删除Cookie 信息。 “name”是要删除的 cookie的名称； “optionsString” 是该Cookie的选项，目前支持的选项包括“路径” ， “域” 。
     *
     * deleteAllCookies() 删除所有 cookie 信息。
     *
     * 下面通过 geCookies()来获取当前浏览器的 cookie 信息。
     */
    @Test
    public void cookieDemo(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");

        Cookie c1 = new Cookie("name", "key-aaaaaaa");
        Cookie c2 = new Cookie("value", "value-bbbbbb");
        driver.manage().addCookie(c1);
        driver.manage().addCookie(c2);

        //获得 cookie
        Set<Cookie> coo = driver.manage().getCookies();
        Cookie name = driver.manage().getCookieNamed("name");


        System.out.println(coo);
        System.out.println(name);

        //删除所有 cookie
        //driver.manage().deleteAllCookies();

        driver.quit();
    }

    /**
     *获取截图
     */
    @Test
    public void getIma(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com");

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile,new File("/Users/haal/Downloads/手机端截图/a.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
