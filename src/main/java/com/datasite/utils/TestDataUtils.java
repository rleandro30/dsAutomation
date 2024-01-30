package com.datasite.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class TestDataUtils {

    public static Iterator<Object[]> getDataProviderForMethod(Method method, String csvPath) throws IOException, CsvValidationException {
        List<Object[]> records = new ArrayList<>();
        String methodName = method.getName();
        boolean headerFound = false;

        try (CSVReader csvReader = new CSVReader(new FileReader(csvPath))) {
            String[] values;

            while ((values = csvReader.readNext()) != null) {
                if(values.length > 1){
                    if(!values[0].isEmpty() && values[0].equals(methodName)){
                        headerFound = true;
                    }
                    if(!values[0].isEmpty() && !values[0].equals(methodName) && headerFound){
                        break;
                    }
                    if (values[0].isEmpty() && headerFound) {
                        records.add(Arrays.copyOfRange(values, 1, values.length));
                    }
                }
            }
        }
        return records.iterator();
    }
}