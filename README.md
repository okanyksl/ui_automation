# Beymen Web Otomasyon Testi

## 📁 Proje Yapısı 

2. Maven bağımlılıklarını yükleyin:
```bash
mvn clean install
```

3. Test verilerini hazırlayın:
   - `src/test/resources/Data.xlsx` dosyasında search için kullanılan datalar yer almaktadır.

4. Testleri çalıştırın:
```bash
mvn clean test
```

## 📝 Notlar

- Test sonuçları `src/test/resources/response.txt` dosyasına kaydedilir
- Log kayıtları Log4j ile tutulur
- Chrome WebDriver otomatik olarak WebDriverManager ile yönetilir
- Test verileri Excel dosyasından okunur#   u i _ a u t o m a t i o n  
 