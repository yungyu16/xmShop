package com.xmlvhy.front.shop.crawler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JdProductCollector implements ProductCollector {
    public static final RuntimeException ex = new RuntimeException();
    private static final Supplier<RuntimeException> exThrower = () -> ex;
    private static final Pattern pattern = Pattern.compile("/n[\\d]/");
    private static final Pattern jdPattern = Pattern.compile("京东.*?\\s+");

    @SneakyThrows
    public static void main(String[] args) {
        String url = "https://search.jd.com/Search?keyword=" + URLEncoder.encode("java", "utf8");
        String body = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36")
                .execute().body();
        Document document = Jsoup.parse(body);
        System.out.println(document.html());
        Elements goodLists = document.select("ul.gl-warp");
        System.out.println(goodLists.html());
        System.out.println(goodLists.size());
    }

    private Optional<String> validateField(String field) {
        if (field == null) {
            return Optional.empty();
        }
        if (field.isEmpty()) {
            return Optional.empty();
        }
        field = field.trim();
        if (field.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(field);
    }

    @SneakyThrows
    @Override
    public List<ProductItem> collect(String keyword) {
        String url = "https://search.jd.com/Search?keyword=" + URLEncoder.encode(keyword, "utf8");
        String body = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36")
                .execute().body();
        Document document = Jsoup.parse(body);
        Elements goodList = document.select("ul.gl-warp");
        if (goodList.isEmpty()) {
            log.error("页面抓取失败...:{}", document.body().html());
            return Collections.emptyList();
        }
        Element gl = goodList.get(0);
        Elements lis = gl.select("li");
        return lis.stream()
                .map(li -> {
                    try {
                        String name = li.select(".p-name em").text();
                        String price = li.select(".p-price i").text();
                        String desc = li.select(".p-name .promo-words").text();
                        String img = li.select(".p-img img").get(0).attr("data-lazy-img");
                        name = validateField(name).orElseThrow(exThrower);
                        price = validateField(price).orElseThrow(exThrower);
                        desc = validateField(desc).orElseThrow(exThrower);
                        img = validateField(img).orElseThrow(exThrower);
                        if (!img.startsWith("http")) {
                            if (img.startsWith("//")) {
                                img = "http:" + img;
                            } else {
                                img = "http://" + img;
                            }
                        }
                        name = jdPattern.matcher(name).replaceAll("");
                        desc = jdPattern.matcher(desc).replaceAll("");
                        //高清图
                        img = pattern.matcher(img).replaceFirst("/cv/");
                        return ProductItem.valueOf(name, Double.valueOf(price), desc, img);
                    } catch (Exception e) {
                        if (e != ex) {
                            log.error("提取产品信息异常", e);
                        }
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
