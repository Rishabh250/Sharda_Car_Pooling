package com.example.shardacarpooling;

public class car_documents {

    String pdfName, pdfURL;

    public car_documents(String pdfName, String pdfURL) {
        this.pdfName = pdfName;
        this.pdfURL = pdfURL;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getPdfURL() {
        return pdfURL;
    }

    public void setPdfURL(String pdfURL) {
        this.pdfURL = pdfURL;
    }
}
