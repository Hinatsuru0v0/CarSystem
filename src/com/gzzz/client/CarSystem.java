package com.gzzz.client;

import com.gzzz.dao.CarDAO;
import com.gzzz.dao.UserDAO;
import com.gzzz.entity.Car;
import com.gzzz.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.gzzz.utils.DBUtils.simpleDateFormat;
import static com.gzzz.utils.LogUtils.logger;

/**
 * CarSystem Main
 * @author GZZZ
 * @version 1.0.0
 */
public class CarSystem {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    boolean is_login = false;
    boolean is_admin = false;

    public void run() {
        if (is_admin) {
            runAsAdmin();
        } else if (is_login) {
            runAsUser();
        } else {
            runAsGuest();
        }
    }

    public void runAsGuest() {
        startNoLoginMenu();
        sc = new Scanner(System.in);
        String startSelector = sc.next();
        switch (startSelector) {
            case "1" -> verifyAccount();
            case "2" -> registerAccount();
            case "5" -> System.exit(0);
        }
        run();
    }

    public void runAsUser() {
        startLoginInMenu();
        sc = new Scanner(System.in);
        String startSelector = sc.next();
        switch (startSelector) {
            case "3" -> System.exit(0);
        }
        run();
    }

    public void runAsAdmin() {
        startAdminMenu();
        sc = new Scanner(System.in);
        String startSelector = sc.next();
        switch (startSelector) {
            case "4" -> System.exit(0);
        }
        run();
    }

    public void await() {
        System.out.print("ENTER ANY KEY TO CONTINUE...");
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public void verifyAccount() {
        System.out.println();
        System.out.print("请输入你的用户名: ");
        String username = sc.next();
        System.out.print("请输入你的登陆密码：");
        String password = sc.next();
        User currentUser = UserDAO.getUser(username);
        System.out.println();
        if (currentUser!=null && password.equals(currentUser.getPassword())) {
            is_login = true;
            is_admin = currentUser.isIs_admin();
            System.out.println("登陆成功！");

        } else {
            logger.error("用户名或密码错误，登陆失败！" + "用户键入的用户名:" + username);
            System.out.println("用户名或密码错误，登陆失败！");
        }
        await();
    }

    public void registerAccount() {
        System.out.println();
        System.out.print("请输入要注册的用户名: ");
        String username = sc.next();
        String password;
        if (UserDAO.getUser(username) != null) {
            logger.error("用户名重复，注册失败！" + "用户键入的用户名:" + username);
            System.out.println("用户名重复，注册失败！");
        } else {
            do {
                System.out.print("请输入你的登陆密码: ");
                password = sc.next();
                System.out.print("请再次确认你的登陆密码: ");
            } while(!password.equals(sc.next()));
            System.out.println();
            if (UserDAO.registerUser(username, password) == 1) {
                System.out.println(username + "用户注册成功，请记住你的密码:" + password);
            } else {
                System.out.println(username + "由于系统故障，注册失败，请稍后重试！");
            }
        }
        await();
        run();
    }


    public void startNoLoginMenu() {
        System.out.println();
        System.out.println("--------Java二手车交易系统--------");
        System.out.println("1.登陆系统");
        System.out.println("2.注册账户");
        System.out.println("3.最新二手车信息");
        System.out.println("4.搜索车辆");
        System.out.println("5.退出系统");
        System.out.print("请输入要执行的方法代号: ");
    }

    public void startLoginInMenu() {
        System.out.println();
        System.out.println("--------Java二手车交易系统--------");
        System.out.println("1.最新二手车信息");
        System.out.println("2.搜索车辆");
        System.out.println("3.退出系统");
        System.out.print("请输入要执行的方法代号: ");
    }

    public void startAdminMenu() {
        System.out.println();
        System.out.println("--------Java二手车交易系统--------");
        System.out.println("1.最新二手车信息");
        System.out.println("2.搜索车辆");
        System.out.println("3.后台管理");
        System.out.println("4.退出系统");
        System.out.print("请输入要执行的方法代号: ");
    }

    public void updatedCarsDisplay() {
        System.out.println();
        System.out.println("--------最新二手车信息--------");
        System.out.println("品牌\t车型\t里程数\t价格\t发布时间");
        List<Car> cars = CarDAO.listUpdatedCars();
        
    }
}
