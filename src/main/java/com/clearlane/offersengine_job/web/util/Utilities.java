package com.clearlane.offersengine_job.web.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class Utilities {

    public static final Logger LOG = LoggerFactory.getLogger(Utilities.class);

    public static final String conditionalApproval_IntroductionText1 = "Please note that this approval is valid for purchase of ONE vehicle only.";
    public static final String conditionalApproval_IntroductionText2 = "However, this approval is subject to you providing additional information. Please contact the dealer for what information is required of you, and be prepared to bring the required information to the dealership. Please note that this approval is valid for purchase of ONE vehicle only.";

    private static DecimalFormat decimalFormatter = new DecimalFormat("$###,###,###.00");
    private static DecimalFormat noDecimalFormatter = new DecimalFormat("$###,###,###");
    private static DecimalFormat integerFormatter = new DecimalFormat("###,###,###");

    private Utilities() {
    }

    public static Double calculateMonthlyPayment(double loanAmount, int termMonths,
            double interestRate) {
        Double monthlyPayment = null;
        try {
            interestRate /= 100.0;
            double monthlyRate = interestRate / 12.0;
            monthlyPayment = (loanAmount * monthlyRate)
                    / (1 - Math.pow(1 + monthlyRate, -termMonths));
        }
        catch (Exception e) {
            LOG.error("Exception in calculateMonthlyPayment {}", e.getMessage(), e);
        }
        return monthlyPayment;
    }

    public static double roundOff(double value, int decimalPlaces) {
        double roundingOfFactor = Math.pow(10, decimalPlaces);
        return ((int) ((value + 0.005) * roundingOfFactor)) / roundingOfFactor;
    }

    public static double roundOff(double value) {
        return roundOff(value, 2);
    }

    /**
     * This method calculates the APR, pass the original loan amount(without the finance charge or fee), the term in months, 
     * for  10year= pass 120as input, and monthypayment, 
     * is the payment amount that is calculate what the customer will pay each month(for the first month, the last amount might be 
     * different but that's ok)
     * @param loanAmount
     * @param term
     * @param monthyPayment
     * @return APR as double with .xxx precision
     */
    public static double getAPR(double loanAmount, double term, double monthyPayment) {
        return calculateapr(loanAmount, term, monthyPayment);
    }

    private static double F(double amount, double numPay, double payment, double x) {
        return amount * x * Math.pow(1 + x, numPay) / (Math.pow(1 + x, numPay) - 1) - payment;
    }

    private static double F_1(double amount, double numPay, double x) {
        double m = amount * (Math.pow(1 + x, numPay) / (-1 + Math.pow(1 + x, numPay))
                - numPay * x * Math.pow(1 + x, -1 + 2 * numPay)
                        / Math.pow(-1 + Math.pow(1 + x, numPay), 2)
                + numPay * x * Math.pow(1 + x, -1 + numPay) / (-1 + Math.pow(1 + x, numPay)));
        return m;
    }

    private static double calculateapr(double loanAmount, double term, double monthyPayment) {
        //start with some number
        double approx = (.05 / 12);

        double prev_approx = approx;
        double error = Math.pow(10, -5);
        double diff = 0;
        for (int k = 0; k < 20; k++) {
            prev_approx = approx;
            approx = prev_approx - (F(loanAmount, term, monthyPayment, prev_approx)
                    / F_1(loanAmount, term, prev_approx));
            diff = Math.abs(approx - prev_approx);
            if (diff < error)
                break;
        }

        return Math.round(approx * 12 * 100000) / 1000d;
    }

    /** Returns Applicant's age 
     * 
     * @param dob
     * @return
     */
    public static int getApplicantAge(Date dob) {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        cal.setTime(dob);
        return currentYear - cal.get(Calendar.YEAR);
    }

    public static String formatDate(Date date, String format) {

        if (date == null) {
            return null;
        }
        if (format == null) {
            format = "MM/dd/yyyy";
        }
        return new SimpleDateFormat(format).format(date);
    }

    public static String formatDouble(Double input) {
        if (input == null)
            return "0.00";
        //return Double.valueOf(decimalFormatter.format(input))+"";
        return decimalFormatter.format(input);
    }

    public static String formatDoubleNoDecimal(Double input) {
        if (input == null)
            return "0";
        return noDecimalFormatter.format(Math.round(input / 1.0) * 1);
    }

    public static String formatInteger(Integer input) {
        if (input == null)
            return "0";
        return integerFormatter.format(input);
    }

    public static String formatUSPhoneNumber(String input) {
        if (StringUtils.isEmpty(input)) {
            return "";
        }
        if (input.length() < 9) {
            input = input + "         ";
        }
        return "(" + input.substring(0, 3) + ") " + input.substring(3, 6) + "-"
                + input.substring(6, 10);
    }

    public static File createTempFile(String prefix, String suffix) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile(prefix, suffix);
        }
        catch (IOException e) {
            //log.error("Exception while creating temp file: {}", e);
        }
        return tempFile;
    }

    public static String getZip5(String fullZip) {
        String zip5 = "";
        if (!StringUtils.isEmpty(fullZip)) {
            if (fullZip.length() > 4) {
                zip5 = fullZip.substring(0, 5);
            }
            else {
                zip5 = fullZip;
            }
        }
        return zip5;
    }

    //public static void main(String[] str) {
    //            double x = 3423.4455;
    //            System.out.println(formatUSPhoneNumber("23454534"));
    //            System.out.println(formatDouble(x));
    //            System.out.println((int) roundOff(x));
    //            System.out.println(Math.round(x / 1.0) * 1);
    //}
}
