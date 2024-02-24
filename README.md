Deployment Link: <br>
https://advshop-khalishana.koyeb.app/product/list <br>
https://advshop-khalishana.koyeb.app/car/listCar

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


# Tutorial 3
## Reflection
1. Explain what principles you apply to your project! <br>
Jawab: Pada exercise 3 ini, saya mengimplementasikan 3 SOLID principles, yaitu Single Responsibility Principle (SRP), Dependency Inversions Principle (DIP), dan Open-Closed Principle (OCP). Saya mengimplementasikan SRP dengan melakukan pemisahan file ```CarController.java``` dari ```ProductController.java``` sehingga dapat tercapai prinsip SRP yakni suatu kelas hanya bertanggung jawab atas satu fungsionalitas saja. Sementara itu, saya mengimplementasikan prinsip DIP dengan memindahkan implementasi pembuatan UUID yang semula berada di model ```Product.java``` menjadi ke dalam file ```ProductRepository.java```, tepatnya ke dalam method create. Perubahan ini dilakukan untuk mendukung prinsip DIP yaitu modul tingkat tinggi (high level modules), dalam hal ini ```Product.java``` yang bertanggung jawab atas pembuatan model, tidak bergantung pada modul pada tingkat rendah. Selain itu, saya juga melakukan implementasi OCP lewat pengimplementasian interface CarService pada ```CarController.java```. Pengimplementasian interface ini mendukung prinsip dari OCP yaitu memungkinkan terjadinya modifikasi terhadap perilaku Car tanpa mengubah source code yang ada didalam ```CarController.java```.

2. Explain the advantages of applying SOLID principles to your project with examples. <br>
Jawab: Keuntungan dari pengimplementasian SOLID principles pada projek yang saya punya, antara lain memudahkan pemeliharaan atau maintanability sehingga kode akan lebih mudah untuk dirawat, dipahami, dan dikembangkan oleh developer lain; memudahkan saya untuk melakukan update pada kode tanpa perlu mempengaruhi komponen lain yang tidak terkait; hingga mengurangi ketergantungan antar modul yang ada.

 3. Explain the disadvantages of not applying SOLID principles to your project with examples. <br>
Jawab: Kerugian yang dapat dialami dengan tidak mengimplementasikan SOLID principles diantaranya kode yang saya miliki akan sulit untuk dikembangkan oleh dveloper lain hingga lebih sulitnya menambahkan update fitur baru karena bisa saja terdapat fungsionalitas modul lain yang terganggu atas terjadinya update tersebut.
