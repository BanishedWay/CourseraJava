package coursera.xujinqi.cousera1.week3;

import java.text.NumberFormat;

/**
 * @author 许 劲淇
 * @date 2022/1/26 16:00
 */

public class test {

        public static void main(String[] args) {

                Double myNumber = 23323.0;
                // getInstance()
                // 返回当前缺省语言环境的缺省数值格式。
                String myString = NumberFormat.getInstance().format(myNumber);
                System.out.println(myString);
                System.out.println(myString.equals("23,323"));
                // getCurrencyInstance()返回当前缺省语言环境的通用格式
                myString = NumberFormat.getCurrencyInstance().format(myNumber);
                System.out.println(myString);

                // getNumberInstance() 返回当前缺省语言环境的通用数值格式。
                myString = NumberFormat.getNumberInstance().format(myNumber);
                System.out.println(myString);
        }
}
