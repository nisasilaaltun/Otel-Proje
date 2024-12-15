-- Create tables
CREATE TABLE IF NOT EXISTS room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL(10, 2),
    available BOOLEAN,
    SIZE DECIMAL(2,0),
    ROOM_PICTURE_NAME VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS room_property (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    property VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS ROOM_ROOM_PROPERTIES (
    room_id BIGINT,
    room_properties_id BIGINT
);

CREATE TABLE IF NOT EXISTS hotel_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    user_role VARCHAR(255)
);

-- Insert rooms
INSERT INTO room (name, price, available, room_picture_name, SIZE) VALUES
    ('Tropik Oda', 3000.00, true, 'tropik.jpg', 4),
    ('Gökyüzü Odası', 1500.00, true, 'gokyuzu.jpg', 3),
    ('Overthink Odası', 1200.00, true, 'overthink.jpg', 2),
    ('Akvaryum Odası', 3800.00, true, 'akvaryum.jpg', 5),
    ('Vintage Odası', 3500.00, true, 'vintage.jpg', 4),
    ('Lüks Süit Oda', 4500.00, true, 'suit.jpg', 6),
    ('Kış Odası', 2800.00, true, 'winter.jpg', 4);

-- Insert room properties
INSERT INTO room_property(property) VALUES
    -- Tropik Oda (Tropical Room)
    ('Egzotik Dekorasyon: Tropik bitkiler, bambu mobilyalar, deniz kabukları ve sıcak renklerle tasarlanmış bir iç mekan.'),
    ('Hamak veya Salıncak: Rahatlama alanı olarak bir hamak veya tavan salıncağı kurulabilir.'),
    ('Manzara Temalı Duvar Kağıdı: Duvarlarda palmiye ağaçları, beyaz kumlu plajlar ve turkuaz denizi gösteren duvar kağıtları.'),
    ('Aromatik Koku ve Aydınlatma: Hindistan cevizi, vanilya veya tropik meyve kokuları ve loş bir ambiyans için LED aydınlatmalar.'),
    ('Spa Deneyimi: Jakuzili bir banyo, doğal taşlarla döşenmiş bir duş veya açık hava hissi veren bir küvet.'),
    ('Tropik Detaylar: Hasır şapka, tiki bar köşesi veya tropik desenli yatak örtüleri gibi tematik aksesuarlar.'),

    -- Gökyüzü Odası (Sky Room)
    ('Panoramik Cam Tavan: Odayı tamamen cam bir tavanla donatarak gerçek gökyüzünü izleme imkanı sağlar.'),
    ('Yıldız Projektörleri: Bulutlu günlerde veya geceyi daha da büyüleyici hale getirmek için yıldız projektörleriyle suni bir gökyüzü efekti ekleyin.'),
    ('Dinlendirici Sesler: Hafif rüzgar sesi, kuş cıvıltıları veya hafif gök gürültüsü gibi doğa sesleri sunan bir ses sistemi.'),
    ('Rahatlama Alanı: Puf koltuklar veya bulut temalı minderlerle, uçuyormuş gibi hissettiren bir oturma köşesi.'),
    ('Gökyüzü Aksesuarları: Bulut şeklinde avizeler, uçan balon figürleri veya güneş ve ay temalı detaylar.'),

    -- Overthink Odası (Overthink Room)
    ('Doğal Malzemeler: Odaya sıcaklık katmak için ahşap ve doğal taş gibi organik malzemeler kullanılmıştır.'),
    ('Boydan Boya Kitaplık: Duvarlar, tavana kadar uzanan ahşap kitaplıklarla kaplı, yüzlerce kitabı düzenli bir şekilde sergileyen bir kütüphane hissi verir.'),
    ('Okuma Köşesi: Yumuşacık bir şezlong, puf minderler ve battaniyelerle donatılmış, huzurlu bir okuma alanı yer alır.'),
    ('Ayarlanabilir Aydınlatma: Gece okuma için kitap dostu LED ışıklar ve vintage masa lambaları, göz yormayan bir ışık sağlar.'),
    ('Doğal Dokular: Ahşap mobilyalar, kitap temalı halılar ve minimalist dekorasyon ile doğal ve sıcak bir atmosfer yaratılmıştır.'),

    -- Akvaryum Odası (Aquarium Room)
    ('Cam Tavan ve Duvarlar: Deniz yaşamını izlemek için tam panoramik bir görüş sunan cam tavan ve duvarlar.'),
    ('Doğal Deniz Manzarası: Akvaryumun içindeki canlı balıklar, mercanlar ve diğer deniz canlıları ile çevrili bir ortam.'),
    ('Ambiyans Aydınlatma: Renk değiştiren LED ışıklarla su altı atmosferini tamamlayan yumuşak aydınlatma.'),
    ('Lüks Yatak Alanı: Deniz manzarasına bakan konforlu bir kral boy yatak.'),

    -- Vintage Odası (Vintage Room)
    ('Eski Tip Aydınlatma: Kristal avizeler, sarkıt lambalar veya eski tarz masa lambaları, vintage bir ambiyans yaratacak şekilde odanın her köşesine yerleştirilmiş.'),
    ('Klasik Halılar ve Kilimler: El dokuması eski halılar veya vintage tarzda desenli kilimler, odanın zeminine nostaljik bir dokunuş katıyor.'),
    ('Eski Metal Çerçeveli Yatak: Metal bir yatak çerçevesi, vintage tarzını destekleyen romantik ve klasik bir detay oluşturuyor.'),
    ('Vintage Duvar Lambaları: Eski tarz duvar lambaları, odanın farklı köşelerine nostaljik bir ışıltı katıyor.'),

    -- Lüks Süit / Süit Oda (Luxury Suite / Suite Room)
    ('Geniş Alan: Lüks süit, geniş bir alana sahiptir ve konforlu bir şekilde tasarlanmıştır.'),
    ('Yatak Alanı: Süit, kral boy bir yatak ile donatılmıştır.'),
    ('Lüks Mobilyalar: Şık, zarif ve rahat mobilyalar ile oda dekore edilmiştir.'),
    ('Özel Oturma Alanı: Rahat oturma köşeleri ve konforlu koltuklarla tasarlanmış bir alan mevcuttur.'),
    ('Banyoda Jakuzi: Özel bir jakuzisi ve rahatlatıcı duş alanı bulunur.'),

    -- Kış Odası (Winter Room)
    ('Ahşap Dekorasyon: Ahşap mobilyalar ve sıcak renklerle dekore edilmiş bir iç mekan yaratır.'),
    ('Somine: Odada sıcak bir atmosfer yaratacak şekilde bir şömine bulunur.'),
    ('Sıcak Aydınlatma: Loş ışıklar ve sıcak tonlar odanın huzur verici bir ortam sağlamasına yardımcı olur.'),
    ('Kış Temalı Aksesuarlar: Yastıklar, battaniyeler ve halılar gibi kış temalı aksesuarlar odanın atmosferini tamamlar.');

-- Insert room-room properties relations
INSERT INTO room_room_properties(room_id, room_properties_id) VALUES
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), -- Tropik Oda
    (2, 7), (2, 8), (2, 9), (2, 10), (2, 11),
     (3, 12), (3, 13), (3, 14), (3, 15), (3, 16),
      (4, 17), (4, 18),  (4, 19), (4, 20),
      (5, 21), (5, 22), (5, 23), (5, 24),
    (6, 25), (6, 26), (6, 27), (6, 28), (6, 29),
    (7, 30),  (7, 31), (7, 32), (7, 33);
INSERT INTO hotel_user (name, email, password, user_role) VALUES
                                                   ('nisasilaaltun', 'nisasilaaltun@test.com', '1234', 'CLIENT'),
                                                    ('sinemnur', 'sinem@gmail.com', '123', 'CLIENT'),
                                                   ('test', 'test@test.com', '1234', 'ADMIN');