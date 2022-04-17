package com.example.homesc;
import java.util.ArrayList;

public class ServiceCategories {
    ArrayList<ServiceCategories> CategoryArrayList = new ArrayList<>();


    String category;

    String firstServiceName;
    String secondServiceName;
    String thirdServiceName;
    String fourthServiceName;

    double PriceOfFirstService;
    double PriceOfSecondService;
    double PriceOfThirdService;
    double PriceOfFourthService;

    public ServiceCategories(String category, String firstServiceName, String secondServiceName, String thirdServiceName, String fourthServiceName, double PriceOfFirstService, double PriceOfSecondService, double PriceOfThirdService, double PriceOfFourthService) {
        this.category = category;
        this.firstServiceName = firstServiceName;
        this.secondServiceName = secondServiceName;
        this.thirdServiceName = thirdServiceName;
        this.fourthServiceName = fourthServiceName;
        this.PriceOfFirstService = PriceOfFirstService;
        this.PriceOfSecondService = PriceOfSecondService;
        this.PriceOfThirdService = PriceOfThirdService;
        this.PriceOfFourthService = PriceOfFourthService;

    }


    public void addServiceCategoryToList() {
        String Category1 = "Electrical";
        String Category2 = "Home Cleaning";
        String Category3 = "Tutoring";
        String Category4 = "Home repair and Painting";
        String Category5 = "Computer Repair";
        String Category6 = "Plumbing";

        ServiceCategories s1 = new ServiceCategories(Category1, "Install Outlet", "Install Ceiling Fan", "Install Light Fixture", "Install Smoke Detector", 209.99, 129.99, 349.99, 69.99);
        ServiceCategories s2 = new ServiceCategories(Category2, "Post Moving Clean","3 bedrooms 2 bathrooms", "4 bedrooms 2 bathrooms", "5 bedrooms 3 bathrooms", 399.99, 129.99, 179.99, 229.99);
        ServiceCategories s3 = new ServiceCategories(Category3, "Learn Arabic", "Learn Calculus", "Learn C++", "Learn Organic Chemistry", 25, 30, 40, 30 );
        ServiceCategories s4 = new ServiceCategories(Category4, "Wood flooring", "Mount TV", "Garage Door Paint", "Chimney Repair", 2499.99, 149.99, 199.99,3499.99 );
        ServiceCategories s5 = new ServiceCategories(Category5, "Replace Screen", "Virus Removal", "Data Recovery", "Water Damage Repair", 149.99, 99.99, 69.99, 179.99 );
        //ServiceCategories s6 = new ServiceCategories(Category6, )


        CategoryArrayList.add(s1);
        CategoryArrayList.add(s2);
        CategoryArrayList.add(s3);
        CategoryArrayList.add(s4);
        CategoryArrayList.add(s5);
        //CategoryArrayList.add(s6);


    }
}
