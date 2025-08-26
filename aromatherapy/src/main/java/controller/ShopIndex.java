package controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Member;
import model.OrderAll;
import model.OrderData;
import model.Product;
import service.impl.MemberServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.StaffServiceImpl;
import util.ButtonTool;
import util.FileTool;
import util.OrderTool;

public class ShopIndex extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField memberName;
	private JTextField memberUsername;
	private JTextField memberPassword;
	private JTextField memberAddress;
	private JTextField memberPhone;
	private JTextField passwordField;
	private JTextField addressField;
	private JTextField phoneField;
	private JTextField usernameLoginField;
	private JPasswordField passwordLoginField;
	private JTable orderView;
	private final ButtonGroup memberAddressPhoneGroup = new ButtonGroup();
	private JTextField freeCustNameField;
	private JTextField freeCustPhoneField;
	private JTextField userMemberNameField;
	private Boolean isLoggedIn=false ; // 默認為未登入
	private Member member;
	private JTextField custNameField;
	private JTextField custAddressField;
	private JTextField custPhoneField;
	private JTextField nameOrderField;
	private JButton loginButton;
	private JButton changeButton;
	private JLabel imdexMes;
	private JLabel memberNameField;
	private JLabel usernameField;
	private JList<String> phoneList;
	private JList<String> addressList;
	private JLabel orderMemberShow;
	private JLabel orderFreeShow2;
	private JLabel orderFreeShow;
	private JLabel orderShow;
	private final ButtonGroup addOrPhone = new ButtonGroup();
	private List<String> phoneUser;
	private List<String> addressUser;
	private List<String> nameUser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopIndex frame = new ShopIndex();
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
	public ShopIndex() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		CardLayout cardLayout = new CardLayout();
		JPanel cardPanel = new JPanel(cardLayout);
		cardPanel.setBackground(new Color(64, 0, 128));
		cardPanel.setBounds(0, 80, 585, 400);
		contentPane.add(cardPanel);

		JPanel shopIndex = new JPanel();
		shopIndex.setBackground(new Color(240, 234, 247));
		cardPanel.add(shopIndex, "shopIndex");
		shopIndex.setLayout(null);
		shopIndex.setName("shopIndex");

		JPanel create = new JPanel();
		create.setBackground(new Color(249, 232, 242));
		create.setBounds(0, 0, 580, 430);
		cardPanel.add(create, "create");
		create.setLayout(null);
		create.setName("create");

		JLabel lblNewLabel_1 = new JLabel("姓名：");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1.setBounds(158, 118, 69, 25);
		create.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("帳號：");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(158, 153, 69, 25);
		create.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("密碼：");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(158, 188, 69, 25);
		create.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("常用住址：");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(142, 223, 85, 25);
		create.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("連絡電話：");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(142, 258, 85, 25);
		create.add(lblNewLabel_1_4);

		memberName = new JTextField();
		memberName.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		memberName.setBounds(252, 118, 103, 26);
		create.add(memberName);
		memberName.setColumns(10);

		memberUsername = new JTextField();
		memberUsername.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		memberUsername.setColumns(10);
		memberUsername.setBounds(252, 153, 103, 26);
		create.add(memberUsername);

		memberPassword = new JTextField();
		memberPassword.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		memberPassword.setColumns(10);
		memberPassword.setBounds(252, 188, 103, 26);
		create.add(memberPassword);

		memberAddress = new JTextField();
		memberAddress.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		memberAddress.setColumns(10);
		memberAddress.setBounds(252, 223, 103, 26);
		create.add(memberAddress);

		memberPhone = new JTextField();
		memberPhone.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		memberPhone.setColumns(10);
		memberPhone.setBounds(252, 258, 103, 26);
		create.add(memberPhone);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(37, 10, 520, 84);
		create.add(scrollPane_1);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		scrollPane_1.setBorder(null);
		
		JTextPane createMes = new JTextPane();
		createMes.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		scrollPane_1.setViewportView(createMes);
		createMes.setEditable(false);
		createMes.setFocusable(false);
		createMes.setOpaque(false);
		createMes.setBorder(null);
		createMes.setText(ButtonTool.createRule());
		ButtonTool.centerText(createMes);
		createMes.setCaretPosition(0);

		JLabel putTime = new JLabel("");
		putTime.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		putTime.setBounds(10, 10, 136, 36);
		create.add(putTime);

		JPanel login = new JPanel();
		login.setBackground(new Color(211, 204, 255));
		cardPanel.add(login, "login");
		login.setLayout(null);
		login.setName("login");

		JLabel lblNewLabel_3 = new JLabel("帳號：");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_3.setBounds(153, 82, 70, 34);
		login.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("密碼：");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_3_1.setBounds(153, 148, 70, 34);
		login.add(lblNewLabel_3_1);
		
		usernameLoginField = new JTextField();
		usernameLoginField.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		usernameLoginField.setBounds(233, 82, 176, 34);
		login.add(usernameLoginField);
		usernameLoginField.setColumns(10);

		passwordLoginField = new JPasswordField();
		passwordLoginField.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		passwordLoginField.setBounds(233, 153, 176, 34);
		login.add(passwordLoginField);
		
		ButtonTool.loginView(usernameLoginField, passwordLoginField);

		JSpinner count = new JSpinner();
		count.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		count.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		count.setBounds(347, 63, 37, 37);
		shopIndex.add(count);

		JComboBox<String> chooselist = new JComboBox<>();
		List<String> productView = new ProductServiceImpl().productName();
		for (String o : productView) {
			chooselist.addItem(o);
		}
		chooselist.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
		            String selectedItem = chooselist.getSelectedItem().toString();

		            // 避免第一項「請選擇商品」觸發邏輯
		            if (!selectedItem.equals("請選擇商品")) {
		                ProductServiceImpl productService = new ProductServiceImpl();
		                String productNumber = productService.takeProductNumber(selectedItem);
		                Product product = productService.productNumber(productNumber);
		                imdexMes.setText(product.getProdouctOverview());
		            } else {
		                imdexMes.setText(""); // 選擇預設空白時清空
		            }
				}
			}
		});
		chooselist.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		chooselist.setBounds(123, 64, 151, 36);
		shopIndex.add(chooselist);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(20, 10, 538, 43);
		scrollPane_4.setOpaque(false);
		scrollPane_4.getViewport().setOpaque(false);
		scrollPane_4.setBorder(null);
		shopIndex.add(scrollPane_4);

		imdexMes = new JLabel();
		scrollPane_4.setViewportView(imdexMes);
		imdexMes.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		imdexMes.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_4 = new JLabel("決定要購買：");
		lblNewLabel_4.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 63, 103, 37);
		shopIndex.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("數量：");
		lblNewLabel_4_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setBounds(281, 63, 56, 37);
		shopIndex.add(lblNewLabel_4_1);
		DefaultTableModel model = new DefaultTableModel(new Object[] { "商品名稱", "數量", "單價", "小計" }, 0); // 初始欄位名稱

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 114, 538, 229);
		shopIndex.add(scrollPane);

		orderView = new JTable();
		scrollPane.setViewportView(orderView);
		orderView.setModel(model);

		JPanel fresh = new JPanel();
		fresh.setBackground(new Color(228, 199, 243));
		cardPanel.add(fresh, "fresh");
		fresh.setLayout(null);
		fresh.setName("fresh");

		JLabel lblNewLabel_2 = new JLabel("請選擇你要修改哪一邊的資訊呢?");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_2.setBounds(175, 29, 267, 43);
		fresh.add(lblNewLabel_2);

		JPanel memberItem = new JPanel();
		memberItem.setBackground(new Color(240, 225, 249));
		memberItem.setBounds(10, 95, 267, 248);
		fresh.add(memberItem);
		memberItem.setLayout(null);

		JLabel lblNewLabel_1_5 = new JLabel("會員姓名：");
		lblNewLabel_1_5.setBounds(23, 46, 85, 25);
		memberItem.add(lblNewLabel_1_5);
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_5.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		JLabel lblNewLabel_1_1_1 = new JLabel("帳號：");
		lblNewLabel_1_1_1.setBounds(39, 81, 69, 25);
		memberItem.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		JLabel lblNewLabel_1_2_1 = new JLabel("密碼：");
		lblNewLabel_1_2_1.setBounds(39, 116, 69, 25);
		memberItem.add(lblNewLabel_1_2_1);
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		passwordField = new JTextField();
		passwordField.setBounds(133, 116, 103, 26);
		memberItem.add(passwordField);
		passwordField.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		passwordField.setColumns(10);

		usernameField = new JLabel("");
		usernameField.setBounds(133, 82, 103, 24);
		memberItem.add(usernameField);
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		usernameField.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		memberNameField = new JLabel("");
		memberNameField.setBounds(133, 46, 103, 24);
		memberItem.add(memberNameField);
		memberNameField.setHorizontalAlignment(SwingConstants.CENTER);
		memberNameField.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		JPanel orderUseField = new JPanel();
		orderUseField.setBackground(new Color(222, 189, 242));
		orderUseField.setBounds(287, 95, 288, 248);
		fresh.add(orderUseField);
		orderUseField.setLayout(null);

		JLabel lblNewLabel_1_5_1 = new JLabel("聯絡姓名：");
		lblNewLabel_1_5_1.setBounds(40, 68, 85, 25);
		orderUseField.add(lblNewLabel_1_5_1);
		lblNewLabel_1_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_5_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		JLabel lblNewLabel_1_3_1 = new JLabel("住址：");
		lblNewLabel_1_3_1.setBounds(56, 102, 69, 25);
		orderUseField.add(lblNewLabel_1_3_1);
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		addressField = new JTextField();
		addressField.setBounds(150, 102, 103, 26);
		orderUseField.add(addressField);
		addressField.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		addressField.setColumns(10);

		JLabel lblNewLabel_1_4_1 = new JLabel("連絡電話：");
		lblNewLabel_1_4_1.setBounds(40, 137, 85, 25);
		orderUseField.add(lblNewLabel_1_4_1);
		lblNewLabel_1_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		phoneField = new JTextField();
		phoneField.setBounds(150, 137, 103, 26);
		orderUseField.add(phoneField);
		phoneField.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		phoneField.setColumns(10);

		nameOrderField = new JTextField();
		nameOrderField.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		nameOrderField.setColumns(10);
		nameOrderField.setBounds(150, 68, 103, 26);
		orderUseField.add(nameOrderField);

		JLabel nameField = new JLabel("");
		nameField.setBounds(150, 68, 103, 24);
		orderUseField.add(nameField);
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(150, 103, 103, 25);
		orderUseField.add(scrollPane_2);
		
				addressList = new JList<>();
				scrollPane_2.setViewportView(addressList);
				addressList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						 if (!e.getValueIsAdjusting()) { // 防止重複觸發
							 int index = addressList.getSelectedIndex();
					            if (index >= 0 && index < addressUser.size()) {
					                phoneField.setText(phoneUser.get(index));
					                addressField.setText(addressUser.get(index));
					                nameField.setText(nameUser.get(index));
					                addressList.setVisible(false);
					                addressField.setVisible(true);
						            phoneField.setVisible(true);
						            lblNewLabel_1_4_1.setVisible(true);
						    		lblNewLabel_1_3_1.setVisible(true);
						    		lblNewLabel_1_5_1.setVisible(true);
					            }else {
					            	addressList.setVisible(true);
					                addressField.setVisible(false);
						            phoneField.setVisible(false);
						            nameField.setText("");
					            }    
					       }
					}
				});
				addressList.setVisible(false);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(150, 138, 103, 25);
		orderUseField.add(scrollPane_3);
		
				phoneList = new JList<>();
				scrollPane_3.setViewportView(phoneList);
				phoneList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						 if (!e.getValueIsAdjusting()) { // 防止重複觸發
							 int index = phoneList.getSelectedIndex();  // 取得選中的 index
					            if (index >= 0 && index < phoneUser.size()) {
					                phoneField.setText(phoneUser.get(index));
					                addressField.setText(addressUser.get(index));
					                nameField.setText(nameUser.get(index));
					                phoneList.setVisible(false);
					                addressField.setVisible(true);
						            phoneField.setVisible(true);
						            lblNewLabel_1_4_1.setVisible(true);
						    		lblNewLabel_1_3_1.setVisible(true);
						    		lblNewLabel_1_5_1.setVisible(true);
					            }else {
					            	phoneList.setVisible(true);
					                addressField.setVisible(false);
						            phoneField.setVisible(false);
						            nameField.setText("");
					            }
					        }
					}
				});
				phoneList.setVisible(false);
		if(member!=null &&member.getMemberNumber()!=null) {
			phoneUser = new ArrayList<>();
		    addressUser = new ArrayList<>();
		    nameUser = new ArrayList<>();
			List<Member> takeForUpdate=new MemberServiceImpl().idAllView(member.getMemberNumber());
			for(Member o:takeForUpdate)
			{
				phoneUser.add(o.getMemberPhone());
				addressUser.add(o.getMemberAddress());
				nameUser.add(o.getMemberName());
			}
			phoneList.setListData(phoneUser.toArray(new String[0]));
			addressList.setListData(addressUser.toArray(new String[0]));
		} else {
		    // 非會員或尚未登入的處理
			phoneUser = new ArrayList<>();
		    addressUser = new ArrayList<>();
		    nameUser = new ArrayList<>();
		    phoneList.setListData(new String[0]);
		    addressList.setListData(new String[0]);
		    phoneList.setVisible(false);            // 隱藏清單
		    addressList.setVisible(false);
		}
		
		JPanel title = new JPanel();
		title.setBackground(new Color(176, 176, 217));
		title.setBounds(0, 0, 586, 76);
		contentPane.add(title);
		title.setLayout(null);

		JLabel indexMessage = new JLabel("");
		indexMessage.setFont(new Font("新宋体", Font.BOLD | Font.ITALIC, 15));
		indexMessage.setHorizontalAlignment(SwingConstants.CENTER);
		indexMessage.setBounds(10, 12, 124, 27);
		title.add(indexMessage);

		JLabel indexMessage_1 = new JLabel("芳香療法");
		indexMessage_1.setFont(new Font("微軟正黑體", Font.BOLD, 26));
		indexMessage_1.setHorizontalAlignment(SwingConstants.CENTER);
		indexMessage_1.setBounds(225, 10, 120, 56);
		title.add(indexMessage_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(98, 40, 168));
		panel.setBounds(0, 485, 586, 68);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("以上跟錢有關的僅供參考");
		lblNewLabel.setForeground(new Color(255, 193, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 13));
		lblNewLabel.setBounds(142, 43, 295, 15);
		panel.add(lblNewLabel);

		JPanel orderCheck = new JPanel();
		orderCheck.setBackground(new Color(223, 232, 244));
		cardPanel.add(orderCheck, "orderCheck");
		orderCheck.setLayout(null);
		orderCheck.setName("orderCheck");

		JTextArea orderOutput = new JTextArea();
		orderOutput.setBounds(50, 168, 472, 194);
		orderCheck.add(orderOutput);

		orderShow = new JLabel("查詢訂單：");
		orderShow.setHorizontalAlignment(SwingConstants.CENTER);
		orderShow.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		orderShow.setBounds(10, 15, 209, 49);
		orderCheck.add(orderShow);

		orderFreeShow2 = new JLabel("客戶姓名：");
		orderFreeShow2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		orderFreeShow2.setBounds(75, 74, 85, 36);
		orderCheck.add(orderFreeShow2);

		orderFreeShow = new JLabel("客戶電話：");
		orderFreeShow.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		orderFreeShow.setBounds(273, 74, 85, 36);
		orderCheck.add(orderFreeShow);

		freeCustNameField = new JTextField();
		freeCustNameField.setBounds(159, 74, 104, 33);
		orderCheck.add(freeCustNameField);
		freeCustNameField.setColumns(10);

		freeCustPhoneField = new JTextField();
		freeCustPhoneField.setColumns(10);
		freeCustPhoneField.setBounds(355, 77, 104, 33);
		orderCheck.add(freeCustPhoneField);

		orderMemberShow = new JLabel("訂單姓名：");
		orderMemberShow.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		orderMemberShow.setBounds(145, 61, 85, 36);
		orderCheck.add(orderMemberShow);

		userMemberNameField = new JTextField();
		userMemberNameField.setColumns(10);
		userMemberNameField.setBounds(273, 67, 104, 33);
		orderCheck.add(userMemberNameField);
		
		if(member!=null)
		{
			orderMemberShow.setVisible(true);
			userMemberNameField.setVisible(true);
			orderFreeShow2.setVisible(false);
			orderFreeShow.setVisible(false);
			freeCustNameField.setVisible(false);
			freeCustPhoneField.setVisible(false);
		}else {
			orderMemberShow.setVisible(false);
			userMemberNameField.setVisible(false);
			orderFreeShow2.setVisible(true);
			orderFreeShow.setVisible(true);
			freeCustNameField.setVisible(true);
			freeCustPhoneField.setVisible(true);
			
		}

		JPanel forCustOrder = new JPanel();
		forCustOrder.setBackground(new Color(240, 234, 247));
		cardPanel.add(forCustOrder, "forCustOrder");
		forCustOrder.setLayout(null);
		forCustOrder.setName("forCustOrder");
		
		JLabel lblNewLabel_1_6 = new JLabel("姓名：");
		lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_6.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1_6.setBounds(154, 83, 69, 25);
		forCustOrder.add(lblNewLabel_1_6);
		
		custNameField = new JTextField();
		custNameField.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		custNameField.setColumns(10);
		custNameField.setBounds(248, 83, 103, 26);
		forCustOrder.add(custNameField);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("寄送住址：");
		lblNewLabel_1_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_2.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1_3_2.setBounds(138, 142, 85, 25);
		forCustOrder.add(lblNewLabel_1_3_2);
		
		custAddressField = new JTextField();
		custAddressField.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		custAddressField.setColumns(10);
		custAddressField.setBounds(248, 142, 103, 26);
		forCustOrder.add(custAddressField);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("連絡電話：");
		lblNewLabel_1_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4_2.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1_4_2.setBounds(138, 201, 85, 25);
		forCustOrder.add(lblNewLabel_1_4_2);
		
		custPhoneField = new JTextField();
		custPhoneField.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		custPhoneField.setColumns(10);
		custPhoneField.setBounds(248, 201, 103, 26);
		forCustOrder.add(custPhoneField);
		
		JLabel 訂單資料填寫 = new JLabel("客戶訂單資料填寫");
		訂單資料填寫.setHorizontalAlignment(SwingConstants.CENTER);
		訂單資料填寫.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		訂單資料填寫.setBounds(122, 10, 284, 41);
		forCustOrder.add(訂單資料填寫);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		lblNewLabel_5.setBounds(59, 323, 477, 63);
		forCustOrder.add(lblNewLabel_5);

		/************************************ ↓按鈕大魔王↓************************************/
		
		CardLayout cardLayoutForChange = (CardLayout) cardPanel.getLayout();
		
		JButton memberLoginButton = new JButton("會員登入");
		memberLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user=usernameLoginField.getText();
				String pass=ButtonTool.changePass(passwordLoginField);
				if(new MemberServiceImpl().userLogin(user, pass))
				{
					member=new MemberServiceImpl().userView(user, pass);
					if(member!=null) {
						JOptionPane.showMessageDialog(null, "歡迎登入選購我們的產品！");
						ButtonTool.saveMember(member);
				        ButtonTool.updateButtonText(ButtonTool.getCurrentCard(cardPanel), member!=null, loginButton, changeButton);
						changeButtonView();
						cardLayoutForChange.show(cardPanel, "shopIndex");
						cardPanel.revalidate();
						cardPanel.repaint();
					}
				}else {
					JOptionPane.showMessageDialog(null, "請確認是否為會員！");
		            usernameLoginField.setText("");
		            passwordLoginField.setText("");
		    		ButtonTool.loginView(usernameLoginField, passwordLoginField);
				}
			}
		});
		memberLoginButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		memberLoginButton.setBounds(113, 239, 118, 34);
		login.add(memberLoginButton);

		JButton staffLoginButton = new JButton("員工登入");
		staffLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username=usernameLoginField.getText();
				String password=ButtonTool.changePass(passwordLoginField);
				if(new StaffServiceImpl().staffLogin(username, password)!=null)
				{
					StaffSystem frame = new StaffSystem();
					frame.setVisible(true);
					dispose();
				}else {
		            JOptionPane.showMessageDialog(null, "請確認是否為員工！");
		            usernameLoginField.setText("");
		            passwordLoginField.setText("");
		    		ButtonTool.loginView(usernameLoginField, passwordLoginField);
				}
			}
		});
		staffLoginButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		staffLoginButton.setBounds(113, 304, 118, 34);
		login.add(staffLoginButton);
		
		JButton clearButton3 = new JButton("取消");
		clearButton3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				usernameLoginField.setText("");
				passwordLoginField.setText("");
				ButtonTool.loginView(usernameLoginField, passwordLoginField);
			}
		});
		clearButton3.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		clearButton3.setBounds(320, 239, 97, 34);
		login.add(clearButton3);

		JButton enterCreatButton = new JButton("確認加入");
		enterCreatButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name=memberName.getText();
				String username=memberUsername.getText();
				String password=memberPassword.getText();
				String address=memberAddress.getText();
				String phone=memberPhone.getText();
				StringBuilder createView = new StringBuilder();

				if(name==null)
				{
					createView.append("請輸入姓名");
				}
				if(!ButtonTool.isUserPass(username))
				{
					createView.append("請輸入正確的帳號，要由英文、數字組成。");
				}else if(new MemberServiceImpl().checkUsername(username)) {
					createView.append("帳號已重複使用。");
				}
				if(!ButtonTool.isUserPass(password))
				{
					createView.append("請輸入正確的密碼，要由英文、數字組成。");
				}
				if(address==null)
				{
					createView.append("請輸入地址。");
				}
				if(!ButtonTool.isPhone(phone))
				{
					createView.append("請輸入正確電話，09開頭、後8碼。");
				}
				ButtonTool.waring(createMes, createView, ButtonTool.createRule());
				if(name!=null&&username!=null&&password!=null&&address!=null&&phone!=null) {
					member=new Member(name,username,password,address,phone,true);
					if(new MemberServiceImpl().addMember(member))
					{
				        ButtonTool.updateButtonText(ButtonTool.getCurrentCard(cardPanel), member!=null, loginButton, changeButton);
			            int result = JOptionPane.showConfirmDialog(
			            	    null,
			            	    "恭喜加入會員！\n是否現在進入會員購物頁面？",
			            	    "加入成功",
			            	    JOptionPane.YES_NO_OPTION,
			            	    JOptionPane.QUESTION_MESSAGE
			            	);
	
			            if (result == JOptionPane.YES_OPTION) {
			    			cardLayoutForChange.show(cardPanel, "shopIndex");
			    			cardPanel.revalidate();
			    			cardPanel.repaint();
			           	}
					}
				}else {
		            JOptionPane.showMessageDialog(null, "請確認是否資料有問題");
				}
			}
		});
		enterCreatButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		enterCreatButton.setBounds(141, 334, 113, 36);
		create.add(enterCreatButton);
		
		JButton clearButton = new JButton("重新填寫");
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				memberNameField.setText("");
	            memberUsername.setText("");
	            memberPassword.setText("");
	            memberAddress.setText("");
	            memberPhone.setText("");
			}
		});
		clearButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		clearButton.setBounds(318, 334, 113, 36);
		create.add(clearButton);

		JButton orderPlusButton = new JButton("新增");
		orderPlusButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String productName = chooselist.getSelectedItem().toString();
				int quantity = Integer.parseInt(count.getValue().toString());
				int price = new ProductServiceImpl().productPrice(productName);
				OrderTool.orderUse(productName, quantity, price, orderView,member);
				chooselist.setSelectedItem(1);
				count.setValue(0);
			}
		});
		orderPlusButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		orderPlusButton.setBounds(394, 63, 76, 37);
		shopIndex.add(orderPlusButton);

		JButton clearButton4 = new JButton("取消");
		clearButton4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chooselist.setSelectedItem(1);
				count.setValue(0);
			}
		});
		clearButton4.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		clearButton4.setBounds(481, 63, 76, 37);
		shopIndex.add(clearButton4);

		JButton orderEnterButton = new JButton("確認訂購");
		orderEnterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(member!=null)
				{
					OrderTool.orderCheckUse(member.getMemberNumber(),orderView);
				}else {
					OrderData orderData=new OrderData();
					FileTool.saveFiled(orderData, "custOrderNotyet.txt");
					cardLayoutForChange.show(cardPanel, "forCustOrder");
					JOptionPane.showMessageDialog(null, "請先填寫寄送資料。");
					cardPanel.revalidate();
	    			cardPanel.repaint();
				}
				
			}
		});
		orderEnterButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		orderEnterButton.setBounds(30, 353, 113, 37);
		shopIndex.add(orderEnterButton);

		JButton saveFileButton = new JButton("再考慮");
		saveFileButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(member!=null)
				{
					OrderTool.custOrderCheck(orderView, member);
					int rowCount = model.getRowCount();
					if(rowCount>0)
					{
						OrderTool.backupOrderIfCancelled(rowCount, productView,member.getMemberNumber());
						chooselist.setSelectedItem(1);
						count.setValue(0);
					}
				}else {
					
				}
			}
		});
		saveFileButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		saveFileButton.setBounds(168, 353, 110, 37);
		shopIndex.add(saveFileButton);

		JButton cansleButton = new JButton("取消選購");
		cansleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model.setRowCount(0);
			}
		});
		cansleButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		cansleButton.setBounds(448, 353, 110, 37);
		shopIndex.add(cansleButton);
		
		JButton deleteButton = new JButton("刪除某項");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = orderView.getSelectedRow(); 
	            if (selectedRow != -1) {
	                model.removeRow(selectedRow); 
	            } else {
	                JOptionPane.showMessageDialog(null, "請先選取一列要刪除的資料！");
	            }
			}
		});
		deleteButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		deleteButton.setBounds(313, 353, 110, 37);
		shopIndex.add(deleteButton);
		
		JButton enterCreatCustButton = new JButton("確認資料");
		enterCreatCustButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name=custNameField.getText();
				String address=custAddressField.getText();
				String phone=custPhoneField.getText();
				if(name!=null&&address!=null&&ButtonTool.isPhone(phone))
				{
					Member cust=new Member();
					cust.setMemberName(name);
					cust.setMemberAddress(address);
					cust.setMemberPhone(phone);
					new MemberServiceImpl().addCust(cust);
					String saveCustNumber=new MemberServiceImpl().getMemberNumber(name);
					FileTool.saveFiled(cust, "custMemberMemry.txt");
					OrderData orderData=(OrderData)FileTool.readFiled("custOrderNotyet.txt");
					OrderAll custOrder=orderData.getOrderall();
					custOrder.setMemberNumber(saveCustNumber);
					orderData.setOrderall(custOrder);
					FileTool.saveFiled(orderData, "custOrderNotyet.txt");
					CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
					cardLayout.show(cardPanel, "shopIndex");
				}else {
					JOptionPane.showMessageDialog(null, "請務必填寫寄送資料。");
				}
			}
		});
		enterCreatCustButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		enterCreatCustButton.setBounds(137, 277, 113, 36);
		forCustOrder.add(enterCreatCustButton);
		
		JButton clearButton5 = new JButton("重新填寫");
		clearButton5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				custNameField.setText("");
				custAddressField.setText("");
				custPhoneField.setText("");
			}
		});
		clearButton5.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		clearButton5.setBounds(314, 277, 113, 36);
		forCustOrder.add(clearButton5);

		JButton enterUpdateButton_1 = new JButton("確認修改");
		enterUpdateButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String password=passwordField.getText();
				if(ButtonTool.isUserPass(password)) 
				{
					new MemberServiceImpl().updatePassword(member.getMemberUsername(), password);
					JOptionPane.showMessageDialog(null, "修改好密碼，記得下次要使用這次密碼。");
				}else {
					JOptionPane.showMessageDialog(null, "須包含英文、數字各1，且2~8個字元。");
				}
			}
		});
		enterUpdateButton_1.setBounds(23, 185, 103, 36);
		memberItem.add(enterUpdateButton_1);
		enterUpdateButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		JButton clearButton2 = new JButton("重新填寫");
		clearButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setText("");
			}
		});
		clearButton2.setBounds(147, 185, 95, 36);
		memberItem.add(clearButton2);
		clearButton2.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		JButton enterUpdateButton_1_1 = new JButton("確認修改");
		enterUpdateButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String address=addressField.getText();
				String phone=phoneField.getText();
				String name=nameField.getText();
				if(address!=null&&ButtonTool.isPhone(phone)) {
					new MemberServiceImpl().updateAddressAndPhone(member.getMemberNumber(),name, phone, address);
					JOptionPane.showMessageDialog(null, "修改完成。");
				}else {
					JOptionPane.showMessageDialog(null, "至少地址跟電話需要填寫。");
				}
			}
		});
		enterUpdateButton_1_1.setBounds(14, 187, 103, 36);
		orderUseField.add(enterUpdateButton_1_1);
		enterUpdateButton_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		JButton clearButton2_1 = new JButton("重新填寫");
		clearButton2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				phoneField.setText("");
				addressField.setText("");
				nameOrderField.setText("");
			}
		});
		clearButton2_1.setBounds(175, 187, 95, 36);
		orderUseField.add(clearButton2_1);
		clearButton2_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		JRadioButton chooseAddressList = new JRadioButton("用地址查找");
		chooseAddressList.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
		            phoneList.setVisible(false);
					nameOrderField.setVisible(false);
					addressField.setVisible(false);
					phoneField.setVisible(false);
					lblNewLabel_1_4_1.setVisible(false);
					lblNewLabel_1_3_1.setVisible(false);
					lblNewLabel_1_5_1.setVisible(false);
			        nameField.setVisible(false);
		            addressList.setVisible(true);

		        }
			}
		});
		addOrPhone.add(chooseAddressList);
		chooseAddressList.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		chooseAddressList.setBackground(new Color(222, 189, 242));
		chooseAddressList.setBounds(28, 106, 116, 23);
		orderUseField.add(chooseAddressList);
		
		JRadioButton choosePhoneList = new JRadioButton("用電話查找：");
		choosePhoneList.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					addressList.setVisible(false);
					nameOrderField.setVisible(false);
					addressField.setVisible(false);
					phoneField.setVisible(false);
					lblNewLabel_1_4_1.setVisible(false);
					lblNewLabel_1_3_1.setVisible(false);
					lblNewLabel_1_5_1.setVisible(false);
			        nameField.setVisible(false);
					phoneList.setVisible(true);
		            }
	            }
		});
		addOrPhone.add(choosePhoneList);
		choosePhoneList.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		choosePhoneList.setBackground(new Color(222, 189, 242));
		choosePhoneList.setBounds(28, 141, 116, 23);
		orderUseField.add(choosePhoneList);
				
		JRadioButton rdbtnNewRadioButton = new JRadioButton("新增寄送資料");
		rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					chooseAddressList.setVisible(false);
					choosePhoneList.setVisible(false);
					addressList.setVisible(false);
					phoneList.setVisible(false);
		            // 新增時：只開姓名欄位，其他都關
		            nameOrderField.setVisible(true);
		            addressField.setVisible(true);
		            phoneField.setVisible(true);
		            lblNewLabel_1_4_1.setVisible(true);
		    		lblNewLabel_1_3_1.setVisible(true);
		    		lblNewLabel_1_5_1.setVisible(true);

		            // 清空內容
		            nameOrderField.setText("");
		            addressField.setText("");
		            phoneField.setText("");
		        }
			}
		});
		memberAddressPhoneGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		rdbtnNewRadioButton.setBackground(new Color(222, 189, 242));
		rdbtnNewRadioButton.setBounds(6, 30, 116, 23);
		orderUseField.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("修改寄送資料");
		rdbtnNewRadioButton_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					addressList.setVisible(false);
					phoneList.setVisible(false);
					nameOrderField.setVisible(false);
					addressField.setVisible(false);
					phoneField.setVisible(false);
					lblNewLabel_1_4_1.setVisible(false);
					lblNewLabel_1_3_1.setVisible(false);
					lblNewLabel_1_5_1.setVisible(false);
			        nameField.setVisible(false);
		            // 開放選擇方式
		            chooseAddressList.setVisible(true);
		            choosePhoneList.setVisible(true);
		        }
			}
		});
		memberAddressPhoneGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		rdbtnNewRadioButton_1.setBackground(new Color(222, 189, 242));
		rdbtnNewRadioButton_1.setBounds(154, 30, 116, 23);
		orderUseField.add(rdbtnNewRadioButton_1);
		
		chooseAddressList.setVisible(false);
		choosePhoneList.setVisible(false);
		nameOrderField.setVisible(false);
		addressField.setVisible(false);
		phoneField.setVisible(false);
		lblNewLabel_1_4_1.setVisible(false);
		lblNewLabel_1_3_1.setVisible(false);
		lblNewLabel_1_5_1.setVisible(false);
        nameField.setVisible(false);
		
		JButton enterUpdateButton_1_1_1 = new JButton("回購物頁");
		enterUpdateButton_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayoutForChange.show(cardPanel, "shopIndex");
				cardPanel.revalidate();
				cardPanel.repaint();

			}
		});
		enterUpdateButton_1_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		enterUpdateButton_1_1_1.setBounds(223, 354, 103, 36);
		fresh.add(enterUpdateButton_1_1_1);
		
		JButton orderSearchCheckButton = new JButton("查詢");
		orderSearchCheckButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String usename=userMemberNameField.getText()!=null? userMemberNameField.getText():"";
				String name=freeCustNameField.getText()!=null ? freeCustNameField.getText():"";
				String phone=freeCustPhoneField.getText()!=null ? freeCustPhoneField.getText():"";
					if(isLoggedIn)
					{
						if(!usename.isEmpty())
							OrderTool.showOrder(orderOutput, usename);
						else
							JOptionPane.showMessageDialog(null, "請先填入資訊才有辦法查詢。");
					}else {
						if(!name.isEmpty()&&!phone.isEmpty())
							OrderTool.showOrder(orderOutput, name, phone);
						else
							JOptionPane.showMessageDialog(null, "請先填入資訊才有辦法查詢。");
						}
			}
		});
		orderSearchCheckButton.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		orderSearchCheckButton.setBounds(222, 120, 91, 36);
		orderCheck.add(orderSearchCheckButton);
		orderOutput.setText("");
		if(isLoggedIn) OrderTool.showOrder(orderOutput, member);
		
		JButton leave = new JButton("關閉");
		leave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		leave.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		leave.setBounds(461, 10, 102, 29);
		panel.add(leave);
		
		/** 按鈕根據狀態來切換 **/

		loginButton = new JButton("登入會員");
		loginButton.setBounds(21, 10, 109, 29);
		panel.add(loginButton);
		loginButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		changeButton = new JButton("加入會員");
		changeButton.setBounds(165, 10, 109, 29);
		panel.add(changeButton);
		changeButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		JButton orderSearchButton = new JButton("查詢訂單");
		orderSearchButton.setBounds(322, 10, 102, 29);
		panel.add(orderSearchButton);
		orderSearchButton.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentCard = ButtonTool.getCurrentCard(cardPanel);
		        if (member!=null) {
		            // ✅ 已登入 → 修改會員資料頁面（fresh）
		        	cardLayoutForChange.show(cardPanel, "shopIndex");
		        } else {
		            if ("login".equals(currentCard)) {
		                // 回到首頁
		            	cardLayoutForChange.show(cardPanel, "shopIndex");
		            } else{
		            	cardLayoutForChange.show(cardPanel, "login");		            	
		            }
		        }
		        ButtonTool.updateButtonText(ButtonTool.getCurrentCard(cardPanel), member!=null, loginButton, changeButton);
		        cardPanel.revalidate();
    			cardPanel.repaint();
			}
		});
		changeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentCard = ButtonTool.getCurrentCard(cardPanel);
		        if (member!=null) {
		            // ✅ 已登入 → 修改會員資料頁面（fresh）
		        	cardLayoutForChange.show(cardPanel, "fresh");
		        } else {
		            if ("create".equals(currentCard)) {
		                // 回到首頁
		            	cardLayoutForChange.show(cardPanel, "shopIndex");		            	
		            }else {
		                // 未登入 → 進入加入會員頁面
		            	cardLayoutForChange.show(cardPanel, "create");
		            }
		        }
		        ButtonTool.updateButtonText(ButtonTool.getCurrentCard(cardPanel), member!=null, loginButton, changeButton);
		        cardPanel.revalidate();
    			cardPanel.repaint();
			}
		});
		orderSearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentCard = ButtonTool.getCurrentCard(cardPanel);
	            if ("orderCheck".equals(currentCard)) {
	            	cardLayoutForChange.show(cardPanel, "shopIndex");
	            	orderSearchButton.setText("查詢訂單");
	            }else {
	            	cardLayoutForChange.show(cardPanel, "orderCheck");
	            	orderSearchButton.setText("回購物頁");
	            }
	            
	            String username=userMemberNameField.getText();
				String freename=freeCustNameField.getText();
				String freephone=freeCustPhoneField.getText();
				if(member!=null) {
					ButtonTool.searchUserOrder(orderOutput, username);
				}else {
					ButtonTool.searchFreeOrder(orderOutput, freename, freephone);
				}
				cardPanel.revalidate();
    			cardPanel.repaint();
			}
		});

	}
	
	private void changeButtonView(){
		if(member!=null)
		{	
			FileTool.saveFiled(member,"memberMemry.txt");
			imdexMes.setText("歡迎"+member.getMemberName()+"回來選購！＼OwO／");
			memberNameField.setText(member.getMemberName());
			usernameField.setText(member.getMemberUsername());
			freeCustNameField.setVisible(false);
			freeCustPhoneField.setVisible(false);
			userMemberNameField.setVisible(true);
			orderMemberShow.setVisible(true);
        	userMemberNameField.setVisible(true);
        	orderFreeShow2.setVisible(false);
        	orderFreeShow.setVisible(false);
        	freeCustNameField.setVisible(false);
        	freeCustPhoneField.setVisible(false);
        	orderShow.setText("請查閱"
        			+ member.getMemberName()+"底下已存入的訂單，也可透過姓名直接找出對應的訂單。");
		}else {
			member = null;
            isLoggedIn = false;
			imdexMes.setText("歡迎來選購~>w<~");
			memberNameField.setText("");
			usernameField.setText("");
			freeCustNameField.setVisible(true);
			freeCustPhoneField.setVisible(true);
			userMemberNameField.setVisible(false);
			orderMemberShow.setVisible(false);
        	userMemberNameField.setVisible(false);
        	orderFreeShow2.setVisible(true);
        	orderFreeShow.setVisible(true);
        	freeCustNameField.setVisible(true);
        	freeCustPhoneField.setVisible(true);
		}
	}
}
