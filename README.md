**API CARDLESS WITHDRAWAL**

BUSINES FLOW
- Nasabah melalui aplikasi (ex: mobile) melakukan generate kode referensi dan mengisi jumlah uang yang ingin diambil
- Aplikasi mobile memanggil API "/cardless-withdrawal/generate-reference-code" dan menampilkan kode referensi
- Selanjutnya nasabah memberikan kode referensi ke mitra (ex: indomart, alfamart, etc)
- Mitra melalui aplikasi memanggil API "/cardless-withdrawal/withdrawal" dan memasukan kode referensi
- Transaksi berhasil dan mitra memberikan uang kepada nasabah
- Saldo nasabah akan berkurang dan saldo mitra akan bertambah

ANALISIS TABEL DATABASE
- ENTITY
1. Customer -> table data diri nasabah
2. Bank Account -> table data rekening
3. Merchant -> table data merchant yang terdaftar
4. Merchant Fee -> table data merchant beserta fee merchant yang telah disepakati
5. Cardless Withdrawal -> table data transaksi

- RELATIONSHIP
1. Customer -> Bank Account = One to Many
2. Bank Account -> Cardless Withdrawal = One to Many
3. Merchant Fee -> Merchant = One to One
4. Merchant -> Cardless Withdrawal = One to Many

TEKNOLOGI YANG DIGUNAKAN
1. Java Version = 17
2. Build Tools = Maven
3. Database = MySQL

CARA MENJALANKAN APLIKASI
1. Setting environtment variable
```shell
DB_HOST=
DB_PORT=
DB_USER=
DB_PASSWORD=
DB_ROOT_PASSWORD=
DB_DATABASE=
```
2. Menjalankan aplikasi
- Via Maven
```shell
mvn spring-boot:run 
```
- Jar File
```shell
mvn clean package -Dmaven.test.skip
cd target
java -jar cardless-withdrawal-0.0.1-SNAPSHOT.jar
```
- Via Docker
```shell
docker compose up -d
```