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
        System.out.print("?????????????????????...");
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public void verifyAccount() {
        System.out.println();
        System.out.print("????????????????????????: ");
        String username = sc.next();
        System.out.print("??????????????????????????????");
        String password = sc.next();
        User currentUser = UserDAO.getUser(username);
        System.out.println();
        if (currentUser!=null && password.equals(currentUser.getPassword())) {
            user = currentUser;
            is_login = true;
            is_admin = currentUser.isIs_admin();
            System.out.println("???????????????");

        } else {
            logger.error("??????????????????????????????????????????" + "????????????????????????:" + username);
            System.out.println("??????????????????????????????????????????");
        }
        await();
    }

    public void registerAccount() {
        System.out.println();
        System.out.print("??????????????????????????????: ");
        String username = sc.next();
        String password;
        if (UserDAO.getUser(username) != null) {
            logger.error("?????????????????????????????????" + "????????????????????????:" + username);
            System.out.println("?????????????????????????????????");
        } else {
            do {
                System.out.print("???????????????????????????: ");
                password = sc.next();
                System.out.print("?????????????????????????????????: ");
            } while(!password.equals(sc.next()));
            System.out.println();
            if (UserDAO.registerUser(username, password) == 1) {
                System.out.println(username + "??????????????????????????????????????????: " + password);
            } else {
                logger.error("??????????????????????????????");
                System.out.println(username + "??????????????????????????????????????????????????????");
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
        System.out.println("--------Java?????????????????????--------");
        System.out.println("1.????????????");
        System.out.println("2.????????????");
        System.out.println("3.?????????????????????");
        System.out.println("4.????????????");
        System.out.println("5.????????????");
        System.out.print("?????????????????????????????????: ");
    }

    public void startLoginInMenu() {
        System.out.println();
        System.out.println("--------Java?????????????????????--------");
        System.out.println("????????????: " + user.getUsername());
        System.out.println("????????????: " + user.getBalance());
        System.out.println("----------------");
        System.out.println("1.?????????????????????");
        System.out.println("2.????????????");
        System.out.println("3.????????????");
        System.out.println("4.????????????");
        System.out.print("?????????????????????????????????: ");
    }

    public void startAdminMenu() {
        System.out.println();
        System.out.println("--------Java?????????????????????--------");
        System.out.println("????????????: " + user.getUsername());
        System.out.println("????????????: " + user.getBalance());
        System.out.println("----------------");
        System.out.println("1.?????????????????????");
        System.out.println("2.????????????");
        System.out.println("3.????????????");
        System.out.println("4.????????????");
        System.out.println("5.????????????");
        System.out.print("?????????????????????????????????: ");
    }

    public void findStartMenu() {
        System.out.println();
        System.out.println("--------???????????????--------");
        System.out.println("1.??????????????????");
        System.out.println("2.??????????????????");
        System.out.println("3.????????????????????????");
        System.out.println("4.???????????????");
        System.out.print("?????????????????????????????????: ");
    }

    public void findPriceMenu() {
        System.out.println();
        System.out.println("1. 10?????????");
        System.out.println("2. 10-25???");
        System.out.println("3. 25-40???");
        System.out.println("4. 40?????????");
        System.out.print("?????????????????????????????????: ");
    }

    public void adminStartMenu() {
        System.out.println();
        System.out.println("--------???????????????--------");
        System.out.println("1.????????????");
        System.out.println("2.????????????");
        System.out.println("3.??????????????????");
        System.out.println("4.???????????????");
        System.out.print("?????????????????????????????????: ");
    }

    public void adminCarMenu() {
        System.out.println();
        System.out.println("--------??????????????????--------");
        System.out.println("1.????????????");
        System.out.println("2.????????????");
        System.out.println("3.?????????????????????");
        System.out.print("?????????????????????????????????: ");
    }

    public void depositCash() {
        System.out.println();
        System.out.println("--------????????????--------");
        System.out.print("?????????????????????: ");
        String received = sc.next();
        int deposit = (int)(Double.parseDouble(received));
        if (deposit < 0) {
            deposit = 0;
        }
        System.out.print("????????????????????????" + deposit + "???(Y-????????????/?????????-??????):");
        sc = new Scanner(System.in);
        String rSelector = sc.nextLine();
        if ("y".equalsIgnoreCase(rSelector)) {
            UserDAO.updateBalance(user.getUsername(), deposit);
            System.out.println("?????????????????????");
        } else {
            System.out.println("?????????????????????");
        }
        await();
    }

    public void updatedCarsDisplay() {
        CarsDisplay(CarDAO.listUpdatedCars());
    }

    public void CarsDisplay(List<Car> cars) {
        System.out.println();
        System.out.println("--------???????????????--------");
        System.out.println("??????\t??????\t??????\t?????????\t??????\t\t????????????");
        int id = 1;
        if (!cars.isEmpty()) {
            for (Car car: cars) {
                System.out.println(id++ + "\t" + BrandDAO.getBrand(car.getBrand_id()).getBrand_name() + "\t" + ModelDAO.getModel(car.getModel_id()).getModel_name()
                + "\t" + decimalFormat.format(car.getMilage()/10000.0) + "???" + "\t" + decimalFormat.format(car.getPrice()/10000.0) + "???" + "\t"
                + simpleDateFormat.format(car.getPublish_time()));
            }
            int selected;
            do {
                System.out.println();
                System.out.print("???????????????????????????????????????: ");
                selected = Integer.parseInt(sc.next())-1;
                if (cars.size() > selected && selected > -1) {
                    carDescription(cars.get(selected));
                } else {
                    logger.error("???????????????????????????????????????????????????" + selected);
                    System.out.println("????????????????????????????????????");
                }
            } while(cars.size() <= selected || selected <= -1);
        } else {
            logger.error("????????????????????????");
            System.out.println("??????????????????????????????????????????");
            await();
        }
    }

    public void carDescription(Car car) {
        System.out.println();
        System.out.println("--------?????????????????????--------");
        System.out.println("??????\t??????\t??????\t\t?????????\t???????????????\t??????\t\t????????????\t\t????????????");
        System.out.println(BrandDAO.getBrand(car.getBrand_id()).getBrand_name() + "\t" + ModelDAO.getModel(car.getModel_id()).getModel_name() + "\t"
                + car.getExhaust() + "\t" + decimalFormat.format(car.getMilage()/10000.0) + "???" + "\t" + car.getClutch() + "\t\t\t"
                + decimalFormat.format(car.getPrice()/10000.0) + "???" + "\t" + simpleDateFormat.format(car.getIssue_time())  + "\t" + simpleDateFormat.format(car.getPublish_time()));
        carPurchase(car);
    }

    public void carPurchase(Car car) {
        if (is_login) {
            System.out.println("1.????????????");
            System.out.println("2.???????????????");
            System.out.print("?????????????????????????????????: ");
            String pSelector = sc.next();
            if ("1".equals(pSelector)) {
                System.out.println();
                if (car.getPrice() > user.getBalance()) {
                    System.out.println("?????????????????????????????????????????????");
                } else if (CarDAO.updateSoldCar(car.getCar_id()) == 1) {
                    UserDAO.updateBalance(user.getUsername(), -car.getPrice());
                    System.out.println("??????????????????????????????????????????????????????: " + car.getPrice());
                } else {
                    logger.error("??????????????????????????????");
                    System.out.println("????????????????????????????????????");
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
        System.out.println("--------?????????????????????--------");
        List<Brand> brands = BrandDAO.listBrands();
        int id = 1;
        if (!brands.isEmpty()) {
            for (Brand brand: brands) {
                System.out.println(id++ +"."+ brand.getBrand_name());
            }
            int selected;
            do {
                System.out.println();
                System.out.print("?????????????????????????????????????????????: ");
                selected = Integer.parseInt(sc.next())-1;
                if (brands.size() > selected && selected > -1) {
                    return brands.get(selected);
                } else {
                    logger.error("???????????????????????????????????????????????????" + selected);
                    System.out.println("??????????????????????????????????????????");
                }
            } while(brands.size() <= selected || selected <= -1);
            return brands.get(Integer.parseInt(sc.next())-1);
        } else {
            logger.error("????????????????????????");
            System.out.println("????????????????????????????????????????????????");
            await();
        }
        return null;
    }

    public Model findModels(Brand brand) {
        System.out.println();
        System.out.println("--------?????????????????????--------");
        List<Model> models = ModelDAO.listModelsByBrand(brand.getBrand_id());
        int id = 1;
        if (!models.isEmpty()) {
            for (Model model: models) {
                System.out.println(id++ + "." + model.getModel_name());
            }
            int selected;
            do {
                System.out.println();
                System.out.print("?????????????????????????????????????????????: ");
                selected = Integer.parseInt(sc.next())-1;
                if (models.size() > selected && selected > -1) {
                    return models.get(selected);
                } else {
                    logger.error("???????????????????????????????????????????????????" + selected);
                    System.out.println("??????????????????????????????????????????");
                }
            } while(models.size() <= selected || selected <= -1);
            return models.get(Integer.parseInt(sc.next())-1);
        } else {
            logger.error("????????????????????????");
            System.out.println("????????????????????????????????????????????????");
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
        System.out.print("?????????????????????????????????: ");
        int start_year = Integer.parseInt(sc.next());
        int start_month;
        do {
            System.out.print("?????????????????????????????????: ");
            start_month = Integer.parseInt(sc.next());
            if (1>start_month || start_month>12) {
                logger.error("???????????????????????????????????????????????????" + start_month);
                System.out.println("??????????????????????????????(1-12)???");
            }
        } while (1>start_month || start_month>12);
        System.out.println("----------------");
        System.out.print("?????????????????????????????????: ");
        int end_year = Integer.parseInt(sc.next());
        int end_month;
        do {
            System.out.print("?????????????????????????????????: ");
            end_month = Integer.parseInt(sc.next());
            if (1>end_month || end_month>12) {
                logger.error("???????????????????????????????????????????????????" + end_month);
                System.out.println("??????????????????????????????(1-12)???");
            }
        } while (1>end_month || end_month>12);
        if ((start_year > end_year) || (start_year==end_year && start_month>=end_month)) {
            System.out.println();
            logger.error("???????????????????????????????????????????????????" + start_year + " " +start_month + " " + end_year + " " + end_month);
            System.out.println("??????????????????????????????????????????");
            await();
            return;
        }
        String start_time = start_year + "-" + start_month + "-" + "01";
        String end_time = end_year + "-" + end_month + "-" + DateUtils.monthOfYear(end_year, end_month);
        CarsDisplay(CarDAO.listCarsByTime(start_time, end_time));
    }

    public void addBrand() {
        System.out.println();
        System.out.println("--------?????????????????????--------");
        System.out.print("???????????????????????????: ");
        String brand_name = sc.next();
        sc = new Scanner(System.in);
        System.out.print("??????????????????????????????: ");
        String remark = sc.nextLine();
        int brand_id = 1001;
        if (!BrandDAO.listBrands().isEmpty()) {
            brand_id = BrandDAO.updatedBrandId()+1;
        }
        System.out.println("?????????????????????:[????????????:" + brand_id + ", ?????????:" + brand_name + ", ??????:" + remark + "]");
        System.out.print("??????????????????????????????(Y-????????????/?????????-??????):");
        sc = new Scanner(System.in);
        String rSelector = sc.nextLine();
        if ("y".equalsIgnoreCase(rSelector)) {
            BrandDAO.insertBrand(brand_id, brand_name, remark);
            System.out.println("???????????????????????????");
        } else {
            System.out.println("????????????????????????");
        }
        await();
    }

    public void addModel() {
        Brand brand = findBrands();
        System.out.println();
        System.out.println("--------?????????????????????--------");
        System.out.print("???????????????????????????: ");
        String model_name = sc.next();
        sc = new Scanner(System.in);
        System.out.print("??????????????????????????????: ");
        String remark = sc.nextLine();
        int model_id = Integer.parseInt(brand.getBrand_id() + "01");
        if (!ModelDAO.listModelsByBrand(brand.getBrand_id()).isEmpty()) {
            model_id = ModelDAO.updatedModelId(brand.getBrand_id())+1;
        }
        System.out.println("?????????????????????:[????????????:" + model_id + ", ?????????:" + model_name + ", ??????:" + remark + "]");
        System.out.print("??????????????????????????????(Y-????????????/?????????-??????):");
        sc = new Scanner(System.in);
        String rSelector = sc.nextLine();
        if ("y".equalsIgnoreCase(rSelector)) {
            ModelDAO.insertModel(model_id, brand.getBrand_id(), model_name, remark);
            System.out.println("???????????????????????????");
        } else {
            System.out.println("????????????????????????");
        }
        await();
    }

    public void deleteModel() {
        Model model = findModels(findBrands());
        System.out.println();
        System.out.println("--------?????????????????????--------");
        System.out.println("?????????????????????:[????????????:" + model.getModel_id() + ", ?????????:" + model.getModel_name() + ", ??????:" + model.getRemark() + "]");
        System.out.print("??????????????????????????????(Y-????????????/?????????-??????):");
        sc = new Scanner(System.in);
        String rSelector = sc.nextLine();
        if ("y".equalsIgnoreCase(rSelector)) {
            ModelDAO.removeModel(model.getModel_id());
            System.out.println("???????????????????????????");
        } else {
            System.out.println("????????????????????????");
        }
        await();
    }

    public void addCar() {
        Brand brand = findBrands();
        Model model = findModels(brand);
        System.out.println();
        System.out.println("--------?????????????????????--------");
        System.out.print("????????????????????????: ");
        String exhaust = sc.next();
        System.out.print("?????????????????????????????????(??????/??????): ");
        String clutch = sc.next();
        System.out.print("???????????????????????????(m): ");
        int milage = Integer.parseInt(sc.next());
        System.out.print("????????????????????????(rmb): ");
        int price = Integer.parseInt(sc.next());
        String issue_time;
        String pattern = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";
        do {
            System.out.print("??????????????????????????????(yyyy-MM-dd): ");
            issue_time = sc.next();
            if (!Pattern.matches(pattern, issue_time)) {
                logger.error("???????????????????????????????????????????????????" + issue_time);
                System.out.println("????????????????????????????????????(2020-05-25)!");
            }
        } while (!Pattern.matches(pattern, issue_time));
        System.out.println("?????????????????????:[??????:" + brand.getBrand_name() + "??????:" + model.getModel_name() + ", ??????:" + exhaust
                + ", ??????:" + milage + ", ??????:" + price + ", ???????????????:" + clutch + "]");
        System.out.print("???????????????????????????(Y-????????????/?????????-??????):");
        sc = new Scanner(System.in);
        String rSelector = sc.nextLine();
        if ("y".equalsIgnoreCase(rSelector)) {
            CarDAO.insertCar(brand.getBrand_id(), model.getModel_id(), exhaust, milage, price, clutch, issue_time);
            System.out.println("??????????????????????????????");
        } else {
            System.out.println("???????????????????????????");
        }
        await();
    }
}
