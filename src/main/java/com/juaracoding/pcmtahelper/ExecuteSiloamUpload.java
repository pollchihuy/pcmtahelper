package com.juaracoding.pcmtahelper;

import com.juaracoding.pcmtahelper.util.DataGenerator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ExecuteSiloamUpload {

    public static void main(String[] args) {
//        SiloamUploadPage siloamUploadPage = new SiloamUploadPage();
//        siloamUploadPage.executeThis();

//        SociolaRotateImage rotateImage = new SociolaRotateImage();
//        rotateImage.eksekusi();

//        SociolaReportValidation sociolaReportValidation = new SociolaReportValidation();
//        sociolaReportValidation.eksekusi();

//        SociolaFilterDashoboard sociolaFilterDashoboard = new SociolaFilterDashoboard();
//        sociolaFilterDashoboard.eksekusi();

//        CanvaDownloadImage canvaDownloadImage = new CanvaDownloadImage();
//        canvaDownloadImage.eksekusi("sweet honey");

//        InventoryDelete inventoryDelete = new InventoryDelete();
//        inventoryDelete.eksekusi();

        InventoryDatePicker inventoryDatePicker = new InventoryDatePicker();
        inventoryDatePicker.eksekusi();

//        DataGenerator d = new DataGenerator();
//        Set s = new HashSet();
//        int intBanyakData = 1;
//        Boolean isValid = false;
//        while(!isValid){
//            if(s.size()==intBanyakData){
//                isValid=true;
//            }
//            s.add(d.dataText());
//        }
//        // HashSet
//        Iterator<Integer> it = s.iterator();
//        while (it.hasNext()) {
//            canvaDownloadImage.eksekusi(String.valueOf(it.next()));
//        }
    }
}