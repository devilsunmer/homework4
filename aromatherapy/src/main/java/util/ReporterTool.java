package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.Member;
import model.OrderData;
import model.Product;
import model.ProductSystemView;
import model.Staff;

public class ReporterTool {

	public static String[] excelChoose() {
		String[] choose = new String[] { "所有客戶列表名單", "員工列表名單", "訂單詳細資訊列表", "產品列表", "現有產品清點列表" };
		return choose;
	}

	public static void createExcel(String fileName, String choosename) {
		Object object = new Object();
		String sheetName = "valueTest";
		LinkedHashMap<String, String> fieldMap = forExcelC(object);
		try (// 開啟 Excel 檔案
				HSSFWorkbook excelbook = new HSSFWorkbook(new FileInputStream(fileName))) {
			HSSFSheet sheet = excelbook.getSheet(sheetName);
			if (sheet == null) {
				sheet = excelbook.createSheet(sheetName);
			}

			int rowCount = sheet.getPhysicalNumberOfRows();
			HSSFRow row = sheet.createRow(rowCount);

			int col = 0;
			for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
				String fieldName = entry.getValue(); // getter 對應的欄位名
				try {
					Method getter = object.getClass().getMethod("get" + capitalize(fieldName));
					Object value = getter.invoke(object);
					row.createCell(col).setCellValue(value != null ? value.toString() : "");
				} catch (NoSuchMethodException e) {
					// 若 getter 不存在，也可擴充支援 boolean isXxx()
					System.err.println("找不到方法: " + fieldName);
				}
				col++;
			}

			// 寫入檔案
			FileOutputStream out = new FileOutputStream(fileName);
			excelbook.write(out);
			out.flush();
			out.close();

			JOptionPane.showMessageDialog(null, "資料已成功寫入 " + fileName, "完成", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 工具：將字段轉成大寫開頭（getter 名稱）
	private static String capitalize(String str) {
		if (str == null || str.isEmpty())
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static LinkedHashMap<String, String> forExcelC(Object object) {
		LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
		if (object instanceof Member) {
			fieldMap.put("會員編號", "memberNumber");
			fieldMap.put("姓名", "memberName");
			fieldMap.put("帳號", "memberUsername");
			fieldMap.put("密碼", "memberPassword");
			fieldMap.put("地址", "memberAddress");
			fieldMap.put("電話", "memberPhone");
			fieldMap.put("是否為會員", "memberOrNot");
		} else if (object instanceof Staff) {
			fieldMap.put("員工編號", "staffNumber");
			fieldMap.put("員工姓名", "staffName");
			fieldMap.put("員工帳號", "staffUsername");
			fieldMap.put("員工密碼", "staffPassword");
			fieldMap.put("員工電話", "staffPhone");
		} else if (object instanceof OrderData) {
			fieldMap.put("訂單編號", "orderNumber");
			fieldMap.put("聯絡姓名", "memberName");
			fieldMap.put("寄送地址", "memberAddress");
			fieldMap.put("聯絡電話", "memberPhone");
			fieldMap.put("訂購產品編號", "productNumber");
			fieldMap.put("訂購產品", "productName");
			fieldMap.put("數量", "custOrderTimeRecoder");
			fieldMap.put("小計金額", "spentMoney");
			fieldMap.put("消費總金額", "memberId");
		} else if (object instanceof Product) {
			fieldMap.put("產品編號", "productNumber");
			fieldMap.put("產品名稱", "productName");
			fieldMap.put("產品分類", "category");
			fieldMap.put("產品介紹大綱", "productOverview");
			fieldMap.put("產品進價", "productCost");
			fieldMap.put("產品售價", "productPrice");
		} else if (object instanceof ProductSystemView) {
			fieldMap.put("產品編號", "number");
			fieldMap.put("產品名稱", "name");
			fieldMap.put("產品進貨量", "in");
			fieldMap.put("產品出貨量", "out");
			fieldMap.put("產品現有數量", "nowHave");
		} else {
			System.err.println("未支援的類型: " + object.getClass().getSimpleName());
		}

		return fieldMap;
	}

	public static void forSave(String filename, String chooseName) {
		String[] orderPrintList = excelChoose();

		String excelname = chooseName + ".xls";
		String txtname = chooseName + ".txt";

		boolean isExcelExist = Arrays.stream(orderPrintList).anyMatch(existing -> existing.equalsIgnoreCase(excelname));
		boolean isTxtExist = Arrays.stream(orderPrintList).anyMatch(existing -> existing.equalsIgnoreCase(txtname));

		if (chooseName.endsWith(".xls")) {
			if (isExcelExist) {
				JOptionPane.showMessageDialog(null, "請重新輸出一個 Excel 報表名稱", "錯誤", JOptionPane.ERROR_MESSAGE);
				return;
			}
			createExcel(filename, chooseName);
		} else if (chooseName.endsWith(".txt")) {
			if (isTxtExist) {
				JOptionPane.showMessageDialog(null, "請重新輸出一個文字報表名稱", "錯誤", JOptionPane.ERROR_MESSAGE);
				return;
			}
			createExcel(filename, chooseName);
		} else {
			JOptionPane.showMessageDialog(null, "不支援的報表類型", "錯誤", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
		public static void forPrint(String filename, String chooseName) {
		try {
			File dir = new File("reporter");
			if (!dir.exists()) {
				dir.mkdirs();
			}

			String filePath = "reporter/" + filename;
			File file = new File(filePath);

			if (!file.exists()) {
				JOptionPane.showMessageDialog(null, "檔案不存在：" + file.getAbsolutePath());
				return;
			}

			// 預覽
			boolean confirmed = FileTool.previewReport(file.getAbsolutePath());
			if (!confirmed) {
				JOptionPane.showMessageDialog(null, "已取消列印");
				return;
			}

			// 分開處理 Excel 與 TXT 列印
			if (chooseName.endsWith(".xls")) {
				FileTool.printExcelAsTable(file.getAbsolutePath());
			} else if (chooseName.endsWith(".txt")) {
				String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
				FileTool.printText(content);
			} else {
				JOptionPane.showMessageDialog(null, "不支援的檔案格式：" + chooseName);
				return;
			}

			JOptionPane.showMessageDialog(null, "開始列印報表", "指令輸出", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "列印時發生錯誤：" + ex.getMessage());
		}
	}

}
