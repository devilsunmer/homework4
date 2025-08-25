精油產品選購

🌿 探索你的芳香療癒旅程

簡單、直覺、專屬你的芳香精油體驗介面。

我們打造了一個讓你輕鬆選購、自在管理的芳香精油療法介面，將複雜簡化，讓選擇療癒的每一步，都成為日常的一部分。無論是初次體驗者，還是熱愛芳療的你，都能在這裡找到最適合自己的香氣組合與訂購節奏。

✨ UI介面特色：

快速香氛選擇：簡單幾步，依個人喜好與功效（如放鬆、提神）快速選出合適精油產品。

會員專屬功能：登入後可查看與修改上次訂單、調整精油數量、享有專屬折扣與優惠提醒。

即時訂單查詢與編輯：不論是否為會員，皆可在下單前查詢與確認選購明細，確保每一次購買都符合需求。

歷史紀錄讀取功能：會員可一鍵載入過往喜愛精油組合，節省重新搭配的時間。

簡潔自然的視覺風格：溫潤色調與直覺操作設計，讓選購過程也成為一種舒壓儀式。

🕯️ 打造屬於你的小小芳香儀式

每一次呼吸的香氣，都是對生活的療癒回應。從選擇產品到完成訂單，我們的介面陪你溫柔而準確地走好每一步。

<img width="703" height="260" alt="image" src="https://github.com/user-attachments/assets/2077265a-3157-4a87-91b5-94646b61f341" />

接下來是我的設計初始化流程：
<img alt="image" src="work4網頁示意圖.png">

然後是我實際設計的大致目錄：

src/<br>
└── main/<br>
    └── java/<br>
        ├── controller/<br>
        │   ├── Shop.java<br>
        │   └── StaffSystem.java<br>
        │<br>
        ├── dao/<br>
        │   ├── MemberDao.java<br>
        │   ├── OrderAllDao.java<br>
        │   ├── OrderItemDao.java<br>
        │   ├── ProductDao.java<br>
        │   ├── ProductStockDao.java<br>
        │   ├── ProductSystemViewDao.java<br>
        │   └── StaffDao.java<br>
        │<br>
        │   └── impl/<br>
        │       ├── MemberDaoImpl.java<br>
        │       ├── OrderAllDaoImpl.java<br>
        │       ├── OrderItemDaoImpl.java<br>
        │       ├── ProductDaoImpl.java<br>
        │       ├── ProductStockDaoImpl.java<br>
        │       ├── ProductSystemViewDaoImpl.java<br>
        │       └── StaffDaoImpl.java<br>
        │<br>
        ├── model/<br>
        │   ├── Category.java<br>
        │   ├── Member.java<br>
        │   ├── OrderAll.java<br>
        │   ├── OrderData.java<br>
        │   ├── OrderItem.java<br>
        │   ├── Product.java<br>
        │   ├── ProductStock.java<br>
        │   ├── ProductSystemView.java<br>
        │   └── Staff.java<br>
        │<br>
        ├── service/<br>
        │   ├── MemberService.java<br>
        │   ├── OrderAllService.java<br>
        │   ├── OrderItemService.java<br>
        │   ├── ProductService.java<br>
        │   ├── ProductStockService.java<br>
        │   └── StaffService.java<br>
        │<br>
        │   └── impl/<br>
        │       ├── MemberServiceImpl.java<br>
        │       ├── OrderAllServiceImpl.java<br>
        │       ├── OrderItemServiceImpl.java<br>
        │       ├── ProductServiceImpl.java<br>
        │       ├── ProductStockServiceImpl.java<br>
        │       └── StaffServiceImpl.java<br>
        │<br>
        └── util/<br>
            ├── ButtonTool.java<br>
            ├── DbConnection.java<br>
            ├── FileTool.java<br>
            ├── OrderTool.java<br>
            ├── SQLTool.java<br>
            └── SystemTool.java<br>

剩下的部分可以參考我給的檔案內容來理解內容。



