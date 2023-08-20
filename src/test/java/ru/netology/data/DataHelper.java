package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
        @Value
        @RequiredArgsConstructor
        public static class CardDetails {
        private String cardNumber;
        private String monthNumber;
        private String yeaNumber;
        private String holder;
        private String codeCvcCvv;
    }

    // номер карты
    public static String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }

    public static String getApprovedCardNumberWithoutSpaces() {
        return ("4444444444444441");
    }

    public static String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }

    public static String getCardNumberWithLengthLessValid() {
        return ("4444");
    }

    public static String getCardNumberWithLengthMoreValid() {
        return ("4444 4444 4444 4441 4");
    }

    public static String getCardNumberWithSpecialSymbols() {
        return ("4444 &$#* 4444 4441");
    }

    public static String getCardNumberWithLetters() {
        return ("4444 cbfj 4444 4441");
    }

    // поле месяц
    public static String getValidMonth() {
        LocalDate localDate = LocalDate.now();
        return String.format("%02d", localDate.getMonthValue());
    }

    public static String getMonthNumberWithTwoZero() {
        return "00";
    }

    public static String getMonthNumberMoreTwelve() {
        return "13";
    }

    public static String getMonthNumberLengthLessValid() {
        return "4";
    }

    public static String getMonthNumberLengthMoreValid() {
        return "594";
    }

    public static String getMonthNumberWithSpecialSymbols() {
        return "2#";
    }

    public static String getMonthNumberWithLetters() {
        return "F4";
    }

    public static String getPrevMonthNumber() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

      // поле год
    public static String getValidYear() {
        return String.format("%ty", Year.now());
    }

    public static String getPrevYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getNextYear() {
        return String.format("%ty", Year.now().plusYears(2));
    }

    public static String getYearNumberMoreValidLength() {
        return "293";
    }

    public static String getYearNumberLessValidLength() {
        return "3";
    }

    public static String getYearNumberWithSpecialSymbols() {
        return ("#6");
    }

    public static String getYearNumberWithLetters() {
        return ("8F");
    }

    // поле держатель
    public static String getValidHolder() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    public static String getHolderNameInKirilitsa() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getHolderNameWithSpecialSymbols() {
        return "&#DIMOVA";
    }

    public static String getHolderNameWithFigures() {
        return "565IMOVA";
    }

    public static String getHolderNameWithHyphen() {
        return "AGEEVA-KOSTINA";
    }

    public static String getHolderNameWithLowerRangeLetters() {
        return "ageeva";
    }

    public static String getHolderNameWithOneLetterLength() {
        return "A";
    }

    // поле cvc/cvv
    public static String getValidCvcCvv() {
        return "591";
    }

    public static String getCodeCvcCvvWithLengthLessValid() {
        return "49";
    }

    public static String getCodeCvcCvvWithLengthMoreValid() {
        return "4257";
    }

    public static String getCodeCvcCvvWithSpecialSymbols() {
        return "4#6";
    }

    public static String getCodeCvcCvvWithLetters() {
        return "D8R";
    }

    public static String getCodeCvcCvvWithSameFigures() {
        return "777";
    }


}