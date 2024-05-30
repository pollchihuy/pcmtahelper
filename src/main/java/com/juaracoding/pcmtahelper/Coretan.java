package com.juaracoding.pcmtahelper;

public class Coretan {

    public static void main(String[] args) {
        String strBlaBlaBla = "Saya yang bertanda tangan dibawah ini :\n" +
                "\n" +
                "Nama : BUDI IDRIS\n" +
                "No. Kartu Tanda Penduduk : 9525300034551324\n" +
                "Alamat : Apt. 601 JI. Gajahmada No.61, Rokan Hulu, GA 01493\n" +
                "\n" +
                "No. Kartu BPJ S Kesehatan : 9440906124606\n" +
                "\n" +
                "Bertindak untuk dan atas nama diri sendiri dengan ini menyatakan :\n" +
                "\n" +
                "1. Dengan ini setuju secara sukarela tanpa paksaan dari pihak manapun untuk memindahkan\n" +
                "alamat faskes BPJ S Kesehatan saya Siloam Clinic Siloam Clinic Bona Indah\n" +
                "\n" +
                "2. Menyerahkan dokumen—dokumen yang diperlukan sehubungan dengan pelaksanaan\n" +
                "maksud dan tujuan pada angka 1 di atas.\n" +
                "\n" +
                "3. Tidak akan melakukan keberatan dikemudian hari terhadap pemindahan faskes BPJ S\n" +
                "Kesehatan ke fakskes yang telah saya sepakati serta bertanggung jawab penuh dan\n" +
                "melepaskan Siloam Clinic atas setiap kerugian yang mungkin timbul serta kewajiban ganti\n" +
                "rugi baik langsung maupun tidak langsung atas maksud dan tujuan dari Surat Pernyataan\n" +
                "1n1.\n" +
                "\n" +
                "Demikian Surat Pernyataan ini saya buat dengan penuh tanggung jawab serta tanpa adanya\n" +
                "paksaan atupun tekanan dari pihak manapun juga.\n" +
                "\n" +
                "SURABAYA , 29 May 2024\n" +
                "\n" +
                "FALL}\n" +
                "\n" +
                "BUDI IDRIS";
        System.out.println(strBlaBlaBla.contains("BUDI IDRIS") && strBlaBlaBla.contains("9525300034551324") && strBlaBlaBla.contains("Apt. 601 JI. Gajahmada No.61, Rokan Hulu, GA 01493"));
    }
}
