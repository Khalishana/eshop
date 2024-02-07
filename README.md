# Reflection 1
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
# Reflection 2
1. Melakukan testing pada kode seperti unit test dan functional test merupakan suatu hal baru dan cukup menantang bagi saya. Lewat tutorial 1 ini, saya mendapatkan ilmu baru bahwa untuk mengecek keakuratan suatu kode dapat dilakukan testing sehingga kita tidak perlu melakukan pengecekan satu per satu. Banyaknya unit test yang perlu dilakukan menurut saya tergantung pada seberapa kompleks suatu class yang dibuat. Pada dasarnya, untuk memastikan bahwa unit test sudah cukup untuk memverifikasi program dapat diketahui dengan memperhatikan hal-hal tertentu, salah satunya coverage. Namun, 100% coverage tetap saja tidak dapat menutup kemungkinan dapat terjadinya suatu bug ataupun error dalam program.
2. Pembuatan uji fungsional baru dengan setup procedure dan instance variables yang sama tentunya akan mempengaruhi kode saya jika ditelaah dari aspek clean code. Pembuatan setup procedure dan instance variables yang sama akan menyebabkan terjadinya perulangan kode pada program yang saya punya. Untuk mengatasi hal ini, solusi yang dilakukan yakni dengan melakukan pemisahan setup procedure di file yang berbeda. Dengan melakukan hal tersebut, seharusnya masalah awal dapat teratasi dan kode tersebut sudah dapat lebih memenuhi standar clean code.
