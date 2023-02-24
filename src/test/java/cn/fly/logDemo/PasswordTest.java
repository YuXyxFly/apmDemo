package cn.fly.logDemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * @author fly
 * @date 2022/11/8
 * @description
 */

public class PasswordTest {
    // 密码长度不少于8位且至少包含大写字母、小写字母、数字和特殊符号中的四种
    public static final String password1 = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
    // 密码长度8-20位且至少包含大写字母、小写字母、数字或特殊符号中的任意三种
    public static final String password = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,20}$";

    public static final String number = "^\\d{1,}$";

    public static void main2(String[] args) {
        System.out.println("1111111".matches(number));
        System.out.println("11111asz11".matches(number));
    }

    public static void main1(String[] args) {
        String password1 = "ABCDEFGHIG";  //全部大写                    --1
        String password2 = "abcdefghig";  //全部小写                    --2
        String password3 = "0123456789";  //全部数字                    --3
        String password4 = "!@#$%^&*()";  //全部特殊字符                 --4
        String password5 = "ABCDEabcde";  //大写和小写                   --5
        String password6 = "ABCDE01234";  //大写和数字                   --6
        String password7 = "ABCDE!@#$%";  //大写和特殊字符                   --7
        String password8 = "abcde01234";  //小写和数字                   --8
        String password9 = "abcde!@#$%";  //小写字母和特殊字符                   --9
        String password10 = "01234!@#$%"; //数字和特殊字符                   --10
        String password11 = "Aa4!";       //长度不够8位数                   --11
        String password12 = "ABCDE01234!@#$%"; //符合要求密码任意三种                   --12
        String password13 = "ABCDEabcde!@#$%"; //符合要求密码任意三种                   --13
        String password14 = "ABCDEabcde01234"; //符合要求密码任意三种                   --14
        String password15 = "abcde01234!@#$%"; //符合要求密码任意三种                   --15
        String password16= "Hdjsy@2022"; //符合要求密码任意三种 和 符合全部的四种                   --16

        System.out.println(password1.matches(password) + " 1");
        System.out.println(password2.matches(password)+ " 2");
        System.out.println(password3.matches(password)+ " 3");
        System.out.println(password4.matches(password)+ " 4");
        System.out.println(password5.matches(password)+ " 5");
        System.out.println(password6.matches(password)+ " 6");
        System.out.println(password7.matches(password)+ " 7");
        System.out.println(password8.matches(password)+ " 8");
        System.out.println(password9.matches(password)+ " 9");
        System.out.println(password10.matches(password)+ " 10");
        System.out.println(password11.matches(password)+ " 11");
        System.out.println(password12.matches(password)+ " 12");
        System.out.println(password13.matches(password)+ " 13");
        System.out.println(password14.matches(password)+ " 14");
        System.out.println(password15.matches(password)+ " 15");
        System.out.println(password16.matches(password)+ " 16");
    }

}


