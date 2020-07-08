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
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.gzzz.utils.DBUtils.simpleDateFormat;
import static com.gzzz.utils.LogUtils.logger;

/**
 * CarSystem Main
 * @author GZZZ
 * @version 1.0.0
 */
public class CarSystem {
    private Scanner sc = new Scanner(System.in);
    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private User user = null;

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
        updateUserInfo();
        startLoginInMenu();
        sc = new Scanner(System.in);
        String startSelector = sc.next();
        switch (startSelector) {
            case "1": updatedCarsDisplay();break;
            case "2": runFind();break;
            case "3": depositCash();break;
            case "4": System.exit(0);break;
        }
        run();
    }

    public void runAsAdmin() {
        updateUserInfo();
        startAdminMenu();
        sc = new Scanner(System.in);
        String startSelector = sc.next();
        switch (startSelector) {
            case "1": updatedCarsDisplay();break;
            case "2": runFind();break;
            case "3": depositCash();break;
            case "4": runAdmin();
            case "5": System.exit(0);break;
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

    public void runAdmin() {
        adminStartMenu();
        sc = new Scanner(System.in);
        String fSelector = sc.next();
        switch (fSelector) {
            case "1": addBrand();break;
            case "2": runModelAdmin();break;
            case "3": addCar();break;
            case "4": run();break;
        }
        runAdmin();
    }

    public void runModelAdmin() {
        adminCarMenu();
        sc = new Scanner(System.in);
        String fSelector = sc.next();
        switch (fSelector) {
            case "1": addModel();break;
            case "2": deleteModel();break;
            case "3": runAdmin();break;
        }
        runModelAdmin();
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
            user = currentUser;
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
                System.out.println(username + "用户注册成功，请记住你的密码: " + password);
            } else {
                logger.error("注册时发生系统故障！");
                System.out.println(username + "由于系统故障，注册失败，请稍后重试！");
            }
        }
        await();
        run();
    }

    public void updateUserInfo() {
        user = UserDAO.getUser(user.getUsername());
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
        System.out.println("当前用户: " + user.getUsername());
        System.out.println("当前余额: " + user.getBalance());
        System.out.println("----------------");
        System.out.println("1.最新二手车信息");
        System.out.println("2.搜索车辆");
        System.out.println("3.存入资金");
        System.out.println("4.退出系统");
        System.out.print("请输入要执行的方法代号: ");
    }

    public void startAdminMenu() {
        System.out.println();
        System.out.println("--------Java二手车交易系统--------");
        System.out.println("当前用户: " + user.getUsername());
        System.out.println("当前余额: " + user.getBalance());
        System.out.println("----------------");
        System.out.println("1.最新二手车信息");
        System.out.println("2.搜索车辆");
        System.out.println("3.存入资金");
        System.out.println("4.后台管理");
        System.out.println("5.退出系统");
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

    public void adminStartMenu() {
        System.out.println();
        System.out.println("--------管理员面板--------");
        System.out.println("1.品牌管理");
        System.out.println("2.车型管理");
        System.out.println("3.发布车辆信息");
        System.out.println("4.返回主菜单");
        System.out.print("请输入要执行的方法代号: ");
    }

    public void adminCarMenu() {
        System.out.println();
        System.out.println("--------车型管理面板--------");
        System.out.println("1.添加车型");
        System.out.println("2.删除车型");
        System.out.println("3.返回管理员面板");
        System.out.print("请输入要执行的方法代号: ");
    }

    public void depositCash() {
        System.out.println();
        System.out.println("--------用户存款--------");
        System.out.print("请输入存款数额: ");
        String received = sc.next();
        int deposit = (int)(Double.parseDouble(received));
        if (deposit < 0) {
            deposit = 0;
        }
        System.out.print("是否确认存入账户" + deposit + "元(Y-添加数据/任意键-返回):");
        sc = new Scanner(System.in);
        String rSelector = sc.nextLine();
        if ("y".equalsIgnoreCase(rSelector)) {
            UserDAO.updateBalance(user.getUsername(), deposit);
            System.out.println("款项存入成功！");
        } else {
            System.out.println("款项存入失败！");
        }
        await();
    }

    public void updatedCarsDisplay() {
        CarsDisplay(CarDAO.listUpdatedCars());
    }

    public void CarsDisplay(List<Car> cars) {
        System.out.println();
        System.out.println("--------二手车信息--------");
        System.out.println("序号\t品牌\t车型\t总里程\t价格\t\t发布时间");
        int id = 1;
        if (!cars.isEmpty()) {
            for (Car car: cars) {
                System.out.println(id++ + "\t" + BrandDAO.getBrand(car.getBrand_id()).getBrand_name() + "\t" + ModelDAO.getModel(car.getModel_id()).getModel_name()
                + "\t" + decimalFormat.format(car.getMilage()/10000.0) + "万" + "\t" + decimalFormat.format(car.getPrice()/10000.0) + "万" + "\t"
                + simpleDateFormat.format(car.getPublish_time()));
            }
            int selected;
            do {
                System.out.println();
                System.out.print("请输入你要查看的二手车序号: ");
                selected = Integer.parseInt(sc.next())-1;
                if (cars.size() > selected && selected > -1) {
                    carDescription(cars.get(selected));
                } else {
                    logger.error("输入不符合规则的数据！输入的数据：" + selected);
                    System.out.println("请输入正确的二手车序号！");
                }
            } while(cars.size() <= selected || selected <= -1);
        } else {
            logger.error("二手车数量不足！");
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
                + decimalFormat.format(car.getPrice()/10000.0) + "万" + "\t" + simpleDateFormat.format(car.getIssue_time())  + "\t" + simpleDateFormat.format(car.getPublish_time()));
        carPurchase(car);
    }

    public void carPurchase(Car car) {
        if (is_login) {
            System.out.println("1.余额购买");
            System.out.println("2.返回主菜单");
            System.out.print("请输入要执行的方法代号: ");
            String pSelector = sc.next();
            if ("1".equals(pSelector)) {
                System.out.println();
                if (car.getPrice() > user.getBalance()) {
                    System.out.println("余额不足！请尽快存入相应款项！");
                } else if (CarDAO.updateSoldCar(car.getCar_id()) == 1) {
                    UserDAO.updateBalance(user.getUsername(), -car.getPrice());
                    System.out.println("购买成功！已从您的余额中扣除相应款项: " + car.getPrice());
                } else {
                    logger.error("购买时发生系统故障！");
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
            int selected;
            do {
                System.out.println();
                System.out.print("请输入你要查看的二手车品牌序号: ");
                selected = Integer.parseInt(sc.next())-1;
                if (brands.size() > selected && selected > -1) {
                    return brands.get(selected);
                } else {
                    logger.error("输入不符合规则的数据！输入的数据：" + selected);
                    System.out.println("请输入正确的二手车品牌序号！");
                }
            } while(brands.size() <= selected || selected <= -1);
            return brands.get(Integer.parseInt(sc.next())-1);
        } else {
            logger.error("二手车数量不足！");
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
            int selected;
            do {
                System.out.println();
                System.out.print("请输入你要查看的二手车车型序号: ");
                selected = Integer.parseInt(sc.next())-1;
                if (models.size() > selected && selected > -1) {
                    return models.get(selected);
                } else {
                    logger.error("输入不符合规则的数据！输入的数据：" + selected);
                    System.out.println("请输入正确的二手车车型序号！");
                }
            } while(models.size() <= selected || selected <= -1);
            return models.get(Integer.parseInt(sc.next())-1);
        } else {
            logger.error("二手车数量不足！");
            System.out.println("暂无二手车车型信息！请稍后再试！");
            await();
            run();
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
        System.out.print("请输入要搜索的起始年份: ");
        int start_year = Integer.parseInt(sc.next());
        int start_month;
        do {
            System.out.print("请输入要搜索的起始月份: ");
            start_month = Integer.parseInt(sc.next());
            if (1>start_month || start_month>12) {
                logger.error("输入不符合规则的数据！输入的数据：" + start_month);
                System.out.println("请输入正确的月份区间(1-12)！");
            }
        } while (1>start_month || start_month>12);
        System.out.println("----------------");
        System.out.print("请输入要搜索的结束年份: ");
        int end_year = Integer.parseInt(sc.next());
        int end_month;
        do {
            System.out.print("请输入要搜索的结束月份: ");
            end_month = Integer.parseInt(sc.next());
            if (1>end_month || end_month>12) {
                logger.error("输入不符合规则的数据！输入的数据：" + end_month);
                System.out.println("请输入正确的月份区间(1-12)！");
            }
        } while (1>end_month || end_month>12);
        if ((start_year > end_year) || (start_year==end_year && start_month>=end_month)) {
            System.out.println();
            logger.error("输入不符合规则的数据！输入的数据：" + start_year + " " +start_month + " " + end_year + " " + end_month);
            System.out.println("请确认起始时间早于结束时间！");
            await();
            return;
        }
        String start_time = start_year + "-" + start_month + "-" + "01";
        String end_time = end_year + "-" + end_month + "-" + DateUtils.monthOfYear(end_year, end_month);
        CarsDisplay(CarDAO.listCarsByTime(start_time, end_time));
    }

    public void addBrand() {
        System.out.println();
        System.out.println("--------添加二手车品牌--------");
        System.out.print("请输入二手车品牌名: ");
        String brand_name = sc.next();
        sc = new Scanner(System.in);
        System.out.print("请输入二手车品牌备注: ");
        String remark = sc.nextLine();
        int brand_id = 1001;
        if (!BrandDAO.listBrands().isEmpty()) {
            brand_id = BrandDAO.updatedBrandId()+1;
        }
        System.out.println("即将添加的数据:[品牌编号:" + brand_id + ", 品牌名:" + brand_name + ", 备注:" + remark + "]");
        System.out.print("是否确认添加品牌数据(Y-添加数据/任意键-返回):");
        sc = new Scanner(System.in);
        String rSelector = sc.nextLine();
        if ("y".equalsIgnoreCase(rSelector)) {
            BrandDAO.insertBrand(brand_id, brand_name, remark);
            System.out.println("品牌数据添加成功！");
        } else {
            System.out.println("品牌数据未添加！");
        }
        await();
    }

    public void addModel() {
        Brand brand = findBrands();
        System.out.println();
        System.out.println("--------添加二手车车型--------");
        System.out.print("请输入二手车车型名: ");
        String model_name = sc.next();
        sc = new Scanner(System.in);
        System.out.print("请输入二手车车型备注: ");
        String remark = sc.nextLine();
        int model_id = Integer.parseInt(brand.getBrand_id() + "01");
        if (!ModelDAO.listModelsByBrand(brand.getBrand_id()).isEmpty()) {
            model_id = ModelDAO.updatedModelId(brand.getBrand_id())+1;
        }
        System.out.println("即将添加的数据:[车型编号:" + model_id + ", 车型名:" + model_name + ", 备注:" + remark + "]");
        System.out.print("是否确认添加车型数据(Y-添加数据/任意键-返回):");
        sc = new Scanner(System.in);
        String rSelector = sc.nextLine();
        if ("y".equalsIgnoreCase(rSelector)) {
            ModelDAO.insertModel(model_id, brand.getBrand_id(), model_name, remark);
            System.out.println("车型数据添加成功！");
        } else {
            System.out.println("车型数据未添加！");
        }
        await();
    }

    public void deleteModel() {
        Model model = findModels(findBrands());
        System.out.println();
        System.out.println("--------删除二手车车型--------");
        System.out.println("即将删除的数据:[车型编号:" + model.getModel_id() + ", 车型名:" + model.getModel_name() + ", 备注:" + model.getRemark() + "]");
        System.out.print("是否确认删除车型数据(Y-添加数据/任意键-返回):");
        sc = new Scanner(System.in);
        String rSelector = sc.nextLine();
        if ("y".equalsIgnoreCase(rSelector)) {
            ModelDAO.removeModel(model.getModel_id());
            System.out.println("车型数据删除成功！");
        } else {
            System.out.println("车型数据未删除！");
        }
        await();
    }

    public void addCar() {
        Brand brand = findBrands();
        Model model = findModels(brand);
        System.out.println();
        System.out.println("--------添加二手车售卖--------");
        System.out.print("请输入二手车排量: ");
        String exhaust = sc.next();
        System.out.print("请输入二手车变速器类型(自动/手动): ");
        String clutch = sc.next();
        System.out.print("请输入二手车总里程(m): ");
        int milage = Integer.parseInt(sc.next());
        System.out.print("请输入二手车价格(rmb): ");
        int price = Integer.parseInt(sc.next());
        String issue_time;
        String pattern = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";
        do {
            System.out.print("请输入二手车上牌时间(yyyy-MM-dd): ");
            issue_time = sc.next();
            if (!Pattern.matches(pattern, issue_time)) {
                logger.error("输入不符合规则的数据！输入的数据：" + issue_time);
                System.out.println("请确认上牌时间数据的格式(2020-05-25)!");
            }
        } while (!Pattern.matches(pattern, issue_time));
        System.out.println("即将添加的数据:[品牌:" + brand.getBrand_name() + "车型:" + model.getModel_name() + ", 排量:" + exhaust
                + ", 里程:" + milage + ", 价格:" + price + ", 离合器类型:" + clutch + "]");
        System.out.print("是否确认添加二手车(Y-添加数据/任意键-返回):");
        sc = new Scanner(System.in);
        String rSelector = sc.nextLine();
        if ("y".equalsIgnoreCase(rSelector)) {
            CarDAO.insertCar(brand.getBrand_id(), model.getModel_id(), exhaust, milage, price, clutch, issue_time);
            System.out.println("二手车数据添加成功！");
        } else {
            System.out.println("二手车数据未添加！");
        }
        await();
    }
}
