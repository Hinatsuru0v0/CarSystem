package com.gzzz.client;

import com.gzzz.dao.BrandDAO;
import com.gzzz.dao.CarDAO;
import com.gzzz.dao.ModelDAO;
import com.gzzz.dao.UserDAO;
import com.gzzz.entity.Brand;
import com.gzzz.entity.Car;
import com.gzzz.entity.Model;
import com.gzzz.entity.User;
import com.gzzz.utils.DateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
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
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

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
            case "1": verifyAccount();break;
            case "2": registerAccount();break;
            case "3": updatedCarsDisplay();break;
            case "4": runFind();break;
            case "5": System.exit(0);break;
        }
        run();
    }

    public void runAsUser() {
        startLoginInMenu();
        sc = new Scanner(System.in);
        String startSelector = sc.next();
        switch (startSelector) {
            case "1": updatedCarsDisplay();break;
            case "2": runFind();break;
            case "3": System.exit(0);break;
        }
        run();
    }

    public void runAsAdmin() {
        startAdminMenu();
        sc = new Scanner(System.in);
        String startSelector = sc.next();
        switch (startSelector) {
            case "1": updatedCarsDisplay();break;
            case "2": runFind();break;
            case "4": System.exit(0);break;
        }
        run();
    }

    public void runFind() {
        findStartMenu();
        sc = new Scanner(System.in);
        String findSelector = sc.next();
        switch (findSelector) {
            case "1": findCarsByBrand(findModels(findBrands()));break;
            case "2": findCarsByPrice();break;
            case "3": findCarsByTime();break;
            case "4": run();break;
        }
        runFind();
    }

    public void await() {
        System.out.print("按下任意键继续...");
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

    public void findStartMenu() {
        System.out.println();
        System.out.println("--------二手车搜索--------");
        System.out.println("1.根据品牌搜索");
        System.out.println("2.根据价格搜索");
        System.out.println("3.根据上牌日期搜索");
        System.out.println("4.返回主菜单");
        System.out.print("请输入要执行的方法代号: ");
    }

    public void findPriceMenu() {
        System.out.println();
        System.out.println("1. 10万以下");
        System.out.println("2. 10-25万");
        System.out.println("3. 25-40万");
        System.out.println("4. 40万以上");
        System.out.print("请输入要选择的价格区间: ");
    }

    public void updatedCarsDisplay() {
        CarsDisplay(CarDAO.listUpdatedCars());
    }

    public void CarsDisplay(List<Car> cars) {
        System.out.println();
        System.out.println("--------最新二手车信息--------");
        System.out.println("序号\t品牌\t车型\t总里程\t价格\t\t发布时间");
        int id = 1;
        if (!cars.isEmpty()) {
            for (Car car: cars) {
                System.out.println(id++ + "\t" + BrandDAO.getBrand(car.getBrand_id()).getBrand_name() + "\t" + ModelDAO.getModel(car.getModel_id()).getModel_name()
                + "\t" + decimalFormat.format(car.getMilage()/10000.0) + "万" + "\t" + decimalFormat.format(car.getPrice()/10000.0) + "万" + "\t"
                + simpleDateFormat.format(car.getPublish_time()));
            }
            System.out.print("请输入你要查看的二手车序号: ");
            carDescription(cars.get(Integer.parseInt(sc.next())-1));
        } else {
            System.out.println("暂无二手车信息！请稍后再试！");
            await();
        }
    }

    public void carDescription(Car car) {
        System.out.println();
        System.out.println("--------二手车详细信息--------");
        System.out.println("品牌\t车型\t排量\t\t总里程\t离合器类型\t价格\t\t上牌时间\t\t发布时间");
        System.out.println(BrandDAO.getBrand(car.getBrand_id()).getBrand_name() + "\t" + ModelDAO.getModel(car.getModel_id()).getModel_name() + "\t"
                + car.getExhaust() + "\t" + decimalFormat.format(car.getMilage()/10000.0) + "万" + "\t" + car.getClutch() + "\t\t\t"
                + decimalFormat.format(car.getPrice()/10000.0) + "万" + "\t" + simpleDateFormat.format(car.getIssue_time())  + "\t" + simpleDateFormat.format(car.getIssue_time()));
        carPurchase(car);
    }

    public void carPurchase(Car car) {
        if (is_login) {
            System.out.println("1.购买该二手车");
            System.out.println("2.返回主菜单");
            System.out.print("请输入要执行的方法代号: ");
            String pSelector = sc.next();
            if ("1".equals(pSelector)) {
                System.out.println();
                if (CarDAO.updateSoldCar(car.getCar_id()) == 1) {
                    System.out.println("购买成功！");
                } else {
                    System.out.println("购买失败，请稍后再试！！");
                }
            } else if ("2".equals(pSelector)) {
                run();
            } else {
                carDescription(car);
            }
        }
        await();
    }

    public Brand findBrands() {
        System.out.println();
        System.out.println("--------二手车品牌信息--------");
        List<Brand> brands = BrandDAO.listBrands();
        int id = 1;
        if (!brands.isEmpty()) {
            for (Brand brand: brands) {
                System.out.println(id++ +"."+ brand.getBrand_name());
            }
            System.out.print("请输入你要查看的二手车品牌: ");
            return brands.get(Integer.parseInt(sc.next())-1);
        } else {
            System.out.println("暂无二手车品牌信息！请稍后再试！");
            await();
        }
        return null;
    }

    public Model findModels(Brand brand) {
        System.out.println();
        System.out.println("--------二手车车型信息--------");
        List<Model> models = ModelDAO.listModelsByBrand(brand.getBrand_id());
        int id = 1;
        if (!models.isEmpty()) {
            for (Model model: models) {
                System.out.println(id++ + "." + model.getModel_name());
            }
            System.out.print("请输入你要查看的二手车车型: ");
            return models.get(Integer.parseInt(sc.next())-1);
        } else {
            System.out.println("暂无二手车车型信息！请稍后再试！");
            await();
        }
        return null;
    }

    public void findCarsByBrand(Model model) {
        System.out.println();
        CarsDisplay(CarDAO.listCarsByModel(model.getModel_id()));
    }

    public void findCarsByPrice() {
        findPriceMenu();
        sc = new Scanner(System.in);
        String priceSelector = sc.next();
        switch (priceSelector) {
            case "1": CarsDisplay(CarDAO.listCarsByPrice(0, 100000));break;
            case "2": CarsDisplay(CarDAO.listCarsByPrice(100000, 250000));break;
            case "3": CarsDisplay(CarDAO.listCarsByPrice(250000, 400000));break;
            case "4": CarsDisplay(CarDAO.listCarsByMaxPrice(400000));break;
        }
        runFind();
    }

    public void findCarsByTime() {
        System.out.println();
        System.out.print("请输入要搜索的起始年份:");
        String start_year = sc.next();
        System.out.print("请输入要搜索的起始月份:");
        String start_month = sc.next();
        System.out.println("----------------");
        System.out.print("请输入要搜索的结束年份:");
        String end_year = sc.next();
        System.out.print("请输入要搜索的结束月份:");
        String end_month = sc.next();
        String start_time = start_year + "-" + start_month + "-" + "01";
        String end_time = end_year + "-" + end_month + "-" + DateUtils.monthOfYear(Integer.parseInt(end_year), Integer.parseInt(end_month));
        CarsDisplay(CarDAO.listCarsByTime(start_time, end_time));

    }
}
