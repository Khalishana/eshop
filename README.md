# Tutorial 1
## Reflection 1
Dalam mengerjakan tutorial 1 ini, saya menerapkan Coding Standards yakni clean code dan secure coding. <br>
- Clean Code: Saya menerapkan prinsip clean code dalam kode yang saya punya dengan mengimplementasikan meaningful names (nama variabel yang jelas ('product')), Function (menerapkan penggunaan function), comments (menerapkan beberapa comment dalam kode), Object and Data Structure (mengimplementasikan objek 'Product'), hingga menerapkan error handling. Berikut salah satu potongan kode yang digunakan untuk mendukung fitur edit product <br>
```
public Product update(Product updatedProduct){
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(updatedProduct.getProductId())) {
                // Update the product with the new details
                productData.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }
```
- Secure Coding: Saya menerapkan secure coding dengan memastikan bahwa setiap id produk yang dibuat bersifat unik dan sulit ditebak dengan mengimplementasikan UUID (Universally Unique Identifier) seperti pada contoh potongan kode berikut
```
@Getter @Setter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;

    public Product() {
        this.productId = UUID.randomUUID().toString();
    }
}
```
## Reflection 2
1. Melakukan testing pada kode seperti unit test dan functional test merupakan suatu hal baru dan cukup menantang bagi saya. Lewat tutorial 1 ini, saya mendapatkan ilmu baru bahwa untuk mengecek keakuratan suatu kode dapat dilakukan testing sehingga kita tidak perlu melakukan pengecekan satu per satu. Banyaknya unit test yang perlu dilakukan menurut saya tergantung pada seberapa kompleks suatu class yang dibuat. Pada dasarnya, untuk memastikan bahwa unit test sudah cukup untuk memverifikasi program dapat diketahui dengan memperhatikan hal-hal tertentu, salah satunya coverage. Namun, 100% coverage tetap saja tidak dapat menutup kemungkinan dapat terjadinya suatu bug ataupun error dalam program.
2. Pembuatan uji fungsional baru dengan setup procedure dan instance variables yang sama tentunya akan mempengaruhi kode saya jika ditelaah dari aspek clean code. Pembuatan setup procedure dan instance variables yang sama akan menyebabkan terjadinya perulangan kode pada program yang saya punya. Untuk mengatasi hal ini, solusi yang dilakukan yakni dengan melakukan pemisahan setup procedure di file yang berbeda. Dengan melakukan hal tersebut, seharusnya masalah awal dapat teratasi dan kode tersebut sudah dapat lebih memenuhi standar clean code.


# Tutorial 2
## Reflection
1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them. <br>
Jawab: Setelah selesai melakukan deployment aplikasi dengan baik, saya mengecek kembali code scanning yang terdapat pada security. Pada bagian tersebut, terdapat beberapa isu yang ditunjukkan oleh PMD, antara lain adanya import yang tidak digunakan hingga modifier yang tidak diperlukan. Untuk mengatasi isu tersebut, saya akan melakukan revisi didalam file terkait sehingga tidak lagi terdapat isu sebagaimana yang ditunjukkan oleh PMD.
   
2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)! <br>
Jawab: Menurut saya, workflow CI/CD yang saya implementasikan pada exercise 2 ini sudah memenuhi definisi dari Continuous Integration dan Continuous Deployment. Alasan workflow tersebut sudah memenuhi definisi tersebut diantaranya proses Continous Integration akan dilakukan oleh adanya file ci.yml. Selain itu, code scanner yang saya miliki (scorecard, PMD, dan sonarcloud) juga akan melakukan pengecekan quality issues kode yang saya punya. Sementara itu, untuk proses Continous Development sendiri dilakukan lewat proses deployment yang dijalankan oleh Koyeb.
