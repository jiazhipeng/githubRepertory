package com.hy.test.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

import com.hy.common.utils.DateUtils;
import com.hy.gcar.entity.PowerUsed;

public class ReadExcel {
	/** 
     * Excel 2003 
     */  
    private final static String XLS = "xls";  
  
    /** 
     * Excel 2007 
     */  
    private final static String XLSX = "xlsx";

	private static final String String = null;  
  
    /** 
     * 由Excel文件的Sheet导出至List 
     *  
     * @param file 
     *            导入的excel文件 
     * @param sheetNum 
     *            excel工作空间,一般情况为0 
     * @return 
     */  
    public static List<Map<String, Object>> exportListFromExcel(File file, int sheetNum) throws IOException {  
        return exportListFromExcel(new FileInputStream(file), FilenameUtils.getExtension(file.getName()), sheetNum);  
    }  
  
    /** 
     * 由Excel流的Sheet导出至List 
     *  
     * @param is 
     * @param extensionName 
     * @param sheetNum 
     * @return 
     * @throws IOException 
     */  
    public static List<Map<String, Object>> exportListFromExcel(InputStream is, String extensionName, int sheetNum) throws IOException {  
  
        Workbook workbook = null;  
  
        if (extensionName.toLowerCase().equals(XLS)) {  
            workbook = new HSSFWorkbook(is);  
        } else if (extensionName.toLowerCase().equals(XLSX)) {  
//            workbook = new XSSFWorkbook(is);  
        }  
  
        return readCell(workbook, sheetNum);  
    }  
  
    /** 
     * 读取Cell的值 
     *  
     * @param sheet 
     * @return 
     */  
    public static List<Map<String, Object>> readCell(Workbook workbook, int sheetNum) {  
        Sheet sheet = workbook.getSheetAt(sheetNum);  
  
        // 解析公式结果  
        // FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();  
  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        // 遍历所有行  
        // for (Row row : sheet)  
        // 除去表头即第一行  
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {  
            Row row = sheet.getRow(i);  
            Map<String, Object> map = new LinkedHashMap<>();  
            // 便利所有列  
            for (Cell cell : row) {  
  
                // 获取单元格的类型  
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());  
                String key = cellRef.formatAsString();  
  
                switch (cell.getCellType()) {  
                // 字符串  
                case Cell.CELL_TYPE_STRING:  
                    map.put(key, cell.getRichStringCellValue().getString());  
                    break;  
                // 数字  
                case Cell.CELL_TYPE_NUMERIC:  
                    if (DateUtil.isCellDateFormatted(cell)) {  
                        map.put(key, cell.getDateCellValue());  
                    } else {  
                        map.put(key, (int)cell.getNumericCellValue());  
                    }  
                    break;  
                // boolean  
                case Cell.CELL_TYPE_BOOLEAN:  
                    map.put(key, cell.getBooleanCellValue());  
                    break;  
                // 方程式  
                case Cell.CELL_TYPE_FORMULA:  
                    map.put(key, cell.getCellFormula());  
                    break;  
                case Cell.CELL_TYPE_BLANK:  
                    break;  
                case Cell.CELL_TYPE_ERROR:  
                    break;  
                // 空值  
                default:  
                    map.put(key, "");  
                }  
            }  
            list.add(map);  
        }  
        return list;  
  
    }  
  
    public static void main(String[] args) throws IOException, ParseException {  
//      String paths = "c:\\excel.xlsx";  
//        String paths = ReadExcel.class.getResource("D:\\td_power_used.xls").getFile();  
        List<Map<String, Object>> lists = ReadExcel.exportListFromExcel(new File("D:\\td_power_used.xls"), 0);  
        System.out.println(lists);
        int size = lists.size();
        for (Map<String, Object> map : lists) {
        	PowerUsed powerUsed = new PowerUsed();
			for (String key:map.keySet()) {
				System.out.println(key);
				System.out.println(map.get(key));
				
				String v=map.get(key)+"";
				if(key.startsWith("A")){
					powerUsed.setMemberId(Long.valueOf(v));
				}else if(key.startsWith("B")){
					powerUsed.setMemberItemId(Long.valueOf(v));
				}else if(key.startsWith("C")){
					powerUsed.setCaroperateId(Long.valueOf(v));
				}else if(key.startsWith("D")){
					powerUsed.setMemberType(Integer.parseInt(v));
				}else if(key.startsWith("E")){
					if(map.get(key) instanceof Date){
						powerUsed.setCarUsedTime((Date)map.get(key));
					}
				}else if(key.startsWith("F")){
					powerUsed.setCarUsedDay(Integer.parseInt(v));
				}else if(key.startsWith("G")){
					powerUsed.setCarUsedAddress(v);
				}else if(key.startsWith("H")){
					if(map.get(key) instanceof Date){
						powerUsed.setCarReturnTime((Date)map.get(key));
					}
				}else if(key.startsWith("I")){
					powerUsed.setCarReturnAddress(v);
				}else if(key.startsWith("J")){
					powerUsed.setCarTypeId(Long.valueOf(v));
				}else if(key.startsWith("K")){
					powerUsed.setUsedStatus(Integer.parseInt(v));
				}else if(key.startsWith("L")){
					powerUsed.setCityCode(v);
				}else if(key.startsWith("M")){
					powerUsed.setMemberItemName(v);
				}else if(key.startsWith("N")){
					powerUsed.setCreated((Date)map.get(key));
				}else if(key.startsWith("O")){
					powerUsed.setLasttimeModify((Date)map.get(key));
				}
			}
			System.out.println(powerUsed.toString());
			System.out.println("============================================"+size--);
		}
    }  
}
