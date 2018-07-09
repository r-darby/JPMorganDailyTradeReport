package com.jpmorgan.instructions.data;

/**
 * Sample input data representing Instructions
 */
public class DataSample {

    public static String getDataSample(){
        StringBuilder sb = new StringBuilder();

        sb.append("foo ,B , 0.50, SGP, 01 Jan 2016, 02 Jan 2016, 200, 100.25 \n");
        sb.append("bar ,S , 0.22, AED, 05 Jan 2016, 07 Jan 2016, 450, 150.5 \n");

        sb.append("bar ,S , 0.22, SAR, 10 May 2017, 11 May 2017, 450, 150.5 \n");
        sb.append("foo ,B , 0.50, AED, 10 May 2017, 12 May 2017, 200, 100.25 \n");
        sb.append("bar ,S , 0.22, SAR, 10 May 2017, 13 May 2017, 450, 150.5 \n");
        sb.append("bar ,S , 0.22, SAR, 10 May 2017, 14 May 2017, 450, 150.5 \n");

        sb.append("bar ,S , 1, USD, 10 May 2017, 12 May 2017, 450, 150.5 \n");
        sb.append("foo ,B , 1, USD, 10 May 2017, 13 May 2017, 200, 100.25 \n");
        sb.append("bar ,S , 1, USD, 10 May 2017, 14 May 2017, 450, 150.5 \n");
        sb.append("bar ,S , 1, USD, 10 May 2017, 15 May 2017, 450, 150.5 \n");

        sb.append("foo ,S , 1, USD, 10 May 2017, 12 May 2017, 100, 100 \n");
        sb.append("foo ,S , 1, USD, 10 May 2017, 13 May 2017, 100, 100 \n");
        sb.append("foo ,S , 1, USD, 10 May 2017, 14 May 2017, 100, 100 \n");
        sb.append("foo ,S , 1, USD, 10 May 2017, 15 May 2017, 100, 100 \n");

        sb.append("aaa ,S , 1, USD, 10 May 2017, 12 May 2017, 200, 100 \n");
        sb.append("aaa ,S , 1, USD, 10 May 2017, 13 May 2017, 200, 100 \n");
        sb.append("aaa ,S , 1, USD, 10 May 2017, 14 May 2017, 200, 100 \n");
        sb.append("aaa ,S , 1, USD, 10 May 2017, 15 May 2017, 200, 100 \n");
        sb.append("aaa ,S , 1, USD, 10 May 2017, 19 May 2017, 200, 100 \n");

        sb.append("aaa ,B , 1, USD, 10 May 2017, 02 May 2017, 200, 100 \n");
        sb.append("aaa ,B , 1, USD, 10 May 2017, 02 May 2017, 200, 100 \n");
        sb.append("aaa ,B , 1, USD, 10 May 2017, 02 May 2017, 200, 100 \n");
        sb.append("aaa ,B , 1, USD, 10 May 2017, 02 May 2017, 200, 100 \n");
        sb.append("aaa ,B , 1, USD, 10 May 2017, 02 May 2017, 200, 100 \n");

        sb.append("aaa ,B , 0.27 , AED, 10 May 2017, 02 May 2017, 200, 100 \n");
        sb.append("aaa ,B , 0.27 , AED, 10 May 2017, 03 May 2017, 200, 100 \n");
        sb.append("aaa ,B , 0.27 , AED, 10 May 2017, 04 May 2017, 200, 100 \n");
        sb.append("aaa ,B , 0.27 , AED, 10 May 2017, 05 May 2017, 200, 100 \n");
        sb.append("aaa ,B , 0.27 , AED, 10 May 2017, 06 May 2017, 200, 100 \n");
        sb.append("aaa ,B , 0.27 , AED, 10 May 2017, 06 May 2017, 200, 100 \n");

        sb.append("bbb ,B , 1.29 , GBP, 10 May 2017, 05 May 2017, 200, 100 \n");
        sb.append("bbb ,B , 1.29 , GBP, 10 May 2017, 06 May 2017, 200, 100 \n");
        sb.append("bbb ,B , 1.29 , GBP, 10 May 2017, 06 May 2017, 200, 100 \n");

        sb.append("bar ,B , 0.22, SAR, 10 May 2017, 10 May 2017, 450, 150.5 \n");
        sb.append("bar ,B , 0.22, SAR, 10 May 2017, 11 May 2017, 450, 150.5 \n");
        sb.append("bar ,B , 0.22, SAR, 10 May 2017, 12 May 2017, 450, 150.5 \n");
        sb.append("bar ,B , 0.22, SAR, 10 May 2017, 13 May 2017, 450, 150.5 \n");
        sb.append("bar ,B , 0.22, SAR, 10 May 2017, 14 May 2017, 450, 150.5 \n");

        return sb.toString();
    }



}
