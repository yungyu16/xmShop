package com.xmlvhy.front.shop.crawler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JdProductCollector implements ProductCollector {
    private static RuntimeException ex = new RuntimeException();

    @SneakyThrows
    @Override
    public List<ProductItem> collect(String keyword) {
        String url = "https://search.jd.com/Search?keyword=" + URLEncoder.encode(keyword, "utf8");
        String body = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36")
                .execute().body();
        Document document = Jsoup.parse(body);
        Element gl = document.select("ul.gl-warp").get(0);
        Elements lis = gl.select("li");
        return lis.stream()
                .map(li -> {
                    try {
                        String name = li.select(".p-name em").text();
                        String price = li.select(".p-price i").text();
                        String desc = li.select(".p-name .promo-words").text();
                        String img = li.select(".p-img img").get(0).attr("data-lazy-img");
                        name = validateField(name).orElseThrow(() -> ex);
                        price = validateField(price).orElseThrow(() -> ex);
                        desc = validateField(desc).orElseThrow(() -> ex);
                        img = validateField(img).orElseThrow(() -> ex);
                        if (!img.startsWith("http")) {
                            if (img.startsWith("//")) {
                                img = "http:" + img;
                            } else {
                                img = "http://" + img;
                            }
                        }
                        return ProductItem.valueOf(name, price, desc, img);
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
}