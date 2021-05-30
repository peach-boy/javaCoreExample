package com.wxt.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Auther: ThomasWu
 * @Date: 2021/5/30 10:11
 * @Description:java序列化
 */
public class Test {

    public static void main(String[] args) throws Exception {
       //序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\user.obj"));
        oos.writeObject(new User("curry", "30", "golden states"));


        //反序列化
        ObjectInputStream ois= new ObjectInputStream(new FileInputStream("E:\\user.obj"));
        User newUser = (User) ois.readObject();

        System.out.println(newUser);
    }
}
