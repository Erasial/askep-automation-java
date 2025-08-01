package org.example.common;

public class Strings {
    public static final String username = "neo";
    public static final String tableDir = "/home/" + username + "/Documents/askep_sorted.xlsx";

    public static final String[] diagnosesWithoutTooth = {"K03.6", "Z01.2"};
    public static final String[] doNotEnd = {"K04.0", "K04.4", "K04.5", "K04.8", "K05.2", "K05.3", "K08.81", "K03.9", "K02.5", "Z01.2"};
    public static final String[] restorations = {"97521", "97522", "97523", "97524", "97525", "97531",
            "97532", "97533", "97534", "97535", "97322", "97322-00"};

    public static final String[] min5 = {"97419", "97415", "97416", "97015", "97458", "97572", "97927", "97411", "97927", "97213"};
    public static final String[] min10 = {"97417", "97418", "97161", "97117", "97118"};
    public static final String[] min20 = {"97455", "97322", "97322-00"};
    public static final String[] min30 = {"97521", "97522", "97523", "97524", "97525", "97531", "97532",
            "97533", "97534", "97535", "97575", "97113", "97114"};

    static final String[] allOperations;

    static {
        allOperations = new String[min5.length + min10.length + min20.length + min30.length];
        System.arraycopy(min5, 0, allOperations, 0, min5.length);
        System.arraycopy(min10, 0, allOperations, min5.length, min10.length);
        System.arraycopy(min20, 0, allOperations, min5.length + min10.length, min20.length);
        System.arraycopy(min30, 0, allOperations, min5.length + min10.length + min20.length, min30.length);
    }

    public static final java.util.Map<String, String> diagnoseFullName = new java.util.HashMap<>();
    static {
        diagnoseFullName.put("K00.2", "(K00.2) Аномалії розмірів та форми зубів");
        diagnoseFullName.put("K00.6", "(K00.6) Порушення в прорізуванні зуба");
        diagnoseFullName.put("K02.0", "(K02.0) Карієс у межах емалі");
        diagnoseFullName.put("K02.1", "(K02.1) Карієс дентину");
        diagnoseFullName.put("K02.5", "(K02.5) Карієс з оголенням пульпи");
        diagnoseFullName.put("K02.8", "(K02.8) Інший карієс зубів");
        diagnoseFullName.put("K03.1", "(K03.1) Стирання зубів");
        diagnoseFullName.put("K03.6", "(K03.6) Відкладення [нарости] на зубах");
        diagnoseFullName.put("K03.8", "(K03.8) Інші уточнені хвороби твердих тканин зубів");
        diagnoseFullName.put("K03.9", "(K03.9) Хвороба твердих тканин зубів, неуточнена");
        diagnoseFullName.put("K04.0", "(K04.0) Пульпіт");
        diagnoseFullName.put("K04.4", "(K04.4) Гострий апікальний періодонтит пульпарного походження");
        diagnoseFullName.put("K04.5", "(K04.5) Хронічний апікальний періодонтит");
        diagnoseFullName.put("K04.8", "(K04.8) Коренева кіста");
        diagnoseFullName.put("K05.2", "(K05.2) Гострий періодонтит");
        diagnoseFullName.put("K05.3", "(K05.3) Хронічний періодонтит");
        diagnoseFullName.put("K07.3", "(K07.3) Аномалії положення зубів");
        diagnoseFullName.put("K08.81", "(K08.81) Патологічний перелом зуба");
        diagnoseFullName.put("K10.3", "(K10.3) Альвеоліт щелеп");
        diagnoseFullName.put("Z01.2", "(Z01.2) Стоматологічне обстеження");
    }
}


