package com.microfinance.hsmicrofinance.Network.data;



import com.microfinance.hsmicrofinance.Network.models.BillPayment;

import java.util.ArrayList;

public class Constants {

    /*Airtime*/
    public static final String EDEPOSIT = "https://member.hsgroup.tech/api/edeposit/mobile/payment/";
    public static final String EDEPOSIT_ALT = "https://member.hsgroup.tech/user/edeposit/mobile/credit/card";
    public static final String SAFARICOM = "safaricom";
    public static final String AIRTEL = "airtel";
    public static final String KENYA_POWER_TOKEN = "kplc_prepaid";
    public static final String STAR_TIMES = "startimes";
    public static final String NAIROBI_WATER = "nairobi_water";
    public static final String GOTV = "gotv";
    public static final String DSTV = "dstv";
    public static final String ZUKU = "zuku";
    public static final String KENYA_POWER_POST = "kplc_postpaid";
    public static final String TELKOM ="telkom";
    public static final String JTL = "jtl";



    public static final String AIRTEL_URL = "https://firebasestorage.googleapis.com/v0/b/bills-intergrations.appspot.com/o/airtel.png?alt=media&token=e0f49f79-4835-4644-ac76-30711224c741";
    public static final String SAFARICOM_URL = "https://firebasestorage.googleapis.com/v0/b/bills-intergrations.appspot.com/o/safaricomjpg.jpg?alt=media&token=4973b1d7-59c6-4261-a9c7-e5598fcd1e3b";
    public static final String TELKOM_URL = "https://firebasestorage.googleapis.com/v0/b/bills-intergrations.appspot.com/o/telkom.png?alt=media&token=340269a1-8347-4505-9c79-7afa4ede4425";
    public static final String EQUITEL_URL = "https://firebasestorage.googleapis.com/v0/b/bills-intergrations.appspot.com/o/equitel.png?alt=media&token=571d589f-c119-44e6-b520-e74f1ddb0f07";
    public static final String JTL_URL = "https://firebasestorage.googleapis.com/v0/b/bills-intergrations.appspot.com/o/jtl.png?alt=media&token=5ebe7d23-83ee-476f-b9a4-08b65d984eef";

    /*Pay TV*/
    public static final String DSTV_URL = "https://firebasestorage.googleapis.com/v0/b/bills-intergrations.appspot.com/o/dstv.png?alt=media&token=2b9574ab-137f-4aec-b4bb-28803cd8f215";
    public static final String GOTV_URL = "https://firebasestorage.googleapis.com/v0/b/bills-intergrations.appspot.com/o/gotv.png?alt=media&token=89491c5f-873a-424d-bb7a-3b92ff21169b";
    public static final String ZUKU_URL = "https://firebasestorage.googleapis.com/v0/b/bills-intergrations.appspot.com/o/zuku.png?alt=media&token=3520d099-63e8-40b1-9fc6-12221c3e31b3";
    public static final String STARTTIMES_URL = "https://firebasestorage.googleapis.com/v0/b/bills-intergrations.appspot.com/o/startimes.png?alt=media&token=f98bd657-64fb-475d-b2f8-54fd9a92f893";

    /*Power*/
    public static final String POWER_POST_PAID_URL = "https://firebasestorage.googleapis.com/v0/b/bills-intergrations.appspot.com/o/kenyapower.png?alt=media&token=d4bb14ce-bc6f-4611-a9ea-558d912649a0";
    /*Water*/

    public static ArrayList<BillPayment> initAirtime() {
        ArrayList<BillPayment> list = new ArrayList<>();

        list.add(new BillPayment("Safaricom", SAFARICOM, SAFARICOM_URL));
        list.add(new BillPayment("Airtel", AIRTEL, AIRTEL_URL));
        list.add(new BillPayment("Telkom", TELKOM, TELKOM_URL));
        list.add(new BillPayment("JTL", JTL, JTL_URL));
        return list;
    }

    public static ArrayList<BillPayment> initPayTV() {
        ArrayList<BillPayment> list = new ArrayList<>();

        list.add(new BillPayment("DSTV", DSTV, DSTV_URL));
        list.add(new BillPayment("GOtv", GOTV, GOTV_URL));
        list.add(new BillPayment("Zuku", ZUKU, ZUKU_URL));
        list.add(new BillPayment("Startimes", STAR_TIMES, STARTTIMES_URL));

        return list;
    }

    public static ArrayList<BillPayment> initPower() {
        ArrayList<BillPayment> list = new ArrayList<>();

        list.add(new BillPayment("KPLC Prepaid", KENYA_POWER_TOKEN, POWER_POST_PAID_URL));
        list.add(new BillPayment("KPLC Postpaid", KENYA_POWER_POST, POWER_POST_PAID_URL));
        return list;
    }

    public static ArrayList<BillPayment> initWater() {
        ArrayList<BillPayment> list = new ArrayList<>();

        list.add(new BillPayment("Nairobi Water", NAIROBI_WATER, "https://bit.ly/CBImageCinque"));
       /* list.add(new BillPayment("Eldowas Water", NAIROBI_WATER, "https://bit.ly/CBImageParis"));
        list.add(new BillPayment("Embu Water", NAIROBI_WATER, "https://bit.ly/CBImageRio"));
        list.add(new BillPayment("Meru Water", NAIROBI_WATER, "https://bit.ly/CBImageRio"));
        list.add(new BillPayment("Nakuru Water", NAIROBI_WATER, "https://bit.ly/CBImageParis"));
        list.add(new BillPayment("Kirinyaga Water", NAIROBI_WATER, "https://bit.ly/CBImageCinque"));*/
        return list;
    }
}
