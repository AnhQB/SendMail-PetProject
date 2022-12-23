package fsoft.jits.mybatis.model;

public class Product {
	private int id;
	private int categoryId;
	private int subcategoryId;
	private String name;
	private String price;
	private byte[] image;
	
	public Product() {
		super();
	}


	public Product(int id, int categoryId, int subcategoryId, String name, String price, byte[] image) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.name = name;
		this.price = price;
		this.image = image;
	}
	
	

	public Product(int categoryId, int subcategoryId, String name, String price, byte[] image) {
		super();
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.name = name;
		this.price = price;
		this.image = image;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	 
}
