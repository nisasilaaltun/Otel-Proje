package com.hotelprject.hotelproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hotelprject.hotelproject.model.FAQ;

@Controller
public class FAQController {

    @GetMapping("/sss")
    public String showFAQPage(Model model) {
        List<FAQ> faqList = new ArrayList<>();

        // Örnek SSS'leri ekleyelim
        faqList.add(new FAQ("Otelin Check-in saati nedir?",
                "Check-in saati 14:00'ten sonra başlamaktadır. Erken check-in için lütfen resepsiyonla iletişime geçiniz."));

        faqList.add(new FAQ("Otele nasıl ulaşabilirim?",
                "Otelimize toplu taşıma ve özel araçla kolayca ulaşabilirsiniz. Detaylı yol tarifi için resepsiyonu arayabilirsiniz."));

        faqList.add(new FAQ("Ücretsiz Wi-Fi var mı?",
                "Evet, otelimizin tüm alanlarında ücretsiz yüksek hızlı Wi-Fi hizmeti sunulmaktadır."));

        faqList.add(new FAQ("Otopark hizmeti sunuluyor mu?",
                "Evet, otelimizde 24 saat güvenlikli, ücretsiz otopark hizmeti mevcuttur."));

        faqList.add(new FAQ("Kahvaltı dahil mi?",
                "Evet, tüm konaklama paketlerimize açık büfe kahvaltı dahildir. Kahvaltı saatleri: 07:00-10:00"));

        faqList.add(new FAQ("Evcil hayvan kabul ediliyor mu?",
                "Evet, küçük boy evcil hayvanlar için özel odalarımız mevcuttur. Rezervasyon sırasında belirtmeniz gerekmektedir."));

        model.addAttribute("faqs", faqList);
        return "sss";
    }
}