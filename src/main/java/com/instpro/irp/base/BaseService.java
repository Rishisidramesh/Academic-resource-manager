package com.instpro.irp.base;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.xml.crypto.Data;

public abstract class BaseService{
    public static final LocalDateTime SYSTEM_DEFAULT_DATE =
        LocalDateTime.of(1900, 1, 1, 0, 0, 0);
     public static final LocalDateTime SYSTEM_MIN_DATE =
        LocalDateTime.of(1900, 1, 1, 0, 0, 0);
     public static final LocalDateTime SYSTEM_MAX_DATE =
        LocalDateTime.of(9999, 12, 31, 23, 59, 59);

    DateTimeFormatter NativeFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");// capital h because we expect 24 hour and differentiate between mintutes and months
    DateTimeFormatter OracleFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
    public static boolean isnull(Object value){
        if (value==null) {
            return true;
        }

        if (value instanceof String) {
            return ((String) value).trim().isEmpty();
        }

        if(value instanceof Collection){
            return ((Collection<?>)value).isEmpty();
        }
        if (value instanceof Map) {
            return ((Map<?,?>)value).isEmpty();
        }
        if (value.getClass().isArray()) {
            return Array.getLength(value)==0;
        }
        return false;
    }
    public static <T> T nvl(T value, T defaultValue) {
    return value != null ? value : defaultValue;
}

    public static final String BlankString(){
        return " ";
    }
    public  static String NumberToString(int number){
        if(number==0){
        return "zero";
    }
    
    String[] ones = {
    "", 
    "one", 
    "two", 
    "three", 
    "four", 
    "five", 
    "six", 
    "seven", 
    "eight", 
    "nine"
};
    if(number<10){
        return ones[number];
    }
    String tens[]={
    "","",
    "twenty",
    "thirty",
    "forty",
    "fifty",
    "sixty",
    "seventy",
    "eighty",
    "ninety"
};
    String teens[]= {
        "ten",
        "eleven",
        "twelve",
        "thirteen",
        "fourteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "nineteen"
    };
    if(number<20 && number>=10){
        return teens[number-10]; //here since we are doing index it is number - 10 so if it 12 then 12-10 so
    }
    int TensPart=number/10;
    int OnesPart=number%10;
    // int HunderedsPart = number/100;
    // int ThousandsPart = number/1000;
    // int TenThousandsPart = number/10000;
    // int LakhPart = number/100000;
    // int TenLakhPart = number/1000000;
    // int CrorePart = number/10000000;
    // int TenCrorePart = number/100000000; 
    if(number<100){
        return tens[number / 10] + (number % 10 != 0 ? " " + ones[number % 10] : "");
    }
    if(number<1000){
        return ones[number/100]+" hundred"+(number%100!=0 ?" "+NumberToString(number%100):"");
    }
    if(number<100000){
        return NumberToString(number/1000)+" thousand"+(number%1000!=0?" "+NumberToString(number%1000):"");
    }
    if(number<10000000){
        return NumberToString(number/100000)+" lakh"+(number%100000!=0?" "+NumberToString(number%100000):"");
    }
    if(number<1000000000){
        return NumberToString(number/10000000)+" crore"+(number%10000000!=0?" "+NumberToString(number%10000000):"");
    }
    // for negative numbers
    if(number<0){
        return "minus"+ NumberToString(-number);
    }
    // if(number<1000 && number>=100){
    //     return ones[HunderedsPart]+" "+tens[TensPart]+" "+ones[OnesPart];
    // }
    // if(number<10000 && number>=1000){
    //     return ones[ThousandsPart]+" "+ones[HunderedsPart]+" "+tens[TensPart]+" "+ones[OnesPart];
    // }
    // if(number<1000000 && number>=10000){
    //     return tens[TenThousandsPart]+" "+ones[ThousandsPart]+" "+ones[HunderedsPart]+" "+tens[TensPart]+" "+ones[OnesPart];
    // }
    // if(number<1000000 && number>=100000){
    //     return ones[LakhPart]+" "+tens[TenThousandsPart]+" "+ones[ThousandsPart]+" "+ones[HunderedsPart]+" "+tens[TensPart]+" "+ones[OnesPart];
    // }
    // if(number<10000000 && number>=1000000){
    //     return tens[TenLakhPart]+" "+ ones[LakhPart]+" "+tens[TenThousandsPart]+" "+ones[ThousandsPart]+" "+ones[HunderedsPart]+" "+tens[TensPart]+" "+ones[OnesPart];
    // }
    // if(number<100000000 && number>=10000000){
    //     return ones[CrorePart]+" "+ ones[LakhPart]+" "+tens[TenThousandsPart]+" "+ones[ThousandsPart]+" "+ones[HunderedsPart]+" "+tens[TensPart]+" "+ones[OnesPart];
    // }
    // if(number<1000000000 && number>=100000000){
    //     return tens[TenCrorePart]+" "+ones[LakhPart]+" "+tens[TenThousandsPart]+" "+ones[ThousandsPart]+" "+ones[HunderedsPart]+" "+tens[TensPart]+" "+ones[OnesPart];
    // }
return "";
}
    public static String toStringValue(Object value){
        if(value==null){
            return "";
        }
        else if (value instanceof String) {
            return (String)value;
        }
        else if(value instanceof Number){
            return NumberToString(((Number)value).intValue());
        }
      return value.toString();
    }
    public static void main(String[] args) {
// testing 
    // NumberToString tests
    System.out.println(NumberToString(0));
    System.out.println(NumberToString(999444123));

    // isNull tests
    System.out.println(isnull(null));              // true
    System.out.println(isnull(""));                // true
    System.out.println(isnull("   "));             // true
    System.out.println(isnull("hello"));           // false
    System.out.println(isnull(0));                 // false
    System.out.println(isnull(123));               // false
    System.out.println(isnull(new int[]{}));       // true
    System.out.println(isnull(new int[]{1,2}));    // false
}
public String DateToString(LocalDateTime dtm){
    if(dtm==null){
        return SYSTEM_DEFAULT_DATE.format(NativeFormat);
    }
    return dtm.format(NativeFormat);
}  
public String DateToString(LocalDateTime dtm,DateTimeFormatter f){
    if(dtm==null){
        return SYSTEM_DEFAULT_DATE.format(f);
    }
    return dtm.format(f);
}  
public LocalDateTime StringToDate(String stdm){
    return LocalDateTime.parse(stdm, NativeFormat); // Parse string to LocalDateTime
        
}
}


