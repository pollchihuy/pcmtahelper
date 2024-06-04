package com.juaracoding.pcmtahelper;

import com.juaracoding.pcmtahelper.util.DataGenerator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Coretan {

    public static void main(String[] args) {
//        String strBlaBlaBla = "Saya yang bertanda tangan dibawah ini :\n" +
//                "\n" +
//                "Nama : BUDI IDRIS\n" +
//                "No. Kartu Tanda Penduduk : 9525300034551324\n" +
//                "Alamat : Apt. 601 JI. Gajahmada No.61, Rokan Hulu, GA 01493\n" +
//                "\n" +
//                "No. Kartu BPJ S Kesehatan : 9440906124606\n" +
//                "\n" +
//                "Bertindak untuk dan atas nama diri sendiri dengan ini menyatakan :\n" +
//                "\n" +
//                "1. Dengan ini setuju secara sukarela tanpa paksaan dari pihak manapun untuk memindahkan\n" +
//                "alamat faskes BPJ S Kesehatan saya Siloam Clinic Siloam Clinic Bona Indah\n" +
//                "\n" +
//                "2. Menyerahkan dokumen—dokumen yang diperlukan sehubungan dengan pelaksanaan\n" +
//                "maksud dan tujuan pada angka 1 di atas.\n" +
//                "\n" +
//                "3. Tidak akan melakukan keberatan dikemudian hari terhadap pemindahan faskes BPJ S\n" +
//                "Kesehatan ke fakskes yang telah saya sepakati serta bertanggung jawab penuh dan\n" +
//                "melepaskan Siloam Clinic atas setiap kerugian yang mungkin timbul serta kewajiban ganti\n" +
//                "rugi baik langsung maupun tidak langsung atas maksud dan tujuan dari Surat Pernyataan\n" +
//                "1n1.\n" +
//                "\n" +
//                "Demikian Surat Pernyataan ini saya buat dengan penuh tanggung jawab serta tanpa adanya\n" +
//                "paksaan atupun tekanan dari pihak manapun juga.\n" +
//                "\n" +
//                "SURABAYA , 29 May 2024\n" +
//                "\n" +
//                "FALL}\n" +
//                "\n" +
//                "BUDI IDRIS";
//        Boolean isValid = strBlaBlaBla.contains("BUDI IDRIS") && strBlaBlaBla.contains("9525300034551324") && strBlaBlaBla.contains("Apt. 601 JI. Gajahmada No.61, Rokan Hulu, GA 01493") && strBlaBlaBla.contains("9440906124606");
//        System.out.println(isValid);

        DataGenerator d = new DataGenerator();
        Set s = new HashSet();
        int intBanyakData = 10;
        Boolean isValid = false;
        while(!isValid){
            if(s.size()==intBanyakData){
                isValid=true;
            }
            s.add(d.dataText());
        }
        // HashSet
        Iterator<Integer> it = s.iterator();
        while (it.hasNext()) {
            System.out.println("variable "+it.next());
        }
        /**
             a mind needs books as a sword needs a whetstone
             if it is to keep its edge.
             Winter is coming.
             Fear cuts deeper than swords.
             Why is it that when one man builds a wall
             the next man immediately needs to know what's on the other side?
             A lion doesn't concern itself with the opinion of sheep.
             When you play a game of thrones you win or you die.
             Every flight begins with a fall.
             Once you’ve accepted your flaws
             no one can use them against you.
             All dwarfs are bastards in their father's eyes
             Hodor? Hodor

             Things we lose have a way of coming back to us in the end
             if not always in the way we expect.
             It is the unknown we fear when we look upon death and darkness, nothing more.
             It takes a great deal of bravery to stand up to our enemies, but just as much to stand up to our friends.
             We’ve all got both light and dark inside us. What matters is the part we choose to act on. That’s who we really are.
             No story lives unless someone wants to listen.
             The stories we love best do live in us forever.
             So whether you come back by page or by the big screen, Hogwarts will always be there to welcome you home.
             If you want to know what a man’s like, take a good look at how he treats his inferiors, not his equals.
             It is our choices, Harry, that show what we truly are, far more than our abilities
         */

    }
}