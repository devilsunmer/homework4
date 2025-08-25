package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import dao.impl.ProductSystemViewDaoImpl;
import model.Member;
import model.OrderAll;
import model.OrderData;
import model.OrderItem;
import model.Product;
import model.ProductSystemView;
import model.Staff;
import service.impl.MemberServiceImpl;
import service.impl.OrderAllServiceImpl;
import service.impl.OrderItemServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.StaffServiceImpl;

public class ReporterTool {

	public static String[] excelChoose() {
		String[] choose = new String[] { "所有客戶列表名單", "員工列表名單", "訂單詳細資訊列表", "產品列表", "現有產品清點列表" };
		return choose;
	}
	
	public static List<Object> objectSet(String name){
		List<Object> list = new ArrayList<>();
		switch (name) {
	        case "所有客戶列表名單":
	        	List<Member> member=new MemberServiceImpl().all();
	        	list=new ArrayList<>(member);
	        	break;
	        case "員工列表名單":
	        	List<Staff> staff=new StaffServiceImpl().allStaff();
	        	list=new ArrayList<>(staff);
	        case "訂單詳細資訊列表":
	        	List<OrderAll> orderall=new OrderAllServiceImpl().allOrder();
	        	List<OrderData> orderDataList=new ArrayList<>();
	        	for(OrderAll o:orderall)
	        	{
        			OrderAll orderAll = new OrderAll();
        			orderAll.setOrderNumber(o.getOrderNumber());
        			orderAll.setMemberNumber(o.getMemberNumber());
        			List<OrderItem> orderItemList = new OrderItemServiceImpl().orderItemView(o.getOrderNumber());
        			orderAll.setOrderSum(o.getOrderSum());
        			orderAll.setOrderDate(o.getOrderDate());
        			OrderData orderData = new OrderData();
        			orderData.setItems(orderItemList);
        			orderData.setOrderall(orderAll);
        			orderDataList.add(orderData);
	        	}
	        	list=new ArrayList<>(orderDataList);
	        case "產品列表":
	        	List<Product> product=new ProductServiceImpl().allProduct();
	        	list=new ArrayList<>(product);
	        case "現有產品清點列表" :
	        	List<ProductSystemView> view=new ProductSystemViewDaoImpl().getByAll();
	        	list=new ArrayList<>(view);
	        default:
	            JOptionPane.showMessageDialog(null, "找不到對應的清單：" + name, "錯誤", JOptionPane.ERROR_MESSAGE);
		}
		return list;
	}

	public static void createExcel(Object object,String fileName, String sheetName,String choosename) {
		 if(sheetName==null) sheetName = "valueTest";
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
			fieldMap.put("數量", "productQuantity");
			fieldMap.put("小計金額", "orderProductPrice");
			fieldMap.put("消費總金額", "orderSum");
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
	
	public static List<OrderData> splitOrderDataList(List<OrderAll> orderAllList) {
	    List<OrderData> resultList = new ArrayList<>();

	    for (OrderAll orderAll : orderAllList) {
	        // 查會員資訊
	        Member member = new MemberServiceImpl().userSomeView(orderAll.getMemberNumber());

	        // 查商品明細
	        List<OrderItem> items = new OrderItemServiceImpl().orderItemView(orderAll.getOrderNumber());

	        // 每筆商品 ➜ 拆出一個 OrderData 物件（同一筆訂單編號與會員）
	        for (OrderItem item : items) {
	            OrderData data = new OrderData();
	            data.setOrderall(orderAll);
	            data.setItems(Arrays.asList(item)); // 包裝成單筆清單

	            // 將會員也傳入，方便 getter 呼叫
	            data.setMember(member);

	            resultList.add(data);
	        }
	    }

	    return resultList;
	}


	public static String excelUse(String filename) {
		if(filename==null) filename="forSystemText.xls";
		if(!filename.endsWith(".xls"))
		{
			filename=filename+".xls";
		}
		File dir = new File("reporter");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String filePath = "reporter/" + filename;
		return filePath;
	}
	
	public static void exportReport(String chooseName, String fileName, String sheetName) {
	    try {
	        List<Object> objects = objectSet(chooseName);
	        if (objects == null || objects.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "無資料可匯出", "錯誤", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // 檔案路徑處理
	        String finalPath = excelUse(fileName);

	        // 如果是「訂單詳細資訊」要進行拆分
	        if ("訂單詳細資訊列表".equals(chooseName)) {
	            List<OrderAll> orderAllList = new OrderAllServiceImpl().allOrder();
	            List<OrderData> orderDataList = splitOrderDataList(orderAllList);
	            for (OrderData data : orderDataList) {
	                createExcel(data, finalPath, sheetName, chooseName);
	            }
	        } else {
	            for (Object obj : objects) {
	                createExcel(obj, finalPath, sheetName, chooseName);
	            }
	        }

	        JOptionPane.showMessageDialog(null, "報表已成功匯出到 " + finalPath, "完成", JOptionPane.INFORMATION_MESSAGE);

	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "報表匯出失敗：" + e.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
	public static void forSave(Object object,String fileName, String sheetName,String choosename) {
		String[] orderPrintList = excelChoose();
		String excelname = fileName.toLowerCase().endsWith(".xls") ? fileName : fileName + ".xls";
	    boolean isExcelExist = Arrays.stream(orderPrintList)
	            .anyMatch(existing -> existing.equalsIgnoreCase(excelname));
	   
		if (fileName.endsWith(".xls")) {
			if (isExcelExist) {
				JOptionPane.showMessageDialog(null, "請重新輸出一個 Excel 報表名稱", "錯誤", JOptionPane.ERROR_MESSAGE);
				return;
			}
			createExcel(object, fileName, sheetName, choosename);
		} else {
			JOptionPane.showMessageDialog(null, "不支援的報表類型", "錯誤", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	public static String chooseSaveLocation(String defaultFileName) {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("選擇儲存位置");
	    if(defaultFileName.endsWith(".txt")) defaultFileName=defaultFileName+".txt";
	    fileChooser.setSelectedFile(new File(defaultFileName));

	    int result = fileChooser.showSaveDialog(null);
	    if (result == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        return selectedFile.getAbsolutePath();
	    } else {
	        return null; // 使用者取消
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
