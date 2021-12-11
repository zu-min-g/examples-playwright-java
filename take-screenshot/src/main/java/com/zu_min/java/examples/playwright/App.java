package com.zu_min.java.examples.playwright;

import java.nio.file.Paths;
import java.util.List;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class App {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      List<BrowserType> browserTypes =
          List.of(playwright.chromium(), playwright.webkit(), playwright.firefox());

      for (BrowserType browserType : browserTypes) {
        try (Browser browser = browserType.launch();
            BrowserContext context = browser.newContext();
            Page page = context.newPage()) {

          page.navigate("https://www.zu-min.com/");
          page.screenshot(new Page.ScreenshotOptions().setFullPage(true)
              .setPath(Paths.get("screenshot-" + browserType.name() + ".png")));
        }
      }
    }
  }
}
