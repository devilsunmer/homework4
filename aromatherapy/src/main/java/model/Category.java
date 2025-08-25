package model;

public enum Category {
	    FLOWER("花類"),
	    LEAF("葉片類"),
	    ROOT("根莖類"),
	    WOOD("木質類"),
	    FRUIT("果實類"),
	    SEED("種子類");

	    private final String chineseName;

		private Category(String chineseName) {
			this.chineseName = chineseName;
		}

		public String getChineseName() {
			return chineseName;
		}
	    
		public static Category fromChinese(String chinese) {
	        for (Category cate : Category.values()) {
	            if (cate.getChineseName().equals(chinese)) {
	                return cate;
	            }
	        }
	        return null;
	    }
		
		public static String[] modelFoComobox() {
			Category[] categories = Category.values();
		    String[] result = new String[categories.length+1];
		    result[0]="";
		    for (int i = 0; i < categories.length; i++) {
		        result[i+1] = categories[i].getChineseName();
		    }

		    return result;
			
		}
	

}
