import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Manage_Start {
    public static void main(String[] args) {
        //定义一个集合存放用户对象
        ArrayList<User> list = new ArrayList<>();

        //登录菜单
        System.out.println("欢迎来到学生管理系统");

        //循环输出，除非登录成功或自动退出
        loop :
        while( true ){
        System.out.println("请选择操作1登录 2注册 3忘记密码 4退出");

        //输入执行操作
        Scanner sc = new Scanner(System.in);
        String choose = sc.next();
            switch ( choose ){
                case "1":{
                    login(list);
                    break;
                }
                case "2":{
                    registered(list);
                    break;
                }
                case "3":{
                    forget_Password(list);
                    break;
                }
                case "4":{
                    System.out.println("退出成功");
                    System.exit(0);
                }
                default:{
                    System.out.println("请输入有效选项");
                }
            }
        }
    }

    //用户注册
    public static void registered(ArrayList<User> list){
        //新建一个用户对象，用于存放信息
        User u1 = new User();
        Scanner sc =new Scanner(System.in);

        //验证用户名
        while (true) {
            System.out.println("请输入用户名称:(用户名长度必须在3~15位之间,只能是字母加数字的组合，但是不能是纯数字)");
            //输入用户名
            String username = sc.next();
            //判断用户名是否合理
            if (name_Check(list, username)) {
                //合理，跳出循环，继续往下执行
                u1.setName(username);
                System.out.println("用户名输入成功");
                break;
            } else {
                //不合理，提示重新输入
                System.out.println("用户名已存在或不合理，请重新输入");
            }
        }

        //验证密码
        while (true) {
            //第一次密码输入
            System.out.println("请输入密码:");
            String password = sc.next();

            //第二次密码输入
            System.out.println("请再次输入密码");
            String second = sc.next();

            //验证两次密码是否一致
            if(password_Check(password,second)){
                //两次密码一致,跳出循环
                System.out.println("密码输入成功");
                u1.setPassword(password);
                break;
            }else {
                //两次密码不一致,重新输入
                System.out.println("两次密码输入不一致，请重新输入");
            }
        }

        //验证身份证
        while (true) {
            System.out.println("请输入身份证号码：");
            String id = sc.next();
            if(id_Check(list,id)){
                //验证通过,跳出循环，继续执行
                u1.setId(id);
                System.out.println("身份证输入成功");
                break;
            }else {
                //验证失败，提示重新输入
                System.out.println("验证失败，请重新输入");
            }
        }

        //手机号验证
        while (true) {
            System.out.println("请输入手机号码：(18位)");
            String phone = sc.next();
            if(phone_Check(phone)){
                //验证通过，跳出循环，继续执行
                u1.setPhone(phone);
                System.out.println("手机号码输入成功");
                break;
            }else {
                //验证失败，提示重新输入
                System.out.println("验证失败，请重新输入");
            }
        }

        //将用户对象存入集合
        list.add(u1);
        System.out.println("注册成功");

    }

    //用户登录
    public static void login(ArrayList<User> list){
        //登录功能：
        //​ 1，键盘录入用户名
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String name = sc.next();

        // 用户名如果未注册，直接结束方法，并提示：用户名未注册，请先注册
        int index = name_Index(list, name);
        if(index >= 0){
            //用户名存在
            loop:
            for (int i = 3; i > 0 ; i--) {
                while (true) {
                    //​ 2，键盘录入密码
                    System.out.println("请输入密码");
                    String password = sc.next();
                    //​ 3，键盘录入验证码
                    String cap = captcha();
                    System.out.println("验证码： " + cap );
                    System.out.println("请输入验证码：");
                    String user_cap = sc.next();
                    if( cap.equalsIgnoreCase(user_cap) ){
                        //验证码正确，匹配用户名和密码
                        boolean flag = list.get(index).getPassword().equals(password);
                        if(flag){
                            //匹配成功,跳出循环
                            System.out.println("登录成功");
                            //创建学生信息管理系统对象
                            Student_Manage star = new Student_Manage();
                            star.star_StudentManage();
                            break loop;
                        }else {
                            //匹配失败，提示剩余次数
                            if(i - 1 != 0){
                                System.out.println("登录失败，你还有" + (i-1) + "次机会");
                                break ;
                            }else {
                                System.out.println("密码错误，账户已被锁定");
                                break loop;
                            }

                        }
                    }else {
                        //验证码错误，提示重新输入验证码
                        System.out.println("验证码错误,请重新输入");
                    }
                }
            }


        }else {
            System.out.println("用户名未注册，请先注册");
            return;
        }

        //验证要求：

        //​ 判断验证码是否正确，如不正确，重新输入
        //
        //​ 再判断用户名和密码是否正确，有3次机会
    }

    //用户名验证
    public static boolean name_Check(ArrayList<User> list , String username){
        //判断是否以及含有此用户名
        int index = name_Index(list, username);
        if(index >= 0){
            //已存在
            return false;
        }

        //用户名长度必须在3~15位之间
        int length = username.length();
        if( length < 3 || length > 15 ){
            return false;
        }

        //定义计数器,计算字母和数字的个数
        int E_count = 0;

        //遍历字符串,对每位字符验证
        for (int i = 0; i < username.length(); i++) {
            //取出每一位字符
            char c = username.charAt(i);

            //判断每一位是否为字母或数字
            if( ( c >='a' && c <= 'z' ) || ( c >='A' && c <= 'Z' ) || ( c >='0' && c <= '9' ) ){
                //每一位是否为字母或数字
                //查询字母个数
                if (( c >='a' && c <= 'z' ) || ( c >='A' && c <= 'Z' )){
                    E_count ++;
                }
            }else {
                //不是字母或数字
                return false;
            }
        }

        if (E_count != 0){
            //符合要求
            return true;
        }else {
            //字符串为纯数字，不符合要求
            return false;
        }
    }

    //密码验证
    public static boolean password_Check(String first,String second){
        //密码键盘输入两次，两次一致才可以进行注册。
        //判断两次是否一致，返回结果
        return first.equals(second);
    }

    //忘记密码
    public static void forget_Password(ArrayList<User> list){
        //忘记密码：
        // 1，键盘录入用户名，判断当前用户名是否存在，如不存在，直接结束方法，并提示：未注册
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String use_name = sc.next();

        //判断当前用户名是否存在
        int index = name_Index(list,use_name);
        if( index >= 0 ){
            // 2，键盘录入身份证号码和手机号码
            System.out.println("请输入身份证号码");
            String id = sc.next();
            System.out.println("请输入手机号码");
            String phone = sc.next();

            //​ 3，判断当前用户的身份证号码和手机号码是否一致，
            User user = list.get(index);
            if(user.getId().equals(id) && user.getPhone().equals(phone)){

                //一致
                while (true) {
                    //提示输入密码，进行修改。
                    System.out.println("请输入新的密码：");
                    String new_password = sc.next();
                    System.out.println("请再次输入此的密码：");
                    String new_password2 = sc.next();
                    if( new_password.equals(new_password2)){
                        //两次新密码一致，录入用户对象
                        System.out.println("新密码修改成功");
                        user.setPassword(new_password);
                        break;
                    }else {
                        System.out.println("两次输入密码不一致,请重新输入");
                    }
                }
            }else {
                System.out.println("与当前用户的身份证号码和手机号码不一致");
            }
        }else {
            System.out.println("未注册");
            return;
        }

    }

    //身份证验证
    public static boolean id_Check(ArrayList<User> list , String id){
        // 验证要求：
        //长度为18位
        if(id.length() != 18){
            return false;
        }
        //不能以0为开头
        if(id.charAt(0) == '0'){
            return false;
        }
        //前17位，必须都是数字
        for (int i = 0; i < 17; i++) {
            char c = id.charAt(i);
            if(c > '9' || c < '0'){
                return false;
            }
        }
        //最为一位可以是数字，也可以是大写X或小写x
        char c =id.charAt(id.length() - 1);
        if( (c >= '0' && c <= '9') || c == 'X' || c == 'x'){
            return true;
        }else {
            return false;
        }
    }

    //手机号验证
    public static boolean phone_Check(String phone){
        //验证要求：
        //​ 长度为11位
        if(phone.length() != 11){
            return false;
        }
        //​ 不能以0为开头
        if(phone.charAt(0) == '0'){
            return false;
        }
        //​ 必须都是数字
        //遍历字符串判断
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if(c > '9' || c <'0'){
                return false;
            }
        }
        //都符合要求
        return true;
    }

    //用户名唯一性,已存在返回在集合中的index,不存在返回-1
    public static int name_Index(ArrayList<User> list , String name){
        //遍历集合，比较name
        for (int i = 0; i < list.size(); i++) {
            if( list.get(i).getName().equals(name) ){
                //存在，返回index;
                return i;
            }
        }
        //不存在，返回-1；
        return -1;
    }



    //生成随机验证码
    public static String captcha(){
        //验证码规则：
        //​ 长度为5
        //​ 由4位大写或者小写字母和1位数字组成，同一个字母可重复
        //​ 数字可以出现在任意位置
        //比如：
        //​ aQa1K

        //随机生成四个字母和一个数字，存放在字符数组里，再打乱顺序，转变为字符串输出

        //大写字母65-90 小写字母97-122
        char [] arr = new char[5];
        Random r = new Random();

        //录入前4位随机字母
        for (int i = 0; i < arr.length - 1; ) {
            //随机产生一个在范围65-122内的数字，判断是否为大小写数字
            int number = r.nextInt(58) + 65;
            if( number>90 && number <97){//不在范围内
            }else {
                arr[i] =(char)number;
                i++;
            }
        }

        //最后一位录入随机数字
        arr[arr.length - 1] = (char)('0' + r.nextInt(10));

        //再打乱顺序
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(arr.length);
            //互换位置
            char temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }

        //转变为字符串输出
        String s = new String(arr);
        return s;

    }
}
