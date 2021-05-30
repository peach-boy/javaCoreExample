package com.wxt.externalizable;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Auther: ThomasWu
 * @Date: 2021/5/30 11:52
 * @Description:
 */
public class Test2 {

    public static void main(String[] args) throws Exception {
        //序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\user2.obj"));
        oos.writeObject(new User2("curry", "30", "golden states"));


        //反序列化
        ObjectInputStream ois= new ObjectInputStream(new FileInputStream("E:\\user2.obj"));
        User2 newUser = (User2) ois.readObject();

        System.out.println(newUser);
    }
}


