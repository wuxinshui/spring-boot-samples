package com.wxs.excel.utils;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/7/22 19:31
 */
public class CsvUtils {
    public static <T> List<T> parseInputCsvRecords(MultipartFile csvFile, Class<T> clazz, int skipCommentLines) throws IOException {
        Reader csvReader = new InputStreamReader(csvFile.getInputStream());
        HeaderColumnNameMappingStrategy strategy = new HeaderColumnNameMappingStrategy<T>();
        strategy.setType(clazz);
        var csvToBean = new CsvToBeanBuilder<T>(csvReader)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(skipCommentLines)
                .withFilter(CsvUtils::nonEmptyStringArrays)
                .withMappingStrategy(strategy).build();
        try {
            return csvToBean.parse();
        } catch (RuntimeException ex) {
            throw new NotOfficeXmlFileException("文件解析错误，请检查文件格式: " + ex.getLocalizedMessage() + "," + ex.getCause().getLocalizedMessage());
        }
    }

    public static <T> void writeCsv(Writer writer, List<T> records, Class<T> clazz) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        var columnMapping = Stream.of(T.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toMap(Function.identity(), Function.identity()));

        var mappingStrategy = new CustomizedHeaderNameMapping<T>();
        mappingStrategy.setType(clazz);
        mappingStrategy.setColumnMapping(columnMapping);
        StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                .withMappingStrategy(mappingStrategy)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();
        beanToCsv.write(records);
    }

    private static boolean nonEmptyStringArrays(String[] lines) {
        return !ArrayUtils.isEmpty(lines) &&
                ((ArrayUtils.getLength(lines) != 1) || StringUtils.isNotEmpty(lines[0]));
    }
}
