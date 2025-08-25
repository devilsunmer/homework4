package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.Member;
import model.OrderAll;
import model.OrderItem;
import service.impl.MemberServiceImpl;
import service.impl.OrderAllServiceImpl;
import service.impl.OrderItemServiceImpl;
import service.impl.ProductServiceImpl;

public class ButtonTool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private static Timer restoreTimer; // 儲存目前的 Timer

	public static String changePass(JPasswordField pass) {
		String getPass = new String(pass.getPassword());
		return getPass;
	}

	public static void loginView(JTextField usernameField, JPasswordField passwordField) {
		String usernameText = "請輸入帳號";
		usernameField.setText(usernameText);
		usernameField.setForeground(new Color(192, 192, 192));
		usernameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (usernameField.getText().equals(usernameText)) {
					usernameField.setText("");
					usernameField.setForeground(new Color(0, 0, 0));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (usernameField.getText().trim().isEmpty()) {
					usernameField.setText(usernameText);
					usernameField.setForeground(new Color(192, 192, 192));
				}
			}
		});
		String passwordFieldText = "請輸入密碼";
		passwordField.setEchoChar((char) 0);
		passwordField.setText(passwordFieldText);
		passwordField.setForeground(new Color(192, 192, 192));
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (changePass(passwordField).equals(passwordFieldText)) {
					passwordField.setText("");
					passwordField.setEchoChar('●');
					passwordField.setForeground(new Color(0, 0, 0));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (changePass(passwordField).trim().isEmpty()) {
					passwordField.setEchoChar((char) 0);
					passwordField.setText(passwordFieldText);
					passwordField.setForeground(new Color(192, 192, 192));
				}
			}
		});
	}

	/** 帳號密碼正規表示法 */
	public static boolean isUserPass(String UserPass) {
		// 至少 1 個英文字母、1 個數字，且長度為 1-8 個字元
		String regex = "^(?=.*[a-zA-Z])(?=.*\\d).{1,8}$";
		return UserPass != null && UserPass.matches(regex);
	}

	/** 電話正規表示法 */
	public static boolean isPhone(String Phone) {
		// 由09開頭，後面8個數字
		String regex = "^09\\d{8}$";
		return Phone != null && Phone.matches(regex);
	}

	public static String createRule() {
		String view = "請切記要輸入正確的：\n" + "帳號密碼(皆須至少包含1個英文、數字，8個單位)、\n" + "電話格式(09、後8碼)、姓名跟地址都要輸入內容才可以。";
		return view;
	}

	/** 錯誤標語的設定 **/
	public static void waring(JTextPane textpaneName, StringBuilder warningView, String orignalString) {
		textpaneName.setText(warningView.toString());
		textpaneName.setForeground(new Color(255, 0, 0));
		textpaneName.setFont(new Font("微軟正黑體", Font.PLAIN, 24));

		if (restoreTimer != null && restoreTimer.isRunning()) {
			restoreTimer.stop();
		}

		restoreTimer = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textpaneName.setText(orignalString);
				textpaneName.setForeground(new Color(0, 0, 0));
				textpaneName.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
			}
		});

		restoreTimer.setRepeats(false);
		restoreTimer.start();
	}

	public static void centerText(JTextPane textPane) {
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
	}

	public static void saveMember(Member member) {
		/* 用來記憶而已 */ }

	public static Member tookMember(Member member) {
		return member;
	}

	public static StringBuilder memberOrCust(String name) {
		return null;

	}

	public static StringBuilder memberOrCust(String name, String phone) {
		return null;

	}

	public static String getCurrentCard(JPanel cardPanel) {
		for (Component comp : cardPanel.getComponents()) {
			if (comp.isVisible()) {
				String name = comp.getName();
				if (name != null)
					return name;
			}
		}
		return "shopIndex"; // fallback 預設頁面
	}

	public static void updateButtonText(String currentCard, boolean isLoggedIn, JButton loginButton,
			JButton changeButton) {
		if (isLoggedIn) {
			// ✅ 已登入：登入按鈕變成「登出」，加入會員變成「修改會員資料」
			loginButton.setText("登出");
			changeButton.setText("修改會員資料");
			return;
		}

		switch (currentCard) {
		case "login":
			loginButton.setText("回購物頁");
			changeButton.setText("加入會員");
			break;
		case "create":
			loginButton.setText("豋入會員");
			changeButton.setText("回購物頁");
			break;
		case "shopIndex":
		default:
			loginButton.setText("登入會員");
			changeButton.setText("加入會員");
			break;
		}
	}

	public static void searchUserOrder(JTextArea orderOutput, String username) {
		String number = new MemberServiceImpl().getMemberNumber(username);
		List<Member> member = new MemberServiceImpl().userView(username);
		List<OrderAll> check = new OrderAllServiceImpl().memberOrderView(number);
		StringBuilder sb = new StringBuilder();
		for (Member m : member) {
			sb.append("會員查詢到的訂購資料：\n");
			sb.append("訂單寄送姓名：").append(m.getMemberName()).append("\n");
			sb.append("訂單寄送連絡電話：").append(m.getMemberPhone()).append("\n");
			sb.append("訂單寄送地址：").append(m.getMemberAddress()).append("\n");
			for (OrderAll o : check) {
				sb.append("訂單下單日期：").append(o.getOrderDate()).append("\n");				
				List<OrderItem> check2 = new OrderItemServiceImpl().orderItemView(o.getOrderNumber());
					for (OrderItem x : check2) {
					String prname=new ProductServiceImpl().takeProductName(x.getProductNumber());
					sb.append("訂購產品：").append(prname).append("\n");
				}
				sb.append("訂單總金額：").append(o.getOrderSum()).append(" 元\n");
			}
		}
		orderOutput.setText(sb.toString());
	}

	public static void searchFreeOrder(JTextArea orderOutput, String name, String phone) {
		Member user = new MemberServiceImpl().freeUserUse(name,phone);
		List<OrderAll> check = new OrderAllServiceImpl().memberOrderView(user.getMemberNumber());
		StringBuilder sb = new StringBuilder();
		sb.append("客戶查詢到的訂購資料：\n");
		sb.append("訂單寄送姓名：").append(user.getMemberName()).append("\n");
		sb.append("訂單寄送連絡電話：").append(user.getMemberPhone()).append("\n");
		sb.append("訂單寄送地址：").append(user.getMemberAddress()).append("\n");
		for (OrderAll o : check) {
			sb.append("訂單下單日期：").append(o.getOrderDate()).append("\n");				
			List<OrderItem> check2 = new OrderItemServiceImpl().orderItemView(o.getOrderNumber());
				for (OrderItem x : check2) {
				String prname=new ProductServiceImpl().takeProductName(x.getProductNumber());
				sb.append("訂購產品：").append(prname).append("\n");
			}
			sb.append("訂單總金額：").append(o.getOrderSum()).append(" 元\n");
		}
		orderOutput.setText(sb.toString());
	}
	
	public static void printPanel(JPanel panel) {
	    PrinterJob job = PrinterJob.getPrinterJob();
	    job.setJobName("列印 JPanel");

	    job.setPrintable((graphics, pageFormat, pageIndex) -> {
	        if (pageIndex > 0) {
	            return Printable.NO_SUCH_PAGE;
	        }

	        Graphics2D g2d = (Graphics2D) graphics;
	        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

	        // 縮放以適配頁面
	        double panelWidth = panel.getWidth();
	        double panelHeight = panel.getHeight();
	        double pageWidth = pageFormat.getImageableWidth();
	        double pageHeight = pageFormat.getImageableHeight();

	        double scaleX = pageWidth / panelWidth;
	        double scaleY = pageHeight / panelHeight;
	        double scale = Math.min(scaleX, scaleY); // 避免超出頁面

	        g2d.scale(scale, scale);

	        panel.printAll(g2d);
	        return Printable.PAGE_EXISTS;
	    });

	    if (job.printDialog()) {
	        try {
	            job.print();
	        } catch (PrinterException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(panel, "列印失敗：" + e.getMessage());
	        }
	    }
	}


}
