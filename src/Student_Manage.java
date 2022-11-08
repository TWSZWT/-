import java.util.ArrayList;
import java.util.Scanner;

public class Student_Manage {
    public static void star_StudentManage() {
        //定义一个集合，存放学生对象
        ArrayList<Student> list = new ArrayList<>();

        while (true) {
        //初始菜单：
        System.out.println("-------------欢迎来到黑马学生管理系统----------------");
        System.out.println("1：添加学生");
        System.out.println("2：删除学生");
        System.out.println("3：修改学生");
        System.out.println("4：查询学生");
        System.out.println("5：退出");
        System.out.println("请输入您的选择:");

        //定义choose接收用户选择
        Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case "1": {
                    add(list);
                    break;
                }
                case "2": {
                    delete(list);
                    break;
                }
                case "3": {
                    change(list);
                    break;
                }
                case "4": {
                    show(list);
                    break;
                }
                case "5": {
                    System.out.println("退出成功");
                    System.exit(0);
                }
                default:
                    System.out.println("无此选项，请重新输入");
            }
        }

    }


    //添加功能：
    //​ 键盘录入每一个学生信息并添加，需要满足以下要求：
    //id唯一
    public static void add(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        //输入id
        System.out.println("请输入该学生的id：");
        while (true) {
            String id = sc.next();

            //判断id的唯一性
            if(id_Index(list,id) >= 0){
                //已存在，不可添加,提示重新输入
                System.out.println("该id已存在，请重新添加");
            }else {
                //未存在，可添加
                //创建一个学生对象添加信息
                Student stu = new Student();
                stu.setId(id);

                System.out.println("请输入该学生的姓名:");
                String name =sc.next();
                stu.setName(name);

                System.out.println("请输入该学生的年龄:");
                int age = sc.nextInt();
                stu.setAge(age);

                System.out.println("请输入该学生的地址:");
                String address = sc.next();
                stu.setAddress(address);

                //将学生对象存入集合中
                list.add(stu);

                System.out.println("添加成功");
                break;
            }
        }

    }

    //删除功能：
    public static void delete(ArrayList<Student> list){
        //键盘录入要删除的学生id
        Scanner sc = new Scanner(System.in);
            System.out.println("请输入要删除的学生id：");
            String id = sc.next();

            //判断id的唯一性,存在返回相应index，不存在返回-1
            int index = id_Index(list,id);
            if(index >= 0){
                //id存在，删除该学生
                list.remove(index);
                System.out.println("删除成功");
            }else {
                //id不存在，并回到初始菜单
                System.out.println("该学生id不存在，返回初始菜单");
            }

    }

    //修改功能：
    //​ 键盘录入要修改的学生id，需要满足以下要求
    //id存在，继续录入其他信息
    //id不存在，需要提示不存在，并回到初始菜单
    public static void change(ArrayList<Student> list){
        //键盘录入要修改的学生id
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的学生id");
        String id = sc.next();

        //判断id唯一性
        int index = id_Index(list,id);
        if( index >= 0){
            //id存在，继续录入其他信息
            list.get(index).setId(id);

            System.out.println("请输入要修改的名字");
            String new_name = sc.next();
            list.get(index).setName(new_name);

            System.out.println("请输入要修改的年龄");
            int new_age = sc.nextInt();
            list.get(index).setAge(new_age);

            System.out.println("请输入要修改的地址");
            String new_address = sc.next();
            list.get(index).setAddress(new_address);

            System.out.println("修改成功");
        }else {
            //id不存在
            System.out.println("id不存在，回到初始菜单");
        }
    }


    //查询功能：
    //打印所有的学生信息
    public static void show(ArrayList<Student> list){
        //如果没有学生信息，提示：当前无学生信息，请添加后再查询
        if (list.size() == 0){
            System.out.println("当前无学生信息，请添加后再查询");
        }else {
            //存在学生信息
            //表头信息
            System.out.println("id\t\t" + "姓名\t" + "年龄\t" + "家庭地址\t");
            //遍历输出学生对象信息
            for (int i = 0; i < list.size(); i++) {
                Student stu = list.get(i);
                System.out.println(stu.getId() + "\t\t" +stu.getName() + "\t" + stu.getAge() + "\t" + stu.getAddress() +"\t");
            }
        }
    }


    //查询是否含有此id，存在返回所处index，不存在返回-1
    public static int id_Index(ArrayList<Student> list, String id) {
    for (int i = 0; i < list.size(); i++) {
        //判断id是否相同
        boolean flag = list.get(i).getId().equals(id);
        if (flag) {
            //id存在
            return i;
        }
    }
    //id不存在
    return -1;

}
}


