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

# Tutorial 4
## Reflection
1. Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests. <br>
Jawab: Mari kita tinjau aspek “Evaluating Your Testing Objectives” berdasarkan beberapa objektif, yakni Correctness, Maintainable Code, dan Productive Workflow. <br>
#### a. Correctness:
- Do I have enough functional tests to reassure myself that my application really works, from point of view of the user? Ya, pada rangkaian kode yang diberikan pada tutorial, ditunjukkan bagaimana proses yang perlu dilakukan sehingga testing yang awalnya tertolak atau ditandai dengan [RED] dapat diterima sepenuhnya yang ditandai dengan [GREEN]
- Am I testing all edge cases thoroughly? Ya
- Do I have tests that check whether all my components fit together properly? Could some integrated tests do this, or are functional tests enough? Ya, penggunaan functional tests saja sebenarnya cukup untuk melakukan tes terhadap seluruh komponen yang ada, akan tetapi penggunaan integrated tests tentunya juga dapat dilakukan untuk melakukan testing secara menyeluruh
#### b. Maintainable Code
- Are my tests giving me the confidence to refactor my code, fearlessly and frequently? Ya
- Are my tests helping me to drive out a good design? If I have a lot of integration tests but less unit tests, do I need to make more unit tests to get better feedback on my code design? Ya, tes yang dilakukan membantu saya untuk mengimplementasikan good design pada kode yang saya punya. Walaupun sudah banyak integration tests yang diimplementasikan, mengintegrasikan integration tests dengan unit tests tidak hanya dapat lebih meningkatkan kualitas kode tetapi juga dapat memastikan desain sistem yang lebih kuat dan lebih mudah dipelihara. Oleh karena itu, membuat lebih banyak unit tests dapat memberi feedback yang lebih baik pada design code yang saya miliki
#### c. Productive Workflow
- Are my feedback cycles as fast as I would like them? When do I get warned about bugs, and is there any practical way to make that happen sooner? Ya, sejauh ini feedback cycles yang diberikan sudah sesuai dengan ekspektasi saya
- Is there some way that I could write faster integration tests that would give me feedback quicker? Melakukan testing berulang secara berkala
- Can I run a subset of the full test suite when I need to? Ya
- Am I spending to much time waiting for tests to run, and thus less time in a productive flow state? Tidak

2. You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests. <br>
Jawab: F.I.R.S.T. principle yang mencakup fast, isolated/independent, repeatable, self-validating, dan thorough/timely sudah diimplementasikan pada kode yang saya punya. Testing yang dilakukan pada kode yang saya punya sudah berjalan dengan cepat tanpa mengganggu workflow yang ada, berlangsung secara independen tanpa mengganggu test case yang lain, menghasilkan hasil yang konsisten, dapat memberi feedback apakah tes berhasil atau tidak secara jelas (self-validating), serta meng-cover baik happy paths maupun unhappy paths dengan baik
