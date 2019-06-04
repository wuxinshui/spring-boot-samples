package com.wxs.excel.utils;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @Author: yoyo
 * @Description: 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors各个方法的弊端：
 * 1）newFixedThreadPool和newSingleThreadExecutor:
 *   主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
 * 2）newCachedThreadPool和newScheduledThreadPool:
 *   主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
 * <p>
 * Positive example 1：
 * //org.apache.commons.lang3.concurrent.BasicThreadFactory
 * ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
 * new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
 * <p>
 * <p>
 * <p>
 * Positive example 2：
 * ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
 * .setNameFormat("demo-pool-%d").build();
 * <p>
 * //Common Thread Pool
 * ExecutorService pool = new ThreadPoolExecutor(5, 200,
 * 0L, TimeUnit.MILLISECONDS,
 * new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
 * <p>
 * pool.execute(()-> System.out.println(Thread.currentThread().getName()));
 * pool.shutdown();//gracefully shutdown
 * <p>
 * <p>
 * <p>
 * Positive example 3：
 * <bean id="userThreadPool"
 * class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
 * <property name="corePoolSize" value="10" />
 * <property name="maxPoolSize" value="100" />
 * <property name="queueCapacity" value="2000" />
 *
 * <property name="threadFactory" value= threadFactory />
 * <property name="rejectedExecutionHandler">
 * <ref local="rejectedExecutionHandler" />
 * </property>
 * </bean>
 * //in code
 * userThreadPool.execute(thread);
 * @Date: Created in 2019/6/4 11:20
 */
public class ExcelFormulaUtil {
    private static final String filePath = "E:\\project\\platform\\店东贷\\22.xlsx";
    private static Set<Object> set=new HashSet<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Map<Integer, String> params = Map.of(2, "郑州", 3, "20000000.00", 4, "222.00", 5, "333", 6, "2", 7, "11");
        Map<Integer, String> params2 = Map.of(2, "郑州", 3, "200000.00", 4, "222.00", 5, "333", 6, "2", 7, "11");

        //ExecutorService executorService = Executors.newFixedThreadPool(10);
        //test_f(params, params2);
        for (int i = 0; i < 10000; i++) {
            CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("future 1..thread-" + Thread.currentThread().getId());
                    return evaluateFormulaCell(params, "I3","future 1..");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            });

            CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println("future 2..thread-" + Thread.currentThread().getId());
                    return evaluateFormulaCell(params2, "I3","future 2..");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            });
            future2.get();
            future1.get();
        }

        System.out.println(set.size());

    }

    private static void test_f(Map<Integer, String> params, Map<Integer, String> params2) throws InterruptedException, ExecutionException {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        for (int i = 0; i < 100; i++) {
            System.out.println("order 1-" + i);
            executorService.submit(() -> {
                try {
                    System.out.println("evaluateFormulaCell 1 call..." + Thread.currentThread().getId());
                    evaluateFormulaCell(params, "I3","");
                    System.out.println("evaluateFormulaCell 2 call..." + Thread.currentThread().getId());
                    evaluateFormulaCell(params2, "I3","");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).get();
        }
    }

    public static void evaluate() throws IOException {
        FileInputStream fis = new FileInputStream("c:/temp/test.xls");
        Workbook wb = new XSSFWorkbook(fis); //or new XSSFWorkbook("c:/temp/test.xls")
        Sheet sheet = wb.getSheetAt(0);
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

// suppose your formula is in B3
        CellReference cellReference = new CellReference("B3");
        Row row = sheet.getRow(cellReference.getRow());
        Cell cell = row.getCell(cellReference.getCol());

        CellValue cellValue = evaluator.evaluate(cell);

        switch (cellValue.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                System.out.println(cellValue.getBooleanValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                System.out.println(cellValue.getNumberValue());
                break;
            case Cell.CELL_TYPE_STRING:
                System.out.println(cellValue.getStringValue());
                break;
            case Cell.CELL_TYPE_BLANK:
                break;
            case Cell.CELL_TYPE_ERROR:
                break;

            // CELL_TYPE_FORMULA will never happen
            case Cell.CELL_TYPE_FORMULA:
                break;
        }
    }


    public static Double evaluateFormulaCell(Map<Integer, String> params, String formulaCell,String future) throws IOException {
        System.out.println("evaluateFormulaCell dealing.." + Thread.currentThread().getId());

        FileInputStream fis = new FileInputStream(filePath);
        //Workbook wb = new HSSFWorkbook(fis); //or new XSSFWorkbook("/somepath/test.xls")
        Workbook wb = new XSSFWorkbook(fis); //or new XSSFWorkbook("/somepath/test.xls")
        Sheet sheet = wb.getSheetAt(0);
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

// suppose your formula is in B3
        CellReference cellReference = new CellReference(formulaCell);
        Row row = sheet.getRow(cellReference.getRow());
        params.keySet().stream().forEach(key -> {
            row.getCell(key).setCellValue(params.get(key));
        });
        Cell cell = row.getCell(cellReference.getCol());

        if (cell != null) {
            switch (evaluator.evaluateFormulaCell(cell)) {
                case Cell.CELL_TYPE_BOOLEAN:
                    System.out.println(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    System.out.println(future+cell.getNumericCellValue());
                    return cell.getNumericCellValue();
                //break;
                case Cell.CELL_TYPE_STRING:
                    System.out.println(cell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                case Cell.CELL_TYPE_ERROR:
                    System.out.println(cell.getErrorCellValue());
                    break;

                // CELL_TYPE_FORMULA will never occur
                case Cell.CELL_TYPE_FORMULA:
                    break;
            }
        }
        return null;
    }

    public static void evaluateInCell() throws IOException {
        FileInputStream fis = new FileInputStream("/somepath/test.xls");
        Workbook wb = new XSSFWorkbook(fis); //or new XSSFWorkbook("/somepath/test.xls")
        Sheet sheet = wb.getSheetAt(0);
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

// suppose your formula is in B3
        CellReference cellReference = new CellReference("B3");
        Row row = sheet.getRow(cellReference.getRow());
        Cell cell = row.getCell(cellReference.getCol());

        if (cell != null) {
            switch (evaluator.evaluateInCell(cell).getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    System.out.println(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    System.out.println(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    System.out.println(cell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                case Cell.CELL_TYPE_ERROR:
                    System.out.println(cell.getErrorCellValue());
                    break;

                // CELL_TYPE_FORMULA will never occur
                case Cell.CELL_TYPE_FORMULA:
                    break;
            }
        }
    }
}
