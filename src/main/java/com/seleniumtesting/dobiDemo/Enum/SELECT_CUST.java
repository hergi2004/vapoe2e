package com.seleniumtesting.dobiDemo.Enum;

import com.seleniumtesting.dobiDemo.CustomerManage.Customer;
import com.seleniumtesting.dobiDemo.basicOperration.stringManipulation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public enum SELECT_CUST {


    //enum ( creating new customer )
    NEW{
        @Override
        public void selectCustomer(WebDriver driver,Customer customer){

            //click the "New Customer" selection when adding sales
            //create new customer
            driver.findElement(By.id("new_customer")).click();
            sleep(2000);

            WebElement cust_name=driver.findElement(By.xpath("/html/body/div/div[1]/section/div/div/div[2]/form/div[1]/input"));
            WebElement cust_phone=driver.findElement(By.xpath("/html/body/div/div[1]/section/div/div/div[2]/form/div[2]/input"));
            WebElement cust_descript=driver.findElement(By.xpath("/html/body/div/div[1]/section/div/div/div[2]/form/div[3]/textarea"));
            WebElement cust_starting_balance=driver.findElement(By.xpath("/html/body/div/div[1]/section/div/div/div[2]/form/div[4]/div/input"));

            //get all the details needed to fill up the all the input fields by getting variables from Customer constructed
            cust_name.sendKeys( customer.getName() );
            cust_phone.sendKeys(customer.getPhone_number());
            cust_descript.sendKeys(customer.getDescription());
            cust_starting_balance.sendKeys(customer.getStarting_balance());

            //click the "add" button to add new customer with details given above
            driver.findElement(By.xpath("/html/body/div/div[1]/section/div/div/div[3]/div/button")).click();

            //click the "add sales" button to add new sales for the particular customer
            driver.findElement(By.xpath("/html/body/div/div[1]/section/div/div[2]/div[1]/center/a[1]")).click();


        }
    },

    //enum ( select from existing customer
    EXISTED{
        @Override
        public void selectCustomer(WebDriver driver,Customer customer){

            //click the "Select Customer" selection when adding sales
            //select from existing customers
            driver.findElement(By.id("select_customer")).click();
            sleep(2000);

            //the String return fromt the element would be like "Showing 1 to 407 of 407 entries"
            //string manipulation to get the 407 out from the String
            String temp=stringManipulation.getNumberofEntries(
                                            driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div[2]/div/div[3]/div[1]/div"))
                                                    .getText() )  ;
            int nth = 0;
            int which_to_select=0;

            //if the getNumberofEntries() failed, it would return a string of "Not Found"
            if(temp.equals("Not Found")){
                which_to_select=3;
            }
            else{
                nth=Integer.parseInt(temp);

                //select a random with the range of nth ( for example , select one out range of 0 to 407
                which_to_select= (int) (Math.random() * nth);
            }


            //generate the xpath of that customer
            String customer_selection_list_xpath="/html/body/div[1]/div[1]/div[2]/div/div/div[2]/div/div[2]/div/table/tbody/tr[" + which_to_select +"]/td[1]/a";

            //click to select that customer
            driver.findElement(By.xpath(customer_selection_list_xpath)).click();


        }

    };


    //abstract method that can be overridden using methods defined inside Enum objects such as NEW or EXISTED
    public abstract void selectCustomer(WebDriver driver, Customer customer);


    /**
     *
     * @param Millis Int: On how many milliseconds the thread should sleep
     */
    public static void sleep(int Millis){
        try {
            Thread.sleep(Millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}