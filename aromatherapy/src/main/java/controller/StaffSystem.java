package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import dao.impl.ProductSystemViewDaoImpl;
import model.Category;
import model.Member;
import model.OrderAll;
import model.OrderData;
import model.OrderItem;
import model.Product;
import model.ProductStock;
import model.ProductSystemView;
import model.Staff;
import service.impl.MemberServiceImpl;
import service.impl.OrderAllServiceImpl;
import service.impl.OrderItemServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.ProductStockServiceImpl;
import service.impl.StaffServiceImpl;
import util.ButtonTool;
import util.FileTool;
import util.ReporterTool;
import util.SystemTool;

public class StaffSystem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField memberUsernameField;
	private JTextField freeCustPhoneField;
	private JTextField freeCustNameField;
	private JTable freeCustOutput;
	private JTable memberOutput;
	private JTextField memberNumberField;
	private JTable staffOutput;
	private JTextField staffNameField;
	private JTextField staffUsernameField;
	private JTextField staffPasswordField;
	private JTextField staffPhoneField;
	private JTextField searchMemberNumberField;
	private JTable orderOutput;
	private JTextField searchOrderNumberField;
	private JTable productOutput;
	private JTextField productNumberField;
	private JTable table;
	private JComboBox<String> nameForStock;
	private JTextField productNameField;
	private JComboBox<String> catergoryField;
	private JSpinner productCostField;
	private JSpinner productPriceField;
	private JTextField excelFieldNameField;
	private JTextField excelSheetNameField;
	private JTextField txtNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffSystem frame = new StaffSystem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StaffSystem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(85, 108, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("芳香療法");
		lblNewLabel.setForeground(new Color(189, 255, 255));
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel.setBounds(61, 10, 86, 45);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("後臺管理");
		lblNewLabel_1.setForeground(new Color(189, 255, 255));
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_1.setBounds(157, 18, 70, 30);
		contentPane.add(lblNewLabel_1);

		JTabbedPane system = new JTabbedPane(JTabbedPane.LEFT);
		system.setForeground(new Color(0, 95, 140));
		system.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		system.setBounds(10, 55, 566, 498);
		contentPane.add(system);

		JPanel memberManager = new JPanel();
		system.addTab("會員管理", null, memberManager, null);
		memberManager.setLayout(null);

		JTabbedPane memberManage = new JTabbedPane(JTabbedPane.TOP);
		memberManage.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		memberManage.setBackground(new Color(191, 251, 239));
		memberManage.setForeground(new Color(37, 102, 133));
		memberManage.setBounds(0, 0, 488, 493);
		memberManager.add(memberManage);

		JPanel memberRead = new JPanel();
		memberManage.addTab("會員查詢", null, memberRead, null);
		memberRead.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("會員帳號：");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2.setBounds(29, 350, 80, 31);
		memberRead.add(lblNewLabel_2);

		memberUsernameField = new JTextField();
		memberUsernameField.setBounds(39, 391, 112, 31);
		memberRead.add(memberUsernameField);
		memberUsernameField.setColumns(10);

		JLabel lblNewLabel_2_1_1_1_2_1 = new JLabel("(可以直接在下方表單進行修改)");
		lblNewLabel_2_1_1_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1_2_1.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		lblNewLabel_2_1_1_1_2_1.setBounds(309, 10, 164, 31);
		memberRead.add(lblNewLabel_2_1_1_1_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("會員表單：");
		lblNewLabel_2_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(10, 10, 80, 31);
		memberRead.add(lblNewLabel_2_2);

		Member member = new Member();
		List<Member> memberList = new MemberServiceImpl().itsMember();
		List<Object> objectList = new ArrayList<>(memberList);

		memberOutput = SystemTool.setForObject(objectList, member);
		memberOutput.setBounds(10, 40, 463, 284);
		memberRead.add(memberOutput);
		Font tableFont = new Font("微軟正黑體", Font.BOLD, 14);
		memberOutput.setFont(tableFont);

		JScrollPane scrollPane = new JScrollPane(memberOutput);
		scrollPane.setBounds(10, 40, 463, 284);
		memberRead.add(scrollPane);

		JLabel memberMes = new JLabel("");
		memberMes.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		memberMes.setBounds(75, 334, 247, 39);
		memberRead.add(memberMes);

		JPanel freeMember = new JPanel();
		memberManage.addTab("訪客會員", null, freeMember, null);
		freeMember.setLayout(null);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("非會員客戶：");
		lblNewLabel_2_1_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_1_1_1.setBounds(10, 10, 98, 31);
		freeMember.add(lblNewLabel_2_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("需要會員簡訊推廣發送嗎？");
		lblNewLabel_2_1_1_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_1_1_1_1.setBounds(10, 332, 200, 31);
		freeMember.add(lblNewLabel_2_1_1_1_1);

		freeCustPhoneField = new JTextField();
		freeCustPhoneField.setColumns(10);
		freeCustPhoneField.setBounds(100, 414, 112, 31);
		freeMember.add(freeCustPhoneField);

		JLabel lblNewLabel_2_1_1 = new JLabel("客戶電話：");
		lblNewLabel_2_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(10, 410, 80, 31);
		freeMember.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("客戶姓名：");
		lblNewLabel_2_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(10, 373, 80, 31);
		freeMember.add(lblNewLabel_2_1);

		freeCustNameField = new JTextField();
		freeCustNameField.setColumns(10);
		freeCustNameField.setBounds(100, 373, 90, 31);
		freeMember.add(freeCustNameField);

		List<Member> freeList = new MemberServiceImpl().notMember();
		List<Object> objectFreeList = new ArrayList<>(freeList);
		
		freeCustOutput = SystemTool.setForObject(objectFreeList, member);
		freeCustOutput.setBounds(10, 51, 463, 267);
		freeMember.add(freeCustOutput);

		JScrollPane scrollPaneoo = new JScrollPane(freeCustOutput);
		scrollPaneoo.setBounds(10, 40, 463, 284);
		freeMember.add(scrollPaneoo);
		
		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("(可以直接在下方表單進行修改)");
		lblNewLabel_2_1_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1_2.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		lblNewLabel_2_1_1_1_2.setBounds(309, 0, 164, 31);
		freeMember.add(lblNewLabel_2_1_1_1_2);

		JLabel freeCustMes = new JLabel("");
		freeCustMes.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		freeCustMes.setBounds(99, 2, 210, 39);
		freeMember.add(freeCustMes);

		JPanel staffManager = new JPanel();
		system.addTab("員工管理", null, staffManager, null);
		staffManager.setLayout(null);

		JTabbedPane staffManage = new JTabbedPane(JTabbedPane.TOP);
		staffManage.setForeground(new Color(43, 67, 85));
		staffManage.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		staffManage.setBounds(0, 0, 488, 493);
		staffManager.add(staffManage);

		JPanel staffRead = new JPanel();
		staffManage.addTab("員工查詢", null, staffRead, null);
		staffRead.setLayout(null);

		JLabel lblNewLabel_2_3 = new JLabel("員工編號：");
		lblNewLabel_2_3.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_3.setBounds(34, 360, 80, 31);
		staffRead.add(lblNewLabel_2_3);

		memberNumberField = new JTextField();
		memberNumberField.setColumns(10);
		memberNumberField.setBounds(44, 401, 112, 31);
		staffRead.add(memberNumberField);

		JLabel lblNewLabel_2_1_1_1_2_1_1 = new JLabel("(可以直接在下方表單進行修改或點選後再透過按鈕刪除)");
		lblNewLabel_2_1_1_1_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1_2_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		lblNewLabel_2_1_1_1_2_1_1.setBounds(164, 10, 309, 31);
		staffRead.add(lblNewLabel_2_1_1_1_2_1_1);

		JLabel lblNewLabel_2_2_1 = new JLabel("員工表單：");
		lblNewLabel_2_2_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_2_1.setBounds(10, 10, 80, 31);
		staffRead.add(lblNewLabel_2_2_1);

		Staff staff=new Staff();
		List<Staff> staffList = new StaffServiceImpl().allStaff();
		List<Object> objectStaffList = new ArrayList<>(staffList);
		
		staffOutput = SystemTool.setForObject(objectStaffList, staff);
		staffOutput.setBounds(10, 40, 463, 284);
		staffRead.add(staffOutput);
		staffOutput.setFont(tableFont);
		
		JScrollPane say = new JScrollPane(staffOutput);
		say.setBounds(10, 40, 463, 284);
		staffRead.add(say);

		JLabel staffMes = new JLabel("");
		staffMes.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		staffMes.setBounds(75, 334, 247, 39);
		staffRead.add(staffMes);

		JPanel staffNew = new JPanel();
		staffManage.addTab("員工新增", null, staffNew, null);
		staffNew.setLayout(null);

		JLabel lblNewLabel_2_3_1 = new JLabel("員工姓名：");
		lblNewLabel_2_3_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_3_1.setBounds(137, 75, 80, 31);
		staffNew.add(lblNewLabel_2_3_1);

		staffNameField = new JTextField();
		staffNameField.setColumns(10);
		staffNameField.setBounds(227, 79, 112, 31);
		staffNew.add(staffNameField);

		staffUsernameField = new JTextField();
		staffUsernameField.setColumns(10);
		staffUsernameField.setBounds(227, 136, 112, 31);
		staffNew.add(staffUsernameField);

		JLabel lblNewLabel_2_3_1_1 = new JLabel("員工帳號：");
		lblNewLabel_2_3_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_3_1_1.setBounds(137, 132, 80, 31);
		staffNew.add(lblNewLabel_2_3_1_1);

		staffPasswordField = new JTextField();
		staffPasswordField.setColumns(10);
		staffPasswordField.setBounds(227, 203, 112, 31);
		staffNew.add(staffPasswordField);

		JLabel lblNewLabel_2_3_1_2 = new JLabel("員工密碼：");
		lblNewLabel_2_3_1_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_3_1_2.setBounds(137, 199, 80, 31);
		staffNew.add(lblNewLabel_2_3_1_2);

		staffPhoneField = new JTextField();
		staffPhoneField.setColumns(10);
		staffPhoneField.setBounds(227, 270, 112, 31);
		staffNew.add(staffPhoneField);

		JLabel lblNewLabel_2_3_1_3 = new JLabel("員工電話：");
		lblNewLabel_2_3_1_3.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_3_1_3.setBounds(137, 266, 80, 31);
		staffNew.add(lblNewLabel_2_3_1_3);

		JLabel staffNewMes = new JLabel("");
		staffNewMes.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		staffNewMes.setBounds(153, 383, 174, 39);
		staffNew.add(staffNewMes);

		JLabel staffNewMes_1 = new JLabel("~歡迎加入我們~");
		staffNewMes_1.setHorizontalAlignment(SwingConstants.CENTER);
		staffNewMes_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		staffNewMes_1.setBounds(19, 10, 174, 39);
		staffNew.add(staffNewMes_1);

		JPanel oderManager = new JPanel();
		system.addTab("訂單管理", null, oderManager, null);
		oderManager.setLayout(null);

		JLabel lblNewLabel_2_4 = new JLabel("會員編號：");
		lblNewLabel_2_4.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_4.setBounds(20, 365, 80, 31);
		oderManager.add(lblNewLabel_2_4);

		searchMemberNumberField = new JTextField();
		searchMemberNumberField.setColumns(10);
		searchMemberNumberField.setBounds(98, 365, 112, 31);
		oderManager.add(searchMemberNumberField);

		JLabel lblNewLabel_2_1_1_1_2_1_2 = new JLabel("(可以直接在下方表單進行修改)");
		lblNewLabel_2_1_1_1_2_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1_2_1_2.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		lblNewLabel_2_1_1_1_2_1_2.setBounds(309, 12, 164, 31);
		oderManager.add(lblNewLabel_2_1_1_1_2_1_2);

		JLabel lblNewLabel_2_2_2 = new JLabel("訂單表單：");
		lblNewLabel_2_2_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_2_2.setBounds(10, 10, 80, 31);
		oderManager.add(lblNewLabel_2_2_2);

		OrderData orderData = new OrderData();
		orderOutput = SystemTool.createOrderDataTable(orderData);
		orderOutput.setBounds(10, 40, 463, 284);
		orderOutput.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		oderManager.add(orderOutput);

		JScrollPane scrollPane3 = new JScrollPane(orderOutput);
		scrollPane3.setBounds(10, 67, 463, 284);
		oderManager.add(scrollPane3);

		JLabel lblNewLabel_2_4_1 = new JLabel("訂單編號：");
		lblNewLabel_2_4_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_4_1.setBounds(20, 414, 80, 31);
		oderManager.add(lblNewLabel_2_4_1);

		searchOrderNumberField = new JTextField();
		searchOrderNumberField.setColumns(10);
		searchOrderNumberField.setBounds(98, 414, 112, 31);
		oderManager.add(searchOrderNumberField);

		JPanel productManager = new JPanel();
		system.addTab("產品管理", null, productManager, null);
		productManager.setLayout(null);

		JTabbedPane productManage = new JTabbedPane(JTabbedPane.TOP);
		productManage.setBounds(0, 0, 488, 493);
		productManage.setForeground(new Color(128, 128, 255));
		productManage.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		productManager.add(productManage);

		JPanel productCreat = new JPanel();
		productManage.addTab("產品新增", null, productCreat, null);
		productCreat.setLayout(null);

		JLabel lblNewLabel_2_3_1_4 = new JLabel("產品名稱：");
		lblNewLabel_2_3_1_4.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_3_1_4.setBounds(137, 59, 80, 31);
		productCreat.add(lblNewLabel_2_3_1_4);

		productNameField = new JTextField();
		productNameField.setColumns(10);
		productNameField.setBounds(227, 63, 112, 31);
		productCreat.add(productNameField);

		catergoryField = new JComboBox<>(Category.modelFoComobox());
		catergoryField.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		catergoryField.setBounds(227, 120, 112, 31);
		productCreat.add(catergoryField);
		catergoryField.setSelectedIndex(0);

		JLabel lblNewLabel_2_3_1_1_1 = new JLabel("產品分類：");
		lblNewLabel_2_3_1_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_3_1_1_1.setBounds(137, 116, 80, 31);
		productCreat.add(lblNewLabel_2_3_1_1_1);

		productCostField = new JSpinner();
		productCostField
				.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		productCostField.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		productCostField.setBounds(227, 240, 112, 31);
		productCreat.add(productCostField);

		JLabel lblNewLabel_2_3_1_2_1 = new JLabel("產品進貨成本：");
		lblNewLabel_2_3_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_3_1_2_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_3_1_2_1.setBounds(95, 236, 122, 31);
		productCreat.add(lblNewLabel_2_3_1_2_1);

		productPriceField = new JSpinner();
		productPriceField
				.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		productPriceField.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		productPriceField.setBounds(227, 307, 112, 31);
		productCreat.add(productPriceField);

		JLabel lblNewLabel_2_3_1_3_1 = new JLabel("產品定價：");
		lblNewLabel_2_3_1_3_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_3_1_3_1.setBounds(137, 303, 80, 31);
		productCreat.add(lblNewLabel_2_3_1_3_1);

		JLabel productNewMes = new JLabel("");
		productNewMes.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		productNewMes.setBounds(153, 402, 174, 39);
		productCreat.add(productNewMes);

		JLabel staffNewMes_1_1 = new JLabel("產品新增");
		staffNewMes_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		staffNewMes_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		staffNewMes_1_1.setBounds(20, 10, 122, 39);
		productCreat.add(staffNewMes_1_1);

		JLabel lblNewLabel_2_3_1_1_1_1 = new JLabel("產品介紹大綱：");
		lblNewLabel_2_3_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_3_1_1_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_3_1_1_1_1.setBounds(95, 177, 123, 31);
		productCreat.add(lblNewLabel_2_3_1_1_1_1);

		JTextArea productOverviewField = new JTextArea();
		productOverviewField.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		productOverviewField.setBounds(227, 171, 232, 53);
		productCreat.add(productOverviewField);

		JPanel productRead = new JPanel();
		productManage.addTab("產品查詢", null, productRead, null);
		productRead.setLayout(null);

		JLabel lblNewLabel_2_1_1_1_2_1_2_1 = new JLabel("(可以直接在下方表單進行修改或點選後透過按鈕刪除)");
		lblNewLabel_2_1_1_1_2_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1_2_1_2_1.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		lblNewLabel_2_1_1_1_2_1_2_1.setBounds(167, 18, 306, 31);
		productRead.add(lblNewLabel_2_1_1_1_2_1_2_1);

		JLabel lblNewLabel_2_2_2_2 = new JLabel("產品表單：");
		lblNewLabel_2_2_2_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_2_2_2.setBounds(10, 18, 80, 31);
		productRead.add(lblNewLabel_2_2_2_2);
		
		Product product=new Product();
		List<Product> productList = new ProductServiceImpl().allProduct();
		List<Object> objectproductList = new ArrayList<>(productList);
		
		productOutput =SystemTool.setForObject(objectproductList, product);
		productOutput.setBounds(10, 48, 463, 284);
		productRead.add(productOutput);
		productOutput.setFont(tableFont);
		
		JScrollPane sayHi = new JScrollPane(productOutput);
		sayHi.setBounds(10, 40, 463, 284);
		productRead.add(sayHi);

		JLabel productMes = new JLabel("");
		productMes.setHorizontalAlignment(SwingConstants.CENTER);
		productMes.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		productMes.setBounds(155, 349, 175, 39);
		productRead.add(productMes);

		JLabel lblNewLabel_2_4_2 = new JLabel("產品編號：");
		lblNewLabel_2_4_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_4_2.setBounds(20, 402, 80, 31);
		productRead.add(lblNewLabel_2_4_2);

		productNumberField = new JTextField();
		productNumberField.setColumns(10);
		productNumberField.setBounds(110, 406, 112, 31);
		productRead.add(productNumberField);

		JPanel productStock = new JPanel();
		productManage.addTab("產品庫存管理", null, productStock, null);
		productStock.setLayout(null);

		JLabel lblNewLabel_2_1_1_1_2_1_2_1_1 = new JLabel("(可以直接在下方表單進行修改)");
		lblNewLabel_2_1_1_1_2_1_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1_2_1_2_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		lblNewLabel_2_1_1_1_2_1_2_1_1.setBounds(167, 10, 306, 31);
		productStock.add(lblNewLabel_2_1_1_1_2_1_2_1_1);

		JLabel lblNewLabel_2_2_2_2_1 = new JLabel("產品庫存表單：");
		lblNewLabel_2_2_2_2_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_2_2_2_1.setBounds(10, 10, 125, 31);
		productStock.add(lblNewLabel_2_2_2_2_1);

		ProductSystemView productSystemView=new ProductSystemView();
		List<ProductSystemView> productStockList = new ProductSystemViewDaoImpl().getByAll();
		List<Object> objectproductStockList = new ArrayList<>(productStockList);
		
		table = SystemTool.setForObject(objectproductStockList, productSystemView);
		table.setBounds(10, 40, 463, 265);
		productStock.add(table);
		table.setFont(tableFont);
		
		JScrollPane we = new JScrollPane(table);
		we.setBounds(10, 40, 463, 284);
		productStock.add(we);

		JLabel productMes_1 = new JLabel("");
		productMes_1.setHorizontalAlignment(SwingConstants.CENTER);
		productMes_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		productMes_1.setBounds(165, 315, 175, 39);
		productStock.add(productMes_1);

		JLabel lblNewLabel_2_4_2_1_1 = new JLabel("進貨數量：");
		lblNewLabel_2_4_2_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_4_2_1_1.setBounds(191, 364, 80, 31);
		productStock.add(lblNewLabel_2_4_2_1_1);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(20)));
		spinner.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		spinner.setBounds(281, 365, 63, 31);
		productStock.add(spinner);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(20)));
		spinner_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		spinner_1.setBounds(281, 414, 63, 31);
		productStock.add(spinner_1);

		JLabel lblNewLabel_2_4_2_1_1_1 = new JLabel("出貨數量：");
		lblNewLabel_2_4_2_1_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_4_2_1_1_1.setBounds(191, 413, 80, 31);
		productStock.add(lblNewLabel_2_4_2_1_1_1);

		nameForStock = new JComboBox<>(new ProductServiceImpl().productName().toArray(new String[0]));
		nameForStock.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		nameForStock.setBounds(101, 387, 80, 31);
		productStock.add(nameForStock);

		JLabel lblNewLabel_2_4_2_1_2 = new JLabel("產品名稱：");
		lblNewLabel_2_4_2_1_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_4_2_1_2.setBounds(14, 390, 80, 31);
		productStock.add(lblNewLabel_2_4_2_1_2);

		JPanel overviewManager = new JPanel();
		system.addTab("報表管理", null, overviewManager, null);
		overviewManager.setLayout(null);

		JTabbedPane overviewManage = new JTabbedPane(JTabbedPane.TOP);
		overviewManage.setForeground(new Color(0, 0, 85));
		overviewManage.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		overviewManage.setBounds(0, 0, 488, 493);
		overviewManager.add(overviewManage);

		JPanel excel = new JPanel();
		overviewManage.addTab("excel匯出", null, excel, null);
		excel.setLayout(null);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(55, 64, 372, 264);
		excel.add(scrollPane1);

		JList<String> excellist = new JList<>(ReporterTool.excelChoose());
		scrollPane1.setViewportView(excellist);

		JLabel lblNewLabel_2_4_2_1_2_1 = new JLabel("可選擇excel匯出：");
		lblNewLabel_2_4_2_1_2_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_4_2_1_2_1.setBounds(24, 10, 136, 31);
		excel.add(lblNewLabel_2_4_2_1_2_1);

		JLabel lblNewLabel_2_4_2_1_3 = new JLabel("檔案名稱：");
		lblNewLabel_2_4_2_1_3.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_4_2_1_3.setBounds(78, 348, 80, 31);
		excel.add(lblNewLabel_2_4_2_1_3);

		excelFieldNameField = new JTextField();
		excelFieldNameField.setColumns(10);
		excelFieldNameField.setBounds(165, 345, 80, 31);
		excel.add(excelFieldNameField);

		excelSheetNameField = new JTextField();
		excelSheetNameField.setColumns(10);
		excelSheetNameField.setBounds(165, 394, 80, 31);
		excel.add(excelSheetNameField);

		JLabel lblNewLabel_2_4_2_1_2_2 = new JLabel("表單名稱：");
		lblNewLabel_2_4_2_1_2_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_4_2_1_2_2.setBounds(78, 397, 80, 31);
		excel.add(lblNewLabel_2_4_2_1_2_2);

		JPanel word = new JPanel();
		overviewManage.addTab("文字檔", null, word, null);
		word.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(57, 63, 372, 110);
		word.add(scrollPane_1);

		JList<String> txtlist = new JList<>(FileTool.getAvailableTxt());
		scrollPane_1.setViewportView(txtlist);

		JLabel lblNewLabel_2_4_2_1_2_1_1 = new JLabel("可選擇文字檔存檔：");
		lblNewLabel_2_4_2_1_2_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_4_2_1_2_1_1.setBounds(26, 22, 153, 31);
		word.add(lblNewLabel_2_4_2_1_2_1_1);

		JLabel lblNewLabel_2_4_2_1_3_1 = new JLabel("檔案名稱：");
		lblNewLabel_2_4_2_1_3_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_4_2_1_3_1.setBounds(75, 391, 80, 31);
		word.add(lblNewLabel_2_4_2_1_3_1);

		txtNameField = new JTextField();
		txtNameField.setColumns(10);
		txtNameField.setBounds(162, 388, 80, 31);
		word.add(txtNameField);

		JTextArea viewTxtOutput = new JTextArea();
		viewTxtOutput.setBounds(67, 178, 349, 177);
		word.add(viewTxtOutput);

		JPanel print = new JPanel();
		overviewManage.addTab("列印", null, print, null);
		print.setLayout(null);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(61, 51, 372, 110);
		print.add(scrollPane_1_1);

		JList<String> printlist = new JList<String>(FileTool.getAvailableTxt());
		scrollPane_1_1.setViewportView(printlist);

		JLabel lblNewLabel_2_4_2_1_2_1_1_1 = new JLabel("可選擇檔案列印：");
		lblNewLabel_2_4_2_1_2_1_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2_4_2_1_2_1_1_1.setBounds(27, 10, 153, 31);
		print.add(lblNewLabel_2_4_2_1_2_1_1_1);

		JTextArea viewPrintOutput = new JTextArea();
		viewPrintOutput.setBounds(10, 171, 463, 232);
		print.add(viewPrintOutput);

		JPanel statisticalChart = new JPanel();
		overviewManage.addTab("統計圖表", null, statisticalChart, null);
		statisticalChart.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("最後處理");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("微軟正黑體", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(120, 164, 207, 92);
		statisticalChart.add(lblNewLabel_3);

		/***********************************
		 * ↓按鈕大魔王↓
		 ************************************/

		JButton searchMember = new JButton("查詢會員");
		searchMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String memberUser = memberUsernameField.getText();
				if (memberUser == null) {
					JOptionPane.showMessageDialog(null, "請填寫會員帳號");
				} else {
					try {
						SystemTool.forSearchMemberNumber(memberUser);
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "查詢失敗，請確認是否正確會員編碼");
					}
				}
			}
		});
		searchMember.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		searchMember.setBounds(191, 379, 98, 39);
		memberRead.add(searchMember);

		JButton searchFreeCustButton_1_1 = new JButton("表單修改確認");
		searchFreeCustButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					for (Member OD : memberList) {
						new MemberServiceImpl().update(OD);
					}
					JOptionPane.showMessageDialog(null, "資料已成功從資料庫刪除！");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "同步失敗：" + ex.getMessage());
				}
			}
		});
		searchFreeCustButton_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		searchFreeCustButton_1_1.setBounds(332, 349, 125, 39);
		memberRead.add(searchFreeCustButton_1_1);

		JButton memberDeleteButton = new JButton("表單刪除確認");
		memberDeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow=staffOutput.getSelectedRow();
				if (selectedRow != -1) {
	            	Member pro=memberList.get(selectedRow);
	            	new MemberServiceImpl().delete(pro);
	            	((AbstractTableModel) staffOutput.getModel()).fireTableDataChanged(); // 更新 JTable
	                JOptionPane.showMessageDialog(null, "刪除完成！");
				} else {
	                JOptionPane.showMessageDialog(null, "請選擇一列來刪除！");
	            }	
			}
		});
		memberDeleteButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		memberDeleteButton.setBounds(332, 410, 125, 39);
		memberRead.add(memberDeleteButton);

		JButton textStringButton = new JButton("推廣客戶");
		textStringButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SystemTool.saveAdvertiseContent();
			}
		});
		textStringButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		textStringButton.setBounds(209, 328, 98, 39);
		freeMember.add(textStringButton);

		JButton searchFreeCustButton = new JButton("查詢客戶");
		searchFreeCustButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name=freeCustPhoneField.getText();
				String phone=freeCustNameField.getText();
				Member free=new MemberServiceImpl().freeUserUse(name, phone);
				if(free.getMemberNumber()==null) {
					JOptionPane.showMessageDialog(null, "請填入編號");
				}else {
					if(new ProductServiceImpl().productNumber(free.getMemberNumber())!=null)
						SystemTool.forSearchMember(free.getMemberNumber());
					else
						JOptionPane.showMessageDialog(null, "請確認編號");
				}
			}
		});
		searchFreeCustButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		searchFreeCustButton.setBounds(222, 387, 98, 39);
		freeMember.add(searchFreeCustButton);

		JButton searchFreeCustButton_1 = new JButton("表單修改確認");
		searchFreeCustButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					for (Member od : memberList) {
						new MemberServiceImpl().update(od);
					}
					JOptionPane.showMessageDialog(null, "資料已成功從資料庫刪除！");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "同步失敗：" + ex.getMessage());
				}
			}
		});
		searchFreeCustButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		searchFreeCustButton_1.setBounds(348, 339, 125, 39);
		freeMember.add(searchFreeCustButton_1);

		JButton freeCustDeleteButton = new JButton("表單刪除確認");
		freeCustDeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow=staffOutput.getSelectedRow();
				if (selectedRow != -1) {
	            	Member pro=memberList.get(selectedRow);
	            	new MemberServiceImpl().delete(pro);
	            	((AbstractTableModel) staffOutput.getModel()).fireTableDataChanged(); // 更新 JTable
	                JOptionPane.showMessageDialog(null, "刪除完成！");
				} else {
	                JOptionPane.showMessageDialog(null, "請選擇一列來刪除！");
	            }	
			}
		});
		freeCustDeleteButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		freeCustDeleteButton.setBounds(348, 402, 125, 39);
		freeMember.add(freeCustDeleteButton);

		JButton searchStaffButton = new JButton("查詢員工");
		searchStaffButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String number=memberNumberField.getText();
				if(number==null) {
					JOptionPane.showMessageDialog(null, "請填入編號");
				}else {
					if(new ProductServiceImpl().productNumber(number)!=null)
						SystemTool.forSearchStaff(number);
					else
						JOptionPane.showMessageDialog(null, "請確認編號");
				}
			}
		});
		searchStaffButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		searchStaffButton.setBounds(166, 376, 98, 39);
		staffRead.add(searchStaffButton);

		JButton staffUpdateButton = new JButton("表單修改確認");
		staffUpdateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					for (Staff op : staffList) {
						new StaffServiceImpl().updateAll(op);
					}
					JOptionPane.showMessageDialog(null, "資料已成功從資料庫刪除！");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "同步失敗：" + ex.getMessage());
				}
			}
		});
		staffUpdateButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		staffUpdateButton.setBounds(314, 347, 125, 39);
		staffRead.add(staffUpdateButton);

		JButton searchStaffButton_1 = new JButton("刪除員工確認");
		searchStaffButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow=staffOutput.getSelectedRow();
				if (selectedRow != -1) {
	            	Staff pro=staffList.get(selectedRow);
	            	new StaffServiceImpl().deleteStaff(pro.getStaffNumber());
	            	((AbstractTableModel) staffOutput.getModel()).fireTableDataChanged(); // 更新 JTable
	                JOptionPane.showMessageDialog(null, "刪除完成！");
				} else {
	                JOptionPane.showMessageDialog(null, "請選擇一列來刪除！");
	            }		
			}
		});
		searchStaffButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		searchStaffButton_1.setBounds(314, 410, 125, 39);
		staffRead.add(searchStaffButton_1);

		JButton newStaffButton = new JButton("新增員工");
		newStaffButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name=staffNameField.getText();
				String username=staffUsernameField.getText();
				String password=staffPasswordField.getText();
				String phone=staffPhoneField.getText();
				if(name!=null&&ButtonTool.isUserPass(username)&&ButtonTool.isUserPass(password)&&ButtonTool.isPhone(phone))
				{
					Staff staff=new Staff(name,username,password,phone);
					new StaffServiceImpl().addStaff(staff);
					staffNameField.setText("");
					staffUsernameField.setText("");
					staffPasswordField.setText("");
					staffPhoneField.setText("");
	                JOptionPane.showMessageDialog(null, "新增員工完成！");
				}else {
	                JOptionPane.showMessageDialog(null, "資料必須填寫");

				}
			}
		});
		newStaffButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		newStaffButton.setBounds(95, 316, 98, 39);
		staffNew.add(newStaffButton);

		JButton clearStaffNewButton = new JButton("清空內容");
		clearStaffNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				staffNameField.setText("");
				staffUsernameField.setText("");
				staffPasswordField.setText("");
				staffPhoneField.setText("");
			}
		});
		clearStaffNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		clearStaffNewButton.setBounds(281, 316, 98, 39);
		staffNew.add(clearStaffNewButton);

		List<OrderData> orderDataList = SystemTool.releaseSpaceForOrderData();

		JButton searchMemberNumberButton = new JButton("查詢會員編號");
		searchMemberNumberButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String orderMemberNumber = searchMemberNumberField.getText();
				if (orderMemberNumber == null) {
					JOptionPane.showMessageDialog(null, "請填寫會員號碼");
				} else {
					try {
						SystemTool.forSearchMemberOrder(orderMemberNumber);
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "查詢失敗，請確認是否正確會員編碼");
					}
				}
			}
		});
		searchMemberNumberButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		searchMemberNumberButton.setBounds(220, 357, 125, 39);
		oderManager.add(searchMemberNumberButton);

		JButton orderUpdateButton = new JButton("表單修改確認");
		orderUpdateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					for (OrderData od : orderDataList) {
						new OrderAllServiceImpl().update(od.getOrderall());
						for (OrderItem item : od.getItems()) {
							new OrderItemServiceImpl().update(item);
						}
					}
					JOptionPane.showMessageDialog(null, "資料已成功同步至資料庫！");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "同步失敗：" + ex.getMessage());
				}
			}
		});
		orderUpdateButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		orderUpdateButton.setBounds(348, 361, 125, 39);
		oderManager.add(orderUpdateButton);

		JButton searchOrderNumberButton = new JButton("查詢訂單編號");
		searchOrderNumberButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String orderNumber = searchOrderNumberField.getText();
				if (orderNumber == null) {
					JOptionPane.showMessageDialog(null, "請填寫訂單編號");
				} else {
					try {
						SystemTool.forSearchOrderNumber(orderNumber);
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "查詢失敗，請確認是否正確訂單編碼");
					}
				}
			}
		});
		searchOrderNumberButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		searchOrderNumberButton.setBounds(220, 406, 125, 39);
		oderManager.add(searchOrderNumberButton);

		JButton orderDeleteButton = new JButton("表單刪除確認");
		orderDeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					for (OrderData od : orderDataList) {
						OrderAll orderAll = new OrderAll();
						orderAll = od.getOrderall();
						new OrderAllServiceImpl().deleteOrder(orderAll.getOrderNumber());
					}
					JOptionPane.showMessageDialog(null, "資料已成功從資料庫刪除！");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "同步失敗：" + ex.getMessage());
				}
			}
		});
		orderDeleteButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		orderDeleteButton.setBounds(348, 406, 125, 39);
		oderManager.add(orderDeleteButton);

		JButton newProductButton = new JButton("新增產品");
		newProductButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String productName=productNameField.getText();
				String category=catergoryField.getSelectedItem().toString();
				String overview=productOverviewField.getText();
				Integer productCost=Integer.parseInt(productCostField.getValue().toString());
				Integer productPrice=Integer.parseInt(productPriceField.getValue().toString());
				if(productName!=null&&category!=null&&overview!=null&&productCost!=null&&productPrice!=null) {
				 new ProductServiceImpl().addProduct(new Product(productName,category,overview,productCost,productPrice));
					JOptionPane.showMessageDialog(null, "新增產品成功");
				}else {
					JOptionPane.showMessageDialog(null, "新增產品失敗，請確認是否有未填寫的地方");					
				}
			}
		});
		newProductButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		newProductButton.setBounds(95, 353, 98, 39);
		productCreat.add(newProductButton);

		JButton clearProductNewButton = new JButton("清空內容");
		clearProductNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				productNameField.setText("");
				catergoryField.setSelectedIndex(0);
				productOverviewField.setText("");
				productCostField.setValue("0");
				productPriceField.setValue("0");	
			}
		});
		clearProductNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		clearProductNewButton.setBounds(281, 353, 98, 39);
		productCreat.add(clearProductNewButton);

		JButton searchProductNumberButton = new JButton("查詢產品");
		searchProductNumberButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String number=productNumberField.getText();
				if(number==null) {
					JOptionPane.showMessageDialog(null, "請填入編號");
				}else {
					if(new ProductServiceImpl().productNumber(number)!=null)
						SystemTool.forSearchProduct(number);
					else
						JOptionPane.showMessageDialog(null, "請確認編號");
				}
			}
		});
		searchProductNumberButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		searchProductNumberButton.setBounds(232, 398, 98, 39);
		productRead.add(searchProductNumberButton);

		JButton productUpdateButton = new JButton("表單修改確認");
		productUpdateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					for (Product od : productList) {
						new ProductServiceImpl().productUpdate(od);
					}
					JOptionPane.showMessageDialog(null, "資料已成功從資料庫刪除！");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "同步失敗：" + ex.getMessage());
				}
			}
		});
		productUpdateButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		productUpdateButton.setBounds(20, 342, 125, 39);
		productRead.add(productUpdateButton);

		JButton deleteProductNumberButton = new JButton("刪除產品");
		deleteProductNumberButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 int selectedRow = productOutput.getSelectedRow();
		            if (selectedRow != -1) {
		            	Product pro=productList.get(selectedRow);
		            	new ProductServiceImpl().deleteProduct(pro.getProductNumber());
		            	((AbstractTableModel) productOutput.getModel()).fireTableDataChanged(); // 更新 JTable
		            } else {
		                JOptionPane.showMessageDialog(null, "請選擇一列來刪除！");
		            }			
		      }
		});
		deleteProductNumberButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		deleteProductNumberButton.setBounds(343, 342, 98, 39);
		productRead.add(deleteProductNumberButton);

		LocalDate today = LocalDate.now();
		Date date = java.sql.Date.valueOf(today);
		
		JButton searchProductNumberButton_1 = new JButton("確認進貨");
		searchProductNumberButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name=nameForStock.getSelectedItem().toString();
				Integer in=(Integer)spinner.getValue();
				if(in!=0)
				{
					String number=new ProductServiceImpl().takeProductNumber(name);
					ProductStock pp=new ProductStockServiceImpl().takeProductStock(number);
					pp.setProductInStock(in);
					pp.setProductStockDate(date);
					new ProductStockServiceImpl().addProductStock(pp);
					JOptionPane.showMessageDialog(null, "確認進貨");
				}
				JOptionPane.showMessageDialog(null, "");
			}
		});
		searchProductNumberButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		searchProductNumberButton_1.setBounds(363, 361, 98, 39);
		productStock.add(searchProductNumberButton_1);

		JButton searchProductNumberButton_1_1 = new JButton("確認出貨");
		searchProductNumberButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name=nameForStock.getSelectedItem().toString();
				Integer out=(Integer)spinner_1.getValue();
				if(out!=0)
				{
					String number=new ProductServiceImpl().takeProductNumber(name);
					ProductStock pp=new ProductStockServiceImpl().takeProductStock(number);
					pp.setProductOutStock(out);
					pp.setProductStockDate(date);
					new ProductStockServiceImpl().addProductStock(pp);
					JOptionPane.showMessageDialog(null, "確認出貨");
				}
				JOptionPane.showMessageDialog(null, "");
			}
		});
		searchProductNumberButton_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		searchProductNumberButton_1_1.setBounds(363, 410, 98, 39);
		productStock.add(searchProductNumberButton_1_1);

		JButton saveExcelButton = new JButton("確認匯出");
		saveExcelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String excelname=excelFieldNameField.getText();
				String sheetname=excelSheetNameField.getText();
				String choose=excellist.getSelectedValue().toString();
				ReporterTool.exportReport(choose, excelname, sheetname);
			}
		});
		saveExcelButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		saveExcelButton.setBounds(304, 371, 110, 39);
		excel.add(saveExcelButton);

		JButton saveTxtButton = new JButton("確認存檔");
		saveTxtButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name=txtlist.getSelectedValue();
				String userUse=txtNameField.getText();
				if(userUse==null)
				{
				String path=ReporterTool.chooseSaveLocation(name);
				viewTxtOutput.setText(path);
				}else {
					String path2=ReporterTool.chooseSaveLocation(userUse);
					viewTxtOutput.setText(path2);
				}
			}
		});
		saveTxtButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		saveTxtButton.setBounds(306, 383, 110, 39);
		word.add(saveTxtButton);

		JButton printButton = new JButton("確認存檔");
		printButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		printButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		printButton.setBounds(191, 410, 110, 39);
		print.add(printButton);

		JButton loginOutButton = new JButton("登出");
		loginOutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Member mem=(Member)FileTool.readFiled("memberMemry.txt");
				mem=new Member();
				FileTool.saveFiled(mem, "memberMemry.txt");
				Shop frame = new Shop();
				frame.setVisible(true);
				dispose();
			}
		});
		loginOutButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		loginOutButton.setBounds(311, 10, 100, 27);
		contentPane.add(loginOutButton);

		JButton leaveButton = new JButton("離開");
		leaveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		leaveButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		leaveButton.setBounds(444, 10, 100, 27);
		contentPane.add(leaveButton);

	}
}
