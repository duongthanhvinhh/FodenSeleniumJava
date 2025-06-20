package org.foden.utils;

import net.datafaker.Faker;

import java.util.Objects;

public class FakerUtils {
    private static Faker faker;
    private FakerUtils() {
    }

    public static Faker getFakerInstance(){
        if (Objects.isNull(faker)){
            synchronized (FakerUtils.class){
                if (Objects.isNull(faker)){
                    faker = new Faker();
                }
            }
        }
        return faker;
    }

    public static void main(String[] args) {
        System.out.println(FakerUtils.getFakerInstance().name().fullName());
    }
}
